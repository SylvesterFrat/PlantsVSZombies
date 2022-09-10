package com.omstead.gamebasics.PvZ;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Walnut extends Plant{

    public Walnut() {}

    public Walnut(float x, float y) {
        setPlantTexture(new Texture("walnut.png"));
        setCost(50);
        setPlant(new Sprite(getPlantTexture()));
        getPlant().setPosition(x,y);
        setHealth(500);
        setId(3);
    }
}
