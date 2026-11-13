
/**
 * Class for candy object - holds a candy name, the quantity of that candy and the price per candy.
 * 
 */
public class Candy
{
    private String candyName;
    private int quantity;
    private double pricePerCandy;

    /**
     * Constructor for objects of class Candy
     */
    public Candy(String candyName, int quantity)
    {
        this.candyName = candyName;
        this.quantity = quantity;
        this.pricePerCandy = GetCandyPrice();
    }
    
    /**
     * Since each candy has a different price, this method assigns a specific price for each type of candy.
     * @params - none
     * @returns - the double of the price for a specific candy type.
     */
    public double GetCandyPrice()
    {
        double candyPrice = 0;
        
        //Using a switch case to assign different prices to different candy types.
        switch(this.candyName)
        {
            case "Swedish Fish":
                candyPrice = 9.99;
                break;
            case "M&Ms":
                candyPrice = 5.99;
                break;
            case "Chocolate hearts":
                candyPrice = 10.20;
                break;
            case "Gummy worms":
                candyPrice = 8.05;
                break;
            case "Variety Pack Haribos":
                candyPrice = 12.00;
                break;
            case "Banana Twizzlers":
                candyPrice = 11.02;
                break;
            case "Gummy Spaghetti":
                candyPrice = 5.00;
                break;
            default:
                candyPrice = 0.00;
        }
        
        return(candyPrice);
    }

    /**
     * Gets the name of the candy object.
     *
     * @param none
     * @return string of candy name.
     */
    public String getCandyName()
    {
        return this.candyName;
    }
    
    /**
     * Gets the quantity of the candy object.
     *
     * @param none
     * @return int of candy quantity
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * Sets the quantity of the candy object.
     *
     * @param integer of the new quantity to set it to. 
     * @return none.
     */
    public void setQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }
}
