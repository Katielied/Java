import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.Character;

// Driver class for Bank project

/**
 * Katarina Liedbeck
 * Main Driver ATM 
 * Version 1, last updated 05/18/2023
 */
public class ATM
{
   /**
    * Driver/main class for ATM Final project.
    * User should be able to choose between deposit, withdraw, checkbalance, display account and exit
    * User will be asked if they want to do another action, and how much they would like to withdraw or deposit
    * The accounts will be read froma  text file and updated in the same text file.
    * The withdraw method will work differently wether or not its a checking or regular account.
    */ 
    public static void main (String[] args)
    {
        try
        {
      
            //create a new dialog box
            IOHandlerInterface ioh = new IOHandlerDialog();
    
            //Printing out what the program should output to the user
            ioh.put("Reading: Bank name, Number of accounts, names, ids ,balances\n");
      
      
            // Read data from a file into a Bank.
            // Each line of the file has info for one account.  
            //Reading from a created text file saved in the same folder as BlueJ project.
            BankInterface myBank = readFromFile("input.txt");
      
            //Setting the userID to the read userID from the user.
            String userID = readUserID(ioh);
      
            //Creating a boolean variable of valid, to check if id is correct.
            boolean valid = isValid(myBank, userID);
      
            //Creating an if statement
            //If valid is true, it will say welcome and also print the account with the given id.
      
            if (valid == true)
            {
                ioh.put("welcome");
                boolean anotherAction = false;
            
                do
                {
                    //Choices for the drop down dialog box.
                    String[] choices = {"deposit", "withdraw", "check balance", "Display my Account", "exit"};
                    String transaction = ioh.getFromList("Transactions", "Choose a transaction.", choices);
                    
                    ioh.put("Transaction selected: " + transaction);
                
                    //Creating an if statement for each act of transaction which the user chooses.
                    if (transaction == "deposit")
                    {
                        String userDeposit = readDepositAmount(ioh); 
                
                        //Using the method of split to get the value of the dollars and cents.
                        String[]stringMoney = userDeposit.split("[.]",0);
                
                        int dollars = Integer.valueOf(stringMoney[0]);
                        int cents = Integer.valueOf(stringMoney[1]);
                        Money depositAmount = new Money(dollars, cents); 

                        //depositing into the account using the userID entered and their deposit amount entered,
                        myBank.deposit(userID, depositAmount);
                        ioh.put("Your account with your new balance is: " + "\n" + myBank.search(userID));
                    
                        //Update new output text file
                        myBank.writeTextToFile("output.txt");
                        
                        //ask to do another action.
                        anotherAction = askAnotherAction(ioh);
                    }
                    else if (transaction == "withdraw")
                    {
                        try
                        {
                            String accountType = "";
                            
                            //Reading the amount to withdraw from the user.
                            String userWithdraw = readWithdrawAmount(ioh); 
                            
                            //Using the method of split to get the value of the dollars and cents.
                            String[] stringMoney = userWithdraw.split("[.]",0);
                
                            int dollars = Integer.valueOf(stringMoney[0]);
                            int cents = Integer.valueOf(stringMoney[1]);
    
                            Money withdrawAmount = new Money(dollars, cents); 
                            
                            //If the account is of type regular, and the balance is less than the withdraw amount, it will throw an insufficient funds exception.
                            if(myBank.search(userID).getbalance().compareTo(withdrawAmount) == -1 && myBank.search(userID).returnAccType() == "r")
                            {
                                throw new InsufficientFundsException("Insufficient Funds.");
                            }
                            
                            //If Account type is checking
                            //If regular account balance is more than the withdraw amount or equal to
                            myBank.withdraw(userID, withdrawAmount);
                            
                            //Displaying the new balance.
                            ioh.put("Your account with your new balance is: " + "\n" + myBank.search(userID));
                            
                            //Write to the new updated output text file.
                            myBank.writeTextToFile("output.txt");
                            
                            //Ask user to do another action .
                            anotherAction = askAnotherAction(ioh);  
                        }
                        catch(InsufficientFundsException ife)
                        {
                            //If the withdraw amount goes past the amount allowed to withdraw.
                            ioh.put("Insufficent Funds, you do not have enough money in your account.");
                            anotherAction = askAnotherAction(ioh);  
                        }
                    }
                    else if (transaction == "Display my Account")
                    {
                        String accountType = "";
                    
                        //Checking to see if acount is of type regular or checking .
                        if(myBank.search(userID) instanceof Account)
                        {
                            accountType = "regular";
                        }
                        if(myBank.search(userID) instanceof Checking)
                        {
                            accountType = "checking";
                        }
                    
                        //Creating the string of the account and account type.
                        String returnString = ("\n" + myBank.search(userID) + "\n" + "Account Type: " + accountType);
                        
                        //If the account type is a checking, the overdraft Maximum will be added to the display .
                        if(accountType.equals("checking"))
                        {
                            returnString += "\n" + "OverDraft Maximum: " + myBank.search(userID).getOM();
                        }
                        ioh.put(returnString);
                        
                        //Asking user to do another action.
                        anotherAction = askAnotherAction(ioh);
                    }
                    else if (transaction == "check balance")
                    {
                        //Using the search and get balance method to recieve the user's balance.
                        ioh.put(myBank.search(userID).getbalance() + "");
                                       
                        //Asking the user to do another action.
                        anotherAction = askAnotherAction(ioh);
                    }
                    else if (transaction.equals("exit"))
                    {                           
                        ioh.put("Thank you, goodbye");
                        //End of Program .
                        break;
                    }
                
                    //If user says "no" to another action, the program will end.
                    if (anotherAction == false)
                    {
                        ioh.put("Thank you, goodbye");
                    }
                
                } while (anotherAction == true); //End of do while loop.
            }  //End of if block
        }    // end of try block.   
      
        catch (IOException ioe) 
        {
            System.out.println("IOException in main: " + ioe.getMessage()); 
            ioe.printStackTrace();
        } // end catch
        catch (Exception e) 
        {
            System.out.println("Exception in main: " + e.getMessage());
            e.printStackTrace();
        } // end catch
   } // end main

