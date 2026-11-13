/** 
 * A blueprint for Money objects...
 * 
 * @Katarina Liedbeck 
 * @version1 04/17/2023
 */
public class Money
{
    // attributes = state variables
    private long totalCents;

    /**
     * Constructor: initializes all attributes (i.e. totalCents)
     * based on the given dollars and cents.
     *
     * @param theDollars the number of dollars
     * @param theCents   the number of cents
     */
    public Money(int theDollars, int theCents)
    {
        this.totalCents = theDollars * 100L + theCents;
    }

    /**
     * Constructor: initializes all attributes (i.e. totalCents)
     * based on the given total cents.
     *
     * @param theCents  the total number of cents
     */
    public Money(long theCents)
    {
        this.totalCents = theCents;
    }
    
    /**
     * Method used to get the total cents as a long.
     * precondition: There is a money object created,
     * postcondition: Returns the total amount in the money object as a long of cents.
     */
    public long getTotalCents()
    {
        return this.totalCents;
    }
    
    /**
     * Method used to get the dollars.
     * precondition: There is a money object created,
     * postcondition: Returns the total dollars in the money object.
     */
    public int getDollars()
    {
        return (int) (this.totalCents / 100);
    }
    
    /**
     * Method used to get the cents.
     * precondition: There is a money object created,
     * postcondition: Returns the cents in the money object.
     */
    public int getCents()
    { 
        return (int) (this.totalCents % 100);
    }
 
    /**
     * add: adds two money values
     * 
     * Precondition: two Money amounts are created and valid
     * Postcondition: the amount in this Money object is added to the Money amount given as parameter; the result is returned.
     * Neither the calling object nor the parameter are changed.
     * 
     * @param a Money, the Money amount to add to the calling object (this)
     * @return Money, the sum
     */
    public Money add (Money theMoney)
    {
        return new Money (this.totalCents + theMoney.totalCents);
    }
    
    /**
     * Method subtracts two money values.
     * Precondition: two money amounts are created and valid.
     * Postcondition: the amount in theMoney object  given as a parameter is subtracted from this.totalcents.
     * 
     * @param Money theMoney, the money amount to subtract from the calling object.
     * @return Money, the new money after subtracting theMoney.
     */
    public Money subtract (Money theMoney)
    {
        return new Money (this.totalCents - theMoney.totalCents);
    }
    
    /**
     * Method which compares two money values.
     * Precondition: two money values exist and are valid.
     * Postcondition: If calling object (this) is greater than other money object (passed in parameter) then the method will return 1
     * If calling object is less than other money object it will return -1, and if they are equal it will return 0.
     * 
     * @param Money other, other money object we are comparing calling object to.
     * @return comparedVal, int value of 0,1 or -1.
     */
    public int compareTo (Money other)
    {
        int comparedVal = 0;
        
        if(this.totalCents > other.totalCents)
        {
            comparedVal = 1;
        }
        else if(this.totalCents < other.totalCents)
        {
            comparedVal = -1;
        }
        else 
        {
            comparedVal = 0;
        }
        
        return comparedVal;
    }
    
    /**
     * toString: return String representation of this Money object
     * Precondition: this Money object is valid
     *
     * @return a String representation of this object
     */
    public String toString()
    {
        String result = "$" + this.getDollars() + "."; 
        
        if (this.getCents() < 10) {
            result += "0";
        }
        
        result += this.getCents();
        return result;
    }
    
    /**
     * equals: compare the status of two money objects.
     * 
     * @param other  a Money object
     * @return true if calling object (this) is in the same state as the Money object received as a parameter, and false otherwise.
     */
    public boolean equals (Money other)
    {
        return (this.totalCents == other.totalCents);    
    }
}
