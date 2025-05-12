package com.example.apicentrale.service;

import com.example.apicentrale.model.Club;
import com.example.apicentrale.model.ClubRanking;
import com.example.apicentrale.repository.ClubRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClubService {
    private final DataSource dataSource;
    private final ClubRepository clubRepository;

    public ClubService(DataSource dataSource, ClubRepository clubRepository) {
        this.dataSource = dataSource;
        this.clubRepository = clubRepository;
    }

    public List<ClubRanking> getBestClubs(int top) {
        String sql = "SELECT * FROM Club ORDER BY ranking_points DESC LIMIT ?";
        List<Club> clubs = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clubs.add(mapToClub(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des meilleurs clubs", e);
        }

        return clubs.stream()
                .map(this::convertToClubRanking)
                .collect(Collectors.toList());
    }

    public List<Club> getTopClubs(int limit) {
        return clubRepository.findTopClubs(limit);
    }

    public Optional<Club> getClub(String id) {
        return clubRepository.findClubById(id);
    }

    private Club mapToClub(ResultSet resultSet) throws SQLException {
        Club club = new Club();
        club.setId(resultSet.getString("id"));
        club.setName(resultSet.getString("name"));
        club.setRankingPoints(resultSet.getInt("ranking_points"));
        club.setScoredGoals(resultSet.getInt("scored_goals"));
        club.setConcededGoals(resultSet.getInt("conceded_goals"));
        club.setCleanSheetNumber(resultSet.getInt("clean_sheet_number"));
        return club;
    }

    private ClubRanking convertToClubRanking(Club club) {
        ClubRanking clubRanking = new ClubRanking();
        clubRanking.setId(club.getId());
        clubRanking.setName(club.getName());
        clubRanking.setRankingPoints(club.getRankingPoints());
        return clubRanking;
    }
}