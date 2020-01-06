package org.nickvision.pos.entities;

public class User
{
    private String firstName, lastName;
    private int loginID;
    private UserType userType;

    public User(String firstName, String lastName, int loginID, UserType userType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginID = loginID;
        this.userType = userType;
    }

    public User()
    {
        this("", "", 0, UserType.Sales);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getLoginID()
    {
        return loginID;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setLoginID(int loginID)
    {
        this.loginID = loginID;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }
}
