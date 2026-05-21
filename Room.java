import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Room.java ---

/*
* Represents a specific map area or location in the game world.
* Tracks item placement, movement pathways, and computes context-aware visual descriptions.
* Used by Game to maintain structural connectivity and by Player to identify current coordinates.
*/
public class Room {
private String name;
private String description;
private ArrayList<Item> items;
private ArrayList<String> exits;

/*
* Constructs a Room instance with an identifier and static fallback text.
* @param name the structural name of the location
* @param description the baseline text description of the area
*/
public Room(String name, String description) {
this.name = name;
this.description = description;
this.items = new ArrayList<Item>();
this.exits = new ArrayList<String>();
}

/*
* Gets the identifier name of the room.
* @return the room name string
*/
public String getName() {
return this.name;
}

/*
* Adds an item asset directly to the room's floor storage.
* @param item the Item object to deposit
*/
public void addItem(Item item) {
this.items.add(item);
}

/*
* Removes an item asset from the room floor using safe index reduction.
* @param name the lookup string of the target item to purge
*/
public void removeItem(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (int i = 0; i < this.items.size(); i++) {
if (this.items.get(i).getName().equalsIgnoreCase(name)) {
this.items.remove(i);
break;
}
}
}

/*
* Appends an exit connection string formatted as Direction:Destination.
* @param direction the action navigation vector keyword
* @param destination the name of the destination room asset
*/
public void addExit(String direction, String destination) {
this.exits.add(direction + ":" + destination);
}

/*
* Accesses the list of exits matching this location.
* @return the collection list of directional strings
*/
public ArrayList<String> getExits() {
return this.exits;
}

/*
* Accesses the list of items resting inside this location.
* @return the collection list of item entities
*/
public ArrayList<Item> getItems() {
return this.items;
}

/*
* Evaluates global flags to display the correct conditional text description.
* @param game the contextual game core instance containing state flags
* @param previousRoom the structural name of the room the player recently came from
* @return the explicit, complete paragraph description dictated by the specification
*/
public String getDescription(Game game, String previousRoom) {
if (this.name.equalsIgnoreCase("Bathroom")) {
if (game.isHasFlashlight()) {
return "As the sharp beam of your Flashlight cuts through the thick, heavy darkness, the room suddenly shudders into view. The light reflects harshly off the yellowed wall tiles, which are slick with a glistening moisture that never seems to dry. The sink is stained with deep, rust-colored streaks that gather around the drain, and the air carries a sharp, stinging scent of bleach and copper.\n\nYour light eventually centers on the medicine cabinet above the sink. The mirror is so layered with grime and grey dust that your reflection looks like a ghost standing in a thick fog. When you pull the small metal handle, the hinges give a piercing, high-pitched squeal that echoes far too loudly in the tiny space. Resting on the top shelf, looking out of place against the empty, glass medicine bottles, is a heavy iron key that glints under your light.";
} else {
return "This room is a cold, dark square that smells like wet pennies and soap. The floor is slick and chilly under your feet. Without a light, the darkness here is physical, pressing against your skin like a wet cloth. You grope along the walls, but the shadows are too deep to see anything inside the medicine cabinet or on the floor.";
}
}


if (this.name.equalsIgnoreCase("Dining Room")) {
  if (game.isHasEatenFood()) {
    return "A long, grand table made of dark wood sits under a layer of grey dust. The silver platter that once held the steaming feast now sits empty, stained with grease and dark crumbs.";
  } else {
    return "A long, grand table made of dark wood sits under a layer of grey dust. In the very center, there is a single plate of steaming hot food that looks and smells delicious. It’s very strange because there is nobody around to cook it, and the steam rises into the air in curly, ghostly shapes.";
  }
}

if (this.name.equalsIgnoreCase("Living Room")) {
  if (game.isHasWeapon()) {
    return "Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. Suddenly, a dark figure emerges from the gloom, raising a weapon to strike. Instinctively, you swing the baseball bat with all your might. The wood connects with a dull thud, dazing the attacker just long enough for you to scramble backward and slip away into the next room. You are safe, for now.";
  } else {
    return "Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. As you step into the center of the room, a floorboard snaps loudly, and the heavy snoring you heard earlier suddenly stops. From the shadows of the corner, a tall, dark figure lunges at you with a jagged blade. Without anything to defend yourself, the cold steel is the last thing you feel. Game Over.";
  }
}

if (this.name.equalsIgnoreCase("Storage")) {
  if (previousRoom != null && previousRoom.equalsIgnoreCase("Garage")) {
    return "DEATH TRAP. As you push open the black metal door from the Garage side, you trigger a hidden tripwire. Before you can even process the sight of the statues, a massive shelf loaded with rusted engine parts collapses from above, pinning you instantly. Game Over.";
  }
  return this.description;
}

if (this.name.equalsIgnoreCase("Garage")) {
  if (previousRoom != null && previousRoom.equalsIgnoreCase("Storage")) {
    return "DEATH TRAP. You step through the metal door into the garage, but the door slams shut behind you with a mechanical bang. The statues from the previous room begin to push against the wood, trapping you in the freezing dark as the locks hiss shut, leaving you with no way out. Game Over.";
  }
  return this.description;
}

if (this.name.equalsIgnoreCase("Basement")) {
  if (game.isHasBasementKey()) {
    return "You walk down a set of narrow, shaky stairs that moan under your weight. The walls are damp and feel fuzzy to the touch. A long, dark hallway leads to a room where an old TV sits on the floor, flickering with \"snowy\" white static that lights up the room in quick flashes. On the floor, a dark, sticky trail leads toward a big iron cage.";
  } else if (game.isBreakLock()) {
    return "The loud thuds seem to have woken something up. Examining your surroundings you find nothing unusual. Then, from behind a dark figure lunges at you with a jagged blade. The cold steel is the last thing you feel. Gameover.";
  } else {
    return "Upon reaching the basement door. You are unable to open the door. You notice some sort of lock attached to the door preventing you from entering.";
  }
}

return this.description;



}
}
