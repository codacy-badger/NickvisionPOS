package org.nickvision.pos.database;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.nickvision.pos.entities.Item;

public class ItemDatabase
{
    private static String path = "POS/Items/";

    public static void addItem(Item item)
    {
        try(FileOutputStream output = new FileOutputStream(path + item.getItemCode() + ".properties"))
        {
            Properties properties = new Properties();
            properties.setProperty("name", item.getName());
            properties.setProperty("description", item.getDescription());
            properties.setProperty("itemCode", String.valueOf(item.getItemCode()));
            properties.setProperty("price", String.valueOf(item.getPrice()));
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void updateItem(Item item)
    {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream(path + item.getItemCode() + ".properties"))
        {
            properties.load(input);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
        try(FileOutputStream output = new FileOutputStream(path + item.getItemCode() + ".properties"))
        {
            properties.setProperty("name", item.getName());
            properties.setProperty("description", item.getDescription());
            properties.setProperty("itemCode", String.valueOf(item.getItemCode()));
            properties.setProperty("price", String.valueOf(item.getPrice()));
            properties.store(output, null);
        }
        catch(IOException ie)
        {
            ie.printStackTrace();
        }
    }

    public static void deleteItem(Item item)
    {
        File file = new File(path + item.getItemCode() + ".properties");
        file.delete();
    }

    public static ArrayList<Item> getAllItems()
    {
        ArrayList<Item> allItems = new ArrayList<>();
        Stream<Path> allFiles = null;
        try
        {
            allFiles = Files.walk(Paths.get(path));
        }
        catch (IOException ignored)
        {

        }
        assert allFiles != null;
        allFiles.forEach(file -> {
            try(FileInputStream input = new FileInputStream(file.toAbsolutePath().toString()))
            {
                Properties properties = new Properties();
                properties.load(input);
                Item item = new Item();
                item.setName(properties.getProperty("name"));
                item.setDescription(properties.getProperty("description"));
                item.setItemCode(Integer.parseInt(properties.getProperty("itemCode")));
                item.setPrice(Double.parseDouble(properties.getProperty("price")));
                allItems.add(item);
            }
            catch (IOException ignored)
            {

            }
        });
        return allItems;
    }

    public static Item getItem(int itemCode) throws Exception
    {
        for(Item item : getAllItems())
        {
            if(item.getItemCode() == itemCode) return item;
        }
        throw new Exception("No item with that Item Code was found.");
    }
}
