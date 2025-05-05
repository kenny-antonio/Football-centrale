package com.example.apicentrale.controller;

import com.example.apicentrale.service.SynchronizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synchronization")
public class SynchronizationController {

    private final SynchronizationService synchronizationService;

    public SynchronizationController(SynchronizationService synchronizationService) {
        this.synchronizationService = synchronizationService;
    }

    @PostMapping
    public ResponseEntity<Void> synchronize() {
        synchronizationService.synchronizeData();
        return ResponseEntity.ok().build();
    }
}