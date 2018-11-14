package viewer.fb.rei.footballmatchscheduleviewer.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_page.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.adapter.ARG_OBJECT
import viewer.fb.rei.footballmatchscheduleviewer.adapter.RecycleMatchScheduleAdapter
import viewer.fb.rei.footballmatchscheduleviewer.adapter.RecycleMatchScheduleFaveAdapter
import viewer.fb.rei.footballmatchscheduleviewer.model.LeagueEventModal
import viewer.fb.rei.footballmatchscheduleviewer.structure.League
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEvent
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEventList
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueList
import viewer.fb.rei.footballmatchscheduleviewer.util.*

class MatchSchedule : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var shim: ShimmerFrameLayout
    private lateinit var lay_match_list: RecyclerView
    private lateinit var swipe: SwipeRefreshLayout
    private val leagueDesc: MutableList<String> = mutableListOf("")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(
                R.layout.fragment_page, container, false
        )
        lay_match_list = rootView.findViewById(R.id.match_list)
        shim = rootView.findViewById(R.id.shimmer_view_container)
        swipe = rootView.findViewById(R.id.swipe_layout)
        swipe.setColorSchemeColors(*loading)
        swipe.setOnRefreshListener(this)
        generateListSchedule()
        return rootView
    }

    fun generateListSchedule() {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val functionRest = if (getInt(ARG_OBJECT) == 0) {
                "eventspastleague"
            } else if (getInt(ARG_OBJECT) == 1) {
                "eventsnextleague"
            } else {
                ""
            }
            if (!functionRest.isEmpty()) {
                getAllLeagueData(functionRest)
            } else {
                dbg("FAVE Display")
                lateinit var faveRecord: List<LeagueEventModal>
                activity?.database?.use {
                    val result = select(LeagueEventModal.TableName)
                    faveRecord = result.parseList(classParser<LeagueEventModal>())
                    swipe.stopRefresh()
                    shim.stopShimmer()
                }
                setFaveLeagueDisplay(faveRecord)
            }
        }
    }

    private fun getLeagueList(events: LeagueList, functionRest: String) {
        val listEvents = events.leagues!!
        leagueDesc.clear()
        var idLiga: String = ""
        for (list in listEvents) {
            if (idLiga.isEmpty()) {
                idLiga = list.idLeague
            }
            leagueDesc.add(list.strLeague)
        }
        list_league.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagueDesc)

        dbg("VALUE " + idLiga)
        getLeagueShchedule(functionRest, idLiga)
        list_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, idx: Int, p3: Long) {
                idLiga = listEvents.get(idx).idLeague
                dbg("VALUE " + idLiga)
                getLeagueShchedule(functionRest, idLiga)
            }

        }
    }

    fun getAllLeagueData(functionRest: String):LeagueList {
        val leagues = arrayListOf<League>()
        var value = LeagueList(leagues)
        apiService.getAllLeague(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { league ->
                            getLeagueList(league, functionRest)
                        },
                        {
                            err("Batal")
                            it.printStackTrace()
                        }
                )
                .collectDispose(bin)
        return value
    }

    fun getLeagueShchedule(functionRest: String, idLiga: String): LeagueEventList {
        var value = LeagueEventList()
        dbg("CALL Schedule liga $idLiga")
        apiService.getLeagueSchedule(apiKey, functionRest, idLiga)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { league ->
                            shim.stopShimmer()
                            swipe.stopRefresh()
                            list_league.visible()
                            getLeagueData(league)
                        },
                        {
                            shim.stopShimmer()
                            swipe.stopRefresh()
                            err("Batal")
                            it.printStackTrace()
                        }
                )
                .collectDispose(bin)
        return value
    }

    private fun getLeagueData(events: LeagueEventList) {
        val listEvents = events.events!!
        dbg("listleagueData" + listEvents.size)
        setLeagueDisplay(listEvents)
    }

    private fun setFaveLeagueDisplay(listEvents: List<LeagueEventModal>) {
        // TODO kenapa kalau pakai matchlist biasa jadinya error
//        match_list.layoutManager = LinearLayoutManager(ctx)
//        match_list.adapter = RecycleMatchScheduleFaveAdapter(ctx, listEvents) {
//            startActivity<MatchScheduleDetailFave>("eventObject" to it)
        lay_match_list.layoutManager = LinearLayoutManager(ctx)
        lay_match_list.adapter = RecycleMatchScheduleFaveAdapter(ctx, listEvents) {
            startActivity<MatchScheduleDetailFave>("eventObject" to it)
        }
    }

    private fun setLeagueDisplay(listEvents: List<LeagueEvent>) {
        match_list.layoutManager = LinearLayoutManager(context)
        match_list.adapter = RecycleMatchScheduleAdapter(context, listEvents) {
            startActivity<MatchScheduleDetail>("eventObject" to it)
        }
    }

    override fun onResume() {
        super.onResume()
        shim.startShimmerAnimation()
        dbg("get data")
        generateListSchedule()
    }

    override fun onDestroy() {
        shim.stopShimmerAnimation()
        super.onDestroy()
    }

    override fun onRefresh() {
        generateListSchedule()
    }

}