package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class RegularTicket extends Ticket {
    public RegularTicket(double price) {
        super(price);
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Ticket price should be greater than zero");
        }
    }

    public double getPrice() {
        return price;
    }
}
