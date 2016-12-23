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
 * @author whitb0039, richj0985, and tatad6701
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
            int turningSpeed = 0;
            int forwardSpeed = 0;            
            
            // Q1
            if(car.getRotation() == 0){
                turningSpeed = 0;
                forwardSpeed = 12;
            }else if(car.getRotation() > 0 && car.getRotation() <= 18){
                turningSpeed = -2;
                forwardSpeed = 8;
            } else if(car.getRotation() > 18 && car.getRotation() <= 36){
                turningSpeed = -4;
                forwardSpeed = 6;
            } else if(car.getRotation() > 36 && car.getRotation() <= 54){
                turningSpeed = -6;
                forwardSpeed = 4;
            } else if(car.getRotation() > 54 && car.getRotation() <= 72){
                turningSpeed = -8;
                forwardSpeed = 2;
            } else if(car.getRotation() > 72 && car.getRotation() < 90){
                turningSpeed = -10;
                forwardSpeed = 0;
            }
            
            // Q2
            if(car.getRotation() == 90){
                turningSpeed = -12;
                forwardSpeed = 0;
            }else if(car.getRotation() > 90 && car.getRotation() <= 108){
                turningSpeed = -10;
                forwardSpeed = -2;
            } else if(car.getRotation() > 108 && car.getRotation() <= 126){
                turningSpeed = -8;
                forwardSpeed = -4;
            } else if(car.getRotation() > 126 && car.getRotation() <= 144){
                turningSpeed = -6;
                forwardSpeed = -6;
            } else if(car.getRotation() > 144 && car.getRotation() <= 162){
                turningSpeed = -4;
                forwardSpeed = -8;
            } else if(car.getRotation() > 162 && car.getRotation() <= 180){
                turningSpeed = -2;
                forwardSpeed = -10;
            }
            
            // Q3
            if(car.getRotation() == 180){
                turningSpeed = 0;
                forwardSpeed = -12;
            }else if(car.getRotation() > 180 && car.getRotation() <= 198){
                turningSpeed = 2;
                forwardSpeed = -8;
            } else if(car.getRotation() > 198 && car.getRotation() <= 216){
                turningSpeed = 4;
                forwardSpeed = -6;
            } else if(car.getRotation() > 216 && car.getRotation() <= 234){
                turningSpeed = 6;
                forwardSpeed = -4;
            } else if(car.getRotation() > 234 && car.getRotation() <= 252){
                turningSpeed = 8;
                forwardSpeed = -2;
            } else if(car.getRotation() > 252 && car.getRotation() < 270){
                turningSpeed = 10;
                forwardSpeed = 0;
            }
            
            // Q4
            if(car.getRotation() == 270){
                turningSpeed = 12;
                forwardSpeed = 0;
            }else if(car.getRotation() > 270 && car.getRotation() <= 288){
                turningSpeed = 10;
                forwardSpeed = 2;
            } else if(car.getRotation() > 288 && car.getRotation() <= 306){
                turningSpeed = 8;
                forwardSpeed = 4;
            } else if(car.getRotation() > 306 && car.getRotation() <= 324){
                turningSpeed = 6;
                forwardSpeed = 6;
            } else if(car.getRotation() > 324 && car.getRotation() <= 342){
                turningSpeed = 4;
                forwardSpeed = 8;
            } else if(car.getRotation() > 342 && car.getRotation() <= 360){
                turningSpeed = 2;
                forwardSpeed = 10;
            }
            
            System.out.println(turningSpeed);
            System.out.println(forwardSpeed);
            System.out.println(car.getRotation());
            
                car.moveHorizontal(turningSpeed);
                car.moveVertical(forwardSpeed);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            car.moveVertical(0);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            car.rotate(4);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            car.rotate(-4);
        }
    }

    @Override
    public void dispose() {
        car.dispose();
    }
}
