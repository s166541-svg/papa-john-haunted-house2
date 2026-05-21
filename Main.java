// --- FILE: Main.java ---
/*
* Serves as the primary entry point framework execution driver for the application.
* Boots up resources by initializing a Game architecture object instance, then delegates
* tracking to the active core loops.
*/
public class Main {
/**
* Main runtime program execution sequence.
* @param args command-line structural arrays passed into program execution
*/
public static void main(String[] args) {
Game game = new Game();
game.start();
}
}
