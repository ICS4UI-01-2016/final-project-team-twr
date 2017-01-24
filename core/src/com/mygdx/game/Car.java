/*
 * This class deals with all of the functions and aspects of the cars within
 * RaceState.
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
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;
import java.text.DecimalFormat;
import com.badlogic.gdx.files.FileHandle;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class Car {
    // ALL OF THE INSTANCE VARIABLES WITHIN CAR
    private float rotation;             // rotation of car when driving
    private Vector3 position;           // center position of the car on the track
    private CarRectCorners carCorners;  // corners bounding box of car on track
    private TextureRegion carPic;       // the picture of the car
    private TextureRegion carPointPic;  // the picture of the points that indicate the boundaries of the car
    private TextureRegion checkPointPic;// the picture of a checkpoint identifying that you hit a checkpoint
    public int carWidth;                // the width of the car
    public int carHeight;               // the height of the car
    private int carScale;               // scalar for the car image to track
    private int carLap;                 // the lap that the car is on
    private int carCheckPoint = 0;      // the checkpoint that the car is on
    private TextureRegion flame;        // the picture of the car flame
    private Rectangle bounds;           // the rectangle of the bounds of the car
    private Rectangle front;            // the front bounds of the car
    private Rectangle back;             // the back bounds of the car
    private Rectangle left;             // the left bounds of the car
    private Rectangle right;            // the right bounds of the car
    private float velocity;             // the velocity of the car
    private float speedX;               // the speed of the car going along the x-axis
    private float speedY;               // the speed of the car going along the y-axis
    private boolean accelerate;         // if the acceleration of the car going forward
    private boolean reverse;            // if the reverse of the car going backwards
    private boolean turnLeft;           // if the car is turning left
    private boolean turnRight;          // if the car is turning right
    private int tempCarType;            // the type of car
    private boolean frontHit;           // if the front is hit (intersection between cars)
    private boolean backHit;            // if the back is hit  (intersection between cars)
    private boolean crashFront = false; // if the front is crashed (intersection with boundaries)
    private boolean crashBack = false;  // if the back is crashed (intersection with boundaries)
    private boolean hitCheckPoint;      // if the car has hit a checkpoint
    private BitmapFont font = new BitmapFont();  // the font printed
    private ShapeRenderer shapeRenderer = new ShapeRenderer(); // creating the shapes within the game
    private boolean turnLeftCrash = false;// if the left side of the car has crashed (intersection between cars)
    private boolean turnRightCrash = false; // if the right side of the car has crashed (intersection between cars)
    private boolean nitro;              // if the car is in nitro
    private float nitroX;               // the red rectangle x width coordintate
    private boolean nitroPushed;        // if the nitro has been pushed
    private float nitroIncrease;        // the increase in nitro
    private float countTimer;           // the timer of the car                
    private Texture count3;             // the image of the countdown number 3
    private Texture count2;             // the image of the countdown number 2
    private Texture count1;             // the image of the countdown number 1
    private Texture go;                 // the image of the countdown go
    private Sound tireScreach1;         // the tire screach of the left turn of the the car when turning
    private Sound tireScreach2;         // the tire screach of the right turn of the car when turning
    private Sound accelerationEffect1;  // the acceleration sound of the car
    private boolean accelSound1;        // if the acceleration sound is being played 
    private boolean carScreech1;        // if the car screech for the left wheel is being played
    private boolean carScreech2;        // if the car screech for the right wheel is being played

    /**
     * Constructor of Car
     * @param x the x coordinate of the car
     * @param y the y coordinate of the car
     * @param carType the type of car
     * @param initialRotation the initialRotation of the car
     * @param initialPositionX the initial x position of the car
     * @param initialPositionY  the initial y position of the car
     */
    public Car(int x, int y, int carType, float initialRotation, int initialPositionX, int initialPositionY) {
        // setting up the parameters to the class
        position = new Vector3(x, y, 0);
        rotation = initialRotation;
        position.x = initialPositionX;
        position.y = initialPositionY;
        tempCarType = carType;
        
        // the images of the class
        flame = new TextureRegion(new Texture("flame.png"));
        carPointPic = new TextureRegion(new Texture("point.png"));
        checkPointPic = new TextureRegion(new Texture("checkpoint.png"));
        count3 = new Texture("count3.png");
        count2 = new Texture("count2.png");
        count1 = new Texture("count1.png");
        go = new Texture("go.png");

        // setup the image of the car, it's appropriate scale on the track
        // and it's bounding rectangle on the track
        if (carType == 1) {
            carPic = new TextureRegion(new Texture("lamborghiniblack-new.png"));
            carScale = 7;
        } else if (carType == 2) {
            carPic = new TextureRegion(new Texture("acura-new.png"));
            carScale = 9;
        } else if (carType == 3) {
            carPic = new TextureRegion(new Texture("lamborghini2-new.png"));
            carScale = 11;
        } else if (carType == 4) {
            carPic = new TextureRegion(new Texture("Bentley2-new.png"));
            carScale = 7;
        }
        
        // setting the various sounds from the car
        tireScreach1 = Gdx.audio.newSound(Gdx.files.internal("tirescreach.mp3"));
        tireScreach2 = Gdx.audio.newSound(Gdx.files.internal("tirescreach.mp3"));
        accelerationEffect1 = Gdx.audio.newSound(Gdx.files.internal("accelerationeffect2.mp3"));

        // initialize the bounding rectangle for the car on track
        carWidth = carPic.getRegionWidth() / carScale;
        carHeight = carPic.getRegionHeight() / carScale;
        carCorners = new CarRectCorners(carWidth, carHeight);
        bounds = new Rectangle(position.x, position.y, carWidth, carHeight);
        
        // seeting the sounds to not being playing off the start
        this.accelSound1 = false;
        this.carScreech1 = false;
    }
    
    /**
     * Updating the car within RaceState
     * @param deltaTime the current time within the game
     * @param raceState bringing in RaceState
     */
    public void update(float deltaTime, RaceState raceState) {
        
        //                            UPDATE
        // Update the coordinates of the car bounding box based on 
        // new car position
        carCorners.updateCornerPos(position, rotation);
        
        //                          TRACK FEATURES
        // Get the track feature underneath the car
        RaceState.TrackFeature feature = raceState.getTrackFeatureAtPt(position);
        
        //                              GRASS
        // Check the track feature under the car and have the car react to it
        // if the car is driving on grass then increase the drag on the card
        // to reduce it's speed
        if (feature == RaceState.TrackFeature.GRASS) {
            // On grass slow the car to min slow pace
            if (velocity > .5 || velocity < - .5) {
                velocity *= .7f;
            }
        }
        
        //                             FINISHLINE
        // if the car in on the finishline area, increase the laps
        // and reset the checkpoints for the next lap 
        if (feature == RaceState.TrackFeature.FINISHLINE) {
            if (carCheckPoint == 7) {
                carLap += 1;
                carCheckPoint = 0;
            }
        }
        
        //                              CHECKPOINTS
        // Check to see if the car has hit a checkpoint
        // then paste an image that identifies that it has hit a check point
        if ((feature.ordinal() >= RaceState.TrackFeature.CHECKPOINT1.ordinal())
                && (feature.ordinal() <= RaceState.TrackFeature.CHECKPOINT8.ordinal())) {
            int newCheckPoint = feature.ordinal() - RaceState.TrackFeature.CHECKPOINT1.ordinal() + 1;
            if (newCheckPoint == (carCheckPoint + 1)) {
                carCheckPoint = newCheckPoint;
                hitCheckPoint = true;
            }
        } else {
            hitCheckPoint = false;
        }
        
        //                                 BOUNDARIES
        // FRONT
        // check if the front of the car has hit a barrier from the front
        // if it has and there is a velocity then bounce it off and set that it has crashed
        // if it hasn't then it hasn't crashed and reset variables
        if (raceState.getTrackFeatureAtPt(carCorners.frontLeft)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.frontRight)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.front)
                == RaceState.TrackFeature.BARRIER) {
            // the car hit a barrier, bound the car of the barrier
            if(velocity > 0){
                accelerate = false;
                velocity *= -.9f;
                turnLeftCrash = true;
                turnRightCrash = true;
            }else{
                velocity = 0;
                accelerate = false;
                crashFront = true;
                turnLeftCrash = true;
                turnRightCrash = true;
                crashBack = false;
            }
        // BACK
        // check if the front of the car has hit a barrier from the front
        // if it has and there is a velocity then bounce it off and set that it has crashed
        // if it hasn't then it hasn't crashed and reset variables
        } else if(raceState.getTrackFeatureAtPt(carCorners.backLeft)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.backRight)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.back)
                == RaceState.TrackFeature.BARRIER){
            if(velocity < 0){
                reverse = false;
                velocity *= -.9f;
                turnLeftCrash = true;
                turnRightCrash = true;
            }else{
                velocity = 0;
                reverse = false;
                crashBack = true;
                turnLeftCrash = true;
                turnRightCrash = true;
                crashFront = false;
            }
        // If neither has intersected then it has not crashed
        } else{
            crashFront = false;
            crashBack = false;
            turnLeftCrash = false;
            turnRightCrash = false;
        }
        
        //                         ACCELERATION
        // If the accelerator is being pressed then increase the velocity
        // otherwise reduce the velocity to simulate the drag/friction
        // of the road and air and make it start moving backwards
        
        // if the accelerator is hit and it is not crashed then accelerate
        if (accelerate && !crashFront) {
            velocity = velocity + 0.05f;
            if (velocity > 1.2f) {
                velocity = 1.2f;
            }
        // If the reverse is hit and it is not crashed then reverse
        } else if (reverse && !crashBack) {
            velocity = velocity - 0.025f * 2f;
            if (velocity < - 1.2f) {
                velocity = - 1.2f;
            }
        // If it still has a velocity in the positive direction use newton's first law 
        // and make it continue to move with drag
        } else if (velocity > 0 && !crashBack){
            velocity = velocity - 0.025f;
            if (velocity < 0) {
                velocity = 0;
            }
        // If it still has a velocity in the negitive direction use newton's first law 
        // and make it continue to move with drag
        } else if(velocity < 0 && !crashFront){
            velocity = velocity + 0.025f;
            if (velocity > 0) {
                velocity = 0;
            }
        }
        
        //                       360 ROTATION
        // Ensure that the rotation value stays within
        // 0-360 and as soon as it goes over or under 
        // the roll it back to value within 0-360 while
        // still maintaining it's relative rotation value
        while (rotation > 360) {
            rotation = rotation - 360;
        }
        while (rotation < 0) {
            rotation = rotation + 360;
        }
        
        //                         NITRO
        // If the nitro is being hit and it is able to boost then increase
        // basically the acceleration
        if (nitro && nitroPushed) {
            nitroIncrease = nitroIncrease + 0.05f;
            nitroX = nitroX - 7;
            if (nitroIncrease > 1.5f) {
                nitroIncrease = 0f;
                nitroX = 0;
                nitro = false;
            }
        // If the nitro is used up reset it
        } else if (nitroIncrease > 0) {
            nitroIncrease = 0f;
            nitroX = 0;
            nitro = false;
        }
        
        //                        MOVEMENT / SPEED
        // If the car has velocity then update the cars postion
        // based on it's velocit and direction/rotation
        if (rotation >= 0 && rotation <= 90) {
            float tempRotation = rotation;
            speedX = (0.0f - (tempRotation / 18.0f)) * velocity;
            speedY = (5.0f - (tempRotation / 18.0f)) * velocity;
        } else if (rotation >= 90 && rotation <= 180) {
            float tempRotation = rotation - 90;
            speedX = (-5.0f + (tempRotation / 18.0f)) * velocity;
            speedY = (0.0f - (tempRotation / 18.0f)) * velocity;
        } else if (rotation >= 180 && rotation <= 270) {
            float tempRotation = rotation - 180;
            speedX = (0.0f + (tempRotation / 18.0f)) * velocity;
            speedY = (-5.0f + (tempRotation / 18.0f)) * velocity;
        } else if (rotation >= 270 && rotation <= 360) {
            float tempRotation = rotation - 270;
            speedX = (5.0f - (tempRotation / 18.0f)) * velocity;
            speedY = (0.0f + (tempRotation / 18.0f)) * velocity;
        }
        
        //  ADDING ACCELERATION, NITRO, SPEED TOGETHER (RESTRICTION OF CRASH)
        if( ((velocity > 0 ) && !frontHit) || 
        ((velocity < 0 ) && !backHit) ) {
            if (nitroIncrease > 0) {
                nitroIncrease = nitroIncrease + velocity;
                speedX = speedX * nitroIncrease;
                speedY = speedY * nitroIncrease;
                nitroIncrease = nitroIncrease - velocity;
            }

            position.x += speedX;
            position.y += speedY;
        }
        
        //                     TURNING
        // Update the cars rotation based on if it is being turned
        // left or right.   Only allow the car to turn
        // if it's in motion (ie has velocity)
        if ( (velocity > .1) || (velocity < -.1) ) {
              if (!turnLeftCrash) {
                if (turnLeft) {
                    // set the amount of turn/rotate based on the cars speed
                    rotation += 4f * (Math.abs(velocity) / 1.2) ;
                    if(carScreech1 == false){
                        tireScreach1.play();
                        carScreech1 = true;
                    }
                } else{
                    carScreech1 = false;
                    tireScreach1.stop();
                }
              }

              if (!turnRightCrash) {
                if (turnRight) {
                    // set the amount of turn/rotate based on the cars speed
                    rotation -= 4f * (Math.abs(velocity) / 1.2);
                    if(carScreech2 == false){
                        tireScreach2.play();
                        carScreech2 = true;
                    }
                }else{
                    carScreech2 = false;
                    tireScreach2.stop();
                }
              }
        }
    
        //                    NITRO BOX
        // Increase the red box based on the time
        if (nitroX < 220) {
            nitroX = nitroX + 1;
            if (nitroX == 220) {
                nitro = true;
            }
        }
        
        //                     SOUND
        // ACCELERATION/REVERSE
        // If the petal is hit then output the sound
        // If it is already playing then don't make it start over
        if(accelerate || reverse){
            if(accelSound1 == false){
                accelerationEffect1.loop();
                accelSound1 = true;
            }
        } else{
            accelSound1 = false;
            accelerationEffect1.stop();
        }
        // If it has hit the end then stop the sound
        if(carLap == 3){
            accelerationEffect1.stop();
        }
        

        bounds.setPosition(position.x, position.y);
