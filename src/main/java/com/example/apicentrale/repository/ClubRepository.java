package com.example.apicentrale.repository;

import com.example.apicentrale.model.Championship;
import com.example.apicentrale.model.Club;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends CrudRepository<Club, String> {

    @Query("""
        SELECT * FROM clubs 
        ORDER BY ranking_points DESC, (scored_goals - conceded_goals) DESC
        LIMIT :limit
        """)
    List<Club> findTopClubs(@Param("limit") int limit);

    @Query("SELECT * FROM clubs WHERE championship = :championship")
    List<Club> findByChampionship(@Param("championship") Championship championship);
}