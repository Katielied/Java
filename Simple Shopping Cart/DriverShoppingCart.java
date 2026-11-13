
/**
 * Driver class of ShoppingCart.
 * This class will add items into an Item array, add these items into a shoppingCart, 
 * finally test out all methods found in ShoppingCart class.
 *
 *Katarina Liedbeck
 * @version1 09/26/23
 */
public class DriverShoppingCart
{
    public static void main (String[] args)
    {
        //Create some Items to add
        Item[] items = {new Item("Sofa", 4055),
                        new Item("Pillow", 1188),
                        new Item("Water Bottle", 1099),
                        new Item("Lamp", 1299)};

        
        //Creating a new object of type shoppingCart.                
        ShoppingCart shoppingCart = new ShoppingCart();
        
        //Addign items into the shoppingCart.
        for (int index = 0; index < items.length; index++)
        {
            Item nextItem = items[index];
            shoppingCart.addItem(nextItem);
            System.out.println("Added item to shopping cart: " + nextItem + "\n"); //Prints items added to cart.
        }
        System.out.println();
        
        //Testing the add multiple method.
        //Adding 5 water bottles.
        System.out.println("Testing addMultiple method, adding 5 of water bottle... \n");
        shoppingCart.addMultiple(items[2], 5); 
        System.out.println("addMultiple Method: added 5 of water bottle to shopping cart \n");
        System.out.println();

        //Testing the add item method.
        //Adding a second sofa
        System.out.println("Testing addItem method, adding another sofa...\n");
        shoppingCart.addItem(items[0]);
        System.out.println("addItem method: added " + items[0] + "\n");
        System.out.println();
        
        //Testing removing a specific item method
        //Removing specific sofa added (1 sofa) 
        System.out.println("Testing removeSpecificItem method, removing the added sofa...\n");
        shoppingCart.removeSpecificItem(items[0]);
        System.out.println("removeSpecificItem Method: removed " + items[0] + "\n");
        System.out.println();

        //Testing removing a random item method.
        //Removing a random item
        System.out.println("Testing removeItem method, removing a random item...\n");
        Item itemRemoved = shoppingCart.removeItem();
        System.out.println("removeItem Method: removed " + itemRemoved + "\n");
        System.out.println();

        
        //Testing the checkBudget method.
        //For this we will pass 60 dollars as the budget to test.
        //Budget should not be enough, and therefore items should be removed in print statements.
        System.out.println("Testing checkBudget method, using parameters of 6000, (60.00) dollars...\n");
        shoppingCart.checkBudget(6000);
        System.out.println();

        
        //Testing the checkout method 
        System.out.println("Testing checkout Method...");
        shoppingCart.checkout();
        
    }
}
