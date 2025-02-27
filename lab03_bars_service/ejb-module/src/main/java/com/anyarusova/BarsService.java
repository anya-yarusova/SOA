package com.anyarusova;

import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;

import jakarta.ejb.Remote;

@Remote
public interface BarsService {
    LabWorkData increaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
    LabWorkData decreaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
}
