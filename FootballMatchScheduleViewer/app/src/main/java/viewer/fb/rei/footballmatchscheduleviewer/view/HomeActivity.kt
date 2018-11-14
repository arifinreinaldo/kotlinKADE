package viewer.fb.rei.footballmatchscheduleviewer.view

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.R.id.*
import viewer.fb.rei.footballmatchscheduleviewer.adapter.ARG_OBJECT
import viewer.fb.rei.footballmatchscheduleviewer.util.BaseActivity
import viewer.fb.rei.footballmatchscheduleviewer.util.dbg

/**
 * Created by sapuser on 10/30/2018.
 */
class HomeActivity : BaseActivity() {
    private var selectedId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == prev_schedule) {
                dbg("PREV")
                loadTeamsFragment(savedInstanceState,0,prev_schedule)
            } else if (item.itemId == next_schedule) {
                dbg("NEXT")
                loadTeamsFragment(savedInstanceState,1,next_schedule)
            } else if (item.itemId == favorite) {
                dbg("FAVE")
                loadTeamsFragment(savedInstanceState,2, favorite)
            }
            true
        }
        bottom_navigation.selectedItemId = prev_schedule
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?,type:Int,id:Int) {
        if (savedInstanceState == null && selectedId!=id) {
            var args:Bundle = Bundle()
            args.putInt(ARG_OBJECT,type)
            var fragMatch:MatchSchedule = MatchSchedule()
            fragMatch.arguments = args
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, fragMatch, MatchSchedule::class.java.simpleName)
                    .commit()
            selectedId = id
        }
    }
}