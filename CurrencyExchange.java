/*LiRosaCurrencyExchange.java
*This class takes in the amount of money a user wants to exchange and exchanges it into the desired currency. 
* By: Rosa Li
* Start Date: 01/14/2021
* End Date: 01/21/2021
*/
public class CurrencyExchange 
{
	//instance variables that store which currency user exchanges to and amount they want to exchange
	private static String strToExchange;
	private static float intAmount;
	
	//constructor that creates CurrencyExchange object
	CurrencyExchange(String toExchange, int amount)
	{
		this.strToExchange = toExchange;
		this.intAmount = amount;
	}
	
	//default constructor
	CurrencyExchange()
	{
		this.strToExchange = "";
		this.intAmount = 0;
	}
	
	//method that takes in currency type and amount then calls appropriate method
	public static void whichCurrency(String exchange, float amount)
	{
		
		if(exchange.equals("usd"))
		{
			toUSD(amount);
		}
		else if(exchange.equals("euro"))
		{
			toEuro(amount);
		}
		else if(exchange.equals("pound"))
		{
			toPound(amount);
		}
		else if (exchange.equals("yen"))
		{
			toYen(amount);
		}
		else if(exchange.equals("rmb"))
		{
			toRMB(amount);
		}
	}
	
	//converts given amount to USD
	public static float toUSD(float amount)
	{
		float fltExchange;
		fltExchange = amount * (float)0.78;
		return fltExchange;
	}
	
	//converts given amount to Euro
	public static float toEuro(float amount)
	{
		float fltExchange;
		fltExchange = amount * (float)0.65;
		return fltExchange;
	}
	
	//converts given amount to Pound
	public static float toPound(float amount)
	{
		float fltExchange;
		fltExchange = amount * (float)0.58;
		return fltExchange;
	}
	
	//converts given amount to Yen
	public static float toYen(float amount)
	{
		float fltExchange;
		fltExchange = amount * (float)81.21;
		return fltExchange;
	}
	
	//converts given amount to RMB
	public static float toRMB(float amount)
	{
		float fltExchange;
		fltExchange = amount * (float)5.08;
		return fltExchange;
	}
	
	
}