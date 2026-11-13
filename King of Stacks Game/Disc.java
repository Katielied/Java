
/**
 * Object Disc class.
 * Katarina Liedbeck
 * Version 1, 10/08/2023
 */
public class Disc
{
    private int playerNumber;
    
    /**
     * Constructor for the disc.
     * It will be assigned to a player, using their number.
     */
    public Disc(int num)
    {
        playerNumber = num;
    }
    
    /**
     * Method which gets the player number.
     * No parameters will be passed in this method.
     * Precondition: There is an assigned playernumber.
     * Postcondition: The method will return the player number.

     */
    public int getPlayerNum()
    {
        return(playerNumber);
    }
}
