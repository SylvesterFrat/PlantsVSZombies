package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Sunflower extends Plant {
    long previousTime = System.currentTimeMillis();
    long attackTime;

    public Sunflower() {}

    public Sunflower(float x, float y) {
        setPlantTexture(new Texture("sunFlower.png"));
        setCost(50);
        setPlant(new Sprite(getPlantTexture()));
        getPlant().setPosition(x,y);
        setHealth(200);
        setId(2);
        setSuns(new ArrayList<Sun>());
    }

    public void attack() {
        attackTime += System.currentTimeMillis() - previousTime;
        if (attackTime > 2400000) {
            getSuns().add(new Sun(getPlant().getX() + 30, getPlant().getY(), 0));
            attackTime = 0;
            previousTime = System.currentTimeMillis();
        }
    }

}
