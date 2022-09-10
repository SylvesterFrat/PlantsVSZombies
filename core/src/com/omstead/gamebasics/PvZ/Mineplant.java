package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mineplant extends Plant {

    public Mineplant() {}

    public Mineplant(float x, float y) {
        setPlantTexture(new Texture("minePlantUnarmed.png"));
        setCost(25);
        setPlant(new Sprite(getPlantTexture()));
        getPlant().setPosition(x,y);
        setHealth(200);
        setId(1);
    }

}
