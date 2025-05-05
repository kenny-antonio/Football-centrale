package com.example.apicentrale.client.dto;

import com.example.apicentrale.model.*;
import lombok.Data;

@Data
public class ExternalClubDto {
    private String id;
    private String name;
    private String acronym;
    private Integer yearCreation;
    private String stadium;
    private Coach coach;
    private ChampionShip championship;
    private Integer rankingPoints;
    private Integer scoredGoals;
    private Integer concededGoals;
    private Integer cleanSheetNumber;
}
