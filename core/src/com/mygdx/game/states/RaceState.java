/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Car;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class RaceState extends State {

    // Create the constant variables
    private final Car lambo;
    private Texture bg;
    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private float velocity;
    private float speedX;
    private float speedY;
    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        lambo = new Car(600, 400, 1);
        bg = new Texture("Track1.jpg");
    }

    // Comment this!
    public void render(SpriteBatch batch) {
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        lambo.render(batch);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        lambo.update(deltaTime);
    }

    @Override
    public void handleInput() {
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            lambo.acceleratorPedal(true);
        } else{
            lambo.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            lambo.turnLeft(true);
        }else{
            lambo.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            lambo.turnRight(true);
        }else{
            lambo.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            lambo.brakePedal(true);
        } else{
            lambo.brakePedal(false);
        }
    }
        
    @Override
    public void dispose() {
        lambo.dispose();
    }
}
