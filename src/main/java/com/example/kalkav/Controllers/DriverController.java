package com.example.kalkav.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kalkav.DTOs.DriverDto;
import com.example.kalkav.Services.DriverService;

@RestController
@RequestMapping("/Driver")
public class DriverController {
    @Autowired
    public DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity<List<DriverDto>> getMethodName() {
        return ResponseEntity.ok().body(driverService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody DriverDto entity) {
        System.out.println("a");
        if (driverService.createDriver(entity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

}
