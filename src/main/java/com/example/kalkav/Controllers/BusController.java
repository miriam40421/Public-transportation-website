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

import com.example.kalkav.DTOs.BusDto;
import com.example.kalkav.Services.BusService;

@RestController
@RequestMapping("/Bus")
public class BusController {
    @Autowired
    public BusService busService;

    @GetMapping("/get")
    public ResponseEntity<List<BusDto>> getMethodName() {
        return ResponseEntity.ok().body(busService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody BusDto entity) {
        System.out.println("a");
        if (busService.createBus(entity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

}
