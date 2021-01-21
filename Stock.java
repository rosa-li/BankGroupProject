import java.lang.Math;
import java.util.*;
import java.io.*;

public class Stock
{
    private String symbol;
    private String name;
    private float initialValue;
    private float currentValue;
    private static ArrayList<Stock> stockList = new ArrayList<Stock>();

    public Stock(String symbol, String name, float value)
    {
        this.symbol = symbol;
        this.name = name;
        this.initialValue = value;
        this.currentValue = value;
    }

    public float getCurrentValue()
    {
        currentValue *= Math.random() * (1.02 - 0.98) + 0.98;
        return currentValue;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String toString()
    {
        return symbol + ", " + name + ", " + getCurrentValue();
    }

    public static Stock add(String symbol, String name, float value)
    {
        Stock stock = search(symbol);
        if(stock == null)
        {
            stock = new Stock(symbol, name, value);
            stockList.add(stock);
        }
        return stock;
    }

    public static ArrayList getList()
    {
        return stockList;
    }

    public static Stock search(String symbol)
    {
        for(Stock stock : stockList)
        {
            if(stock.symbol.equalsIgnoreCase(symbol))
            {
                return stock;
            }
        }
        return null;
    }

    public static void write()
    {
        try 
        {   
            FileWriter myWriter = new FileWriter("stocks.txt");
            for(Stock stock : stockList)
            {
                myWriter.write(stock.toString() + "\n");
            }
            myWriter.close();
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void read()
    {
        stockList.clear();
        try
        {
            Scanner sc = new Scanner(new File("stocks.txt"));
            while (sc.hasNextLine()) 
            {
                String[] tokens = (sc.nextLine().split(", "));
                add(tokens[0], tokens[1], Float.parseFloat(tokens[2]));       
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            //System.out.println("File stocks.txt not found");
        }
    }
}
