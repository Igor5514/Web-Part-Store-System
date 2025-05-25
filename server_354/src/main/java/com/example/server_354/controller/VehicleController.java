package com.example.server_354.controller;

import com.example.server_354.Services.VehicleService;
import com.example.server_354.object.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    public String substringIncomingString(String text){
        return text.substring(1, text.length() - 1);
    }

    @GetMapping("/getMake")
    public ResponseEntity<?> getVehicleMake() {
        Map<String, String> errorResponse = new HashMap<>();
        try {
            return ResponseEntity.ok(vehicleService.getEveryMake());
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            errorResponse.put("error", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/getModel")
    public ResponseEntity<?> getVehicleModel(@RequestBody String make){
        String substrigedMake = substringIncomingString(make);
        Integer makeId = vehicleService.getMakeIdByMake(substrigedMake);
        Map<String, String> errorResponse = new HashMap<>();
        try{
            List<String> modelList = vehicleService.getModelByMakeId(makeId);
            return ResponseEntity.ok(modelList);
        }catch (DataAccessException e){
            System.out.println(e.getMessage());
            errorResponse.put("message", "couldn't retrieve model"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/getGeneration")
    public ResponseEntity<?> getVehicleGeneration(@RequestBody String model){
        String substrigedModel = substringIncomingString(model);
        Map<String, String> errorResponse = new HashMap<>();
        try{
            List<String> generationList = vehicleService.getGenerationByModelId(substrigedModel);
            return ResponseEntity.ok(generationList);
        }catch (DataAccessException e){
            System.out.println(e.getMessage());
            errorResponse.put("error", "couldn't retrieve generation"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/getEngine")
    public ResponseEntity<?> getVehicleEngine(@RequestBody EngineModel engineModel){
        String substringModel = engineModel.getModel();
        String substringGeneration = engineModel.getGeneration();
        Map<String, String> errorResponse = new HashMap<>();
        try{
            List<String> engineList = vehicleService.getEngineByModelAndGeneration(substringModel, substringGeneration);
            return ResponseEntity.ok(engineList);
        }catch (DataAccessException e){
            System.out.println(e.getMessage());
            errorResponse.put("message", "couldn't retrieve engine"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getPartGroup")
    private ResponseEntity<?> getPartGroup(){
        Map<String,String> response = new HashMap<>();
        try{
            List<PartGroup> list = new ArrayList<>();
            List<CarPartGroup> groupList= vehicleService.getPartsGroup();
            for(CarPartGroup pg : groupList){
                String base64Image = Base64.getEncoder().encodeToString(pg.getImage());
                String groupName = pg.getGroup_name();
                List<String> partsList = vehicleService.getFourCarPartsByGroupId(pg.getGroup_id());
                list.add( new PartGroup(groupName,base64Image, partsList));
            }
            return ResponseEntity.ok(list);
        }catch (DataAccessException e){
            System.out.println("message: "+e.getMessage());
            response.put("message", "error: "+e.getMessage());
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/getPartsList")
    private ResponseEntity<?> getPartsList(@RequestBody String partGroup){
        Map<String,String> response = new HashMap<>();
        String partGroupSub = substringIncomingString(partGroup);
        try{
            int groupId = vehicleService.getGroupIdByGroupName(partGroupSub);
            List<Part> list = new ArrayList<>();
            List<CarPartType> partsList = vehicleService.getPartsTypeByGroupId(groupId);
            for(CarPartType pg : partsList){
                String base64Image = Base64.getEncoder().encodeToString(pg.getImage());
                String partTypeName = pg.getPartTypeName();
                list.add(new Part(partTypeName,base64Image));
            }
            return ResponseEntity.ok(list);
        }catch (DataAccessException e){
            System.out.println("message: "+e.getMessage());
            response.put("message", "error: "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getModelAll")
    public ResponseEntity<?> getEveryModel(){
        try {
            return ResponseEntity.ok(vehicleService.getEveryModel());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error loading model " +e.getMessage());
        }
    }

    @GetMapping("/getGenerationAll")
    public ResponseEntity<?> getEveryGeneration(){
        try {
            List<Generation> list = vehicleService.getEveryGeneration();
            for (Generation o : list) {
                System.out.println(o.getGeneration()+" "+o.getGeneration_id());
            }
            return ResponseEntity.ok(vehicleService.getEveryGeneration());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error loading generation " +e.getMessage());
        }
    }

    @GetMapping("/getEngineAll")
    public ResponseEntity<?> getEveryEngine(){
        try {
            return ResponseEntity.ok(vehicleService.getEveryEngine());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error loading engine " +e.getMessage());
        }
    }

}
