/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Map;
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
    private Texture Track1;
    private Texture Track2;
    private Texture Track3;
    private Texture button;

    /**
     * Constructor for the pick track screen
     *
     * @param sm the state manager
     */
    public PickTrackState(StateManager sm) {
        super(sm);
        PickTrackBackground = new Texture("");
        Track1 = new Texture("Track1.jpg");
        Track2 = new Texture("Track2.jpg");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(Track1, 20, 20, 400, 400);
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
        // Button location
        float buttonX = getViewWidth() / 2 - button.getWidth() / 2;
        float buttonY = getViewHeight() / 2;
    }

    @Override
    public void dispose() {
    }
}
