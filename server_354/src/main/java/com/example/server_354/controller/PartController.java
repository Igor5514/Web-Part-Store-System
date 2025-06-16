package com.example.server_354.controller;

import com.example.server_354.Services.PartService;
import com.example.server_354.object.Parts;
import com.example.server_354.object.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(parts);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error while retrieving parts");
        }
    }

    @PostMapping("/postCartItem")
    public ResponseEntity<?> postCartItem(@RequestParam("email") String email,
                                          @RequestParam("partId") Integer partId){
        try{
            System.out.println(email +" "+ partId);
            partService.postCartItem(partId, email);
            return ResponseEntity.ok("part successfully added");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error trying to post cart item: "+e.getMessage());
        }
    }

    @GetMapping("/getCartItem")
    public ResponseEntity<?> getCartItems(@RequestParam("email") String email){
        try{
            List<Parts> cartItems = partService.getCartItems(email);
            return ResponseEntity.ok(cartItems);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error trying to post cart item: "+e.getMessage());
        }
    }

}
