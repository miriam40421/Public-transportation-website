package com.example.kalkav.DTOs;

import java.util.ArrayList;
import java.util.List;

public class StationDto {

    private Long id;
    private String name;
    private int number;
    private List<Integer> lines = new ArrayList<>();

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

    public void setLines(List<Integer> lines) {
        this.lines = lines;
    }

    public List<Integer> getLines() {

        return lines;
    }
}
