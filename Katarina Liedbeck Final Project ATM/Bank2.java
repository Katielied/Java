import java.util.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Katarina Liedbeck
 * @version1 04/17/2023
 */
public class Bank2 implements BankInterface
{
    //Implementing the BankInterface, which means creating all the methods promised in BankInterface.
    
    // Creating the private variables 
    private String nameOfBank;
    
    //Creating the arrayList of type object Account
    private ArrayList<Account> accounts;
    private int numOfAcc;

    /**
     * Constructor for objects of class Bank2
     */
    public Bank2()
    {
        //Using the same constructor as the original Bank, however also using the ArrayList instead of Array.
        this.nameOfBank = nameOfBank;
        this.accounts = new ArrayList<Account>();
        this.numOfAcc = 0;
    }

    /**
    * The method is used to set the name of the Bank to a new name.
    * Precondition: There is a bank which exists.
    * Postcondition: The name of the bank will now have the String newName passed as a parameter.
    * 
    * @param String newName, new name of the bank
    */
    public void setName(String newName)
    {
        //Creating a setName method in order to create the name of the bank.
        this.nameOfBank = newName;
    }
    
    /**
     * Method used to get the name of the bank.
     * Precondition: There is a bank which exists.
     * Postcondition: the method will return the name of the existing bank.
     * 
     * @return this.nameOfBank, returns the name of the bank.
     */
    public String getName()
    {
        //Creating a method to get the name of the bank.
        return(this.nameOfBank);
    }
    
    /**
     * Method used to add an account to the Bank
     * Precondition: An array exits in the Bank to store the accounts, there is a maximum number of accounts allowed to be stored in the array.
     * Postcondition: The method will add 1 to the number of the accounts in the array, and it will also add the whole account to the 
     * array of accounts in the bank. 
     * 
     * @param Account addedAcc, new account added
     */
    public void addAcc(Account addedAcc)
    {
        //Since we are not using an array, we need only use the variable name of accounts and use the add method with
        // () parenthesis instead of [] brackets.
        this.accounts.add(addedAcc);        
        this.numOfAcc++;
    }
    
    
    /**
     * Method which searches for an Account, using the parameter of a String id.
     * Precondition: getId() method exists.
     * Postcondition: returns the account with the same String id as String searchedId parameter, if id not found, method returns null.
     * 
     * @param String searchedId, id of account user wishes to search for.
     * @return, returns account with matching id if found, else returns null.
     */
    public Account search(String searchedId)
    {
        //Using the same method of search as used in the original Bank Class
        //Changing numOfAcc variable to accounts.size, and using open parentheses. 1
        //Since we are using an array list, we can also just use the .get method, and .getId method
        //As well as the .equals.
         
        for(int i = 0; i<numOfAcc; i++)
        {
            String id = accounts.get(i).getId();
            
            if (id.equals(searchedId))
            {
                return accounts.get(i);
            }
            
        }
        
        return null;
    }
    
    /**
     * Method used to withdraw money from a specific account in the bank
     * Precondition: The search method used exists, and the withdraw method used exits, the money to withdraw is written in correct
     * format of 00.00
     * Postcondition: method withdraws moneytoWithdraw from specific String id account. If the account is not found, it will not 
     * withdraw from the account.
     * 
     * @param String id, id of the account you want to withdraw money from
     * @param Money moneyToWithdraw, the money you want to withdraw from the accoubn
     */
    public void withdraw(String id, Money moneyToWithdraw)
    {
        //Using the same withdraw method as in Bank.
        Account result = this.search(id);
        if (result != null)
        {
            result.withdraw(moneyToWithdraw); 
        }
    }
    
     /**
     * Method used to get number of accounts in bank
     * precondition: there is an array of accounts created.
     * Postcondition: returns the number of accounts in the account array in the Bank.
     * 
     * @return this.numOfAcc, returning the number of accounts in the array.
     */
    public int getNumOfAcc()
    {
        //Replacing the variable of getNumOfAcc with the accounts using the .size method.
        return accounts.size();
    }
    
    /**
     * Method used to deposit money into an account in the Bank
     * Uses the method of search to find account where money will be depositted into.
     * Precondition: moneyToDeposit is written in correct format (00.00), the search method and the deposit method already exists.
     * Postcondition: moneyToDeposit parameter will be added to the balance of the searched account using the String id parameter.
     * if account is not found using search method, money will not be depositted.
     * 
     * @param String id, id of the account you want to deposit money into.
     * @param Moneu moneyToDeposit, the amount of money you want to deposit into an account.
     */
    public void deposit(String id, Money moneyToDeposit)
    {
        //Using the same deposit method as in the original Bank Class
        
        Account result = this.search(id);
        if (result != null)
        {
                result.deposit(moneyToDeposit); 
        }
        
    }
    
