
/**
 * Item.java - implementation of an Item to be placed in ShoppingCart
 */
public class Item
{
    private String name;
    private int price;      //in cents
    
    //Constructor
    public Item(String n, int p)
    {
        name = n;
        price = p;
    }
    
    public boolean equals(Object other)
    {
        if ((this.getClass() != other.getClass()) || other == null)
            return false;
            
        Item otherItem = (Item) other;
        return this.name.equals(otherItem.name) && this.price == otherItem.price;
    }
    
    //displays name of item and price in properly formatted manner
    public String toString()
    {
        return name + ", price: $" + price/100 + "." + price%100;
    }
    
    //Getter methods
    public int getPrice()
    {
        return price;
    }
    
    public String getName()
    {
        return name;
    }
}