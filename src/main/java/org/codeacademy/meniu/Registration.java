package org.codeacademy.meniu;

import org.codeacademy.current_user.User;
import org.codeacademy.exceptions.InvalidChoiceException;

public class Registration extends AbstractMenu{
    private SingnedInUserMenu userMenu = new SingnedInUserMenu();

    public void registration()  {
        print.printInputUserId();
        String userId = input.readString();
        print.printInputUserName();
        String userName = input.readString();
        print.printInputUserPass();
        String userPass = input.readString();
        print.printInputName();
        String name = input.readString();
        print.printInputSurname();
        String surname = input.readString();
        dbManager.register(userId, userName, userPass, name, surname);
        User signedInUser = new User(userId, userName, userPass, name, surname);
        userMenu.singnedInUserMenu(signedInUser);
    }
}
