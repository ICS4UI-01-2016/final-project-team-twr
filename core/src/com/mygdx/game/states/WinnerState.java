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
public class WinnerState extends State{

    // Create instance variables
    private Texture winnerPic;
    // Creating the instance variables for the back button
    private Texture picBackButton;
    private Rectangle backButton;
    private int winner;

    WinnerState (StateManager sm, int winner) {
        super(sm);
        this.winner = winner;
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Creating the background
        winnerPic = new Texture("bg.jpg");
        // Creating the rectangle (picture) for the back button
        picBackButton = new Texture("blackrectangle1.png");
        // Creating the rectangle for the back button
        backButton = new Rectangle(40, 23, 218, 62);
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
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        // End the batch to allow the HUD to be displated and use ShaperRenderr
        batch.end();
        
        // Begin to draw
        batch.begin();
        // Draw the back button
        batch.draw(picBackButton, backButton.x, backButton.y, backButton.width, backButton.height);
        // Draw the background 
        batch.draw(winnerPic, 0, 0, getViewWidth(), getViewHeight());
        // End the drawing
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
            if (touch.x >= backButton.x && touch.x <= backButton.x + backButton.width
                    && touch.y >= backButton.y && touch.y <= backButton.y + backButton.height) {
                StateManager gsm = getStateManager();
                // Change state to MenuState
                gsm.push(new MenuState(gsm));
            }

        }
    }

    @Override
    public void dispose() {
        // Dispose the used images
        picBackButton.dispose();
        winnerPic.dispose();
    }
    
}
