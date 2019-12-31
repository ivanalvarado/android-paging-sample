package com.ivanalvarado.template.network.services

import com.ivanalvarado.template.network.models.UsersResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface StackOverflowService {

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchUsers(): Call<UsersResponse>

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchUsersRx(): Observable<Response<UsersResponse>>
}