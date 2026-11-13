import java.util.*;

/**
 * Inventory object to hold all candies in the shop, and their quantities available. 
 *
 */
public class Inventory
{
    private List<Candy> candies;

    /**
     * Constructor for objects of class Inventory
     */
    public Inventory(List<Candy> candies)
    {
        this.candies = candies;
    }

    /**
     * Method used to update the inventory - when client wants to buy a candy in the inventory.
     * using keyword synchronized, ensuring only one thread can update inventory at a time.
     * @param - String candyName the client wants to buy, and the int quantity of how many of that candy they want to buy.
     * @return - boolean if the inventory was updated or not.
     */
    public synchronized boolean updateInventory(String candyName, int quantity)
    {
        boolean success = false;
        
        //loop through arraylist
        for(int i = 0; i < this.candies.size(); i++)
        {
            Candy candy = this.candies.get(i);
            //If the candy in loop is equal to the searched for candy
            if(candy.getCandyName().equals(candyName))
            {
                //If quantity of that candy is available
                if(candy.getQuantity()>=quantity)
                {
                    //Set new quantity to the inventory.
                    int newQuantity = candy.getQuantity() - quantity;
                    candy.setQuantity(newQuantity);
                    success = true;
                }
                else //Not enough of the candy available in the inventory.
                {
                    success = false;
                }
            }
        }
         
        //Return boolean.
        return success;
    }
    
    /**
     * Method used to display the inventory (toString).
     * @param - None.
     * @return - String of the inventory.
     */
    public String displayInventory()
    {
        String inventoryToString = "";
        //Using .format() to make sure the string is more readable when printed.
        inventoryToString += String.format("%-20s %-10s %-10s%n", "Candy Name", "Quantity", "Price Each");
        inventoryToString += "-----------------------------------------------------------\n";
        
        //Looping through the candies.
        for(int i = 0; i < this.candies.size(); i++)
        {
            Candy candy = this.candies.get(i);
            String candyName = candy.getCandyName();
            int quantity = candy.getQuantity();
            double price = candy.GetCandyPrice();
            
            //Adding to the String.
            inventoryToString += String.format("%-20s %-10d $%-9.2f%n", candyName, quantity, price);
        }
        
        //Returning the new String.
        return inventoryToString;
    }
}
