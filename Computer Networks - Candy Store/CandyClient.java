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

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.Serializable;

public class CandyClient 
{
    public static void main(String[] args) throws IOException 
    {
        
        if (args.length > 3) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String userName = args[2];

        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            
            //updating the input, output stream from printwriter to the java.io imported class including object output/input streams.
            ObjectOutputStream out = new ObjectOutputStream(kkSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(kkSocket.getInputStream());
            
        ) {
            //Buffered reader for user input.
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            String fromUser = "User is Connecting";
            Transaction fromServer; //= new Transaction(null);
            Order clientOrder = new Order(userName);
            
            //Display welcome message and menu options 
            System.out.println("WELCOME TO THE CANDY STORE");
           
            //Get server choice in form of transaction object 
            //Deserializing the message object from the server and reading it
            while ((fromServer = (Transaction)in.readObject()) != null)
            {
                //Getting the servers transaction response.
                String serverTransString = fromServer.getTransName();
                
                //Splitting up in parts - only used in the case of buying transaction response.
                String serverTransParts[] = serverTransString.split(":");
                
                if (serverTransParts.length==3) //If client has chosen to buy
                {
                    String successOrFail = serverTransParts[0];
                    String candyName = serverTransParts[1];
                    int quantity = Integer.parseInt(serverTransParts[2]);
                    
                    if (successOrFail.equals("buySuccess")) //If server transaction response was a success.
                    {
                        Candy candyToAdd = new Candy(candyName,quantity);
                        
                        //Adding to the clients order.
                        clientOrder.addCandy(candyToAdd);
                        
                        //Adding to their running total.
                        double candyPrice = candyToAdd.GetCandyPrice();
                        double priceToAdd = candyPrice * quantity;
                        clientOrder.addtoTotal(priceToAdd);
                        
                        //Printing out their order.
                        System.out.println("Updated Order:");
                        clientOrder.displayOrder();
                        
                        System.out.println("Menu:");
                        System.out.println("1.See Inventory\n2.Buy Candy\n3.See Order So Far\n4.Finish Order/Exit");
                        System.out.println("Please type the corresponding number.");
                    }
                    else if (successOrFail.equals("buyFail")) //If their buy transaction failed.
                    {
                        if (candyName.equals("Name")) //If the server determines error to be candy name
                        {
                            System.out.println("Unable to purchase - Candy Name does not exist.");
                        }
                        else if (candyName.equals("Quantity")) //If the server determines the error to be their chosen quantity.
                        {
                            System.out.println("Unable to purchase - Chosen quantity exceeds quantity left in inventory.");
                        }                 
                        System.out.println("Menu:");
                        System.out.println("1.See Inventory\n2.Buy Candy\n3.See Order So Far\n4.Finish Order/Exit");
                        System.out.println("Please type the corresponding number.");
                    }  
                } 
                else //clients choice was not to buy - server response transaction is not to check buy success or fail.
                {
                    if(serverTransString.equals("Exit")) //Client chooses to end. - End program and connection.
                    {
                        break;
                    }
                    else //Client has chosen to either see inventory or chosen to see their order.
                    {
                        System.out.println(serverTransString);
                        System.out.println("Menu:");
                        System.out.println("1.See Inventory\n2.Buy Candy\n3.See Order So Far\n4.Finish Order/Exit");
                        System.out.println("Please type the corresponding number.");
                        
                    }
                    
                }
                //Used to read in user choice.
                fromUser = stdIn.readLine();
                
                //If user chooses:
                if (fromUser != null) {
                    if (fromUser.equals("1"))
                    {
                        //Displaying
                        System.out.println("You Chose: See Inventory.");
                        
                        //Creating new message object and serializing it to send to the server.
                        Transaction userTrans = new Transaction("ListInventory",userName);
                        out.writeObject(userTrans);
                    }
                    else if (fromUser.equals("2"))
                    {
                        //Displaying
                        System.out.println("You Chose: Buy Candy");
                        
                        //Setting up scanner to read in user input for buying candy.
                        Scanner scanner = new Scanner(System.in);
                        
                        System.out.println("Please type the Candy Name:");
                        String candyName = scanner.nextLine();
                        
                        System.out.println("How many packs would you like?");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        
                        //Send transaction object, using ':' to seperate their choice of candy and quantity for server.
                        Transaction userTrans = new Transaction("Buy:" + candyName + ":" + quantity,userName);
                        out.writeObject(userTrans);
                    }
                    else if (fromUser.equals("3"))
                    {
                        //Displaying
                        System.out.println("You Chose: See Order So Far");
                        
                        //Calling method to print.
                        clientOrder.displayOrder();  
                        
                        //Sending empty transaction object to avoid deadlock.
                        Transaction userTrans = new Transaction("",userName);
                        out.writeObject(userTrans);
                        
                    }
                    else if (fromUser.equals("4"))
                    {
                        //Displaying
                        System.out.println("You Chose: Finish Order/Exit");
                        System.out.println("Final Order:");
                        
                        //Calling display order to show final order to client.
                        clientOrder.displayOrder();

                        //Creating new message object and serializing it to send to the server.
                        Transaction userTrans = new Transaction("Exit",userName);
                        out.writeObject(userTrans);
                        
                    }
                    else //Client types invalid number choice.
                    {
                         System.out.println("Please type a valid number."); 
                         
                         //Sending empty transaction object to avoid deadlock.
                         Transaction userTrans = new Transaction("",userName);
                         out.writeObject(userTrans);
                    }
                }
            }
            kkSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e){
            System.err.println("Couldn't read object " +
            hostName);
            System.exit(1);
        }
    }
}
