package com.example.apicentrale.repository;

import com.example.apicentrale.model.PlayerRanking;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRankingRepository extends CrudRepository<PlayerRanking, String> {

}