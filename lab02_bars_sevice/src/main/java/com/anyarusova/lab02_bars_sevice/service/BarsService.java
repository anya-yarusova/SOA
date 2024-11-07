package com.anyarusova.lab02_bars_sevice.service;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedException;

public interface BarsService {
    LabWorkData increaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
    LabWorkData decreaseDifficulty(long labWorkId, int stepsCount) throws ExtendedException;
}
