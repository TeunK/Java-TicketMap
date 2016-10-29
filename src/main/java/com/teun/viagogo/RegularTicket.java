package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class RegularTicket implements Ticket {
    private double price;

    public RegularTicket(double price) {
        setPrice(price);
    }

    public void setPrice(double price) {
        if (price >= 1) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Ticket price should be 1.00 USD or above");
        }
    }

    public double getPrice() {
        return price;
    }
}
