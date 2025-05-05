package com.example.apicentrale.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Coach {
    private String name;
    private String nationality;
}