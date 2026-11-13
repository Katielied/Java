import javax.swing.JOptionPane;

/**
 * IOHandlerDialog: uses dialog boxes for input (via get method) and output (via put method).
 * Note: exceptions are potentially thrown by methods of JOptionPane; refer to documentation in Java standard library. 
 */
public class IOHandlerDialog implements IOHandlerInterface
{
    // Attributes
    private JOptionPane panel; // for dialog IO 
    
    /**
     * constructor
     * @param none
     */
    public IOHandlerDialog()
    {
        panel = new JOptionPane();
    }
    
    /**
     * get: 
     * @param String prompt
     * @return String that was input by the user via dialog box
     * or "" if the user clicked the cancel button.
     */
    @Override
    public String get(String prompt)
    {
    // Source is input dialog box.
        // Show input dialog box and return the input.
        String input = panel.showInputDialog (prompt);
        if (input == null) // user clicks cancel button
           return "";
        else 
           return input;
    }
    
    /**
     * put: 
     * @param String to write to the selected destination.
     */
    @Override
    public void put(String output)
    {
    // Destination is output dialog box.
        // Show output dialog box, using a default frame.
        panel.showMessageDialog (null, output);    
    }
    
    /**
     * getFromList:
     * @param String listTitle
     * @param String prompt
     * @param String[] choices
     * @return String that was input by the user.
     */
    public String getFromList(String listTitle, String prompt, String[] choices)
    {
        String input = (String) JOptionPane.showInputDialog(null, prompt, listTitle, JOptionPane.QUESTION_MESSAGE, null, 
        choices, // Array of choices
        choices[0]); // Initial choice
        
        return input;
    }
}
