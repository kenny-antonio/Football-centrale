package com.example.apicentrale.repository;

import com.example.apicentrale.model.PlayerRanking;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRankingRepository extends CrudRepository<PlayerRanking, String> {
}