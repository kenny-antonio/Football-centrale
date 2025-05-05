package com.example.apicentrale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PlayerRanking {
    private Integer rank;
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
