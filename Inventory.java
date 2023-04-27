import java.util.ArrayList;

public class Inventory {
    private boolean hasItem = false;
    private ArrayList<String> inventory;

    public Inventory() {
        inventory = new ArrayList<String>();
        inventory.add("Coin");
    }

    public void addItem (String item) {
        inventory.add(item);
    }

    public void removeItem (String item) {
        inventory.remove(item);
    }

    public void printInventory() {
        for (String s : inventory) {
            System.out.println(s);
        }
    }

    public boolean hasItem (String item) {
        hasItem = false;

        for (String s : inventory) {
            if (s.equalsIgnoreCase(item)) {
                hasItem = true;
            }
        }

        return hasItem;
    }

}