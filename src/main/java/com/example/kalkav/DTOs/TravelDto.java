package com.example.kalkav.DTOs;

import java.sql.Time;

// import lombok.Data;

// @Data
public class TravelDto {
    private Long id;
    private Long bus;
    private Long driver;
    private Long line;
    private Time departureTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getbus() {
        return bus;
    }

    public void setbus(Long b) {
        this.bus = b;
    }

    public Long getdriver() {
        return driver;
    }

    public void setdriver(Long d) {
        this.driver = d;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long l) {
        this.line = l;
    }

    public Time getdepartTime() {
        return departureTime;
    }

    public void setdepartTime(Time l) {
        this.departureTime = l;
    }

    @Override
    public String toString() {
        return "id:" + id + ",bus:" + bus + ",driver:" + driver + ",line:" + line + ",time:" + departureTime;
    }
}
