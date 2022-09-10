package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Flagzombie extends BaseZombie implements IShootable{
    private int health;
    private Texture zombieTexture;
    private Sprite zombie;
    private float speed;
    private int damage;
    long previousTime = System.currentTimeMillis();
    long attackTime;
    Sound damageSound = Gdx.audio.newSound(Gdx.files.internal("melonimpact.mp3"));


    public Flagzombie(int x, int y) {
        setZombieTexture(new Texture("flagZombie.png"));
        setZombie(new Sprite(getZombieTexture()));
        setHealth(200);
        getZombie().setPosition(x,y);
        setSpeed(-0.35f);
    }


    public void advance() {
        getZombie().translateX(getSpeed());
    }

    public void attack() {
        attackTime += System.currentTimeMillis() - previousTime;
        getZombie().translateX(getSpeed() * -1);
        setDamage(0);
        if (attackTime > 1000) {
            damageSound.play(0.5f);
            System.out.println("does damage");
            setDamage(20);
            attackTime = 0;
            previousTime = System.currentTimeMillis();
        }
    }

    public void freeze() {
        getZombie().setTexture(new Texture("flagZombieFrozen.png"));
        getFreezeSound().play(1.0f);
    }

    //getters and setters

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
