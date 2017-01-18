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
import com.mygdx.game.states.RaceState;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;

/**
 * 
 * @author whitb0039, richj0985, and tatad6701
 */
public class Car {
    private float rotation;
    private float spin;
    private Vector3 position;           // center position of the car on the track
    private CarRectCorners carCorners;  // corners bounding box of car on track
    private TextureRegion  carPic;
    private TextureRegion  carPointPic;
    private TextureRegion  checkPointPic;
    public  int            carWidth;
    public  int            carHeight;
    private int            carScale;               // scalar for the car image to track
    private int            carLap;
    private int            carCheckPoint = 0;
    
    private TextureRegion  blackBox;
    private TextureRegion  blackBox2;
    private TextureRegion  blackBox3;
    private TextureRegion  blackBox4;
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
    private boolean crash = false;
    private boolean hitCheckPoint;
    private BitmapFont font = new BitmapFont();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Circle bounds2;
    private boolean turnLeftCrash = false;
    private boolean turnRightCrash = false;
    
    public Car(int x, int y, int carType, float initialRotation, int initialPositionX, int initialPositionY){
        position = new Vector3(x,y,0);
        rotation = initialRotation;
        position.x = initialPositionX;
        position.y = initialPositionY;
        
        carPointPic = new TextureRegion(new Texture("point.png"));
        checkPointPic = new TextureRegion(new Texture("checkpoint.png"));
        
        // setup the image of the car, it's appropriate scale on the track
        // and it's bounding rectangle on the track
        if(carType == 1){
            carPic = new TextureRegion(new Texture("lamborghiniblack-new.png"));
            carScale = 7;            
        } else if(carType == 2){
            carPic = new TextureRegion(new Texture("acura-new.png"));
            carScale = 9;            
        } else if(carType == 3){
            carPic = new TextureRegion(new Texture("lamborghini2-new.png"));
            carScale = 11;            
        } else if(carType == 4){
            carPic = new TextureRegion(new Texture("Bentley2-new.png"));
            carScale = 7;            
        }
        
        // initialize the bounding rectangle for the car on track
        carWidth   = carPic.getRegionWidth() / carScale;
        carHeight  = carPic.getRegionHeight() / carScale;
        carCorners = new CarRectCorners( carWidth, carHeight );
        
        tempCarType = carType;
        bounds = new Rectangle(position.x, position.y, carWidth, carHeight);
    }

        
    public void update(float deltaTime, RaceState raceState){
                  
        // Update the coordinates of the car bounding box based on 
        // new car position.
        carCorners.updateCornerPos( position, rotation);


        // Get the track feature underneath the car
        RaceState.TrackFeature feature = raceState.getTrackFeatureAtPt( position );

        System.out.println("(" + position.x +"," + position.y +")=" 
                   + feature + "   LC(" + carCorners.backLeft.x + "," 
                   + carCorners.backLeft.x +")" );
           
        // Based on the Track feature that is under the car
        // have the car react.
        // switch( feature ) {
        if ( feature == RaceState.TrackFeature.GRASS ){
            // On grass slow the car to min slow pace
            if (velocity > .5) {
                velocity *= .25f;
            }
        }

        // ceck to see if the car has reached the finishline after
        // having passed over all of the required check points.
        if ( feature == RaceState.TrackFeature.FINISHLINE ){
            if ( carCheckPoint == 7) {
                carLap += 1;
                carCheckPoint = 0;
            }
        }

        // Check to see if I am on checkpoint
        if ( (feature.ordinal() >= RaceState.TrackFeature.CHECKPOINT1.ordinal() ) &&
             (feature.ordinal() <= RaceState.TrackFeature.CHECKPOINT8.ordinal() )) {
            int newCheckPoint = feature.ordinal() - RaceState.TrackFeature.CHECKPOINT1.ordinal() + 1;
            if ( newCheckPoint == (carCheckPoint + 1) ) {
                carCheckPoint = newCheckPoint;
                hitCheckPoint = true;
            }
        } else{
                hitCheckPoint = false;
        }   
                
        // check if the front of the car has hit a barrier
        if ( raceState.getTrackFeatureAtPt( carCorners.frontLeft) == 
                   RaceState.TrackFeature.BARRIER  
             || raceState.getTrackFeatureAtPt( carCorners.frontRight) == 
                   RaceState.TrackFeature.BARRIER ) { 
            // the car hit a barrier, bound the car of the barrier    
            velocity *= .9f;          
            spin = 1f;
        }

        // check if the car is in a spin, if it is
        // then continue to rotate the car but reduce the spin
        if ( spin > 0.1f ) {
            rotation += 60;
            spin *= 0.4f;
        } else {
            spin = 0f;
        }

        
        // If the accelerator is being pressed then increase the velocity
        // otherwise reduce the velocity to simulate the drag/friction
        // of the road and air
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
 
        // Ensure that the rotation value stays within
        // 0-360 and as soon as it goes over or under 
        // the roll it back to value within 0-360 while
        // still maintaining it's relative rotation value
        while(rotation > 360 ){
            rotation = rotation - 360;
        }
        while(rotation < 0){
            rotation = rotation + 360;
        }
        


        // If the car has velocity then update the cars postion
        // based on it's velocit and direction/rotation
        if(!crash){
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
        }

        // Update the cars rotation based on if it is being turned
        // left or right.   Only allow the car to turn
        // if it's in motion (ie has velocity)
        if ( velocity > 0 ) {
            if(!turnLeftCrash){
                if(turnLeft){
                    rotation += 4f;
                }
            }
            
            if(!turnRightCrash){
                if(turnRight){
                    rotation -= 4f;
                }
            }
        }
        
        bounds.setPosition(position.x, position.y);
//        front.setPosition(position.x, position.y + 50);
//        back.setPosition(position.x, position.y);
//        left.setPosition(position.x, position.y);
//        right.setPosition(position.x + 50, position.y);
    }

