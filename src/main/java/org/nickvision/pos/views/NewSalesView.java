package org.nickvision.pos.views;

import java.io.IOException;
import java.util.Scanner;
import org.nickvision.pos.ConsoleUtils;
import org.nickvision.pos.entities.Pair;
import org.nickvision.pos.entities.Receipt;
import org.nickvision.pos.entities.Customer;
import org.nickvision.pos.entities.Item;

public class NewSalesView
{
    private Scanner sc;
    private Receipt receipt;

    public NewSalesView()
    {
        sc = new Scanner(System.in);
    }

    public void startSale()
    {
        boolean validCustomer;
        Customer customer;
        Pair pair = new CustomerView().getCustomer();
        validCustomer = pair.getIsValid();
        customer = pair.getCustomer();
        if(!validCustomer)
        {
            System.out.println("Invalid Customer. Please check the Phone Number.");
            ConsoleUtils.sleep(800);
        }
        else
        {
            receipt = new Receipt(customer);
            sale(customer);
        }
    }

    private void sale(Customer customer)
    {
        boolean finishedSale = false;
        while(!finishedSale)
        {
            int choice = 0;
            ConsoleUtils.clear();
            System.out.println("New Sale");
            System.out.println("===========");
            System.out.println("[1] Cancel Sale | [2] Add Item | [3] Delete Item | [4] Remove All Items | [5] Checkout\n");
            for(String s : receipt.getReceipt())
            {
                System.out.println(s);
            }
            System.out.print("\nCOMMAND> ");
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch (Exception e)
            {
                continue;
            }
            switch(choice)
            {
                case 1:
                    finishedSale = true;
                    break;
                case 2:
                    boolean validItem;
                    Item item;
                    Pair pair = new ItemView().getItem();
                    validItem = pair.getIsValid();
                    item = pair.getItem();
                    if(!validItem)
                    {
                        System.out.println("Invalid Item. Please check the Item Code.");
                        ConsoleUtils.sleep(800);
                        break;
                    }
                    receipt.addItem(item);
                    break;
                case 3:
                    boolean validItem2;
                    Item item2;
                    Pair pair2 = new ItemView().getItem();
                    validItem2 = pair2.getIsValid();
                    item2 = pair2.getItem();
                    if(!validItem2)
                    {
                        System.out.println("Invalid Item. Please check the Item Code.");
                        ConsoleUtils.sleep(800);
                        break;
                    }
                    receipt.removeItem(item2);
                    break;
                case 4:
                    receipt.clearReceipt();
                    break;
                case 5:
                    double amountGiven = 0;
                    System.out.print("Amount Given: $");
                    try
                    {
                        amountGiven = Double.parseDouble(sc.nextLine());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid Amount. Please make sure it is a number.");
                        ConsoleUtils.sleep(800);
                        break;
                    }
                    if(amountGiven < receipt.getTotal())
                    {
                        System.out.println("Invalid Amount. Please make sure it is greater than or equal to the total.");
                        ConsoleUtils.sleep(800);
                        break;
                    }
                    double change = receipt.checkout(amountGiven);
                    System.out.println("Change To Give: $" + change);
                    ConsoleUtils.enterToContinue();
                    try
                    {
                        receipt.saveReceipt();
                    }
                    catch (IOException ie)
                    {
                        ie.printStackTrace();
                    }
                    finishedSale = true;
                    break;
                default:
                    break;
            }
        }

    }
}
