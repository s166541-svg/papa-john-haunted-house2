import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Item.java ---
/*
* Represents a generic item in the game.
* This class serves as the base class for all collectible or interactive entities
* within the game world. It is utilized by Room and Player classes to manage collections
* of items using standard ArrayList structures.
*/
public class Item {
private String name;
private String description;

/*
* Constructs an Item with a name and description.
* @param name the unique name of the item
* @param description the detailed flavor text of the item
*/
public Item(String name, String description) {
this.name = name;
this.description = description;
}

/*
* Gets the name of the item.
* @return the name of the item
*/
public String getName() {
return this.name;
}

/*
* Gets the description of the item.
* @return the item description
*/
public String getDescription() {
return this.description;
}

/*
* Returns the item's name as its string representation.
* @return the name of the item
*/
@Override
public String toString() {
return this.name;
}
}
