// --- FILE: Item.java ---
/*

Base class for all items in the game world.

Provides the foundation for inventory objects with names and descriptions.
*/
public class Item {
private String name;
private String description;

/*

Constructs an Item.

@param name The display name of the item

@param description The sensory description of the item
*/
public Item(String name, String description) {
this.name = name;
this.description = description;
}

public String getName() { return name; }
public String getDescription() { return description; }

@Override
public String toString() {
return name;
}
}




