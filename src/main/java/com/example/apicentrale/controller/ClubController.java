package com.example.apicentrale.controller;

import com.example.apicentrale.model.ClubRanking;
import com.example.apicentrale.service.ClubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bestClubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<ClubRanking> getBestClubs(
            @RequestParam(defaultValue = "5") int top) {
        return clubService.getBestClubs(top);
    }
}