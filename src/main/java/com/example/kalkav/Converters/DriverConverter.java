package com.example.kalkav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kalkav.DTOs.DriverDto;
import com.example.kalkav.Models.Driver;

public class DriverConverter {

    public DriverDto toDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setName(driver.getName());
        driverDto.setPhone(driver.getPhone());
        return driverDto;
    }

    public List<DriverDto> toDtoList(List<Driver> list) {
        return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    }

    public Driver toModel(DriverDto dd) {
        Driver d = new Driver();
        d.setId(dd.getId());
        d.setName(dd.getName());
        d.setPhone(dd.getPhone());
        return d;
    }

    public List<Driver> toModelList(List<DriverDto> list) {
        return list.stream().map(t -> toModel(t)).collect(Collectors.toList());

    }
}
