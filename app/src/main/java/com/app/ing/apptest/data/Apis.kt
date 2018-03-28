package com.app.ing.apptest.data

import com.app.ing.apptest.model.User
import com.app.ing.apptest.model.UserResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jose on 27/03/18.
 */

interface UserApi {

    companion object {
        fun createUserService(): UserApi {
            val userService = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://randomuser.me")
                    .build()

            return userService.create(UserApi::class.java)
        }
    }

    @GET("api")
    fun getUsers(@Query("results") result: Int): Observable<UserResponse>

}