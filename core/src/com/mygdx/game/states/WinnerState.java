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
import com.mygdx.game.RaceIt;

/**
 *
 * @author richj0985
 */
public class WinnerState extends State {

    // Create instance variables
    // Instance variables for player 1 and 2 winning
    private Texture player1Winner;
    private Texture player2Winner;
    // Instance variables for the back button
    private Texture picToMenuButton;
    private Rectangle backToMenuButton;
    // Instance variable for the winning car
    private int winnerCar;
    // Instance variable for the winning player
    private int winnerPlayer;
    // Instance variables for the cars
    private Texture lambo;
    private Texture acura;
    private Texture lambo2;
    private Texture bentley;
    private Texture Rect;
    private StateManager sm;

    /**
     * Constructor for the winner state
     *
     * @param sm
     * @param winnerCar
     * @param winnerPlayer
     */
    public WinnerState(StateManager sm, int winnerCar, int winnerPlayer) {
        // Call the state manager from the super class
        super(sm);
        this.sm = sm;
        this.sm.end();
        // Setting the instance varaibles to be correct
        this.winnerCar = winnerCar;
        this.winnerPlayer = winnerPlayer;
        // Creating the player 1 and 2 winner picture
        player1Winner = new Texture("WinnerPlayer1.jpg");
        player2Winner = new Texture("WinnerPlayer2.jpg");
        // Creating the rectangle (picture) for the back button
        picToMenuButton = new Texture("blackrectangle1.png");
        // Creating the rectangle for the back button
        backToMenuButton = new Rectangle(734, 35, 233, 62);
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
        // Set the camera properly       
        batch.setProjectionMatrix(getCombinedCamera());
        // Set the camera properly
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Begin the drawings
        batch.begin();
        // Set viewport for RIGHT side,leave a space of 2 pixels to separte the frames 
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Draw the "to menu" option
        batch.draw(picToMenuButton, backToMenuButton.x, backToMenuButton.y, backToMenuButton.width, backToMenuButton.height);
        // If player 1 wins, print the player one jpeg
        if (winnerPlayer == 1) {
            batch.draw(player1Winner, 0, 0, getViewWidth(), getViewHeight());
        } else {
            // If not print the player two jpeg
            batch.draw(player2Winner, 0, 0, getViewWidth(), getViewHeight());
        }
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
        // Get the mouse click/touch position
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // Convert the point to game coordinates
        unproject(touch);

        if (Gdx.input.justTouched()) {
            // If the to menu button is clicked on, then the state changes to the menu state
            if (touch.x >= backToMenuButton.x && touch.x <= backToMenuButton.x + backToMenuButton.width
                    && touch.y >= backToMenuButton.y && touch.y <= backToMenuButton.y + backToMenuButton.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change state to MenuState
                gsm.push(new MenuState(gsm, 2));
            }
        }
    }

    /**
     * Method that disposes the images that were used
     */
    @Override
    public void dispose() {
        // Dispose all the images used within this state
        player1Winner.dispose();
        player2Winner.dispose();
        picToMenuButton.dispose();
        lambo.dispose();
        acura.dispose();
        lambo2.dispose();
        bentley.dispose();
        Rect.dispose();
    }
}
