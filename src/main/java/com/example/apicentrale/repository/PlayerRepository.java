package com.example.apicentrale.repository;

import com.example.apicentrale.model.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {

    @Query("""
        SELECT * FROM players 
        ORDER BY scored_goals DESC, playing_time_value DESC
        LIMIT :limit
        """)
    List<Player> findTopPlayers(@Param("limit") int limit);
}