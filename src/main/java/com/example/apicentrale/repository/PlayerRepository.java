package com.example.apicentrale.repository;
import com.example.apicentrale.model.ChampionShip;
import com.example.apicentrale.model.DurationUnit;
import com.example.apicentrale.model.Player;
import com.example.apicentrale.model.PlayerPosition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Repository
public class PlayerRepository {
    private final DataSource dataSource;

    public PlayerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Player> findTopPlayers(int top) {
        String sql = "SELECT * FROM Player ORDER BY scored_goals DESC LIMIT ?";
        List<Player> players = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, top);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                players.add(mapToPlayer(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des meilleurs joueurs", e);
        }

        return players;
    }

    private Player mapToPlayer(ResultSet resultSet) throws SQLException {
        Player player = new Player();
        player.setId(resultSet.getString("id"));
        player.setName(resultSet.getString("name"));
        player.setNumber(resultSet.getInt("number"));
        player.setPosition(PlayerPosition.valueOf(resultSet.getString("position")));
        player.setNationality(resultSet.getString("nationality"));
        player.setAge(resultSet.getInt("age"));
        player.setChampionship(ChampionShip.valueOf(resultSet.getString("championship_id")));
        player.setScoredGoals(resultSet.getInt("scored_goals"));
        player.setPlayingTimeValue(resultSet.getInt("playing_time_value"));
        player.setPlayingTimeUnit(DurationUnit.valueOf(resultSet.getString("playing_time_unit")));
        return player;
    }
}