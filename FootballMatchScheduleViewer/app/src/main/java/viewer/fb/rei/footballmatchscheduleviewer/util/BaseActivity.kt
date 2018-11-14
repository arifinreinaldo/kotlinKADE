package viewer.fb.rei.footballmatchscheduleviewer.util

import android.support.v7.app.AppCompatActivity
import viewer.fb.rei.footballmatchscheduleviewer.api.Service

/**
 * Created by sapuser on 10/30/2018.
 */
val apiService by lazy {
    Service.create()
}
var bin: DisposablePool = DisposablePool()

open class BaseActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        bin?.dispose()
        dbg("Destroy")
    }

//    override fun onStop() {
//        super.onStop()
//        bin?.dispose()
//    }
}
