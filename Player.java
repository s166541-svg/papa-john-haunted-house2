import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Player.java ---

/*

Represents the player character in the game.

This class manages the player's inventory and current location. It interacts

with the Room and Item classes to facilitate movement and item collection.
*/
public class Player {
private ArrayList inventory;
private Room currentRoom;

/*

Constructs a new Player.
*/
public Player() {
this.inventory = new ArrayList();
}

/*

Adds an item to the player's inventory.

@param item the Item to add
*/
public void addItem(Item item) {
inventory.add(item);
}

/*

Removes an item from the player's inventory by name.

@param itemName the name of the item to remove
*/
public void removeItem(String itemName) {
// Manual loop required per constraints
for (int i = 0; i < inventory.size(); i++) {
if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
inventory.remove(i);
return;
}
}
}

/*

Finds an item in the inventory using a for-each loop.

@param itemName the name of the item to find

@return the Item if found, otherwise null
*/
public Item findItem(String itemName) {
for (Item item : inventory) {
if (item.getName().equalsIgnoreCase(itemName)) {
return item;
}
}
return null;
}

/*

Returns the player's current inventory.

@return the ArrayList of Items
*/
public ArrayList getInventory() {
return inventory;
}

/*

Returns the player's current room.

@return the current Room object
*/
public Room getCurrentRoom() {
return currentRoom;
}

/*

Sets the player's current room.

@param currentRoom the Room to move the player to
*/
public void setCurrentRoom(Room currentRoom) {
this.currentRoom = currentRoom;
}
}
