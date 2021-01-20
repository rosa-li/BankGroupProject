/* Name:  Ben Tait
 * Date Started:  Thursday 14th, 2021
 * Date Finished:  Monday 18th, 2021
 * Function: The class is responsible for saving the new/changed data to the file
 */

import java.io.*;
//import java.io.FileWriter;  //For file opening 
//import java.io.BufferedWriter;
//import java.io.IOException;  //For catching the IOException
import java.util.ArrayList;  //Using array lists

public class SaveFile
{
    SaveFile(int daysPassed, ArrayList<UserInfo> users)
    {
        try
        {
            //Creates a variable called file type File that acceses userInfo
            BufferedWriter myFile = new BufferedWriter(new FileWriter("userInfo.txt", false));
            //Initialize variables to store the value and write it to the file
            String temp;
            
            //Increase the number of days by 1
            myFile.write((daysPassed + 1) + "\n");
            
            for(int i = 0; i <users.size(); i++)  //loop throughout array list
            {
                 //Write details to file
                 myFile.write(users.get(i).getName() + "\n");
                 myFile.write(users.get(i).getLastName() + "\n");
                 myFile.write(users.get(i).getPassword() + "\n");
                 
                 //Use temporary String to display as write() cant display floats or longs
                 temp = String.valueOf(users.get(i).getCardNum());
                 myFile.write(temp + "\n");
                 
                 temp = String.valueOf(users.get(i).getMoney());
                 myFile.write(temp + "\n");
                 
                 temp = String.valueOf(users.get(i).getWithdraws());
                 myFile.write(temp);
                 
                 //Will add the next line unless its at the end of the array, in which case it wont
                 //This is because if you did, the code would think you had another account when you run the 
                 //program again, causing an error
                 if(i < (users.size()-1))
                 {
                     myFile.write("\n");
                 }

            }
            
            //Close scanner
            myFile.close();
        }
        catch(IOException e)  //If there is an error with the file, then it will output an error message
        {
            System.out.println("There was an error...");
            e.printStackTrace();  //Display exact error
        }
            
    }
}
