package com.example.apicentrale.client.dto;

import com.example.apicentrale.model.ChampionShip;
import com.example.apicentrale.model.PlayerPosition;
import com.example.apicentrale.model.PlayingTime;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ExternalPlayerDto {
    private String id;
    private String name;
    private Integer number;
    private PlayerPosition position;
    private String nationality;
    private Integer age;
    private ChampionShip championship;
    private Integer scoredGoals;
    private PlayingTime playingTime;
}