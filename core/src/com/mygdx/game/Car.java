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
    private TextureRegion blackBox;
    private TextureRegion blackBox2;
    private TextureRegion blackBox3;
    private TextureRegion blackBox4;
    private Rectangle bounds;    
    private Rectangle front;
    private Rectangle back;
    private Rectangle left;
    private Rectangle right;
    private float velocity;
    private float damageX;
    private float damageY;
    private float speedX;
    private float speedY;
    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private int tempCarType;
    private boolean frontHit;
    private boolean backHit;
    private boolean leftHit;
    private boolean rightHit;
    private boolean crash;
    
    public Car(int x, int y, int carType, float initialRotation, int initialPositionX, int initialPositionY){
        position = new Vector3(x,y,0);
        rotation = initialRotation;
        position.x = initialPositionX;
        position.y = initialPositionY;
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
        bounds = new Rectangle(position.x, position.y, carPic.getRegionWidth(), carPic.getRegionHeight());
        front = new Rectangle(position.x, position.y + 50, 50, 1);
        back = new Rectangle(position.x, position.y, 50, 1);
        right = new Rectangle(position.x + 50, position.y, 1, 50);
        left = new Rectangle(position.x, position.y, 1, 50);
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
            velocity = velocity - 0.025f * 2f;
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
                rotation += 3.5f;
            }

            if(turnRight){
                rotation -= 3.5f;
            }
        }
        
        if(crash){
            if(frontHit){
                speedX = 0;
                speedY = 0;
                crash = true;
                damageY = damageY + 0.1f;
                if(damageY == 1){
                    frontHit = false;
                    crash = false;
                    damageY = 0;
                }
                System.out.println("HERE");
            }

            if(backHit){
                speedX = 0;
                speedY = 0;
                crash = true;
                damageY = damageY - 0.1f;
                if(damageY == -1){
                    backHit = false;
                    crash = false;
                    damageY = 0;
                }
                System.out.println("HERE");
            }

            position.y = position.y + damageY;
        }
        
        if(tempCarType == 1){
            if(position.x + 40 > RaceIt.WIDTH){
                position.x = RaceIt.WIDTH - 40;
            }

            if(position.x < 10){
                position.x = 10;
            }

            if(position.y + 55 > RaceIt.HEIGHT){
                position.y = RaceIt.HEIGHT - 55;
            }

            if(position.y < - 5){
                position.y = - 5;
            }
        } else if(tempCarType == 2){
            if(position.x + 40 > RaceIt.WIDTH){
                position.x = RaceIt.WIDTH - 40;
            }

            if(position.x < 10){
                position.x = 10;
            }

            if(position.y + 55 > RaceIt.HEIGHT){
                position.y = RaceIt.HEIGHT - 55;
            }

            if(position.y < - 5){
                position.y = - 5;
            }
        } else if(tempCarType == 3){
            if(position.x + 85 > RaceIt.WIDTH){
                position.x = RaceIt.WIDTH - 85;
            }

            if(position.x < - 35){
                position.x = - 35;
            }

            if(position.y + 55 > RaceIt.HEIGHT){
                position.y = RaceIt.HEIGHT - 55;
            }

            if(position.y < - 5){
                position.y = - 5;
            }
        } else if(tempCarType == 4){
            if(position.x + 40 > RaceIt.WIDTH){
                position.x = RaceIt.WIDTH - 40;
            }

            if(position.x < 10){
                position.x = 10;
            }

            if(position.y + 55 > RaceIt.HEIGHT){
                position.y = RaceIt.HEIGHT - 55;
            }

            if(position.y < - 5){
                position.y = - 5;
            }
        }
        
        bounds.setPosition(position.x, position.y);
        front.setPosition(position.x, position.y + 50);
        back.setPosition(position.x, position.y);
        left.setPosition(position.x, position.y);
        right.setPosition(position.x + 50, position.y);
    }
    
    public void render(SpriteBatch batch){
        if(tempCarType == 1){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 14, carPic.getRegionHeight() / 14, carPic.getRegionWidth() / 7, carPic.getRegionHeight() / 7, 1, 1, rotation);
        } else if(tempCarType == 2){
            batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 18, carPic.getRegionHeight() / 18, carPic.getRegionWidth() / 9, carPic.getRegionHeight() / 9, 1, 1, rotation);
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
    
    public Rectangle rightBounds(){
        return right;
    }
    
    public Rectangle leftBounds(){
        return left;
    }
    
    public Rectangle frontBounds(){
        return front;
    }
    
    public Rectangle backBounds(){
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
    
    public void collides(Car b){
        if(front.overlaps(b.getBounds())){
            System.out.println(tempCarType + ": FRONT");
            frontHit = true;
            crash = true;
        } else{
            frontHit = false;
        }
        
        if(back.overlaps(b.getBounds())){
            System.out.println(tempCarType + ": BACK");
            backHit = true;
            crash = true;
        } else{
            backHit = false;
        }
        
        if(left.overlaps(b.getBounds())){
            System.out.println(tempCarType + ": LEFT");
            leftHit = true;
            crash = true;
        } else{
            leftHit = false;
        }
        
        if(right.overlaps(b.getBounds())){
            System.out.println(tempCarType + ": RIGHT");
            rightHit = true;
            crash = true;
        } else{
            rightHit = false;
        }
    }
    
    public boolean crashed(){
        return crash;
    }
    
    public float getSpeedX(){
        return speedX + velocity;
    }
    
    public float getSpeedY(){
        return speedY + velocity;
    }
}
