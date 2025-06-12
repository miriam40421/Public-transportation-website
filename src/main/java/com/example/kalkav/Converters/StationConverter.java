package com.example.kalkav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kalkav.DTOs.StationDto;
import com.example.kalkav.Models.Station;

public class StationConverter {

    public StationDto toDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setNumber(station.getNumber());
        station.getLines().forEach(x -> stationDto.getLines().add(x.getLineId().getNumber()));
        if (station.getId() == 6) {
            System.out.println("stationDto after" + stationDto.getLines().size());
            System.out.println("station after" + station.getLines().size());
        }

        return stationDto;
    }

    public List<StationDto> toDtoList(List<Station> list) {
        return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    }

    public Station toModel(StationDto sts) {
        Station s = new Station();
        s.setId(sts.getId());
        s.setName(sts.getName());
        s.setNumber(sts.getNumber());
        return s;
    }

    public List<Station> toModelList(List<StationDto> list) {
        return list.stream().map(t -> toModel(t)).collect(Collectors.toList());

    }
}
