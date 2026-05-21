import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Game.java ---

/*
* Governs the foundational loop structure and interaction states of the game.
* Manages game clock processing, processes inputs, initializes world content,
* and processes progress mechanics. Interacts directly with Player and Room objects.
*/
public class Game {
private Player player;
private ArrayList<Room> rooms;
private int turnCount;
private boolean isRunning;
private boolean isGameOver;
private String previousRoomName;

// Global state flags defined by specification rules
private boolean hasFlashlight;
private boolean hasBasementKey;
private boolean hasEatenFood;
private boolean hasWeapon;
private boolean breakLock;

/*
* Initializes state trackers and prepares world collections.
*/
public Game() {
this.rooms = new ArrayList<Room>();
this.player = new Player();
this.turnCount = 0;
this.isRunning = true;
this.isGameOver = false;
this.previousRoomName = null;


this.hasFlashlight = false;
this.hasBasementKey = false;
this.hasEatenFood = false;
this.hasWeapon = false;
this.breakLock = false;

this.initializeWorld();



}

/*
* Returns validation tracking status of the flash lighting asset.
* @return true if flashlight state criteria met
*/
public boolean isHasFlashlight() {
return this.hasFlashlight;
}

/*
* Returns verification on whether the cellar lock key was acquired.
* @return true if the inventory holds the baseline room key
*/
public boolean isHasBasementKey() {
return this.hasBasementKey;
}

/*
* Evaluates if eating event loops occurred inside the dining map block.
* @return true if the item consumption process executed
*/
public boolean isHasEatenFood() {
return this.hasEatenFood;
}

/*
* Checks whether the user holds a verified weapon asset for combat.
* @return true if defensive item criteria has been satisfied
*/
public boolean isHasWeapon() {
return this.hasWeapon;
}

/*
* Tracks whether destructive entrance attempts occurred outside the cellar.
* @return true if force structural breaches occurred
*/
public boolean isBreakLock() {
return this.breakLock;
}

/*
* Standardized generator tracking setup routine for all map tiles and assets.
*/
private void initializeWorld() {
Room outside = new Room("Outside", "You are standing in a dark forest filled with giant redwood trees. The air is chilly, and the cabin in front of you looks very old and shaky. The porch is broken, and some of the windows have cracks in them. To your left, there is a heavy cellar door with a lock. To your right, a small path leads around to the back of the house.");
Room mainEntrance = new Room("Main Entrance", "The moment you step inside, the door thuds shut, locking out the wind. The air here is stale and smells like old paper. It is very dim, and every step you take makes the floorboards groan like they are complaining. From somewhere deep in the house, you hear a slow, heavy snoring sound—huff... wheeze...—as if something very big is dreaming nearby.");
Room bathroom = new Room("Bathroom", ""); // Dynamic completely
Room backyard = new Room("Backyard", "Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house, waiting for you to enter.");
Room storage = new Room("Storage", "This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you.");
Room garage = new Room("Garage", "The dead silence in the garage creates a sense of fear and the cold air pierces your skin. In front of you there is a broken down school bus and a Papa John’s delivery car. Upon exploring, you find a baseball bat. You can enter a door to the west of you and to the north of you.");
Room diningRoom = new Room("Dining Room", ""); // Dynamic
Room kitchen = new Room("Kitchen", "The kitchen is the coldest part of the house. An old, white refrigerator shudders and shakes, making a low humming noise that never stops. Rusted cabinets hang open, showing rows of dusty jars filled with strange, murky liquids. A single faucet drips into the sink—drop... drop... drop—sounding like a ticking clock.");
Room livingRoom = new Room("Living Room", ""); // Dynamic
Room guestRoom = new Room("Guest Room", "The air here is heavy and still, filled with tiny bits of dust dancing in the dark. Long, grey cobwebs hang from the ceiling like messy decorations. A giant bed with a thick, tattered blanket sits in the center. It looks like someone just got up from a nap, leaving a deep dent in the middle of the mattress.");
Room basement = new Room("Basement", ""); // Dynamic


// Add exits
outside.addExit("West", "Basement");
outside.addExit("North", "Main Entrance");
outside.addExit("East", "Backyard");

mainEntrance.addExit("West", "Guest Room");
mainEntrance.addExit("East", "Kitchen");
mainEntrance.addExit("North", "Bathroom");

bathroom.addExit("South", "Main Entrance");

backyard.addExit("South", "Storage");
backyard.addExit("West", "Outside");

storage.addExit("South", "Garage");
storage.addExit("West", "Dining Room");

garage.addExit("West", "Dining Room");
garage.addExit("North", "Storage");

diningRoom.addExit("West", "Living Room");
diningRoom.addExit("South", "Kitchen");
diningRoom.addExit("North East", "Storage");
diningRoom.addExit("South East", "Garage");

kitchen.addExit("North", "Dining Room");
kitchen.addExit("West", "Main Entrance");

livingRoom.addExit("South", "Guest Room");
livingRoom.addExit("East", "Dining Room");

guestRoom.addExit("North", "Living Room");
guestRoom.addExit("East", "Main Entrance");

basement.addExit("East", "Outside");

// Add items per specification layout guidelines
guestRoom.addItem(new Utility("Flashlight", "A simple utility item that emits a sharp beam of light."));
storage.addItem(new Utility("Flashlight", "A secondary hand-held light source found among statues."));
garage.addItem(new Weapon("Baseball bat", "A sturdy wooden bat useful for emergency defense."));
bathroom.addItem(new Utility("Basement Key", "A heavy iron key discovered in the dark cabinet."));

// Populate tracking list manually
this.rooms.add(outside);
this.rooms.add(mainEntrance);
this.rooms.add(bathroom);
this.rooms.add(backyard);
this.rooms.add(storage);
this.rooms.add(garage);
this.rooms.add(diningRoom);
this.rooms.add(kitchen);
this.rooms.add(livingRoom);
this.rooms.add(guestRoom);
this.rooms.add(basement);

// Initial setup placement definition
this.player.setCurrentRoom(outside);

}

/*
* Traverses structural map records to match target key indices.
* @param name the lookup room string to parse
* @return the explicit Room reference found, or null if unassigned
*/
public Room findRoom(String name) {
// Manual loop required — built-in search methods are not allowed per AP CS A constraints
for (Room r : this.rooms) {
if (r.getName().equalsIgnoreCase(name)) {
return r;
}
}
return null;
}

/*
* Executes processing sequences of the central interaction loop until terminated.
*/
public void start() {
Scanner scanner = new Scanner(System.in);
boolean roomChanged = true;


System.out.println("--- Welcome to Papa John's Haunted House ---");

while (this.isRunning && !this.isGameOver) {
  if (roomChanged) {
    System.out.println("\n------------------------------------------------");
    System.out.println(this.player.getCurrentRoom().getDescription(this, this.previousRoomName));
    
    // Immediate validation screening for specific room trap contexts
    if (this.player.getCurrentRoom().getName().equalsIgnoreCase("Living Room") && !this.hasWeapon) {
      this.isGameOver = true;
      break;
    }
    if (this.player.getCurrentRoom().getName().equalsIgnoreCase("Storage") && this.previousRoomName != null && this.previousRoomName.equalsIgnoreCase("Garage")) {
      this.isGameOver = true;
      break;
    }
    if (this.player.getCurrentRoom().getName().equalsIgnoreCase("Garage") && this.previousRoomName != null && this.previousRoomName.equalsIgnoreCase("Storage")) {
      this.isGameOver = true;
      break;
    }
    if (this.player.getCurrentRoom().getName().equalsIgnoreCase("Basement")) {
      if (this.breakLock) {
        this.isGameOver = true;
        break;
      } else if (this.hasBasementKey) {
        System.out.println("\nYou have successfully survived Papa John's Haunted House! You win!");
        this.isGameOver = true;
        break;
      }
    }
    roomChanged = false;
  }

  System.out.print("\n> ");
  String command = scanner.nextLine().trim();
  if (command.isEmpty()) {
    continue;
  }

  int previousTurnCount = this.turnCount;
  this.processCommand(command, scanner);

  if (this.turnCount > previousTurnCount) {
    roomChanged = true;
  }
}
scanner.close();
System.out.println("\nThank you for playing!");



}

/*
* Evaluates input vectors and dispatches subroutines.
* @param command the raw alphanumeric instruction string written by the user
* @param scanner the shared system input engine passed down to subroutines
*/
private void processCommand(String command, Scanner scanner) {
String lowerInput = command.toLowerCase();


if (lowerInput.equalsIgnoreCase("quit")) {
  this.isRunning = false;
} else if (lowerInput.equalsIgnoreCase("look")) {
  System.out.println(this.player.getCurrentRoom().getDescription(this, this.previousRoomName));
  System.out.print("Items here: ");
  if (this.player.getCurrentRoom().getItems().isEmpty()) {
    System.out.println("None");
  } else {
    // Manual iteration loop tracking layout rules
    for (int i = 0; i < this.player.getCurrentRoom().getItems().size(); i++) {
      System.out.print(this.player.getCurrentRoom().getItems().get(i));
      if (i < this.player.getCurrentRoom().getItems().size() - 1) {
        System.out.print(", ");
      }
    }
    System.out.println();
  }
} else if (lowerInput.equalsIgnoreCase("inventory")) {
  System.out.print("Your inventory: ");
  if (this.player.getInventory().isEmpty()) {
    System.out.println("Empty");
  } else {
    // Manual structural verification tracking rules
    for (int i = 0; i < this.player.getInventory().size(); i++) {
      System.out.print(this.player.getInventory().get(i));
      if (i < this.player.getInventory().size() - 1) {
        System.out.print(", ");
      }
    }
    System.out.println();
  }
} else if (lowerInput.startsWith("go ")) {
  String direction = command.substring(3).trim();
  this.handleMovement(direction);
} else if (lowerInput.startsWith("take ")) {
  String itemName = command.substring(5).trim();
  this.handleTake(itemName);
} else if (lowerInput.equalsIgnoreCase("eat food")) {
  this.handleEatFood();
} else if (lowerInput.equalsIgnoreCase("break lock")) {
  this.handleBreakLock();
} else {
  System.out.println("Unknown command code or phrase.");
}



}

/*
* Validates grid structural connections to update position frames.
* @param direction the chosen direction vector string
*/
private void handleMovement(String direction) {
String destinationName = null;


// Iterates the current Room's exits, splitting each string to evaluate matches
for (String exit : this.player.getCurrentRoom().getExits()) {
  String[] parts = exit.split(":");
  if (parts[0].equalsIgnoreCase(direction)) {
    destinationName = parts[1];
    break;
  }
}

if (destinationName == null) {
  System.out.println("You cannot go that way.");
  return;
}

// Handles locking barriers blocking the critical path to the cellar
if (destinationName.equalsIgnoreCase("Basement")) {
  if (!this.hasBasementKey && !this.breakLock) {
    System.out.println("Upon reaching the basement door. You are unable to open the door. You notice some sort of lock attached to the door preventing you from entering.");
    return;
  }
}

Room nextRoom = this.findRoom(destinationName);
if (nextRoom != null) {
  this.previousRoomName = this.player.getCurrentRoom().getName();
  this.player.setCurrentRoom(nextRoom);
  this.turnCount++;
  this.processTurn();
}



}

/*
* Transfers context assets from the current ground array to player inventory.
* @param itemName the parsed identification name of the item
*/
private void handleTake(String itemName) {
Item targetItem = null;


for (Item item : this.player.getCurrentRoom().getItems()) {
  if (item.getName().equalsIgnoreCase(itemName)) {
    targetItem = item;
    break;
  }
}

if (targetItem == null) {
  System.out.println("That item is not here.");
  return;
}

// Prevents key extraction when darkness constraints are unresolved
if (targetItem.getName().equalsIgnoreCase("Basement Key") && !this.hasFlashlight) {
  System.out.println("It is too dark to find anything!");
  return;
}

this.player.addItem(targetItem);
this.player.getCurrentRoom().removeItem(itemName);
System.out.println("You picked up the " + targetItem.getName() + ".");

// Evaluate flag assignment updates derived from item possession
if (targetItem.getName().equalsIgnoreCase("Flashlight")) {
  this.hasFlashlight = true;
} else if (targetItem.getName().equalsIgnoreCase("Baseball bat")) {
  this.hasWeapon = true;
} else if (targetItem.getName().equalsIgnoreCase("Basement Key")) {
  this.hasBasementKey = true;
}

this.turnCount++;
this.processTurn();



}

/*
* Processes custom consumable interaction events inside the dining room.
*/
private void handleEatFood() {
if (!this.player.getCurrentRoom().getName().equalsIgnoreCase("Dining Room")) {
System.out.println("There is nothing here to eat.");
return;
}


if (this.hasEatenFood) {
  System.out.println("The silver platter that once held the steaming feast now sits empty, stained with grease and dark crumbs.");
  return;
}

this.hasEatenFood = true;
this.turnCount++;

// 50/50 randomized execution path matching specification criteria
if (Math.random() < 0.5) {
  System.out.println("You eat the food. It looks and smells delicious, giving you the strength to continue!");
  this.processTurn();
} else {
  System.out.println("The food was poisoned! You collapse to the floor. Game Over.");
  this.isGameOver = true;
}



}

/*
* Handles lock breach interactions while outside the basement door.
*/
private void handleBreakLock() {
if (!this.player.getCurrentRoom().getName().equalsIgnoreCase("Outside")) {
System.out.println("There is no lock here to break.");
return;
}


this.breakLock = true;
this.turnCount++;

// Instantly routes player location to trigger the basement game over description
this.previousRoomName = "Outside";
this.player.setCurrentRoom(this.findRoom("Basement"));
this.processTurn();



}

/*
* Hook for execution of scheduled background mechanics at the close of every valid turn cycle.
*/
private void processTurn() {
// Left empty intentionally per specifications to preserve structural hooks for turn mechanics
}
}
