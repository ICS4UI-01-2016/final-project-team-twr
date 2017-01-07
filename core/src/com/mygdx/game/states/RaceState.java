/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture bg;

    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        car = new Car(600, 400);
        bg = new Texture("Track1.jpg");
    }

    // Comment this!
    public void render(SpriteBatch batch) {
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
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
            xCoordinateSpeed = 0 - (rotation / 15);
            yCoordinateSpeed = 6 - (rotation / 15); 
        } else if (car.getRotation() >= 90 && car.getRotation() <= 180){
            float rotation = car.getRotation() - 90;
            xCoordinateSpeed = -6 + (rotation / 15);
            yCoordinateSpeed = 0 - (rotation / 15); 
        } else if (car.getRotation() >= 180 && car.getRotation() <= 270){
            float rotation = car.getRotation() - 180;
            xCoordinateSpeed = 0 + (rotation / 15);
            yCoordinateSpeed = -6 + (rotation / 15); 
        } else if (car.getRotation() >= 270 && car.getRotation() <= 360){
            float rotation = car.getRotation() - 270;
            xCoordinateSpeed = 6 - (rotation / 15);
            yCoordinateSpeed = 0 + (rotation / 15); 
        }
        
        xCoordinateSpeed = xCoordinateSpeed;
        yCoordinateSpeed = yCoordinateSpeed;
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            degreeTurned = 2;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            degreeTurned = -2;
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
