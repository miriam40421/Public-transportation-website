package com.example.kalkav.Converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kalkav.DTOs.TravelDto;
import com.example.kalkav.Models.Bus;
import com.example.kalkav.Models.Driver;
import com.example.kalkav.Models.Line;
import com.example.kalkav.Models.Travel;
import com.example.kalkav.Repositories.BusRepository;
import com.example.kalkav.Repositories.DriverRepository;
import com.example.kalkav.Repositories.LineRepository;

@Component
public class TravelConverter {
    @Autowired
    public BusRepository busRepository;
    @Autowired
    public DriverRepository driverRepository;
    @Autowired
    public LineRepository lineRepository;

    public TravelDto toDto(Travel travel) {
        TravelDto travelDto = new TravelDto();

        if (travel != null) {
            travelDto.setId(travel.getId());

            if (travel.getBus() != null) {
                travelDto.setbus(travel.getBus().getId());
            } else {
                travelDto.setbus(null);
            }

            if (travel.getDriver() != null) {
                travelDto.setdriver(travel.getDriver().getId());
            } else {
                travelDto.setdriver(null);
            }

            if (travel.getLine() != null) {
                travelDto.setLine(travel.getLine().getId());
            } else {
                travelDto.setLine(null);
            }

            if (travel.getDeparture_time() != null) {
                travelDto.setdepartTime(travel.getDeparture_time());
            } else {
                travelDto.setdepartTime(null);
            }
        }

        return travelDto;
    }

    public List<TravelDto> toDtoList(List<Travel> list) {
        return list.stream().map(t -> toDto(t)).collect(Collectors.toList());

    }

    public Travel toModel(TravelDto travelDto) {
        Travel travel = new Travel();
        try {
            Bus bus = busRepository.findById(travelDto.getbus()).get();
            travel.setBus(bus);
            // busRepository.findById(travelDto.getbus()).get().travels.add(travelConverter.toModel(travelDto));
        } catch (Exception e) {
            System.out.println("**********************************************************");
            System.out.println(travelDto.getbus());

        }
        try {
            Driver driver = driverRepository.findById(travelDto.getdriver()).get();
            travel.setDriver(driver);
            // driverRepository.findById(travelDto.getbus()).get().getTravel().add(travelConverter.toModel(travelDto));

        } catch (Exception e) {
            System.out.println("**********************************************************");
            System.out.println(travelDto.getdriver());

        }
        try {
            Line line = lineRepository.findById(travelDto.getLine()).get();
            travel.setLine(line);
            // lineRepository.findById(travelDto.getbus()).get().getTravel().add(travelConverter.toModel(travelDto));

        } catch (Exception e) {
            System.out.println("**********************************************************");
            System.out.println(travelDto.getLine());

        }
        System.out.println(travelDto.getdepartTime());
        // travel.setDriver(driverRepository.findById(travelDto.getdriver()).get());
        // travel.setLine(lineRepository.findById(travelDto.getLine()).get());
        travel.setDeparture_time(travelDto.getdepartTime());
        return travel;
    }

    public List<Travel> toModelList(List<TravelDto> list) {
        return list.stream().map(t -> toModel(t)).collect(Collectors.toList());

    }
}
