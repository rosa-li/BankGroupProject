import java.lang.Math;
import java.util.*;
import java.io.*;

public class Investor
{
	private long cardNumber;
	private ArrayList<Stock> stockList = new ArrayList<Stock>();
	private static ArrayList<Investor> investorList = new ArrayList<Investor>();
	
	public Investor(long cardNum)
	{
	    cardNumber = cardNum;
	}
	
	public String toString()
	{
	    String symbols = "";
	    for(Stock stock : stockList)
	    {
	        symbols += ", " + stock.getSymbol();
	    }
	    return cardNumber + symbols;
	}
	
	public Stock buy(String symbol)
	{
	    Stock stock = Stock.search(symbol);
	    if(stock != null)
	    {
	        if(stockList.contains(stock))
	        {
	            stock = null;
	        }
	        else
	        {
	            stockList.add(stock);
	        }
	    }
	    return stock;
	}
	
	public Stock sell(String symbol)
	{
	    Stock stock = Stock.search(symbol);
	    if(stock != null)
	    {
	        if(stockList.remove(stock) == false)
	        {
	            stock = null;
	        }
	    }
	    return stock;
	}
	
	public ArrayList<Stock> owned()
	{
	    return stockList;
	}
	
	public static Investor add(long cardNumber)
	{
	    Investor investor = new Investor(cardNumber);
	    investorList.add(investor);
	    return investor;
	}
	
	public static ArrayList<Investor> getList()
	{
	    return investorList;
	}
	
	public static Investor search(long cardNum)
	{
	    for(Investor investor : investorList)
	    {
	        if(investor.cardNumber == cardNum)
	        {
	            return investor;
	        }
	    }
	    return null;
	}
	
	public static void write()
	{
	    try 
	    {   
	        FileWriter myWriter = new FileWriter("investors.txt");
	        for(Investor investor : investorList)
	        {
	            myWriter.write(investor.toString() + "\\n");
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
	    investorList.clear();
	    try
	    {
	        Scanner sc = new Scanner(new File("investors.txt"));
	        while (sc.hasNextLine()) 
	        {
	            String[] tokens = (sc.nextLine().split(", "));
	            Investor investor = add(Long.parseLong(tokens[0]));       
	            
	            for(int i = 1; i < tokens.length; i ++)
	            {
	                Stock stock = Stock.search(tokens[i]);
	                if(stock != null)
	                {
	                    investor.stockList.add(stock);
	                }
	            }
	        }
	        sc.close();
	    }
	    catch (FileNotFoundException e)
	    {
	        //System.out.println("File investors.txt not found");
	    }
	}
}
	
