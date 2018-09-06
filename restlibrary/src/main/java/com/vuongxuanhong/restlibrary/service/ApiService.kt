package com.vuongxuanhong.restlibrary.service

import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by xuanhong on 8/29/18. HappyCoding!
 */
interface ApiService {

    @GET
    fun get(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf(), @QueryMap params: MutableMap<String, String> = mutableMapOf()): Single<Any>

    @POST
    @FormUrlEncoded
    fun postForm(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf(), @FieldMap params: MutableMap<String, String> = mutableMapOf()): Single<String>

    @POST
    @Multipart
    fun postFile(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf(), @PartMap params: MutableMap<String, RequestBody> = mutableMapOf()): Single<String>

    @PUT
    @FormUrlEncoded
    fun putForm(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf(), @FieldMap params: MutableMap<String, String> = mutableMapOf()): Single<String>

    @PUT
    @Multipart
    fun putFile(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf(), @PartMap params: MutableMap<String, RequestBody> = mutableMapOf()): Single<String>

    @DELETE
    fun delete(@Url url: String, @HeaderMap headers: MutableMap<String, String> = mutableMapOf()): Single<String>

}