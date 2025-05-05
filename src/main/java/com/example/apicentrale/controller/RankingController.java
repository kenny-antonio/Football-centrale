package com.example.apicentrale.controller;

import com.example.apicentrale.model.ChampionShipRanking;
import com.example.apicentrale.service.ChampionShipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/championshipRankings")
public class RankingController {

    private final ChampionShipService championshipService;

    public RankingController(ChampionShipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public List<ChampionShipRanking> getChampionshipRankings() {
        return championshipService.getChampionshipRankings();
    }
}