/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class MenuState extends State {
    // Create the instance variables 

    private StateManager sm;
    private Texture bg;

    public MenuState(StateManager sm) {
        super(sm);
        bg = new Texture("bg.jpg");
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            StateManager sm = getStateManager();
            sm.push(new ChooseAmountPlayersState(sm));
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
