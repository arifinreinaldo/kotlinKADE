package viewer.fb.rei.footballmatchscheduleviewer.view

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import viewer.fb.rei.footballmatchscheduleviewer.R
import viewer.fb.rei.footballmatchscheduleviewer.adapter.MatchAdapter
import viewer.fb.rei.footballmatchscheduleviewer.util.BaseActivity


class MainActivity : BaseActivity() {
    private lateinit var mMatchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMatchAdapter = MatchAdapter(supportFragmentManager)
        pager.adapter = mMatchAdapter

        sliding_tabs.setupWithViewPager(pager)
    }
}
