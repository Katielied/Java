import java.util.ArrayList;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 *
 * @author Katarina Liedbeck
 * @version Version1 -- 03/29/2023
 */
public class Bank implements BankInterface
{
    //Creating private variables for this class.
    private String nameOfBank;
    private Account[] accounts;
    private int numOfAcc;
    private final int NUM_ACC = 1000;
                                    
    
    /**
     * Constructor for objects of class Bank
     */
        public Bank()
        {
        //Creating instance variables, which will be replaced by alias'
        this.nameOfBank = "";
        this.accounts = new Account[NUM_ACC];
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
        //Method to set the name of the bank.
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
        //numOfAcc is the variable for how many accounts there are.
        //Then we add one to the numOfAcc
        accounts[numOfAcc] = addedAcc;
        numOfAcc++;
        
    }
    
    /**
     * Method used to search for an account in an array using binary search
     * Precondition: method of binary search exits, and sortAccounts method exits.
     * Postcondition: method returns the found account using the String searchedId through binary search. If the account if is not found
     * It will return null.
     * 
     * @param String searchedId, id which you want to find account of.
     * @return found, return the found account
     */
    public Account search(String searchedId)
    {
        //Creating a for loop to go through the array of accounts.
        //If the id of one of the accounts in the array is equal to the id searched for, it will be returned.
        //If the for loop finishes, and the account id is not found, it will return null.
        sortAccounts();
        
        int foundIndex = binarySearch(this.accounts, 0, this.numOfAcc - 1, searchedId);
        
        if(foundIndex == -1)
        {
            return(null);
        }
        
        Account found = this.accounts[foundIndex];
        
        if(found == null)
        {
            return(null);
        }
        
        return(found);
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
        //First we use the search method to find the account we want to withdraw from.
        //then we create an if condition statement, which takes the searched account and withdraws the parameters from its balance.
        // if the searched account is not found, the result == null, which means the money will not be withdrawn == nothing happens.
          
        Account result = this.search(id);
        if (result != null)
        {
            result.withdraw(moneyToWithdraw); 
        }
          
    }
    
    /**
     * Method used to get the balance of specific account in Bank.
     * Precondition: the search method and the getbalance method from account class used exists 
     * Postcondition: returns the balance of the specific account in array, using parameter of String id and search method, also uses
     * the getbalance method from the account class.
     * If account not found, the balance will not be found.
     * 
     * @param Stirng id, id which you want to get the balance from.
     */
    public void getBalance(String id)
    {
        Account result = this.search(id);
        
        if(result != null)
        {
            result.getbalance();
        }
    }
    
    /**
     * Method used to get number of accounts in bank
     * Postcondition: returns the number of accounts in the account array in the Bank.
     * 
     * @return this.numOfAcc, returning the number of accounts in the array.
     */
    public int getNumOfAcc()
    {
        return(this.numOfAcc);
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
        //First we use the search method to find the account we want to withdraw from.
        //then we create an if condition statement, which takes the searched account and deposits the parameters from its balance.
        // if the searched account is not found, the result == null, which means the money will not be depositted == nothing happens.
        
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
        //Creating a method to create a string of the bank and accounts.
        //Creating an empty string first.
        //concatinating the name of the bank, the number of accounts,
        //Finally adding the different accounts in the array, we use a for loop
        //Using a for loop, we search through each element in the array and add it to the string.
        
        String result = "";
        
        result += "The name of the bank is :" + this.nameOfBank + "\n";
        //result += "The number of accounts are :" + this.numOfAcc + "\n";
        result += "The accounts are :\n\n";
        
        for(int i = 0; i<numOfAcc; i++)
        {
            result += accounts[i].toString() + "\n";
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
            if (value.equals(anArray[mid].getId())) 
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
        SortsClass.bubbleSort(this.accounts, this.numOfAcc);
    }
    
    /**
     * Method used to write the updated accounts into a file
     * precondition: There is an existing file to write to
     * postcondition: The file will be updated with the new accounts.
     * @param: String fileName, file which you are writing to.
     * 
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
                ofStream.print(this.accounts[i].getname() + ",");
                ofStream.print(this.accounts[i].getId() + ",");
                ofStream.print(this.accounts[i].getBalanceAsLong() + ",");
                
                if(this.accounts[i] instanceof Checking)
                {
                    ofStream.print("c" + ",");
                    ofStream.print(this.accounts[i].getOM());
                }
                else
                {
                    ofStream.print("r" + ",");
                }
                ofStream.print("\n");
            }
            
            ofStream.close();
        } // end try
        catch (IOException e) 
        {
            System.out.println("Error writing to file");
        } // end catch
        finally 
        {
            if (ofStream != null) 
            {
                ofStream.close();
            } // end if
        } // end finally
        
    } // end copyTextFile
}

