package org.codeacademy.meniu;

import org.codeacademy.dataBase.DbManager;
import org.codeacademy.UserInputReader;

abstract public class AbstractMenu {
    protected static MenuPrint print = new MenuPrint();
    protected UserInputReader input = new UserInputReader();
    protected DbManager dbManager = new DbManager();

    protected int mainMenuChoices = 2;
    protected int signedInMenuChoices = 7;
    protected int accCreationChoices = 2;

}
