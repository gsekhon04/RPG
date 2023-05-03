/**
 * Gunar Sekhon
 * Programming Assignment 2: RPG
 * Section: 020
 * 05/04/23
 */

//Imports ArrayList class
import java.util.ArrayList;

public class Room {
    
    //Declares Strings for the name and description of each room
    private String name;
    private String description;

    //Initializes a boolean that represents if an inputted action is valid
    private boolean validAction = false;

    //Initializes an ArrayList that holds all valid actions to be performed in this room
    private ArrayList<String> actions = new ArrayList<String>();

    /**
     * Initialize a room
     * @param name the name of the room
     * @param description the description of the room
     * Code from lab 12 template by Evan Kessler
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Set the description of a room
     * @param description- A description of the room
     */
    public void setDescription (String description) {
        this.description = description;
    }

    /**
     * Adds an action to actions ArrayList
     * @param action- A string of the name of the action to be added
     */
    public void addActions (String action) {
        actions.add(action);
    }

    /**
     * Removes an action from actions ArrayList
     * @param action- A string of the name of the action to be removed
     */
    public void removeActions (String action) {
        actions.remove(action);
    }

    /**
     * Returns name of the room
     * @return- String of the name of the room
     * Code from lab 12 template by Evan Kessler
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a string of all possible actions
     * @return- String of all actions in quotation marks separated by spaces
     */
    public String getActions() {
        //Initializes actionString with message to be followed by all options
        String actionString = "Please select one of the following:";

        //Iterates through actions ArrayList and adds each one surrounded by "" to actionString with spaces between each option.
        for (String a : actions) {
            actionString = actionString + " " + "\"" + a + "\"   ";
        }

        return actionString;
    }

    /**
     * Checks if an inputted action is valid
     * @param action- String of the name of the action to be tested
     * @return- A boolean that is true if the action is valid and false otherwise.
     */
    public boolean validAction(String action) {
        //Resets validAction to false for each test
        validAction = false;

        //Iterates through actions ArrayList and if inputted action is found, sets validAction to true.
        for (String a : actions) {
            if (a.equalsIgnoreCase(action)) {
                validAction = true;
            }
        }

        return validAction;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the actions.
     * @return- Returns a String of the description of the room and its actions
     */
    public String toString() {
        String roomToString = getName() + ": " + this.description + "\n" + "\n" + getActions();
        return roomToString;
    }
}