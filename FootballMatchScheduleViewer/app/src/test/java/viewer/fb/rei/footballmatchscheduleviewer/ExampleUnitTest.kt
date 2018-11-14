package viewer.fb.rei.footballmatchscheduleviewer

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import viewer.fb.rei.footballmatchscheduleviewer.structure.League
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEvent
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueEventList
import viewer.fb.rei.footballmatchscheduleviewer.structure.LeagueList
import viewer.fb.rei.footballmatchscheduleviewer.util.formatDayddMMMyyyy
import viewer.fb.rei.footballmatchscheduleviewer.view.MatchSchedule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    lateinit var value : LeagueEventList

    @Mock
    lateinit var match :MatchSchedule

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        match = MatchSchedule()
    }

    @Test
    fun tesGetLeagueList() {
        val league = LeagueEventList()

        val mock = org.mockito.Mockito.mock<MatchSchedule>(MatchSchedule::class.java)
        Mockito.`when`(mock.getLeagueShchedule("eventspastleague","4328")).thenReturn(league)
    }
    @Test
    fun tesGetAllLeagueData() {
        val leagues = arrayListOf<League>()
        var value = LeagueList(leagues)
        val mock = org.mockito.Mockito.mock<MatchSchedule>(MatchSchedule::class.java)
        Mockito.`when`(mock.getAllLeagueData("eventspastleague")).thenReturn(value)
    }
    @Test
    fun testformatDayddMMMyyyy(){
        assertEquals("Mon, 01 Jan 2018","2018-01-01".formatDayddMMMyyyy())
    }
}
