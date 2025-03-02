package com.anyarusova.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private float x;
    private Integer y; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 783, Поле может быть null
}
