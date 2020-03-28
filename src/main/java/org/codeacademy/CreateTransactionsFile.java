package org.codeacademy;

import org.codeacademy.current_user.AccTransaction;
import org.codeacademy.exceptions.FileSaveException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTransactionsFile {
    private static final String FILENAME = "transakcijos.txt";


    public static void transactionsToFile(ArrayList<AccTransaction> transactionsBetweenDates) throws FileSaveException {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))){
            for (AccTransaction transaction: transactionsBetweenDates) {
                bw.write("\n" +
                        "Suma: "+ transaction.getAmount()+
                        " Paskirtis: "+ transaction.getDestination()+
                        " Data: "+ transaction.getDate()+
                        " Laikas: "+ transaction.getTime());
            }
        } catch (IOException e) {
            throw new FileSaveException();
        }
    }

}
