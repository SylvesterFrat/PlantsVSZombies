package com.omstead.gamebasics.PvZ;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


import java.awt.*;
import java.util.ArrayList;

public abstract class Plant {
    private int health;
    private Texture plantTexture;
    private Sprite plant;
    private int cost;
    private int id;
    private ArrayList<Bullet> bullets;
    private ArrayList<Sun> suns;
    private float bulletSpeed;
    private boolean attacking;
    private int bulletDamage;
    private boolean ready;
    private float mineplantTimer;

    //will be overridden in subclasses
    public void attack() {}


    //getters and setters
    public float getMineplantTimer() {
        return mineplantTimer;
    }

    public void setMineplantTimer(float mineplantTimer) {
        this.mineplantTimer = mineplantTimer;
    }

    public boolean getReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public boolean getAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public ArrayList<Sun> getSuns() {
        return suns;
    }

    public void setSuns(ArrayList<Sun> suns) {
        this.suns = suns;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Texture getPlantTexture() {
        return plantTexture;
    }

    public void setPlantTexture(Texture plantTexture) {
        this.plantTexture = plantTexture;
    }

    public Sprite getPlant() {
        return plant;
    }

    public void setPlant(Sprite plant) {
        this.plant = plant;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
