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
    private final Car car1;
    private final Car car2;
    private Texture bg;
    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private float velocity;
    private float speedX;
    private float speedY;
    private int track = 1;
    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        if(track == 1){
            car1 = new Car(600, 400, 1, 270, 207, RaceIt.HEIGHT - 190);
            car2 = new Car(600, 400, 3, 270, 160, RaceIt.HEIGHT - 145);
            bg = new Texture("Track1.jpg");
        } else{
            car1 = new Car(0, 0, 0, 0, 0, 0);
            car2 = new Car(0, 0, 0, 0, 0, 0);
        }
    }

    // Comment this!
    public void render(SpriteBatch batch) {
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        car1.render(batch);
        car2.render(batch);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        car1.update(deltaTime);
        car2.update(deltaTime);
        
        if(!car1.crashed() && !car2.crashed()){
            car1.collides(car2);
            car2.collides(car1);
        }
            
    }

    @Override
    public void handleInput() {
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            car1.acceleratorPedal(true);
        } else{
            car1.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            car1.turnLeft(true);
        }else{
            car1.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            car1.turnRight(true);
        }else{
            car1.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            car1.brakePedal(true);
        } else{
            car1.brakePedal(false);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            car2.acceleratorPedal(true);
        } else{
            car2.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            car2.turnLeft(true);
        }else{
            car2.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            car2.turnRight(true);
        }else{
            car2.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            car2.brakePedal(true);
        } else{
            car2.brakePedal(false);
        }
    }
        
    @Override
    public void dispose() {
        car1.dispose();
        car2.dispose();
    }
}
