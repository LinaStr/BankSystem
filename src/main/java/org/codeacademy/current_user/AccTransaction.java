package org.codeacademy.current_user;

import java.sql.Date;
import java.sql.Time;

public class AccTransaction {
    private String bankAccNo;
    private double amount;
    private String destination;
    private Date date;
    private Time time;

    public AccTransaction(String bankAccNo, double amount, String destination, Date date, Time time) {
        this.bankAccNo = bankAccNo;
        this.amount = amount;
        this.destination = destination;
        this.date = date;
        this.time = time;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public double getAmount() {
        return amount;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
