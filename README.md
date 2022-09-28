# Plants VS Zombies


I made this project just for fun, it is a simple Plants VS Zombies game developed with the libGDX library in Java.

### Gameplay and Features

https://user-images.githubusercontent.com/112988540/192851992-0380e107-6b7f-490a-a0d8-cf54011cd817.mp4

Description: This 2d plants vs zombies game consists of a small garden tile map along with 6 different plants that can be purchased and put on the garden
to fight against the oncoming zombies. Both peashooters will help fight from long range with pea projectiles, while the cherry and mineplant will fight 
melee style. Use the sunflower and walnut to stop oncoming enemies and increase your sun count. The 4 types of zombies come at you in waves for each level
(8 levels). The buckethead and pylonhead zombies are more rare, but much harder to eliminate. Between each level the garden of plants, the health of the house
and the time needed to buy on the menu will reset. However, you will be able to keep your sun count (currency) throughout the whole game. Take down all of the
zombies to win the game. If three zombies slip past you in one round, your house will break down and you will lose the game.

Instructions:
- Purchase and place plants in the garden strategically using suns (currency).
- Keep an eye out for what zombies are coming at you, as they have different amounts of health.
- Eliminate all zombies in that wave (shows counter in corner), to move onto the next level.
- Get through 8 levels and you win the game.
- If three zombies get past you in one wave, you lose the game.

Features:
- Plants:
- Snapping plants to the grid.
- 6 Plants: Peashooter, IcePeashooter, Mineplant, Cherry, Sunflower and Walnut.
- Peashooters shoot projectiles that deal damage to zombies.
- Icepeashooter freezes enemy with bullets (turns him blue and slows him down).
- Mineplant will eliminate any zombie it collides with, as long as the mineplant has grown out of the ground.
- Cherry will explode a 3x3 grid of zombies near it, 3 seconds after being placed in the garden.
- Walnut has increased health to act as a barrier.
- Sunflower drops suns on a timer.

- Menu:
- Pull plants from menu to snap into a grid position if affordable to the player.
- If not placed on the grid, plant will snap back to menu.
- Once a plant is bought, a timer appears which indicates it cannot be bought until the timer is up. (Varies for each plant)
- Resets every round.
- Shovel allows you to dig out a plant of choice to remove them from the garden.

- Zombies:
- 4 Zombies: Standard, PylonHead, BucketHead, FlagZombie.
- All zombies stop at plants and destroy them before continuing on.
- If zombie reaches the house, they will go inside (dissapear from screen) and break the house (1/3 house lives).
- Standard zombie is most common zombie and is the only one to appear in the early stages of the round (other than flagzombie).
- FlagZombie is the same as the standard zombie except is always the first zombie in each wave (carries flag).
- PylonHead and BucketHead have increased health and only appear in the late stages of each wave.

- Widgets:
- House health top left corner shows the health of the house in that round.
- Level counter shows what level you are on.
- Zombie counter shows how many zombies are left in that level.
- Sun counter shows the amount of suns you have.

- Sounds:
- Sounds added for the following:
- Planting in garden
- Digging out with shovel
- Game Music
- Peashooters shooting
- Mineplant and Cherry exploding
- Zombies breaking house
- Zombies attacking plants
- Zombies groaning

- Added features:
- Peashooter only shoots if zombie is on-screen.
- Mineplant starts in the ground and grows after 14 seconds.
- Cherry explodes 3 seconds after placement.
- Peashooter slows down zombies and turns them blue.
- Suns fall from sky.
- Two end screen pictures depending on if you win or lose.


### Playing The Game
- Import the game files to your IntelliJ IDE
- Navigate through Desktop -> src -> com -> omstead -> gamebasics -> desktop and run 'DesktopLauncher.java'
- Let game files configure and enjoy this unique version of PvZ
