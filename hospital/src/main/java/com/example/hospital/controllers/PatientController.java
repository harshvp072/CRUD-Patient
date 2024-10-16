package com.example.hospital.controllers;

import com.example.hospital.entities.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/patients") // <- endpoint
//server_url/v1/patients
public class PatientController {

    HashMap<Integer,Patient> patients = new HashMap<>();

//    CREATE OPERATION
    @PostMapping("")
    public String addPatient(@RequestBody Patient patint){
        int key = patint.getId();
        patients.put(key,patint);
        return "Patient added Successfully";
    }

//    READ or RETRIEVE OPERATION

    @GetMapping("/all")
    public List<Patient> getAll(){
        return new ArrayList<>(patients.values());
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable int patientId){
        //esme hum path me id dalenge tab hi hume value milegi
        //localhost:8080/v1/patients/1
        return patients.get(patientId);
    }

    @GetMapping("")
    public Patient getpatinetByQuery(@RequestParam int patientId){
        //esme hum path me "?" dalke value ko nikal skte hai
        //localhost:8080/v1/patients?patientId=1
        return patients.get(patientId);
    }

//   UPDATE OPERATION
    //PUT
    @PutMapping("")
    public String putmapping(@RequestParam int patientId,@RequestBody Patient updatedPatnt){

        if(patients.containsKey(patientId)){
            patients.put(patientId,updatedPatnt);
            return "patient updated successfully";
        }else{
            return "Patient not found";
        }
    }

    //PATCH
   @PatchMapping("")
    public String patchMapping(@RequestParam int patntID,@RequestBody Patient updatedinfo){
        if(patients.containsKey(patntID)){
            Patient existingPat = patients.get(patntID);

            if(updatedinfo.getName() != null){
                existingPat.setName(updatedinfo.getName());
            }
            if(updatedinfo.getAge() != 0){
                existingPat.setAge(updatedinfo.getAge());
            }
            if(updatedinfo.getDisease() != null){
                existingPat.setDisease(updatedinfo.getDisease());
            }
            if(updatedinfo.getId() != 0){
                existingPat.setId(updatedinfo.getId());
            }

            patients.put(patntID,existingPat);
            return "Patient added successfully";
        }else{
            return "patient not found";
        }
   }

//   DELETE OPERATION

    @DeleteMapping("/{patientID}")
    public String delete(@PathVariable int patientID){
        if(patients.containsKey(patientID)){
            patients.remove(patientID);
            return "patient deleted";
        }
        else{
            return "patient not deleted";
        }

    }

}
