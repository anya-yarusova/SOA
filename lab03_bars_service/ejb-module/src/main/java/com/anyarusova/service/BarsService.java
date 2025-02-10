package com.anyarusova.service;

import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;

import javax.ejb.Remote;

@Remote
public interface BarsService {
    LabWorkData increaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
    LabWorkData decreaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
}
