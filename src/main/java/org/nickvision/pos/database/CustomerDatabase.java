package org.nickvision.pos.database;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.nickvision.pos.entities.Customer;;

public class CustomerDatabase
{
    private static String path = "POS/Customers/";

    public static void addCustomer(Customer customer)
    {
        try(FileOutputStream output = new FileOutputStream(path + customer.getPhoneNumber() + ".properties"))
        {
            Properties properties = new Properties();
            properties.setProperty("firstName", customer.getFirstName());
            properties.setProperty("lastName", customer.getLastName());
            properties.setProperty("phoneNumber", String.valueOf(customer.getPhoneNumber()));
            properties.setProperty("email", customer.getEmail());
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void updateCustomer(Customer customer)
    {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream(path + customer.getPhoneNumber() + ".properties"))
        {
            properties.load(input);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
        try(FileOutputStream output = new FileOutputStream(path + customer.getPhoneNumber() + ".properties"))
        {
            properties.setProperty("firstName", customer.getFirstName());
            properties.setProperty("lastName", customer.getLastName());
            properties.setProperty("phoneNumber", String.valueOf(customer.getPhoneNumber()));
            properties.setProperty("email", customer.getEmail());
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void deleteCustomer(Customer customer)
    {
        File file = new File(path + customer.getPhoneNumber() + ".properties");
        file.delete();
    }

    public static ArrayList<Customer> getAllCustomers()
    {
        ArrayList<Customer> allCustomers = new ArrayList<>();
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
                Customer customer = new Customer();
                customer.setFirstName(properties.getProperty("firstName"));
                customer.setLastName(properties.getProperty("lastName"));
                customer.setPhoneNumber(Long.parseLong(properties.getProperty("phoneNumber")));
                customer.setEmail(properties.getProperty("email"));
                allCustomers.add(customer);
            }
            catch (IOException ignored)
            {

            }
        });
        return allCustomers;
    }

    public static Customer getCustomer(long phoneNumber) throws Exception
    {
        for(Customer customer : getAllCustomers())
        {
            if(customer.getPhoneNumber() == phoneNumber) return customer;
        }
        throw new Exception("No customer with that Phone Number was found.");
    }
}
