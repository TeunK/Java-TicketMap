package com.teun.viagogo.tickets;

/**
 * Created by Teun on 10/29/2016.
 */
public abstract class Ticket implements Comparable<Ticket>  {
    protected double price;

    public Ticket(double price) {
        setPrice(price);
    }

    public abstract void setPrice(double price);
    public abstract double getPrice();

    public int compareTo(Ticket o) {
        if (this.price > o.getPrice()) {
            return 1;
        } else if (this.price < o.getPrice()) {
            return -1;
        } else {
            return 0;
        }
    }
}
