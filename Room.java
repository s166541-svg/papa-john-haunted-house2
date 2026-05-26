import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Room.java ---
import java.util.ArrayList;

/*
Represents a physical grid zone inside or outside the haunted house.

Holds name data, static descriptions, dynamic state tracking, items, and map exits.

Interacts directly with Game for map traversal and Player for target items.
*/
public class Room {
private String name;
private String baseDescription;
private String alternateDescription;
private ArrayList<Item> items;
private ArrayList<String> exits;

/*
Constructs a Room with a fixed name and a standard base description text.

@param name the unique name mapping this room

@param baseDescription the default flavor text printed when entering
*/
public Room(String name, String baseDescription) {
this.name = name;
this.baseDescription = baseDescription;
this.alternateDescription = "";
this.items = new ArrayList<Item>();
this.exits = new ArrayList<String>();
}

/*

Links a secondary state text narrative used for dynamic room updates.

@param alternateDescription the text narrative triggered by global state updates
*/
public void setAlternateDescription(String alternateDescription) {
this.alternateDescription = alternateDescription;
}

/*

Gets the name field of the room object.

@return the name string
*/
public String getName() {
return name;
}

/*

Computes the current visible room description based on the global flags inside Game.

Resolves the student spec specifications dynamically per environmental factors.

@param game the master game tracking container containing state flags

@return the correct dynamic block text string matching current status
*/
public String getDescription(Game game) {
if (name.equalsIgnoreCase("Outside")) {
if (game.isHasBasementKey()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Bathroom")) {
if (game.isHasFlashlight()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Backyard")) {
if (game.isHasBasementKey()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Storage")) {
if (game.isHasBasementKey()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Dining Room")) {
if (game.isHasEatenFood()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Living Room")) {
if (game.isHasWeapon()) {
return alternateDescription;
}
return baseDescription;
} else if (name.equalsIgnoreCase("Basement")) {
if (game.isHasBasementKey()) {
return baseDescription;
}
return alternateDescription;
}
return baseDescription;
}

/*

Adds an item to the structural room grid.

@param item the item reference to place
*/
public void addItem(Item item) {
items.add(item);
}

/*

Removes an item from the floor room list via index value.

Uses clear indexing to guarantee that no implicit object checks occur.

@param index the explicit coordinate pointer of the item arraylist
*/
public void removeItem(int index) {
items.remove(index);
}

/*

Gets the list of current floor item contents.

@return the internal array list tracking items
*/
public ArrayList<Item> getItems() {
return items;
}

/*

Configures a navigation target using standard string pairing convention.

@param exit the "Direction:Destination" composite label
*/
public void addExit(String exit) {
exits.add(exit);
}

/*

Gets the total active paths leading away from this zone.

@return the list configuration of text paths
*/
public ArrayList<String> getExits() {
return exits;
}
}
