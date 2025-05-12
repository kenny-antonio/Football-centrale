package com.example.apicentrale.service;

import com.example.apicentrale.model.*;
import com.example.apicentrale.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerRanking> getBestPlayers(int top, DurationUnit unit) {
        List<Player> players = playerRepository.findTopPlayers(top);

        return players.stream()
                .map(player -> convertToPlayerRanking(player, unit))
                .collect(Collectors.toList());
    }

    private PlayerRanking convertToPlayerRanking(Player player, DurationUnit unit) {
        PlayerRanking ranking = new PlayerRanking();
        ranking.setId(player.getId());
        ranking.setName(player.getName());
        ranking.setNumber(player.getNumber());
        ranking.setPosition(player.getPosition());
        ranking.setNationality(player.getNationality());
        ranking.setAge(player.getAge());
        ranking.setChampionship(player.getChampionship());
        ranking.setScoredGoals(player.getScoredGoals());

        PlayingTime playingTime = new PlayingTime();
        playingTime.setValue(convertPlayingTime(player.getPlayingTimeValue(), player.getPlayingTimeUnit(), unit));
        playingTime.setDurationUnit(unit);
        ranking.setPlayingTime(playingTime);

        return ranking;
    }

    private double convertPlayingTime(int value, DurationUnit fromUnit, DurationUnit toUnit) {
        double valueInSeconds = convertToSeconds(value, fromUnit);
        return convertFromSeconds(valueInSeconds, toUnit);
    }

    private double convertToSeconds(int value, DurationUnit unit) {
        switch (unit) {
            case HOUR: return value * 3600;
            case MINUTE: return value * 60;
            case SECOND: return value;
            default: return value;
        }
    }

    private double convertFromSeconds(double seconds, DurationUnit unit) {
        switch (unit) {
            case HOUR: return seconds / 3600;
            case MINUTE: return seconds / 60;
            case SECOND: return seconds;
            default: return seconds;
        }
    }
}