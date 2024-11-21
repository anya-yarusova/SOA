package com.anyarusova.lab02_bars_sevice.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabWork {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null

    public void increaseDifficulty(int stepsCount)
    {
        this.difficulty = this.difficulty.increaseDifficulty(stepsCount);
    }

    public void decreaseDifficulty(int stepsCount)
    {
        this.difficulty = this.difficulty.decreaseDifficulty(stepsCount);
    }
}
