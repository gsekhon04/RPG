import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private ArrayList<String> exits = new ArrayList<String>;
    private boolean validExit = false;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void addExit (String exit) {
        exits.add(exit);
    }

    public boolean validExit(String exit) {
        validExit = false;

        for (String s : exits) {
            if (s.equalsIgnoreCase(exit)) {
                validExit = true;
            }
        }

        return validExit;
    }

    public String getName() {
        return this.name;
    }

    /**
     * List all of the rooms as a string
     * @return returns all of the names of the rooms on new lines
     */
    public String listExits() {
        String exitString = "Exits:\n";
        for (String s : exits) {
            exitString = exitString + s + "\n";
        }

        return exitString;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     * @return- Returns a String of the description of the room and its exits
     */
    public String toString() {
        String roomToString = this.name + ": " + this.description + "\n" + "\n" + listExits();
        return roomToString;
    }
}