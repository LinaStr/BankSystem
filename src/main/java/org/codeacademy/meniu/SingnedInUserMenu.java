package org.codeacademy.meniu;

import org.codeacademy.CreateTransactionsFile;
import org.codeacademy.current_user.AccTransaction;
import org.codeacademy.current_user.Account;
import org.codeacademy.current_user.User;
import org.codeacademy.exceptions.AccDoesNotBelongToUserException;
import org.codeacademy.exceptions.AccNotExistingException;
import org.codeacademy.exceptions.FileSaveException;
import org.codeacademy.exceptions.InvalidChoiceException;

import java.sql.Date;
import java.util.ArrayList;

public class SingnedInUserMenu extends AbstractMenu {

    public void singnedInUserMenu(User signedInUser) {
        try {
            do {
                print.printActionMenu();
                int choice = input.getValidChoice(signedInMenuChoices);
                switch (choice) {
                    case 1:
                        viewAccAndBalances(signedInUser);
                        break;
                    case 2:
                        viewTransactions(signedInUser);
                        break;
                    case 3:
                        withdrawOrDepositMoney(signedInUser, choice);
                        break;
                    case 4:
                        withdrawOrDepositMoney(signedInUser, choice);
                        break;
                    case 5:
                        doTransaction(signedInUser);
                        break;
                    case 6:
                        transactionsToTxt(signedInUser);
                        break;
                    case 7:
                        createAcc(signedInUser);
                        break;
                    case 0:
                        exitProgram(signedInUser);
                        break;

                }

            } while (true);
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        }

    }


    private void exitProgram(User signedInUser) {
        print.exitinfo(signedInUser.getUserName());
        System.exit(0);
    }

    private void createAcc(User signedInUser) {
        try {
            print.printAccCreationMenu();
            int acctype = input.getValidChoice(accCreationChoices);
            dbManager.createAccInDb(signedInUser, acctype);
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        }

    }

    private void transactionsToTxt(User signedInUser) {
        try {
            print.printAccountSelection();
            viewAccAndBalances(signedInUser);
            String accountNo = input.readString();
            if (!accountDoesBelongToUser(accountNo, signedInUser)) {
                throw new AccDoesNotBelongToUserException();
            }

            print.printTransactionDateChoiceFrom();
            Date dateFrom = input.getValidDate();
            print.printTransactionDateChoiceTill();
            Date dateTill = input.getValidDate();

            ArrayList<AccTransaction> transactionsBetweenDates = dbManager.getTransactionsBetweenDates(accountNo, dateFrom, dateTill);
            CreateTransactionsFile createTransactionsFile = new CreateTransactionsFile();
            createTransactionsFile.transactionsToFile(transactionsBetweenDates);

        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        } catch (AccDoesNotBelongToUserException e) {
            System.out.println(e.getMessage());
        } catch (FileSaveException e) {
            System.out.println(e.getMessage());
        }

    }


    private void withdrawOrDepositMoney(User signedInUser, int choice) {
        try {
            String purpose;
            print.printTransactionAmount();
            double amount = input.readDouble();
            print.printWhereTransactionFrom();
            viewAccAndBalances(signedInUser);
            String accountNo = input.readString();
            if (!accountDoesBelongToUser(accountNo, signedInUser)) {
                throw new AccDoesNotBelongToUserException();
            }
            if (choice == 3) {
                purpose = "Įdėjimas";
            } else if (choice == 4) {
                purpose = "Išėmimas";
                amount = 0 - amount;
            } else {
                purpose = "Nežinoma";
            }
            dbManager.dbWithdrawOrDepositMoney(amount, accountNo, purpose);

        } catch (AccDoesNotBelongToUserException e) {
            System.out.println(e.getMessage());
        }

    }

    private void doTransaction(User signedInUser) {
        try {
            String purposeFrom = "Pavedimas iš ";
            String purposeTo = "Pavedimas į ";
            print.printTransactionAmount();
            double amount = input.readDouble();
            print.printWhereTransactionFrom();
            viewAccAndBalances(signedInUser);
            String accountFrom = input.readString();
            if (!accountDoesBelongToUser(accountFrom, signedInUser)) {
                throw new AccDoesNotBelongToUserException();
            }

            print.printWhereTransactionTo();
            String accountTo = input.readString();
            if (!accountIsExisting(accountTo)) {
                throw new AccNotExistingException();
            }
            dbManager.dbWithdrawOrDepositMoney(-amount, accountFrom, purposeTo + accountTo);
            dbManager.dbWithdrawOrDepositMoney(amount, accountTo, purposeFrom + accountFrom);

        } catch (AccNotExistingException e) {
            System.out.println(e.getMessage());
        } catch (AccDoesNotBelongToUserException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean accountIsExisting(String accountTo) throws AccNotExistingException {
        if (dbManager.accountValidation(accountTo)) {
            return true;

        }
        throw new AccNotExistingException();
    }

    private boolean accountDoesBelongToUser(String accountFrom, User signedInUser) throws
            AccDoesNotBelongToUserException {
        if (dbManager.userAccountValidation(accountFrom, signedInUser.getPersonalId())) {
            return true;

        }
        throw new AccDoesNotBelongToUserException();
    }


    private void viewAccAndBalances(User signedInUser) {
        ArrayList<Account> accountslist = dbManager.getDbAccAndBalances(signedInUser);
        print.printAccInfo(accountslist);

    }

    private void viewTransactions(User signedInUser) {
        print.printAccountSelection();
        viewAccAndBalances(signedInUser);
        String accountNo = input.readString();
        ArrayList<AccTransaction> transactions = dbManager.getAllTransactions(accountNo);
        print.printAccTransactions(transactions);
    }
}
