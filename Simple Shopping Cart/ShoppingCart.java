
/**
 * Blueprint for shoppingCart 
 *
 * @Katarina Liedbeck 
 * @version1
 */
public class ShoppingCart
{
    // instance variables
    private int tot;
    private BagInterface<Item> bag;

    /**
     * default Constructor for objects of class shoppingCart
     * Setting total to 0, creating an arrayBag of type bag.
     */
    public ShoppingCart()
    {
        tot = 0;
        bag = new ArrayBag<>();
        
    }

    /**
     * Creating shoppingcart as a bagInterface using type Item. 
     * Setting total to 0, creating an empty bag.
     */
    public ShoppingCart(BagInterface<Item> aBag)
    {
        tot = 0;
        bag = aBag;
        
    }
    
    /**
     * Method used to add an item.
     * parameters: item object of an item to add.
     * Preconditions: uses the methods of getPrice() and add().
     * postcondition: Returns a boolean of true if item is added, otherwise false.
     */
    public boolean addItem(Item anItem)
    {
        tot += anItem.getPrice(); //using method getPrice()  and calling it on anItem to add to total.
        return(bag.add(anItem)); 
    }
    
    
    /**
     * Method to add multiple of an item to the bag.
     * parameters: item object (anItem, what you want to add), integer of a quantity (how many).
     * precondition: uses the method of addItem().
     * Postcondition: returns a true boolean if added successfully, otherwise returns false.
     * 
     */
    public boolean addMultiple(Item anItem, int quantity)
    {
        boolean added = false; 
        
        //Creating a for loop to add the amount of the specific item
        for(int i = 0; i <= quantity; i++)
        {
            added = addItem(anItem); 
        }
        
        return (added); //boolean of added will return true when executed using the addItem() method.
        
    }
    
    /**
     * Method to remove an item from bag.
     * parameters: none.
     * preconditions: Using a pre made method of remove(), the bag is not empty.
     * Postcondition: returns the Item removed.
     */
    public Item removeItem()
    {
        Item item = bag.remove();
        tot -= item.getPrice();

        return (item);
    }
    
    /**
     * Method to remove a specific item.
     * parameters: Item object of an item, the specific item you want to remove.
     * Preconditions: the bag is not empty, the item exists in the bag.
     * Postcondition: Returns the boolean of true if successful, false otherwise.
     */
    public boolean removeSpecificItem(Item anItem)
    {
        tot -= anItem.getPrice();
        return (bag.remove(anItem));
    }
    
    /**
     * Method for checking out, "scans" items and shows the total.
     * parameters: none.
     * preconditions: using the method isEmpty(), remove() and toString()
     * postconditions: returns the total amount of money.
     */
    public int checkout()
    {
        System.out.println("Checking out your items...");
        System.out.println();
        while(!bag.isEmpty())
        {
            Item item = bag.remove();
            System.out.println(item.toString());
        }
        
        System.out.println("Total: " + "$" + tot/100 + "." + tot%100 + "\n");
        System.out.println();

        return(tot);
    }
    
    /**
     * Method used to checkBudget
     * parameters: integer of user budget.
     * preconditions: uses methods of remove(), getPrice() and toString().
     * postconditions: returns the new total after checking if over budget, if over budget: prints which items have been removed, 
     * prints new total after items are emoved. If under budget, no changes to total or shoppingCart will occur.
     */
    public int checkBudget(int budget)
    {
        System.out.println("Your budget: " + "$" + budget/100 + "." + budget%100 + "\n");
        System.out.println("Your current shopping cart total: " + tot/100 + "." + tot%100 + "\n");
        System.out.println();

        while (tot > budget)
        {
            Item item = bag.remove();
            System.out.println("Budget was not enough, item removed: " + item.toString() + "\n");
            tot -= item.getPrice();
        }
        System.out.println("Budget is enough!");
        System.out.println();

        System.out.println("Current total: " + "$" + tot/100 + "." + tot%100 + "\n");
        System.out.println();

        return tot;
    }
    
    
}
