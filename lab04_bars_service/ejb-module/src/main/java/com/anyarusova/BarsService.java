package com.anyarusova;

import com.anyarusova.dto.Response;
import com.anyarusova.interceptor.ExtendedException;

import jakarta.ejb.Remote;

@Remote
public interface BarsService {
    Response increaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
    Response decreaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
}
