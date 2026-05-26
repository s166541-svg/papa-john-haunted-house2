import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: UtilityItem.java ---
/

Represents a utility item used for puzzle progression or environmental illumination.

Demonstrates inheritance by extending Item and providing specific context for progression objects.

Used by Player and Game to bypass physical barriers like dark rooms or heavy locks.
/
public class UtilityItem extends Item {
/*

Constructs a UtilityItem by routing fields to the parent constructor.

@param name the descriptive name of the utility object

@param description the detailed layout text of the utility object
*/
public UtilityItem(String name, String description) {
super(name, description);
}
}
