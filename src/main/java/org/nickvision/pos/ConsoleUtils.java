package org.nickvision.pos;

public class ConsoleUtils
{
    public static void clear()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("\n");
        }
    }

    public static void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void enterToContinue()
    {
        System.out.println("\nPress Enter to continue....");
        try
        {
            System.in.read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
