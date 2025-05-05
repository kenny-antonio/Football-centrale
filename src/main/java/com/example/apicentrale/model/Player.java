package com.example.apicentrale.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String id;
    private String name;
    private Integer number;
    private PlayerPosition position;
    private String nationality;
    private Integer age;
    private ChampionShip championship;
    private Integer scoredGoals;
    private Integer playingTimeValue;
    private DurationUnit playingTimeUnit;
}
