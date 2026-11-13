import java.util.NoSuchElementException;

/**
 * Class of StockLedger, blueprint for Driver to Use.
 *
 * @Katarina Liedbeck
 * Helped by Tyler Reid
 * @version1 10/31/2023
 */
public class StockLedger
{
    DequeInterface<StockLot> stocks;
    private int totalCapitalGainLoss;

    /**
     * Constructor for the object of StockLedger.
     * Preconditions: totalCapitalGainLoss has already been declared as a private int, and stocks has already been declared.
     * Postconditions: Will create a new StockLedger object.
     * Parameters: none
     * Returns: none
     */
    public StockLedger()
    {
        stocks = new LinkedDeque();
        totalCapitalGainLoss = 0;
    }

    /**
     * Buy method - Used for the user to buy stocks, with their own shares and price wanted.
     * Preconditions: the user input of the stock they wish to buy has already been validated, getShares() getBuyPrice and addToBack() methods have already been created 
     * and declared.
     * Postcondition: The method will add to the back of the linkedDeque of stocks, meaning the user will buy the stock which they wish.
     * Parameters: User input of an object of a StockLot, consists of the buy price and the shares they wish to buy.
     * Returns: none.
     */
    public void buy(StockLot stockToBuy)
    {
        //Using the .getShares and .getBuyPrice methods in order to recieve the price and shares of the stock they wish to buy.        
        int sharesToBuy = stockToBuy.getShares();
        double priceToBuy = stockToBuy.getBuyPrice();
        
        //Using the .addToBack method in order to add the stock to the linked Deque of stocks.
        stocks.addToBack(stockToBuy);       
        
        //Printing a message to the user of how many shares they bought and at what price.
        System.out.println("You bought " + sharesToBuy + " shares at $" + priceToBuy + "\n");
    }

    /**
     * Sell method - Used for the user to sell their own amount of shares and at their own price chosen.
     * Preconditions: The Stock they wish to sell has already been validated, and the getShares, getBuyPrice, getFront, setShares, and removeFront methods have 
     * already been created and declared.
     * Postconditions: The method will sell the wanted amount of shares at the wanted price by the user, it will continue selling until their shares reach 0, if their shares
     * reach 0 and a stockLot which has been partially sold does not reach zero, we will set the shares of this stock to its new shares. It a stoctLot reached zero, it will
     * be removed from the linkedDeque of Stocks.
     * If there are not enough shares in their Stocks, it will sell a part of what the user wants to sell and display that to the user.
     * Parameters: Taked in a parameter of a StockLot, including the user's input of the shares and price which they wish to sell at.
     * Returns: None.
     */
    public void sell(StockLot stockToSell)
    {
        //Creating a double variable of the current capital gain or loss and setting it equal to zero.
        double currentCapitalGainLoss = 0;

        //retrieving the user's shares and prices by using the getShares and getBuyPrice methods.
        int shares = stockToSell.getShares();
        double price = stockToSell.getBuyPrice();

        //Using printShares as a temp variable to keep the original amount of shares they wish to buy.
        int printShares = shares;
    
        
        //Creating a try catch, in order to catch if the user types in an amount of shares which they do not have.
        try
        {
            //While the user's shares has not all been sold,
            while(shares > 0)
            {
                //Creating a double of the stocksPrice in the stock linkedDeque, and an in of the stockShares in the stock linkedDeque.
                StockLot sellingStock = stocks.getFront();
                
                double stockPrice = sellingStock.getBuyPrice();
                int stockShares = sellingStock.getShares();

                //If the stocksShares in the deque is greater than the amount they wish to buy, meaning we are selling part of a lot.
                if (stockShares > shares)
                {
                    //Creating a new int of newShares 
                    int newShares = stockShares - shares;
                    
                    //Getting the front and setting its new shares
                    sellingStock.setShares(newShares);

                    //Calculate gain/loss
                    currentCapitalGainLoss += (price - stockPrice) * shares;

                    //Setting the users shares to 0
                    shares = 0;
                    //System.out.println("selling part of lot");
                }
                //If the user's  shares are greater than the stock, or equal to, we will be selling the entire lot and or more.
                else
                {
                    //Calculate gain/loss
                    currentCapitalGainLoss += (price - stockPrice) * stockShares;
                    
                    //Subtracting the stockShares from the amount of shares the user wants to buy.
                    shares -= stockShares;
                    
                    //Removing the stock.
                    stocks.removeFront();
                    //System.out.println("selling entire lot");
                }
            }

            System.out.println("You sold " + printShares + " shares at $" + price + "\n");

            //Determining wether the sale was a gain or loss.
            if (currentCapitalGainLoss > 0)
            {
                System.out.println("Your total capitalized gain for this sale: $" + currentCapitalGainLoss);
            }
            else
            {
                System.out.println("Your total capitalized loss for this sale: $" + currentCapitalGainLoss);
            }
            
        }
        //If shares are still greater than 0, and there are no shares left in the stocks linkeddeque to sell.
        catch (Exception error)
        {
            if(error instanceof NoSuchElementException)
            {
                //Creating a variable of shares which are not sold.
                int notSellable = printShares - shares;
                
                //If some of the user's stocks were sold but not all, and there were stocks to begin with in the ledger. 
                //Shares left in stock will always be zero here, as an exception is thrown because there arent enough shares left to sell.
                if (shares < printShares)
                {
                    System.out.println("You were only able to sell part of your stocks\nshares sold: " + notSellable + " out of " + printShares + "\n" + "shares left in stocks: 0");
                    if (currentCapitalGainLoss > 0)
                    {
                        System.out.println("Your total capitalized gain made from this sale: $" + currentCapitalGainLoss);
                    }
                    else
                    {
                        System.out.println("Your total capitalized loss for this sale: $" + currentCapitalGainLoss);
                    }
                    
                }
                //If none of the stocks were sold, because there are no available stocks to sell at all in the ledger.
                else 
                {
                    System.out.println("No Stocks available to sell, was not able to sell any of your wanted shares.");
                }
                
            }
        }
        
        //Adding or subtracting to the total Capital Gain/Loss.
        totalCapitalGainLoss += currentCapitalGainLoss;
    }
        
    /**
     * Computing the total gain or loss 
     * Preconditions: The totalCapitalGainLoss should be updated during the program.
     * Postconditions: Returns the total capital gain or loss.
     * Parameters: none
     * Returns: returns the total capital gain or loss.
     */
    public double computeTotalGainLoss()
    {
        return(totalCapitalGainLoss);
    }
}
