package org.codeacademy.current_user;

import java.sql.ResultSet;

public class User {
    String personalId;
    String userName;
    String password;
    String name;
    String surname;

    public User(String personalId, String userName, String password, String name, String surname) {
        this.personalId = personalId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(String personalId, String userName) {
        this.personalId = personalId;
        this.userName = userName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getUserName() {
        return userName;
    }
}
