package org.nickvision.pos;

import java.io.*;
import org.nickvision.pos.views.LoginView;

public class POS
{
    public POS()
    {
        ConsoleUtils.clear();
    }

    public static void main(String[] args)
    {
        new POS().run();
    }

    private void run()
    {
        System.out.println("Nickvision Point of Sales");
        System.out.println("\nPlease Wait...\n");
        System.out.print("[");
        for(int i = 0; i < 12; i++)
        {
            System.out.print("=");
            ConsoleUtils.sleep(100);
        }
        new File("POS").mkdirs();
        new File("POS/Users").mkdirs();
        new File("POS/Items").mkdirs();
        new File("POS/Customers").mkdirs();
        new File("POS/Receipts").mkdirs();
        for(int i = 0; i < 12; i++)
        {
            System.out.print("=");
            ConsoleUtils.sleep(100);
        }
        System.out.print("]\n");
        new LoginView().show();
    }
}
