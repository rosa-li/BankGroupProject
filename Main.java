import java.util.ArrayList;

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
	       
	       Stock invest = new Stock("SNSC", "Sunshine corporations", 300.0);
	       
	       ReadingFromFile read = new ReadingFromFile();
	      // System.out.println("Would you like to exchange to another currency?");
	       CurrencyExchange exchange = new CurrencyExchange();
	       
	       
	        
	    }
}

