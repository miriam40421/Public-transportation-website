package com.example.kalkav.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalkav.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByNumber(int number);

}
