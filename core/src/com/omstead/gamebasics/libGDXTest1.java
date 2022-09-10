package com.omstead.gamebasics;

import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//SYLVESTER FRATARCANGELI
public class libGDXTest1 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture flappy;
	Sprite bird;
	Texture justin;
	Sprite bullet;
	Texture invader;
	Sprite enemy;
	float x;
	float y;
	float enemyv = 5;
	int vy = 2;
	int score = 0;
	ArrayList<Sprite> bullets = new ArrayList<Sprite>();


	@Override
	public void create() {
		// background = new Texture("BackdropBlackLittleSparkBlack.png")
		batch = new SpriteBatch();

		//creating the bird (and scaling)
		flappy = new Texture("Pinky.png");
		bird = new Sprite(flappy);
		x = Gdx.graphics.getWidth() / 2;
		y = 15;
		bird.setPosition(x,y);

		//creating bullets and scaling
		justin = new Texture("headshot.png");

		//creating the invader
		invader = new Texture("invader.png");
		enemy = new Sprite(invader);
		enemy.setPosition(20, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);



		System.out.println("Screen size: " + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//move bird with mouse input
		int birdX = Gdx.input.getX();
		if (birdX > flappy.getWidth()*0.5 && birdX < Gdx.graphics.getWidth() - flappy.getWidth()*0.5) {
			bird.setPosition((float)(birdX-0.5*bird.getWidth()), y);
		}

		//fire projectile
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			fire();
		}
		//move bullets and removed at the top
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).translateY(5);
		}

		for (int i = bullets.size()-1; i > 0; i--) {
			if (bullets.get(i).getX() > Gdx.graphics.getHeight()) {
				bullets.remove(i);
			}
			//collision
			if (enemy.getBoundingRectangle().overlaps(bullets.get(i).getBoundingRectangle())) {
				enemy.setPosition((int)((Math.random()*Gdx.graphics.getWidth()-200)+200), Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/6);
				enemyv = 5;
				score += 1;
				System.out.println("Score : " + score);
			}

		}


		//invader movement
		enemy.translateX(enemyv);
		if (enemy.getX() > Gdx.graphics.getWidth() - 55 || enemy.getX() < 5) {
			enemy.translateY(-Gdx.graphics.getHeight()/6);
			enemyv *= -1;
		}

		if (enemy.getY() < bird.getY()) {
			enemy.setPosition(100000, -100000);
		}





		//send sprites and textures
		batch.begin();
		bird.draw(batch);
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(batch);
		}
		enemy.draw(batch);
		batch.end();
	}

	private void fire() {
		//create new projectile
		Sprite bullet = new Sprite(justin);
		bullet.setScale(.4f, .4f);
		//projectiles starts at sprite
		bullet.setPosition(bird.getX(), bird.getY());
		bullets.add(bullet);
	}
}
