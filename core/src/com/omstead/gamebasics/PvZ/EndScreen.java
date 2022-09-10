package com.omstead.gamebasics.PvZ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EndScreen implements Screen, InputProcessor{
    //fields
    private GameClass game;
    private Texture startButtonPic;
    private Sprite startButton;
    private Texture startScreenPic;
    private boolean playerWon;


    //Constructor to define game
    public EndScreen(GameClass game, boolean playerWon) {
        setGame(game);
        setPlayerWon(playerWon);
    }

    //create or show method for initalizing everything
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        if (getPlayerWon()) {
            startScreenPic = new Texture("gameOverWinScreen.png");
        } else {
            startScreenPic = new Texture("gameOverScreen.jpg");

        }
    }

    //render method to display everything
    @Override
    public void render(float delta) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            System.out.println(Gdx.input.getX());
            System.out.println(Gdx.input.getY());
        }

        //checking if start button is pressed
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            buttonPressed();
        }


        //sending information to cpu with batch
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getGame().getBatch().begin();
        getGame().getBatch().draw(getStartScreenPic(), 0 ,0 );
        getGame().getBatch().end();
    }

    //touchDown method to switch screen after pressing start button
    public void buttonPressed() {
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        System.out.println(x);
        System.out.println(y);
        if (x >= 464 && x <= 565 && y >= 521 && y <= 560) {
            System.exit(0);
        }
    }


    //methods implemented
    @Override
    public boolean touchDown(int i , int i1, int i2, int i3) {
        return false;
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //getters and setters

    public boolean getPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    public GameClass getGame() {
        return game;
    }

    public void setGame(GameClass game) {
        this.game = game;
    }

    public Texture getStartButtonPic() {
        return startButtonPic;
    }

    public void setStartButtonPic(Texture startButtonPic) {
        this.startButtonPic = startButtonPic;
    }

    public Sprite getStartButton() {
        return startButton;
    }

    public void setStartButton(Sprite startButton) {
        this.startButton = startButton;
    }

    public Texture getStartScreenPic() {
        return startScreenPic;
    }

    public void setStartScreenPic(Texture startScreenPic) {
        this.startScreenPic = startScreenPic;
    }



    //methods implemented
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}