/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author tatad6701
 */
public class ChooseAmountPlayersState extends State {

    private StateManager sm;
    private Texture bg;
    private Texture next;

    public ChooseAmountPlayersState(StateManager sm) {

        super(sm);
        bg = new Texture("MenuScreen.jpg");

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

    }

    @Override
    public void dispose() {

    }

}
