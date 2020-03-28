package org.codeacademy.meniu;

import org.codeacademy.current_user.AccTransaction;
import org.codeacademy.current_user.Account;

import java.util.ArrayList;

public class MenuPrint {

    public void printConnectMenu(){
        System.out.println("1. Prisijungti\n" +
                "2. Registruotis");
    }

    public void printInputUserName(){
        System.out.println("Įveskite vartotojo vardą");
    }
    public void printInputUserPass(){
        System.out.println("Iveskite slaptažodį");
    }
    public void printInputUserId(){
        System.out.println("Įveskite savo asmens kodą");
    }
    public void printInputName(){
        System.out.println("Įveskite savo vardą");
    }
    public void printInputSurname(){
        System.out.println("Įveskite savo pavardę");
    }


    public void printActionMenu(){
        System.out.println("\n" +
                "1. Peržiūrėti savo sąsakaitas ir balansus\n" +
                "2. Peržiūrėti tranzakcijų istoriją\n" +
                "3. Įnešti pinigų\n" +
                "4. Išsiimti pinigus\n" +
                "5. Padaryti pavedimą\n" +
                "6. Išeksportuoti transakcijų istoriją už norimą datą\n" +
                "7. Sukurti sąskaitą\n" +
                "0. Išeiti");
    }

    public void printAccCreationMenu(){
        System.out.println("1. Sukurti debetinę sąskaitą\n" +
                "2. Sukurti kretinę sąskaitą");
    }

    public void printTransactionAmount(){
        System.out.println("Įveskite pervedamą sumą");
    }
    public void printWhereTransactionTo(){
        System.out.println("Įveskite sąskaitos numerį į kurią norit įnešti pinigus");
    }

    public void printWhereTransactionFrom(){
        System.out.println("Įveskite sąskaitos numerį iš kurios norit išimti pinigus");
    }

    public void printAccountSelection(){
        System.out.println("Įveskite sąskaitos numerį, kurios pavedimus norit matyti");
    }

    public void printTransactionDateChoiceFrom(){
        System.out.println("Nuo kada atliktos transakcijos?\n" +
                "(formatas yyyy-mm-dd)");
    }

    public void printTransactionDateChoiceTill(){
        System.out.println("Iki kada atliktos transakcijos?\n" +
                "(formatas yyyy-mm-dd)");
    }

    public void notValidSignIn() {
        System.out.println("Prisijungimas nepavyko");
    }

    public void exitinfo(String userName) {
        System.out.println("Ačiū, kad apsilankėt "+userName);
    }

    public void printAccInfo(ArrayList<Account> accountslist) {
        System.out.println("----------------------------------------------------------------------------");
        for (Account a:accountslist) {
            System.out.println("Sąskaitos nr.: "+ a.getAccNo()+
                    " Sąskaitos tipas: "+ a.getAccType()+
                    " Balansas: "+ a.getBalance()
            );

        }
        System.out.println("----------------------------------------------------------------------------");
    }

    public void printAccTransactions(ArrayList<AccTransaction> transactions) {
        System.out.println("----------------------------------------------------------------------------");
        for (AccTransaction a:transactions) {
            System.out.println("Suma: " + a.getAmount() +
                    " Paskirtis: " + a.getDestination() +
                    " Data: " + a.getDate() +
                    " Laikas: " + a.getTime()
            );
        }
        System.out.println("----------------------------------------------------------------------------");
    }
}
