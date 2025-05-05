package com.example.apicentrale.client;

import com.example.apicentrale.client.dto.*;
import com.example.apicentrale.mapper.ClubMapper;
import com.example.apicentrale.mapper.PlayerMapper;
import com.example.apicentrale.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ChampionShipClient {

    @Value("${championship.api.base-urls}")
    private String[] baseUrls;

    private final RestTemplate restTemplate;
    private final PlayerMapper playerMapper;
    private final ClubMapper clubMapper;

    public ChampionShipClient(RestTemplate restTemplate,
                              PlayerMapper playerMapper,
                              ClubMapper clubMapper) {
        this.restTemplate = restTemplate;
        this.playerMapper = playerMapper;
        this.clubMapper = clubMapper;
    }

    public List<Player> fetchAllPlayers() {
        List<Player> allPlayers = new ArrayList<>();

        for (String baseUrl : baseUrls) {
            try {
                String url = baseUrl + "/players";
                ResponseEntity<ExternalPlayerDto[]> response = restTemplate.getForEntity(
                        url,
                        ExternalPlayerDto[].class
                );

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    Arrays.stream(response.getBody())
                            .map(playerMapper::toEntity)
                            .forEach(allPlayers::add);
                }
            } catch (Exception e) {
                // Log the error and continue with next API
                System.err.println("Error fetching players from " + baseUrl + ": " + e.getMessage());
            }
        }

        return allPlayers;
    }

    public List<Club> fetchAllClubs() {
        List<Club> allClubs = new ArrayList<>();

        for (String baseUrl : baseUrls) {
            try {
                String url = baseUrl + "/clubs";
                ResponseEntity<ExternalClubDto[]> response = restTemplate.getForEntity(
                        url,
                        ExternalClubDto[].class
                );

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    Arrays.stream(response.getBody())
                            .map(clubMapper::toEntity)
                            .forEach(allClubs::add);
                }
            } catch (Exception e) {
                // Log the error and continue with next API
                System.err.println("Error fetching clubs from " + baseUrl + ": " + e.getMessage());
            }
        }

        return allClubs;
    }
}