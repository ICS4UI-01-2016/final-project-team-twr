/*
 * This class deals with all of the functions and aspects of the cars within
 * RaceState.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.states.RaceState;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;
import java.text.DecimalFormat;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class Car {
    // ALL OF THE INSTANCE VARIABLES WITHIN CAR

    private float rotation;              // rotation of car when driving
    private Vector3 position;            // center position of the car on the track
    private CarRectCorners carCorners;   // corners bounding box of car on track
    private TextureRegion carPic;        // the picture of the car
    private TextureRegion carPointPic;   // the picture of the points that indicate the boundaries of the car
    private TextureRegion checkPointPic; // the picture of a checkpoint identifying that you hit a checkpoint
    public int carWidth;                // the width of the car
    public int carHeight;               // the height of the car
    private int carScale;                // scalar for the car image in world 
    private int carLap;                  // the lap that the car is on
    private int carCheckPoint = 0;       // the checkpoint that the car is on
    private TextureRegion flame;         // the picture of the car flame
    private float velocity;            // the velocity of the car
    private float speedX;              // the speed of the car going along the x-axis
    private float speedY;              // the speed of the car going along the y-axis
    private boolean accelerating;        // if the acceleration of the car going forward
    private boolean reversing;           // if the reverse of the car going backwards
    private boolean turnLeft;            // if the car is turning left
    private boolean turnRight;           // if the car is turning right
    private boolean frontHit;            // if the front is hit (intersection between cars)
    private boolean backHit;             // if the back is hit  (intersection between cars)
    private boolean crashFront = false;  // if the front is crashed (intersection with boundaries)
    private boolean crashBack = false;   // if the back is crashed (intersection with boundaries)
    private boolean hitCheckPoint;       // if the car has hit a checkpoint
    private BitmapFont font = new BitmapFont();  // the font printed
    private ShapeRenderer shapeRenderer = new ShapeRenderer(); // creating the shapes within the game
    private boolean turnLeftCrash = false;// if the left side of the car has crashed (intersection between cars)
    private boolean turnRightCrash = false; // if the right side of the car has crashed (intersection between cars)
    private boolean nitro;               // if the car is in apply nitro state
    private float nitroX;              // the red rectangle x width coordintate
    private boolean nitroPushed;         // if the nitro has been pushed
    private float nitroIncrease;       // the increase in nitro
    private float countTimer;          // the timer of the car
    private Texture count3;              // the image of the countdown number 3
    private Texture count2;              // the image of the countdown number 2
    private Texture count1;              // the image of the countdown number 1
    private Texture go;                  // the image of the countdown go
    private Sound tireScreach1;        // the tire screach of the left turn of the the car when turning
    private Sound tireScreach2;        // the tire screach of the right turn of the car when turning
    private Sound accelerationEffect1; // the acceleration sound of the car
    private Sound nitroEffect;         // the sound the car makes in nitro
    private boolean accelSound1;         // if the acceleration sound is being played 
    private boolean carScreech1;         // if the car screech for the left wheel is being played
    private boolean carScreech2;         // if the car screech for the right wheel is being played
    private boolean nitroSound1;         // If the nitro sound was put into affect

    /**
     * Constructor of Car
     *
     * @param x the x coordinate of the car
     * @param y the y coordinate of the car
     * @param carType the type of car
     * @param initialRotation the initialRotation of the car
     * @param initialPositionX the initial x position of the car
     * @param initialPositionY the initial y position of the car
     */
    public Car(int x, int y, int carType, float initialRotation, int initialPositionX, int initialPositionY) {
        // setting up the parameters to the class
        position = new Vector3(x, y, 0);
        rotation = initialRotation;
        position.x = initialPositionX;
        position.y = initialPositionY;

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
            carPic = new TextureRegion(new Texture("Lamborghiniblack-new.png"));
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
        nitroEffect = Gdx.audio.newSound(Gdx.files.internal("Nitro.mp3"));

        // initialize the bounding rectangle for the car on track
        carWidth = carPic.getRegionWidth() / carScale;
        carHeight = carPic.getRegionHeight() / carScale;
        carCorners = new CarRectCorners(carWidth, carHeight);

        // seeting the sounds to not being playing off the start
        this.accelSound1 = false;
        this.carScreech1 = false;
        this.nitroSound1 = false;
    }

    /**
     * Updating the car within RaceState
     *
     * @param deltaTime the current time within the game
     * @param raceState bringing in RaceState
     */
    public void update(float deltaTime, RaceState raceState) {

        // Update the coordinates of the car bounding box based on 
        // new car position
        carCorners.updateCornerPos(position, rotation);

        // Get the track feature underneath the car
        RaceState.TrackFeature feature = raceState.getTrackFeatureAtPt(position);

        // CHECK IF CAR ON GRASS
        // Check the track feature under the car and have the car react to it
        // if the car is driving on grass then increase the drag on the card
        // to reduce it's speed
        if (feature == RaceState.TrackFeature.GRASS) {
            // On grass slow the car to min slow pace
            if (velocity > .5 || velocity < -.5) {
                velocity *= .7f;
            }
        }

        // CHECK IF ON FINISHLINE
        // If the car in on the finishline area, increase the laps
        // and reset the checkpoints for the next lap 
        if (feature == RaceState.TrackFeature.FINISHLINE) {
            if (carCheckPoint == 7) {
                carLap += 1;
                carCheckPoint = 0;
            }
        }

        // CHECK IF ON CHECKPOINT
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

        // CAR HIT BARRIER AT FRONT OF CAR?
        // Check if the front of the car has hit a barrier 
        // If it has and the car has significant velocity then 
        // bounce the car off the barrier and set the car to crashed
        // state so it cannot turn left or right
        if (raceState.getTrackFeatureAtPt(carCorners.frontLeft)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.frontRight)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.front)
                == RaceState.TrackFeature.BARRIER) {
            // We hit a barrier.  Set the state of the 
            // car that the car is not accelerating forward and indicate the
            // car is in a crash state and not allowed to turn left or right
            // while in contact with the barrier
            accelerating = false;
            turnLeftCrash = true;
            turnRightCrash = true;

            // Check to see if the car has significant velocity to 
            // determine if the car actual bounces off the barrier 
            // or is just stopped dead when it hits
            if (velocity > 0) {
                // Bounce the car off the barrier but reduce the 
                // enertia of car as a result of hitting th barrier
                velocity *= -.9f;
            } else {
                // Not moving with signfiicant speed.  car is stopp
                // dead as a result of hitting the barrier
                velocity = 0;
                crashFront = true;
                crashBack = false;
            }

            // CAR HIT BARRIER AT BACKEND OF CAR?
            // Check if the back/rear of the car has hit a barrier 
            // If it has and the car has significant velocity then 
            // bounce the car off the barrier and set the car to crashed
            // state so it cannot turn left or right
        } else if (raceState.getTrackFeatureAtPt(carCorners.backLeft)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.backRight)
                == RaceState.TrackFeature.BARRIER
                || raceState.getTrackFeatureAtPt(carCorners.back)
                == RaceState.TrackFeature.BARRIER) {
            // We hit a barrier.  Set the state of the 
            // car that the car is not accelerating forward and indicate the
            // car is in a crash state and not allowed to turn left or right
            // while in contact with the barrier
            reversing = false;
            turnLeftCrash = true;
            turnRightCrash = true;

            // Check to see if the car has significant velocity to 
            // determine if the car actual bounces off the barrier 
            // or is just stopped dead when it hits
            if (velocity < 0) {
                // Bounce the car off the barrier but reduce the 
                // enertia of car as a result of hitting th barrier
                velocity *= -.9f;
            } else {
                // Not moving with signfiicant speed.  car is stopp
                // dead as a result of hitting the barrier
                velocity = 0;
                crashBack = true;
                crashFront = false;
            }

            // The car has not hit a barrier on the track hence ensure
            // the car is not in a creashed state
        } else {
            crashFront = false;
            crashBack = false;
            turnLeftCrash = false;
            turnRightCrash = false;
        }

        // ACCELERATING?
        // If the accelerator is being pressed and not in a crashedFront
        // state then increase the velocity of the car
        if (accelerating && !crashFront) {
            //Accelerator pressed and not in crashFront state
            velocity = velocity + 0.05f;
            // Limit the top speed of the car in forward direction
            if (velocity > 1.2f) {
                velocity = 1.2f;
            }

            // REVERSING OR BREAKING?
            // If the Reverse is pressed then reduce the velocity in forward
            // direction and if reverse is held that car will begin to move
            // in the reversing direction
        } else if (reversing && !crashBack) {
            velocity = velocity - 0.025f * 2f;

            // Limit the max speed of the car in reverse direction
            if (velocity < -1.2f) {
                velocity = -1.2f;
            }


            // MOVING FORWARD BUT NOT ACCELERAT FORWARD PRESSED?  APPLY NEWTONS FIST LAW
            // If the car is still has forward velocity but not accelerting then
            // use newton's first law and make it continue to move forward
            // but reduce the cars speed due to drag
        } else if (velocity > 0 && !crashBack) {
            velocity = velocity - 0.025f;
            if (velocity < 0) {
                velocity = 0;
            }

            // MOVING BACKWARDS BUT NOT REVERSE PRESSED?   APPLY NEWTONS FIST LAW
            // If the car is still has reverse velocity but the not applying
            // additional reverse force then use newton's first law and make it 
            // continue to move backwards but reduce the cars speed due to drag
        } else if (velocity < 0 && !crashFront) {
            velocity = velocity + 0.025f;
            if (velocity > 0) {
                velocity = 0;
            }
        }

        // CHECK CARS CURRENT ROTATION AMOUNT AND ADJUST
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

        // NITRO PRESSED or IN APPLY NITRO STATE?
        // If the nitro is being pressed or in apply nitro state
        // then boost the forward speed for a short period until 
        // the current nitro boost is exhausted. 
        if (nitro && nitroPushed) {
            nitroIncrease = nitroIncrease + 0.05f;
            // compute new location of nitro image
            nitroX = nitroX - 7;

            // Once we have reach the max nitro increase then
            // the Nitro boost if over
            if (nitroIncrease > 1.5f) {
                // Nitro boost limitt has been hit
                // indicate no longer in Nitro state. 
                nitro = false;
                nitroIncrease = 0f;
                nitroX = 0;
            }

            // If Nitro is not pressed then reset NITRO states of off/false
        } else if (nitroIncrease > 0) {
            nitro = false;
            nitroIncrease = 0f;
            nitroX = 0;
        }

        // UPDATE CAR POSITION BASED ON VELOCITY & DIRECTION
        // Use the cars current rotation (0-360) and velocity
        // to determine the speed of the car in the X and Y 
        // direction in the world coordinates
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


        // UPDATE THE CARS POSITION IN THE WORLD
        // ADDING ACCELERATION, NITRO, SPEED TOGETHER (RESTRICTION OF CRASH)
        if (((velocity > 0) && !frontHit)
                || ((velocity < 0) && !backHit)) {

            // Enhance the speed of the cars in the X and Y direction 
            // based on the NitroIncrease amount.
            if (nitroIncrease > 0) {
                speedX = speedX * (nitroIncrease + velocity);
                speedY = speedY * (nitroIncrease + velocity);
            }

            // Update the cars position the world
            position.x += speedX;
            position.y += speedY;
        }

        // CAR TURNING?   ONLY ALLOWED TO TURN WHILE IN MOTION
        // Update the cars rotation based on if it is being turned
        // left or right.   Only allow the car to turn
        // if it's in motion (ie has velocity)
        if ((velocity > .1) || (velocity < -.1)) {
            // Check if turning left and not in crash due to turning left
            if (!turnLeftCrash) {
                if (turnLeft) {
                    // Turning left, update the rotation of car in world
                    // Set the amount of rotation based on the cars speed
                    // to simulate realistic turn behaviour of car
                    rotation += 4f * (Math.abs(velocity) / 1.2);

                    // Screech the tires based on turn
                    if (carScreech1 == false) {
                        tireScreach1.play();
                        carScreech1 = true;
                    }
                } else {
                    // not turning, so ensure no tire screech
                    carScreech1 = false;
                    tireScreach1.stop();
                }
            }

            // Check if turning rigt and not in crash due to turning right
            if (!turnRightCrash) {
                if (turnRight) {
                    // Turning right, update the rotation of car in world
                    // Set the amount of rotation based on the cars speed
                    // to simulate realistic turn behaviour of car
                    rotation -= 4f * (Math.abs(velocity) / 1.2);

                    // Screech the tires based on turn
                    if (carScreech2 == false) {
                        tireScreach2.play();
                        carScreech2 = true;
                    }
                } else {
                    // not turning, so ensure no tire screech
                    carScreech2 = false;
                    tireScreach2.stop();
                }
            }
        }

        // UPDATE THE COORDINATES OF THE NITRO BOX
        // Increase the size of the red box to show Nitro 
        // up to a max size of time.
        if (nitroX < 220) {
            nitroX = nitroX + 1;
            if (nitroX == 220) {
                nitro = true;
            }
        }

        // 
        // SOUND (ACCELERATION/REVERSE)
        //
        // If the petal is hit then output the sound
        // If it is already playing then don't make it start over
        if (accelerating || reversing) {

            // Play sounds for car acceleration
            if (accelSound1 == false) {
                accelerationEffect1.loop();
                accelSound1 = true;
            }
        } else {
            // Stop the sournce for car acceleration
            accelSound1 = false;
            accelerationEffect1.stop();
        }

        // SOUND (NITRO) - Play sound when Nitro is being used
        if (nitroIncrease > 0) {
            if (nitroSound1 == false) {
                nitroEffect.play();
                nitroSound1 = true;
            }
        } else {
            // Stop the Nitro sound
            nitroSound1 = false;
            nitroEffect.stop();
        }

        // SOUND (REACH END)
        // If it has hit the end then stop the sound
        if (carLap == 6) {
            tireScreach1.stop();
            tireScreach2.stop();
            accelerationEffect1.stop();
            nitroEffect.stop();
        }
    }

    /**
     * Method used to render the HeadsUpDisplay that shows the players current
     * lap, checkpoint in the lap and time playing the game
     *
     * @param state - race state object
     * @param batch - SpriteBatch to accelerate drawing to screen
     * @param playTime - length of play
     */
    public void renderHUD(RaceState state, SpriteBatch batch, float playTime) {

        // Draw the heads up display relative to the camera position
        // On the HUD, show the following informaiton
        //   - lap #, 
        //   - checkpoint
        //   - time in race.
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

        // Enable transparency
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Draw the HUD background that is a RED box on top of Black background
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
        String text = "Lap: " + carLap + " CheckPoint: " + carCheckPoint + " Time: " + df.format(playTime);
        font.draw(batch, text, state.getCameraX() - 105, state.getCameraY() + 200);
        countTimer += Gdx.graphics.getDeltaTime();
        System.out.println("Car: " + countTimer);

        // At the start of the race, draw the race count down 
        if (countTimer > 4 && countTimer < 5) {
            batch.draw(count3, state.getCameraX() - 50, state.getCameraY(), 100, 100);
        } else if (countTimer < 6 && countTimer > 5) {
            batch.draw(count2, state.getCameraX() - 50, state.getCameraY(), 100, 100);
        } else if (countTimer < 7 && countTimer > 6) {
            batch.draw(count1, state.getCameraX() - 50, state.getCameraY(), 100, 100);
        } else if (countTimer > 7 && countTimer < 8) {
            batch.draw(go, state.getCameraX() - 50, state.getCameraY(), 100, 100);
        }
        batch.end();
    }

    /**
     * Method used to render the car onto the world and draw an indicator when
     * that car hits a check point on the track
     *
     * @param batch - SpriteBatch to accelerate drawing
     */
    public void render(SpriteBatch batch) {

        // DRAW NITRO:
        // If the car is in Nitro mode then draw the nitro with the car 
        if (nitroIncrease > 0) {
            batch.draw(flame, position.x - carWidth / 2, position.y - carHeight / 2, carWidth / 2, carHeight / 2, carWidth, carHeight * 2, 1, 1, rotation + 180);
        }

        // DRAW THE CAR
        batch.draw(carPic, position.x - carWidth / 2, position.y - carHeight / 2, carWidth / 2, carHeight / 2, carWidth, carHeight, 1, 1, rotation);

//        If player would like to see the corners of the car
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

        // DRAW A CHECK
        // If the car has passed a checkpoint, draw a checkpoint on track
        if (hitCheckPoint) {
            float cpX = position.x;
            float cpY = position.y;
            batch.draw(checkPointPic, cpX, cpY, 50, 50);
        }
    }

    /**
     * Method to return the X position of the car in world coordinates
     *
     * @return - x position
     */
    public float getX() {
        return position.x;
    }

    /**
     * Method to return the Y position of the car in world coordinates
     *
     * @return - y position
     */
    public float getY() {
        return position.y;
    }

    /**
     * Method to dispose of RaceState object
     */
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

    /**
     * Method to set the car as accelerator pedal pressed or not.
     *
     * @param accelerating - pressed or not
     */
    public void acceleratorPedal(boolean accelerating) {
        this.accelerating = accelerating;
    }

    /**
     * Method to set the cars brake or reverse pedal is pressed
     *
     * @param reversing
     */
    public void brakePedal(boolean reversing) {
        this.reversing = reversing;
    }

    /**
     * Method to indicate if car is turning left
     *
     * @param turningLeft
     */
    public void turnLeft(boolean turningLeft) {
        turnLeft = turningLeft;
    }

    /**
     * Method to indicate if car is turning right
     *
     * @param turningLeft
     */
    public void turnRight(boolean turningRight) {
        turnRight = turningRight;
    }

    /**
     * Method to indicate if car's Nitro is pressed
     *
     * @param turningLeft
     */
    public void nitroPushed(boolean boosting) {
        nitroPushed = boosting;
    }
