package com.example.server_354.Services;

import org.springframework.stereotype.Service;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository){
        this.partRepository = partRepository;
    }

}
