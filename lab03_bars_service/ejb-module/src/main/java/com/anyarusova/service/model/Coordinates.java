package com.anyarusova.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private float x; //Максимальное значение поля: 592
    private double y; //Максимальное значение поля: 892
}
