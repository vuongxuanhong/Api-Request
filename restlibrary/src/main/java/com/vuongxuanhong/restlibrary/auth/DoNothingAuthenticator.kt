package com.vuongxuanhong.restlibrary.auth

import android.util.Log
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Created by vuongxuanhong on 9/6/18. HappyCoding!
 */
class DoNothingAuthenticator : AppAuthenticator() {
    companion object {
        private val TAG = "DoNothingAuthenticator"
    }

    override fun authenticate(route: Route?, response: Response?): Request? {
        Log.d(TAG, "face 401 Error, should refresh token here ")
        return null
    }


}