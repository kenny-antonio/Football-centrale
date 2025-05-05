package com.example.apicentrale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ClubRanking {
    private Integer rank;
    private Club club;
    private Integer rankingPoints;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer differenceGoals;
    private Integer cleanSheetNumber;
}