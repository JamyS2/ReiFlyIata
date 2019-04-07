package com.iata.reifly;

import java.io.Serializable;
import java.util.ArrayList;

public class Offer implements Serializable {
    ArrayList<Route> route;
    String totalprice;
    String baseprice;
    String title;

    public Offer() {

    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalprice = totalPrice;
    }

    public void setBasePrice(String basePrice) {
        this.baseprice = basePrice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Route> getRoute() {
        return route;
    }

    public String getTotalPrice() {
        return totalprice;
    }

    public String getBasePrice() {
        return baseprice;
    }

    public String getTitle() {
        return title;
    }


}
