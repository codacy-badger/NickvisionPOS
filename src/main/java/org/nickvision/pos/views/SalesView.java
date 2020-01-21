package org.nickvision.pos.views;

import java.util.Scanner;
import org.nickvision.pos.ConsoleUtils;
import org.nickvision.pos.entities.User;

public class SalesView
{
    private Scanner sc;

    public SalesView()
    {
        sc = new Scanner(System.in);
    }

    public void show(User user)
    {
        boolean salesLogout = false;
        while(!salesLogout)
        {
            int choice = 0;
            ConsoleUtils.clear();
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName());
            System.out.println("===========================");
            System.out.println("\n1. New Sale");
            System.out.println("\n2. View Item");
            System.out.println("3. List All Items");
            System.out.println("\n4. New Customer");
            System.out.println("5. Edit Customer");
            System.out.println("6. View Customer");
            System.out.println("7. List All Customers");
            System.out.println("\n8. Logout");
            System.out.print("Number of Choice: ");
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch(Exception e)
            {
                System.out.println("Invalid choice. Please make sure it is a number.");
                ConsoleUtils.sleep(800);
                continue;
            }
            switch(choice)
            {
                case 1:
                    new NewSalesView().startSale();
                    salesLogout = true;
                    break;
                case 2:
                    new ItemView().viewItem();
                    break;
                case 3:
                    new ItemView().listItems();
                    break;
                case 4:
                    new CustomerView().newCustomer();
                    break;
                case 5:
                    new CustomerView().editCustomer();
                    break;
                case 6:
                    new CustomerView().viewCustomer();
                    break;
                case 7:
                    new CustomerView().listCustomers();
                    break;
                case 8:
                    salesLogout = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please make sure it is between 1-8.");
                    ConsoleUtils.sleep(800);
                    break;
            }
        }
    }
}