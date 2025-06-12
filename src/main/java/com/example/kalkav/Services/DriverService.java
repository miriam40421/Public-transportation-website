package com.example.kalkav.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kalkav.Converters.DriverConverter;
import com.example.kalkav.DTOs.DriverDto;
import com.example.kalkav.Models.Driver;
import com.example.kalkav.Repositories.DriverRepository;

@Service
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;
    @Autowired
    public DriverConverter driverConverter;

    public List<DriverDto> getAll() {
        return driverConverter.toDtoList(driverRepository.findAll());
    }

    public boolean createDriver(DriverDto TravelDto) {
        Driver Driver = driverConverter.toModel(TravelDto);
        if (!driverRepository.exists(Example.of(Driver))) {
            driverRepository.save(Driver);
            return true;
        }
        return false;
    }
}
