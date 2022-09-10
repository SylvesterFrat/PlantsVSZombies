package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;

import java.lang.reflect.Array;
import java.util.*;

/*Question
Do I need to make seperate arraylists for each of my IShootables(zombies) because I am not able to access their data (example health or speed),
through polymorphism of an interface like I would an abstract class, or is there a different way to access that?
Finally maybe should i switch it to an Abstract class or make both an interface(IShootable) and an abstract class(BaseZombie) for the zombies?
*/

public class GameScreen implements Screen, InputProcessor {
    private GameClass game;
    private Texture garden;
    private Plant plantGrid[][] = new Plant[5][9];
    private ArrayList<Plant> plants = new ArrayList<Plant>();
    private ArrayList<Sun> suns = new ArrayList<Sun>();
    private float timeSuns;
    private int sunCount;
    private ArrayList<BaseZombie> zombies = new ArrayList<BaseZombie>();
    private int zombieYSpawn[] = new int[5];
    private boolean wave;
    private int houseHealth;
    private int level;
    private int zombieAmount;

    //booleans for menu pressing
    boolean menu;
    boolean walnutOn;
    boolean walnutContact;
    boolean mineplantOn;
    boolean mineplantContact;
    boolean peashooterOn;
    boolean peashooterContact;
    boolean icePeashooterOn;
    boolean icePeashooterContact;
    boolean cherryOn;
    boolean cherryContact;
    boolean sunflowerOn;
    boolean sunflowerContact;
    boolean shovelOn;
    boolean shovelContact;

    //Sprites and timers for menu
    Texture clock;
    Sprite clockMineplant;
    Sprite clockSunflower;
    Sprite clockWalnut;
    Sprite clockPeaShooter;
    Sprite clockCherry;
    Sprite clockIcePeashooter;

    float timerMineplant;
    float timerSunflower;
    float timerWalnut;
    float timerPeashooter;
    float timerCherry;
    float timerIcepeashooter;

    //second timer for cherry and mineplant
    float timerCherryExplosion;
    float timerMineplantExplosion;

    //shovel texture and shovel
    Texture dig;
    Sprite shovel;
    Sound shovelSound;

    //texture for health bar
    Texture bar;
    Sprite health;
    Texture houseTexture;
    Sprite house;

    //small zombie and level icon
    Texture smallZombie;
    Sprite zombieIcon;
    BitmapFont zombiesLeftCounter;
    Texture levelTexture;
    Sprite levelIcon;
    BitmapFont levelCounter;

    //for drawing text to screen
    BitmapFont sunFont;

    //sounds
    Sound place;
    Sound groan;
    float groanTimer;
    Sound music;
    float musicTimer;
    Sound plantGrow;
    Sound cherrySound;
    Sound damageSound;
    Sound breakHouse;
    Sound sploosh;

    //gamescreen constructor
    GameScreen(GameClass game) {
        setGame(game);
    }

