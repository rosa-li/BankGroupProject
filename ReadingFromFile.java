/* Name:  Ben Tait
 * Date Started:  Thursday 14th, 2021
 * Date Finished:  Monday 18th, 2021
 * Function: The class is responsible for reading the file and transfering all of that data to the ArrayList
 */

//Importing 
import java.io.File;  //For file opening 
import java.io.IOException;  //For catching the IOException
import java.util.Scanner;  //For scanning the file 
import java.util.ArrayList;  //Using array lists

public class ReadingFromFile
{
    //For how many times the program has been run
    int daysPassed;
    
    //Constructor  - takes in ArrayList
    ReadingFromFile(ArrayList<UserInfo> users)
    {
        //Initializing variables
        String fn, ln, pass;  //For storing first and last name and password
        float money, withdraws;  //Store money and withdraws
        long cardNum;  //Store the card number
        
        try
        {
            //Creates a variable called file type File that acceses userInfo
            File file = new File("userInfo.txt");
            
            if(file.createNewFile())
            {
                //The file will then be created if one does not exist
            }
            else
            {
                //If it gets here then the file exists and connected...
                
                //Initialize scanner
                Scanner scf = new Scanner(file);
                //String temp;
                
                //record how many times the program has been run - days passed
                this.daysPassed = scf.nextInt();
                System.out.println(daysPassed);
                
                //Repeat while the file has another line  - will scan again if theres another persons details
                while(scf.hasNextLine())
                {
                    fn = scf.next() + scf.nextLine();  //Scan for first name  (The reason theres next() is because without it the scanner would break 
                                                       //if it looped again as on the last scf.nextFloat, the scanner stays there so I have to read 
                                                       //blank space and then the next line. (Its a bug in the scanner)
                    ln = scf.nextLine();  //Scan for last name on the next line
                    pass = scf.nextLine();  //Scan for password on the next line
                    cardNum = scf.nextLong();  //Scan for the card number and store it
                    money = scf.nextFloat();  //Scan for money and store it
                    withdraws = scf.nextFloat();   //Scan for withdraws and store it
                    
                    //Add a new position in the array with the varibles we just scanned
                    users.add(new UserInfo(fn, ln, pass, cardNum, money, withdraws));
                }
                
                scf.close();
            }
        }
        catch(IOException e)  //If there is an error with the file, then it will output an error message
        {
            System.out.println("There was an error...");
            e.printStackTrace();  //Display exact error
        }
    }
    
    //Default constrcutor
    ReadingFromFile()
    {
        
    }
    
    public int getDaysPassed()
    {
        return this.daysPassed;
    }
}
