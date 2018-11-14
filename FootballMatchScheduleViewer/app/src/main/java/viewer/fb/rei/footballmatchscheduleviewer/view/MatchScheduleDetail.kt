package viewer.fb.rei.footballmatchscheduleviewer.view

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.R.drawable.ic_add_to_favorites
import viewer.fb.rei.footballmatchscheduleviewer.R.drawable.ic_added_to_favorites
import viewer.fb.rei.footballmatchscheduleviewer.R.id.add_to_favorite
import viewer.fb.rei.footballmatchscheduleviewer.R.menu.detail_menu
import viewer.fb.rei.footballmatchscheduleviewer.model.LeagueEventModal
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEvent
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEventList
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueTeam
import viewer.fb.rei.footballmatchscheduleviewer.structure.Team
import viewer.fb.rei.footballmatchscheduleviewer.util.*
import java.text.SimpleDateFormat

class MatchScheduleDetail : BaseActivity() {

    private lateinit var teamHome: Team
    private lateinit var teamAway: Team
    private var eventObject: LeagueEvent? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var currentIcon = ic_add_to_favorites
    private var idEvent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        eventObject = intent.getSerializableExtra("eventObject") as LeagueEvent
        idEvent = eventObject?.idEvent!!
        if (checkFavorite(idEvent)) {
            currentIcon = ic_added_to_favorites
        }
        dbg("SET LAYOUT")
        callLeagueEvent()
        callLeagueTeam(eventObject?.idHomeTeam.toString(), true)
        callLeagueTeam(eventObject?.idAwayTeam.toString(), false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        dbg("SET MENU")
        setFave(currentIcon)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == add_to_favorite) {
            addRemoveToFavorite()
        }
        return true
    }

    fun callLeagueEvent() {
        dbg("Call Event")
        apiService.getLookUpEvent(apiKey, eventObject?.idEvent!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { league ->
                            getLeague(league)
                        },
                        {
                            err("Failed Import getLookUpEvent ${eventObject?.idEvent}")
                            err(it.message!!)
                            it.printStackTrace()
                        }
                )
                .collectDispose(bin)
    }

    private fun getLeague(events: LeagueEventList) {
        val leagueEvent = events.events!![0]
        eventDate.text = leagueEvent.dateEvent.formatDayddMMMyyyy()
        homeName.text = leagueEvent.strHomeTeam
        awayName.text = leagueEvent.strAwayTeam
        homeScore.text = leagueEvent.intHomeScore
        awayScore.text = leagueEvent.intAwayScore
        homeGoals.text = leagueEvent.strHomeGoalDetails.splitEnter()
        homeGoals.text = leagueEvent.strAwayGoalDetails.splitEnter()
        homeShots.text = leagueEvent.intHomeShots.beString()
        awayShots.text = leagueEvent.intAwayShots.beString()
        homeGoalKeeper.text = leagueEvent.strHomeLineupGoalkeeper.splitEnter()
        awayGoalKeeper.text = leagueEvent.strAwayLineupGoalkeeper.splitEnter()
        homeDefense.text = leagueEvent.strHomeLineupDefense.splitEnter()
        awayDefense.text = leagueEvent.strAwayLineupDefense.splitEnter()
        homeMidfield.text = leagueEvent.strHomeLineupMidfield.splitEnter()
        awayMidfield.text = leagueEvent.strAwayLineupMidfield.splitEnter()
        homeForward.text = leagueEvent.strHomeLineupForward.splitEnter()
        awayForward.text = leagueEvent.strAwayLineupForward.splitEnter()
        homeSubs.text = leagueEvent.strHomeLineupSubstitutes.splitEnter()
        awaySubs.text = leagueEvent.strAwayLineupSubstitutes.splitEnter()
    }

    fun callLeagueTeam(id: String, isHome: Boolean) {
        dbg("Call Team")
        apiService.getLookUpTeam(apiKey, id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { team ->
                            getTeam(team, isHome)
                        },
                        {
                            err("Failed Import getLookUpTeam")
                            err(it.message!!)
                            it.printStackTrace()
                        }
                )
                .collectDispose(bin)
    }

    private fun getTeam(teams: LeagueTeam, isHome: Boolean) {
        val teamData = teams.teams.get(0)
        dbg(teamData.strTeamBadge)
        if (isHome) {
            homeBadge.loadGlide(teamData.strTeamBadge)
        } else {
            awayBadge.loadGlide(teamData.strTeamBadge)
        }
    }

    private fun addRemoveToFavorite() {
        var rst: Long = 0
        var rstDel: Int = 0
        if (checkFavorite(idEvent)) {
            database.use {
                rstDel = delete(LeagueEventModal.TableName, "(${LeagueEventModal.idEvent} = {id})", "id" to idEvent)
            }
            if (rstDel > 0) {
                setFave(ic_add_to_favorites)
            }
        } else {
            database.use {
                rst = insert(LeagueEventModal.TableName,
                        LeagueEventModal.idEvent to eventObject?.idEvent,
                        LeagueEventModal.idHomeTeam to eventObject?.idHomeTeam,
                        LeagueEventModal.idAwayTeam to eventObject?.idAwayTeam,
                        LeagueEventModal.strHomeTeam to eventObject?.strHomeTeam,
                        LeagueEventModal.strAwayTeam to eventObject?.strAwayTeam,
                        LeagueEventModal.intHomeScore to eventObject?.intHomeScore,
                        LeagueEventModal.intAwayScore to eventObject?.intAwayScore,
                        LeagueEventModal.dateEvent to eventObject?.dateEvent)
            }
            if (rst > 0) {
                setFave(ic_added_to_favorites)
                if(eventObject?.intHomeScore==null){
//                    confirmation()//TODO
                }
            }
        }

    }

    private fun checkFavorite(id: String): Boolean {
        var isFave: Boolean = false
        database.use {
            val result = select(LeagueEventModal.TableName).whereArgs("${LeagueEventModal.idEvent} = {id}", "id" to id)
            val faveRecord = result.parseList(classParser<LeagueEventModal>())
            if (!faveRecord.isEmpty()) isFave = true
        }
        dbg("IS FAVE " + isFave)
        return isFave
    }

    private fun setFave(icon: Int) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, icon)
    }

    private fun confirmation() {
        alert("Do you want to create schedule in your Calendar?") {
            title = "Confirmation"
            positiveButton("Yes") { setEvent(eventObject!!) }
            negativeButton("No") { }
        }.show()
//                setEvent(eventObject!!)//for FinalsOnly
    }

    private fun setEvent(eventObject: LeagueEvent) {

        val intent = Intent(Intent.ACTION_EDIT)
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, eventObject?.strEvent.toString());
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateStart = sdf.parse(eventObject?.dateEvent + " " + eventObject?.strTime)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateStart.time)
        intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
        startActivity(intent)
    }
}