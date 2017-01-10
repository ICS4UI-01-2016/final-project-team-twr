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
    private final Car acura;
    private final Car lambo2;
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
        acura = new Car(600, 400, 2);
        lambo2 = new Car(600, 400, 3);
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
        acura.render(batch);
        lambo2.render(batch);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        lambo.update(deltaTime);
        acura.update(deltaTime);
        lambo2.update(deltaTime);
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
            lambo2.brakePedal(true);
        } else{
            lambo2.brakePedal(false);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            lambo2.acceleratorPedal(true);
        } else{
            lambo2.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            lambo2.turnLeft(true);
        }else{
            lambo2.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            lambo2.turnRight(true);
        }else{
            lambo2.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            lambo2.brakePedal(true);
        } else{
            lambo2.brakePedal(false);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_5)){
            lambo2.acceleratorPedal(true);
        } else{
            lambo2.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            lambo2.turnLeft(true);
        }else{
            lambo2.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            lambo2.turnRight(true);
        }else{
            lambo2.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            lambo2.brakePedal(true);
        } else{
            lambo2.brakePedal(false);
        }
    }
        
    @Override
    public void dispose() {
        lambo.dispose();
        acura.dispose();
        lambo2.dispose();
    }
}
