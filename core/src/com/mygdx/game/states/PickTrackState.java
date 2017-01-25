/*
 * This class deals with the players being able to choose a track they wish to race on!
 Our game offers two unique tracks!
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
public class PickTrackState extends State {
    // Create constant variables

    private StateManager sm;
    // Instance variable for the background (made on photoshop)
    private Texture PickTrackBackground;
    // Track 1 option instance variables
    private Texture track1Button;
    private Rectangle picOfTrack1;
    // Track 2 option instance variables
    private Texture track2Button;
    private Rectangle picOfTrack2;
    // Back button instance variables
    private Texture picBackButton;
    private Rectangle backButton;
    // Car tpye instance variables
    private int car1Type;
    private int car2Type;

    /**
     * Constructor for the pick track state
     *
     * @param sm the state manager
     */
    public PickTrackState(StateManager sm, int car1Type, int car2Type) {
        // Call the state manager from the super class
        super(sm);
        // Creating the background image
        PickTrackBackground = new Texture("PickTrackState.jpg");
        // Creating the button for track 1
        picOfTrack1 = new Rectangle(155, 305, 250, 223);
        track1Button = new Texture("blackrectangle1.png");
        // Creating the button for track 2
        picOfTrack2 = new Rectangle(595, 305, 250, 223);
        track2Button = new Texture("blackrectangle1.png");
        // Creating the back button
        picBackButton = new Texture("blackrectangle1.png");
        backButton = new Rectangle(55, 29, 146, 50);
        // Setting instance variables correctly for car type
        this.car1Type = car1Type;
        this.car2Type = car2Type;
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
        // Start the drawings
        batch.begin();
        // Drawing the back button rectangle
        batch.draw(picBackButton, backButton.x, backButton.y, backButton.width, backButton.height);
        // Drawing the track 1 button rectangle
        batch.draw(track1Button, picOfTrack1.x, picOfTrack1.y, picOfTrack1.width, picOfTrack1.height);
        // Drawing the track 1 button rectangle
        batch.draw(track2Button, picOfTrack2.x, picOfTrack2.y, picOfTrack2.width, picOfTrack2.height);
        // Drawing the backgrounf image
        batch.draw(PickTrackBackground, 0, 0, getViewWidth(), getViewHeight());
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
        // Get the mouse click/touch position
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // Convert the point to game coordinates
        unproject(touch);

        if (Gdx.input.justTouched()) {
            // If track 1 was clicked on, then change the screen to race state but track 1
            if (touch.x >= picOfTrack1.x && touch.x <= picOfTrack1.x + picOfTrack1.width
                    && touch.y >= picOfTrack1.y && touch.y <= picOfTrack1.y + picOfTrack1.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change the state to RaceState
                gsm.push(new RaceState(gsm, 1, this.car1Type, this.car2Type));
            }

            // If track 2 was clicked on, then change the screen to race state but track 2
            if (touch.x >= picOfTrack2.x && touch.x <= picOfTrack2.x + picOfTrack2.width
                    && touch.y >= picOfTrack2.y && touch.y <= picOfTrack2.y + picOfTrack2.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change the state to RaceState
                gsm.push(new RaceState(gsm, 2, this.car1Type, this.car2Type));
            }

            // If the back button was clicked on, then change the screen to car choice state
            if (touch.x >= backButton.x && touch.x <= backButton.x + backButton.width
                    && touch.y >= backButton.y && touch.y <= backButton.y + backButton.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change the state to CarChoiceState
                gsm.push(new CarChoiceState(gsm));
            }
        }
    }

    /**
     * Method that disposes the images that were used
     */
    @Override
    public void dispose() {
        // Dispose all the images used within this state
        PickTrackBackground.dispose();
        track1Button.dispose();
        track2Button.dispose();
        picBackButton.dispose();
    }
}
