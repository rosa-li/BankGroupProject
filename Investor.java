/**
 * Omar Khattab
 * 1/21/2021
 * This class is used to set up investors and store them in an arraylist
 * and contains methods to add investors, buy and sell stocks as well as display owned stocks
*/

import java.lang.Math;
import java.util.*;
import java.io.*;

public class Investor
{
    //long to store credit card number
    private long cardNumber;
    //array list of stocks for each investor
    private ArrayList<Stock> stockList = new ArrayList<Stock>();
    //static arraylist of investors
    private static ArrayList<Investor> investorList = new ArrayList<Investor>();

    //constructor for investors, takes a long and sets it to cardNumber
    public Investor(long cardNum)
    {
        cardNumber = cardNum;
    }

    //toString method that prints the credit card of the user and the symbol of the stocks they own
    public String toString()
    {
        String symbols = "";
        //for loop that loops through the array of stocks
        for(Stock stock : stockList)
        {
            //add the symbol of each stock in the list to a string
            symbols += ", " + stock.getSymbol();
        }
        //returns credit card and symbols of owned stocks
        return cardNumber + symbols;
    }

    //method to buy stocks that takes a stock object and returns a boolean. false if already owned and true if not owned
    public boolean buy(Stock stock)
    {
        //checks if the stock matches a stock in the arraylist
        if(stockList.contains(stock))
        {
            //prints a message indicating already owned and returns false
            System.out.println(stock.getSymbol() + " already owned");
            return false;
        }
        //calls the add method, passing it the stock and returning true
        stockList.add(stock);
        return true;
    }

    //method to sell stocks, takes a stock object and returns a boolean.
    public boolean sell(Stock stock)
    {
        //the .remove method for arraylists returns true if the stock existed before being removed, otherwise it returns false. This result is saved in ret
        boolean ret = stockList.remove(stock);
        //checks if ret is false
        if(ret == false)
        {
            //informs the user that they dont own this stock
            System.out.println(stock.getSymbol() + " is not owned");
        }
        //returns ret
        return ret;
    }

    //method that takes no parameters and returns an arraylist of owned stocks
    public ArrayList<Stock> owned()
    {
        return stockList;
    }

    //method to add ne investors that takes a long and returns an investor object
    public static Investor add(long cardNum)
    {
        //calls the search method passing it a long
        Investor investor = search(cardNum);
        //if the search method returns null it creates a new investor with this long and adds it to the list of investors
        if(investor == null)
        {
            investor = new Investor(cardNum);
            investorList.add(investor);
        }
        //returns the investor
        return investor;
    }

    //method that returns a list of investors
    public static ArrayList<Investor> getList()
    {
        return investorList;
    }

    //search method that takes a long and returns an investor that is affiliated with this card number
    public static Investor search(long cardNum)
    {
        //for loop that loops through the list of investors
        for(Investor investor : investorList)
        {
            //if it finds an investor who has a card number that matches the long, it returns the investor
            if(investor.cardNumber == cardNum)
            {
                return investor;
            }
        }
        //other wise returns null
        return null;
    }

    //static write method to save the stocks
    public static void write()
    {
        //try catch to prevent the program from crashing in case of error
        try 
        {   
            //creates a FileWriter object and provides a file name
            FileWriter myWriter = new FileWriter("investors.txt");
             //for loop that loops through the array of investors
            for(Investor investor : investorList)
            {
                //writes down the toString of the investor in the designated file above
                myWriter.write(investor.toString() + "\n");
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

    //static read method to read the investors that were written down
    public static void read()
    {
        //clears the current investorlist
        investorList.clear();
        //try catch to prevent program from crashing
        try
        {
            //scanner to read the designated file
            Scanner sc = new Scanner(new File("investors.txt"));
            //while loop that goes though each line in the file and "tokenises" each line, using those values to recreate the investors 
            while (sc.hasNextLine()) 
            {
                //splits the line by a specified character combination
                String[] tokens = (sc.nextLine().split(", "));
                //parses the first token from string to long
                Investor investor = add(Long.parseLong(tokens[0]));       
                //for loop that loops through all the tokens in a line starting at the second token
                for(int i = 1; i < tokens.length; i ++)
                {
                    //calls the search method from stocks and passes it a token (the symbol)
                    Stock stock = Stock.search(tokens[i]);
                    //if a stock with the specified symbol exists it adds it to the current investor objects  arraylist
                    if(stock != null)
                    {
                        investor.stockList.add(stock);
                    }
                }
            }
            //closes scanner
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            //System.out.println("File investors.txt not found");
        }
    }

    //method that takes a userinfo object and allows them to invest in stocks
    public static void addInvestment(UserInfo customer)
    {
        //if statement to make sure the UserInfo object is valid
        if(customer == null)
        {
            System.out.println("Invalid user");
            return;
        }
        //scanner to read user input
        Scanner in = new Scanner(System.in);
        //calls the search method and assigns it the card number of the UserInfo object
        Investor investor = Investor.search(customer.getCardNum());
        //string to store input
        String input;
        //if the search method returned a null, then it means they havent opened an investment account
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
            //creates an investor object and adds it to the investor list
            investor = Investor.add(customer.getCardNum());
        }

        //do while loop that shows stocks and allows user to buy stocks
        do
        {
            //prompts user for an input
            System.out.println("Enter stock symbol to buy or enter cancel to quit");
            //creates and stores an array list of stocks
            ArrayList<Stock> stockList = Stock.getList();
            //loops through this array list and prints out each stock for the user
            for(Stock stock : stockList)
            {
                System.out.println(stock); 
            }
            //stores user input
            input = in.nextLine();
            //checks to see if user wants to quit
            if(input.equalsIgnoreCase("cancel"))
            {
                return;
            }
            //searches for a stock that matches the symbol the user typed
            Stock stock = Stock.search(input);
            //if a stock is found it then checks to see if the user can afford before buying the stock
            if(stock != null)
            {
                //stores current value of stock
                float value = stock.getCurrentValue();
                //stores current balance of the user
                float money = customer.getMoney();
                //checks to see if the user can afford the stock
                if(money >= value)
                {
                    //if statement to check if the stock is succesfully bought
                    if(investor.buy(stock))
                    {
                        //sets the customers balance to the new balance after paying for the stock
                        customer.setMoney(money - value);
                        //outputs a message to verify a stock has been bought and their newe balance
                        System.out.println(stock.getSymbol() + " has been bought!\n" + 
                            "your new balance is: " + customer.getMoney());
                    }
                }
                else
                {
                    //if the customer lacks the necessary amount, it prints a message alerting the customer
                    System.out.println("Insufficient funds");
                }
            }
            //keeps looping while true
        } while(true);
    }

    //method that sets up a list of stocks
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
