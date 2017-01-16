/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Car;
import com.mygdx.game.RaceIt;
import java.util.ArrayList;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class CarChoiceState extends State {

    // Create constant variables
    private StateManager sm;
    private Texture PickCarBackground;
    private Texture lambo;
    private Texture acura;
    private Texture lambo2;
    private Texture bentley;
    // Creating instance variables for the next button
    private Texture picNextButton;
    private Rectangle nextButton;
    // Creating instance variables for the back button
    private Texture picBackButton;
    private Rectangle backButton;

    /**
     * Constructor for the pick track screen for the players
     *
     * @param sm the state manager
     */
    public CarChoiceState(StateManager sm) {
        super(sm);
        PickCarBackground = new Texture("CarChoiceState.jpg");
        lambo = new Texture("lamborghiniblack.png");
        acura = new Texture("acura.png");
        lambo2 = new Texture("lamborghini2.png");
        bentley = new Texture("Bentley2.png");
        // Placing the next button
        picNextButton = new Texture("blackrectangle.png");
        nextButton = new Rectangle(763, 30, 215, 50);
        // Placing the back button
        picBackButton = new Texture("blackrectangle.png");
        backButton = new Rectangle(19, 30, 220, 50);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        // Beginning the drawings
        batch.begin();
        // Drawing the back button
        batch.draw(picBackButton, backButton.x, backButton.y, backButton.width, backButton.height);
        // Drawing the next button option
        batch.draw(picNextButton, nextButton.x, nextButton.y, nextButton.width, nextButton.height);
        batch.draw(PickCarBackground, 0, 0, getViewWidth(), getViewHeight());
        // Ending the drawings
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
        if (Gdx.input.justTouched()) {
            // If the next button is clicked on, the state then changes to pick a track state
            if (touch.x >= nextButton.x && touch.x <= nextButton.x + nextButton.width
                    && touch.y >= nextButton.y && touch.y <= nextButton.y + nextButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to pick a track state
                gsm.push(new PickTrackState(gsm));
            }

            // If the back button is clicked on, the state then changes to menu state
            if (touch.x >= backButton.x && touch.x <= backButton.x + backButton.width
                    && touch.y >= backButton.y && touch.y <= backButton.y + backButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to pick a track state
                gsm.push(new MenuState(gsm));
            }

            // Saving Player 1's clicked option
            // Saving Player 2's clicked option
        }

    }

    @Override
    public void dispose() {
        PickCarBackground.dispose();
        picNextButton.dispose();
        picBackButton.dispose();
    }
}
