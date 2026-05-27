// --- FILE: UtilityItem.java ---
/*

Represents items used for progression or utility.

This class extends Item and is used for objects like the Flashlight or

the Basement Key which are required to unlock certain areas or interactions.
*/
public class UtilityItem extends Item {
/*

Constructs a UtilityItem.

@param name The name of the utility item.

@param description The description of the utility item.
*/
public UtilityItem(String name, String description) {
super(name, description);
}
}
