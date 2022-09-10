package com.omstead.gamebasics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class L3AMoveSpritesSimpleMovement extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;	// character on game
	int x, y = 0;

	Texture blue;
	Sprite blueSprite;

	@Override
	public void create () {
		batch = new SpriteBatch();
		x = 300;
		img = new Texture("swirl_blue.png");	// create a Texture (image)
		sprite = new Sprite(img);				// create a Sprite from your Texture
		sprite.setPosition(x, y);				// set initial position
		System.out.println(Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight());

		blue = new Texture("mm_blue.png");
		blueSprite = new Sprite(blue);
		blueSprite.setPosition(x,y);

	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 45, 250, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sprite.translateX(5.0f);			// translateX moves the sprite's x position by a set amt each iteration
		blueSprite.translateX(3.0f);

		batch.begin();
		sprite.draw(batch);
		blueSprite.draw(batch);
		batch.end();
	}
}

