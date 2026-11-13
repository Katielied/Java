import java.util.List;
import java.util.Random;
import java.util.EmptyStackException;

/**
 * Main blueprint for all the methods of the KingOfStacks game.
 * This should work with either type of stack: array, linked or even vector.
 * @Katarina liedbeck Version 1 10/08/2023
 * Helped by Chris Sciortino
 */
public class KingOfStacks
{
    //Creating the different stacks, each having objects of the disc class.
    StackInterface<Disc> stack1;
    StackInterface<Disc> stack2;
    StackInterface<Disc> stack3;
    
    //Declaring the player 1 and player 2 scores as private integers.
    private int p1Score;
    private int p2Score;
    
    //Declaring the current round and total round as private integers.
    private int currentR;
    private int totR;
    
    /**
     * constructor for the KingOfStacks object.
     * Initializing the total rounds to the user input of turns
     * Initializing current rounds to 1
     * Initializing players' scores to 0 
     * Creating 3 new stacks
     */
    public KingOfStacks(int turns)
    {
        //Initializing the total rounds to the amount of turns which the user will want to play 
        totR = turns;
        
        //Setting the current rounds to 1 
        currentR = 1;
        
        //Setting the players' score to 0
        p1Score = 0;
        p2Score = 0;
        
        //Creating new stacks.
        //Creating a stack of each, array, linked and vector as this should work with either type.
        stack1 = new ArrayStack();
        stack2 = new LinkedStack();
        stack3 = new VectorStack();
        
        
    }
    
    /**
     * Method used to play the full game 
     * Method plays MULTIPLE rounds
     * Preconditions: the three stacks are already initialized and declared, player 1 and player 2 scores are also already declared and initialized.
     * clearGame method, checkScore and playRound have already been created.
     * Postconditions: The method will play the full game with the input of the user's amount of rounds, it will check and update The scores of the players. 
     * The method does not return anything.
     */
    public void playGame()
    {
        //Printing out a statement to let the user know they are beginning to play the game.
        System.out.println("King of Stacks game is being played!");
        
        //Using the created clearGame method in order to "restart" the game.
        clearGame();
        
        //Creating a for loop which will play 1 round and add to the current rounds, calling the playRound method.
        //It will stop at the desired user input of total rounds they would like to play.
        for (int i = 1; i <= totR; i++)
        {
            playRound();
            currentR++;
        }
        
        //Updating the scores of the players.
        calculateScore();
    }
    
    /**
     * Method used to clear the game before restarting.
     * This method does not need anything to be passed in the parameters.
     * Precondition: the scores, current round and stacks have been declared, the clear method has also been created.
     * Postcondition: the method will clear the stacks, set the current round back to 1 and set the players scores to zero.
     * This method will not return anything.
     */
    public void clearGame()
    {
        //Setting scores to 0 
        p1Score = 0;
        p2Score = 0;
        
        //Setting current round to 1.
        currentR = 1;
        
        //Calling the clear method on the stacks.
        stack1.clear();
        stack2.clear();
        stack3.clear();
        
    }
    
    /**
     * Method used to play one round, used as a helper method for the playGame method.
     * There will be no arguments passed in the parameters.
     * Preconditions: The playerMove and checkForPop methods are already created.
     * Postconditions: The method will play one round of the game, check if a disc needs to be popped from a stack, if so it will pop one from a stack, otherwise
     * it will not pop anything.
     * This method will not return anything.
     */
    public void playRound()
    {
        //Printing out the round number.
        System.out.println("Round: " + currentR);
        
        //Calling the playerMove method, passing the player number in the parameter, also displaying the action by the players.
        playerMove(1);
        playerMove(2);
        
        //Checking to see if a disc should be popped, and displaying it if so.
        popDisc();
        
        System.out.println();

    }
    
    /**
     * Method used for a players move.
     * The parameters passed is an integer of the player number.
     * Precondition: There are 3 different stacks already created, the method push is also already created.
     * PostCondition: player will push a disc onto a random stack, either stack 1,2 or 3, it will then also display which player pushed a disc onto which stack.
     * The method does not return anything.
     */
    public void playerMove(int playerNum)
    {
        //Creating a new disc object which corresponds to the player number passed in the parameters.
        Disc disc = new Disc(playerNum);
        
        //Creating a random stack object, referring to 3 different options it can choose randomly from.
        Random randomStack = new Random();
        int stack = randomStack.nextInt(3);
        
        if (stack == 0)
        {
            //Calling the push method and passing the parameter of the disc with the corresponding player number.
            //Printing and displaying which player pushed a disc onto stack 1.
            stack1.push(disc);
            System.out.println("Player " + playerNum + " pushed disc onto stack 1");
           
        }
        else if (stack == 1)
        {
            //Calling the push method and passing the parameter of the disc with the corresponding player number.
            //Printing and displaying which player pushed a disc onto stack 2.
            stack2.push(disc);
            System.out.println("Player " + playerNum + " pushed disc onto stack 2");
            
        }
        else 
        {
            //Calling the push method and passing the parameter of the disc with the corresponding player number.
            //Printing and displaying which player pushed a disc onto stack 3.
            stack3.push(disc);
            System.out.println("Player " + playerNum + " pushed disc onto stack 3");
            
        }
        

    }
    
