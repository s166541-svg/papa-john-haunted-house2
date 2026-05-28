\# \[Papa John's Haunted House\]  
\*\*Group Members:\*\*  
Andrew Ngo,  
Jonathan Cabrera,  
Ethan Yen  
\---  
\#\# Specification:

- Do NOT use raw type initialization because the java compiler assumes it’s a generic object which causes errors

\#\# I. Global Game State

\- Inventory system (ex. hasBasementKey)  
\- Locked rooms (ex. basementLocked)

\#\# II. Class Hierarchy

\- Main game class (stores inventory)  
\- Room class (stores item in room, if room is locked)

\#\# III. Room Definitions

\#\#\# \[Outside\]  
\- \*\*Description:\*\* (isStartofGame \= True) You are standing in a dark forest filled with giant redwood trees. The air is chilly, and the cabin North of you looks very old and shaky. The porch is broken, and some of the windows have cracks in them. To your West, there is a heavy cellar door with a lock. To your East, a small path leads around to the back of the house. 

\- \*\*Description:\*\* (hasBasementKey \= True) You take a deep breath, exhilarated from the horrors you experienced in the haunted house. As you close the door behind you, the only option is to head to the basement and find out what’s inside. To your West, there is the coveted basement you finally have the key for. To your East, there is the same path you walked down so long ago.  
\- \*\*Items:\*\* None  
\- \*\*Exits:\*\* Basement \[West\] (locked without basementKey), Main Entrance \[North\], Back Entrance \[East\]

\#\#\# \[Main Entrance\]  
\- \*\*Description:\*\* (while move counter is \= 0)The moment you step inside, the door thuds shut, locking out the wind. The air here is stale and smells like old paper. It is very dim, and every step you take makes the floorboards groan like they are complaining. To the West, you hear a slow, heavy snoring sound—*huff... wheeze...*—as if something very big is dreaming nearby. To the East of you seems to be a kitchen. To the North of you is a bathroom.

\- \*\*Description:\*\* (while the move counter is \> 0\) The air here is stale and smells like old paper. It is very dim, and every step you take makes the floorboards groan like they are complaining. To the West, the Guest Room. To the East of you seems to be a kitchen. To the North of you is a bathroom.

\- \*\*Items:\*\* None  
\- \*\*Exits:\*\* Guest Room \[West\], Kitchen \[East\], Bathroom \[North\], Outside \[South\] (only if user has basementKey)

\#\#\# \[Bathroom\]  
\- \*\*Description:\*\* (hasFlashlight \= False) This room is a cold, dark square that smells like wet pennies and soap. The floor is slick and chilly under your feet. Without a light, the darkness here is physical, pressing against your skin like a wet cloth. You grope along the walls, but the shadows are too deep to see anything inside the medicine cabinet or on the floor. To the South of you is the Main Entrance.

\- \*\*Description:\*\* (hasFlashlight \= True && hasBasementKey \= False) As the sharp beam of your Flashlight cuts through the thick, heavy darkness, the room suddenly shudders into view. The light reflects harshly off the yellowed wall tiles, which are slick with a glistening moisture that never seems to dry. The sink is stained with deep, rust-colored streaks that gather around the drain, and the air carries a sharp, stinging scent of bleach and copper. 

Your light eventually centers on the medicine cabinet above the sink. The mirror is so layered with grime and grey dust that your reflection looks like a ghost standing in a thick fog. When you pull the small metal handle, the hinges give a piercing, high-pitched squeal that echoes far too loudly in the tiny space. Resting on the top shelf, looking out of place against the empty, glass medicine bottles, is a heavy iron key that glints under your light. To the South of you is the Main Entrance. Take the Basement Key? “*Take Basement Key”*

\- \*\*Description:\*\* (hasFlashlight \= True && hasBasementKey \= True) The light reflects harshly off the yellowed wall tiles, which are slick with a glistening moisture that never seems to dry. The sink is stained with deep, rust-colored streaks that gather around the drain, and the air carries a sharp, stinging scent of bleach and copper. To the South of you is the Main Entrance.

\- \*\*Items:\*\* basementKey (requires flashlight)  
\- \*\*Exits:\*\* Main Entrance \[South\]

