/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;

public class CSProtocol {
    //Keeping track of the states:
    //Only using two states in this case, waiting for a connection and in a transaction.
    private static final int WAITING = 0;
    private static final int IN_TRANSACTION = 1;
    private int state = WAITING;
    
    /*
     * Precondition: A string input from the user passed in as a parameter.
     * Postcondition: Method returns string output response to the user depending on the state.
     */
    public String processInput(Transaction TransactionChoice) 
    {
        String theOutput = null;

        if (state == WAITING) 
        {
            //First output to the user when they connect.
            theOutput = "connection established with the server....";
            //Change state since in conversation.
            state = IN_TRANSACTION;
        } 
        else if (state == IN_TRANSACTION) 
        { 
            //If transaction choice is to buy.
            //using split to just get the transaction name.
            if(TransactionChoice.getTransName().split(":")[0].equals("Buy"))
            {
                theOutput = "Buy";
            }
            else if(TransactionChoice.getTransName().equals("ListInventory")) //If user wants to list inventory.
            {
                theOutput = "Display inventory";
            }
            else if(TransactionChoice.getTransName().equals("Exit")) //If user chooses to end.
            {
                theOutput = "Exit";
            }
            else
            {
                theOutput = "";
            }
        }
        //Returning the string output.
        
        return theOutput;
    }
}
