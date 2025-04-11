package com.example.sem3b_lab_1.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArtistaResponseDto {

    private String username;
    private List<Long> cancionIdList = new ArrayList<>();
}
