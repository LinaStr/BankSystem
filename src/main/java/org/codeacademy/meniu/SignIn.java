package org.codeacademy.meniu;

import org.codeacademy.current_user.User;
import org.codeacademy.exceptions.InvalidChoiceException;

public class SignIn extends AbstractMenu {
    private SingnedInUserMenu userMenu = new SingnedInUserMenu();

    public void signIn() {
        print.printInputUserName();
        String userName = input.readString();
        print.printInputUserPass();
        String userPass = input.readString();
        String userId = dbManager.signIn(userName, userPass);
        if (userId != null) {
            User signedInUser = new User(userId, userName);
            userMenu.singnedInUserMenu(signedInUser);
        } else {
            print.notValidSignIn();
        }
    }



}
