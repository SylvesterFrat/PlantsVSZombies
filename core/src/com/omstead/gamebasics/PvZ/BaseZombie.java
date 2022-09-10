package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class BaseZombie {
    private int health;
    private Texture zombieTexture;
    private Sprite zombie;
    private float speed;
    private int damage;
    long previousTime = System.currentTimeMillis();
    long attackTime;
    private Sound freezeSound = Gdx.audio.newSound(Gdx.files.internal("frozen.mp3"));


    public void advance() {}

    public void attack() {}

    public void freeze() {}


    //getters and setters

    public Sound getFreezeSound() {
        return freezeSound;
    }

    public void setFreezeSound(Sound freezeSound) {
        this.freezeSound = freezeSound;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Texture getZombieTexture() {
        return zombieTexture;
    }

    public void setZombieTexture(Texture zombieTexture) {
        this.zombieTexture = zombieTexture;
    }

    public Sprite getZombie() {
        return zombie;
    }

    public void setZombie(Sprite zombie) {
        this.zombie = zombie;
    }
}
