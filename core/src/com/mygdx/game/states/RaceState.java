/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author tatad6701
 */
public class RaceState {

    // Create the constant variables
    private Car[] cars;

    /**
     * Contructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
    }

    public void render(SpriteBatch batch) {
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();

    }
}
