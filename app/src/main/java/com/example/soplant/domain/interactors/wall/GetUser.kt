package com.example.soplant.domain.interactors.wall

import com.example.soplant.domain.repositories.UserRepository
import javax.inject.Inject

class GetUser @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(userId: String) = repository.getUser(userId)
}