package org.nickvision.pos.views;

import java.util.Scanner;
import org.nickvision.pos.Utils;
import org.nickvision.pos.entities.User;

public class AdminView
{
    private Scanner sc;

    public AdminView()
    {
        sc = new Scanner(System.in);
    }

    public void show(User user)
    {
        boolean adminLogout = false;
        while(!adminLogout)
        {
            int choice = 0;
            Utils.clear();
            System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName());
            System.out.println("===========================");
            System.out.println("\n1. Sales Screen");
            System.out.println("\n2. New User");
            System.out.println("3. Edit User");
            System.out.println("4. View User");
            System.out.println("5. Delete User");
            System.out.println("6. List All Users");
            System.out.println("\n7. New Item");
            System.out.println("8. Edit Item");
            System.out.println("9. View Item");
            System.out.println("10. Delete Item");
            System.out.println("11. List All Items");
            System.out.println("\n12. New Customer");
            System.out.println("13. Edit Customer");
            System.out.println("14. View Customer");
            System.out.println("15. Delete Customer");
            System.out.println("16. List All Customers");
            System.out.println("\n17. Logout");
            System.out.println("18. Exit POS");
            System.out.print("Number of Choice: ");
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch(Exception e)
            {
                System.out.println("Invalid choice. Please make sure it is a number.");
                Utils.sleep(800);
                continue;
            }
            switch(choice)
            {
                case 1:
                    new SalesView().show(user);
                    adminLogout = true;
                    break;
                case 2:
                    new UserView().newUser(false);
                    break;
                case 3:
                    new UserView().editUser();
                    break;
                case 4:
                    new UserView().viewUser();
                    break;
                case 5:
                    new UserView().deleteUser();
                    break;
                case 6:
                    new UserView().listUsers();
                    break;
                case 7:
                    new ItemView().newItem();
                    break;
                case 8:
                    new ItemView().editItem();
                    break;
                case 9:
                    new ItemView().viewItem();
                    break;
                case 10:
                    new ItemView().deleteItem();
                    break;
                case 11:
                    new ItemView().listItems();
                    break;
                case 12:
                    new CustomerView().newCustomer();
                    break;
                case 13:
                    new CustomerView().editCustomer();
                    break;
                case 14:
                    new CustomerView().viewCustomer();
                    break;
                case 15:
                    new CustomerView().deleteCustomer();
                    break;
                case 16:
                    new CustomerView().listCustomers();
                    break;
                case 17:
                    adminLogout = true;
                    break;
                case 18:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please make sure it is between 1-18.");
                    Utils.sleep(800);
                    break;
            }
        }
    }
}