   /**
    * readFromFile: 
    * Method which reads the accounts from a file 
    * precondition: a file exists with accounts, and are formatted correctly, inputmanager class is created, add account method is created.
    * posticondition: Returns the bank with the read from file accounts .
    * 
    */
    public static BankInterface readFromFile (String fileName) throws IOException
    {
        // Creata a bank.
        BankInterface myBank = new Bank();
     
        // Open a file for reading.
        Scanner inputSource = new Scanner (new File(fileName));
    
        // while there are more tokens to read from the input source...
        while (inputSource.hasNext()) 
        {

            // Read one line of input from the file into an Account object
            Account acct = InputManager.readOneAccountFrom (inputSource);
        
            // Store the account info in the bank.
            myBank.addAcc(acct);
        
        } // end while

        return myBank;
    } // end readFromFile

    /**
     * readUserID, reads the id of the user.
     * precondition: equals method is already created, there are ID's which match accounts in the bank .
     * postcondition: returns the userID if entered correctly, if the ID is invalid the user will be asked to enter their ID again .
     * @param IOHandlerInterface ioh
     * @return String id read from user.

    */
    public static String readUserID(IOHandlerInterface ioh)
    {
        boolean returnVal = false;
        String user = "";
        
        do
        {
            user = ioh.get("Please enter your ID:");
            
            if(user.equals("0") || user.equals("1") || user.equals("2") || user.equals("3") || user.equals("4") || user.equals("5") || user.equals("6") || user.equals("7"))
            {
                returnVal = true;
            }
            
            else
            {
                ioh.put("Incorrect id, please try again");
            }
        } while (returnVal == false);
        
        return user;
    }
   
