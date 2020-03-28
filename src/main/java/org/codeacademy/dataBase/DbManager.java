package org.codeacademy.dataBase;

import org.codeacademy.current_user.AccTransaction;
import org.codeacademy.current_user.Account;
import org.codeacademy.current_user.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class DbManager {

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    //todo prid4ti normaliu exceptionus
    public void register(String userId, String userName, String userPass, String name, String surname) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?);");
            statement.setString(1, userId);
            statement.setString(2, userName);
            statement.setString(3, userPass);
            statement.setString(4, name);
            statement.setString(5, surname);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createAccInDb(User signedInUser, int acctype) {
        String bankAccNo = "LT" + new Random().nextInt(999999999 - 1000000) + 1;
        String accountType;
        if (acctype == 1) {
            accountType = "debit";
        } else {
            accountType = "credit";
        }
        String personalId = signedInUser.getPersonalId();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bankAcc (bankAccNo, personal_id, acc_type) VALUES (?, ?, ?);");
            statement.setString(1, bankAccNo);
            statement.setString(2, personalId);
            statement.setString(3, accountType);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String signIn(String userName, String userPass) {
        String personId = null;
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT PERSONAL_ID FROM USER WHERE USER_NAME = ? AND PASSWORD = ?");
            statement.setString(1, userName);
            statement.setString(2, userPass);


            ResultSet personIdSet = statement.executeQuery();
            while (personIdSet.next()) {
                personId = personIdSet.getString("PERSONAL_ID");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return personId;
    }

    public ArrayList<Account> getDbAccAndBalances(User signedInUser) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT bankAccNo, balance, acc_type FROM bankAcc WHERE PERSONAL_ID = ?");
            statement.setString(1, signedInUser.getPersonalId());
            ResultSet accAndBalancesSet = statement.executeQuery();

            ArrayList<Account> accountsList = new ArrayList<>();
            while (accAndBalancesSet.next()) {
                String bankAccNo = accAndBalancesSet.getString("bankAccNo");
                double balance = accAndBalancesSet.getDouble("balance");
                String accType = accAndBalancesSet.getString("acc_type");
                Account account = new Account(
                        bankAccNo, balance, accType
                );
                accountsList.add(account);
            }
            return accountsList;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    private void transactionRecord(String accountNo, double amount, String purpose) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO transaction VALUES (?, ?, ?, ?, ?);");
            statement.setString(1, accountNo);
            statement.setDouble(2, amount);
            statement.setString(3, purpose);
            statement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            statement.setTime(5, Time.valueOf(LocalTime.now()));

            statement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dbWithdrawOrDepositMoney(double amount, String accountNo, String purpose) {
        double balance = 0;
        double newBalance;
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM bankAcc WHERE bankAccNo = ?");
            statement.setString(1, accountNo);

            ResultSet balanceSet = statement.executeQuery();

            while (balanceSet.next()) {
                balance = balanceSet.getDouble("balance");
            }

            newBalance = balance + amount;

            statement = connection.prepareStatement("UPDATE bankAcc SET balance = ? WHERE bankAccNo = ?");
            statement.setDouble(1, newBalance);
            statement.setString(2, accountNo);
            statement.executeUpdate();

            transactionRecord(accountNo, amount, purpose);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean accountValidation(String accountTo) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT bankAccNo FROM bankAcc WHERE bankAccNo = ?");
            statement.setString(1, accountTo);
            ResultSet accounts = statement.executeQuery();

            if (accounts.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean userAccountValidation(String accountTo, String signedInUser) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT bankAccNo FROM bankAcc WHERE bankAccNo = ? AND personal_id = ?");
            statement.setString(1, accountTo);
            statement.setString(2, signedInUser);
            ResultSet accounts = statement.executeQuery();

            if (accounts.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public ArrayList<AccTransaction> getAllTransactions(String accountNo) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT amount, destination, date, time  FROM transaction WHERE bankAccNo = ?");
            statement.setString(1, accountNo);
            ResultSet transactionsSet = statement.executeQuery();

            return getAccTransactions(accountNo, transactionsSet);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public ArrayList<AccTransaction> getTransactionsBetweenDates(String accountNo, Date dateFrom, Date dateTill) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT amount, destination, date, time  FROM transaction WHERE bankAccNo = ?" +
                    " AND date BETWEEN ? AND ?");
            statement.setString(1, accountNo);
            statement.setDate(2, dateFrom);
            statement.setDate(3, dateTill);
            ResultSet transactionsSet = statement.executeQuery();

            return getAccTransactions(accountNo, transactionsSet);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private ArrayList<AccTransaction> getAccTransactions(String accountNo, ResultSet transactionsSet) throws SQLException {
        ArrayList<AccTransaction> transactionsList = new ArrayList<>();
        while (transactionsSet.next()) {
            double amount = transactionsSet.getDouble("amount");
            String destination = transactionsSet.getString("destination");
            Date date = transactionsSet.getDate("date");
            Time time = transactionsSet.getTime("time");
            AccTransaction transaction = new AccTransaction(
                    accountNo, amount, destination, date, time
            );
            transactionsList.add(transaction);
        }
        return transactionsList;
    }
}

