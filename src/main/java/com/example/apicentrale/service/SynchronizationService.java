package com.example.apicentrale.service;

import com.example.apicentrale.client.ChampionShipClient;
import com.example.apicentrale.model.*;
import com.example.apicentrale.repository.ClubRepository;
import com.example.apicentrale.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SynchronizationService {

    private final ChampionShipClient championshipClient;
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    public SynchronizationService(ChampionShipClient championshipClient,
                                  PlayerRepository playerRepository,
                                  ClubRepository clubRepository) {
        this.championshipClient = championshipClient;
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    @Transactional
    public void synchronizeData() {
        // Clear existing data
        playerRepository.deleteAll();
        clubRepository.deleteAll();

        // Fetch and save new data
        List<Player> players = championshipClient.fetchAllPlayers();
        playerRepository.saveAll(players);

        List<Club> clubs = championshipClient.fetchAllClubs();
        clubRepository.saveAll(clubs);
    }
}