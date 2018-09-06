package com.vuongxuanhong.restlibrary

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by vuongxuanhong on 9/6/18. HappyCoding!
 */
open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}