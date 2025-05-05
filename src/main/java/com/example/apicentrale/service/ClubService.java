package com.example.apicentrale.service;

import com.example.apicentrale.model.*;
import com.example.apicentrale.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ClubRanking> getBestClubs(int top) {
        List<Club> clubs = clubRepository.findTopClubs(top);

        return clubs.stream()
                .map(this::convertToClubRanking)
                .collect(Collectors.toList());
    }

    private ClubRanking convertToClubRanking(Club club) {
        ClubRanking ranking = new ClubRanking();
        ranking.setClub(club);
        ranking.setRankingPoints(club.getRankingPoints());
        ranking.setScoredGoals(club.getScoredGoals());
        ranking.setConcededGoals(club.getConcededGoals());
        ranking.setDifferenceGoals(club.getScoredGoals() - club.getConcededGoals());
        ranking.setCleanSheetNumber(club.getCleanSheetNumber());
        return ranking;
    }
}