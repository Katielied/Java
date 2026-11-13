------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Candy Store Enhanced Version 
PURPOSE OF PROJECT: Computer Networks Assignment
VERSION or DATE: Version 2 - Due May 14th 2025
HOW TO START THIS PROJECT: Start Server side thread first - enter port number and then localhost (or host name), then start client side (on separate computer or copy of project) enter the same number and server name, and also the clients name (any name you want).
AUTHORS: Katarina Liedbeck
USER INSTRUCTIONS:
- After connecting from the client side to the sever, a menu will be shown to the client.
- enter input number corresponding to the menu choice.
- To see the inventory of candy, enter 1
- To buy candy and add to your order, enter 2.
- To see your order so far, enter 3.
- To exit/order enter 4. 
- All these above options are prompted on the terminal as well.
- For the buy option it will also ask user to enter the candy name and the quantity which you want to buy.

Synchronization:
Used keyword "Synchronized" for updateInventory method, ensuring that only one thread can update the inventory at a time. This ensures validated and correct information being passed to any of the threads.

Object:
Transaction object is being passed, includes a string of the transaction name and the clients name to and from the server.

If I had more time:
If I had more time, I would try to create a GUI, to make it more appealing for the user experience. I would also try to make it so that the inventory stays updated when the application is closed and reopened again. 

Features implemented so far:
All required in my enhanced version, multiple clients can buy from the inventory, and it updates for all of the different client threads using that server. Server side can see the name of the client they are speaking to, and clients can see the updated inventory and updated orders. 
