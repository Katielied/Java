
/**
 * Write a description of class Account here.
 *
 * @Katarina Liedbeck
 * @version1 04/17/2023
 */
public class Account implements Comparable //Implementing a predefined class of Comparable
{
    
    private String name, id;
    private Money balance;

    /**
     * Constructor for objects of class Account
     */
    public Account(String theName, String theid, Money theBalance)
    {
        this.name = theName;
        this.id = theid;
        this.balance = theBalance;
    }

    /**
     * Method used to deposit money into an account
     * Precondition: the account which you deposit money into exists, and the method add() already exists.
     * Postcondition: the old balance of the account will add the new moneyToAdd which is passed in the parameter.
     * 
     * @param Money moneyToAdd, money to deposit to balance.
     */
    public void deposit(Money moneyToAdd)
    {
        this.balance = this.balance.add(moneyToAdd);
    }
    
    /**
     * Method used to withdraw money from an account.
     * Precondition: the account which you withdraw money from already exists, and the method subtract() already exists.
     * Postcondition: The old balance of the account will subtract the amount of new moneyToSubtract which is passed in the parameter.
     * 
     * @param Money moneyToSubtract, money to withdraw from balance.
     */
    public void withdraw(Money moneyToSubtract)
    {
        this.balance = this.balance.subtract(moneyToSubtract);
    }
    
    /**
     * Method used to transfer money from one account to another
     * Precondition: Both the accounts exists, and the withdraw and deposit methods already exist.
     * Postcondition: The old account will subtract the amount of moneyToTransfer from their balance, and the new account
     * (theAccount) passed in the parameter will add that amount of moneyToTransfer to their balance.
     * 
     * @param Account theAccount, the account to transfer to.
     * @param Money moneyToTransfer, the money you wish to transfer to the account.
     */
    public void transfer(Account theAccount, Money moneyToTransfer)
    {
        withdraw(moneyToTransfer);
        theAccount.deposit(moneyToTransfer);
    }
    
    /**
     * method which returns the total amount of money.
     * precondition: There is an existing account with a balance.
     * postcondition: The method will return the balance of that same account .
     */
    public Money getTotalMoney()
    {
        return this.balance;
    }
    
    /**
     * Method used to turn the account into a readable String
     * Precondition: The account exists.
     * Postcondition: The account will be returned as a readable string. 
     * 
     * @return result, returns the String result of the Account.
     */
    public String toString()
    {
        String result = "Account name: " + this.name + "\n" + "Account id: " + this.id + "\n" + "Account Balance: " + this.balance + "\n";
        return result;
    }
    
    /**
     * Method used which checks if two accounts are identical in all of their attributes.
     * Precondition: Both accounts exists which we are comparing.
     * Postcondition: The method will return a boolean of true or false if both accounts are identical or not.
     * 
     * @param Account otherAccount, account which we are comparing with, to see if they are equal.
     * @returns boolean, returns true or false if accounts are equivalent or not.
     */
    public boolean equals(Account otherAccount)
    {
        return(this.balance.equals(otherAccount.getbalance()) && this.name.equals(otherAccount.getname()) && this.id.equals(otherAccount.getId()));
    }
     
    /**
     * Method used to return the id of the account.
     * Precondition: There is an existing account with an id.
     * Postcondition: The method will return only the id of the account.
     * 
     * @return this.id, returns the String id of the account. 
     */
    public String getId()
    {
        return(this.id);
    }
    
    /**
     * Overriding the predefined compareTo Method, comparing 2 Account type objects. 
     * Precondition : the parameter o is  object of type Account
     * Postcondition : return 0 if this.id is the same as o.id, -1 if this.id < o.id, 1 if this.id > o.id.
     * Returns 2 if none of the options above.
     * 
     * @param Object o, object we are comparing to, of type Account
     * @return, 0, -1,1 or 2
     */
    @Override 
    public int compareTo(Object o)
    {
        //If the object is an instance of account.
        if (o instanceof Account) 
        {
          
            Account acc = (Account) o;
            return this.id.compareTo(acc.getId());
        } 
        else
        {
            return 2;
        }
    }
    
    /**
     * Sorting the accounts in the array.
     * Precondition: the sortsClass() already exists, and the bubbleSort method already exists.
     * Postcondition: The accounts will be sorted in an array in ascending order.
     * 
     * @param Comparable[] theArray, the array we wish to sort.
     * @param int n, n items in the array.
     */
    
    public void sortAccounts(Comparable[] theArray, int n)
    {
        //Creating a new sort object
        SortsClass sort = new SortsClass();
        
        //Calling the method of bubble sort on the new sort object.
        sort.bubbleSort(theArray, n);
        
    }
    
    /**
     * The method is used to get only the balance of the account.
     * Precondition: There is an account to get the balance from.
     * Postcondition: The method will return just the type Money balance from the existing account.
     * 
     * @return, returns the balance of type Money of the Account.
     */
    public Money getbalance()
    {
        return this.balance;
    }
    
    /**
     * The method is used to get only the name of the account.
     * Precondition: There is an account to get the name from.
     * Postcondition: The method will return just the type String name from the existing account. 
     * 
     * @return this.name, returns the name of the account of type String.
     */
    public String getname()
    {
        return this.name;
    }
    
    /**
     * The method is used to get only the overdraft maximum of the account if type checking.
     * Precondition: The account exists, and it is of type checking.
     * Postcondition: The method will return just the type Money overdraft maximum of the checking account, if regular account
     * it will return an overdraft maximum of 00.00
     * 
     * @return returnMoney, returns the overdraft maximum of the account if of type checking.
     */
    public Money getOM()
    {
        Money returnMoney = new Money(00, 00);
        return(returnMoney);
    }
    
    /**
     * Method used to get the total balance as a long.
     * precondition: There is an account with a balance.
     * postcondition: The method will return the total cents as a long data type.
     */
    public long getBalanceAsLong()
    {
        return this.balance.getTotalCents();
    }
    
    /**
     * method used to return the account type, of regular account
     * precondtion: There is an account which is of type regular.
     * Postcondtion: The method will return "r" as the account type.
     */
    public String returnAccType()
    {
        return "r";
    }
}