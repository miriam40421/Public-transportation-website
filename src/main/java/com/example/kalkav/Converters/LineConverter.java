package com.example.kalkav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kalkav.DTOs.LineDto;
import com.example.kalkav.Models.Line;
import com.example.kalkav.Models.Station;
import com.example.kalkav.Models.Station_Line;
import com.example.kalkav.Repositories.LineRepository;
import com.example.kalkav.Repositories.StationLineRepository;
import com.example.kalkav.Repositories.StationRepository;

public class LineConverter {

    @Autowired

    private StationRepository stationRepository;
    @Autowired

    private LineRepository lineRepository;
    @Autowired

    private StationLineRepository stationLineRepository;

    public LineConverter() {
    }

    public LineDto toDto(Line line) {
        LineDto lineDto = new LineDto();
        lineDto.setId(line.getId());
        lineDto.setNumber(line.getNumber());
        lineDto.setSourse(line.getSourse());
        lineDto.setDestination(line.getDestination());
        line.getStation().forEach(x -> lineDto.getStations().add(x.getStationId().getId()));
        lineDto.setnumOfStation(line.getnumOfStation());
        return lineDto;
    }

    public List<LineDto> toDtoList(List<Line> list) {
        return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    }

    int orderIndex = 0;

    public Line toModel(LineDto lineDto) {
        if (lineDto == null) {
            throw new IllegalArgumentException("LineDto cannot be null");
        }
        if (lineDto.getSourse() == null || lineDto.getDestination() == null) {
            throw new IllegalArgumentException("Number, Source, and Destination cannot be null");
        }

        System.out.println("\n" + "******************************convert*********************");
        Line line = new Line();
        line.setId(lineDto.getId());
        line.setNumber(lineDto.getNumber());
        line.setSourse(lineDto.getSourse());
        line.setDestination(lineDto.getDestination());
        line.setnumOfStation(lineDto.getnumOfStation());
        lineRepository.save(line);

        lineDto.getStations().forEach(s -> {
            Station st = stationRepository.findById(s)
                    .orElseThrow(() -> new IllegalArgumentException("Station with ID " + s + " not found"));

            Station_Line sl = new Station_Line();
            sl.setLineId(line);
            sl.setStationId(st);
            sl.setorderIndex(orderIndex++);
            st.getLines().add(sl);
            line.getStation().add(sl);
            st.getLines().forEach(x -> System.out.println(x.getLineId().getNumber()));
            stationLineRepository.save(sl);
        });

        return line;
    }

    public List<Line> toModelList(List<LineDto> list) {
        return list.stream().map(t -> toModel(t))
                .collect(Collectors.toList());
    }
}
