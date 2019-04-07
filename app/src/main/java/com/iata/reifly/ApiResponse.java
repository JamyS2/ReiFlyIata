package com.iata.reifly;

import java.util.ArrayList;

public class ApiResponse {
    public ApiResponse() {

    }
    ArrayList<Offer> offers;
    ArrayList<SearchData> searchData;

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public ArrayList<SearchData> getSearchData() {
        return searchData;
    }

    public void setSearchData(ArrayList<SearchData> searchData) {
        this.searchData = searchData;
    }
}
