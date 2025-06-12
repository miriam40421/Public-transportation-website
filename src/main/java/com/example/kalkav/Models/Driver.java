package com.example.kalkav.Models;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String Phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", fetch = FetchType.LAZY)
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

    // public void setTraval(List<Travel> tr) {
    // travels = tr;
    // }

    // public List<Travel> getTravel() {
    // return travels;
    // }
}
