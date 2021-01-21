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

    public boolean buy(Stock stock)
    {
        if(stockList.contains(stock))
        {
            System.out.println(stock.getSymbol() + " already owned");
            return false;
        }
        stockList.add(stock);
        return true;
    }

    public boolean sell(Stock stock)
    {
        boolean ret = stockList.remove(stock);
        if(ret == false)
        {
            System.out.println(stock.getSymbol() + " is not owned");
        }
        return ret;
    }

    public ArrayList<Stock> owned()
    {
        return stockList;
    }

    public static Investor add(long cardNum)
    {
        Investor investor = search(cardNum);
        if(investor == null)
        {
            investor = new Investor(cardNum);
            investorList.add(investor);
        }
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
                myWriter.write(investor.toString() + "\n");
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

    public static void addInvestment(UserInfo customer)
    {
        if(customer == null)
        {
            System.out.println("Invalid user");
            return;
        }
        Scanner in = new Scanner(System.in);
        Investor investor = Investor.search(customer.getCardNum());
        String input;
        if(investor == null)
        {
            //prompts user to decide if they want the choice to invest
            System.out.println("Type yes if you would like to open an investment account");
            input = in.nextLine();
            //if statement that calls a method to setup the investment account
            if(input.equalsIgnoreCase("yes") == false)
            {
                return;
            }
            investor = Investor.add(customer.getCardNum());
        }

        do
        {
            System.out.println("Enter stock symbol to buy or enter cancel to skip");
            ArrayList<Stock> stockList = Stock.getList();
            for(Stock stock : stockList)
            {
                System.out.println(stock); 
            }

            input = in.nextLine();
            if(input.equalsIgnoreCase("cancel"))
            {
                return;
            }

            Stock stock = Stock.search(input);
            if(stock != null)
            {
                float value = stock.getCurrentValue();
                float money = customer.getMoney();
                if(money >= value)
                {
                    if(investor.buy(stock))
                    {
                        customer.setMoney(money - value);
                        System.out.println(stock.getSymbol() + " has been bought!\n" + 
                            "your new balance is: " + customer.getMoney());
                    }
                }
                else
                {
                    System.out.println("Insufficient funds");
                }
            }
        } while(true);
    }

    public static void setupInvestments()
    {
        //Stock.read() must be called before Investor.read()
        Stock.read(); //reads the list of stocks
        Investor.read(); //reads the list of investors
        ArrayList<Stock> stockList = Stock.getList();
        //in case stock textfile cant be found, create ficticious stocks
        if(stockList.isEmpty())
        {
            Stock.add("SNSC", "Sunshine corporations", 500);
            Stock.add("ALPN", "Alp Navigations", 750);
            Stock.add("JWE", "Jewel Entertainment", 1000);
            Stock.add("JPN", "Jupiter Networks", 800);
            Stock.add("AI", "Ace Intelligence", 1500);
        }
    }
}
