package com.example.kalkav.Controllers;

import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kalkav.DTOs.LineDto;
import com.example.kalkav.Services.LineService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Line")
public class LineController {
    @Autowired
    public LineService lineService;

    @GetMapping("/get")
    public ResponseEntity<List<LineDto>> getMethodName() {
        return ResponseEntity.ok().body(lineService.getAll());
    }

    // כל תחנות הקו
    @GetMapping("/lines/{lineNumber}/stations")
    public ResponseEntity<List<String>> getAllStations(@PathVariable Integer lineNumber) {
        List<String> stations = lineService.allStaioLine(lineNumber);
        return ResponseEntity.ok(stations);
    }

    // לוחות זמנים של קו
    @GetMapping("/lines/{lineNumber}/schedules")
    public ResponseEntity<List<Time>> getSchedules(@PathVariable int lineNumber,
            @RequestParam(required = false) String time) {
        List<Time> schedules = lineService.schedules(lineNumber, Optional.ofNullable(time));
        return ResponseEntity.ok(schedules);
    }

    // מיקומם של האוטובוסים לאורך כל ציר הניעה
    @GetMapping("/{number}/stations")
    public ResponseEntity<List<String>> getAllPlacesLine(@PathVariable int number) {
        try {
            List<String> stations = lineService.allPlacesLine(number);
            return ResponseEntity.ok(stations);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody LineDto entity) {

        if (lineService.createLine(entity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.badRequest().build();
    }

}