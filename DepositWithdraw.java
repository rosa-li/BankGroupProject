/*DepositWithdraw
* This class handles bank account deposits and withdrawals and returns the new money value
* By: Nicolas Wilkinson
* Start Date: 01/14/2021
* End Date: 01/21/2021
*/
import java.util.*;
	public class DepositWithdraw
	{
	   //prompts for user to enter specific amount to deposit and calls on newBalanceDeposit method
	   public static float depositMoney(float money)
	   {
	       float amount;
	       Scanner keyboard = new Scanner(System.in);
	       System.out.println("Enter an amount to deposit: ");
	       amount = keyboard.nextFloat();
	       //returns new balance
	       return newBalanceDeposit(amount, money);
	   }
	   //prompts for user to enter specific amount to withdraw and calls on newBalanceWithdraw method
	   public static float withdrawMoney(float money)
	   {
	       float amount;
	       Scanner keyboard = new Scanner(System.in);
	       System.out.println("Enter an amount to withdraw: ");
	       amount = keyboard.nextFloat();
	       //returns new balance
	       return newBalanceWithdraw(amount, money);
	   }
	   //calculates new balance after deposit
	   public static float newBalanceDeposit(float fltAmount, float fltMoney)
	   {
	       float newBalance;
	       newBalance = fltMoney + fltAmount;
	       //returns new balance
	       return newBalance;
	   }
	   //calculates new balance after withdrawal
	   public static float newBalanceWithdraw(float fltAmount, float fltMoney)
	   {
	       float newBalance;
	       newBalance = fltMoney - fltAmount;
	       //returns new balance
	       return newBalance;
	   }
	}


