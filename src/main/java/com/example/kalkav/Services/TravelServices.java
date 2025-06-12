package com.example.kalkav.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kalkav.Converters.TravelConverter;
import com.example.kalkav.DTOs.TravelDto;
import com.example.kalkav.Models.Travel;
import com.example.kalkav.Repositories.TravelRepository;

@Service
public class TravelServices {
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public TravelConverter travelConverter;

    public List<TravelDto> getAll() {
        return travelConverter.toDtoList(travelRepository.findAll());
    }

    public boolean createTravle(TravelDto TravelDto) {
        Travel travle = travelConverter.toModel(TravelDto);
        if (!travelRepository.exists(Example.of(travle))) {
            travelRepository.save(travle);
            return true;
        }
        return false;
    }

}