//        front.setPosition(position.x, position.y + 50);
//        back.setPosition(position.x, position.y);
//        left.setPosition(position.x, position.y);
//        right.setPosition(position.x + 50, position.y);
    }

    public void renderHUD(RaceState state, SpriteBatch batch, float PlayTime) {

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
        shapeRenderer.rect(state.getCameraX() - 110, state.getCameraY() + 175, 220, 35);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(state.getCameraX() - 110, state.getCameraY() + 175, nitroX, 35);
        shapeRenderer.end();

        // End Transparency
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // Draw the HUD Text as separate batch from shapeRender since
        // the need to work on different batches
        batch.begin();
        font.setColor(Color.WHITE);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        String text = "Lap: " + carLap + " CheckPoint: " + carCheckPoint + " Time: " + df.format(PlayTime);
        font.draw(batch, text, state.getCameraX() - 105, state.getCameraY() + 200);
        countTimer += Gdx.graphics.getDeltaTime();

        System.out.println(countTimer);
        

        if (countTimer < 1) {
            batch.draw(count3, state.getCameraX()  - 50, state.getCameraY(), 100, 100);
        }
        if (countTimer < 2 && countTimer > 1) {
            batch.draw(count2, state.getCameraX()  - 50, state.getCameraY(), 100, 100);
        }
        if (countTimer < 3 && countTimer > 2) {
            batch.draw(count1, state.getCameraX()  - 50, state.getCameraY(), 100, 100);
        }
        if (countTimer > 3 && countTimer < 4) {
            batch.draw(go, state.getCameraX()  - 50, state.getCameraY(), 100, 100);
        }
        batch.end();
    }

    public void render(SpriteBatch batch) {

        if (nitroIncrease > 0) {
            batch.draw(flame, position.x - carWidth / 2, position.y - carHeight / 2, carWidth / 2, carHeight / 2, carWidth, carHeight * 2, 1, 1, rotation + 180);
        }

        batch.draw(carPic, position.x - carWidth / 2, position.y - carHeight / 2, carWidth / 2, carHeight / 2, carWidth, carHeight, 1, 1, rotation);
//        batch.draw( carPointPic, position.x, position.y);
//        
//        // draw corners of the car
//        batch.draw( carPointPic, carCorners.backLeftCorner.x,    carCorners.backLeftCorner.y);
//        batch.draw( carPointPic, carCorners.backRightCorner.x,   carCorners.backRightCorner.y);
//        batch.draw( carPointPic, carCorners.frontLeftCorner.x,   carCorners.frontLeftCorner.y);
//        batch.draw( carPointPic, carCorners.frontRightCorner.x,  carCorners.frontRightCorner.y);
//        
//        // draw corners of the car
//        batch.draw( carPointPic, carCorners.back.x,    carCorners.back.y);
//        batch.draw( carPointPic, carCorners.right.x,   carCorners.right.y);
//        batch.draw( carPointPic, carCorners.left.x,   carCorners.left.y);
//        batch.draw( carPointPic, carCorners.front.x,  carCorners.front.y);
//        
        if (hitCheckPoint) {
            float cpX = position.x;
            float cpY = position.y;
            batch.draw(checkPointPic, cpX, cpY, 50, 50);
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

//    public Rectangle getBounds() {
//        return bounds;
//    }
//
//    public Rectangle rightBounds() {
//        return right;
//    }
//
//    public Rectangle leftBounds() {
//        return left;
//    }
//
//    public Rectangle frontBounds() {
//        return front;
//    }
//
//    public Rectangle backBounds() {
//        return bounds;
//    }

    public void dispose() {
        carPic.getTexture().dispose();
        flame.getTexture().dispose();
        carPointPic.getTexture().dispose();
        checkPointPic.getTexture().dispose();
        count3.getTextureData().disposePixmap();
        count2.getTextureData().disposePixmap();
        count1.getTextureData().disposePixmap();
        go.getTextureData().disposePixmap();
        tireScreach1.dispose();
        tireScreach2.dispose();
        accelerationEffect1.dispose();

    }

    public void acceleratorPedal(boolean accelerating) {
        accelerate = accelerating;
    }

    public void brakePedal(boolean braking) {
        reverse = braking;
    }

    public void turnLeft(boolean turningLeft) {
        turnLeft = turningLeft;
    }

    public void turnRight(boolean turningRight) {
        turnRight = turningRight;
    }

    public void nitroPushed(boolean boosting) {
        nitroPushed = boosting;
    }

    public float getSpeedX() {
        return speedX + velocity;
    }

    public float getSpeedY() {
        return speedY + velocity;
    }

    // Determine the number of points of overlap between our car
    // and a point on the opponents car
    public int numPointsOverlap( Circle pointOnOpponent) {
        int numOverlaps = 0;

        // check 
        if ( carCorners.frontC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.backC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.leftC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.rightC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.frontLeftCornerC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.frontRightCornerC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.backLeftCornerC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        if ( carCorners.backRightCornerC.overlaps( pointOnOpponent )) {
            numOverlaps +=1;
        }
        
        return numOverlaps;
    }
    
    public void checkCollision(Car opponent) {
        //
        // Reset the crash indicators 
        // 
        frontHit     = false;
        backHit     = false;
        turnLeftCrash  = false;
        turnRightCrash = false;
        
        // check to see if the front of the car has hit the opponent car and
        // if it has then block the car from forward movement
        if ( carCorners.frontC.overlaps(opponent.carCorners.backC)  ||
             carCorners.frontC.overlaps(opponent.carCorners.leftC)  ||
             carCorners.frontC.overlaps(opponent.carCorners.rightC) ||
             carCorners.frontC.overlaps(opponent.carCorners.frontC) ||
             carCorners.frontC.overlaps(opponent.carCorners.frontLeftCornerC)  ||
             carCorners.frontC.overlaps(opponent.carCorners.backLeftCornerC)   ||
             carCorners.frontC.overlaps(opponent.carCorners.frontRightCornerC) ||
             carCorners.frontC.overlaps(opponent.carCorners.backRightCornerC) ) {
            
            // hit opponent, block movement and transfer some enertia from
            // car to opponents car
            frontHit = true;
        }
        
        // check to see if the back of the car has hit the opponents car and 
        // if it has then block it from reverse motion
        if ( carCorners.backC.overlaps(opponent.carCorners.backC)  ||
             carCorners.backC.overlaps(opponent.carCorners.leftC)  ||
             carCorners.backC.overlaps(opponent.carCorners.rightC) ||
             carCorners.backC.overlaps(opponent.carCorners.frontC) ||
             carCorners.backC.overlaps(opponent.carCorners.frontLeftCornerC)  ||
             carCorners.backC.overlaps(opponent.carCorners.backLeftCornerC)   ||
             carCorners.backC.overlaps(opponent.carCorners.frontRightCornerC) ||
             carCorners.backC.overlaps(opponent.carCorners.backRightCornerC)) {
            // hit opponent, block movement and transfer some enertia from
            // car to opponents car
            backHit = true;
        } else {
            backHit = false;
        }

        // if while traveling forward out car has contacted the 
        // back end of the opponent car then transfer inertia of our 
        // car to the opponent
        if ( carCorners.frontC.overlaps(opponent.carCorners.backC) ) {
            opponent.velocity += velocity / 2;
            velocity = velocity / 2;
        }
        // if reversing out car has contacted the front of the 
        // opponent car then transfer inertia of our 
        // car to the opponent
        if ( carCorners.backC.overlaps(opponent.carCorners.frontC) ) {
            opponent.velocity += velocity / 2;
            velocity = velocity / 2;
        }
  
        // Determine if based on contact, if we have hit the opponents car 
        // in one of the 4 corners only and if we have then cause the opponents
        // car to spin 
        if (carCorners.frontRightCornerC.overlaps(opponent.carCorners.backLeftCornerC) &&
             numPointsOverlap(opponent.carCorners.backC) == 1 ) {
            turnRightCrash = true;
            opponent.rotation += 20f;
        } else {
            turnRightCrash = false;
        }
        if (carCorners.frontRightCornerC.overlaps(opponent.carCorners.frontLeftCornerC) &&
             numPointsOverlap(opponent.carCorners.backC) == 1 ) {
            turnRightCrash = true;
            opponent.rotation -= 20f;
        } else {
            turnRightCrash = false;
        }
        if (carCorners.frontLeftCornerC.overlaps(opponent.carCorners.backRightCornerC) &&
             numPointsOverlap(opponent.carCorners.backC) == 1 ) {
            turnLeftCrash = true;
            opponent.rotation -= 20f;
        } else {
            turnLeftCrash = false;
        }
        if (carCorners.frontLeftCornerC.overlaps(opponent.carCorners.frontRightCornerC) &&
             numPointsOverlap(opponent.carCorners.backC) == 1 ) {
            turnLeftCrash = true;
            opponent.rotation += 20f;
        } else {
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

    public boolean crashed() {
        return crashFront;
    }

    public int getLap() {
        return carLap;
    }
}
