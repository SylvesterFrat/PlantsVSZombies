package com.omstead.gamebasics;

import java.awt.*;
import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;



public class Basic2DTest extends ApplicationAdapter {
    SpriteBatch batch;
    Texture npc;
    Texture npc2;
    Texture slimePic;
    Sprite player;
    Texture background;
    int x = 640;
    int y = 115;
    float gravity = 0.5f;
    int slimeX;
    int slimeY = 115;
    ArrayList<Sprite> slimes;
    ArrayList<Sprite> arrows = new ArrayList<Sprite>();
    int health = 100;
    Texture arrowRight;
    Texture arrowLeft;
    float scrnWidth;
    float scrnHeight;
    int playervx = 0;
    int playervy = 0;

    long previousTime, timeLapseArrows, timeLapseSlimeHit, timeLapseJump;

    //font for text
    BitmapFont font;
    //font = new BitmapFont();

    CharSequence str = "Hello World!";


    @Override
    public void create () {
        scrnWidth = Gdx.graphics.getWidth();
        scrnHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        npc = new Texture("terrarianpc.png");
        npc2 = new Texture("terrarianpc2.png");
        player = new Sprite(npc);
        background = new Texture("terrariabckgrnd.jpeg");
        player.setPosition(x,y);

        slimePic = new Texture("slime_blue.png");
        slimes = new ArrayList<Sprite>();
        for (int i = 0; i < 4; i++) {
            Random rand = new Random();
            slimes.add(new Sprite(slimePic));
            slimeX = rand.nextInt(Gdx.graphics.getWidth()+1);
            slimes.get(i).setPosition(slimeX, slimeY);

        }

        arrowRight = new Texture("arrowright.png");
        arrowLeft = new Texture("arrowleft.png");

        previousTime = System.currentTimeMillis();

    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 45, 250, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeLapseJump += System.currentTimeMillis() - previousTime;
        timeLapseSlimeHit += System.currentTimeMillis() - previousTime;
        timeLapseArrows += System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();


        //player movement input (and projectile)
        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.getX() >= 0) {
            playervx = -5;
            player.setTexture(npc);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && player.getX() <= 1216) {
            playervx = 5;
            player.setTexture(npc2);
        } else {
            playervx = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getY() <= 115) {
            playervy = 20;
        } else if (player.getY() > 115) {
            playervy -= 1;
        }


        //stop player from going down
        if(player.getY() < 115) {
            player.setY(115);
            playervy = 0;
        }

        player.translate(playervx, playervy);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire();
        }


        //move arrows
        for (int i = 0; i < arrows.size(); i++) {
            if (player.getTexture() == npc2) {
                arrows.get(i).translate(5,-2);
            } else if(player.getTexture() == npc) {
                arrows.get(i).translate(-5,-2);
            }
            if (arrows.get(i).getX() > Gdx.graphics.getWidth() || arrows.get(i).getY() < 115) {
                arrows.remove(i);
            }
        }

        //slimes follow player
        for (int i = 0; i < slimes.size(); i++) {
            if (slimes.get(i).getX() < player.getX()) {
                slimes.get(i).translateX((int)(Math.random()*4+1));
            } else if (slimes.get(i).getX() > player.getX()) {
                slimes.get(i).translateX(-(int)(Math.random()*4+1));
            }
        }

        //players hitbox
        Rectangle playerBox = player.getBoundingRectangle();
        //collision between player and slime
        for (int i = 0; i < slimes.size(); i++) {
            Rectangle slimeBox = slimes.get(i).getBoundingRectangle();
            if (timeLapseSlimeHit > 1000) {
                timeLapseSlimeHit = 0;
                if (playerBox.overlaps(slimeBox)) {
                    health -= 5;
                    System.out.println("hit");
                }
            }
        }

        //player death
        if (health <= 0) {
            player.setPosition(435, 320); // sit him on the tree and let him starve
        }

        //draw to screen
        int level = 1;
        batch.begin();
        batch.draw(background, 0, 0);
        player.draw(batch);
        for (int i = 0; i < slimes.size(); i++) {
            slimes.get(i).draw(batch);
        }
        for (int i = 0; i < arrows.size(); i++) {
            arrows.get(i).draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        npc.dispose();
        slimePic.dispose();
    }

    public void fire() {
        if (timeLapseArrows > 500) {
            timeLapseArrows = 0;
            Sprite arrow;
            if (player.getTexture() == npc2) {
                arrow = new Sprite(arrowRight);
                arrow.setPosition(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2);
                arrows.add(arrow);
            } else if (player.getTexture() == npc){
                arrow = new Sprite(arrowLeft);
                arrow.setPosition(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2);
                arrows.add(arrow);
            }
        }
    }
}
