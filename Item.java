// --- FILE: Item.java ---
/*

Base class for all interactive objects in the game.

This class provides the fundamental structure for items, including a name

and description. It serves as the parent for specialized item types like

UtilityItem and WeaponItem.
*/
public abstract class Item {
private String name;
private String description;

/*

Constructs a new Item with a name and description.

@param name The display name of the item.

@param description The detailed description of the item.
*/
public Item(String name, String description) {
this.name = name;
this.description = description;
}

/*

Retrieves the name of the item.

@return The item's name.
*/
public String getName() {
return name;
}

/*

Provides a string representation of the item for inventory display.

@return The item's name.
*/
@Override
public String toString() {
return name;
}
}
