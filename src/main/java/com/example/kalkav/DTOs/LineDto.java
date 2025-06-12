package com.example.kalkav.DTOs;

import java.util.ArrayList;
import java.util.List;

public class LineDto {
    private Long Id;
    private int Number;
    private int numOfStation;

    private String Sourse;
    private String destination;
    private List<Long> stations = new ArrayList<Long>();

    public Long getId() {
        return Id;
    }

    public int getNumber() {
        return Number;
    }

    public int getnumOfStation() {
        return numOfStation;
    }

    public String getSourse() {
        return Sourse;
    }

    public String getDestination() {
        return destination;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setNumber(int num) {
        Number = num;
    }

    public void setnumOfStation(int num) {
        numOfStation = num;
    }

    public void setSourse(String s) {
        Sourse = s;
    }

    public void setDestination(String d) {
        destination = d;
    }

    public List<Long> getStations() {
        return stations;
    }

    public void setStations(List<Long> stations) {
        this.stations = stations;
    }

}
