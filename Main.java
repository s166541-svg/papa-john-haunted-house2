// --- FILE: Main.java ---
/*

Main application entry framework required by standard execution guidelines.

Instantiates the main Game manager instance and sets up the live play context.
*/
public class Main {
/*

Default constructor for the entry framework class.
*/
public Main() {
// Standard implicit constructor declaration
}

/*

Invokes the game initialization sequences.

@param args standard console parameter arrays passed down at launch times
*/
public static void main(String[] args) {
Game softwareEngine = new Game();
softwareEngine.start();
}
}
