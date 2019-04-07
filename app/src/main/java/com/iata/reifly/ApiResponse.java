package com.iata.reifly;

import java.util.ArrayList;

public class ApiResponse {
    public ApiResponse() {

    }
    ArrayList<Offer> offers;

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
}
