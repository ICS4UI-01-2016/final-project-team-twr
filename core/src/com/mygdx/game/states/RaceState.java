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
        car.update(deltaTime);
    }

    @Override
    public void handleInput() {
        float xCoordinateSpeed = 0;
        float yCoordinateSpeed = 0;
        float degreeTurned = 0;
        
        if(car.getRotation() >= 0 && car.getRotation() <= 90){
            float rotation = car.getRotation();
            xCoordinateSpeed = 0 - (rotation / 9);
            yCoordinateSpeed = 10 - (rotation / 9); 
        } else if (car.getRotation() >= 90 && car.getRotation() <= 180){
            float rotation = car.getRotation() - 90;
            xCoordinateSpeed = -10 + (rotation / 9);
            yCoordinateSpeed = 0 - (rotation / 9); 
        } else if (car.getRotation() >= 180 && car.getRotation() <= 270){
            float rotation = car.getRotation() - 180;
            xCoordinateSpeed = 0 + (rotation / 9);
            yCoordinateSpeed = -10 + (rotation / 9); 
        } else if (car.getRotation() >= 270 && car.getRotation() <= 360){
            float rotation = car.getRotation() - 270;
            xCoordinateSpeed = 10 - (rotation / 9);
            yCoordinateSpeed = 0 + (rotation / 9); 
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            degreeTurned = 4;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            degreeTurned = -4;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){        
            car.drive(xCoordinateSpeed, yCoordinateSpeed);
            car.turn(degreeTurned);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            xCoordinateSpeed = xCoordinateSpeed * -1;
            yCoordinateSpeed = yCoordinateSpeed * -1;
            degreeTurned = degreeTurned * -1;
            car.drive(xCoordinateSpeed, yCoordinateSpeed);
            car.turn(degreeTurned);
        }
    }
        
    @Override
    public void dispose() {
        car.dispose();
    }
}
