/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Car;
import com.mygdx.game.RaceIt;

/**
 *
 * @author tatad6701
 */
public class RaceState extends State {

    // Create the constant variables
    private Car[] cars;
    private final Car car;

    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        car = new Car(600, 400);
    }

    // Comment this!
    public void render(SpriteBatch batch) {
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();
        car.render(batch);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
//        car.update(deltaTime);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            
        }
    }

    @Override
    public void dispose() {
        car.dispose();
    }
}
