package com.example.apicentrale.mapper;

import com.example.apicentrale.client.dto.ExternalClubDto;
import com.example.apicentrale.model.Club;
import org.springframework.stereotype.Component;

@Component
public class ClubMapper {

    public Club toEntity(ExternalClubDto dto) {
        Club club = new Club();
        club.setId(dto.getId());
        club.setName(dto.getName());
        club.setAcronym(dto.getAcronym());
        club.setYearCreation(dto.getYearCreation());
        club.setStadium(dto.getStadium());
        club.setCoachName(dto.getCoach().getName());
        club.setCoachNationality(dto.getCoach().getNationality());
        club.setChampionship(dto.getChampionship());
        club.setRankingPoints(dto.getRankingPoints());
        club.setScoredGoals(dto.getScoredGoals());
        club.setConcededGoals(dto.getConcededGoals());
        club.setCleanSheetNumber(dto.getCleanSheetNumber());
        return club;
    }
}