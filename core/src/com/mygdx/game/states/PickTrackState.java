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
import com.mygdx.game.Map;
import com.mygdx.game.RaceIt;
import java.util.ArrayList;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class PickTrackState extends State {

    private ArrayList<Map> maps;
    // Create constant variables
    private StateManager sm;
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

    /**
     * Constructor for the pick track screen for the players
     *
     * @param sm the state manager
     */
    public PickTrackState(StateManager sm) {
        super(sm);
        PickTrackBackground = new Texture("PickTrackState.jpg");
        // Buttons for Track1
        picOfTrack1 = new Rectangle(95, 305, 373, 223);
        track1Button = new Texture("blackrectangle.png");
        // Buttons for Track2
        picOfTrack2 = new Rectangle(535, 305, 373, 223);
        track2Button = new Texture("blackrectangle.png");
        // Placing the back button
        picBackButton = new Texture("blackrectangle.png");
        backButton = new Rectangle(21, 30, 215, 50);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        // Drawing the back button rectangle
        batch.draw(picBackButton, backButton.x, backButton.y, backButton.width, backButton.height);
        // Drawing the track 1 button rectangle
        batch.draw(track1Button, picOfTrack1.x, picOfTrack1.y, picOfTrack1.width, picOfTrack1.height);
        // Drawing the track 1 button rectangle
        batch.draw(track2Button, picOfTrack2.x, picOfTrack2.y, picOfTrack2.width, picOfTrack2.height);
        batch.draw(PickTrackBackground, 0, 0, getViewWidth(), getViewHeight());

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
            // If track 1 was clicked on, then change the screen to race state but track 1
            if (touch.x >= picOfTrack1.x && touch.x <= picOfTrack1.x + picOfTrack1.width
                    && touch.y >= picOfTrack1.y && touch.y <= picOfTrack1.y + picOfTrack1.height) {
                StateManager gsm = getStateManager();
                // Change the state to RaceState
                gsm.push(new RaceState(gsm, 1));
            }

            // If track 2 was clicked on, then change the screen to race state but track 2
            if (touch.x >= picOfTrack2.x && touch.y <= picOfTrack2.x + picOfTrack2.width
                    && touch.y >= picOfTrack2.y && touch.y <= picOfTrack2.y + picOfTrack2.height) {
                StateManager gsm = getStateManager();
                // Change the state to RaceState
                gsm.push(new RaceState(gsm, 2));
            }

            // If the back button was clicked on, then change the screen to car choice state
            if (touch.x >= backButton.x && touch.y <= backButton.x + backButton.width
                    && touch.y >= backButton.y && touch.y <= backButton.y + backButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to CarChoiceState
                gsm.push(new CarChoiceState(gsm));
            }
        }
    }

    @Override
    public void dispose() {
        PickTrackBackground.dispose();
        track1Button.dispose();
        track2Button.dispose();
    }
}
