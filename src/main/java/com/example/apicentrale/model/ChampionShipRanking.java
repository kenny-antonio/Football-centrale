package com.example.apicentrale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChampionShipRanking {
    private Integer rank;
    private ChampionShip championship;
    private Double differenceGoalsMedian;
    private List<Club> clubs;
}