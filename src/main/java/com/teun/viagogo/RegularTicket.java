package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class RegularTicket implements Ticket {
    private double price;

    public RegularTicket(double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
