package org.codeacademy.current_user;

public class Account {
    private String accNo;
    private double balance;
    private String accType;

    public Account(String accNo, double balance, String accType) {
        this.accNo = accNo;
        this.balance = balance;
        this.accType = accType;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccType() {
        return accType;
    }
}