    /**
     * Method used to read the deposit amount from user.
     * Checks if user's deposit amount is valid 
     * If user input format is invalid, user will be asked to input amount of money again.
     * precondition : user has chosen the transaction of deposit and their ID is entered and validated .
     * postcondition : the method will return the calidated amount of money which the user wants to deposit into their account .
     * @returns the String user's amount of deposit money 
     * @param IOHandlerInterface ioh
     */
    public static String readDepositAmount(IOHandlerInterface ioh)
    {
        boolean returnVal = false;
        String user = "";
        
        do
        {
            user = ioh.get("How much would you like to deposit? (Please enter numbers in format '00.00'");
            
            //Checking that the length of the money money is atleast 5.
            if(user.length() >= 5)
            {
                String[] split = user.split("[.]", 0);
                boolean check1 = false;
                boolean check2 = false;
                
                //Creating 2 for loops which check if the money amnounts are digits.
                // the do while loop will continue until the user enters a valid deposit amount of Money .
                for(int i = 0; i < split[0].length(); i++)
                {
                    if(Character.isDigit(split[0].charAt(i)))
                    {
                        check1 = true;
                    }
                    
                    else
                    {
                        check1 = false;
                    }
                }
                
                for(int i = 0; i < split[1].length(); i++)
                {
                    if(Character.isDigit(split[1].charAt(i)))
                    {
                        check2 = true;
                    }
                    else
                    {
                        check2 = false;
                    }
                }
                
                if (check1 == true && check2 == true)
                {
                    returnVal = true;
                } 
            }
            else
            {
                returnVal = false;
                ioh.put("Invalid format, please enter in format '00.00'");
            }
        } while(returnVal == false);
        
        return user;
    }
    
    /**
     * Method used to read the withdraw amount from user.
     * Checks if user's withdraw amount is valid 
     * If user input format is invalid, user will be asked to input amount of money again.
     * precondition : The user has chosen the transaction withdraw, and their id is entered and validated . 
     * @returns the String user's amount of withdraw money 
     * @param IOHandlerInterface ioh
     */
    public static String readWithdrawAmount(IOHandlerInterface ioh)
    {
        boolean returnVal = false;
        String user = "";
        
        //Creating 2 for loops which check if the money amnounts are digits.
        // the do while loop will continue until the user enters a valid withdraw amount of Money .
        
        do
        {
            user = ioh.get("How much would you like to withdraw? (Please enter numbers in format '00.00'");
            
            if(user.length() >= 5)
            {
                String[] split = user.split("[.]", 0);
                boolean check1 = false;
                boolean check2 = false;
                
                for(int i = 0; i < split[0].length(); i++)
                {
                    if(Character.isDigit(split[0].charAt(i)))
                    {
                        check1 = true;
                    }
                    else
                    {
                        check1 = false;
                    }
                }
                
                for(int i = 0; i < split[1].length(); i++)
                {
                    if(Character.isDigit(split[1].charAt(i)))
                    {
                        check2 = true;
                    }
                    else
                    {
                        check2 = false;
                    }
                }
                
                if (check1 == true && check2 == true)
                {
                    returnVal = true;
                } 
            }
            else
            {
                returnVal = false;
                ioh.put("Invalid format, please enter in format '00.00'");
            }
        } while(returnVal == false);
        return user;
    }
    
    
    /**
     * *isValid method:
     * FoundAccount is set to null
     * if account id is valid it will return true
     * precondition: the id is entered by the user already, there is a bank which exists with accounts attached to IDs. 
     * postcondition : The method vill validate the user id, and return true if the account is found and if the account is not found it will return false.
     * @param BankInterface bank
     * @param String id
     */
    public static boolean isValid(BankInterface bank, String id)
    {
        //Setting foundAccount to null
        Account foundAccount = null;
        
        //Using the search method to find account
        foundAccount = bank.search(id);
        
        //Declaring the accountFound to a boolean
        boolean accountFound;
        
        if (foundAccount == null)
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
   
    /**
     * Method used to ask if the user wants to do another action.
     * precondition: The user has just completed an action, the equals method exists.
     * postcondition : The method will return the users choice of yes or no, implying wether or not they want to do another action.
     * If the user types neither yes or no, it will continue asking if the user wants to do another action.
     * @param IOHandlerInterface ioh
     * @return userChoice 
     */
    public static boolean askAnotherAction(IOHandlerInterface ioh)
    {
        boolean userChoice = false;
        boolean valid = false;
        
        do
        {
            //Ask the user if they would like to do another action.
            String user = ioh.get("Would you like to do another action? (Please type 'yes' or 'no'");
            
            //If user types yes or no.
            if(user.equals("yes") || user.equals("no"))
            {
                valid = true;
                if (user.equals("yes"))  
                {
                    return(true);
                }
                
                if(user.equals("no"))
                {
                    return(false);
                }
            }
            //If user types in invalid response.
            else
            {
                valid = false;
                ioh.put("Invalid data, please type 'yes' or 'no'");
            }
        }
        while (valid == false);        
        return userChoice;
    }
}