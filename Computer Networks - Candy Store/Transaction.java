import java.io.Serializable;

/**
 * Transaction object being passed between the client and the server.
 *
 */
public class Transaction implements Serializable
{
    private String transactionName;
    private int quantity;
    private String candyName;
    private String username;
    
    //For enhanced version - add a name of the client to the transaction.
    
    /**
     * Constructor for objects of class Transaction
     */
    public Transaction(String transactionName, String username)
    {
        this.transactionName = transactionName;
        this.username = username;
    }

    /**
     * Gets the name of the transaction object.
     *
     * @param none
     * @return string of transaction name.
     */
    public String getTransName()
    {
        return this.transactionName;
    }
    
    public String getUsername()
    {
        return this.username;
    }
}
