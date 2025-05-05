package com.example.apicentrale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private String id;
    private String name;
    private String acronym;
    private Integer yearCreation;
    private String stadium;
    private String coachName;
    private String coachNationality;
    private ChampionShip championship;
    private Integer rankingPoints;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer cleanSheetNumber;
}