package org.nickvision.pos.views;

import org.nickvision.pos.Utils;
import org.nickvision.pos.database.ItemDatabase;
import org.nickvision.pos.entities.Item;
import org.nickvision.pos.entities.Pair;
import java.io.IOException;
import java.util.Scanner;

public class ItemView
{
    private Scanner sc;

    public ItemView()
    {
        sc = new Scanner(System.in);
    }

    public Pair getItem()
    {
        int itemCode = 0;
        boolean validInput = false;
        while(!validInput)
        {
            System.out.print("\nItem Code: ");
            try
            {
                itemCode = Integer.parseInt(sc.nextLine());
                validInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Item Code. Make sure it is a number.");
            }
        }
        try
        {
            return new Pair(true, ItemDatabase.getItem(itemCode));
        }
        catch (Exception e)
        {
            return new Pair(false, (Item) null);
        }
    }

    public void newItem()
    {
        Item newItem = new Item();
        Utils.clear();
        System.out.println("New Item");
        System.out.println("===========");
        System.out.print("\nName: ");
        newItem.setName(sc.nextLine());
        System.out.print("Description: ");
        newItem.setDescription(sc.nextLine());
        boolean validItemCode = false;
        while (!validItemCode)
        {
            System.out.print("Item Code: ");
            try
            {
                newItem.setItemCode(Integer.parseInt(sc.nextLine()));
            }
            catch (Exception e)
            {
                System.out.println("Invalid Item Code. Please make sure it is a number.");
                continue;
            }
            if(ItemDatabase.getAllItems().size() == 0)
            {
                validItemCode = true;
            }
            else
            {
                for (Item item : ItemDatabase.getAllItems())
                {
                    if (item.getItemCode() == newItem.getItemCode())
                    {
                        System.out.println("Invalid Item Code. Item already exists.");
                        validItemCode = false;
                    }
                    else
                    {
                        validItemCode = true;
                    }
                }
            }
        }
        boolean validPrice = false;
        while(!validPrice)
        {
            System.out.print("Price: $");
            try
            {
                newItem.setPrice(Double.parseDouble(sc.nextLine()));
                validPrice = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Price. Make sure it is a number.");
            }

        }
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) ItemDatabase.addItem(newItem);
        else newItem();
    }

    public void editItem()
    {
        boolean validItem;
        Item editItem;
        Utils.clear();
        System.out.println("Edit Item");
        System.out.println("============");
        Pair pair = getItem();
        validItem = pair.getIsValid();
        editItem = pair.getItem();
        if(!validItem)
        {
            System.out.println("Invalid Item. Please check the Item Code.");
            Utils.sleep(800);
            return;
        }
        System.out.println("\nCurrent Item Info:");
        System.out.println("Name: " + editItem.getName());
        System.out.println("Description: " + editItem.getDescription());
        System.out.println("Item Code: " + editItem.getItemCode());
        System.out.println("Price: $" + editItem.getPrice());
        System.out.println("\nNew Item Info:");
        System.out.print("Name: ");
        editItem.setName(sc.nextLine());
        System.out.print("Description: ");
        editItem.setDescription(sc.nextLine());
        System.out.println("Item Code: " + editItem.getItemCode());
        boolean validPrice = false;
        while(!validPrice)
        {
            System.out.print("Price: $");
            try
            {
                editItem.setPrice(Double.parseDouble(sc.nextLine()));
                validPrice = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Price. Make sure it is a number.");
            }

        }
        System.out.print("Everything Correct (Y / N)? ");
        String isCorrect = sc.nextLine().toLowerCase();
        if(isCorrect.equals("y") || isCorrect.equals("yes")) ItemDatabase.updateItem(editItem);
        else editItem();
    }

    public void viewItem()
    {
        boolean validItem;
        Item viewItem;
        Utils.clear();
        System.out.println("View Item");
        System.out.println("============");
        Pair pair = getItem();
        validItem = pair.getIsValid();
        viewItem = pair.getItem();
        if(!validItem)
        {
            System.out.println("Invalid Item. Please check the Item Code.");
            Utils.sleep(800);
            return;
        }
        System.out.println("\nItem Info:");
        System.out.println("Name: " + viewItem.getName());
        System.out.println("Description: " + viewItem.getDescription());
        System.out.println("Item Code: " + viewItem.getItemCode());
        System.out.println("Price: $" + viewItem.getPrice());
        Utils.enterToContinue();
    }

    public void deleteItem()
    {
        boolean validItem;
        Item deleteItem;
        Utils.clear();
        System.out.println("Delete Item");
        System.out.println("==============");
        Pair pair = getItem();
        validItem = pair.getIsValid();
        deleteItem = pair.getItem();
        if(!validItem)
        {
            System.out.println("Invalid Item. Please check the Item Code.");
            Utils.sleep(800);
            return;
        }
        System.out.println("\nItem: " + deleteItem.getName() + " | Item Code: " + deleteItem.getItemCode());
        System.out.println("Are you sure you want to delete this item (Y / N)? ");
        String canDelete = sc.nextLine().toLowerCase();
        if(canDelete.equals("y") || canDelete.equals("yes"))
        {
            ItemDatabase.deleteItem(deleteItem);
            System.out.println("\nItem deleted.");
            Utils.enterToContinue();
        }
    }

    public void listItems()
    {
        Utils.clear();
        System.out.println("List of All Items");
        System.out.println("====================");
        for(Item item : ItemDatabase.getAllItems())
        {
            System.out.println("\nName: " + item.getName() + " | Description: " + item.getDescription() + " | Item Code: " + item.getItemCode() + " | Price: $" + item.getPrice());
        }
        Utils.enterToContinue();
    }
}