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
    private Texture player1Winner;
    private Texture player2Winner;
    // Creating the instance variables for the back button
    private Texture picToMenuButton;
    private Rectangle backToMenuButton;
    private int winnerCar;
    private int winnerPlayer;
    // Instance variables for the cars
    private Texture lambo;
    private Texture acura;
    private Texture lambo2;
    private Texture bentley;
    private Texture Rect;

    public WinnerState(StateManager sm, int winnerCar, int winnerPlayer) {
        super(sm);
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

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());

        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        //
        // Render the view for Car1
        //
        batch.begin();
        // set viewport for RIGHT side,leave a space of 2 pixels to separte the frames 
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Draw the
        batch.draw(picToMenuButton, backToMenuButton.x, backToMenuButton.y, backToMenuButton.width, backToMenuButton.height);
        // If player 1 wins, print the player one jpeg
        if (winnerPlayer == 1) {
            batch.draw(player1Winner, 0, 0, getViewWidth(), getViewHeight());
            //batch.draw()
        } else {
            // If not print the player two jpeg
            batch.draw(player2Winner, 0, 0, getViewWidth(), getViewHeight());
        }

        // End the batch to allow the HUD to be displated and use ShaperRenderr
        batch.end();

    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        unproject(touch);
        if (Gdx.input.justTouched()) {
            // If the back button is clicked on, then the state changes to the manu state
            if (touch.x >= backToMenuButton.x && touch.x <= backToMenuButton.x + backToMenuButton.width
                    && touch.y >= backToMenuButton.y && touch.y <= backToMenuButton.y + backToMenuButton.height) {
                StateManager gsm = getStateManager();
                // Change state to MenuState
                gsm.push(new MenuState(gsm));
            }


        }
    }

    @Override
    public void dispose() {
        player1Winner.dispose();
        player2Winner.dispose();
        // Dispose the used images
        picToMenuButton.dispose();
        lambo.dispose();
        acura.dispose();
        lambo2.dispose();
        bentley.dispose();
        Rect.dispose();
    }
}
