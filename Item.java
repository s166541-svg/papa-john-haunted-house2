import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Item.java ---
/*

Represents a generic item in the game world.

This class serves as the base class for all items, demonstrating inheritance.

It is used by the Player and Room classes to manage collections of objects.
*/
public class Item {
private String name;
private String description;

/*

Constructs a new Item with a name and description.

@param name the name of the item

@param description the description of what the item is
*/
public Item(String name, String description) {
this.name = name;
this.description = description;
}

/*

Returns the name of the item.

@return the item name
*/
public String getName() {
return name;
}

/*

Returns the description of the item.

@return the item description
*/
public String getDescription() {
return description;
}

/*

Provides a string representation of the item.

@return the name of the item
*/
@Override
public String toString() {
return name;
}
}
