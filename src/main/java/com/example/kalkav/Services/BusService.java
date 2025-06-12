package com.example.kalkav.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kalkav.Converters.BusConverter;
import com.example.kalkav.DTOs.BusDto;
import com.example.kalkav.Models.Bus;
import com.example.kalkav.Repositories.BusRepository;

@Service
public class BusService {
    @Autowired
    public BusRepository busRepository;

    @Autowired
    public BusConverter busConverter;

    public List<BusDto> getAll() {
        return busConverter.toDtoList(busRepository.findAll());
    }

    public boolean createBus(BusDto TravelDto) {
        Bus bus = busConverter.toModel(TravelDto);
        if (!busRepository.exists(Example.of(bus))) {
            busRepository.save(bus);
            return true;
        }
        return false;
    }

}
