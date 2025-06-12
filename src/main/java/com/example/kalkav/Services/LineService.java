package com.example.kalkav.Services;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kalkav.Converters.LineConverter;
import com.example.kalkav.DTOs.LineDto;
import com.example.kalkav.Models.Line;
import com.example.kalkav.Models.Station;
import com.example.kalkav.Models.Station_Line;
import com.example.kalkav.Models.Travel;
import com.example.kalkav.Repositories.LineRepository;
import com.example.kalkav.Repositories.StationLineRepository;
import com.example.kalkav.Repositories.StationRepository;
import com.example.kalkav.Repositories.TravelRepository;

@Service
public class LineService {
    @Autowired
    public LineRepository lineRepository;
    @Autowired
    public LineConverter lineConverter;
    @Autowired
    public TravelRepository travelRepository;
    @Autowired
    public StationRepository stationRepository;
    @Autowired
    public StationLineRepository stationLineRepository;

    public List<LineDto> getAll() {
        return lineConverter.toDtoList(lineRepository.findAll());
    }

    // הוספת קו
    public boolean createLine(LineDto lineDto) {
        Line line = lineConverter.toModel(lineDto);
        if (lineRepository.exists(Example.of(line))) {
            return true;
        }
        return false;
    }

    // לשמיעת כל תחנות הקו
    public List<String> allStaioLine(int lineNumber) {
        Line lin = lineRepository.findByNumber(lineNumber)
                .orElseThrow(() -> new NoSuchElementException("Line not found"));
        List<Station_Line> stations = lin.getStation();
        List<String> l = new ArrayList<>(); // אתחול הרשימה

        for (Station_Line stationLine : stations) {
            Station s = stationRepository.findById(stationLine.getStationId().getId())
                    .orElseThrow(() -> new NoSuchElementException("Station not found"));
            l.add(s.getName()); // הוספת שם התחנה לרשימה
        }
        return l;
    }

    // לוחות זמנים של קו
    public List<Time> schedules(int number, Optional<String> t) {
        List<Time> departureTimes = new ArrayList<>(); // אתחול הרשימה

        // אם t לא קיים או ריק, מחזירים את כל הנסיעות
        if (!t.isPresent() || t.get().isEmpty()) {
            Line lin = lineRepository.findByNumber(number)
                    .orElseThrow(() -> new NoSuchElementException("Line not found"));
            Long id = lin.getId();
            List<Travel> travels = travelRepository.findByLineId(id);

            for (Travel travel : travels) {
                departureTimes.add(travel.getDeparture_time()); // הוספת זמן היציאה לרשימה
            }
            return departureTimes;
        }

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        try {
            java.util.Date parsedDate = format.parse(t.get());
            Time sqlTime = new Time(parsedDate.getTime());

            // יצירת אובייקט Calendar כדי לחשב את הזמנים
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);

            // שעה אחרי
            calendar.add(Calendar.MINUTE, 60);
            Time halfHourAfter = new Time(calendar.getTimeInMillis());

            // שעה לפני
            calendar.setTime(parsedDate);
            calendar.add(Calendar.HOUR, -1);
            Time oneHourBefore = new Time(calendar.getTimeInMillis());

            System.out.println("Converted Time: " + sqlTime);
            Line lin = lineRepository.findByNumber(number)
                    .orElseThrow(() -> new NoSuchElementException("Line not found"));
            Long id = lin.getId();
            List<Travel> travels = travelRepository.findByLineId(id);

            for (Travel travel : travels) {
                Time departureTime = travel.getDeparture_time(); // קבלת זמן היציאה

                // השוואת השעה שהוזנה עם זמן היציאה
                if (departureTime.equals(sqlTime) ||
                        (departureTime.after(sqlTime) && departureTime.before(halfHourAfter)) ||
                        (departureTime.before(sqlTime) && departureTime.after(oneHourBefore))) {
                    departureTimes.add(departureTime); // הוספת זמן היציאה לרשימה אם הוא בטווחים המבוקשים
                }
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format: " + e.getMessage());
        }

        return departureTimes;
    }

    // מיקומם של האוטובוסים לאורך כל ציר הנסיעה
    public List<String> allPlacesLine(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Line number must be positive");
        }
        LocalTime currentTime = LocalTime.now();
        Line lin = lineRepository.findByNumber(number)
                .orElseThrow(() -> new NoSuchElementException("Line not found"));
        Long id = lin.getId();
        List<Travel> travels = travelRepository.findByLineId(id);
        if (travels.isEmpty()) {
            throw new NoSuchElementException("No travels found for line with number: " + number);
        }

        List<Station_Line> station_Lines = lin.getStation();
        LocalTime maxTime = currentTime.minusMinutes(station_Lines.size());
        List<Travel> newTravels = travels.stream()
                .filter(t -> t.getDeparture_time().toLocalTime().isBefore(currentTime)
                        && t.getDeparture_time().toLocalTime().isAfter(maxTime))
                .collect(Collectors.toList());
        List<String> results = new ArrayList<>();

        for (Travel t : newTravels) {

            Time departureTime = t.getDeparture_time();

            int minutesDifference = (int) java.time.Duration.between(departureTime.toLocalTime(), currentTime)
                    .toMinutes();
            System.out.println(minutesDifference + ".......................");
            for (Station_Line s : station_Lines) {
                if (s.getorderIndex() == minutesDifference) {
                    results.add(s.getStationId().getName());
                }
            }
        }
        return results;
    }
}