package com.example.kalkav.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    // שינוי
    @JoinTable(name = "stationId", joinColumns = @JoinColumn(name = "station_id"), inverseJoinColumns = @JoinColumn(name = "lineId"))
    private List<Station_Line> lines = new ArrayList<>();
    private int number;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int num) {
        number = num;
    }

    public List<Station_Line> getLines() {
        return lines;
    }

    public void setLines(List<Station_Line> lines) {
        this.lines = lines;
    }

}
