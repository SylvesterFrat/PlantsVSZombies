package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cherry extends Plant {
    public Cherry() {}

    public Cherry(float x, float y) {
        setPlantTexture(new Texture("cherry.png"));
        setCost(150);
        setPlant(new Sprite(getPlantTexture()));
        getPlant().setPosition(x,y);
        setHealth(200);
        setId(5);
    }
}
