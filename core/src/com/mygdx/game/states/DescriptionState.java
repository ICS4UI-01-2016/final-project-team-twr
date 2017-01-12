/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
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
    private Rectangle backButton;
    //
    private Texture Extra1;
    private Texture Extra2;

    public DescriptionState(StateManager sm) {
        super(sm);
        description = new Texture("Description.jpg");
        Extra1 = new Texture("Track1.jpg");
        Extra2 = new Texture("Track2.jpg");
        backButton = new Rectangle(100, 350, 200, 200);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
    }
 
    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(description, 55, 350, 200, 200);
        batch.draw(Extra1, 350, 350, 200, 200);
        batch.draw(Extra2, 650, 350, 200, 200);
        batch.end();
        batch.begin();
        batch.setColor(Color.BLACK);
        backButton.setPosition(10, 10);
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
        // If the "Back" button is touched, then go back to menustate
        float buttonX = getViewWidth() / 2 - backButton.getWidth() / 2;
        float buttonY = getViewHeight() / 2;
//        if(float.x > buttonX && touch.x < buttonX + backButton.getWidth() && touch.y > buttonY && touch.y < buttonY + backButton.getHeight()){
//            StateManager gsm = getStateManager();
//            gsm.push(new MenuState(gsm));
//        }
    }

    @Override
    public void dispose() {
        description.dispose();
    }

}
