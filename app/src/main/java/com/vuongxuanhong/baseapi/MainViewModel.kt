package com.vuongxuanhong.baseapi

import android.app.Application
import android.util.Log
import com.vuongxuanhong.restlibrary.service.ApiService
import com.vuongxuanhong.restlibrary.BaseViewModel
import com.vuongxuanhong.restlibrary.Injectable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by vuongxuanhong on 9/6/18. HappyCoding!
 */
class MainViewModel : BaseViewModel(), Injectable {

    companion object {
        private val TAG = "MainViewModel"
    }

    private lateinit var service: ApiService

    override fun inject(application: Application) {
        if (application is MyApp) {
            service = application.baseService.apiService
        }
    }

    fun loadData() {
        addDisposable(service.get("https://api.thecatapi.com/v1/images/search?")
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<Any>() {
                    override fun onSuccess(value: Any?) {
                        Log.d(TAG, "response: $value")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "response error: ${e?.message}")
                    }

                })
        )

    }


}