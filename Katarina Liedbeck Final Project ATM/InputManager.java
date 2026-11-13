
/**
 * Manage input to be read from either keyboard or file.
 * 
 * @Katarina Liedbeck
 * @version1 04/27/2023
 */

import java.util.Scanner;

public class InputManager
{
    /**
     * Method: readOneAccountFrom
     * Precondition: inputSource is a Scanner object, already set up to read from a text file or standard input source (keyboard).
     * Postcondition: returns an Account with the data read for its attributes.
     * Assumption: Account data will be in the format of: name,id,balance
     */
    public static Account readOneAccountFrom (Scanner inputSource)
    {
        Account oneAccount = new Account("", "", new Money(0, 00));
        
        // Read one line of account data into oneLine
        String oneLine = inputSource.nextLine();
        
        // Parse line of account data, separated by commas.
        Scanner lineTokenizer = new Scanner (oneLine);
        lineTokenizer.useDelimiter (",");
        
        // Get account data (i.e. name, accountNum and balance) from oneLine
        String name = lineTokenizer.next ();
        String id = lineTokenizer.next();
        long balance = lineTokenizer.nextLong();
        String accountType = lineTokenizer.next();
        long overdraft = lineTokenizer.nextLong();
        
        //if the account type equals r or c, refers to which instance of account it is. 
        if(accountType.equals("r"))
        {
            oneAccount = new Account (name, id, new Money(balance));
        }
        
        if(accountType.equals("c"))
        {
            oneAccount = new Checking (name, id, new Money(balance), new Money(overdraft));
        }
    
        return oneAccount;
    } // end readOneAccountFrom
} // end InputManager

