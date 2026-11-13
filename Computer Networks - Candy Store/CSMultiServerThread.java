/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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
import java.util.*;
import java.io.Serializable;

public class CSMultiServerThread extends Thread {
    private Socket socket = null;
    private String serverName = "";
    private Inventory candyShop;
    private ArrayList<Candy> candies; 

    public CSMultiServerThread(Socket socket, String serverName, Inventory candyShop, ArrayList<Candy> candies) {
        super("CSMultiServerThread");
        this.socket = socket;
        //Adding in a name for the server, passing it as an argument.
        this.serverName = serverName;
        this.candyShop = candyShop;
        this.candies = candies;
    }
    
    public void run() {

        try (
        
        //updating the input, output stream from printwriter to the java.io imported class including object output/input streams.
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            String inputLine,outputLine;
            
            //Initiate conversation with client
            CSProtocol csp = new CSProtocol();
            outputLine = csp.processInput(null);
            
            //Send first transaction message object.
            Transaction serverTransaction = new Transaction(outputLine,"Server");
            System.out.println("Sending initial connection message: " + serverTransaction.getTransName());
            out.writeObject(serverTransaction);
            System.out.println("Sent intial message.");
            
            //Used to read server response.
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            //Declaring the message object from the client.
            Transaction clientTransaction; 
            
            //While the client is sending a transaction object as a choice.
            while ((clientTransaction = (Transaction)in.readObject()) != null)
            {
                //Using protocol, processing the inputs transaction. 
                outputLine = csp.processInput(clientTransaction);
                String clientName = clientTransaction.getUsername();
                
                //If the user chooses exit, end the program.
                if (outputLine.equals("Exit"))
                {
                    // Print line saying that the user finished their order.
                    //System.out.println(userName + " said goodbye, connection with " + userName + " ended.");
                    Transaction serverResponse = new Transaction("Exit","Server");
                    out.writeObject(serverResponse);
                    System.out.println("Session over, " + clientName + " has Ordered/Exit.");
                    break;
                }
                else if (outputLine.equals("Display inventory"))
                {
                    //using diaplayInventory method to return string for transaction object.
                    String inventory = candyShop.displayInventory();
                    Transaction serverResponse = new Transaction(inventory,"Server");
                    out.writeObject(serverResponse);
                    System.out.println(clientName + " has chosen to display inventory - inventory sent.");
                    
                }
                else if (outputLine.equals("Buy")) 
                {
                    //is user has chosen to buy.
                    //Candy name and quantity are stored in string in format: Buy:*candyName*:*quantity*
                    System.out.println(clientName + " wants to purchase candy.");
                    String transactionString = clientTransaction.getTransName();
                    
                    //Splitting up the parts of the string.
                    String transActionParts[] = transactionString.split(":");
                    String candyName = transActionParts[1];
                    int quantity = Integer.parseInt(transActionParts[2]);
                    
                    System.out.println(clientName + " wants to buy " + quantity + " packs of " + candyName);
                    
                    //Creating a boolean to see if buy was successful.
                    boolean success = candyShop.updateInventory(candyName,quantity);
                    if (success == true) //if inventory update worked.
                    {
                        //Print message to server.
                        System.out.println("updated inventory successfully:\nupdated " + candyName + "\nquantity subtracted: " + quantity);
                        
                        //Send server transaction response.
                        Transaction serverResponse = new Transaction("buySuccess:"+candyName+":"+quantity,"Server");
                        out.writeObject(serverResponse);
                    }
                    else //Inventory update failed.
                    {
                        String buyFail = "";
                        String serverMessage = "";
                        
                        //Looping through to see if the candy name or the quantity was the error.
                        for(int i = 0; i < candies.size(); i++)
                        {
                            Candy candy = candies.get(i);
                            if(candy.getCandyName().equals(candyName))
                            {
                                if(candy.getQuantity()<quantity) //quantity wanted was the error.
                                {
                                    buyFail = "Quantity";
                                    serverMessage = "Unable to purchase - Chosen quantity exceeds quantity left in inventory.";
                                    break;
                                }
                            }
                            else //Candy name not found, candy name does not exist.
                            {
                                buyFail = "Name";
                                serverMessage = "Unable to purchase - Candy Name does not exist.";
                            }
                        }
                        
                        //Send to client what the error was.
                        Transaction serverResponse = new Transaction("buyFail:"+buyFail+":"+0,"Server");
                        out.writeObject(serverResponse);
                        
                        //Print the error reasoning.
                        System.out.println(serverMessage);
                    }
                    
                }
                else //Used for displaying clients order and if user does not enter a valid choice
                //Send exmpty transaction object to make sure no deadlock.
                {
                    Transaction serverResponse = new Transaction("","Server");
                    out.writeObject(serverResponse);
                }
            }
            //Closing the socket if breaking the loop from bye.
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.err.println("Couldn't read object ");
            System.exit(1);
        }
    }
}
