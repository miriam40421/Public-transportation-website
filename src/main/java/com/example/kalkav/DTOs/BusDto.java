package com.example.kalkav.DTOs;

public class BusDto {
    private Long Id;
    private int Seats;
    private String License_plate;

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
