/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map;
import java.util.ArrayList;

/**
 *
 * @author tatad6701
 */
public class PickTrackState extends State {

    private ArrayList<Map> maps;

    /**
     * Constructor for the pick track
     *
     * @param sm the state manager
     */
    public PickTrackState(StateManager sm) {
        super(sm);
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void dispose() {
    }
}
