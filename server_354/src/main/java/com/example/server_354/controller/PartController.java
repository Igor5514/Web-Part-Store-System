package com.example.server_354.controller;

import com.example.server_354.Services.PartService;
import com.example.server_354.object.Parts;
import com.example.server_354.object.Parts;
import com.example.server_354.object.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService){
        this.partService = partService;
    }

    @PostMapping("/getPartsForVehicle")
    public ResponseEntity<?> getPartsForSpecificVehicle(@RequestBody Vehicle vehicle){
        try {
            List<Parts> parts = partService.getPartsForSpecificVehicle(vehicle);
            for(Parts part : parts){
                System.out.println(part.getName());
            }
            return ResponseEntity.ok(parts);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error while retrieving parts");
        }

    }

}
