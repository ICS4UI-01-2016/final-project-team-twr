/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class Car {
    private float rotation;
    private Vector3 origin;
    private Vector3 position;
    private TextureRegion carPic;
    private Rectangle bounds;
    private float velocity;
    private float speedX;
    private float speedY;
    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private int tempCarType;
    
    public Car(int x, int y, int carType){
        position = new Vector3(x,y,0);
        if(carType == 1){
            carPic = new TextureRegion(new Texture("lamborghiniblack.png"));
        } else if(carType == 2){
            carPic = new TextureRegion(new Texture("acura.png"));
        } else if(carType == 3){
            carPic = new TextureRegion(new Texture("lamborghini2.png"));
        } else if(carType == 4){
            carPic = new TextureRegion(new Texture("Bentley2.png"));
        }
        tempCarType = carType;
        bounds = new Rectangle(position.x,position.y, carPic.getRegionWidth(), carPic.getRegionHeight());
    }
    
    public void update(float deltaTime){
        while(rotation > 360 ){
            rotation = rotation - 360;
        }
        
        while(rotation < 0){
            rotation = rotation + 360;
        }
        if(accelerate){
            velocity = velocity + 0.05f;
            if(velocity > 1.2f){
                velocity = 1.2f;
            }
        } else{
            velocity = velocity - 0.025f;
            if(velocity < 0){
                velocity = 0;
            }
        }
        
        if(stop){
            velocity = velocity - 0.025f * 2;
            if(velocity < 0){
                velocity = 0;
            }
        }
        
        if(velocity > 0){
            if(rotation >= 0 && rotation <= 90){
                float tempRotation = rotation;
                speedX = (0.0f - (tempRotation / 18.0f)) * velocity;
                speedY = (5.0f - (tempRotation / 18.0f)) * velocity; 
            } else if (rotation >= 90 && rotation <= 180){
                float tempRotation = rotation - 90;
                speedX = (-5.0f + (tempRotation / 18.0f)) * velocity;
                speedY = (0.0f - (tempRotation / 18.0f)) * velocity; 
            } else if (rotation >= 180 && rotation <= 270){
                float tempRotation = rotation - 180;
                speedX = (0.0f + (tempRotation / 18.0f)) * velocity;
                speedY = (-5.0f + (tempRotation / 18.0f)) * velocity; 
            } else if (rotation >= 270 && rotation <= 360){
                float tempRotation = rotation - 270;
                speedX = (5.0f - (tempRotation / 18.0f)) * velocity;
                speedY = (0.0f + (tempRotation / 18.0f)) * velocity; 
            }
            position.x += speedX;
            position.y += speedY;
        }
        
        // only allow the car to turn when in motion
        if ( velocity > 0 ) {
            if(turnLeft){
                rotation += 4;
            }

            if(turnRight){
                rotation -= 4;
            }
        }
        bounds.setPosition(position.x, position.y);
    }
    
    public void render(SpriteBatch batch){
        if(tempCarType == 1){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 14, carPic.getRegionHeight() / 14, carPic.getRegionWidth() / 7, carPic.getRegionHeight() / 7, 1, 1, rotation);
        } else if(tempCarType == 2){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 14, carPic.getRegionHeight() / 14, carPic.getRegionWidth() / 7, carPic.getRegionHeight() / 7, 1, 1, rotation);
        } else if(tempCarType == 3){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 22, carPic.getRegionHeight() / 22, carPic.getRegionWidth() / 11, carPic.getRegionHeight() / 11, 1, 1, rotation);
        } else if (tempCarType == 4){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 14, carPic.getRegionHeight() / 14, carPic.getRegionWidth() / 7, carPic.getRegionHeight() / 7, 1, 1, rotation);
        }
    }
    
    public float getX(){
        return position.x;
    }
    
    public float getY(){
        return position.y;
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    public void dispose(){
        carPic.getTexture().dispose();
    }
    
    public void acceleratorPedal(boolean accelerating){
        accelerate = accelerating;
    }
    
    public void brakePedal(boolean braking){
        stop = braking;
    }
    
    public void turnLeft(boolean turningLeft){
        turnLeft = turningLeft;
    }
    
    public void turnRight(boolean turningRight){
        turnRight = turningRight;
    }
}
