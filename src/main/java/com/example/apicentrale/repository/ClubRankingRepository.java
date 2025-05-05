package com.example.apicentrale.repository;

import com.example.apicentrale.model.ClubRanking;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRankingRepository extends CrudRepository<ClubRanking, String> {
}