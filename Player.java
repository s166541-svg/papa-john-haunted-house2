// --- FILE: Player.java ---
import java.util.ArrayList;

/*

Represents the human player in the game.

This class tracks the player's current location and inventory.

It provides methods for item management and inventory searching.
*/
public class Player {
private Room currentRoom;
private ArrayList inventory;

/*

Constructs a new Player and initializes an empty inventory.
*/
public Player() {
this.inventory = new ArrayList<>();
}

/*

Adds an item to the player's inventory.

@param item The Item to be added.
*/
public void addItem(Item item) {
inventory.add(item);
}

/*

Removes an item from the player's inventory by name.

@param itemName The name of the item to remove.
*/
public void removeItem(String itemName) {
// Manual loop required - built-in search methods are not allowed per AP CS A constraints
for (int i = 0; i < inventory.size(); i++) {
if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
inventory.remove(i);
return;
}
}
}

/*

Finds an item in the player's inventory using a for-each loop.

@param itemName The name of the item to search for.

@return The Item object if found, otherwise null.
*/
public Item findItem(String itemName) {
// Manual loop required - built-in search methods are not allowed per AP CS A constraints
for (Item item : inventory) {
if (item.getName().equalsIgnoreCase(itemName)) {
return item;
}
}
return null;
}

/*

Sets the player's current location.

@param room The Room the player is moving into.
*/
public void setCurrentRoom(Room room) {
this.currentRoom = room;
}

/*

Gets the player's current location.

@return The current Room object.
*/
public Room getCurrentRoom() {
return currentRoom;
}

/*

Gets the player's inventory list.

@return An ArrayList of Items.
*/
public ArrayList getInventory() {
return inventory;
}
}
