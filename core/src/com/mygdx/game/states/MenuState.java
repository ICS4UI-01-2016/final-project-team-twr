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
 * @author whitb0039, richj0985, and tatad6701
 */
public class MenuState extends State {

    // Create the instance variables 
    private StateManager sm;
    // Instance variable for the background image
    private Texture bg;
    // Description option instance variables
    private Texture picOfDescriptionButton;
    private Rectangle descriptionButton;
    // How To Play option instance variables
    private Texture picOfHowToPlayButton;
    private Rectangle howToPlayButton;
    // Play option instance variables
    private Texture picOfPlayButton;
    private Rectangle playButton;

    /**
     *
     * @param sm the state manager
     * @param loop the loop that the game is on for the music to play correctly
     */
    public MenuState(StateManager sm, int loop) {
        // Call the state manager from the super class
        super(sm);
        // Bringing in the state manager
        this.sm = sm;

        // If it is the first loop through then start the music
        if (loop == 1) {
            sm.startMusic();

            // if it is starting over from winner state then play continue music
        } else if (loop == 2) {
            sm.continueMusic();
        }
        // Create the background texture
        bg = new Texture("MenuState.jpg");
        // Description Button instance variables
        picOfDescriptionButton = new Texture("blackrectangle1.png");
        // Create the rectangle behind the description button option 
        descriptionButton = new Rectangle(721, 409, 175, 50);
        // Create the picture of the black rectangle 
        picOfHowToPlayButton = new Texture("blackrectangle1.png");
        // Create the rectangle button for the how to play option
        howToPlayButton = new Rectangle(110, 409, 175, 50);
        // Create the picture of the rectangle button for the play option
        picOfPlayButton = new Texture("blackrectangle1.png");
        // Create the rectangle of the play button
        playButton = new Rectangle(419, 409, 176, 50);
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
        // Beginning the drawings
        batch.begin();
        // Drawing the rectangle behind the Play option
        batch.draw(picOfPlayButton, playButton.x, playButton.y, playButton.width, playButton.height);
        // Drawing the rectangle behind the How To Play option
        batch.draw(picOfHowToPlayButton, howToPlayButton.x, howToPlayButton.y, howToPlayButton.width, howToPlayButton.height);
        // Drawing the rectangle behind the description option
        batch.draw(picOfDescriptionButton, descriptionButton.x, descriptionButton.y, descriptionButton.width, descriptionButton.height);
        // Drawing the background image
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
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
            // If the "Description" button is clicked on, change the screen to description screen
            if (touch.x >= descriptionButton.x && touch.x <= descriptionButton.x + descriptionButton.width
                    && touch.y >= descriptionButton.y && touch.y <= descriptionButton.y + descriptionButton.height) {
                // Call the state manager in order to change states!               
                StateManager gsm = getStateManager();
                // Change the state to DescriptionState
                gsm.push(new DescriptionState(gsm));
            }

            // If the "How To Play" button is clicked on, change the screen to How To Play screen
            if (touch.x >= howToPlayButton.x && touch.x <= howToPlayButton.x + howToPlayButton.width
                    && touch.y >= howToPlayButton.y && touch.y <= howToPlayButton.y + howToPlayButton.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change the state to HowToPlayState
                gsm.push(new HowToPlayState(gsm));
            }

            // If the "Play" button is clicked on, change the screen to Choose Player Amount screen
            if (touch.x >= playButton.x && touch.x <= playButton.x + playButton.width
                    && touch.y >= playButton.y && touch.y <= playButton.y + playButton.height) {
                // Call the state manager in order to change states!
                StateManager gsm = getStateManager();
                // Change the state to ChooseAmountPlayersState
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
        bg.dispose();
        picOfDescriptionButton.dispose();
        picOfHowToPlayButton.dispose();
        picOfPlayButton.dispose();
    }
}