    public void renderHUD(RaceState state, SpriteBatch batch){
 
        // Draw the heads up display relative to the camera position
        // On the HUD, show the following informaiton
        //   - lap #, 
        //   - checkpoint
        //   - time in race.
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        // Enable transparency
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(state.getCameraX()-110, state.getCameraY()+175, 220, 35);
        shapeRenderer.end();
        
        // End Transparency
        Gdx.gl.glDisable(GL20.GL_BLEND);
 
        // Draw the HUD Text as separate batch from shapeRender since
        // the need to work on different batches
        batch.begin();
        font.setColor(Color.BLUE);
        String text = "Lap:" + carLap +" CheckPoint: " + carCheckPoint + " Time:  ";
        font.draw( batch, text, state.getCameraX()-100, state.getCameraY()+200);
        batch.end(); 
    }
    
    public void render(SpriteBatch batch){
        batch.draw( carPic,      position.x-carWidth/2, position.y-carHeight/2, carWidth/2, carHeight/2, carWidth, carHeight, 1, 1, rotation);
        batch.draw( carPointPic, position.x, position.y);
        
        // draw corners of the car
        batch.draw( carPointPic, carCorners.backLeftCorner.x,    carCorners.backLeftCorner.y);
        batch.draw( carPointPic, carCorners.backRightCorner.x,   carCorners.backRightCorner.y);
        batch.draw( carPointPic, carCorners.frontLeftCorner.x,   carCorners.frontLeftCorner.y);
        batch.draw( carPointPic, carCorners.frontRightCorner.x,  carCorners.frontRightCorner.y);
        
        // draw corners of the car
        batch.draw( carPointPic, carCorners.back.x,    carCorners.back.y);
        batch.draw( carPointPic, carCorners.right.x,   carCorners.right.y);
        batch.draw( carPointPic, carCorners.left.x,   carCorners.left.y);
        batch.draw( carPointPic, carCorners.front.x,  carCorners.front.y);
        
        if(hitCheckPoint){
            float cpX = position.x;
            float cpY = position.y;
            batch.draw(checkPointPic, cpX, cpY, 50, 50);
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
    
    public float getSpeedX(){
        return speedX + velocity;
    }
    
    public float getSpeedY(){
        return speedY + velocity;
    }
    
    public void checkCollision(Car opponent){
        if(carCorners.front.overlaps(opponent.carCorners.back)  ||  
           carCorners.front.overlaps(opponent.carCorners.left)  || 
           carCorners.front.overlaps(opponent.carCorners.right) ||
           carCorners.front.overlaps(opponent.carCorners.front) ){
            crash = true;
        } else{
            crash = false;
        }
        
        if(carCorners.front.overlaps(opponent.carCorners.back)  ||  
           carCorners.front.overlaps(opponent.carCorners.left)  || 
           carCorners.front.overlaps(opponent.carCorners.right) ||
           carCorners.front.overlaps(opponent.carCorners.front) ||
            carCorners.front.overlaps(opponent.carCorners.frontLeftCorner)  ||  
           carCorners.front.overlaps(opponent.carCorners.backLeftCorner)  || 
           carCorners.front.overlaps(opponent.carCorners.frontRightCorner) ||
           carCorners.front.overlaps(opponent.carCorners.backRightCorner)){
            crash = true;
        } else{
            crash = false;
        }
        
        if(carCorners.frontRightCorner.overlaps(opponent.carCorners.backLeftCorner)){
            turnRightCrash = true;
            opponent.rotation += 20f;
        } else{
            turnRightCrash = false;
        }
        
        if(carCorners.frontLeftCorner.overlaps(opponent.carCorners.backRightCorner)){
            turnLeftCrash = true;
            opponent.rotation -= 20f;
        } else{
            turnLeftCrash = false;
        }
        
        if(carCorners.frontRightCorner.overlaps(opponent.carCorners.frontLeftCorner)){
            turnRightCrash = true;
            opponent.rotation -= 20f;
        } else{
            turnRightCrash = false;
        }
        
        if(carCorners.frontLeftCorner.overlaps(opponent.carCorners.frontRightCorner)){
            turnLeftCrash = true;
            opponent.rotation += 20f;
        } else{
            turnLeftCrash = false;
        }
        
//        if(this.carCorners.getFront().overlaps(b.carCorners.getFront()) || this.carCorners.getFront().overlaps(b.carCorners.getBack())){
//            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            accelerate = false;
//        }
//        
//        if(this.carCorners.getLeft().overlaps(b.carCorners.getRight())){
//            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            turnLeft = false;
//        }
//        
//        if(this.carCorners.getRight().overlaps(b.carCorners.getLeft())){
//            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            turnRight = false;
//        }
    }
    
    public boolean crashed(){
        return crash;
    }
    
    public int getLap(){
        return carLap;
    }
}
