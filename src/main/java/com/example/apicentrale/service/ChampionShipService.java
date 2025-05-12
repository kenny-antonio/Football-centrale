package com.example.apicentrale.service;

import com.example.apicentrale.model.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class ChampionShipService {
    private final DataSource dataSource;

    public ChampionShipService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ChampionShipRanking> getChampionshipRankings() {
        String sql = "SELECT * FROM Club";
        Map<ChampionShip, List<Club>> clubsByChampionship = new HashMap<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Club club = mapToClub(resultSet);
                clubsByChampionship
                        .computeIfAbsent(club.getChampionship(), k -> new ArrayList<>())
                        .add(club);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des clubs", e);
        }

        List<ChampionShipRanking> rankings = new ArrayList<>();
        for (Map.Entry<ChampionShip, List<Club>> entry : clubsByChampionship.entrySet()) {
            ChampionShipRanking ranking = new ChampionShipRanking();
            ranking.setChampionship(entry.getKey());
            ranking.setClubs(entry.getValue());
            rankings.add(ranking);
        }

        return rankings;
    }

    private Club mapToClub(ResultSet resultSet) throws SQLException {
        Club club = new Club();
        club.setId(resultSet.getString("id"));
        club.setName(resultSet.getString("name"));
        club.setRankingPoints(resultSet.getInt("ranking_points"));
        club.setScoredGoals(resultSet.getInt("scored_goals"));
        club.setConcededGoals(resultSet.getInt("conceded_goals"));
        club.setCleanSheetNumber(resultSet.getInt("clean_sheet_number"));
        club.setChampionship(ChampionShip.valueOf(resultSet.getString("championship_id")));
        return club;
    }
}