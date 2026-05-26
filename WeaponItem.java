// --- FILE: WeaponItem.java ---
/*
Represents a protective combat tool used for defending against hostile threats.

Demonstrates inheritance by extending Item and adding specialized context for defenses.

Evaluated by Game to determine if the player survives dangerous encounters with Papa John.
*/
public class WeaponItem extends Item {
/*

Constructs a WeaponItem by routing fields to the parent constructor.

@param name the name of the protective gear

@param description the text describing the weapon
*/
public WeaponItem(String name, String description) {
super(name, description);
}
}
