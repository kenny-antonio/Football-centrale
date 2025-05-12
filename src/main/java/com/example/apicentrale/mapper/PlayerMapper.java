package com.example.apicentrale.mapper;

import com.example.apicentrale.client.dto.ExternalPlayerDto;
import com.example.apicentrale.model.Player;
import org.springframework.stereotype.Component;


@Component
public class PlayerMapper {
    public Player toEntity(ExternalPlayerDto dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setNumber(dto.getNumber());
        player.setPosition(dto.getPosition());
        player.setNationality(dto.getNationality());
        player.setAge(dto.getAge());
        player.setChampionship(dto.getChampionship());
        player.setScoredGoals(dto.getScoredGoals());
        player.setPlayingTimeValue(dto.getPlayingTime().getValue().intValue());
        player.setPlayingTimeUnit(dto.getPlayingTime().getDurationUnit());
        return player;
    }
}