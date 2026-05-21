import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Utility.java ---
/*
* Represents a utility item in the game world.
* Demonstrates inheritance by extending Item to group specific functional tools.
* Used by Game to check progression prerequisites, such as tracking illumination tools or keys.
*/
public class Utility extends Item {
/*
* Constructs a Utility item.
* @param name the unique name of the utility item
* @param description the descriptive flavor text of the utility item
*/
public Utility(String name, String description) {
super(name, description);
}
}
