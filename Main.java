import java.util.ArrayList;
import java.lang.Math;
import java.util.*;
import java.io.*;

public class Main
{
	   public static void main(String [] args)
	   {
	       SetUpAccounts start = new SetUpAccounts();
	       int position, daysPassed;
	       ArrayList<UserInfo> users = new ArrayList<UserInfo>();
	        
	       position = start.SetUpAccounts(users);
	       daysPassed = start.getDaysPassed();
	        
	       System.out.println(position);
	       System.out.println(daysPassed);
	       System.out.println(users.get(position).getName());
	       
	       UserInfo customer = new UserInfo();
	       //System.out.println("Would you like to invest in a stock?");
	       
	       Stock invest = new Stock("SNSC", "Sunshine corporations", (float)300.0);
	       
	       ReadingFromFile read = new ReadingFromFile();
	      // System.out.println("Would you like to exchange to another currency?");
	       CurrencyExchange exchange = new CurrencyExchange();
	       
	       UserInfo info = new UserInfo();
	       
	       DepositWithdraw yes = new DepositWithdraw();
	       
	       SaveFile file1 = new SaveFile(5, users);
	       
	       
	       
	       
	       
	       
	         
	        Stock.read();
	        ArrayList<Stock> stockList = Stock.getList();
	        if(stockList.isEmpty())
	        {
	           Stock.add("SNSC", "Sunshine corporations", 500);
	           Stock.add("ALPN", "Alp Navigations", 750);
	           Stock.add("JWE", "Jewel Entertainment", 1000);
	           Stock.add("JPN", "Jupiter Networks", 800);
	           Stock.add("AI", "Ace Intelligence", 1500);
	        }
	              
			stockList = Stock.getList();
			for(Stock stock : stockList)
			{
			System.out.println(stock); 
			}
				               
			Stock.write();
			Stock.read();
				               
			stockList = Stock.getList();
			for(Stock stock : stockList)
			{
			System.out.println(stock); 
			}
	               
	            
	       
	    
}

