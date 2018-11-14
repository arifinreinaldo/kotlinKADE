package viewer.fb.rei.footballmatchscheduleviewer.util

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.SimpleDateFormat
import java.util.*


//ImageVIew Function
fun ImageView.loadGlide(aset: Any) {
    if(aset!=null){
        Glide.with(context).load(aset)
                .into(this)
    }
}

fun String.formatDayddMMMyyyy(): String {
    return SimpleDateFormat("yyyy-MM-dd").parse(this).formatDayddMMMyyyy()
}

fun Date.formatDayddMMMyyyy(): String {
    return SimpleDateFormat("EEE, dd MMM yyyy").format(this)
}

fun dbg(string: String) {
    Log.d("debug", string)
}

fun err(string: String) {
    Log.e("error", string)
}

fun Any?.beString(): String {
    var ret: String = ""
    this?.let { ret = this.toString() }
    return ret
}

fun Any?.splitEnter(): String {
    var ret: String = ""
    this?.let { ret = this.toString().replace(";", "\n") }
    return ret
}

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ShimmerFrameLayout.stopShimmer() {
    this.stopShimmerAnimation()
    visibility = View.INVISIBLE
}

fun SwipeRefreshLayout.stopRefresh(){
    this.isRefreshing = false;
}