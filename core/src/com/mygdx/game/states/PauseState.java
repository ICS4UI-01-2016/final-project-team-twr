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
    private StateManager sm;
    // Instance variable for the background (track 1 & 2)
    private Texture bg1;
    // Instance variable for the background ("Paused" image)
    private Texture bg2;
    // Instant variable for track
    private int track;
    // Instant variable for cartype 1
    private int carType1;
    private int carType2;
    // Instant variable for cartype 2

    /**
     * Constructor method for pause state
     *
     * @param sm
     * @param track
     * @param carType1
     * @param carType2
     */
    PauseState(StateManager sm, int track, int carType1, int carType2) {
        // Call the state manager from the super class
        super(sm);
        // Setting instance variables correctly
        this.sm = sm;
        this.track = track;
        this.carType1 = carType1;
        this.carType2 = carType2;
        // Creating the background image
        if (track == 1) {
            // Set bg1 to be track 1
            bg1 = new Texture("Track1.jpg");
        } else {
            // Set bg1 to be track 2 if not track 1
            bg1 = new Texture("Track2.1.jpg");
        }
        // Set bg2 to be the paused state image (made within photoshop
        bg2 = new Texture("PauseState.png");

    }

    /**
     * Method used to draw the images and rectangles (buttons)
     *
     * @param batch
     */
    @Override
    public void render(SpriteBatch batch) {
        // Set the camera properly        
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin the drawing
        batch.begin();
        // Set the camera view to be correct for the game
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Set viewport 
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Drawing the first background (JPEG image)
        batch.draw(bg1, 0, 0, getViewWidth(), getViewHeight());
        // Drawing the second background (png image)
        batch.draw(bg2, 0, 0, getViewWidth(), getViewHeight());
        // End the drawings
        batch.end();
    }

    /**
     * Empty update method
     *
     * @param DeltaTime
     */
    @Override
    public void update(float DeltaTime) {
    }

    /**
     * Method that deals with handling where the user clicks
     */
    @Override
    public void handleInput() {
        // If the alt key is pressed, show the paused state && if alt is pressed while pause state is up, it pops off
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
            // State pops off revealing the state underneath, the race state
            sm.pop();
        }
    }

    /**
     * Method that disposes the images that were used
     */
    @Override
    public void dispose() {
        // Dispose all the images used within this state
        bg1.dispose();
        bg2.dispose();
    }
}