\#\#\# \[Backyard\]  
\- \*\*Description:\*\* (hasBasementKey \= False) Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter.   
\- \*\*Description:\*\* (hasBasementKey \= True) Outside, the wind whistles through the giant trees, making the branches rub together with a scratching sound. The back of the cabin looks tall and spooky against the night sky. The grass is long and tangled, and a small, sturdy wooden door stands at the back of the house (South), waiting for you to enter. Going West, you can finally leave the house.

\- \*\*Items:\*\* None  
\- \*\*Exits:\*\* Storage \[South\], Outside \[West\]

\#\#\# \[Storage\]  
\- \*\*Description:\*\* (hasBasementKey \= False && hasFlashlight \= False) This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. And find a box with flashlights. Take the flashlight? “Take Flashlight”

\- \*\*Description:\*\* (hasBasementKey \= False && hasFlashlight \= True) This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. 

\- \*\*Description:\*\* (hasBasementKey \= True) This room is crowded with dozens of hand-carved statues. There are carvings of birds, bears, and crying people. Their shiny glass eyes seem to catch the light and follow you as you walk. Every time you turn your back, you feel like the statues are quietly scooting a little bit closer to you. You notice a door South of you on the opposite end of the room, and a door leading to the West of you. Going North, you can finally exit the house.

\- \*\* Description:\*\* (Entering from Garage) **DEATH TRAP.** As you push open the black metal door from the Garage side, you trigger a hidden tripwire. Before you can even process the sight of the statues, a massive shelf loaded with rusted engine parts collapses from above, pinning you instantly. **Game Over.**   
\- \*\*Items:\*\* Flashlight  
\- \*\*Exits:\*\* Garage \[South\], Dining room \[West\], Backyard \[North\] (after grabbing the basement key)

\#\#\# \[Garage\]  
\- \*\*Description:\*\* (hasBaseballBat \= False)The dead silence in the garage creates a sense of fear and the cold air pierces your skin. In front of you there is a broken down school bus and a Papa John’s delivery car. Upon exploring, you find a baseball bat. There is a door leading to the Kitchen to the West of you and another leading to a Storage room North of you. Take the baseball bat? *“Take Baseball Bat”*

\- \*\*Description:\*\* (hasBaseballBat \= True)The dead silence in the garage creates a sense of fear and the cold air pierces your skin. In front of you there is a broken down school bus and a Papa John’s delivery car. There is a door leading to the Kitchen to the West of you and another leading to a Storage room North of you. 

\- \*\*Description:\*\* (Entering from Storage Room) **DEATH TRAP**. You step through the metal door into the garage, but the door slams shut behind you with a mechanical bang. The statues from the previous room begin to push against the wood, trapping you in the freezing dark as the locks hiss shut, leaving you with no way out. **Game Over.**   
\- \*\*Items:\*\* Baseball bat  
\- \*\*Exits:\*\* Dining Room \[West\], Storage \[North\]

\#\#\# \[Dining Room\]  
\- \*\*Description:\*\* (hasEatenFood \= False) A long, grand table made of dark wood sits under a layer of grey dust. In the very center, there is a single plate of steaming hot food that looks and smells delicious. It’s very strange because there is nobody around to cook it, and the steam rises into the air in curly, ghostly shapes. To the South is the Kitchen, to the West is the Living Room, and to the East is the Storage room.

Eat the food? *“Eat Food”*  

\- \*\*Description:\*\* (hasEatenFood \= True) A long, grand table made of dark wood sits under a layer of grey dust. The silver platter that once held the steaming feast now sits empty, stained with grease and dark crumbs. To the South is the Kitchen, to the West is the Living Room, and to the East is the Storage room.

\- \*\*Items:\*\* N/A  
\- \*\*Exits:\*\* Living Room \[West\], Kitchen \[South\], Storage \[East\],   
\#\#\# \[Kitchen\]  
\- \*\*Description:\*\* The kitchen is the coldest part of the house. An old, white refrigerator shudders and shakes, making a low humming noise that never stops. Rusted cabinets hang open, showing rows of dusty jars filled with strange, murky liquids. A single faucet drips into the sink—drop... drop... drop—sounding like a ticking clock. To the North is the Dining Room, to the West is the Main Entrance, and to the East is the Garage.  
\- \*\*Items:\*\* N/A  
\- \*\*Exits:\*\* Dining Room \[North\], Main Entrance \[West\], Garage \[East\]

