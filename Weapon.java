// --- FILE: Weapon.java ---
/*

Represents a weapon item used for defense.

This class extends Item to categorize objects that can protect the player.

It is used by the Game class to determine survival during hostile encounters.
*/
public class Weapon extends Item {
/*

Constructs a new Weapon item.

@param name the name of the weapon

@param description the description of the weapon
*/
public Weapon(String name, String description) {
super(name, description);
}
}
