package org.nickvision.pos.entities;

public class Item
{
    private String name;
    private String description;
    private int itemCode;
    private double price;

    public Item(String name, String description, int itemCode, double price)
    {
        this.name = name;
        this.description = description;
        this.itemCode = itemCode;
        this.price = price;
    }

    public Item()
    {
        this("", "", 0, 0.00);
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getItemCode()
    {
        return itemCode;
    }

    public double getPrice()
    {
        return price;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setItemCode(int itemCode)
    {
        this.itemCode = itemCode;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
