package com.example.apicentrale.controller;

import com.example.apicentrale.model.DurationUnit;
import com.example.apicentrale.model.PlayerRanking;
import com.example.apicentrale.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestPlayers")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerRanking> getBestPlayers(
            @RequestParam(defaultValue = "5") int top,
            @RequestParam DurationUnit playingTimeUnit) {
        return playerService.getBestPlayers(top, playingTimeUnit);
    }
}