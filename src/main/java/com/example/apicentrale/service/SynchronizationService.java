package com.example.apicentrale.service;

import com.example.apicentrale.client.ChampionShipClient;
import com.example.apicentrale.model.*;
import com.example.apicentrale.repository.ClubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class SynchronizationService {

    private final DataSource dataSource;
    private final ChampionShipClient championShipClient;
    private final ClubRepository clubRepository;

    public SynchronizationService(ClubRepository clubRepository, DataSource dataSource, ChampionShipClient championShipClient) {
        this.clubRepository = clubRepository;
        this.dataSource = dataSource;
        this.championShipClient = championShipClient;
    }

    @Transactional
    public void synchronizeData() {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement deletePlayers = connection.prepareStatement("DELETE FROM Player");
                 PreparedStatement deleteClubs = connection.prepareStatement("DELETE FROM Club")) {
                deletePlayers.executeUpdate();
                deleteClubs.executeUpdate();
            }


            List<Player> players = championShipClient.fetchAllPlayers();
            String insertPlayerSQL = "INSERT INTO Player (id, name, position, age, scored_goals) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertPlayer = connection.prepareStatement(insertPlayerSQL)) {
                for (Player player : players) {
                    insertPlayer.setString(1, player.getId());
                    insertPlayer.setString(2, player.getName());
                    insertPlayer.setString(3, player.getPosition().toString());
                    insertPlayer.setInt(4, player.getAge());
                    insertPlayer.setInt(5, player.getScoredGoals());
                    insertPlayer.addBatch();
                }
                insertPlayer.executeBatch();
            }


            List<Club> clubs = championShipClient.fetchAllClubs();
            String insertClubSQL = "INSERT INTO Club (id, name, ranking_points, scored_goals, conceded_goals) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertClub = connection.prepareStatement(insertClubSQL)) {
                for (Club club : clubs) {
                    insertClub.setString(1, club.getId());
                    insertClub.setString(2, club.getName());
                    insertClub.setInt(3, club.getRankingPoints());
                    insertClub.setInt(4, club.getScoredGoals());
                    insertClub.setInt(5, club.getConcededGoals());
                    insertClub.addBatch();
                }
                insertClub.executeBatch();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la synchronisation des données", e);
        }
    }
}