    /**
     * Method to check if there is a disk that has been popped from a stack.
     * There will be no arguments passed in the parameters.
     * Precondition: There are 3 stacks already created and initialized.
     * Postcondition: The method will pop a disk from stack 1 every 3 rounds, pop a disk from 
     * stack 2 every 5 rounds and pop a disk from stack 3 every 8 rounds.
     * If a stack is empty, the program will throw an exception as we cannot pop from an empty stack. The method will display when a disc is popped and from 
     * which stack it is popped from.
     * The method will not return anything.
     */
    public void popDisc()
    {
        //Popping from stack 1 
        //Using modular division to see if it is a multiple of 3, meaning every three rounds.
        if (currentR % 3 == 0)
        {
            //Trying to pop, if stack is empty, an exception will be thrown and an error message will be printed.
            //Calling the pop method, and printing and displaying a statement if a disc has been popped, if so from where. 
            try
            {
                stack1.pop();
                System.out.println("A disc has been popped from stack 1.");
            }
            catch (Exception error)
            {
                if (error instanceof EmptyStackException)
                {
                    System.out.println("Stack 1 is empty, unable to pop from stack.");
                }
            }
        }
        
        //Using the same if and try and catch blocks for the other 2 stacks, however for stack 2 it is every 5 rounds (mod 5 == 0)
        //And for stack 3 it is for every 8 rounds (mod 8 == 0)
        if (currentR % 5 == 0)
        {
            try
            {
                stack2.pop();
                System.out.println("A disc has been popped from stack 2.");
            }
            catch (Exception error)
            {
                if (error instanceof EmptyStackException)
                {
                    System.out.println("Stack 2 is empty, unable to pop from stack.");
                }
            }
        }
        
        
        if (currentR % 8 == 0)
        {
            try
            {
                stack3.pop();
                System.out.println("A disc has been popped from stack 3.");
            }
            catch (Exception error)
            {
                if (error instanceof EmptyStackException)
                {
                    System.out.println("Stack 3 is empty, unable to pop from stack.");
                }
            }
        }
        
    }
    
    /**
     * Method used to keep track of the score. 
     * There will be no arguments passed in the parameters.
     * Precondition: The methods isEmpty, pop and getPlayerNum have already been created.
     * Postcondition: The method will keep popping from a stack until the stack is empty, if the disc corresponds to player 1, it will add 1 to their score, 
     * vice versa for player 2. The method will continue looping until the stack is empty. 
     * This method does not return anything.
     */
    public void calculateScore()
    {
        //Creating 3 booleans representing wether or not a stack is empty, true if it is empty, false otherwise.
        boolean stackEmpty1 = false;
        boolean stackEmpty2 = false;
        boolean stackEmpty3 = false;
        
        //Creating a while loop which continues to count the discs while the stack is not empty.
        while (stackEmpty1 == false)
        {
            //Using an if statement to check if the boolean is true or false.
            //Calling the isEmpty method to check wether or not the boolean is true or false.
            //If the boolean is true, it will exit out of the while loop.
            if (stack1.isEmpty() == true)
            {
                stackEmpty1 = true;
            }
            //If the stack is not empty, it is set to false.
            else
            {
                stackEmpty1 = false;
                
                //Creating a disc object of "playerDisc", set equal to calling the pop method on the stack.
                //Using pop, since it both returns the first disc and also removes it from the stack.
                Disc playerDisc = stack1.pop();
                
                //Creating the number of the player's disc integer
                //Using the getPlayerNum method, in order to get the player number of the disc removed and returned from the stack.
                int playerDiscNum = playerDisc.getPlayerNum();
                
                //Creating another if statement which will check if the disc's player number is equal to 1 or 2
                //If == 1, it will increase the player1 score by one, vice versa for player 2.
                if (playerDiscNum == 1)
                {
                    p1Score++;
                }
                else if (playerDiscNum == 2)
                {
                    p2Score++;
                }
                
            }
            
        }
        
        //REpeating this while loop twice more for the other 2 stacks.
        
        while (stackEmpty2 == false)
        {
            if (stack2.isEmpty() == true)
            {
                stackEmpty2 = true;
            }
            else
            {
                stackEmpty2 = false;
                
                Disc playerDisc = stack2.pop();
                int playerDiscNum = playerDisc.getPlayerNum();
                
                if (playerDiscNum == 1)
                {
                    p1Score++;
                }
                else if (playerDiscNum == 2)
                {
                    p2Score++;
                }
                
            }
            
            
        }
        
        while (stackEmpty3 == false)
        {
            if (stack3.isEmpty() == true)
            {
                stackEmpty3 = true;
            }
            else
            {
                stackEmpty3 = false;
                
                Disc playerDisc = stack3.pop();
                int playerDiscNum = playerDisc.getPlayerNum();
                
                if (playerDiscNum == 1)
                {
                    p1Score++;
                }
                else if (playerDiscNum == 2)
                {
                    p2Score++;
                }
                
            }
            
            
        }
        
    }
    
    /**
     * The method is used to displayScores.
     * There will be no arguments passed in the parameters.
     * Precondition: p1Score and p2Score have already been initialized and declared.
     * Postcondition: The method will print the scores of player 1 and 2 respectively.
     * This method will not return anything.
     */
    public void displayScores()
    {
        //Creating print statements which displays the score of player 1 and player 2.
        System.out.println();
        System.out.println("Player 1 Score: " + p1Score);
        System.out.println("Player 2 Score: " + p2Score);
        
    }
    
    /**
     * Method used to display the winner of the game.
     * There will be no arguments passed in the parameters.
     * Precondition: p1Score and p2Score have already been initialized and declared.
     * Postcondition: The method will display the winner of the game, or if it is a tie.
     * This method will not return anything.
     * 
     */
    public void displayWinner()
    {
        //Creating if statements for each of the scenarios of the scores.
        //Printing out and displaying the Winner or if the scores are equal, a tie.
        if (p1Score > p2Score)
        {
            System.out.println("Player 1 is the winner!");
        }
        
        if (p1Score < p2Score)
        {
            System.out.println("Player 2 is the winner!");
        }
        
        if (p1Score == p2Score)
        {
            System.out.println("It's a tie!");
        }
        
    }
}
