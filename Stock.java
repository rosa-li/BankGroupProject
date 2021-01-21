/**
 * Omar Khattab
 * 1/21/2021
 * This class is used to set up stocks and store them in an arraylist
 * and contains methods to add,remove and search for specific stocks or info
 */

import java.lang.Math;
import java.util.*;
import java.io.*;

public class Stock
{
    //String to store the symbol of a stock
    private String symbol;
    //String to store the name of a stock
    private String name;
    //float of the original value of a stock
    private float initialValue;
    //float of the current value of a stock
    private float currentValue;
    //static array list of available stocks
    private static ArrayList<Stock> stockList = new ArrayList<Stock>();

    //constructor that takes symbol, name and valuu and assigns them to their respective variable 
    public Stock(String symbol, String name, float value)
    {
        this.symbol = symbol;
        this.name = name;
        this.initialValue = value;
        this.currentValue = value;
    }

    //method that takes no parameters and returns a float value of the current price of a stock
    public float getCurrentValue()
    {
        //multiplies a stock's current value by a random amount between 1.02 and 0.98, using the result to set the new price of the stock
        currentValue *= Math.random() * (1.02 - 0.98) + 0.98;
        return currentValue;
    }

    //method that takes no parameters and returns a String value for the symbol of a specific stock
    public String getSymbol()
    {
        return symbol;
    }

    //toString method for Stock
    public String toString()
    {
        return symbol + ", " + name + ", " + getCurrentValue();
    }

    //static method to add stocks, takes a symbol, name and value and returns a stock object
    public static Stock add(String symbol, String name, float value)
    {
        //calls search method and assigns it symbol
        Stock stock = search(symbol);
        //decision structure that creates a new stock and adds it to the arrayList if stock is null
        if(stock == null)
        {
            stock = new Stock(symbol, name, value);
            stockList.add(stock);
        }
        //returns stock
        return stock;
    }

    //static method that returns the arraylist of stocks, takes no parameters 
    public static ArrayList getList()
    {
        return stockList;
    }

    //static search method that takes symbol and returns a stock object
    public static Stock search(String symbol)
    {
        //for loop that loops through the array of stocks
        for(Stock stock : stockList)
        {
            //checks to see if the string symbol in the paramater matches a string symbol of an existing stock
            if(stock.symbol.equalsIgnoreCase(symbol))
            {
                return stock;
            }
        }
        //returns null to indicate the stock doesnt exist
        return null;
    }

    //static write method to save the stocks
    public static void write()
    {
        //try catch to prevent the program from crashing in case of error
        try 
        {   
            //creates a FileWriter object and provides a file name
            FileWriter myWriter = new FileWriter("stocks.txt");
            //for loop that loops through the array of stock
            for(Stock stock : stockList)
            {
                //writes down the toString of the stock in the designated file above
                myWriter.write(stock.toString() + "\n");
            }
            //closes the write
            myWriter.close();
        } 
        catch (IOException e) 
        {
            //prints out an error message incase of error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //static read method to read the stocks that were written down
    public static void read()
    {
        //clears the current stockList
        stockList.clear();
        //try catch to prevent program from crashing
        try
        {
            //scanner to read the designated file
            Scanner sc = new Scanner(new File("stocks.txt"));
            //while loop that "tokenises" each line, using those values to create stocks 
            while (sc.hasNextLine()) 
            {
                //splits the line by a specified character combination
                String[] tokens = (sc.nextLine().split(", "));
                //calls the add method, providing tokens for their respective parameters 
                add(tokens[0], tokens[1], Float.parseFloat(tokens[2]));       
            }
            //closes scanner
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            //System.out.println("File stocks.txt not found");
        }
    }
}
