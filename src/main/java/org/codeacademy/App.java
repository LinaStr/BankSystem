package org.codeacademy;

import org.codeacademy.exceptions.InvalidChoiceException;
import org.codeacademy.meniu.MainMenu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println("Sveiki atvykę į bakinę platformą");
        System.out.println("Sistema pasiruošusi darbui");
        MainMenu menu = new MainMenu();
        do{
            menu.mainMenu();
        }while (true);






}}
