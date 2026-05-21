import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Player.java ---


/*
* Tracks individual status attributes and equipment inventories of the user.
* Coordinates player inventory storage and maintains spatial tracking of the active room.
* Utilized across Game logic streams to inspect or update items carried.
*/
public class Player {
private ArrayList<Item> inventory;
private Room currentRoom;

/*
* Initializes the player with a clean inventory state.
*/
public Player() {
this.inventory = new ArrayList<Item>();
}

/*
* Returns the current room resource coordinates.
* @return the Room entity currently occupied
*/
public Room getCurrentRoom() {
return this.currentRoom;
}

/*
* Shifts player coordinates to a new target room location.
* @param currentRoom the destination Room object
*/
public void setCurrentRoom(Room currentRoom) {
this.currentRoom = currentRoom;
}

/*
* Commits an item item into the tracking array list.
* @param item the item block to add
*/
public void addItem(Item item) {
this.inventory.add(item);
}

/*
* Extracts an item asset using safe sequential index logic.
* @param name the explicit string name of the object to look up and purge
*/
public void removeItem(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (int i = 0; i < this.inventory.size(); i++) {
if (this.inventory.get(i).getName().equalsIgnoreCase(name)) {
this.inventory.remove(i);
break;
}
}
}

/*
* Traverses inventory contents to locate an item match.
* @param name the lookup string of the item sought
* @return the Item matching the name parameter, or null if unassigned
*/
public Item findItem(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (Item item : this.inventory) {
if (item.getName().equalsIgnoreCase(name)) {
return item;
}
}
return null;
}

/*
* Yields direct lookup read access to the master inventory tracker.
* @return the raw inventory collection list
*/
public ArrayList<Item> getInventory() {
return this.inventory;
}
}
