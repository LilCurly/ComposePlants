package com.example.soplant.domain.interactors.wall

import com.example.soplant.domain.repositories.UserRepository
import javax.inject.Inject

class GetUsers @Inject constructor(private val repository: UserRepository) {
    operator fun invoke() = repository.getUsers()
}