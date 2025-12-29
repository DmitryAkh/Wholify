package com.dakh.wholify.domain.useCase

import com.dakh.wholify.domain.entity.GameSettings
import com.dakh.wholify.domain.entity.Level
import com.dakh.wholify.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
       return repository.getGameSettings(level)
    }
}