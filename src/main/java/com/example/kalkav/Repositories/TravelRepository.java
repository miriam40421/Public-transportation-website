package com.example.kalkav.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kalkav.Models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByLineId(Long id);

}
