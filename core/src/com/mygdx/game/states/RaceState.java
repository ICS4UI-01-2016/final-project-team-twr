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
    private Car[] cars;
    private final Car car;
    private Texture bg;
    private boolean acceleration;
    private Vector3 accelerate;

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
        accelerate = new Vector3(-1,0,0);
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
        float xCoordinateSpeed = 0;
        float yCoordinateSpeed = 0;
        
        if(acceleration){
            if(car.getRotation() >= 0 && car.getRotation() <= 90){
                float rotation = car.getRotation();
                xCoordinateSpeed = 0 - (rotation / 18);
                yCoordinateSpeed = 5 - (rotation / 18); 
            } else if (car.getRotation() >= 90 && car.getRotation() <= 180){
                float rotation = car.getRotation() - 90;
                xCoordinateSpeed = -5 + (rotation / 18);
                yCoordinateSpeed = 0 - (rotation / 18); 
            } else if (car.getRotation() >= 180 && car.getRotation() <= 270){
                float rotation = car.getRotation() - 180;
                xCoordinateSpeed = 0 + (rotation / 18);
                yCoordinateSpeed = -5 + (rotation / 18); 
            } else if (car.getRotation() >= 270 && car.getRotation() <= 360){
                float rotation = car.getRotation() - 270;
                xCoordinateSpeed = 5 - (rotation / 18);
                yCoordinateSpeed = 0 + (rotation / 18); 
            }
            car.drive(xCoordinateSpeed, yCoordinateSpeed);
        } else{
//            deceleration.y += 1;
//            deceleration.scl(deltaTime);
//            position.add(deceleration);
//            deceleration.scl(1/deltaTime);
        }
    }

    @Override
    public void handleInput() {
        float degreeTurned = 0;
        
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            degreeTurned = 3;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            degreeTurned = -3;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            acceleration = true;
            car.turn(degreeTurned);
        } else{
            acceleration = false;
        
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            xCoordinateSpeed = xCoordinateSpeed * -1;
//            yCoordinateSpeed = yCoordinateSpeed * -1;
//            degreeTurned = degreeTurned * -1;
//            car.drive(xCoordinateSpeed, yCoordinateSpeed);
//            car.turn(degreeTurned);
        }
    }
        
    @Override
    public void dispose() {
        car.dispose();
    }
}
