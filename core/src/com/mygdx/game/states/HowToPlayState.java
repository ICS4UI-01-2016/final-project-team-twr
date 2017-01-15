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
public class HowToPlayState extends State {

    // Create instance variables
    private Texture howToPlay;
    // Creating the instance variables for the back button
    private Texture picOfHowToPlayButton;
    private Rectangle howToPlayButton;

    HowToPlayState(StateManager sm) {
        super(sm);
        howToPlay = new Texture("HowToPlayState.jpg");
        picOfHowToPlayButton = new Texture("blackrectangle.png");
        howToPlayButton = new Rectangle(-7, 25, 311, 59);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(picOfHowToPlayButton, howToPlayButton.x, howToPlayButton.y, howToPlayButton.width, howToPlayButton.height);
        batch.draw(howToPlay, 0, 0, getViewWidth(), getViewHeight());
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
            if (touch.x >= howToPlayButton.x && touch.x <= howToPlayButton.x + howToPlayButton.width
                    && touch.y >= howToPlayButton.y && touch.y <= howToPlayButton.y + howToPlayButton.height) {
                StateManager gsm = getStateManager();
                gsm.push(new MenuState(gsm));
            }

        }
    }

    @Override
    public void dispose() {
        howToPlay.dispose();
    }

}
