package org.nickvision.pos.views;

import org.nickvision.pos.ConsoleUtils;
import org.nickvision.pos.database.CustomerDatabase;
import org.nickvision.pos.entities.Customer;
import org.nickvision.pos.entities.Pair;
import java.util.Scanner;

public class CustomerView
{
    private Scanner sc;

    public CustomerView()
    {
        sc = new Scanner(System.in);
    }

    public Pair getCustomer()
    {
        long phoneNumber = 0;
        boolean validInput = false;
        while(!validInput)
        {
            System.out.print("\nPhone Number: ");
            try
            {
                phoneNumber = Long.parseLong(sc.nextLine());
                validInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Phone Number. Make sure it is a number.");
            }
        }
        try
        {
            return new Pair(true, CustomerDatabase.getCustomer(phoneNumber));
        }
        catch (Exception e)
        {
            return new Pair(false, (Customer) null);
        }
    }

    public void newCustomer()
    {
        Customer newCustomer = new Customer();
        ConsoleUtils.clear();
        System.out.println("New Customer");
        System.out.println("===============");
        System.out.print("\nFirst Name: ");
        newCustomer.setFirstName(sc.nextLine());
        System.out.print("Last Name: ");
        newCustomer.setLastName(sc.nextLine());
        boolean validPhoneNumber = false;
        while (!validPhoneNumber)
        {
            System.out.print("Phone Number: ");
            try
            {
                newCustomer.setPhoneNumber(Long.parseLong(sc.nextLine()));
            }
            catch (Exception e)
            {
                System.out.println("Invalid Phone Number. Please make sure it is a number.");
                continue;
            }
            if(CustomerDatabase.getAllCustomers().size() == 0)
            {
                validPhoneNumber = true;
            }
            else
            {
                for (Customer customer : CustomerDatabase.getAllCustomers())
                {
                    if (customer.getPhoneNumber() == newCustomer.getPhoneNumber())
                    {
                        System.out.println("Invalid Phone Number. Customer already exists.");
                        validPhoneNumber = false;
                    }
                    else
                    {
                        validPhoneNumber = true;
                    }
                }
            }
        }
        System.out.print("Email: ");
        newCustomer.setEmail(sc.nextLine());
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) CustomerDatabase.addCustomer(newCustomer);
        else newCustomer();
    }

    public void editCustomer()
    {
        boolean validCustomer;
        Customer editCustomer;
        ConsoleUtils.clear();
        System.out.println("Edit Customer");
        System.out.println("================");
        Pair pair = getCustomer();
        validCustomer = pair.getIsValid();
        editCustomer = pair.getCustomer();
        if(!validCustomer)
        {
            System.out.println("Invalid Customer. Please check the Phone Number.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nCurrent Customer Info:");
        System.out.println("First Name: " + editCustomer.getFirstName());
        System.out.println("Last Name: " + editCustomer.getLastName());
        System.out.println("Phone Number: " + editCustomer.getPhoneNumber());
        System.out.println("Email: " + editCustomer.getEmail());
        System.out.println("\nNew Customer Info:");
        System.out.print("First Name: ");
        editCustomer.setFirstName(sc.nextLine());
        System.out.print("Last Name: ");
        editCustomer.setLastName(sc.nextLine());
        System.out.println("Phone Number: " + editCustomer.getPhoneNumber());
        System.out.print("Email: ");
        editCustomer.setEmail(sc.nextLine());
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) CustomerDatabase.updateCustomer(editCustomer);
        else editCustomer();
    }

    public void viewCustomer()
    {
        boolean validCustomer;
        Customer viewCustomer;
        ConsoleUtils.clear();
        System.out.println("View Customer");
        System.out.println("================");
        Pair pair = getCustomer();
        validCustomer = pair.getIsValid();
        viewCustomer = pair.getCustomer();
        if(!validCustomer)
        {
            System.out.println("Invalid Customer. Please check the Phone Number.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nCustomer Info:");
        System.out.println("First Name: " + viewCustomer.getFirstName());
        System.out.println("Last Name: " + viewCustomer.getLastName());
        System.out.println("Phone Number: " + viewCustomer.getPhoneNumber());
        System.out.println("Email: " + viewCustomer.getEmail());
        ConsoleUtils.enterToContinue();
    }

    public void deleteCustomer()
    {
        boolean validCustomer;
        Customer deleteCustomer;
        ConsoleUtils.clear();
        System.out.println("Delete Customer");
        System.out.println("==================");
        Pair pair = getCustomer();
        validCustomer = pair.getIsValid();
        deleteCustomer = pair.getCustomer();
        if(!validCustomer)
        {
            System.out.println("Invalid Customer. Please check the Phone Number.");
            ConsoleUtils.sleep(800);
            return;
        }
        System.out.println("\nCustomer: " + deleteCustomer.getFirstName() + " " + deleteCustomer.getLastName() + " | Phone Number: " + deleteCustomer.getPhoneNumber());
        System.out.println("Are you sure you want to delete this customer (Y / N)? ");
        String canDelete = sc.nextLine().toLowerCase();
        if(canDelete.equals("y") || canDelete.equals("yes"))
        {
            CustomerDatabase.deleteCustomer(deleteCustomer);
            System.out.println("\nCustomer deleted.");
            ConsoleUtils.enterToContinue();
        }
    }

    public void listCustomers()
    {
        ConsoleUtils.clear();
        System.out.println("List of All Customers");
        System.out.println("========================");
        for(Customer customer : CustomerDatabase.getAllCustomers())
        {
            System.out.println("\nName: " + customer.getFirstName() + " " + customer.getLastName() + " | Phone Number: " + customer.getPhoneNumber() + " | Email: " + customer.getEmail());
        }
        ConsoleUtils.enterToContinue();
    }
}