\#\#\# \[Living Room\]  
\- \*\*Description:\*\* (hasWeapon \= False) Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. As you step into the center of the room, a floorboard snaps loudly. From the shadows of the corner, a tall, dark figure lunges at you with a jagged blade. Without anything to defend yourself, the cold steel is the last thing you feel. **Game Over.** 

\- \*\*Description:\*\* (hasWeapon \= True) Faded wallpaper peels away in long, skin-like strips, and a lone rocking chair creaks rhythmically by the cold fireplace. Suddenly, a dark figure emerges from the gloom, raising a weapon to strike. Instinctively, you swing the baseball bat with all your might. The wood connects with a dull thud, dazing the attacker just long enough for you to scramble backward and slip away into the next room (South) or to the previous room (East). You are safe, for now. 

\- \*\*Items:\*\* N/A  
\- \*\*Exits:\*\* Guest Room \[South\], Dining room \[East\]

\#\#\# \[Guest Room\]  
\- \*\*Description:\*\* (hasFlashlight \= False)The air here is heavy and still, filled with tiny bits of dust dancing in the dark. Long, grey cobwebs hang from the ceiling like messy decorations. A giant bed with a thick, tattered blanket sits in the center. It looks like someone just got up from a nap, leaving a deep dent in the middle of the mattress. To the North is the living room and to the East is the Main Entrance. Take the Flashlight? *“Take Flashlight”*

\- \*\*Description:\*\* (hasFlashlight \= True)The air here is heavy and still, filled with tiny bits of dust dancing in the dark. Long, grey cobwebs hang from the ceiling like messy decorations. A giant bed with a thick, tattered blanket sits in the center. It looks like someone just got up from a nap, leaving a deep dent in the middle of the mattress. To the North is the living room and to the East is the Main Entrance. 

\- \*\*Items:\*\* Flashlight  
\- \*\*Exits:\*\* Living Room \[North\], Main Entrance \[East\]

\#\#\# \[Basement\]  
\- \*\*Description:\*\* (hasBasementKey \= True) You walk down a set of narrow, shaky stairs that moan under your weight. The walls are damp and feel fuzzy to the touch. A long, dark hallway leads to a room where an old TV sits on the floor, flickering with "snowy" white static that lights up the room in quick flashes. On the floor, you notice a mountain of valuable loot. 

You WIN\!  

\- \*\*Description:\*\* (hasBasementKey \= False) Upon reaching the basement door. You are unable to open the door. You notice some sort of lock attached to the door preventing you from entering. You have the option to break the lock or to go back (East) to Outside.

Break the Lock? *“Break Lock”*

\- \*\*Description:\*\* (hasBasementKey \= False && breakLock \= True) The loud thuds seem to have woken something up. Examining your surroundings you find nothing unusual. Then, from behind a dark figure lunges at you with a jagged blade. The cold steel is the last thing you feel. **Gameover**.   
\- \*\*Items:\*\* N/A  
\- \*\*Exits:\*\* Outside \[East\]

\#\# IV. Interaction Logic

\#\#\# \[Receiving Basement key\]  
\- \*\*Action:\*\* Picking up basement key  
\- \*\*Location:\*\* Bathroom  
\- \*\*Prerequisite:\*\* Flashlight  
\- \*\*Effect:\*\* Can now open basement door

\#\#\# \[Defense\]  
\- \*\*Action:\*\* Prevent Papa John from killing you  
\- \*\*Location:\*\* Basement, Living Room  
\- \*\*Prerequisite:\*\* Baseball Bat  
\- \*\*Effect:\*\* Safely evade Papa John

\#\#\# \[Eating\]  
\- \*\*Action:\*\* Eat the food   
\- \*\*Location:\*\* Dining Room  
\- \*\*Prerequisite:\*\* N/A  
\- \*\*Effect:\*\* 50/50 chance you live and continue or die and lose. 

\#\#\# \[Break Lock\]  
\- \*\*Action:\*\* Break the lock  
\- \*\*Location:\*\* Basement  
\- \*\*Prerequisite:\*\* N/A  
\- \*\*Effect:\*\* Alert Papa John **Gameover**

