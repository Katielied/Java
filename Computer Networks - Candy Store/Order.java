import java.io.*;
import java.util.*;


/**
 * Order object used by the client side, keeps track of each client's order, including the order name,
 * their candies bought, quantities of each candy and running total.
 *
 * @Katarina Liedbeck 
 */
public class Order  
{
    private List<Candy> candies;
    private double runningTotal;
    private String orderName;

    /**
     * Constructor for objects of class Message
     * Message object must have the name of the client and their desired response to a chat.
     */
    public Order(String orderName)
    {
        this.orderName = orderName;
        this.candies = new ArrayList<>();
        this.runningTotal = 0.0;
    }

    /**
     * Used to get the running total of the order.
     * Preconditions: none
     * Postconditions: Returns a double - running total.
     */
    public double getRunningTotal()
    {
        return this.runningTotal;
    }
    
    /**
     * Adds to the running total, used when client buys new candy.
     * Preconditions: double of the amount to add.
     * Postconditions: none.
     */
    public void addtoTotal(double priceToAdd)
    {
         this.runningTotal += priceToAdd;
    }
    
    /**
     * Adds a new candy object to their order.
     * Preconditions: Candy object of the new candy being added to their order.
     * Postconditions: none.
     */
    public void addCandy(Candy newCandy)
    {
        this.candies.add(newCandy);
    }
    
    /**
     * Used to print out a string of the clients order (displaying their order.)
     * Preconditions: None
     * Postconditions: Will print the order out as a string. 
     */
    public void displayOrder()
    {
        System.out.println("Order Name: " + this.orderName);
        System.out.println("-------------------------------------------------------------------------------");
        //Using printf to format the print statements, creating more readable output.
        //Printing headers.
        System.out.printf("%-20s %-12s %-12s %-12s%n", "Candy Name", "Quantity", "Price Each", "Total Price");
        //Looping through candies.
        for(int i=0; i<this.candies.size(); i++)
        {
            Candy candy = this.candies.get(i);
            String candyName = candy.getCandyName();
            int quantity = candy.getQuantity();
            double pricePer = candy.GetCandyPrice();
            double price = pricePer * quantity;
            
            //For each candy in their order, print their names, quantities, price per and total price for that quantity of candies.
            System.out.printf("%-20s %-12s %-12s %-12s%n", candyName, quantity, pricePer, price);
        }
        System.out.println("-------------------------------------------------------------------------------");
        //Printing the runnign total.
        System.out.println("Total Price: " + this.runningTotal+"\n");
    }
    
}
