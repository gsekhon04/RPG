import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private boolean validAction = false;
    private ArrayList<String> actions = new ArrayList<String>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void addActions (String action) {
        actions.add(action);
    }

    public void removeActions (String action) {
        actions.remove(action);
    }

    public String getName() {
        return this.name;
    }

    public String getActions() {
        String actionString = "Please select";

        for (String a : actions) {
            actionString = actionString + " " + a;
        }

        return actionString;
    }

    public boolean validAction(String action) {
        validAction = false;

        for (String a : actions) {
            if (a.equalsIgnoreCase(action)) {
                validAction = true;
            }
        }

        return validAction;
    }

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     * @return- Returns a String of the description of the room and its exits
     */
    public String toString() {
        String roomToString = this.name + ": " + this.description + "\n" + "\n" + getActions();
        return roomToString;
    }
}