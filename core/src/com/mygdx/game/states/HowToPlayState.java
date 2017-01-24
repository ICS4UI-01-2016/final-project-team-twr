/*
 * This class deals with explaining how to play the game as player 1 and player 2!
 * 
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class HowToPlayState extends State {

    // Create instance variables
    // Instance variable for the background image 
    private Texture howToPlay;
    // Instance variables for the back button
    private Texture picBackButton;
    private Rectangle backButton;

    /**
     * Constructor method for the how to play state
     *
     * @param sm
     */
    HowToPlayState(StateManager sm) {
        // Call the state manager from the super class
        super(sm);
        // Creating the background
        howToPlay = new Texture("HowToPlayState.jpg");
        // Creating the rectangle (picture) for the back button
        picBackButton = new Texture("blackrectangle1.png");
        // Creating the rectangle for the back button 
        backButton = new Rectangle(40, 23, 218, 62);
        // Set the camera view to be correct for the game
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    /**
     * Method used to draw the images and rectangles (buttons)
     *
     * @param batch
     */
    @Override
    public void render(SpriteBatch batch) {
        // Sets camera properly
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin to draw
        batch.begin();
        // Draw the back button
        batch.draw(picBackButton, backButton.x, backButton.y, backButton.width, backButton.height);
        // Draw the background image
        batch.draw(howToPlay, 0, 0, getViewWidth(), getViewHeight());
        // End the drawing
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
        // Get the mouse click/touch position of the user
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // Convert the point to game coordinates
        unproject(touch);

        if (Gdx.input.justTouched()) {
            // If the back button is clicked on, then the state changes to the menu state
            if (touch.x >= backButton.x && touch.x <= backButton.x + backButton.width
                    && touch.y >= backButton.y && touch.y <= backButton.y + backButton.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change state to MenuState
                gsm.push(new MenuState(gsm, 3));
            }
        }
    }

    /**
     * Method that disposes the images that were used
     */
    @Override
    public void dispose() {
        // Dispose all the images used within this state
        howToPlay.dispose();
        picBackButton.dispose();
    }
}
