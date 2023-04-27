public class RPGEngine {
    public static void main (String[] args) {

        Room currentRoom = new Room("Port", "A large port for pirates with several ships docked. You are standing in front of the Raven, a large black ship.");

        Room mainDeck = new Room ("mainDeck", "The main portion of the ship. There is the helm to the back, the captains quarters underneath it, the forecastle deck to the front, and a the cabin underneath.");

        Room helm = new Room ("helm", "The steering wheel of the ship. The captain stands there to guide the her and her crew.");

        Room captainsQuarters = new Room("captainsQuarters", "The place where the captain sleeps. It is his personal room and nobody goes in without permission.");

        Room forecastleDeck = new Room("forecastleDeck", "The front of the ship. From here you can see the expansive sea in all its glory");

        Room cabin = new Room("cabin", "The area under the deck. This is where the crew stays and also where bulk items are stored.");
        

    }
}