package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.DescriptionState;
import com.mygdx.game.states.HowToPlayState;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.PickTrackState;
import com.mygdx.game.states.RaceState;
import com.mygdx.game.states.State;
import com.mygdx.game.states.StateManager;
import com.mygdx.game.states.WinnerState;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class RaceIt extends ApplicationAdapter {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 900;
    private SpriteBatch batch; // to draw stuffs
    private StateManager stateManager; // look after the different states
    private Music song;

    // Initial Setup
    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1, 1, 1, 1); // colour to clear the screen with

        stateManager = new StateManager();
        State firstScreen = new MenuState(stateManager, 1);
//            State firstScreen = new RaceState(stateManager);

        
        stateManager.push(firstScreen); // load the first screen
    }

    // Game Loop
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // handle input
        stateManager.handleInput();
        // update the game states
        stateManager.update(Gdx.graphics.getDeltaTime());
        // draw the screen
        stateManager.render(batch);
    }

    // End Section
    @Override
    public void dispose() {
        batch.dispose();
    }
}
