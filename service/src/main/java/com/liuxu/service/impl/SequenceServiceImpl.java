package com.liuxu.service.impl;

import com.liuxu.service.intf.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;

public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceService sequenceService;

    @Override
    public Long getSequenceId(String sequence) {
        return sequenceService.getSequenceId(sequence);
    }
}
