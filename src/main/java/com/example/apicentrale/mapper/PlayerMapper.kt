package com.example.apicentrale.mapper

import com.example.apicentrale.client.dto.ExternalPlayerDto
import com.example.apicentrale.model.Player
import org.springframework.stereotype.Component

@Component
class PlayerMapper {
    fun toEntity(dto: ExternalPlayerDto): Player {
        val player = Player()
        player.setId(dto.id)
        player.setName(dto.name)
        player.setNumber(dto.number)
        player.setPosition(dto.position)
        player.setNationality(dto.nationality)
        player.setAge(dto.age)
        player.setChampionship(dto.championship)
        player.setScoredGoals(dto.scoredGoals)
        player.setPlayingTimeValue(dto.playingTime.value.toInt())
        player.setPlayingTimeUnit(dto.playingTime.durationUnit)
        return player
    }
}