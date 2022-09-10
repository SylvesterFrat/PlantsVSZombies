package com.mygdx.game;

import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SideScrollAssignment extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background1, background2;
    private Texture flappy;
    private Sprite bird;
    private int birdX = 100;
    private int birdY= 75;
    private int birdvx = 10;
    private int birdvy = 10;
    private int backx1, backx2 = 0;
    private int max;
    private ArrayList<Sprite> lollis = new ArrayList<Sprite>();
    private Texture sucker;
    private long previousTime, timeLapseProjectiles;
    private boolean jump;


    @Override
    public void create () {
        batch = new SpriteBatch();
        background1 = new Texture("sideScrollBackground.png");
        background2 = new Texture("sideScrollBackground.png");

        flappy = new Texture("Pinky.png");
        bird = new Sprite(flappy);
        bird.setPosition(birdX, birdY);
        max = 4096;
        backx1 = max*(-1);
        backx2 = 0;

        sucker = new Texture("lollipop_pink.png");

        previousTime = System.currentTimeMillis();
        jump = false;

    }

    @Override
    public void render () {

        timeLapseProjectiles += System.currentTimeMillis() - previousTime;
        previousTime = System.currentTimeMillis();

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        //endless background
        backx2 = backx1 + max;
        if (backx1 >= 0) {
            backx1 = max*(-1);
            backx2 = 0;
        }
        else if (backx2 <= 0) {
            backx2 = max;
            backx1 = 0;
        }


        batch.draw(background1, backx1, 0);
        batch.draw(background2, backx2, 0);

        for (int i = 0; i < lollis.size(); i++) {
            lollis.get(i).draw(batch);
        }
        bird.draw(batch);
        batch.end();




        //background movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bird.getX() <= 100) {
            backx2+=5;
            backx1+=5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && bird.getX() >= 372) {
            backx2-=5;
            backx1-=5;
        }

        //bird movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bird.getX() > 100) {
            bird.translateX(birdvx * -1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && bird.getX() < 372) {
            bird.translateX(birdvx);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !jump) {
            birdvy = 25;
            jump = true;
        } else if (bird.getY() > 150) {
            bird.translateY(-5);
            jump = true;
        }
        bird.translateY(birdvy);
        if (bird.getY() <= 75) {
            jump = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && bird.getY() <= 75) {
            birdvy = 20;
        } else if (bird.getY() > 75) {
            birdvy -= 1;
        }

        if (bird.getY() <= 74.9) {
            bird.setY(75);
            birdvy = 0;
        }

        //projectile fire
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fire();
        }
        for (int i = 0; i < lollis.size(); i++) {
            lollis.get(i).translateX(15);
            if (lollis.get(i).getX() > Gdx.graphics.getWidth() || lollis.get(i).getY() < 100) {
                lollis.remove(i);
            }
        }

    }

    public void fire() {
        if (timeLapseProjectiles > 500) {
            timeLapseProjectiles = 0;
            Sprite lollipop;
            lollipop = new Sprite(sucker);
            lollipop.setPosition(bird.getX() + bird.getWidth()/2, bird.getY() - 30 + bird.getHeight()/2);
            lollis.add(lollipop);
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        background1.dispose();
    }
}
