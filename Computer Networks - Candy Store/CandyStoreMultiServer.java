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

public class CandyStoreMultiServer 
{
    public static void main(String[] args) throws IOException 
    {

        if (args.length != 2) {
            System.err.println("Usage: java KKMultiServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        String serverName = args[1];
        boolean listening = true;
        
        //Create candies to be added into the inventory.
        ArrayList<Candy> candies = new ArrayList<>();
        Candy swedishFish = new Candy("Swedish Fish",20);
        Candy MMs = new Candy("M&Ms",50);
        Candy chocolateHearts = new Candy("Chocolate hearts",40);
        Candy gummyWorms = new Candy("Gummy worms",25);
        Candy haribos = new Candy("Variety Pack Haribos",40);
        Candy bananaTwizzlers = new Candy("Banana Twizzlers",20);
        Candy gummySpaghetti = new Candy("Gummy Spaghetti",35);
            
        //Adding candies into Array List.
        candies.add(swedishFish);
        candies.add(MMs);
        candies.add(chocolateHearts);
        candies.add(gummyWorms);
        candies.add(haribos);
        candies.add(bananaTwizzlers);
        candies.add(gummySpaghetti);
            
        //Initializing candyshop inventory obj, using candies in array list.
        Inventory candyShop = new Inventory(candies);
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) 
        { 
            //Infinate loop for the server to accept new sockets.
            while (listening) {
                new CSMultiServerThread(serverSocket.accept(),serverName, candyShop,candies).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
