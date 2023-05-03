/**
 * Gunar Sekhon
 * Programming Assignment 2: RPG
 * Section: 020
 * 05/04/23
 */

//Imports Scanner, FileNotFoundException, FileOutputStream, and PrintWriter classes
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class RPGEngine {
    public static void main (String[] args) {

        //Initializes scanner
        Scanner scnr = new Scanner(System.in);

        //Initializes Inventory and Map classes
        Inventory inventory = new Inventory();
        Map map = new Map();
        
        // Counts all valid user inputs. Used for the inputs.txt file where this number will be printed
        int validInputCount = 0;

        //Initializes variables used in main method
        String userInput = "";
        boolean gameOver = false;
        boolean bribedGuard = false;
        boolean captainCalled = false;
        boolean actionAdded = false;

        //Sets up file output system by initializing FileOutputStream and PrintWriter. Will be used to log all user inputs
        FileOutputStream fis = null;
        try {
            fis = new FileOutputStream("inputs.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find inputs.txt");
        } 
        PrintWriter writer = new PrintWriter(fis);

        //Creates Room object named currentRoom and starts it as Port. Will be used to show which room the player is in. Adds "I" and "Pay to Board" to its actions
        Room currentRoom = new Room("Port", "A large port for pirates with several ships docked. You are standing in front of the Raven, a large black ship. There is a man accepting payment for people boarding");
        currentRoom.addActions("I");
        currentRoom.addActions("Pay to Board");

        //Creates Room object named mainDeck with actions "I", "Help mop", "helm", "captainsQuarters", "forecastleDeck", and "cabin"
        Room mainDeck = new Room ("mainDeck", "The main portion of the ship. There is the helm to the back, the captains quarters underneath it, the forecastle deck to the front, and a the cabin underneath. There is a man mopping the deck.");
        mainDeck.addActions("I");
        mainDeck.addActions("Help mop");
        mainDeck.addActions("helm");
        mainDeck.addActions("captainsQuarters");
        mainDeck.addActions("forecastleDeck");
        mainDeck.addActions("cabin");
        map.addRoom(mainDeck);

        //Creates Room object named helm with actions "I" and "mainDeck"
        Room helm = new Room ("helm", "The steering wheel of the ship. The captain stands there to guide the her and her crew.");
        helm.addActions("I");
        helm.addActions("mainDeck");
        map.addRoom(helm);

        //Creates Room object named captainsQuarters with no actions as it will be the final room
        Room captainsQuarters = new Room("captainsQuarters", "The place where the captain sleeps. It is his personal room and nobody goes in without permission.");
        map.addRoom(captainsQuarters);

        //Creates Room object named forecastleDeck with actions "I" and "mainDeck"
        Room forecastleDeck = new Room("forecastleDeck", "The front of the ship. From here you can see the expansive sea in all its glory.");
        forecastleDeck.addActions("mainDeck");
        forecastleDeck.addActions("I");
        map.addRoom(forecastleDeck);

        //Creates Room object named cabin with actions "I", "Bribe guard", and "mainDeck"
        Room cabin = new Room("cabin", "The area under the deck. This is where the crew stays and also where bulk items are stored. There are lines of cannons on either side being watched by a guard.");
        cabin.addActions("Bribe guard");
        cabin.addActions("mainDeck");
        cabin.addActions("I");
        map.addRoom(cabin);
        
        //Prints out the start of the game and asks user to press any key to start game
        System.out.println("Welcome to \"A Pirate Adventure\"!");
        System.out.println("This is a text based RPG. Options for inputs are provided and selecting \"I\" will show you your inventory.");
        System.out.println("Click any key to start!");
        scnr.nextLine();
        System.out.println();

        //Prints backstory for the game
        System.out.println("You are a young lad named James. You were raised by your single mother.");
        System.out.println("Your father was a pirate who was killed at sea and you have always had a dream to become a pirate yourself and avenge him.");
        System.out.println("Now, the chance lies in front of you as you stand at a port with a coin in your hand and a ship in front of you.");
        System.out.println("The Pirate: Ye must pay if ye wish to join the crew of the great Captain Salazar!");

        //Loops for userInput until user inputs "Pay to Board"
        while (!userInput.equals("Pay to Board")) {
            System.out.println();

            //Prints out currentRoom which asks user for an input. Sets input to userInput
            System.out.println(currentRoom);
            userInput = scnr.nextLine();

            System.out.println();

            //Prints the user's input to inputs.txt
            writer.println(userInput);

            //Validates user's input and keeps asking for new input until valid input is provided. Records every input, valid or not
            while (!currentRoom.validAction(userInput)) {
                System.out.println("Invalid selection, try again.");
                userInput = scnr.nextLine();
                System.out.println();
                writer.println(userInput);
            }

            //Increments validInputCount
            validInputCount++;

            //Switch case to detect what course of action to take
            switch (userInput) {
                case "I": //Will print out inventory
                    inventory.printInventory();
                    break;
                case "Pay to Board": //Will board the ship and remove Coin from inventory. Sets currentRoom to mainDeck.
                    System.out.println("You pay the man and board the ship.");
                    inventory.removeItem("Coin");
                    currentRoom = mainDeck;
                    break;
            }
        }
        
        //Loops until the game is over
        while (!gameOver) {
            System.out.println();

            //Prints out currentRoom which asks user for an input. Sets the input to userInput
            System.out.println(currentRoom);
            userInput = scnr.nextLine();

            System.out.println();

            //Prints the user's input to inputs.txt
            writer.println(userInput);
            
            //Validates user's input and keeps asking for new input until valid input is provided. Records every input, valid or not
            while (!currentRoom.validAction(userInput)) {
                System.out.println("Invalid selection, try again");
                userInput = scnr.nextLine();
                writer.println(userInput);
            }

            //Increments validInput count
            validInputCount++;

            //Switch case to detect what course of action to take
            switch (userInput) {

                case "I": //Will print out inventory
                    inventory.printInventory();
                    break;

                case "Help mop": //If user inputs Help mop
                    System.out.println("You help the man mop the deck and he gives you a coin");

                    //Removes Coin from inventory
                    inventory.addItem("Coin");

                    //Removes Help mop action
                    mainDeck.removeActions("Help mop");

                    //Changes mainDeck description
                    mainDeck.setDescription("The main portion of the ship. There is the helm to the back, the captains quarters underneath it, the forecastle deck to the front, and a the cabin underneath.");
                    break;

                case "Bribe guard": //If user inputs Bribe guard

                    //If user has a Coin, bribes the guard and prints so
                    if (inventory.hasItem("Coin")) {
                        System.out.println("You bribe the guard with your coin and he walks away from the cannons.");

                        //Removes Coin from inventory and removes Bribe guard action
                        inventory.removeItem("Coin");
                        cabin.removeActions("Bribe guard");

                        //Changes forecastleDeck description and sets bribedGuard boolean to true
                        forecastleDeck.setDescription("The front of the ship crowded with all of the crew. From here you can see that a British ship has appeared. It seems to be led by Robert Maynard: The man who killed Blackbeard... and your father.");
                        bribedGuard = true;
                        System.out.println("The Captain shouts from up above: \"PIRATE HUNTERS! ALL YE MEN HAUL A** AND GET UP ON THE FORECASTLE DECK!");

                    } else { //If the user doesn't have a coin, says so
                        System.out.println("You don't have a coin to bribe him with.");
                    }
                    break;

                case "helm": //If user inputs helm

                    if (captainCalled) { //If captain has called player, lets you up to the helm to talk with the captain
                        
                        //Sets currentRoom to userInput, which is "helm"
                        currentRoom = map.getRoom(userInput);

                        System.out.println("The Captain: \"Well done young lad, I have something for you.\"");
                        System.out.println("He hands you the key to his quarters and says \"Lets go and look on me desk\" and follows you down.");

                        //Adds Key to user's inventory
                        inventory.addItem("Key");

                    } else { //If captain hasn't called player, says you cannot go to the helm
                        System.out.println("The captain is steering the ship, probably best to not disturb him.");
                    }
                    
                    break;

                case "captainsQuarters": //If user inputs captainsQuarters

                    if (inventory.hasItem("Key")) { //If user has a Key
                        
                        //Sets currentRoom to userInput, which is "captainsQuarters"
                        currentRoom = map.getRoom(userInput);

                        //Prints out the end game dialogue lines
                        System.out.println("You and the captain enter the captain's quarters");
                        System.out.println("You look at his desk and see a picture. In it you see a younger captain alongside a man who resembles you.");
                        System.out.println("The captain notices your look of shock. \"That's right, I sailed with your ol' man. He was a fine sailor and an even better friend.\"");
                        System.out.println("\"He would have been pround of the man you've become.\"");
                        System.out.println("As he says this, you feel a sense of relaxation as you feel that your father's soul can finally rest.");
                        
                        //Sets gameOver boolean to true
                        gameOver = true;

                    } else { //If user doesn't have a key, doesn't let them into the room
                        System.out.println("That is the captain's room, you cannot enter without permission!");
                    }

                    break;

                case "forecastleDeck": //If user inputs forecastleDeck

                    //Sets currentRoom to userInput, which is "forecastleDeck"
                    currentRoom = map.getRoom(userInput);

                    if (bribedGuard) { //If user has bribed guard
                        //Part of story printed
                        System.out.println("One of the cannons on Maynard's ship fires and strikes a hole in your ship at cabin level.");

                        //Changes cabin description
                        cabin.setDescription("The area under the deck. This is where the crew stays and also where bulk items are stored. There are lines of cannons on either side. You notice that the guard from before is unconscious.");
                        
                        if (!actionAdded) { //If this action hasn't been added before, adds "Fire cannon" and sets actionAdded to true, else does nothing
                            cabin.addActions("Fire cannon");
                            actionAdded = true;
                        }
                    }
                    break;

                case "cabin": //If user inputs cabin

                    //Sets currentRoom to userInput, which is "cabin"
                    currentRoom = map.getRoom(userInput);
                    break;

                case "mainDeck": //If user inputs mainDeck

                    //Sets currentRoom to userInput, which is "mainDeck"
                    currentRoom = map.getRoom(userInput);
                    break;

                case "Fire cannon": //If user inputs Fire cannon

                    //Prints out the actions the player did to fire the cannon and its result. All story, nothing user dependent
                    System.out.println("You rush over to the cannons. They seem to already have been loaded. You aim it and light the fuse.");
                    System.out.println("BOOM!");
                    System.out.println("The shot went right through the lower part of Maynard's ship, causing it to sink. With it's angle off, his ship cannot fire at yours.");
                    System.out.println("You hear cheering above deck and feel triumphant. You have saved the crew and avenged your father.");
                    System.out.println("The Captain shouts from above: \"WHOEVER FIRED THAT CANNON, GET UP HERE NOW!\" He seems to be at the helm.");

                    //Sets captainCalled boolean to true
                    captainCalled = true;

                    //Changes forecastleDeck description
                    forecastleDeck.setDescription("The front of the ship. The crew is all there cheering and watching the British ship sink.");

                    //Remobes Fire cannon action
                    cabin.removeActions("Fire cannon");

                    break;
            }
        }

        //Prints out "GAME OVER"
        System.out.println("GAME OVER!");

        //Writes Valid Input Count along with the value of validInputCount to inputs.txt
        writer.println("Valid Input Count = " + validInputCount);

        //Closes PrintWriter object
        writer.close();


    }
}