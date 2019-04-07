package com.iata.reifly;

import java.io.Serializable;

public class Route implements Serializable {

    String start, end, start_wait, end_wait, dep_time, arrival_time, flight_number, baggage_allowance;

    public Route() {

    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getStart_wait() {
        return start_wait;
    }

    public String getEnd_wait() {
        return end_wait;
    }

    public String getDep_time() {
        return dep_time;
    }

    public String getBaggage_allowance() {
        return baggage_allowance;
    }

    public void setBaggage_allowance(String baggage_allowance) {
        this.baggage_allowance = baggage_allowance;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setStart_wait(String start_wait) {
        this.start_wait = start_wait;
    }

    public void setEnd_wait(String end_wait) {
        this.end_wait = end_wait;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public void setArrival_time(String arriaval_time) {
        this.arrival_time = arriaval_time;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

}
