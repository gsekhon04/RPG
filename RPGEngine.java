import java.util.Scanner;

public class RPGEngine {
    public static void main (String[] args) {

        Scanner scnr = new Scanner(System.in);

        Inventory inventory = new Inventory();
        Map map = new Map();

        String userInput = "";
        boolean gameOver = false;
        boolean bribedGuard = false;
        boolean captainCalled = false;

        Room currentRoom = new Room("Port", "A large port for pirates with several ships docked. You are standing in front of the Raven, a large black ship. There is a man accepting payment for people boarding");
        currentRoom.addActions("I");
        currentRoom.addActions("Pay to Board");

        Room mainDeck = new Room ("mainDeck", "The main portion of the ship. There is the helm to the back, the captains quarters underneath it, the forecastle deck to the front, and a the cabin underneath. There is a man mopping the deck.");
        mainDeck.addActions("I");
        mainDeck.addActions("Help mop");
        mainDeck.addActions("helm");
        mainDeck.addActions("captainsQuarters");
        mainDeck.addActions("forecastleDeck");
        mainDeck.addActions("cabin");
        map.addRoom(mainDeck);


        Room helm = new Room ("helm", "The steering wheel of the ship. The captain stands there to guide the her and her crew.");
        helm.addActions("I");
        helm.addActions("mainDeck");
        map.addRoom(helm);

        Room captainsQuarters = new Room("captainsQuarters", "The place where the captain sleeps. It is his personal room and nobody goes in without permission.");
        map.addRoom(captainsQuarters);

        Room forecastleDeck = new Room("forecastleDeck", "The front of the ship. From here you can see the expansive sea in all its glory.");
        forecastleDeck.addActions("mainDeck");
        forecastleDeck.addActions("I");
        map.addRoom(forecastleDeck);

        Room cabin = new Room("cabin", "The area under the deck. This is where the crew stays and also where bulk items are stored.");
        cabin.addActions("Bribe guard");
        cabin.addActions("mainDeck");
        cabin.addActions("I");
        map.addRoom(cabin);

        System.out.println("Welcome to \"A Pirate Adventure\"!");
        System.out.println("This is a text based RPG. Options for inputs are provided and selecting \"I\" will show you your inventory.");
        System.out.println("Click any key to start!");
        scnr.nextLine();

        System.out.println("You are a young lad named James. You were raised by your single mother.");
        System.out.println("Your father was a pirate who was killed at sea and you have always had a dream to become a pirate yourself and avenge him.");
        System.out.println("Now, the chance lies in front of you as you stand at a port with a coin in your hand and a ship in front of you.");
        System.out.println("The Pirate: Ye must pay if ye wish to join the crew of the great Captain Salazar!");

        while (!userInput.equals("Pay to Board")) {
            System.out.println(currentRoom);
            userInput = scnr.nextLine();

            while (!currentRoom.validAction(userInput)) {
            System.out.println("Invalid selection, try again.");
            userInput = scnr.nextLine();
            }

            switch (userInput) {
                case "I":
                    inventory.printInventory();
                    break;
                case "Pay to Board":
                    System.out.println("You pay the man and board the ship.");
                    inventory.removeItem("Coin");
                    currentRoom = mainDeck;
                    break;
            }
        }
        

        while (!gameOver) {
            System.out.println(currentRoom);
            userInput = scnr.nextLine();
            
            while (!currentRoom.validAction(userInput)) {
                System.out.println("Invalid selection, try again");
                userInput = scnr.nextLine();
            }

            switch (userInput) {
                case "I":
                    inventory.printInventory();
                    break;
                case "Help mop":
                    System.out.println("You help the man mop the deck and he gives you a coin");
                    inventory.addItem("Coin");
                    mainDeck.removeActions("Help mop");
                    break;
                case "Bribe guard":
                    if (inventory.hasItem("Coin")) {
                        System.out.println("You bribe the guard with your coin and he walks away from the cannons.");
                        inventory.removeItem("Coin");
                        cabin.removeActions("Bribe guard");
                        forecastleDeck.setDescription("The front of the ship crowded with all of the crew. From here you can see that a British ship has appeared. It seems to be led by Robert Maynard: The man who killed Blackbeard... and your father.");
                        bribedGuard = true;
                        System.out.println("The Captain shouts from up above: \"PIRATE HUNTERS, ALL YE MEN HAUL A** AND GET UP ON THE FORECASTLE DECK!");
                    }
                    break;
                case "helm":
                    if (captainCalled) {
                        currentRoom = map.getRoom(userInput);
                    } else {
                        System.out.println("The captain is steering the ship, probably best to not disturb him.");
                    }
                    
                    break;
                case "captainsQuarters":
                    if (inventory.hasItem("Key")) {
                        currentRoom = map.getRoom(userInput);
                    } else {
                        System.out.println("That is the captain's room, you cannot enter without permission!");
                    }
                    break;
                case "forecastleDeck":
                    currentRoom = map.getRoom(userInput);
                    break;
                case "cabin":
                    currentRoom = map.getRoom(userInput);
                    break;
                case "mainDeck":
                    currentRoom = map.getRoom(userInput);
                    break;
            }

            

        }

    }
}