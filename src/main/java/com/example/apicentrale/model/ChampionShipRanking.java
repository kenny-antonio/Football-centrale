package com.example.apicentrale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChampionShipRanking {
    private Integer rank;
    private ChampionShip championship;
    private Double differenceGoalsMedian;
}