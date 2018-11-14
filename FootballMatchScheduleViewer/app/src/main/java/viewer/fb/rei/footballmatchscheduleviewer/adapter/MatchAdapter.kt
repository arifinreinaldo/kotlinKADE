package viewer.fb.rei.footballmatchscheduleviewer.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import viewer.fb.rei.footballmatchscheduleviewer.view.MatchSchedule


const val ARG_OBJECT = "object"

class MatchAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = 2
    val menuName = arrayOf("Prev Match","Next Match")
    override fun getItem(i: Int): Fragment {
        val fragment = MatchSchedule()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, i)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return menuName[position]
    }
}
