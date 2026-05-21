import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Weapon.java ---
/*
* Represents a weapon item in the game world.
* Demonstrates inheritance by extending Item to identify defensive tools.
* Used by Game to verify if the player can defend themselves against hostile encounters.
*/
public class Weapon extends Item {
/*
* Constructs a Weapon item.
* @param name the unique name of the weapon
* @param description the descriptive flavor text of the weapon
*/
public Weapon(String name, String description) {
super(name, description);
}
}