    //show method to create texture and sprites
    @Override
    public void show() {
        setGarden(new Texture("PvZMapUpdated.png"));
        //setting up coordinate axis
        int offsetX = 80;
        int offsetY = 80;
        int counter = 0;

        //zombie amount starts at 20
        setZombieAmount(20);

        //booleans for menu pressing
        menu = true;
        walnutOn = false;
        mineplantOn = false;
        peashooterOn = false;
        icePeashooterOn = false;
        cherryOn = false;
        sunflowerOn = false;
        shovelOn = false;

        //timers and clock sprites
        clock = new Texture("clock.png");
        clockMineplant = new Sprite(clock);
        clockSunflower = new Sprite(clock);
        clockWalnut = new Sprite(clock);
        clockPeaShooter = new Sprite(clock);
        clockCherry = new Sprite(clock);
        clockIcePeashooter = new Sprite(clock);

        timerMineplant = 26;
        timerSunflower = 8;
        timerWalnut = 26;
        timerPeashooter = 8;
        timerCherry = 40;
        timerIcepeashooter = 8;

        //setting the y-coordinates for zombies to randomly spawn and wave boolean
        getZombieYSpawn()[0] = 420;
        getZombieYSpawn()[1] = 320;
        getZombieYSpawn()[2] = 220;
        getZombieYSpawn()[3] = 120;
        getZombieYSpawn()[4] = 20;
        setWave(true);

        //setting up zombie icon and level icon
        smallZombie = new Texture("smallZombie.png");
        zombieIcon = new Sprite(smallZombie);
        zombieIcon.setPosition(890, 520);
        zombiesLeftCounter = new BitmapFont();
        levelTexture = new Texture("levelText.png");
        levelIcon = new Sprite(levelTexture);
        levelIcon.setPosition(470,540);
        levelCounter = new BitmapFont();


        //giving the house a health value
        houseHealth = 3;

        //print healthbar and house to the screen
        bar = new Texture("threeHealth.png");
        health = new Sprite(bar);
        health.setPosition(290,545);
        houseTexture = new Texture("house.png");
        house = new Sprite(houseTexture);
        house.setPosition(210,535);

        //print shovel to the screen
        dig = new Texture("shovel.png");
        shovel = new Sprite(dig);
        shovel.setPosition(40,2);
        shovelSound = Gdx.audio.newSound(Gdx.files.internal("shovel.mp3"));

        //setting up bitmap fonts
        sunFont = new BitmapFont();

        //sounds
        place = Gdx.audio.newSound(Gdx.files.internal("place.mp3"));
        groan = Gdx.audio.newSound(Gdx.files.internal("groan.mp3"));
        groanTimer = 0;
        music = Gdx.audio.newSound(Gdx.files.internal("music.mp3"));
        musicTimer = 140f;
        plantGrow = Gdx.audio.newSound(Gdx.files.internal("plantGrow.mp3"));
        cherrySound = Gdx.audio.newSound(Gdx.files.internal("cherryBomb.mp3"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("melonimpact.mp3"));
        breakHouse = Gdx.audio.newSound(Gdx.files.internal("houseBreak.mp3"));
        sploosh = Gdx.audio.newSound(Gdx.files.internal("butter.mp3"));
    }

    //render method to render everything to the screen
    @Override
    public void render(float delta) {
        //test area
        System.out.println(groanTimer);
        //System.out.println("Sun count: " + sunCount);
        //timers
        timeSuns += Gdx.graphics.getDeltaTime();
        timerMineplant += Gdx.graphics.getDeltaTime();
        timerSunflower += Gdx.graphics.getDeltaTime();
        timerWalnut += Gdx.graphics.getDeltaTime();
        timerPeashooter += Gdx.graphics.getDeltaTime();
        timerCherry += Gdx.graphics.getDeltaTime();
        timerIcepeashooter += Gdx.graphics.getDeltaTime();
        groanTimer += Gdx.graphics.getDeltaTime();
        musicTimer += Gdx.graphics.getDeltaTime();


        //game music
        if (musicTimer >= 140) {
            music.play(1.0f);
            musicTimer = 0;
        }

        //checking clicks on menu (to add plant to plants arraylist)
        if (menu) {
            //walnut
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 9 && Gdx.input.getX() <= 82 && Gdx.input.getY() >= 154 && Gdx.input.getY() <= 258 && sunCount >= 50 && timerWalnut >= 26) {
                plants.add(new Walnut(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                walnutContact = true;
                menu = false;
            }
            //mineplant
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 9 && Gdx.input.getX() <= 82 && Gdx.input.getY() >= 28 && Gdx.input.getY() <= 135 && sunCount >= 25 && timerMineplant >= 26) {
                plants.add(new Mineplant(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                mineplantContact = true;
                menu = false;
            }
            //peashooter
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 98 && Gdx.input.getX() <= 172 && Gdx.input.getY() >= 154 && Gdx.input.getY() <= 258 && sunCount >= 100 && timerPeashooter >= 8) {
                plants.add(new Peashooter(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                peashooterContact = true;
                menu = false;
            }
            //ice peashooter
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 98 && Gdx.input.getX() <= 172 && Gdx.input.getY() >= 278 && Gdx.input.getY() <= 384 && sunCount >= 175 && timerIcepeashooter >= 8) {
                plants.add(new IcePeashooter(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                icePeashooterContact = true;
                menu = false;
            }
            //cherry
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 9 && Gdx.input.getX() <= 82 && Gdx.input.getY() >= 278 && Gdx.input.getY() <= 384 && sunCount >= 150 && timerCherry >= 40) {
                plants.add(new Cherry(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                cherryContact = true;
                menu = false;
            }
            //sunflower
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 98 && Gdx.input.getX() <= 172 && Gdx.input.getY() >= 28 && Gdx.input.getY() <= 135 && sunCount >= 50 && timerSunflower >= 8) {
                plants.add(new Sunflower(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50));
                sunflowerContact = true;
                menu = false;
            }
            //shovel
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() >= 40 && Gdx.input.getX() <= 116 && Gdx.graphics.getHeight() - Gdx.input.getY() >= 2 && Gdx.graphics.getHeight() - Gdx.input.getY() <= 92) {
                System.out.println("HIT SHOVEL");
                shovelContact = true;
                menu = false;
            }
        }

        //dragging plants across the screen (turns off menu)
        //walnut
        if (walnutContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                walnutContact = false;
            }
        }
        //mineplant
        if (mineplantContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                mineplantContact = false;
            }
        }
        //peashooter
        if (peashooterContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                peashooterContact = false;
            }
        }
        //ice peashooter
        if (icePeashooterContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                icePeashooterContact = false;
            }
        }
        //cherry
        if (cherryContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                cherryContact = false;
            }
        }
        //sunflower
        if (sunflowerContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                plants.get(plants.size()-1).getPlant().setPosition(Gdx.input.getX() - 50, Gdx.graphics.getHeight() - Gdx.input.getY() - 50);
            } else {
                drop();
                menu = true;
                sunflowerContact = false;
            }
        }
        //shovel
        if (shovelContact) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                shovel.setPosition(Gdx.input.getX() - 43, Gdx.graphics.getHeight() - Gdx.input.getY() - 45);
            } else {
                if (Gdx.input.getX() >= 240 && Gdx.input.getX() <= 980 && Gdx.input.getY() >= 80 && Gdx.input.getY() <= 570) {
                    int j = (int)((Gdx.input.getX() - 240) / 80);
                    int i = (int)((515 - (Gdx.graphics.getHeight() - Gdx.input.getY())) / 100);
                    if (plantGrid[i][j] != null) {
                        plantGrid[i][j].setHealth(0);
                        shovelSound.play(1.0f);
                    }
                }
                shovel.setPosition(40,2);
                menu = true;
                shovelContact = false;
            }
        }




        //adding clocks to the screen
        //mineplant
        if (timerMineplant < 26) {
            clockMineplant.setPosition(12,468);
        } else {
            clockMineplant.setPosition(-100,468);
        }
        //sunflower
        if (timerSunflower < 8) {
            clockSunflower.setPosition(100,468);
        } else {
            clockSunflower.setPosition(-100,468);
        }
        //walnut
        if (timerWalnut < 26) {
            clockWalnut.setPosition(12,345);
        } else {
            clockWalnut.setPosition(-100, 345);
        }
        //peashooter
        if (timerPeashooter < 8) {
            clockPeaShooter.setPosition(100, 345);
        } else {
            clockPeaShooter.setPosition(-100,345);
        }
        //cherry
        if (timerCherry < 40) {
            clockCherry.setPosition(12, 222);
        } else {
            clockCherry.setPosition(-100, 222);
        }
        //icepeashooter
        if (timerIcepeashooter < 8) {
            clockIcePeashooter.setPosition(100, 222);
        } else {
            clockIcePeashooter.setPosition(-100, 222);
        }


        //dropping suns on screen
        if (getTimeSuns() >= 5) {
            suns.add(new Sun(((int)(Math.random()*716)+ 240), 550, -2));
            setTimeSuns(0);
        }

        //translate suns
        for (int i = 0; i < suns.size(); i++) {
            suns.get(i).getSun().translateY(suns.get(i).getSpeed());
            if (suns.get(i).getSun().getY() <= (int)(Math.random()*150)) {
                suns.get(i).setSpeed(0);
                //System.out.println(suns.get(i).getSun().getY());
            }
        }

        //sun collision with mouse
        for (int i = 0; i < suns.size(); i++) {
            if (Gdx.input.getX() > suns.get(i).getSun().getX() && Gdx.input.getX() < suns.get(i).getSun().getX() + 64) {
                if (Gdx.graphics.getHeight() - Gdx.input.getY() < suns.get(i).getSun().getY() + 64 && Gdx.graphics.getHeight() - Gdx.input.getY() > suns.get(i).getSun().getY()) {
                    suns.remove(i);
                    sunCount += 25;
                }
            }
        }

        //suns collision with mouse for sunflower
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getId() == 2) {
                for (int j = 0; j < plants.get(i).getSuns().size(); j++) {
                    if (plants.get(i).getId() == 2) {
                        if (Gdx.input.getX() > plants.get(i).getSuns().get(j).getSun().getX() && Gdx.input.getX() < plants.get(i).getSuns().get(j).getSun().getX() + 64) {
                            if (Gdx.graphics.getHeight() - Gdx.input.getY() < plants.get(i).getSuns().get(j).getSun().getY() + 64 && Gdx.graphics.getHeight() - Gdx.input.getY() > plants.get(i).getSuns().get(j).getSun().getY()) {
                                plants.get(i).getSuns().remove(j);
                                sunCount += 25;
                            }
                        }
                    }
                }
            }
        }

        //adding waves of zombies
        if (getWave()) {
            zombieWave();
            setHouseHealth(3);
            setLevel(getLevel() + 1);
            setZombieAmount(getZombieAmount() + 5);
            setWave(false);

            //reset plants and timers
            for (int i = 0; i < plantGrid.length; i++) {
                for (int j = 0; j < plantGrid[0].length; j++) {
                    plantGrid[i][j] = null;
                }
            }
            plants.clear();
            timerMineplant = 26;
            timerSunflower = 8;
            timerWalnut = 26;
            timerPeashooter = 8;
            timerCherry = 40;
            timerIcepeashooter = 8;

        }

        //adding new wave
        if (zombies.size() == 0 && getLevel() < 9) {
            setWave(true);
        }

        //advance zombies
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).advance();
        }


        //have plants constantly attacking and dieing if health is 0
        for (int i = 0; i < plantGrid.length; i++) {
            for (int j = 0; j < plantGrid[0].length; j++) {
                if (plantGrid[i][j] != null) {
                    plantGrid[i][j].attack();
                    if (plantGrid[i][j].getHealth() <= 0) {
                        plantGrid[i][j] = null;
                    }
                }
            }
        }

        //remove plants from plants arraylist when they die
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getHealth() <= 0) {
                plants.remove(i);
            }
        }

        //remove zombies from arraylist if they die
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).getHealth() <= 0) {
                zombies.remove(i);
            }
        }

        //translate bullets when the zombie is in the lane of a plant / bullet collisions with zombies
        if (plants.size() > 0) {
            for (int i = 0; i < plants.size(); i++) {
                for (int j = 0; j < zombies.size(); j++) {
                    if (plants.get(i).getId() == 4 || plants.get(i).getId() == 6) {
                        if (zombies.get(j).getZombie().getY() == plants.get(i).getPlant().getY() && zombies.get(j).getZombie().getX() < 1020 && zombies.get(j).getZombie().getX() > plants.get(i).getPlant().getX()) {
                            System.out.println("Zombie Y: " + zombies.get(j).getZombie().getY());
                            System.out.println("plant Y: " + plants.get(i).getPlant().getY());
                            plants.get(i).setAttacking(true);
                            plants.get(i).setBulletSpeed(7);

                            //bullet collisions with zombies
                            for (int k = 0; k < plants.get(i).getBullets().size(); k++) {
                                if (plants.get(i).getBullets().get(k).getBullet().getBoundingRectangle().overlaps(zombies.get(j).getZombie().getBoundingRectangle())) {
                                    zombies.get(j).setHealth(zombies.get(j).getHealth() - plants.get(i).getBulletDamage());
                                    sploosh.play(1.0f);
                                    if (plants.get(i).getId() == 6) {
                                        zombies.get(j).setSpeed(-0.2f);
                                        zombies.get(j).freeze();
                                    }
                                    plants.get(i).getBullets().remove(k);
                                    plants.get(i).setAttacking(false);
                                }
                            }
                        }
                    }
                }
            }
        }

        //make sure peashooters dont keep shooting
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getBullets() != null) {
                for (int j = 0; j < plants.get(i).getBullets().size(); j++) {
                    if (plants.get(i).getBullets().get(j).getBullet().getX() >= 1000) {
                        plants.get(i).getBullets().remove(j);
                        System.out.println("bullet past barrier");
                        plants.get(i).setAttacking(false);
                    }
                }
            }
        }


        //cherry attacking with 3 second timer and mineplant to see if ready to explode
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getId() == 5) {
                timerCherryExplosion += Gdx.graphics.getDeltaTime();
                if (timerCherryExplosion > 3) {
                    if (plants.get(i).getId() == 5) {
                        if (timerCherryExplosion > 3) {
                            cherrySound.play(1.0f);
                            Rectangle hitbox = plants.get(i).getPlant().getBoundingRectangle().setSize(240, 300);
                            for (int k = 0; k < zombies.size(); k++) {
                                if (hitbox.overlaps(zombies.get(k).getZombie().getBoundingRectangle())) {
                                    zombies.remove(k);
                                }
                            }
                            plants.get(i).setHealth(0);
                        }
                    }
                    timerCherryExplosion = 0;
                }
            }
            if (plants.get(i).getId() == 1) {
                plants.get(i).setMineplantTimer(plants.get(i).getMineplantTimer() + Gdx.graphics.getDeltaTime());
                if (plants.get(i).getMineplantTimer() > 14) {
                    plants.get(i).setReady(true);
                    //plantGrow.play(1.0f);
                    plants.get(i).getPlant().setTexture(new Texture("minePlant.png"));
                }
            }
        }

        //collisions for all plants and all zombies
        for (int i = 0; i < plantGrid.length; i++) {
            for (int j = 0; j < plantGrid[0].length; j++) {
                if (plantGrid[i][j] != null) {
                    for (int k = 0; k < zombies.size(); k++) {
                        if (plantGrid[i][j].getPlant().getBoundingRectangle().overlaps(zombies.get(k).getZombie().getBoundingRectangle().setHeight(20)))  {
                            // this is so that the zombies in other grid locations dont collide with plants because the zombies are taller than the grid heights ^^
                            //mineplant
                            if (plantGrid[i][j].getId() == 1 && plantGrid[i][j].getReady()) {
                                cherrySound.play(1.0f);
                                plantGrid[i][j].setHealth(0);
                                zombies.remove(k);
                            } else {
                                zombies.get(k).attack();
                                plantGrid[i][j].setHealth(plantGrid[i][j].getHealth() - zombies.get(k).getDamage());
                            }
                        }
                    }
                }
            }
        }

        //zombies make noise
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.size() > 0 && zombies.get(i).getZombie().getX() < 1020) {
                if (groanTimer > 5) {
                    System.out.println("groan");
                    groan.play(1.0f);
                    groanTimer = 0;
                }
            }
        }


        //checking for zombies doing damage to the house
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).getZombie().getX() <= 210) {
                setHouseHealth(getHouseHealth() - 1);
                zombies.remove(i);
                breakHouse.play(1.0f);
            }
        }

        //switch statement for changing textures of health bar
        switch(getHouseHealth()) {
            case 1:
                health.setTexture(new Texture("oneHealth.png"));
                break;
            case 2:
                health.setTexture(new Texture("twoHealth.png"));
                break;
            case 3:
                health.setTexture(new Texture("threeHealth.png"));
                break;
        }


        //end game if healthbar is 0
        if (getHouseHealth() == 0) {
            getGame().setScreen(new EndScreen(getGame(), false));
        }

        //end game if player wins (level == 9)
        if (getLevel() == 9) {
            getGame().setScreen(new EndScreen(getGame(), true));
        }


        //shipping information to cpu
        getGame().getBatch().begin();
        getGame().getBatch().draw(getGarden(), 0, 0);
        printPlants();
        printSuns();
        printZombies();
        printBullets();
        printClocks();
        printExtras();
        getGame().getBatch().end();
    }

    //printing shovel, healthbar, zombie count and level count
    public void printExtras() {
        shovel.draw(getGame().getBatch());
        health.draw(getGame().getBatch());
        zombieIcon.draw(getGame().getBatch());
        levelIcon.draw(getGame().getBatch());
        house.draw(getGame().getBatch());

        zombiesLeftCounter.setColor(Color.GOLD);
        zombiesLeftCounter.getData().setScale(2);
        zombiesLeftCounter.draw(getGame().getBatch(), Integer.toString(zombies.size()), 865, 568);

        levelCounter.setColor(Color.GOLD);
        levelCounter.getData().setScale(3);
        levelCounter.draw(getGame().getBatch(), Integer.toString(getLevel()), 710, 588);

    }

    //print the clock
    public void printClocks() {
        clockMineplant.draw(getGame().getBatch());
        clockSunflower.draw(getGame().getBatch());
        clockWalnut.draw(getGame().getBatch());
        clockPeaShooter.draw(getGame().getBatch());
        clockCherry.draw(getGame().getBatch());
        clockIcePeashooter.draw(getGame().getBatch());
    }

    //prints plants at all times
    public void printPlants() {
        for (int i = 0; i < plants.size(); i++) {
            plants.get(i).getPlant().draw(getGame().getBatch());
        }
    }

    //printing the bullets
    public void printBullets() {
        if (plants.size() > 0) {
            for (int i = 0; i < plants.size(); i++) {
                if ((plants.get(i).getId() == 4 || plants.get(i).getId() == 6)  && !plants.get(i).getBullets().isEmpty()) {
                    plants.get(i).getBullets().get(plants.get(i).getBullets().size()-1).getBullet().draw(getGame().getBatch());
                }
            }
        }
    }

    //prints the suns
    public void printSuns() {
        for (int i = 0; i < suns.size(); i++) {
            suns.get(i).getSun().draw(getGame().getBatch());
        }
        //print suns
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getId() == 2) {
                for (int j = 0; j < plants.get(i).getSuns().size(); j++) {
                    plants.get(i).getSuns().get(j).getSun().draw(getGame().getBatch());
                }
            }
        }
        //print the number if suns
        sunFont.getData().setScale(1.5f);
        sunFont.setColor(Color.GOLD);
        sunFont.draw(getGame().getBatch(), Integer.toString(sunCount), 102, 143);
    }

    //prints the zombies at all times and make them talk
    public void printZombies() {
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).getZombie().draw(getGame().getBatch());
        }
    }

    //drop method on grid (or not on grid)
    public void drop() {
        if (Gdx.input.getX() >= 240 && Gdx.input.getX() <= 980 && Gdx.input.getY() >= 80 && Gdx.input.getY() <= 570) {
            int j = (int)((Gdx.input.getX() - 240) / 80);
            int i = (int)((515 - (Gdx.graphics.getHeight() - Gdx.input.getY())) / 100);
            if (plantGrid[i][j] == null) {
                place.play(1.0f);
                plantGrid[i][j] = plants.get(plants.size()-1);
                plants.get(plants.size()-1).getPlant().setPosition(240 + (j * 80),420 - (i * 100));
                sunCount -= plants.get(plants.size()-1).getCost();
            } else {
                plants.remove(plants.size()-1);
            }

            //timers reset for each - switch case
            int id = plantGrid[i][j].getId();
            switch (id) {
                case 1:
                    timerMineplant = 0;
                    break;
                case 2:
                    timerSunflower = 0;
                    break;
                case 3:
                    timerWalnut = 0;
                    break;
                case 4:
                    timerPeashooter = 0;
                    break;
                case 5:
                    timerCherry = 0;
                    break;
                case 6:
                    timerIcepeashooter = 0;
                    break;
            }

        } else {
            plants.remove(plants.size()-1);
        }
    }


    //zombie wave
    public void zombieWave() {
        //first zombie of each wave is flag zombie
        zombies.add(new Flagzombie((int)(1099), getZombieYSpawn()[(int)(Math.random()*5)]));

        //normal zombies more common
        for (int i = 0; i < getZombieAmount()/2; i++) {
            zombies.add(new Zombie((int)((Math.random()*7000) + 1100), getZombieYSpawn()[(int)(Math.random()*5)]));
        }
        for (int i = 0; i < getZombieAmount()/4; i++) {
            zombies.add(new Buckethead((int)((Math.random()*5000) + 4100), getZombieYSpawn()[(int)(Math.random()*5)]));
        }
        for (int i = 0; i < getZombieAmount()/4; i++) {
            zombies.add(new Pylonhead((int)((Math.random()*5000) + 4100), getZombieYSpawn()[(int)(Math.random()*5)]));
        }

    }


    //getters and setters
    public int getZombieAmount() {
        return zombieAmount;
    }

    public void setZombieAmount(int zombieAmount) {
        this.zombieAmount = zombieAmount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHouseHealth() {
        return houseHealth;
    }

    public void setHouseHealth(int houseHealth) {
        this.houseHealth = houseHealth;
    }

    public boolean getWave() {
        return wave;
    }

    public void setWave(boolean wave) {
        this.wave = wave;
    }

    public int[] getZombieYSpawn() {
        return zombieYSpawn;
    }

    public void setZombieYSpawn(int[] zombieYSpawn) {
        this.zombieYSpawn = zombieYSpawn;
    }

    public float getTimeSuns() {
        return timeSuns;
    }

    public void setTimeSuns(float time) {
        this.timeSuns = time;
    }

    public Texture getGarden() {
        return garden;
    }

    public void setGarden(Texture garden) {
        this.garden = garden;
    }

    public GameClass getGame() {
        return game;
    }

    public void setGame(GameClass game) {
        this.game = game;
    }

    //methods implemented
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
