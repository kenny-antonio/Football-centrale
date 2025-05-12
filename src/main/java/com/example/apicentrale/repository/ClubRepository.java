package com.example.apicentrale.repository;

import com.example.apicentrale.model.Club;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClubRepository {
    private final DataSource dataSource;

    public ClubRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Club> findTopClubs(int limit) {
        String sql = "SELECT * FROM Club ORDER BY ranking_points DESC LIMIT ?";
        List<Club> clubs = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clubs.add(mapToClub(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des meilleurs clubs", e);
        }

        return clubs;
    }

    public Optional<Club> findClubById(String id) {
        String sql = "SELECT * FROM Club WHERE id = ?";
        Club club = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                club = mapToClub(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du club", e);
        }

        return Optional.ofNullable(club);
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
}