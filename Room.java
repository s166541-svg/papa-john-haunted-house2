// --- FILE: Room.java ---
import java.util.ArrayList;

/*
Represents a location within the game world.

This class stores room-specific data including names, descriptions,

items, and exits. It handles dynamic descriptions based on the

current state of the Game.
*/
public class Room {
private String name;
private String description;
private ArrayList<Item> items;
private ArrayList<String> exits;

/*
Constructs a Room with a name and base description.

@param name The unique name of the room.

@param description The default text describing the room.
*/
public Room(String name, String description) {
this.name = name;
this.description = description;
this.items = new ArrayList<Item>();
this.exits = new ArrayList<String>();
}

/*
Adds an item to the room's inventory.

@param item The Item object to place in the room.
*/
public void addItem(Item item) {
items.add(item);
}

/*
Removes an item from the room by name.

@param itemName The name of the item to remove.
*/
public void removeItem(String itemName) {
for (int i = 0; i < items.size(); i++) {
if (items.get(i).getName().equalsIgnoreCase(itemName)) {
items.remove(i);
return;
}
}
}

/*
Defines a connection to another room.

@param direction The direction to travel (e.g., North).

@param destination The name of the destination room.
*/
public void addExit(String direction, String destination) {
exits.add(direction + ":" + destination);
}

/*
Generates the room's description based on the global game state.

@param game The current Game instance to check state flags.

@return The specific description text relevant to the current state.
*/
public String getDescription(Game game) {
if (name.equalsIgnoreCase("Outside")) {
if (game.isHasBasementKey()) {
return "You take a deep breath, exhilarated from the horrors you experienced in the haunted house. As you close the door behind you, the only option is to head to the basement and find out what’s inside. To your East, there is the coveted basement you finally have the key for. To your West, there is the same path you walked down so long ago.";
}
return "You are standing in a dark forest filled with giant redwood trees. The air is chilly, and the cabin North of you looks very old and shaky. The porch is broken, and some of the windows have cracks in them. To your East, there is a heavy cellar door with a lock. To your West, a small path leads around to the back of the house.";
}

if (name.equalsIgnoreCase("Bathroom")) {
  if (game.isHasFlashlight()) {
    return "As the sharp beam of your Flashlight cuts through the thick, heavy darkness, the room suddenly shudders into view. The light reflects harshly off the yellowed wall tiles, which are slick with a glistening moisture that never seems to dry. The sink is stained with deep, rust-colored streaks that gather around the drain, and the air carries a sharp, stinging scent of bleach and copper. Your light eventually centers on the medicine cabinet above the sink. The mirror is so layered with grime and grey dust that your reflection looks like a ghost standing in a thick fog. When you pull the small metal handle, the hinges give a piercing, high-pitched squeal that echoes far too loudly in the tiny space. Resting on the top shelf, looking out of place against the empty, glass medicine bottles, is a heavy iron key that glints under your light. To the South of you is the Main Entrance. Take the Basement Key? “Take Basement Key”";
  }
  return "This room is a cold, dark square that smells like wet pennies and soap. The floor is slick and chilly under your feet. Without a light, the darkness here is physical, pressing against your skin like a wet cloth. You grope along the walls, but the shadows are too deep to see anything inside the medicine cabinet or on the floor. To the South of you is the Main Entrance.";
}

if (name.equalsIgnoreCase("Backyard")) {
  if (game.isHasBasementKey()) {
    return "Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter. Going West, you can finally leave the house.";
  }
  return "Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter.";
}

if (name.equalsIgnoreCase("Storage")) {
  if (game.isHasBasementKey()) {
    return "This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. Going North, you can finally exit the house.";
  }
  return "This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. And find a box with flashlights. Take the flashlight? “Take Flashlight”";
}

if (name.equalsIgnoreCase("Dining Room")) {
  if (game.isHasEatenFood()) {
    return "A long, grand table made of dark wood sits under a layer of grey dust. The silver platter that once held the steaming feast now sits empty, stained with grease and dark crumbs. To the South is the Kitchen and to the West is the Living Room.";
  }
  return "A long, grand table made of dark wood sits under a layer of grey dust. In the very center, there is a single plate of steaming hot food that looks and smells delicious. It’s very strange because there is nobody around to cook it, and the steam rises into the air in curly, ghostly shapes. To the South is the Kitchen and to the West is the Living Room. Eat the food? “Eat Food”";
}

if (name.equalsIgnoreCase("Living Room")) {
  if (game.isHasWeapon()) {
    return "Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. Suddenly, a dark figure emerges from the gloom, raising a weapon to strike. Instinctively, you swing the baseball bat with all your might. The wood connects with a dull thud, dazing the attacker just long enough for you to scramble backward and slip away into the next room (South) or to the previous room (East). You are safe, for now.";
  }
  return "Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. As you step into the center of the room, a floorboard snaps loudly, and the heavy snoring you heard earlier suddenly stops. From the shadows of the corner, a tall, dark figure lunges at you with a jagged blade. Without anything to defend yourself, the cold steel is the last thing you feel. Game Over.";
}

if (name.equalsIgnoreCase("Basement")) {
  if (game.isHasBasementKey()) {
    return "You walk down a set of narrow, shaky stairs that moan under your weight. The walls are damp and feel fuzzy to the touch. A long, dark hallway leads to a room where an old TV sits on the floor, flickering with \"snowy\" white static that lights up the room in quick flashes. On the floor, you notice a mountain of valuable loot.\n\nYou WIN!";
  }
  return "Upon reaching the basement door. You are unable to open the door. You notice some sort of lock attached to the door preventing you from entering. You have the option to break the lock or to go back (East) to Outside.\n\nBreak the Lock? “Break Lock”";
}

return description;
}

/*
Retrieves the list of exits for this room.

@return An ArrayList of exit strings in "Direction:Destination" format.
*/
public ArrayList<String> getExits() {
return exits;
}

/*
Retrieves the items currently in the room.

@return An ArrayList of Item objects.
*/
public ArrayList<Item> getItems() {
return items;
}

/*
Retrieves the name of the room.

@return The room's name.
*/
public String getName() {
return name;
}
}
