package com.example.apicentrale.repository;

import com.example.apicentrale.model.ClubRanking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRankingRepository extends CrudRepository<ClubRanking, String> {
}