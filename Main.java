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

        UserInfo customer = new UserInfo();
        
        UserInfo customer2 = users.get(position);

        Investor.setupInvestments();
        Investor.addInvestment(customer2);

        ReadingFromFile read = new ReadingFromFile();
        // System.out.println("Would you like to exchange to another currency?");
        CurrencyExchange exchange = new CurrencyExchange();

        UserInfo info = new UserInfo();

        DepositWithdraw yes = new DepositWithdraw();

        SaveFile file1 = new SaveFile(daysPassed, users);
        Stock.write();
        Investor.write();
    }

