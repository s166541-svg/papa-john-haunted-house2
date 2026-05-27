// --- FILE: Game.java ---
import java.util.ArrayList;
import java.util.Scanner;

/*

Manages the game logic, state, and world construction.

This class contains the main game loop, processes user input,

tracks turns, and evaluates win/loss conditions based on student specs.
*/
public class Game {
private ArrayList rooms;
private Player player;
private boolean isRunning;
private Room lastRoom;

// Global state flags
private boolean hasFlashlight = false;
private boolean hasBasementKey = false;
private boolean hasWeapon = false;
private boolean hasEatenFood = false;
private boolean breakLock = false;
private int turnCount = 0;

/*

Initializes the game world and player state.
*/
public Game() {
initializeGame();
}

/*

Sets up all rooms, items, and connections.
*/
private void initializeGame() {
rooms = new ArrayList<>();
player = new Player();
isRunning = true;
turnCount = 0;
hasFlashlight = false;
hasBasementKey = false;
hasWeapon = false;
hasEatenFood = false;
breakLock = false;

// Room creation
Room outside = new Room("Outside", "");
Room mainEntrance = new Room("Main Entrance", "The moment you step inside, the door thuds shut, locking out the wind. The air here is stale and smells like old paper. It is very dim, and every step you take makes the floorboards groan like they are complaining. To the West, you hear a slow, heavy snoring sound—huff... wheeze...—as if something very big is dreaming nearby. To the East of you seems to be a kitchen. To the North of you is a bathroom.");
Room bathroom = new Room("Bathroom", "");
Room backyard = new Room("Backyard", "");
Room storage = new Room("Storage", "");
Room garage = new Room("Garage", "The dead silence in the garage creates a sense of fear and the cold air pierces your skin. In front of you there is a broken down school bus and a Papa John’s delivery car. Upon exploring, you find a baseball bat. A door leading to the dining room to the West of you and another leading to a Storage room North of you. Take the baseball bat? “Take Baseball Bat”");
Room diningRoom = new Room("Dining Room", "");
Room kitchen = new Room("Kitchen", "The kitchen is the coldest part of the house. An old, white refrigerator shudders and shakes, making a low humming noise that never stops. Rusted cabinets hang open, showing rows of dusty jars filled with strange, murky liquids. A single faucet drips into the sink—drop... drop... drop—sounding like a ticking clock. To the North is the Dining Room and to the West is the Main Entrance.");
Room livingRoom = new Room("Living Room", "");
Room guestRoom = new Room("Guest Room", "The air here is heavy and still, filled with tiny bits of dust dancing in the dark. Long, grey cobwebs hang from the ceiling like messy decorations. A giant bed with a thick, tattered blanket sits in the center. It looks like someone just got up from a nap, leaving a deep dent in the middle of the mattress. To the North is the living room and to the East is the Main Entrance. Take the Flashlight? “Take Flashlight”");
Room basement = new Room("Basement", "");

// Exits
outside.addExit("North", "Main Entrance");
outside.addExit("East", "Backyard");
outside.addExit("West", "Basement");

mainEntrance.addExit("West", "Guest Room");
mainEntrance.addExit("East", "Kitchen");
mainEntrance.addExit("North", "Bathroom");
mainEntrance.addExit("South", "Outside");

bathroom.addExit("South", "Main Entrance");

backyard.addExit("South", "Storage");
backyard.addExit("West", "Outside");

storage.addExit("South", "Garage");
storage.addExit("West", "Dining Room");
storage.addExit("North", "Backyard");

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

// Items
storage.addItem(new UtilityItem("Flashlight", "A sturdy flashlight with full batteries."));
guestRoom.addItem(new UtilityItem("Flashlight", "A sturdy flashlight found on the guest bed."));
garage.addItem(new WeaponItem("Baseball Bat", "A heavy wooden bat, good for defense."));
bathroom.addItem(new UtilityItem("Basement Key", "A heavy iron key found in the medicine cabinet."));

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

player.setCurrentRoom(outside);
lastRoom = null;
}

/*

Starts the game loop.
*/
public void start() {
Scanner sc = new Scanner(System.in);
System.out.println("Welcome to Papa John's Haunted House!");

while (isRunning) {
  System.out.println("---");
  System.out.println(player.getCurrentRoom().getName());
  System.out.println(player.getCurrentRoom().getDescription(this));
  
  // Check for passive Room deaths/wins immediately after description
  if (checkConditions()) break;

  System.out.print("> ");
  String input = sc.nextLine().toLowerCase();
  processCommand(input);
}
sc.close();
}

/*

Parses and executes user commands.

@param input The raw string input from the user.
*/
public void processCommand(String input) {
if (input.equals("quit")) {
isRunning = false;
return;
}

if (input.equals("restart")) {
  initializeGame();
  System.out.println("Game restarted.");
  return;
}

if (input.equals("look")) {
  System.out.println("Items here: " + player.getCurrentRoom().getItems());
  return;
}

if (input.equals("inventory")) {
  System.out.println("You are carrying: " + player.getInventory());
  return;
}

if (input.startsWith("go ")) {
  String dir = input.substring(3).trim();
  movePlayer(dir);
  return;
}

if (input.startsWith("take ")) {
  String itemName = input.substring(5).trim();
  takeItem(itemName);
  return;
}

if (input.equals("eat food")) {
  if (player.getCurrentRoom().getName().equals("Dining Room") && !hasEatenFood) {
    hasEatenFood = true;
    incrementTurn();
    if (Math.random() < 0.5) {
      System.out.println("The food was delicious and revitalizing!");
    } else {
      System.out.println("The food was poisoned! You collapse to the floor. Game Over.");
      isRunning = false;
    }
  } else {
    System.out.println("There is no food here to eat.");
  }
  return;
}

if (input.equals("break lock")) {
  if (player.getCurrentRoom().getName().equals("Basement") && !hasBasementKey) {
    breakLock = true;
    incrementTurn();
  } else {
    System.out.println("There is nothing to break here.");
  }
  return;
}

System.out.println("I don't understand that command.");
}

/*

Handles player movement logic.

@param direction The direction string provided by the user.
*/
private void movePlayer(String direction) {
Room current = player.getCurrentRoom();

// Check for locked Basement
if (current.getName().equals("Outside") && direction.equalsIgnoreCase("west")) {
  if (!hasBasementKey) {
    System.out.println("The cellar door is locked. You need a key.");
    return;
  }
}

// Logic for finding the exit
for (String exitStr : current.getExits()) {
  String[] parts = exitStr.split(":");
  if (parts[0].equalsIgnoreCase(direction)) {
    Room nextRoom = findRoom(parts[1]);
    if (nextRoom != null) {
      lastRoom = current;
      player.setCurrentRoom(nextRoom);
      incrementTurn();
      return;
    }
  }
}
System.out.println("You can't go that way.");
}

/*

Handles picking up items.

@param name The name of the item to take.
*/
private void takeItem(String name) {
Room room = player.getCurrentRoom();
Item found = null;

// Logic for Bathroom key requirement
if (room.getName().equals("Bathroom") && name.equalsIgnoreCase("basement key")) {
  if (!hasFlashlight) {
    System.out.println("It's too dark to find anything in here!");
    return;
  }
}

for (Item item : room.getItems()) {
  if (item.getName().equalsIgnoreCase(name)) {
    found = item;
    break;
  }
}

if (found != null) {
  player.addItem(found);
  room.removeItem(name);
  
  // Update state flags
  if (name.equalsIgnoreCase("flashlight")) hasFlashlight = true;
  if (name.equalsIgnoreCase("basement key")) hasBasementKey = true;
  if (name.equalsIgnoreCase("baseball bat")) hasWeapon = true;
  
  System.out.println("You took the " + name + ".");
  incrementTurn();
} else {
  System.out.println("That item isn't here.");
}
}

/*

Increases the turn counter and triggers scheduled events.
*/
private void incrementTurn() {
turnCount++;
processTurn();
}

/*

Handles per-turn logic and narrative warnings.
*/
private void processTurn() {
if (turnCount == 5) {
System.out.println("You hear heavy footsteps somewhere deeper in the house.");
} else if (turnCount == 10) {
System.out.println("Papa John’s breathing sounds much closer now.");
} else if (turnCount >= 15 && !hasWeapon) {
System.out.println("Papa John has found you in the halls! Without a weapon, you cannot defend yourself. Game Over.");
isRunning = false;
}
}

/*

Evaluates win, loss, and trap conditions based on current state.

@return true if the game should end, false otherwise.
*/
private boolean checkConditions() {
Room current = player.getCurrentRoom();

// Trap: Storage from Garage
if (current.getName().equals("Storage") && lastRoom != null && lastRoom.getName().equals("Garage")) {
  System.out.println("DEATH TRAP. As you push open the black metal door from the Garage side, you trigger a hidden tripwire. Before you can even process the sight of the statues, a massive shelf loaded with rusted engine parts collapses from above, pinning you instantly. Game Over.");
  isRunning = false;
  return true;
}

// Trap: Garage from Storage
if (current.getName().equals("Garage") && lastRoom != null && lastRoom.getName().equals("Storage")) {
  System.out.println("DEATH TRAP. You step through the metal door into the garage, but the door slams shut behind you with a mechanical bang. The statues from the previous room begin to push against the wood, trapping you in the freezing dark as the locks hiss shut, leaving you with no way out. Game Over.");
  isRunning = false;
  return true;
}

// Living Room Encounter
if (current.getName().equals("Living Room") && !hasWeapon) {
  // Description already printed "Game Over" text via getDescription logic
  isRunning = false;
  return true;
}

// Break Lock Death
if (breakLock) {
  System.out.println("The loud thuds seem to have woken something up. Examining your surroundings you find nothing unusual. Then, from behind a dark figure lunges at you with a jagged blade. The cold steel is the last thing you feel. Gameover.");
  isRunning = false;
  return true;
}

// Win Condition
if (current.getName().equals("Basement") && hasBasementKey) {
  isRunning = false;
  return true;
}

return false;
}

/*

Searches for a room by name using a for-each loop.

@param roomName The name of the room to find.

@return The Room object or null if not found.
*/
public Room findRoom(String roomName) {
// Manual loop required - built-in search methods are not allowed per AP CS A constraints
for (Room r : rooms) {
if (r.getName().equalsIgnoreCase(roomName)) {
return r;
}
}
return null;
}

/*

Getter for hasFlashlight flag.

@return true if player has found a flashlight.
*/
public boolean isHasFlashlight() {
return hasFlashlight;
}

/*

Getter for hasBasementKey flag.

@return true if player has the basement key.
*/
public boolean isHasBasementKey() {
return hasBasementKey;
}

/*

Getter for hasWeapon flag.

@return true if player has the baseball bat.
*/
public boolean isHasWeapon() {
return hasWeapon;
}

/*

Getter for hasEatenFood flag.

@return true if player has eaten the dining room food.
*/
public boolean isHasEatenFood() {
return hasEatenFood;
}
}
