import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private boolean validExit = false;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setDescription (String description) {
        this.description = description;
    }


    public String getName() {
        return this.name;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     * @return- Returns a String of the description of the room and its exits
     */
    public String toString() {
        String roomToString = this.name + ": " + this.description + "\n";
        return roomToString;
    }
}