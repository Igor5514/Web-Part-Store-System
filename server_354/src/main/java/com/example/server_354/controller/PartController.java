package com.example.server_354.controller;

import com.example.server_354.Services.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService){
        this.partService = partService;
    }



}
