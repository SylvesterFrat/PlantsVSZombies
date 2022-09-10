package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class IcePeashooter extends Plant {
    long previousTime = System.currentTimeMillis();
    long attackTime;
    Sound bulletSound = Gdx.audio.newSound(Gdx.files.internal("shoop.mp3"));

    public IcePeashooter() {}

    public IcePeashooter(float x, float y) {
        setPlantTexture(new Texture("icePeaShooter.png"));
        setCost(175);
        setPlant(new Sprite(getPlantTexture()));
        getPlant().setPosition(x,y);
        setHealth(200);
        setId(6);
        setBullets(new ArrayList<Bullet>());
        setBulletSpeed(0f);
        setAttacking(false);
        setBulletDamage(20);
    }

    public void attack() {
        if (getAttacking()) {
            attackTime += System.currentTimeMillis() - previousTime;
            if (attackTime > 80000) {
                getBullets().add(new Bullet((int)(getPlant().getX()) + 75, (int)(getPlant().getY()) + 65, "Blue"));
                bulletSound.setVolume(50,50);
                bulletSound.play(1.0f);
                attackTime = 0;
                previousTime = System.currentTimeMillis();
            }
            for (int i = 0; i < getBullets().size(); i++) {
                getBullets().get(i).getBullet().translateX(getBulletSpeed());
            }
        }
    }
}
