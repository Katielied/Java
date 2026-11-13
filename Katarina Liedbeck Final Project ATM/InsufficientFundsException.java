
/**
 *
 * @Katarina Liedbeck
 * @version1
 */
class InsufficientFundsException extends RuntimeException
{
    
    //Extending the predefined RuntimeException
    /**
     * Constructor for objects of class InsufficientFundsException
     */
    public InsufficientFundsException(String s)
    {
        //String comes from calling class.
        super(s);
    }

}
