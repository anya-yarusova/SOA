package com.anyarusova.lab02_bars_sevice.service;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;

public interface BarsService {
    LabWorkData increaseDifficulty(long labWorkId, int stepsCount);
    LabWorkData decreaseDifficulty(long labWorkId, int stepsCount);
}
