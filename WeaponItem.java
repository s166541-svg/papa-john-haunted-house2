// --- FILE: WeaponItem.java ---
/*

Represents items used for defense.

This class extends Item and is specifically used for the Baseball Bat,

which changes the outcome of encounters with Papa John in the Living Room.
*/
public class WeaponItem extends Item {
/*

Constructs a WeaponItem.

@param name The name of the weapon.

@param description The description of the weapon.
*/
public WeaponItem(String name, String description) {
super(name, description);
}
}
