package com.example.kalkav.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalkav.Models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

}
