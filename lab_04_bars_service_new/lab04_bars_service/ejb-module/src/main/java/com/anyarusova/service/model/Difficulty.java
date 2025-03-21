package com.anyarusova.service.model;

import com.anyarusova.interceptor.ExtendedException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Difficulty {
    VERY_EASY,
    EASY,
    NORMAL,
    IMPOSSIBLE,
    INSANE;

    static final Difficulty[] VALUES = values();

    public Difficulty increaseDifficulty(int stepsCount) throws ExtendedException {
        try {
            return VALUES[ordinal() + stepsCount];
        } catch (Exception e) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Нельзя повысить сложность");
        }
    }

    public Difficulty decreaseDifficulty(int stepsCount) throws ExtendedException {
        try {
            return VALUES[ordinal() - stepsCount];
        } catch (Exception e) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Нельзя понизить сложность");
        }
    }
}
