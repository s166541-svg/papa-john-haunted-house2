import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Game.java ---
import java.util.ArrayList;
import java.util.Scanner;

/*

The master orchestration layout engine controlling core logic, state flags, and user loops.

Monitors item lookups, movement parsing, turn management alerts, and win/loss rules.

Directs processing from Main down into specific Player, Room, and Item sub-configurations.
*/
public class Game {
private boolean hasBasementKey;
private boolean hasFlashlight;
private boolean hasWeapon;
private boolean hasEatenFood;
private boolean breakLock;
private boolean isGameOver;
private int turnCount;
private ArrayList<Room> rooms;
private Player player;

/*

Sets up default architecture spaces and launches primary world assembly patterns.
*/
public Game() {
this.player = new Player();
initializeRooms();
resetStateFlags();
}

/*

Completely maps out all map nodes, textual descriptions, connections, and items.

Ensures that no automatic dictionary or map associations are called.
*/
private void initializeRooms() {
rooms = new ArrayList<Room>();

// Instantiate all rooms with core data
Room outside = new Room("Outside", "You are standing in a dark forest filled with giant redwood trees. The air is chilly, and the cabin North of you looks very old and shaky. The porch is broken, and some of the windows have cracks in them. To your East, there is a heavy cellar door with a lock. To your West, a small path leads around to the back of the house.");
outside.setAlternateDescription("You take a deep breath, exhilarated from the horrors you experienced in the haunted house. As you close the door behind you, the only option is to head to the basement and find out what’s inside. To your East, there is the coveted basement you finally have the key for. To your West, there is the same path you walked down so long ago.");

Room mainEntrance = new Room("Main Entrance", "The moment you step inside, the door thuds shut, locking out the wind. The air here is stale and smells like old paper. It is very dim, and every step you take makes the floorboards groan like they are complaining. To the West, you hear a slow, heavy snoring sound—huff... wheeze...—as if something very big is dreaming nearby. To the East of you seems to be a kitchen. To the North of you is a bathroom.");

Room bathroom = new Room("Bathroom", "This room is a cold, dark square that smells like wet pennies and soap. The floor is slick and chilly under your feet. Without a light, the darkness here is physical, pressing against your skin like a wet cloth. You grope along the walls, but the shadows are too deep to see anything inside the medicine cabinet or on the floor. To the South of you is the Main Entrance.");
bathroom.setAlternateDescription("As the sharp beam of your Flashlight cuts through the thick, heavy darkness, the room suddenly shudders into view. The light reflects harshly off the yellowed wall tiles, which are slick with a glistening moisture that never seems to dry. The sink is stained with deep, rust-colored streaks that gather around the drain, and the air carries a sharp, stinging scent of bleach and copper.\n\nYour light eventually centers on the medicine cabinet above the sink. The mirror is so layered with grime and grey dust that your reflection looks like a ghost standing in a thick fog. When you pull the small metal handle, the hinges give a piercing, high-pitched squeal that echoes far too loudly in the tiny space. Resting on the top shelf, looking out of place against the empty, glass medicine bottles, is a heavy iron key that glints under your light. To the South of you is the Main Entrance.");

Room backyard = new Room("Backyard", "Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter.");
backyard.setAlternateDescription("Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter. Going West, you can finally leave the house.");

Room storage = new Room("Storage", "This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. And find a box with flashlights.");
storage.setAlternateDescription("This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. Going North, you can finally exit the house.");

Room garage = new Room("Garage", "The dead silence in the garage creates a sense of fear and the cold air pierces your skin. In front of you there is a broken down school bus and a Papa John’s delivery car. Upon exploring, you find a baseball bat. A door leading to the dining room to the West of you and another leading to a Storage room North of you.");

Room diningRoom = new Room("Dining Room", "A long, grand table made of dark wood sits under a layer of grey dust. In the very center, there is a single plate of steaming hot food that looks and smells delicious. It’s very strange because there is nobody around to cook it, and the steam rises into the air in curly, ghostly shapes. To the South is the Kitchen and to the West is the Living Room.");
diningRoom.setAlternateDescription("A long, grand table made of dark wood sits under a layer of grey dust. The silver platter that once held the steaming feast now sits empty, stained with grease and dark crumbs. To the South is the Kitchen and to the West is the Living Room.");

Room kitchen = new Room("Kitchen", "The kitchen is the coldest part of the house. An old, white refrigerator shudders and shakes, making a low humming noise that never stops. Rusted cabinets hang open, showing rows of dusty jars filled with strange, murky liquids. A single faucet drips into the sink—drop... drop... drop—sounding like a ticking clock. To the North is the Dining Room and to the West is the Main Entrance.");

Room livingRoom = new Room("Living Room", "Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. As you step into the center of the room, a floorboard snaps loudly, and the heavy snoring you heard earlier suddenly stops. From the shadows of the corner, a tall, dark figure lunges at you with a jagged blade. Without anything to defend yourself, the cold steel is the last thing you feel. Game Over.");
livingRoom.setAlternateDescription("Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. Suddenly, a dark figure emerges from the gloom, raising a weapon to strike. Instinctively, you swing the baseball bat with all your might. The wood connects with a dull thud, dazing the attacker just long enough for you to scramble backward and slip away into the next room (South) or to the previous room (East). You are safe, for now.");

Room guestRoom = new Room("Guest Room", "The air here is heavy and still, filled with tiny bits of dust dancing in the dark. Long, grey cobwebs hang from the ceiling like messy decorations. A giant bed with a thick, tattered blanket sits in the center. It looks like someone just got up from a nap, leaving a deep dent in the middle of the mattress. To the North is the living room and to the East is the Main Entrance.");

Room basement = new Room("Basement", "You walk down a set of narrow, shaky stairs that moan under your weight. The walls are damp and feel fuzzy to the touch. A long, dark hallway leads to a room where an old TV sits on the floor, flickering with \"snowy\" white static that lights up the room in quick flashes. On the floor, you notice a mountain of valuable loot.\n\nYou WIN!");
basement.setAlternateDescription("Upon reaching the basement door. You are unable to open the door. You notice some sort of lock attached to the door preventing you from entering. You have the option to break the lock or to go back (East) to Outside.");

// Connect spatial coordinate exit arrays exactly as defined by requirements
outside.addExit("North:Main Entrance");
outside.addExit("East:Backyard");
outside.addExit("West:Basement");

mainEntrance.addExit("West:Guest Room");
mainEntrance.addExit("East:Kitchen");
mainEntrance.addExit("North:Bathroom");
mainEntrance.addExit("South:Outside");

bathroom.addExit("South:Main Entrance");

backyard.addExit("South:Storage");
backyard.addExit("West:Outside");

storage.addExit("South:Garage");
storage.addExit("West:Dining Room");
storage.addExit("North:Backyard");

garage.addExit("West:Dining Room");
garage.addExit("North:Storage");

diningRoom.addExit("West:Living Room");
diningRoom.addExit("South:Kitchen");
diningRoom.addExit("northeast:Storage");
diningRoom.addExit("southeast:Garage");

kitchen.addExit("North:Dining Room");
kitchen.addExit("West:Main Entrance");

livingRoom.addExit("South:Guest Room");
livingRoom.addExit("East:Dining Room");

guestRoom.addExit("North:Living Room");
guestRoom.addExit("East:Main Entrance");

basement.addExit("East:Outside");

// Populate actual instances inside tracking layout arrays
bathroom.addItem(new UtilityItem("Basement Key", "A heavy iron key that glints under your light."));
storage.addItem(new UtilityItem("Flashlight", "A sturdy hand-held flashlight."));
garage.addItem(new WeaponItem("Baseball Bat", "A heavy wooden bat, perfect for self-defense."));
guestRoom.addItem(new UtilityItem("Flashlight", "A bright metal flashlight."));

rooms.add(outside);
rooms.add(mainEntrance);
rooms.add(bathroom);
rooms.add(backyard);
rooms.add(storage);
rooms.add(garage);
rooms.add(diningRoom);
rooms.add(kitchen);
rooms.add(livingRoom);
rooms.add(guestRoom);
rooms.add(basement);
}

/*

Resets status flags to original configurations.
*/
private void resetStateFlags() {
this.hasBasementKey = false;
this.hasFlashlight = false;
this.hasWeapon = false;
this.hasEatenFood = false;
this.breakLock = false;
this.isGameOver = false;
this.turnCount = 0;
}

/*

Gets whether the basement key has been fetched.

@return true if held
*/
public boolean isHasBasementKey() {
return hasBasementKey;
}

/*

Gets whether a flashlight is ready.

@return true if held
*/
public boolean isHasFlashlight() {
return hasFlashlight;
}

/*

Gets whether the bat weapon is held.

@return true if held
*/
public boolean isHasWeapon() {
return hasWeapon;
}

/*

Gets whether the feast was eaten.

@return true if eaten
*/
public boolean isHasEatenFood() {
return hasEatenFood;
}

/*

Launches the loop terminal processing until an exit flag evaluates to true.
*/
public void start() {
Scanner scanner = new Scanner(System.in);
player.setCurrentRoom(findRoom("Outside"));

System.out.println("=== WELCOME TO PAPA JOHN'S HAUNTED HOUSE ===");
System.out.println(player.getCurrentRoom().getDescription(this));

while (!isGameOver) {
  System.out.print("\n> ");
  String line = scanner.nextLine();
  if (line == null || line.trim().isEmpty()) {
    continue;
  }
  processCommand(line);
}
scanner.close();
}

/*

Dispatches input directives parsed down from command lines.

@param input the raw string submitted from terminal streams
*/
public void processCommand(String input) {
String lowerInput = input.trim().toLowerCase();

if (lowerInput.equals("look")) {
  Room current = player.getCurrentRoom();
  System.out.println(current.getDescription(this));
  System.out.print("Items here: ");
  ArrayList<Item> items = current.getItems();
  if (items.isEmpty()) {
    System.out.println("None");
  } else {
    // Formats items sequentially without automatic list string methods
    for (int i = 0; i < items.size(); i++) {
      System.out.print(items.get(i).getName() + (i == items.size() - 1 ? "" : ", "));
    }
    System.out.println();
  }
} else if (lowerInput.equals("inventory")) {
  ArrayList<Item> inv = player.getInventory();
  System.out.print("Your Inventory: ");
  if (inv.isEmpty()) {
    System.out.println("Empty");
  } else {
    // Sequential formatting loop avoiding native helper methods
    for (int i = 0; i < inv.size(); i++) {
      System.out.print(inv.get(i).getName() + (i == inv.size() - 1 ? "" : ", "));
    }
    System.out.println();
  }
} else if (lowerInput.startsWith("go ")) {
  String direction = input.substring(3).trim();
  movePlayer(direction);
} else if (lowerInput.startsWith("take ")) {
  String itemName = input.substring(5).trim();
  takeItem(itemName);
} else if (lowerInput.equals("eat food") || lowerInput.equals("eat")) {
  executeEatAction();
} else if (lowerInput.equals("break lock")) {
  executeBreakLockAction();
} else if (lowerInput.equals("restart")) {
  executeRestartAction();
} else if (lowerInput.equals("quit")) {
  isGameOver = true;
  System.out.println("Exiting haunted house. Goodbye!");
} else {
  System.out.println("Unknown command. Available choices: go [direction], take [item], look, inventory, eat food, break lock, restart, quit");
}
}

/*

Navigates rooms using clear exit string structures.

@param direction the direction argument provided by the player
*/
private void movePlayer(String direction) {
Room current = player.getCurrentRoom();
ArrayList<String> exits = current.getExits();
String destination = null;

// Manual separation loop to bypass structural index operations or containment features
for (String exit : exits) {
  String[] components = exit.split(":");
  if (components[0].equalsIgnoreCase(direction)) {
    destination = components[1];
    break;
  }
}

if (destination != null) {
  Room targetRoom = findRoom(destination);
  if (targetRoom != null) {
    // Enforce physical constraints for the Basement door lock
    if (targetRoom.getName().equalsIgnoreCase("Basement") && !hasBasementKey) {
      System.out.println(targetRoom.getDescription(this));
      return;
    }

    // Enforce front door sealing rule for Main Entrance
    if (targetRoom.getName().equalsIgnoreCase("Outside") && current.getName().equalsIgnoreCase("Main Entrance") && !hasBasementKey) {
      System.out.println("The front door snapped shut and locked from the outside when you entered. You can't leave this way yet.");
      return;
    }

    // Evaluate dangerous transitional crossing vectors (Traps)
    if (current.getName().equalsIgnoreCase("Garage") && targetRoom.getName().equalsIgnoreCase("Storage")) {
      System.out.println("DEATH TRAP. As you push open the black metal door from the Garage side, you trigger a hidden tripwire. Before you can even process the sight of the statues, a massive shelf loaded with rusted engine parts collapses from above, pinning you instantly. Game Over.");
      isGameOver = true;
      return;
    }
    if (current.getName().equalsIgnoreCase("Storage") && targetRoom.getName().equalsIgnoreCase("Garage")) {
      System.out.println("DEATH TRAP. You step through the metal door into the garage, but the door slams shut behind you with a mechanical bang. The statues from the previous room begin to push against the wood, trapping you in the freezing dark as the locks hiss shut, leaving you with no way out. Game Over.");
      isGameOver = true;
      return;
    }

    // Reposition Player entity inside management loop
    player.setCurrentRoom(targetRoom);
    System.out.println("\nYou move to: " + targetRoom.getName());

    // Process hostile environmental traps upon entering
    if (targetRoom.getName().equalsIgnoreCase("Living Room") && !hasWeapon) {
      System.out.println(targetRoom.getDescription(this));
      isGameOver = true;
      return;
    }

    if (targetRoom.getName().equalsIgnoreCase("Basement") && hasBasementKey) {
      System.out.println(targetRoom.getDescription(this));
      isGameOver = true;
      return;
    }

    System.out.println(targetRoom.getDescription(this));
    turnCount++;
    processTurn();
  }
} else {
  System.out.println("You cannot go that way.");
}
}

/*

Retrieves an item from the floor and applies game-state transitions.

@param itemName the text sequence parsing out the requested object
*/
private void takeItem(String itemName) {
Room current = player.getCurrentRoom();
ArrayList<Item> roomItems = current.getItems();
int foundIndex = -1;

// Traditional loop to bypass built-in array index matching
for (int i = 0; i < roomItems.size(); i++) {
  if (roomItems.get(i).getName().equalsIgnoreCase(itemName)) {
    foundIndex = i;
    break;
  }
}

if (foundIndex != -1) {
  Item targetItem = roomItems.get(foundIndex);

  // Verify lighting conditions inside Bathroom
  if (targetItem.getName().equalsIgnoreCase("Basement Key") && !hasFlashlight) {
    System.out.println("The room is too dark to find anything inside the medicine cabinet or on the floor.");
    return;
  }

  // Safe index-based array list clear manipulation pattern
  current.removeItem(foundIndex);
  player.addItem(targetItem);
  System.out.println("You picked up the " + targetItem.getName() + ".");

  // Sync specific flag modifications
  if (targetItem.getName().equalsIgnoreCase("Flashlight")) {
    hasFlashlight = true;
  } else if (targetItem.getName().equalsIgnoreCase("Baseball Bat")) {
    hasWeapon = true;
  } else if (targetItem.getName().equalsIgnoreCase("Basement Key")) {
    hasBasementKey = true;
  }

  turnCount++;
  processTurn();
} else {
  System.out.println("That item is not here.");
}
}

/*

Processes the dynamic eating interaction command sequences inside the Dining Room.
*/
private void executeEatAction() {
Room current = player.getCurrentRoom();
if (!current.getName().equalsIgnoreCase("Dining Room")) {
System.out.println("There is nothing edible here.");
return;
}
if (hasEatenFood) {
System.out.println("The feast platter is already cleared.");
return;
}

// Evaluates 50/50 probability engine requested by student parameters
if (Math.random() < 0.5) {
  System.out.println("You eat the food and it fills you with vital energy. You live and continue your journey!");
  hasEatenFood = true;
  turnCount++;
  processTurn();
} else {
  System.out.println("You eat the food... but it was poisoned! You sink into unconsciousness. Game Over.");
  isGameOver = true;
}
}

/*

Activates the lock breaking mechanism on the locked basement entryway doors.
*/
private void executeBreakLockAction() {
Room current = player.getCurrentRoom();
if (!current.getName().equalsIgnoreCase("Outside") && !current.getName().equalsIgnoreCase("Basement")) {
System.out.println("There is no heavy locking device to smash here.");
return;
}
if (hasBasementKey) {
System.out.println("You already hold the needed key tool.");
return;
}

System.out.println("The loud thuds seem to have woken something up. Examining your surroundings you find nothing unusual. Then, from behind a dark figure lunges at you with a jagged blade. The cold steel is the last thing you feel. Gameover.");
isGameOver = true;
}

/*

Triggers a clean game state re-initialization without closing running streams.
*/
private void executeRestartAction() {
// Manually empty out the items list tracking inside player containers
while (!player.getInventory().isEmpty()) {
player.removeItem(0);
}

resetStateFlags();
initializeRooms();
player.setCurrentRoom(findRoom("Outside"));

System.out.println("\n--- GAME RESET TO BEGINNING ---");
System.out.println(player.getCurrentRoom().getDescription(this));
}

/*

Runs end-of-turn calculations and outputs progression alerts.
*/
private void processTurn() {
if (isGameOver) {
return;
}

// Evaluates intervals matching turn timeline requests
if (turnCount == 5) {
  System.out.println("\n[ALERT] You hear strange noises in the house.");
} else if (turnCount == 10) {
  System.out.println("\n[ALERT] You hear Papa John moving closer.");
} else if (turnCount == 15) {
  System.out.println("\n[ALERT] Papa John strikes from the shadows!");
  if (hasWeapon) {
    System.out.println("Instinctively, you swing the baseball bat with all your might. The wood connects with a dull thud, dazing the attacker just long enough for you to stay safe.");
  } else {
    System.out.println("Without anything to defend yourself, the cold steel is the last thing you feel. Game End.");
    isGameOver = true;
  }
}
}

/*

Searches the primary layout array list tracking configurations for explicit name tags.

Manual loop required — built-in search methods are not allowed per AP CS A constraints.

@param name the raw text string pointer to locate

@return the verified Room pointer reference discovered, or null if missing
*/
public Room findRoom(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (Room r : rooms) {
if (r.getName().equalsIgnoreCase(name)) {
return r;
}
}
return null;
}
}
