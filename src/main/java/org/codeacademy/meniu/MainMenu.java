package org.codeacademy.meniu;

//import org.codeacademy.DataBase.DbManager;
import org.codeacademy.exceptions.InvalidChoiceException;

public class MainMenu extends AbstractMenu {

    private SignIn signin = new SignIn();
    private Registration registration = new Registration();



    public void mainMenu() {
        print.printConnectMenu();
        try {
            int choice = input.getValidChoice(mainMenuChoices);
            switch (choice) {
                case 1:
                    signin.signIn();
                    break;
                case 2:
                    registration.registration();
                    break;

            }
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        }

    }







}
