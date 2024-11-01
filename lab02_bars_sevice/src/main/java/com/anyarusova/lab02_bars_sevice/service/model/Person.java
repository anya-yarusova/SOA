package com.anyarusova.lab02_bars_sevice.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location; //Поле не может быть null
}
