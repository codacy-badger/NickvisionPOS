package org.nickvision.pos.entities;

public class Pair
{
    private boolean isValid;
    private User user;
    private Item item;
    private Customer customer;

    public Pair(boolean isValid, User user)
    {
        this.isValid = isValid;
        this.user = user;
    }

    public Pair(boolean isValid, Item item)
    {
        this.isValid = isValid;
        this.item = item;
    }

    public Pair(boolean isValid, Customer customer)
    {
        this.isValid = isValid;
        this.customer = customer;
    }

    public boolean getIsValid()
    {
        return isValid;
    }

    public User getUser()
    {
        return user;
    }

    public Item getItem()
    {
        return item;
    }

    public Customer getCustomer()
    {
        return customer;
    }
}
