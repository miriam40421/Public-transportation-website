package com.example.kalkav.DTOs;

// import com.example.lesson2.Models.Travel;

public class DriverDto {
    private Long Id;
    private String Name;
    private String Phone;
    // private List<Travel> travels;

    public Long getId() {
        return Id;
    }

    public String getPhone() {
        return Phone;
    }

    public String getName() {
        return Name;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setName(String n) {
        Name = n;
    }

    public void setPhone(String l) {
        Phone = l;
    }
}
