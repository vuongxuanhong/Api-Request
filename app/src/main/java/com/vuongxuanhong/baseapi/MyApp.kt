package com.vuongxuanhong.baseapi

import android.app.Application
import com.vuongxuanhong.restlibrary.BaseService
import com.vuongxuanhong.restlibrary.config.DefaultConfiguration

/**
 * Created by vuongxuanhong on 9/6/18. HappyCoding!
 */
class MyApp: Application() {

    lateinit var baseService: BaseService

    override fun onCreate() {
        super.onCreate()

        baseService = BaseService(DefaultConfiguration(), this)
    }

}