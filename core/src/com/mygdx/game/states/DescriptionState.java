/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
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
    private Texture description;
    private Rectangle backButtonRectangle;
    private Texture button;
    private Music music;

    public DescriptionState(StateManager sm) {
        super(sm);
        description = new Texture("Description.jpg");
        // backButtonRectangle = new Rectangle(10, 10, 200, 50);
        button = new Texture("blackrectangle.png");
        backButtonRectangle = new Rectangle(-22, 15, 265, 54);
        // Adding music to the state 
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(button, backButtonRectangle.x, backButtonRectangle.y, backButtonRectangle.width, backButtonRectangle.height);
        batch.draw(description, 0, 0, getViewWidth(), getViewHeight());
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            // Get the mouse click/touch position of the user
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert the point to game coordinates
            unproject(touch);
            // If the "Back" button is touched, then go back to DescriptionState
            if (touch.x >= backButtonRectangle.x && touch.x <= backButtonRectangle.x + backButtonRectangle.width
                    && touch.y >= backButtonRectangle.y && touch.y <= backButtonRectangle.y + backButtonRectangle.height) {
                StateManager gsm = getStateManager();
                // Change state to MenuState
                gsm.push(new MenuState(gsm));
                // Stop the music
                music.stop();
            }
        }
    }

    @Override
    public void dispose() {
        description.dispose();
        button.dispose();
    }
}