    /**
     * Method used to create String of the bank and accounts.
     * Precondition: The Bank has a name, and there are added accounts into the bank.
     * Postcondition: Method returns the bank and accounts in String Format, if no name of the bank is found, name will be an empty String
     * If no accounts in the bank are found, the bank will be returned empty.
     * 
     * @return result, returning the String result of the Account.
     */
    public String toString()
    {
         //Using the same toString method as in Bank
         //However changing the for loop which goes through the ArrayList instead of an Array.
         //using the accounts.size() method instead
         //Also using the .get method 
         //Also using the () open parenthesis instead of [] brackets.
        
        String result = "";
        
        result += "The name of the bank is :" + this.nameOfBank + "\n";
        result += "The number of accounts are :" + this.accounts.size() + "\n";
        result += "The accounts are :\n";
        
        for(int i = 0; i<accounts.size(); i++)
        {
            result += accounts.get(i).toString() + "\n";
        }
        
        return result;
        
    }
    
    /**
     * Method used to find accounts in array using binary search
     * Takes in parameters of an account array, first and last index, and String id.
     * Preconditions: 0<=first, last <= SIZE-1
     * Postconditions: If the value (String id) is found in the array, method returns index of array item with that value,
     * else, returns -1.
     * 
     * @param Account[] anArray, the array of accounts.
     * @param int first, the first element in the array.
     * @param int last, the last element in the array.
     * @param String Value, the Account id we are trying to search for.
     * @return index, returns the index found with that value, if value not found, returns -1.
     */
    public int binarySearch(Account[] anArray, int first, int last, String value) 
    {
        // Searches the array items anArray[first] through
        // anArray[last] for value by using a binary search.
        // Precondition: 0 <= first, last <= SIZE-1, where
        // SIZE is the maximum size of the array, and
        // anArray[first] <= anArray[first+1] <= ... <= anArray[last].
        // Postcondition: If value is in the array, the method
        // returns the index of the array item that equals value;
        // otherwise the method returns -1.

        int index;

        if (first > last) 
        {

            index = -1;      // value not in original array

        } 
        else 
        {

            // Invariant: If value is in anArray, 
            // anArray[first] <= value <= anArray[last]

            int mid = (first + last)/2;
            if (value.equals(anArray[mid])) 
            {

                index = mid;  // value found at anArray[mid]

            } 
            else if (value.compareTo(anArray[mid].getId()) < 0) 
            {

                index = binarySearch(anArray, first, mid-1, value);   // point X

            } 
            else 
            {

                index = binarySearch(anArray, mid+1, last, value);    // point Y

            }   // end if
        }   // end if
        return index;
    }  
    
    /**
     * Method used to sort the accounts 
     * precondition: SortsClass is already created, method in SortsClass (bubbleSort) is already created
     * postcondition: Sorts items in the array in ascending order
     */
    public void sortAccounts()
    {
        //Using the predefined class of collections to sort the accounts
        //Where accounts implements comparable and overrides the CompareTo Method.
        Collections.sort(this.accounts);
    }
    
    /**
     * Method used to write the updated accounts into a file
     * precondition: There is an existing file to write to
     * postcondition: The file will be updated with the new accounts.
     * @param: String fileName, file which you will write to.
     */
    public void writeTextToFile(String fileName) 
    {
        // ---------------------------------------------------------
        // Makes a duplicate copy of a text file.
        // Precondition: originalFileName is the name of an existing
        // external text file, and copyFileName is the name of the
        // text file to be created.
        // Postcondition: The text file named copyFileName is a
        // duplicate of the file named originalFileName.
        // ---------------------------------------------------------
        PrintWriter ofStream = null; // declare an output file stream
        try 
        {
            // Initialize the output file stream, based on the name of the output file, stored in copyFileName
            ofStream = new PrintWriter(new FileWriter(fileName)); 

            String line;

            // copy lines one at a time from given file to new file
            for(int i = 0; i < numOfAcc; i++)
            {
                ofStream.print(this.accounts.get(i).getname() + ",");
                ofStream.print(this.accounts.get(i).getId() + ",");
                ofStream.print(this.accounts.get(i).getBalanceAsLong() + ",");
                ofStream.print("\n");
            }
            
            ofStream.close();
        } // end try
        catch (IOException e) 
        {
            System.out.println("Error copying file");
        } // end catch
        finally 
        {
            if (ofStream != null) 
            {
                ofStream.close();
            } // end if
        } // end finally
        
 
    }
}
