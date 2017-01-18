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
    private Texture Rect;
    // Creating instance variables for the next button
    private Texture picNextButton;
    private Rectangle nextButton;
    // Creating instance variables for the back button
    private Texture picBackButton;
    private Rectangle backButton;
    // Creating instance variables for the car choice buttons
    private Rectangle player1Lambo;
    private Rectangle player1Acura;
    private Rectangle player1Lambo2;
    private Rectangle player1Bentley;
    private Rectangle player2Lambo;
    private Rectangle player2Acura;
    private Rectangle player2Lambo2;
    private Rectangle player2Bentley;
    private int car1Type;
    private int car2Type;

    /**
     * Constructor for the pick track screen for the players
     *
     * @param sm the state manager
     */
    public CarChoiceState(StateManager sm) {
        super(sm);
        // Creating the images
        PickCarBackground = new Texture("CarChoiceState.png");
        // Creating the car images 
        lambo = new Texture("lamborghiniblack.png");
        acura = new Texture("acura.png");
        lambo2 = new Texture("lamborghini2.png");
        bentley = new Texture("Bentley2.png");
        Rect = new Texture("badlogic.jpg");
        // Placing the next button
        picNextButton = new Texture("blackrectangle1.png");
        nextButton = new Rectangle(799, 30, 144, 50);
        // Placing the back button
        picBackButton = new Texture("blackrectangle1.png");
        backButton = new Rectangle(56, 29, 147, 50);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Placing the car choice buttons for player 1
        player1Lambo = new Rectangle(81, 215, 100, 100);
        player1Acura = new Rectangle(324, 215, 100, 100);
        player1Lambo2 = new Rectangle(520, 215, 100, 100);
        player1Bentley = new Rectangle(742, 215, 100, 100);
        // Placing the car choice buttons for player 1
        player2Lambo = new Rectangle(81, 515, 100, 100);
        player2Acura = new Rectangle(324, 515, 100, 100);
        player2Lambo2 = new Rectangle(520, 515, 100, 100);
        player2Bentley = new Rectangle(742, 515, 100, 100);

        car1Type = 1;
        car2Type = 2;

    }
    
    /**
     * Method used to 
     * @param batch 
     */
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
                System.out.println(car1Type);
                System.out.println(car2Type);
                gsm.push(new PickTrackState(gsm, car1Type, car2Type));
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
            if (touch.x >= player1Lambo.x && touch.x <= player1Lambo.x + player1Lambo.width
                    && touch.y >= player1Lambo.y && touch.y <= player1Lambo.y + player1Lambo.height) {
                System.out.println("p1");
                car1Type = 1;
            }
            if (touch.x >= player1Acura.x && touch.x <= player1Acura.x + player1Acura.width
                    && touch.y >= player1Acura.y && touch.y <= player1Acura.y + player1Acura.height) {
                System.out.println("p2");
                car1Type = 2;
            }
            if (touch.x >= player1Lambo2.x && touch.x <= player1Lambo2.x + player1Lambo2.width
                    && touch.y >= player1Lambo2.y && touch.y <= player1Lambo2.y + player1Lambo2.height) {
                System.out.println("p3");
                car1Type = 3;
            }
            if (touch.x >= player1Bentley.x && touch.x <= player1Bentley.x + player1Bentley.width
                    && touch.y >= player1Bentley.y && touch.y <= player1Bentley.y + player1Bentley.height) {
                System.out.println("p4");
                car1Type = 4;
            }

            if (touch.x >= player2Lambo.x && touch.x <= player2Lambo.x + player2Lambo.width
                    && touch.y >= player2Lambo.y && touch.y <= player2Lambo.y + player2Lambo.height) {
                car2Type = 1;
            }
            if (touch.x >= player2Acura.x && touch.x <= player2Acura.x + player2Acura.width
                    && touch.y >= player2Acura.y && touch.y <= player2Acura.y + player2Acura.height) {
                car2Type = 2;
            }
            if (touch.x >= player2Lambo2.x && touch.x <= player2Lambo2.x + player2Lambo2.width
                    && touch.y >= player2Lambo2.y && touch.y <= player2Lambo2.y + player2Lambo2.height) {
                car2Type = 3;
            }
            if (touch.x >= player2Bentley.x && touch.x <= player2Bentley.x + player2Bentley.width
                    && touch.y >= player2Bentley.y && touch.y <= player2Bentley.y + player2Bentley.height) {
                car2Type = 4;
            }
        }

    }

    @Override
    public void dispose() {
        PickCarBackground.dispose();
        picNextButton.dispose();
        picBackButton.dispose();
    }
}
