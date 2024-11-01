package com.anyarusova.lab02_bars_sevice.service.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Difficulty {
    VERY_EASY,
    EASY,
    NORMAL,
    IMPOSSIBLE,
    INSANE;

    static final Difficulty[] VALUES = values();

    public Difficulty increaseDifficulty(int stepsCount) {
        try {
            return VALUES[ordinal() + stepsCount];
        } catch (Exception e) {
            throw new UnsupportedOperationException("Нельзя повысить сложность");
        }
    }

    public Difficulty decreaseDifficulty(int stepsCount) {
        try {
            return VALUES[ordinal() - stepsCount];
        } catch (Exception e) {
            throw new UnsupportedOperationException("Нельзя понизить сложность");
        }
    }
}
