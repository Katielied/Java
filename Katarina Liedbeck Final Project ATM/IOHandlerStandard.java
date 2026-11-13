import java.util.Scanner;

/**
 * IOHandlerStandard: uses standard IO for input (via get method) and output (via put method).
 * Note: exceptions are potentially thrown by methods of Scanner; refer to documentation in Java standard library. 
 */
public class IOHandlerStandard implements IOHandlerInterface
{
    // Attributes
    private Scanner input;
    
    /**
     * constructor: 
     * @param none
     */
    public IOHandlerStandard()
    {
        input = new Scanner (System.in);
    }
    
    /**
     * get: 
     * @param a String, prompt to display to standard output destination.
     * @return a String, user input from standard input source.
     */
    @Override
    public String get(String prompt)
    {
         System.out.println (prompt);
         return input.next();
    } // end get
    
    /**
     * put: 
     * @param a String to write to the selected destination.
     */
    @Override
    public void put(String output)
    {
         System.out.println (output);
    } // end put
    
    /**
     * getFromList:
     * @param String listTitle
     * @param String prompt
     * @param String[] choices
     * @return String that was input by the user.
     */
    public String getFromList(String listTitle, String prompt, String[] choices)
    {
        System.out.println(listTitle);
        
        System.out.println("The choices are: ");
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i+1) + ") " + choices[i]);
        }
        System.out.println();
        
        System.out.println(prompt);
        return input.next();
    }
    
} // end IOHandlerStandard
