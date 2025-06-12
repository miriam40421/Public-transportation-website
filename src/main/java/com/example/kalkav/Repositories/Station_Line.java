package com.example.kalkav.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalkav.Models.Station;

@Repository
public interface Station_Line extends JpaRepository<Station, Long> {

}
