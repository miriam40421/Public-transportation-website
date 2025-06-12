package com.example.kalkav.Models;

import jakarta.persistence.*;
// import lombok.Data;

// @Data
@Entity
@Table(name = "station_line")

public class Station_Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station stationId;
    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line lineId;

    private Integer orderIndex;

    public Station_Line() {
        this.orderIndex = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    public int getorderIndex() {
        return orderIndex;
    }

    public void setorderIndex(int o) {
        orderIndex = o;
    }

}
