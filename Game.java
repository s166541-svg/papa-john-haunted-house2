import java.util.ArrayList;
import java.util.Scanner;
// --- FILE: Game.java ---

/*

Manages the central game logic, state, and main loop.

This class coordinates interactions between Player, Room, and Items. It

handles command parsing, state flags, and turn mechanics.
*/
public class Game {
private Player player;
private ArrayList rooms;
private boolean isRunning;
private Scanner scanner;
private int turnCount;

// Global State Flags
private boolean hasBasementKey = false;
private boolean hasFlashlight = false;
private boolean hasWeapon = false;
private boolean hasEatenFood = false;
private boolean breakLock = false;

/*

Initializes the game world and sets up the player.
*/
public Game() {
this.player = new Player();
this.rooms = new ArrayList();
this.scanner = new Scanner(System.in);
this.isRunning = true;
this.turnCount = 0;
initializeWorld();
}

/*

Creates all rooms, items, and connections per the game specification.
*/
private void initializeWorld() {
// Create Rooms
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

// Add Exits
outside.addExit("North", "Main Entrance");
outside.addExit("West", "Backyard");
outside.addExit("East", "Basement");

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

// Add Items
guestRoom.addItem(new Utility("Flashlight", "A portable light source."));
storage.addItem(new Utility("Flashlight", "A portable light source."));
bathroom.addItem(new Utility("Basement Key", "A heavy iron key."));
garage.addItem(new Weapon("Baseball Bat", "A sturdy wooden bat."));

// Add rooms to global list
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
}

/*

Starts the main game loop.
*/
public void start() {
System.out.println("Welcome to Papa John's Haunted House.");
while (isRunning) {
Room current = player.getCurrentRoom();
System.out.println("\n--- " + current.getName() + " ---");
System.out.println(current.getDescription(this));

// Check Win/Loss conditions
if (current.getName().equalsIgnoreCase("Basement") && hasBasementKey) {
isRunning = false;
break;
}
if (current.getName().equalsIgnoreCase("Living Room") && !hasWeapon) {
isRunning = false;
break;
}

System.out.print("\n> ");
String input = scanner.nextLine().toLowerCase();
processCommand(input);
}
}

/*

Parses and executes player commands.

@param input the raw string input from the user
*/
public void processCommand(String input) {
if (input.equals("quit")) {
isRunning = false;
return;
}

if (input.equals("restart")) {
  resetGame();
  System.out.println("Game restarted.");
  return;
}

if (input.equals("look")) {
  System.out.println("Items in the room:");
  ArrayList<Item> items = player.getCurrentRoom().getItems();
  for (Item item : items) {
    System.out.println("- " + item.getName());
  }
  return;
}

if (input.equals("inventory")) {
  System.out.println("Your inventory:");
  for (Item item : player.getInventory()) {
    System.out.println("- " + item.getName());
  }
  return;
}

if (input.startsWith("go ")) {
  String direction = input.substring(3).trim();
  movePlayer(direction);
  return;
}

if (input.startsWith("take ")) {
  String itemName = input.substring(5).trim();
  handleTake(itemName);
  return;
}

if (input.equals("eat food")) {
  handleEatFood();
  return;
}

if (input.equals("break lock")) {
  handleBreakLock();
  return;
}

System.out.println("I don't understand that command.");
}

/*

Logic for moving the player between rooms.

@param direction the direction string to match
*/
private void movePlayer(String direction) {
Room current = player.getCurrentRoom();
String destinationName = null;

for (String exit : current.getExits()) {
  String[] parts = exit.split(":");
  if (parts[0].equalsIgnoreCase(direction)) {
    destinationName = parts[1];
    break;
  }
}

if (destinationName != null) {
  // Check specific locked path logic
  if (current.getName().equalsIgnoreCase("Outside") && direction.equalsIgnoreCase("East") && !hasBasementKey) {
    System.out.println("Upon reaching the basement door. You are unable to open the door.");
    player.setCurrentRoom(findRoom("Basement"));
    incrementTurn();
    return;
  }

  // Check Death Traps for Garage/Storage
  if (current.getName().equalsIgnoreCase("Garage") && destinationName.equalsIgnoreCase("Storage")) {
    System.out.println("DEATH TRAP. As you push open the black metal door from the Garage side, you trigger a hidden tripwire. Before you can even process the sight of the statues, a massive shelf loaded with rusted engine parts collapses from above, pinning you instantly. Game Over.");
    isRunning = false;
    return;
  }
  if (current.getName().equalsIgnoreCase("Storage") && destinationName.equalsIgnoreCase("Garage")) {
    System.out.println("DEATH TRAP. You step through the metal door into the garage, but the door slams shut behind you with a mechanical bang. The statues from the previous room begin to push against the wood, trapping you in the freezing dark as the locks hiss shut, leaving you with no way out. Game Over.");
    isRunning = false;
    return;
  }

  Room nextRoom = findRoom(destinationName);
  if (nextRoom != null) {
    player.setCurrentRoom(nextRoom);
    incrementTurn();
  }
} else {
  System.out.println("You can't go that way.");
}
}

/*

Logic for picking up items based on prerequisites.

@param itemName the name of the item to take
*/
private void handleTake(String itemName) {
Room current = player.getCurrentRoom();
Item found = null;

// Manual search in room
for (Item item : current.getItems()) {
  if (item.getName().equalsIgnoreCase(itemName)) {
    found = item;
    break;
  }
}

if (found != null) {
  // Check prerequisites
  if (itemName.equalsIgnoreCase("Basement Key") && !hasFlashlight) {
    System.out.println("The shadows are too deep to see anything inside the medicine cabinet.");
    return;
  }

  player.addItem(found);
  current.removeItem(itemName);
  System.out.println("You took the " + itemName + ".");

  // Set state flags
  if (itemName.equalsIgnoreCase("Flashlight")) hasFlashlight = true;
  if (itemName.equalsIgnoreCase("Basement Key")) hasBasementKey = true;
  if (itemName.equalsIgnoreCase("Baseball Bat")) hasWeapon = true;

  incrementTurn();
} else {
  System.out.println("There is no " + itemName + " here.");
}
}

/*

Handles the custom interaction for eating food.
*/
private void handleEatFood() {
if (player.getCurrentRoom().getName().equalsIgnoreCase("Dining Room") && !hasEatenFood) {
hasEatenFood = true;
incrementTurn();
// 50/50 chance
if (Math.random() < 0.5) {
System.out.println("The food was poisoned! You collapse to the floor. Game Over.");
isRunning = false;
} else {
System.out.println("The food was delicious and you feel energized.");
}
} else {
System.out.println("There is nothing to eat here.");
}
}

/*

Handles the custom interaction for breaking the basement lock.
*/
private void handleBreakLock() {
if (player.getCurrentRoom().getName().equalsIgnoreCase("Basement") && !hasBasementKey) {
breakLock = true;
System.out.println("The loud thuds seem to have woken something up. Examining your surroundings you find nothing unusual. Then, from behind a dark figure lunges at you with a jagged blade. The cold steel is the last thing you feel. Gameover.");
isRunning = false;
} else {
System.out.println("There is no lock to break.");
}
}

/*

Increments the turn count and triggers turn-based logic.
*/
private void incrementTurn() {
turnCount++;
processTurn();
}

/*

Runs logic related to turn progression, such as NPC warnings.
*/
public void processTurn() {
if (turnCount == 5) {
System.out.println("\n[WARNING] You hear strange noises in the house.");
} else if (turnCount == 10) {
System.out.println("\n[WARNING] You hear Papa John moving closer.");
} else if (turnCount == 15) {
if (!hasWeapon) {
System.out.println("\nPapa John has found you! Without a weapon, you cannot defend yourself. Game Over.");
isRunning = false;
} else {
System.out.println("\nPapa John attacks! You use the Baseball Bat to defend yourself and survive.");
}
}
}

/*

Resets all game state variables and the player's status.
*/
private void resetGame() {
player = new Player();
rooms = new ArrayList();
hasBasementKey = false;
hasFlashlight = false;
hasWeapon = false;
hasEatenFood = false;
breakLock = false;
turnCount = 0;
initializeWorld();
}

/*

Finds a Room object in the global list by its name.

@param name the name of the room to search for

@return the Room object if found, otherwise null
*/
public Room findRoom(String name) {
for (Room r : rooms) {
if (r.getName().equalsIgnoreCase(name)) {
return r;
}
}
return null;
}

public boolean isHasBasementKey() { return hasBasementKey; }
public boolean isHasFlashlight() { return hasFlashlight; }
public boolean isHasWeapon() { return hasWeapon; }
public boolean isHasEatenFood() { return hasEatenFood; }
}
