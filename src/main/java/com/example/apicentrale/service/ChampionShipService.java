package com.example.apicentrale.service;

import com.example.apicentrale.model.*;
import com.example.apicentrale.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChampionShipService {

    private final ClubRepository clubRepository;

    public ChampionShipService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<ChampionShipRanking> getChampionshipRankings() {
        Map<ChampionShip, List<Club>> clubsByChampionship = clubRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Club::getChampionship));

        List<ChampionShipRanking> rankings = new ArrayList<>();

        clubsByChampionship.forEach((championship, clubs) -> {
            ChampionShipRanking ranking = new ChampionShipRanking();
            ranking.setChampionship(championship);

            List<Integer> differences = clubs.stream()
                    .map(c -> c.getScoredGoals() - c.getConcededGoals())
                    .sorted()
                    .collect(Collectors.toList());

            double median = calculateMedian(differences);
            ranking.setDifferenceGoalsMedian(median);

            rankings.add(ranking);
        });

        // Sort by median (ascending - lower is better)
        rankings.sort(Comparator.comparingDouble(ChampionShipRanking::getDifferenceGoalsMedian));

        // Assign ranks
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        return rankings;
    }

    private double calculateMedian(List<Integer> values) {
        if (values.isEmpty()) return 0.0;

        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size/2 - 1) + values.get(size/2)) / 2.0;
        } else {
            return values.get(size/2);
        }
    }
}