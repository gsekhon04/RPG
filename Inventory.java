/**
 * Gunar Sekhon
 * Programming Assignment 2: RPG
 * Section: 020
 * 05/04/23
 */

//Imports ArrayList class
import java.util.ArrayList;

public class Inventory {
    
    //A boolean that is true if a certain item is detected in the player's inventory
    private boolean hasItem = false;

    //An ArrayList that stores all items in the player's inventory
    private ArrayList<String> inventory;

    /**
     * Constructor that creates a new inventory ArrayLlist and gives it "Coin" as a starter item
     */
    public Inventory() {
        inventory = new ArrayList<String>();
        inventory.add("Coin");
    }

    /**
     * Adds an item to the inventory Arraylist
     * @param item- A string of the name of the item to be added
     */
    public void addItem (String item) {
        inventory.add(item);
    }

    /**
     * Removes an item from the inventory ArrayList
     * @param item- A string of the name of the item to be removed
     */
    public void removeItem (String item) {
        inventory.remove(item);
    }

    /**
     * Prints the player's current inventory
     * Prints "You have nothing in your inventory." if inventory is empty
     */
    public void printInventory() {
        //If inventory is empty
        if (inventory.size() == 0) {

            System.out.println("You have nothing in your inventory.");

        } else { //If inventory is not empty
            
            System.out.println("Inventory:");
            //Iterates through inventory ArrayList and prints each item in it
            for (String s : inventory) {
                System.out.println(s);
            }

        }
    }

    /**
     * Checks if player has a certain item in their inventory
     * @param item- String of the name of the item being looked for
     * @return- Boolean that tells if the item was found. True if found, else false;
     */
    public boolean hasItem (String item) {
        //Resets hasItem boolean for each check
        hasItem = false;

        //Iterates through inventory ArrayList and if the item is found, sets hasItem to true
        for (String s : inventory) {
            if (s.equalsIgnoreCase(item)) {
                hasItem = true;
            }
        }

        return hasItem;
    }

}