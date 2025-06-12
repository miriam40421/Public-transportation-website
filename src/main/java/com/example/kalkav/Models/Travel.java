package com.example.kalkav.Models;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
// import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// import lombok.Data;

// @Data
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus._id")
    private Bus bus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver._id")
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Line_id")
    private Line line;
    private Time Departure_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus b) {
        this.bus = b;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver d) {
        this.driver = d;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line l) {
        this.line = l;
    }

    public Time getDeparture_time() {
        return Departure_time;
    }

    public void setDeparture_time(Time l) {
        this.Departure_time = l;
    }

}
