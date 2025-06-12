package com.example.kalkav.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kalkav.Converters.StationConverter;
import com.example.kalkav.DTOs.StationDto;
import com.example.kalkav.Models.Line;
import com.example.kalkav.Models.Station;
import com.example.kalkav.Models.Station_Line;
import com.example.kalkav.Models.Travel;
import com.example.kalkav.Repositories.LineRepository;
import com.example.kalkav.Repositories.StationLineRepository;
import com.example.kalkav.Repositories.StationRepository;
import com.example.kalkav.Repositories.TravelRepository;

import java.time.LocalTime;

@Service
public class StationService {

    @Autowired
    public StationRepository stationRepository;
    @Autowired
    public StationConverter stationConverter;
    @Autowired
    public LineRepository lineRepository;
    @Autowired
    public StationLineRepository stationLineRepository;
    @Autowired
    public TravelRepository travelRepository;

    public List<StationDto> getAll() {
        return stationConverter.toDtoList(stationRepository.findAll());
    }

    public boolean createStation(StationDto stationDto) {
        Station station = stationConverter.toModel(stationDto);
        if (!stationRepository.exists(Example.of(station))) {
            stationRepository.save(station);
            return true;
        }
        return false;
    }

    // הוספת תחנה לקו
    public boolean AddToLine(Long stationId, Long lineId, int orderIndex) {
        try {
            Line line = lineRepository.findById(lineId)
                    .orElseThrow(() -> new IllegalArgumentException("Line not found"));
            Station station = stationRepository.findById(stationId)
                    .orElseThrow(() -> new IllegalArgumentException("Station not found"));

            System.out.println("before add line" + line.getStation().size());
            System.out.println("before add st" + station.getLines().size());

            List<Station_Line> stations = line.getStation();
            int i = 0;
            while (i < stations.size() && stations.get(i).getorderIndex() < orderIndex) {
                i++;
            }
            for (; i < stations.size(); i++) {
                stations.get(i).setorderIndex(stations.get(i).getorderIndex() + 1);
            }

            Station_Line station_Line = new Station_Line();
            station_Line.setorderIndex(orderIndex);
            station_Line.setStationId(station);
            station_Line.setLineId(line);
            line.getStation().add(station_Line);
            station.getLines().add(station_Line);
            stationLineRepository.save(station_Line);

            System.out.println("after add line" + line.getStation().size());
            System.out.println("after add st" + station.getLines().size());
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }

    // מחיקת תחנה מקו
    // Long y;
    // int c = 0;
    // boolean f = false;

    public boolean removeFromLine(Long stationId, Long lineId) {
        try {
            Line line = lineRepository.findById(lineId).orElse(null);
            if (line == null) {
                return false; // הקו לא קיים
            }

            List<Station_Line> stations = line.getStation();
            Station_Line stationToRemove = null;

            // חפש את התחנה שברצונך להסיר
            for (Station_Line station : stations) {
                if (station.getStationId().getId().equals(stationId)) {
                    stationToRemove = station;
                    break;
                }
            }

            if (stationToRemove == null) {
                return false; // התחנה לא נמצאה בקו
            }

            // עדכן את אינדקסים של התחנות
            for (Station_Line station : stations) {
                if (station.getorderIndex() > stationToRemove.getorderIndex()) {
                    station.setorderIndex(station.getorderIndex() - 1);
                }
            }

            // הסר את התחנה מהקו
            stations.remove(stationToRemove);
            Station station = stationRepository.findById(stationId).orElse(null);
            if (station != null) {
                station.getLines().removeIf(x -> x.getLineId().getId().equals(lineId));
            }

            stationLineRepository.delete(stationToRemove);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    // חיפוש לפי תחנה-כל הקווים
    public List<String> findAllLineStation(int numStation) {
        List<String> results = new ArrayList<>();

        Station station = stationRepository.findByNumber(numStation)
                .orElseThrow(() -> new NoSuchElementException("Station not found"));
        System.out.println(station.getNumber());
        List<Station_Line> ss = station.getLines();
        for (Station_Line s : ss) {
            Line w = lineRepository.findById(s.getLineId().getId()).get();
            System.out.println(w.getNumber());
            int g = w.getNumber();
            System.out.println(g);
            List<String> d = findStation(numStation, g);
            results.addAll(d);
        }
        return results;
    }

    // חיפוש לפי תחנה-הכנסת קו אחד
    public List<String> findStation(int numStation, int numLine) {
        Station station = stationRepository.findByNumber(numStation)
                .orElseThrow(() -> new NoSuchElementException("Station not found"));
        Line line = lineRepository.findByNumber(numLine)
                .orElseThrow(() -> new NoSuchElementException("Line not found"));
        List<String> results = new ArrayList<>();

        boolean exists = station.getLines().stream()
                .anyMatch(stationLine -> stationLine.getLineId().getNumber() == line.getNumber());

        if (!exists) {
            results.add("אחד מהנתונים שהוקשו שגוי");
        } else {
            Station_Line stationLine = stationLineRepository
                    .findByLineId_IdAndStationId_Id(line.getId(), station.getId())
                    .orElseThrow(() -> new NoSuchElementException("Station-Line relation not found"));

            List<Travel> travels = travelRepository.findByLineId(line.getId());

            LocalTime currentTime = LocalTime.now();
            LocalTime maxTime = currentTime.plusHours(3);

            for (Travel travel : travels) {
                LocalTime departureTime = travel.getDeparture_time().toLocalTime();
                LocalTime adjustedTime = departureTime.plusMinutes(stationLine.getorderIndex());

                // בדוק אם זמן היציאה נמצא בטווח של 3 שעות מהזמן הנוכחי
                if (departureTime.isAfter(currentTime)) {
                    results.add("go out: " + departureTime + " come: " + adjustedTime + " line: "
                            + travel.getLine().getNumber() + "pp" + travel.getId());
                }
                // בדוק אם זמן הגעת התחנה הוא אחרי הזמן הנוכחי
                else if (currentTime.isAfter(departureTime) && adjustedTime.isAfter(currentTime)) {
                    results.add("come: "
                            + adjustedTime.minusHours(currentTime.getHour()).minusMinutes(currentTime.getMinute())
                            + " minutes " + travel.getLine().getNumber() + travel.getId());
                }
            }
        }

        return results;
    }

    public boolean deleteStation(Long id) {
        if (stationRepository.existsById(id)) {
            stationRepository.deleteById(id);
            return true;
        }
        return false;

    }

}
