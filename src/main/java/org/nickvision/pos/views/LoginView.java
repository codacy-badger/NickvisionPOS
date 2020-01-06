package org.nickvision.pos.views;

import org.nickvision.pos.Utils;
import org.nickvision.pos.database.UserDatabase;
import org.nickvision.pos.entities.User;
import org.nickvision.pos.entities.UserType;
import java.util.Scanner;
import java.io.IOException;

public class LoginView
{
    private boolean adminExists()
    {
        int adminUsers = 0;
        for(User user : UserDatabase.getAllUsers())
        {
            if(user.getUserType() == UserType.Admin) adminUsers++;
        }
        return adminUsers != 0;
    }

    public void show()
    {
        while(true)
        {
            User loginUser = new User();
            Scanner sc = new Scanner(System.in);
            int loginID = 0;
            boolean validLogin = false;
            if(!adminExists()) new UserView().newUser(true);
            Utils.clear();
            System.out.println("Nickvision POS Login");
            System.out.println("=======================");
            System.out.print("\nLogin ID: ");
            try
            {
                loginID = Integer.parseInt(sc.nextLine());
            }
            catch (Exception e)
            {
                System.out.println("Invalid Login ID. Please make sure it is a number.");
                Utils.sleep(800);
                continue;
            }
            for(User user : UserDatabase.getAllUsers())
            {
                if(loginID == user.getLoginID())
                {
                    validLogin = true;
                    loginUser = user;
                }
            }
            if(validLogin)
            {
                if(loginUser.getUserType() == UserType.Admin) new AdminView().show(loginUser);
                else new SalesView().show(loginUser);
            }
            else
            {
                System.out.println("Invalid Login ID. Please try again.");
                Utils.sleep(800);
            }
        }
    }
}
