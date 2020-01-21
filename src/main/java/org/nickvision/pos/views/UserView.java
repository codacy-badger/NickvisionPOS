package org.nickvision.pos.views;

import org.nickvision.pos.ConsoleUtils;
import org.nickvision.pos.database.UserDatabase;
import org.nickvision.pos.entities.User;
import org.nickvision.pos.entities.UserType;
import org.nickvision.pos.entities.Pair;
import java.util.Scanner;

public class UserView
{
    private Scanner sc;

    public UserView()
    {
        sc = new Scanner(System.in);
    }

    private Pair getUser()
    {
        int loginID = 0;
        boolean validInput = false;
        while(!validInput)
        {
            System.out.print("\nLogin ID: ");
            try
            {
                loginID = Integer.parseInt(sc.nextLine());
                validInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Login ID. Make sure it is a number.");
            }
        }
        try
        {
            return new Pair(true, UserDatabase.getUser(loginID));
        }
        catch (Exception e)
        {
            return new Pair(false, (User)null);
        }
    }

    public void newUser(boolean mustBeAdmin)
    {
        User newUser = new User();
        ConsoleUtils.clear();
        System.out.println("New User");
        System.out.println("===========");
        System.out.print("\nFirst Name: ");
        newUser.setFirstName(sc.nextLine());
        System.out.print("Last Name: ");
        newUser.setLastName(sc.nextLine());
        boolean validLoginID = false;
        while (!validLoginID)
        {
            System.out.print("Login ID: ");
            try
            {
                newUser.setLoginID(Integer.parseInt(sc.nextLine()));
            }
            catch (Exception e)
            {
                System.out.println("Invalid Login ID. Please make sure it is a number.");
                continue;
            }
            if(UserDatabase.getAllUsers().size() == 0)
            {
                validLoginID = true;
            }
            else
            {
                for (User user : UserDatabase.getAllUsers())
                {
                    if (user.getLoginID() == newUser.getLoginID())
                    {
                        System.out.println("Invalid Login ID. User already exists.");
                        validLoginID = false;
                    }
                    else
                    {
                        validLoginID = true;
                    }
                }
            }
        }
        if (mustBeAdmin)
        {
            System.out.println("User Type: Admin");
            newUser.setUserType(UserType.Admin);
        }
        else
        {
            boolean validUserType = false;
            while(!validUserType)
            {
                System.out.print("User Type (Sales / Admin): ");
                String type = sc.nextLine().toLowerCase();
                if(type.equals("sales"))
                {
                    newUser.setUserType(UserType.Sales);
                    validUserType = true;
                }
                else if(type.equals("admin"))
                {
                    newUser.setUserType(UserType.Admin);
                    validUserType = true;
                }
                else
                {
                    System.out.println("Invalid User Type. Must be sales or admin");
                }
            }
        }
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) UserDatabase.addUser(newUser);
        else newUser(mustBeAdmin);
    }

    public void editUser()
    {
        boolean validUser;
        User editUser;
        ConsoleUtils.clear();
        System.out.println("Edit User");
        System.out.println("============");
        Pair pair = getUser();
        validUser = pair.getIsValid();
        editUser = pair.getUser();
        if(!validUser)
        {
            System.out.println("Invalid User. Please check the Login ID.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nCurrent User Info:");
        System.out.println("First Name: " + editUser.getFirstName());
        System.out.println("Last Name: " + editUser.getLastName());
        System.out.println("Login ID: " + editUser.getLoginID());
        System.out.println("User Type: " + editUser.getUserType().name());
        System.out.println("\nNew User Info:");
        System.out.print("First Name: ");
        editUser.setFirstName(sc.nextLine());
        System.out.print("Last Name: ");
        editUser.setLastName(sc.nextLine());
        System.out.println("Login ID: " + editUser.getLoginID());
        boolean validUserType = false;
        while(!validUserType)
        {
            System.out.print("User Type (Sales / Admin): ");
            String type = sc.nextLine().toLowerCase();
            if(type.equals("sales"))
            {
                editUser.setUserType(UserType.Sales);
                validUserType = true;
            }
            else if(type.equals("admin"))
            {
                editUser.setUserType(UserType.Admin);
                validUserType = true;
            }
            else
            {
                System.out.println("Invalid User Type. Must be sales or admin");
            }
        }
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) UserDatabase.updateUser(editUser);
        else editUser();
    }

    public void viewUser()
    {
        boolean validUser;
        User viewUser;
        ConsoleUtils.clear();
        System.out.println("View User");
        System.out.println("============");
        Pair pair = getUser();
        validUser = pair.getIsValid();
        viewUser = pair.getUser();
        if(!validUser)
        {
            System.out.println("Invalid User. Please check the Login ID.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nUser Info:");
        System.out.println("First Name: " + viewUser.getFirstName());
        System.out.println("Last Name: " + viewUser.getLastName());
        System.out.println("Login ID: " + viewUser.getLoginID());
        System.out.println("User Type: " + viewUser.getUserType().name());
        ConsoleUtils.enterToContinue();
    }

    public void deleteUser()
    {
        boolean validUser;
        User deleteUser;
        ConsoleUtils.clear();
        System.out.println("Delete User");
        System.out.println("==============");
        Pair pair = getUser();
        validUser = pair.getIsValid();
        deleteUser = pair.getUser();
        if(!validUser)
        {
            System.out.println("Invalid User. Please check the Login ID.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nUser: " + deleteUser.getFirstName() + " " + deleteUser.getLastName() + " | Login ID: " + deleteUser.getLoginID());
        System.out.println("Are you sure you want to delete this user (Y / N)? ");
        String canDelete = sc.nextLine().toLowerCase();
        if(canDelete.equals("y") || canDelete.equals("yes"))
        {
            UserDatabase.deleteUser(deleteUser);
            System.out.println("\nUser deleted.");
            ConsoleUtils.enterToContinue();
        }
    }

    public void listUsers()
    {
        ConsoleUtils.clear();
        System.out.println("List of All Users");
        System.out.println("====================");
        for(User user : UserDatabase.getAllUsers())
        {
            System.out.println("\nName: " + user.getFirstName() + " " + user.getLastName() + " | Login ID: " + user.getLoginID() + " | User Type: " + user.getUserType().name());
        }
        ConsoleUtils.enterToContinue();
    }
}
