package com.example.apicentrale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayingTime {
    private Double value;
    private DurationUnit durationUnit;
}