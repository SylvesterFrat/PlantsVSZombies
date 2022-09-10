package com.omstead.gamebasics.PvZ;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {
    private SpriteBatch batch;

    //create method for creating SpriteBatch
    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new StartScreen(this));
    }

    //getters and setters
    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

}
