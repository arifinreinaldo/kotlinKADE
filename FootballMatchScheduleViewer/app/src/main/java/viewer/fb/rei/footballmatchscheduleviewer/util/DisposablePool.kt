package viewer.fb.rei.footballmatchscheduleviewer.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by sapuser on 10/30/2018.
 */
class DisposablePool{
    private var compositeDisposable:CompositeDisposable ? = null
    fun add(disposable:Disposable){
        if(compositeDisposable==null){
            compositeDisposable = CompositeDisposable()
        }
        dbg("Adding dispose")
        compositeDisposable?.add(disposable)
    }
    fun dispose(){
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}

fun Disposable.collectDispose(bin:DisposablePool){
    bin.add(this)
}