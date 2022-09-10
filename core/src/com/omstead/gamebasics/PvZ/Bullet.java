package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    private Texture bulletTexture;
    private Sprite bullet;

    public Bullet(int x, int y, String colour) {
        if (colour.equals("Green")) {
            setBulletTexture(new Texture("pea.png"));
        } else if (colour.equals("Blue")) {
            setBulletTexture(new Texture("snowpea.png"));
        }
        setBullet(new Sprite(getBulletTexture()));
        getBullet().setPosition(x,y);
    }

    //getters and setters

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public void setBulletTexture(Texture bulletTexture) {
        this.bulletTexture = bulletTexture;
    }

    public Sprite getBullet() {
        return bullet;
    }

    public void setBullet(Sprite bullet) {
        this.bullet = bullet;
    }
}
