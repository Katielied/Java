import java.util.ArrayList;
/**
 * Write a description of class BankInterface here.
 *
 * @Katarina Liedbeck
 * @version1 04/17/2023
 */
public interface BankInterface //Creating an  interface class.
{
    //Interface does not need a constructor.
    //Including all the methods and parameters of Bank without a method body.
    
    /**
     * Method to set the String name.
     * 
     */
    public void setName(String newName);
    
    /**
     * Method which returns the string of the name.
     * 
     */
    public String getName();
    
    /**
     * Method which adds an account of type account.
     */
    public void addAcc(Account addedAcc);
    
    /**
     * Method which searches for an Account, using the parameter of a String id.
     */
    public Account search(String searchedId);
    
    /**
     * Method which withdraws from an account in the array, after searching for the desired id.
     */
    public void withdraw(String id, Money moneyToWithdraw);
    
    /**
     * Method which gets the number of accounts in the array.
     */
    public int getNumOfAcc();
    
    /**
     * Method which deposits to an account in the array, after searching for the desired id.
     */
    public void deposit(String id, Money moneyToDeposit);
    
    /**
     * Method which turns the bank attributes into a better understood String.
     */
    public String toString();
    
    /**
     * This method will use the method of binary search in order to find the account in the bank.
     *
     */
    public int binarySearch(Account[] anArray, int first, int last, String value);
    
    /**
     * This method will write the new updated accounts into a file.
     */
    public void writeTextToFile(String originalFileName);

    }
