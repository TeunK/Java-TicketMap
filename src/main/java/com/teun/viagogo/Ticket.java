package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public interface Ticket extends Comparable<Ticket>  {
    void setPrice(double price);
    double getPrice();
    int compareTo(Ticket o);
}
