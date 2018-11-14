package viewer.fb.rei.footballmatchscheduleviewer.util

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by sapuser on 11/6/2018.
 */
class ExtKtTest {
    @Test
    fun testFormatDayddMMMyyyy() {
        //Scenario 1
        assertEquals("Sat, 10 Nov 2018","2018-11-10".formatDayddMMMyyyy())
    }

}