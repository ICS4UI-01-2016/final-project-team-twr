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
            int xCoordinateMove = 0;
            int yCoordinateMove = 0;   
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){        
            
            // Q1
            if(car.getRotation() >= 0 && car.getRotation() <= 15){
                xCoordinateMove = 0;
                yCoordinateMove = 12;
            }else if(car.getRotation() > 15 && car.getRotation() <= 30){
                xCoordinateMove = -2;
                yCoordinateMove = 8;
            } else if(car.getRotation() > 30 && car.getRotation() <= 45){
                xCoordinateMove = -4;
                yCoordinateMove = 6;
            } else if(car.getRotation() > 45 && car.getRotation() <= 60){
                xCoordinateMove = -6;
                yCoordinateMove = 4;
            } else if(car.getRotation() > 60 && car.getRotation() <= 75){
                xCoordinateMove = -8;
                yCoordinateMove = 2;
            } else if(car.getRotation() > 75 && car.getRotation() < 90){
                xCoordinateMove = -10;
                yCoordinateMove = 0;
            }
            
            // Q2
            if(car.getRotation() >= 90 && car.getRotation() <= 105){
                xCoordinateMove = -12;
                yCoordinateMove = 0;
            }else if(car.getRotation() > 105 && car.getRotation() <= 120){
                xCoordinateMove = -10;
                yCoordinateMove = -2;
            } else if(car.getRotation() > 120 && car.getRotation() <= 135){
                xCoordinateMove = -8;
                yCoordinateMove = -4;
            } else if(car.getRotation() > 135 && car.getRotation() <= 150){
                xCoordinateMove = -6;
                yCoordinateMove = -6;
            } else if(car.getRotation() > 150 && car.getRotation() <= 165){
                xCoordinateMove = -4;
                yCoordinateMove = -8;
            } else if(car.getRotation() > 165 && car.getRotation() <= 180){
                xCoordinateMove = -2;
                yCoordinateMove = -10;
            }
            
            // Q3
            if(car.getRotation() == 180){
                xCoordinateMove = 0;
                yCoordinateMove = -12;
            }else if(car.getRotation() > 180 && car.getRotation() <= 198){
                xCoordinateMove = 2;
                yCoordinateMove = -8;
            } else if(car.getRotation() > 198 && car.getRotation() <= 216){
                xCoordinateMove = 4;
                yCoordinateMove = -6;
            } else if(car.getRotation() > 216 && car.getRotation() <= 234){
                xCoordinateMove = 6;
                yCoordinateMove = -4;
            } else if(car.getRotation() > 234 && car.getRotation() <= 252){
                xCoordinateMove = 8;
                yCoordinateMove = -2;
            } else if(car.getRotation() > 252 && car.getRotation() < 270){
                xCoordinateMove = 10;
                yCoordinateMove = 0;
            }
            
            // Q4
            if(car.getRotation() == 270){
                xCoordinateMove = 12;
                yCoordinateMove = 0;
            }else if(car.getRotation() > 270 && car.getRotation() <= 288){
                xCoordinateMove = 10;
                yCoordinateMove = 2;
            } else if(car.getRotation() > 288 && car.getRotation() <= 306){
                xCoordinateMove = 8;
                yCoordinateMove = 4;
            } else if(car.getRotation() > 306 && car.getRotation() <= 324){
                xCoordinateMove = 6;
                yCoordinateMove = 6;
            } else if(car.getRotation() > 324 && car.getRotation() <= 342){
                xCoordinateMove = 4;
                yCoordinateMove = 8;
            } else if(car.getRotation() > 342 && car.getRotation() <= 360){
                xCoordinateMove = 2;
                yCoordinateMove = 10;
            }
            
            System.out.println(xCoordinateMove);
            System.out.println(yCoordinateMove);
            System.out.println(car.getRotation());
            
                car.moveHorizontal(xCoordinateMove);
                car.moveVertical(yCoordinateMove);
                
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
    }

    @Override
    public void dispose() {
        car.dispose();
    }
}
