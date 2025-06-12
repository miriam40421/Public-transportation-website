package com.example.kalkav.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kalkav.DTOs.StationDto;
import com.example.kalkav.Services.StationService;

@RestController
@RequestMapping("/Station")
public class StationController {
    @Autowired
    public StationService stationService;

    @GetMapping("/get")
    public ResponseEntity<List<StationDto>> getMethodName() {
        return ResponseEntity.ok().body(stationService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody StationDto entity) {
        if (stationService.createStation(entity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/addToLine")
    public ResponseEntity<Void> AddToLine(@RequestParam(name = "stationId", required = true) Long stationId,
            @RequestParam(name = "lineId", required = true) Long lineId,
            @RequestParam(name = "orderIndex", required = true) int orderIndex) {
        if (stationService.AddToLine(stationId, lineId, orderIndex)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/removeFromLine")
    public ResponseEntity<Integer> removeFromLine(@RequestParam(name = "stationId", required = true) Long stationId,
            @RequestParam(name = "lineId", required = true) Long lineId) {
        if (stationService.removeFromLine(stationId, lineId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/{numStation}/lines/{numLine}")
    public List<String> findStation(
            @PathVariable int numStation,
            @PathVariable int numLine) {
        return stationService.findStation(numStation, numLine);
    }

    @GetMapping("/{numStation}/lines")
    public ResponseEntity<List<String>> getAllLineStations(@PathVariable int numStation) {
        List<String> results = stationService.findAllLineStation(numStation);
        return ResponseEntity.ok(results);
    }

}
