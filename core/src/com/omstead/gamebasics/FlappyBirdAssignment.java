package com.omstead.gamebasics;

import java.awt.*;
import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyBirdAssignment extends ApplicationAdapter {
    //field variables
    private SpriteBatch batch;
    private Texture background;
    private int backx;
    private Texture flappy;
    private Sprite bird;
    private int birdX = 50;
    private int birdY = 300;
    private int birdvy;
    private Texture plumbing;
    private Sprite[] pipes = new Sprite[50];
    Random rand;
    boolean dead;
    private Texture gameover;
    private boolean gamedone = false;
    private float gravity;
    private Sprite game;


    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("background1.png");
        backx = 0;

        //creating bird
        flappy = new Texture("Flappy.png");
        bird = new Sprite(flappy);
        bird.setPosition(birdX, birdY);

        //creating array of pipes
        rand = new Random();
        plumbing = new Texture("pipe.png");
        for (int i = 0; i < pipes.length; i++) {
            //first half of array is normal pipes
            if (i <= 25) {
                pipes[i] = new Sprite(plumbing);
                int pipex = rand.nextInt(4000) + 200;
                int pipey = rand.nextInt(200) - 220;
                pipes[i].setPosition(pipex, pipey);
            }
            //second half of array is upside down pipes
            if (i > 25) {
                pipes[i] = new Sprite(plumbing);
                pipes[i].rotate(180);
                int pipex = rand.nextInt(4000) + 200;
                int pipey = rand.nextInt(200) + 270;
                pipes[i].setPosition(pipex, pipey);
            }

        }

        //creating game over
        gameover = new Texture("gameover.png");
        game = new Sprite(gameover);
        game.setScale(4);
        game.setPosition(300,100);

    }

    @Override
    public void render () {
        //draw to screen
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(background, backx, 0);
        bird.draw(batch);
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].draw(batch);
        }
        if (gamedone) {
            game.draw(batch);
        }
        batch.end();

        boolean dead = false;
        //bird flying movement
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !dead && bird.getY() < 440) {
            birdvy = 6;
            gravity = 0.1f;
            bird.setRotation(30);
        } else {
            gravity += 0.05;
            bird.translateY(-5 * gravity);
            birdvy = 0;
            bird.setRotation(-15 * gravity);
        }
        bird.translateY(birdvy);

        //bird hits ground
        if (bird.getY() <= 0) {
            bird.setPosition(birdX, birdY);
            for (int i = 0; i < pipes.length; i++) {
                pipes[i].translateX(5);
            }
            gamedone = true;
        }
        //pipe movement
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].translateX(-5);
        }

        //pipe roll off screen - bottom
        for (int i = 0; i <= pipes.length/2; i++) {
            if (pipes[i].getX() <= 30) {
                pipes[i].translateY(-10);
            }
        }

        //pipe roll off screen top
        for (int i = 25; i < pipes.length; i++) {
            if (pipes[i].getX() <= 30) {
                pipes[i].translateY(10);
            }
        }

        //pipe bird collision
        Rectangle birdBox = bird.getBoundingRectangle();
        birdBox.setSize(45,33); //makes it so that the birds hitbox is better size (without this the collision looks off)
        Rectangle pipeBox;
        for (int i = 0; i < pipes.length; i++) {
            pipeBox = pipes[i].getBoundingRectangle();
            if (birdBox.overlaps(pipeBox)) {
                bird.setPosition(birdX, birdY);
                for (int j = 0; j < pipes.length; j++) {
                    pipes[j].translateX(5);
                }
                dead = true;
                System.out.println("Game Over");
                gamedone = true;
            }
        }

    }

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();
    }
}
