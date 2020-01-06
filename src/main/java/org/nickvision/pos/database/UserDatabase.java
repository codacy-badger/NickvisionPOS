package org.nickvision.pos.database;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.nickvision.pos.entities.User;
import org.nickvision.pos.entities.UserType;

public class UserDatabase
{
    private static String path = "POS/Users/";

    public static void addUser(User user)
    {
        try(FileOutputStream output = new FileOutputStream(path + user.getLoginID() + ".properties"))
        {
            Properties properties = new Properties();
            properties.setProperty("firstName", user.getFirstName());
            properties.setProperty("lastName", user.getLastName());
            properties.setProperty("loginID", String.valueOf(user.getLoginID()));
            properties.setProperty("userType", user.getUserType().toString());
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void updateUser(User user)
    {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream(path + user.getLoginID() + ".properties"))
        {
            properties.load(input);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
        try(FileOutputStream output = new FileOutputStream(path + user.getLoginID() + ".properties"))
        {
            properties.setProperty("firstName", user.getFirstName());
            properties.setProperty("lastName", user.getLastName());
            properties.setProperty("loginID", String.valueOf(user.getLoginID()));
            properties.setProperty("userType", user.getUserType().toString());
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void deleteUser(User user)
    {
        File file = new File(path + user.getLoginID() + ".properties");
        file.delete();
    }

    public static ArrayList<User> getAllUsers()
    {
        ArrayList<User> allUsers = new ArrayList<>();
        Stream<Path> allFiles = null;
        try
        {
            allFiles = Files.walk(Paths.get(path));
        }
        catch (IOException ignored)
        {

        }
        assert allFiles != null;
        allFiles.forEach(file -> {
            try(FileInputStream input = new FileInputStream(file.toAbsolutePath().toString()))
            {
                Properties properties = new Properties();
                properties.load(input);
                User user = new User();
                user.setFirstName(properties.getProperty("firstName"));
                user.setLastName(properties.getProperty("lastName"));
                user.setLoginID(Integer.parseInt(properties.getProperty("loginID")));
                user.setUserType(UserType.valueOf(properties.getProperty("userType")));
                allUsers.add(user);
            }
            catch (IOException ignored)
            {

            }
        });
        return allUsers;
    }

    public static User getUser(int loginID) throws Exception
    {
        for(User user : getAllUsers())
        {
            if(user.getLoginID() == loginID) return user;
        }
        throw new Exception("No user with that Login ID was found.");
    }
}
