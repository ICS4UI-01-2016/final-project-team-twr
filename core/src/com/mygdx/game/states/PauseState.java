/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class PauseState extends State {

    // Create instance variables
    private Texture bg;
    private int track;
    private int carType1;
    private int carType2;
    private StateManager sm;

    PauseState(StateManager sm, int track, int carType1, int carType2) {
        super(sm);
        this.sm = sm;
        this.track = track;
        this.carType1 = carType1;
        this.carType2 = carType2;
        // Creating the background image
        bg = new Texture("PauseState.png");
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)){
            sm.pop();
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
    }

}
