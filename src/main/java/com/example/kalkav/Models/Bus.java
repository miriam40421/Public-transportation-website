package com.example.kalkav.Models;

import jakarta.persistence.*;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int Seats;
    private String License_plate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bus", fetch = FetchType.LAZY)
    // public List<Travel> travels;

    public Long getId() {
        return Id;
    }

    public int getSeats() {
        return Seats;
    }

    public String getLicense_plate() {
        return License_plate;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setSeats(int seats) {
        Seats = seats;
    }

    public void setLicense_plate(String l) {
        License_plate = l;
    }
}
