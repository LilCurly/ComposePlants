package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.UserDto
import com.example.soplant.application.network.models.UsersDto
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface UserService {
    @GET(Constants.Endpoints.UserApi.GET_USERS)
    suspend fun getUsers(@HeaderMap headers: Map<String, String>): Response<UsersDto>

    @GET(Constants.Endpoints.UserApi.GET_USER)
    suspend fun getUser(@HeaderMap headers: Map<String, String>, @Path("userId") userId: String): Response<UserDto>
}