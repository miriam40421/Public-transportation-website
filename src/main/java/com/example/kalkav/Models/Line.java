package com.example.kalkav.Models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Line {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String Sourse;
    private String destination;
    @OneToMany(mappedBy = "lineId")
    private List<Station_Line> stations = new ArrayList<>();;
    private int numOfStation;
    // private List<Travel> travels;

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
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
        this.id = Id;
    }

    public void setNumber(int num) {
        number = num;
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

    public void setStation(List<Station_Line> tr) {
        stations = tr;
    }

    public List<Station_Line> getStation() {
        return stations;
    }
}
