// --- FILE: Utility.java ---
/*

Represents a utility item that provides specific functionality.

This class extends Item to categorize objects like keys or flashlights.

It is used by the Game class to verify prerequisites for certain actions.
*/
public class Utility extends Item {
/*

Constructs a new Utility item.

@param name the name of the utility item

@param description the description of the utility item
*/
public Utility(String name, String description) {
super(name, description);
}
}
