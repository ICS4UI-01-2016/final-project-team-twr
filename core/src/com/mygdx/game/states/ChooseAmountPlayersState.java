/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class ChooseAmountPlayersState extends State {

    private StateManager sm;
    private Texture bg;
    private Texture next;

    public ChooseAmountPlayersState(StateManager sm) {

        super(sm);
        bg = new Texture("MenuScreen.jpg");
        next = new Texture("NEXT.png");
        
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.draw(next, 0, -100,  100, 300);
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            
        }

    }

    @Override
    public void dispose() {
        bg.dispose();
        next.dispose();
    }

}
