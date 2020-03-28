package org.codeacademy;

import org.codeacademy.exceptions.InvalidChoiceException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UserInputReader {
    Scanner sc = new Scanner(System.in);

    public String readString() {
        return sc.next();
    }

    public double readDouble() {
        return Math.abs(sc.nextDouble());
    }

    public int getValidChoice(int choices) throws InvalidChoiceException {
        String choiceString = sc.next();
        try {
            int choice = Integer.parseInt(choiceString);
            if (choice > 0 && choice <= choices) {
                return choice;
            }
        } catch (Exception e) {
            throw new InvalidChoiceException();

        }

        throw new InvalidChoiceException();
    }


    public Date getValidDate() throws InvalidChoiceException {
        String userInput = sc.next();
        try {
            Date date = Date.valueOf(userInput);
            return date;

        } catch (Exception e) {
            throw new InvalidChoiceException();
        }
    }


}
