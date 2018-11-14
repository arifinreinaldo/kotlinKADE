package viewer.fb.rei.footballmatchscheduleviewer

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import viewer.fb.rei.footballmatchscheduleviewer.R.id.add_to_favorite
import viewer.fb.rei.footballmatchscheduleviewer.view.HomeActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAddtoFavorite() {
        //add sleep supaya tidak langsung klik
        Thread.sleep(8000);

        Espresso.onView(ViewMatchers.withText("Chelsea"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Chelsea")).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
    }
}
