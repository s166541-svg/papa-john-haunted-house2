import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Item.java ---
/*
Base class representing an item in the game world.

This class serves as the root of the item hierarchy and is extended by specialized item types.

It interacts with Room and Player classes to represent physical entities.
*/
public class Item {
private String name;
private String description;

/*
Constructs an Item with a specified name and description.

@param name the unique name of the item

@param description the verbose description of what the item does or looks like
*/
public Item(String name, String description) {
this.name = name;
this.description = description;
}

/*
Gets the name of the item.

@return the item name string
*/
public String getName() {
return name;
}

/*
Gets the description of the item.

@return the item description text
*/
public String getDescription() {
return description;
}

/*
Overrides the toString method to directly print the item's name.

@return the name of the item
*/
@Override
public String toString() {
return name;
}
}