//
//    /**
//     * Method to get the current 
//     * @return 
//     */
//    public float getSpeedX() {
//        return speedX + velocity;
//    }
//
//    public float getSpeedY() {
//        return speedY + velocity;
//    }

    // Determine the number of points of overlap between our car
    // and a point on the opponents car
    /**
     *
     * @param pointOnOpponent - opponent car point to check for overlaps on the
     * car
     * @return - returns number of points on car that overlap the point on
     * opponent
     */
    public int numPointsOverlap(Circle pointOnOpponent) {
        int numOverlaps = 0;

        // Total up the number of points on the car that
        // overlaps or intersect with the cars points
        if (carCorners.front.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.back.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.left.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.right.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.frontLeft.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.frontRight.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.backLeft.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }
        if (carCorners.backRight.overlaps(pointOnOpponent)) {
            numOverlaps += 1;
        }

        return numOverlaps;
    }

    /**
     * Method to update car state based on if the car has hit/collision with the
     * opponents car.
     *
     * @param opponent
     */
    public void checkCollision(Car opponent) {
        // Reset the crash indicators 
        frontHit = false;
        backHit = false;
        turnLeftCrash = false;
        turnRightCrash = false;

        // check to see if the front of the car has hit the opponent car and
        // if it has then block the car from forward movement
        if (carCorners.front.overlaps(opponent.carCorners.back)
                || carCorners.front.overlaps(opponent.carCorners.left)
                || carCorners.front.overlaps(opponent.carCorners.right)
                || carCorners.front.overlaps(opponent.carCorners.front)
                || carCorners.front.overlaps(opponent.carCorners.frontLeft)
                || carCorners.front.overlaps(opponent.carCorners.backLeft)
                || carCorners.front.overlaps(opponent.carCorners.frontRight)
                || carCorners.front.overlaps(opponent.carCorners.backRight)) {

            // hit opponent, block movement and transfer some enertia from
            // car to opponents car
            frontHit = true;
        }

        // check to see if the back of the car has hit the opponents car and 
        // if it has then block it from reverse motion
        if (carCorners.back.overlaps(opponent.carCorners.back)
                || carCorners.back.overlaps(opponent.carCorners.left)
                || carCorners.back.overlaps(opponent.carCorners.right)
                || carCorners.back.overlaps(opponent.carCorners.front)
                || carCorners.back.overlaps(opponent.carCorners.frontLeft)
                || carCorners.back.overlaps(opponent.carCorners.backLeft)
                || carCorners.back.overlaps(opponent.carCorners.frontRight)
                || carCorners.back.overlaps(opponent.carCorners.backRight)) {
            // hit opponent, block movement and transfer some enertia from
            // car to opponents car
            backHit = true;
        } else {
            backHit = false;
        }

        // if while traveling forward out car has contacted the 
        // back end of the opponent car then transfer inertia of our 
        // car to the opponent
        if (carCorners.front.overlaps(opponent.carCorners.back)) {
            opponent.velocity += velocity / 2;
            velocity = velocity / 2;
        }
        // if reversing out car has contacted the front of the 
        // opponent car then transfer inertia of our 
        // car to the opponent
        if (carCorners.back.overlaps(opponent.carCorners.front)) {
            opponent.velocity += velocity / 2;
            velocity = velocity / 2;
        }

        // Determine if based on contact, if we have hit the opponents car 
        // in one of the 4 corners only and if we have then cause the opponents
        // car to spin 
        if (carCorners.frontRight.overlaps(opponent.carCorners.backLeft)
                && numPointsOverlap(opponent.carCorners.back) == 1) {
            turnRightCrash = true;
            opponent.rotation += 20f;
        } else {
            turnRightCrash = false;
        }
        if (carCorners.frontRight.overlaps(opponent.carCorners.frontLeft)
                && numPointsOverlap(opponent.carCorners.back) == 1) {
            turnRightCrash = true;
            opponent.rotation -= 20f;
        } else {
            turnRightCrash = false;
        }
        if (carCorners.frontLeft.overlaps(opponent.carCorners.backRight)
                && numPointsOverlap(opponent.carCorners.back) == 1) {
            turnLeftCrash = true;
            opponent.rotation -= 20f;
        } else {
            turnLeftCrash = false;
        }
        if (carCorners.frontLeft.overlaps(opponent.carCorners.frontRight)
                && numPointsOverlap(opponent.carCorners.back) == 1) {
            turnLeftCrash = true;
            opponent.rotation += 20f;
        } else {
            turnLeftCrash = false;
        }
    }

    /**
     * Method to return the number of successful laps that the car has completed
     *
     * @return
     */
    public int getLap() {
        return carLap;
    }
}
