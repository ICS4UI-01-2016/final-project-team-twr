/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Car;
import java.util.ArrayList;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class CarChoiceState extends State {
    // Create constant variables
    private StateManager sm;
    private Texture PickTrackBackground;
    private Texture lambo;
    private Texture acura;
    private Texture lambo2;
    private Texture bentley;

    /**
     * Constructor for the pick track screen for the players
     *
     * @param sm the state manager
     */
    public CarChoiceState(StateManager sm) {
        super(sm);
        //PickTrackBackground = new Texture("");
        lambo = new Texture("lamborghiniblack.png");
        acura = new Texture("acura.png");
        lambo2 = new Texture("lamborghini2.png");
        bentley = new Texture("Bentley2.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(lambo, 0, 0, getViewWidth(), getViewHeight());
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        // Get the mouse click/touch position
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // Convert the point to game coordinates
        unproject(touch);
        // Button location
        //float buttonX = getViewWidth() / 2 - button.getWidth() / 2;
        //float buttonY = getViewHeight() / 2;
    }

    @Override
    public void dispose() {
        lambo.dispose();
    }
}
