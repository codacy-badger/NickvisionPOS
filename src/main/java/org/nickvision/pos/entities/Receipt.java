package org.nickvision.pos.entities;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.File;

public class Receipt
{
    private Customer customer;
    private ArrayList<Item> items;
    private ArrayList<String> receipt;
    private DecimalFormat money;


    public Receipt(Customer customer)
    {
        this.customer = customer;
        items = new ArrayList<>();
        money = new DecimalFormat("#.##");
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        Item itemToRemove = new Item();
        for(Item i : items)
        {
            if(i.getItemCode() == item.getItemCode()) itemToRemove = i;
        }
        items.remove(itemToRemove);
    }

    public void clearReceipt()
    {
        items.clear();
    }

    public double getTotal()
    {
        double total = 0.00;
        for(Item i : items)
        {
            total += i.getPrice();
        }
        return total;
    }

    public ArrayList<String> getReceipt()
    {
        receipt = new ArrayList<>();
        receipt.add("Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n");
        for(Item i : items)
        {
            receipt.add(i.getName() + ": $" + money.format(i.getPrice()));
        }
        receipt.add("\nTotal: $" + money.format(getTotal()));
        return receipt;
    }

    public double checkout(double amountGiven)
    {
        double change = amountGiven - getTotal();
        change = Double.parseDouble(money.format(change));
        receipt.add("Amount Given: " + amountGiven);
        receipt.add("Change: " + change);
        return change;
    }

    public void saveReceipt() throws IOException
    {
        receipt.add("\nCustomer Info:");
        receipt.add("Name: " + customer.getFirstName() + " " + customer.getLastName());
        receipt.add("Phone Number: " + customer.getPhoneNumber());
        receipt.add("Email: " + customer.getEmail());
        int fileNumber = new File("POS/Receipts").list().length + 1;
        String path = "POS/Receipts/" + fileNumber + ".txt";
        FileWriter writer = new FileWriter(path);
        for(String s : receipt)
        {
            writer.write(s);
        }
        writer.close();
    }
}
