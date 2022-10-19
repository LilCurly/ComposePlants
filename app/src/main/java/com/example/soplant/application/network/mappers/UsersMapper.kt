package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.UsersDto
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class UsersMapper @Inject constructor(private val userMapper: UserMapper) :
    Mapper<UsersDto, List<User>> {
    override fun mapToEntity(model: UsersDto): List<User> {
        return model.users.map {
            userMapper.mapToEntity(it)
        }
    }

    override fun mapFromEntity(entity: List<User>): UsersDto {
        return UsersDto(
            users = entity.map {
                userMapper.mapFromEntity(it)
            }
        )
    }
}