/*
 * This class deals with the desciption of the actual game itself!
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
public class DescriptionState extends State {

    // Creating instant variables
    // Instance variable for the background image
    private Texture description;
    // Instance variable for the back button
    private Rectangle backButtonRectangle;
    private Texture button;

    /**
     * Constructor method for the description state
     *
     * @param sm
     */
    public DescriptionState(StateManager sm) {
        // Call the state manager from the super class
        super(sm);
        // Creating the background image
        description = new Texture("Description.jpg");
        // Creating and placing the back button
        button = new Texture("blackrectangle1.png");
        backButtonRectangle = new Rectangle(22, 18, 175, 48);
        // Set the camera to view the state properly 
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
        // Begin the drawings
        batch.begin();
        // Drawing the back button option
        batch.draw(button, backButtonRectangle.x, backButtonRectangle.y, backButtonRectangle.width, backButtonRectangle.height);
        // Drawing the description background image 
        batch.draw(description, 0, 0, getViewWidth(), getViewHeight());
        // Ending the drawings
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
            // If the "Back" button option is touched, then go back to DescriptionState
            if (touch.x >= backButtonRectangle.x && touch.x <= backButtonRectangle.x + backButtonRectangle.width
                    && touch.y >= backButtonRectangle.y && touch.y <= backButtonRectangle.y + backButtonRectangle.height) {
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
        description.dispose();
        button.dispose();
    }
}
