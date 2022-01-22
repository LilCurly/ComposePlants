package com.example.soplant.domain.interactors.confirmation

import com.example.soplant.domain.repositories.ExplorationRepository
import javax.inject.Inject

class CreateExploration @Inject constructor(private val repository: ExplorationRepository) {
    operator fun invoke() = repository.createExploration()
}