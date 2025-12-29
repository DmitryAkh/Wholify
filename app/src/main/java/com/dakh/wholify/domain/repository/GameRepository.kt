package com.dakh.wholify.domain.repository

import com.dakh.wholify.domain.entity.GameSettings
import com.dakh.wholify.domain.entity.Level
import com.dakh.wholify.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question

    fun getGameSettings(level: Level): GameSettings
}