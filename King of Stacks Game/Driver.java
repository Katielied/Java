
import java.util.Scanner;

/**
 * Driver class for the King of Stacks game.
 * @Katarina Liedbeck 
 * @version1 10/08/2023
 * Helped by Chris Sciortino
 */
public class Driver
{
    /**
     * This driver will scan a user input of how many rounds they would like to play, while the user input is less than 30 rounds it will continue 
     * prompting the user.
     * It will then create a new object of NewGame of KingOfStacks and use the helper methods of playGame, displayScores, and displayWinner in the KingOfStacks
     * class.
     * It will play the full game of KingOfStacks and display the scores of player 1 and 2, and the winner. 
     * It will also display every time a disc is pushed onto a stack, and everytime a disc is popped from a stack.
     */
    public static void main()
    {
        //Declaring and initilizing the rounds to an integer of 0.
        int rounds = 0;
        
        //Creating a scanner object assigned to the user input of how many rounds they would like to play.
        Scanner userInput = new Scanner (System.in);
        
        //Declaring and initializing valid as a boolean to false, which is the validity of the user's input.
        boolean valid = false;
    
        //Creating a while loop, which will continue looping if the user's input is not valid (under 30 rounds).
        while(!valid)
        {
            //Printing out asking the user how many rounds they would like to play.
            System.out.println("How many rounds would you like to play? (Please enter a minimum of 30 rounds.)");
            
            //using our scanner object to check their input, and turning it into an integer.
            String playerRounds = userInput.nextLine();
            rounds = Integer.valueOf(playerRounds);
                    
            //Creating an if statement to check if the player rounds are valid, if false the loop will continue.
            if (rounds >= 30)
            {
                valid = true;
            }
            else
            {
                valid = false;
                //Printing out to the user if their input was not valid.
                System.out.println("User input not valid, please enter a round number larger than 30.");
            }
        }
        
        //Creating a new game of King of Stacks, using the parameter of how many rounds the user would like to play.
        KingOfStacks NewGame = new KingOfStacks(rounds);
        
        //Calling the playGame method on the KingOfStaks object of newGame.
        NewGame.playGame();
        
        //Calling the displayScores method on the KingOfStacks object of newgame.
        NewGame.displayScores();
        
        //Displaying the winner of the game.
        NewGame.displayWinner();
        
    }
}