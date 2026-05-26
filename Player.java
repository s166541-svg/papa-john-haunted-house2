import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Player.java ---
import java.util.ArrayList;

/*

Tracks the main player inventory state and active spatial room positioning.

Operates additions and reductions of components acquired via text interaction.

Interacts directly with Game processes during item pickup and room navigation updates.
*/
public class Player {
private ArrayList<Item> inventory;
private Room currentRoom;

/*

Prepares a fresh player container with initialized empty list collections.
*/
public Player() {
this.inventory = new ArrayList<Item>();
}

/*

Identifies where the player is currently positioned.

@return the active Room object reference
*/
public Room getCurrentRoom() {
return currentRoom;
}

/*

Sets the location parameter of the player character.

@param room the new room anchor point
*/
public void setCurrentRoom(Room room) {
this.currentRoom = room;
}

/*

Safely appends an item object into the player's private inventory file.

@param item the target object to save
*/
public void addItem(Item item) {
inventory.add(item);
}

/*

Extracts an item component out of the personal tracking array list by positional integer.

@param index the index reference to destroy
*/
public void removeItem(int index) {
inventory.remove(index);
}

/*

Iterates inventory to search for matching names without using native list shortcuts.

Manual loop required — built-in search methods are not allowed per AP CS A constraints.

@param name the raw string sequence to find

@return the matching discovered Item link, or null if missing
*/
public Item findItem(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (Item item : inventory) {
if (item.getName().equalsIgnoreCase(name)) {
return item;
}
}
return null;
}

/*

Gets the full structural listing container of inventory items.

@return the explicit item array list tracking structure
*/
public ArrayList<Item> getInventory() {
return inventory;
}
}
