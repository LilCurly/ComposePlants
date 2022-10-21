package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.ErrorMapper
import com.example.soplant.application.network.mappers.UserMapper
import com.example.soplant.application.network.mappers.UsersMapper
import com.example.soplant.application.network.services.UserService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.domain.entities.User
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: UserService,
    private val userMapper: UserMapper,
    private val usersMapper: UsersMapper,
    private val errorMapper: ErrorMapper
) {
    suspend fun getUsers(): CustomResponse<List<User>> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.getUsers(headers)
        var users = listOf<User>()
        if (response.isSuccessful && response.body() != null) {
            users = usersMapper.mapToEntity(response.body()!!)
        }
        return CustomResponse(
            users,
            response.isSuccessful,
            response.code(),
            com.example.soplant.application.network.utils.Error.getErrorModel(response.errorBody())
                ?.let { errorMapper.mapToEntity(it) })
    }

    suspend fun getUser(userId: String): CustomResponse<User> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.getUser(headers, userId)
        var user: User? = null
        if (response.isSuccessful && response.body() != null) {
            user = userMapper.mapToEntity(response.body()!!)
            SharedPreferencesManager.shared().storeLastUserId(user.id)
        }
        return CustomResponse(
            user,
            response.isSuccessful,
            response.code(),
            com.example.soplant.application.network.utils.Error.getErrorModel(response.errorBody())
                ?.let { errorMapper.mapToEntity(it) })
    }
}