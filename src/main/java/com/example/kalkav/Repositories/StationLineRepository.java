package com.example.kalkav.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kalkav.Models.Station_Line;

public interface StationLineRepository extends JpaRepository<Station_Line, Long> {
    Optional<Station_Line> findByLineId_IdAndStationId_Id(Long lineId, Long stationId);

}
