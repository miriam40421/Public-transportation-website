package com.example.kalkav.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kalkav.DTOs.TravelDto;
import com.example.kalkav.Services.TravelServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Travel")
public class TravelController {
    @Autowired
    public TravelServices travelServices;

    @GetMapping("/get")
    public ResponseEntity<List<TravelDto>> getMethodName() {
        return ResponseEntity.ok().body(travelServices.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody TravelDto entity) {
        if (travelServices.createTravle(entity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
