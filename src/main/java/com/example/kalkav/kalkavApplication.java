package com.example.kalkav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.kalkav.Converters.BusConverter;
import com.example.kalkav.Converters.DriverConverter;
import com.example.kalkav.Converters.LineConverter;
import com.example.kalkav.Converters.StationConverter;
import com.example.kalkav.Converters.TravelConverter;

@SpringBootApplication

public class kalkavApplication {

	public static void main(String[] args) {
		SpringApplication.run(kalkavApplication.class, args);
	}

	@Bean
	public BusConverter getBusConverter() {
		return new BusConverter();
	}

	@Bean
	public TravelConverter getTravelConverter() {
		return new TravelConverter();
	}

	@Bean
	public DriverConverter getdDriverConverter() {
		return new DriverConverter();
	}

	@Bean
	public StationConverter getsStationConverter() {
		return new StationConverter();
	}

	@Bean
	public LineConverter getLineConverter() {
		return new LineConverter();
	}

}
