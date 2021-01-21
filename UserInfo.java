/* Name:  Ben Tait
 * Date Started:  Thursday 14th, 2021
 * Date Finished:  Monday 18th, 2021
 * Function:  Responsible for aksing which user they are, or if they want to create a new one
 */

//Importing
import java.util.ArrayList;  //For using array lists
import java.util.Scanner;   //For using the scanner

public class SetUpAccounts
{
    //Variable for storing how many days passed
    private int daysPassed;

    //Default constructor
    SetUpAccounts()
    {

    }

    //Method called in main to run the main part of code  -takes in the ArrayList
    public int SetUpAccounts(ArrayList<UserInfo> users)
    {
        //Calls the class to read the file an populate the ArrayList
        ReadingFromFile setUp = new ReadingFromFile(users);

        //Days passed is scanned from the file in the ReadFromFile class and then we use a get method to store that value in this class
        daysPassed = setUp.getDaysPassed();

        //Initialize scanner
        Scanner sc = new Scanner(System.in);

        //Initializing variables
        int intUserResponse;  //For the users response
        boolean incPassword;  //Boolean to tell if they inputted the correct password or not

        do
        {
            //Set intUserResponse to -1 so it will run again if a string is inputted
            intUserResponse = -1;

            do
            {
                System.out.println("Hello, which user are you?");

                //Call method to display the users
                displayOptions(users);

                //Reboot scanner to ensure it dosent bug out
                sc.close();
                sc = new Scanner(System.in);

                try
                {
                    //Store user response
                    intUserResponse = sc.nextInt();

                    //Display error if the input isnt between 1 and the size+1 (+1 for the option to create a new account)
                    if(intUserResponse > users.size()+1 || intUserResponse < 1)
                    {
                        System.out.println("Please input a number between 1 and " + (users.size()+1));
                    }
                }
                catch(Exception e)  //Catch exception  (if string inputted)
                {
                    System.out.println("Please input a number!");
                }
            }while(intUserResponse > users.size()+1 || intUserResponse < 1);  //Loop while the user response is within the options presented

            // subtract one from their input so that its the position in the arraylist
            intUserResponse--;

            //Method call to clear the screen
            clear();

            //If they want to create a new account
            if(intUserResponse == users.size())
            {
                //Call method to create new account
                createNewAccount(users);

                //Set boolean to true so it will break out of the loop
                incPassword = true;
            }
            else  //They selected a previous account and we need them to input their password
            {
                //Call method to get their password  -return true or false whether they inputted it correctly or not, and sends in the correct password
                incPassword = password(users, users.get(intUserResponse).getPassword());
            }

            //clear screen
            clear();

        }while(incPassword == false);  //repeat while they havent inputted the correct password

        //Once their in, it outputs a message
        //Once their in, it outputs users details
        System.out.println("Name: " + users.get(intUserResponse).getName() + " " + users.get(intUserResponse).getLastName());
        System.out.println("Card Number: " + users.get(intUserResponse).getCardNum());
        System.out.println("Money: $" + users.get(intUserResponse).getMoney());

        //return the position in the array of which account their using
        return intUserResponse;
    }

    //Method to clear the screen easier
    public static void clear()
    {
        //clear screen
        System.out.print("\u000C");
    }

    //Method to display the accounts and the option to create a new one
    public static void displayOptions(ArrayList<UserInfo> users)
    {
        //Loops through the array list using for loop
        for(int i = 0; i < users.size(); i++)
        {
            //Use get methods to output first and last name
            System.out.println((i+1) + ") " + users.get(i).getName() + " " + users.get(i).getLastName());    
        }
        //print last option
        System.out.println((users.size()+1) + ") Create new Account");
    }

    //Method to ask for the password and return true or false (5 attempts to guess correctly), also takes in the correct password as a parameter
    public static boolean password (ArrayList<UserInfo> users, String password)
    {
        //Initialize scanner
        Scanner sc = new Scanner(System.in);

        //Initialize string to store the users input
        String userEnter;

        //loop through 5 times
        for(int i = 0; i< 5; i++)
        {
            System.out.println("Whats the password to your account? ");
            userEnter = sc.nextLine();

            //If they answered the correct password return true
            if(userEnter.equals(password))  //It is case sensitive
            {
                return true;
            }
            else  //If incorrect then keep looping
            {
                System.out.println("Incorrect...\n");
            }
        }

        //if they never input the correct password after 5 tries then return false (will go back to the title screen)
        return false;
    }

    //This method is responsible for creating a new account
    public static void createNewAccount(ArrayList<UserInfo> users)
    {
        //Initialize scanner
        Scanner sc = new Scanner(System.in);

        //Initialize variables
        String fn, ln, pass, checkPass;  //to store the first, last name, password and then double chek the password
        long cardNum = -1;  //Store the card number
        float money = -1;   //Store the money in account
        boolean loop = false;  //Loop to ensure they input a float value when inputting money

        //Ask for details and then store the values
        System.out.print("Whats your name? ");
        fn = sc.nextLine();
        clear();

        System.out.print("Whats your last name? ");
        ln = sc.nextLine();
        clear();

        //Ask for password
        do
        {
            System.out.print("What do you want your password to be? ");
            pass = sc.nextLine();
            clear();

            //Ask to retype password
            System.out.println("Re-type your password: ");
            checkPass = sc.nextLine();
            clear();

            //If the passwords dont match then display error message
            if(!(pass.equals(checkPass)))
            {
                System.out.println("Passwords do not match");
            }
        }while(!(pass.equals(checkPass)));  //ensure passwords match

        do
        {
            System.out.print("Insert your card number: ");

            //Reboot scanner - to stop other issues with the scanner(bugs)
            sc.close();
            sc = new Scanner(System.in);

            try
            {
                cardNum = sc.nextLong();
            }
            catch(Exception e)  //If they input a charactar (not a number)
            {
                System.out.println("Please input a number...");
                cardNum = -1;  //reset card number to -1 so it will loop again
            }
        }while(cardNum == -1);  //Repeat while the card number is -1 (starting value)
        clear();

        do
        {
            System.out.print("How much money is in your account: ");

            //Reboot scanner - to stop other issues with the scanner(bugs)
            sc.close();
            sc = new Scanner(System.in);

            try
            {
                money = sc.nextFloat();
                loop = true;  //Stop looping
            }
            catch(Exception e)  //If error occurs
            {
                System.out.println("Please input a number...");
            }
        }while(loop == false);  //Will loop until they input a float (-ve or +ve)

        //Add a new position in the array list  - set withdraws to 0.0 as they start at 0
        users.add(new UserInfo(fn, ln, pass, cardNum, money, (float)(0.0)));
    }

    //get method to be used by main to get the number of days passed (how many times the program has run)
    public int getDaysPassed()
    {
        return this.daysPassed;
    }
}
