package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Sun {
    private Texture sunTexture;
    private int speed;
    private Sprite sun;
    public Sun() {}

    public Sun(float x, float y, int speed) {
        setSunTexture(new Texture("suns.png"));
        setSun(new Sprite(getSunTexture()));
        getSun().setPosition(x,y);
        setSpeed(speed);
    }


    //getters and setters

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Texture getSunTexture() {
        return sunTexture;
    }

    public void setSunTexture(Texture sunTexture) {
        this.sunTexture = sunTexture;
    }

    public Sprite getSun() {
        return sun;
    }

    public void setSun(Sprite sun) {
        this.sun = sun;
    }
}

