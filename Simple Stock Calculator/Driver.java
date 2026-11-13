import java.util.Scanner;

/**
 * Driver class of StockCalculator
 * Katarina Liedbeck
 * @version1 10/20/2023
 * This is the driver class for StockLedger
 * It should allow the user to buy and sell stocks, additionally allow to display the total Capital gain/loss of the user so far, and it total.
 * The user is allowed to quit at anytime.
 */
public class Driver
{
    public static void main()    
    {
        //Setting up a scanner object for the user's choice of the menu.
        Scanner userMenu = new Scanner (System.in);

        //Valid will be used to keep prompting the menu after each user decision.
        boolean valid = false;

        //User's choice variable.
        int user;

        //Creating a new object of Stock Ledger 
        StockLedger NewStockLedger = new StockLedger();

        //While the user has not chosen to quit, the menu will keep being prompted.
        while(!valid)
        {
            System.out.println("------------------------------------");
            System.out.println("Stock Calculator program menu,\nOPTIONS:\nBuy: type 1\nSell: type 2\nDiaply Total Realized Capital Gain/Loss so far: type 3\nQuit: type 4");
            System.out.println("------------------------------------");

            //Reading in the user's choice as an integer.
            String userOption = userMenu.nextLine();
            user = Integer.valueOf(userOption);

            //If the user's choice is 1, the buy method will be used.
            if(user == 1)
            {
                System.out.println("Option Chosen: Buy");
                System.out.println("------------------------------------");
                //Setting up a scanner object for user input for amount of shares to buy.
                Scanner buyAmount = new Scanner (System.in);
                System.out.println("How many shares would you like to buy?");
                
                //Retaining the shares the user wants to buy as an integer, called sharesToBuy.
                String buyShares = buyAmount.nextLine();
                int sharesToBuy = Integer.valueOf(buyShares);
                
                //Setting up a scanner object for user input for at what price they would like to buy at.
                Scanner buyPrice = new Scanner (System.in);
                System.out.println("At what price do you wish to buy them?");

                //Reading in the priceToBuy as a double.
                String sharePriceBuy = buyPrice.nextLine();
                double priceToBuy = Double.valueOf(sharePriceBuy);
                System.out.println("------------------------------------");

                //Checking validity of user inputs
                //If either the shares or the price is less than zero, no stock will be added.
                //Else, the NewStockLedger object will user the buy method, passing the parameter of the stock bought by the user.
                if (sharesToBuy < 0 && priceToBuy < 0)
                {
                    System.out.println("Please re-enter both your shares and price you would like to buy, as 0 or greater, returning to option menu");
                    
                }
                if (sharesToBuy < 0)
                {
                    System.out.println("Please re-enter your shares you would like to buy, as 0 or greater, returning to option menu");

                }
                
                else if (priceToBuy < 0)
                {
                    System.out.println("Please re-enter your price you would like to buy your shares at, as 0 or greater, returning to option menu");

                }
                else
                {
                    StockLot stocksToBuy = new StockLot(sharesToBuy, priceToBuy);
                    NewStockLedger.buy(stocksToBuy); 
                }
                
                //Setting valid to false, to show the menu options again.
                valid = false;

            }
            //If the user types in 2 at the option menu, they will choose to call upon the sell method.
            else if(user == 2)
            {
                System.out.println("Option Chosen: Sell");
                System.out.println("------------------------------------");

                //Scanning the amount of shares the user would like to sell as an integer.
                Scanner sellAmount = new Scanner (System.in);
                System.out.println("How many shares would you like to sell?");
                String sellShares = sellAmount.nextLine();
                int sharesToSell = Integer.valueOf(sellShares);
                System.out.println("------------------------------------");

                //Scanning the price at which the user would like to sell their stocks at, as a double.
                Scanner sellPrice = new Scanner (System.in);
                System.out.println("At what price do you wish to sell them?");
                String sharePriceSell = sellPrice.nextLine();
                double priceToSell = Double.valueOf(sharePriceSell);
                System.out.println("------------------------------------");

                //If both shares and price are less than zero, or either shares or price is less than zero, the action will not be done and the menu will be shown again.
                //Else, the stock will be crreated and passed through as a parameter for the method of sell.
                if (sharesToSell < 0 && priceToSell < 0)
                {
                    System.out.println("Please re-enter both your shares and price you would like to sell, as 0 or greater, returning to option menu");
                }
                else if (sharesToSell < 0)
                {
                    System.out.println("Please re-enter your shares you would like to sell, as 0 or greater, returning to option menu");
                }
                
                else if (priceToSell < 0)
                {
                    System.out.println("Please re-enter your price you would like to sell your shares at, as 0 or greater, returning to option menu");
                }
                else
                {
                    StockLot stocksToSell = new StockLot(sharesToSell, priceToSell);
                    NewStockLedger.sell(stocksToSell); 
                }

                //Setting valid to false, in order for the menu to be prompted again.
                valid = false;

            }
            //If the user types in 3, their total gain or loss so far will be displayed.
            else if(user == 3)
            {
                System.out.println("Option Chosen: Display total capital gain");
                System.out.println("------------------------------------");

                //Calling on the method of computeTotalGainLoss().
                System.out.println("Your total realized capital gain/loss from all sell transactions so far is:\n$" + NewStockLedger.computeTotalGainLoss());
                
                //Setting valid to false to prompt the menu again.
                valid = false;
            }
            //If the user types in 4, they have chosen to quit, and the program will end. 
            else if(user == 4)
            {
                System.out.println("Option Chosen: Quit");
                System.out.println("Program Ended");
                System.out.println("------------------------------------");

                //Setting valid to true, so that the program will stop running and the menu will not be prompted again.
                valid = true;
            }
            //If the user does not type any of the four options, it will be asked to retype their choice again.
            else
            {
                System.out.println("Invalid input, please choose between one of the options displayed.");
                valid = false;
            }
        }

        
    }
}