\#\#\# \[Reset Game\]  
\- \*\*Action:\*\* Restart the game  
\- \*\*Command:\*\* restart  
\- \*\*Location:\*\* Any room  
\- \*\*Prerequisite:\*\* N/A  
\- \*\*Effect:\*\* Resets the player’s inventory, room position, turn counter, and all game state flags. The player returns to the Outside room and can start over without closing the program. 

- The game should include a restart option so the player is not permanently stuck. At any point, the player can type “restart” to reset the game back to the starting room. This clears the player’s inventory, resets all game flags, resets the turn counter, and places the player back outside the house.

- This improves the game because there are several ways to lose or get trapped. Instead of needing to close and rerun the program, the player can quickly restart and try a different path.

\#\# V. Critical Path

1. Main Entrance \- Kitchen \- Dining Room \- Storage \- Dining Room \- Garage \- Dining Room \- Kitchen \- Main Entrance \- Bathroom (grab basementKey) \- Main Entrance \- Outside \- Basement  
2. BackYard \- Storage \- Dining Room \- Garage \- Dining Room \- Kitchen \- Main Entrance \- Bathroom (grab basementKey) \- Main Entrance \- Outside \- Basement  
3. BackYard \- Storage \- Dining Room \- Kitchen \- Main Entrance \- Bathroom (grab basementKey) \- Main Entrance \- Outside \- Basement

\#\# VI. Item Placement

| Item | Type | Room | Used On |  
|------|------|------|---------|  
|   Flashlight  |  Utility   |   Guest Room & Storage   |  Illuminating the bathroom for the key.     |  
| Basement Key | Utility | Bathroom | Unlocking Basement |  
| Baseball Bat | Weapon | Garage | Evade Papa John |

\#\# VII. Turn Mechanics (Optional)

\#\#\# NPCs \- Papa John

- Papa John acts as the main danger inside the haunted house. He does not move like a normal player, but his presence controls the fear and pressure of the game. The player hears hints of him through sounds like heavy breathing, snoring, footsteps, and sudden attacks. This makes the house feel alive instead of just being a set of rooms.  
- Papa John appears as a threat in specific dangerous rooms, especially the Living Room, Garage, Storage, and Basement. If the player enters the Living Room without the baseball bat, Papa John attacks and the game ends. If the player enters dangerous trap paths, such as going from the Storage Room to the Garage or from the Garage to the Storage Room, the player is also trapped and loses. This makes movement choices more important because not every exit is safe.  
- The baseball bat works as the player’s main protection against Papa John. If the player finds and takes the bat before entering the Living Room, they can survive the attack and keep moving. This gives the player a reason to explore the house carefully instead of rushing straight to the basement.

\#\#\# Turn Counter

- The game can use a turn counter to track how many meaningful actions the player takes. A turn increases whenever the player moves to another room, picks up an item, eats the food, or breaks the lock. Simple commands like “look,” “inventory,” or “restart” do not need to count as turns because they do not move the story forward.  
- The turn counter makes the game feel more tense because every action matters. After a certain number of turns, the game can display warning messages to show that Papa John is getting closer. For example, after several turns, the game could say, “You hear heavy footsteps somewhere deeper in the house.” Later, it could say, “Papa John’s breathing sounds much closer now.” These warnings create suspense without instantly ending the game.


\#\#\# Possible Turn-Based Events

- After 5 turns, the player hears strange noises in the house. This warns them that Papa John is active.  
- After 10 turns, the player hears Papa John moving closer. This tells the player they should stop wasting time and focus on finding the basement key.  
- After 15 turns, Papa John can attack if the player has not found a weapon. If the player has the baseball bat, they can defend themselves and continue. If they do not have the bat, the game ends.  
- This turn system adds pressure, but it should not make the game unfair. The player still has enough time to explore, find the flashlight, get the basement key, and reach the basement.

\#\#\# Inventory Limit N/A

- There is no inventory limit. The player can carry all important items needed to finish the game, including the flashlight, basement key, and baseball bat. This keeps the game simple and makes it easier for the player to focus on solving the haunted house path instead of managing item space.

\#\#\# Resources: N/A

- There are no limited resources such as health, stamina, food, batteries, or money. The main things the player manages are inventory items and safe movement choices.

