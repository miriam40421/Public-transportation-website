package com.example.kalkav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kalkav.DTOs.BusDto;
import com.example.kalkav.Models.Bus;

public class BusConverter {

    public BusDto toDto(Bus bus) {
        BusDto BusDto = new BusDto();
        BusDto.setId(bus.getId());
        BusDto.setLicense_plate(bus.getLicense_plate());
        BusDto.setSeats(bus.getSeats());
        return BusDto;
    }

    public List<BusDto> toDtoList(List<Bus> list) {
        return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    }

    public Bus toModel(BusDto BusDto) {
        Bus Bus = new Bus();
        Bus.setId(BusDto.getId());
        Bus.setLicense_plate(BusDto.getLicense_plate());
        Bus.setSeats(BusDto.getSeats());
        return Bus;
    }

    public List<Bus> toModelList(List<BusDto> list) {
        return list.stream().map(t -> toModel(t)).collect(Collectors.toList());

    }
}
