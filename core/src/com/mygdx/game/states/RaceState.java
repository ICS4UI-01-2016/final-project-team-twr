/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Car;
import com.mygdx.game.CarRectCorners;
import com.mygdx.game.RaceIt;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */

/*
 * RateState class - class used to perform all game functionality when the 
 *     game is in the "Race State".    The RaceState class contains all 
 *     data, objects and functionality required by the racing game while 
 *     in the RaceState.
 */
public class RaceState extends State {

    // Create the constant variables
    private final Car car1;                  // Car1 = Player1
    private final Car car2;                  // Car2
    private Texture bg;                      // The Track being raced on
    private int track;                       // The number of the track selected
    private int carType1;                    // Type of car selected by player1
    private int carType2;                    // Type of car selected by player2
    private float playTime;                  // Length of play in RaceState mode
    private TrackFeature[] trackFeaturesMap; // array of track feautre at each (X,Y)
    private int  trackFeaturesMapWidth;      // width of the track feature map array
    private int trackFeaturesMapHeight;      // height of the track feature map array
    private StateManager sm;                 // State Manager
    private float countTimer;                // Count Down Time at start of race
    private Sound countDown;                 // Sound to play at count down
    private boolean countDownOn = false;     // Count down sound played

    // Track features that are supported in the TrackFeaturesMap arrage
    public enum TrackFeature {  
        UNKNOWN, ROAD, GRASS, BARRIER, FINISHLINE,
        CHECKPOINT1, CHECKPOINT2, CHECKPOINT3, CHECKPOINT4,
        CHECKPOINT5, CHECKPOINT6, CHECKPOINT7, CHECKPOINT8
    };
  
    
    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm, int track, int car1Type, int car2Type) {
        super(sm);
        sm.stopMusic();
        setCameraView(RaceIt.WIDTH / 4, RaceIt.HEIGHT / 2);
        this.track = track;
        this.carType1 = car1Type;
        this.carType2 = car2Type;
        playTime = 0.0f;
        this.sm = sm;
        countDown = Gdx.audio.newSound(Gdx.files.internal("321.mp3"));
        if (track == 1) {
            car1 = new Car(600, 400, carType2, 270, 220, 800);
            car2 = new Car(600, 400, carType1, 270, 220, 750);
            bg = new Texture("Track1.jpg");
            loadTrackFeatureMap("Track1Boundaries.png");
        } else {
            car1 = new Car(600, 400, carType2, 0, 855, 535);
            car2 = new Car(600, 400, carType1, 0, 930, 535);
            bg = new Texture("Track2.1.jpg");
            loadTrackFeatureMap("Track2.1Boundaries.png");
        }
        
        countTimer = 0;

    }

    /**
     * Method used to move the camera view based on the cars position in the
     *    world   The camera follows the position of each car.
     * 
     * @param batch - Spritebatch used to batch the screen updates
     * @param car   - car the camera should be centered over
     */
    public void moveCamView(SpriteBatch batch, Car car) {
        // Update the camera view
        moveCameraX(car.getX());
        if (getCameraX() > 870) {
            moveCameraX(870);
        }

        if ((getCameraX() < 125)) {
            moveCameraX(125);
        }

        moveCameraY(car.getY());
        if (!(getCameraY() < 675)) {
            moveCameraY(675);
        }

        if (!(getCameraY() > 225)) {
            moveCameraY(225);
        }

        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
    }

    /**
     * Method to render the RaceState
     * @param batch 
     */
    public void render(SpriteBatch batch) {

        // Set the side of camera view
        setCameraView(RaceIt.WIDTH / 4, RaceIt.HEIGHT / 2);

        //
        // RENDER CAR1 - on right side of the screen (split pane)
        //
        batch.begin();
        // set viewport for RIGHT side,leave a space of 2 pixels to separte the frames 
        Gdx.gl.glViewport(Gdx.graphics.getWidth() / 2 + 2, 0 + 2, Gdx.graphics.getWidth() / 2 - 4, Gdx.graphics.getHeight() - 4);
        moveCamView(batch, car1);
        // draw track
        batch.draw(bg, 0, 0, getViewWidth() * 4, getViewHeight() * 2);
        // draw track
        car1.render(batch);
        car2.render(batch);
        // End the batch to allow the HUD to be displated and use ShaperRenderr
        batch.end();
        // draw the cars Hends Up Display
        car1.renderHUD(this, batch, playTime);

        // RENDER CAR2 - on left side of the screen (split pane)
        batch.begin();
        // set viewport for LEFT side, leave a space of 2 pixels to separte the frames 
        Gdx.gl.glViewport(0 + 2, 0 + 2, Gdx.graphics.getWidth() / 2 - 4, Gdx.graphics.getHeight() - 4);
        moveCamView(batch, car2);
        // draw track
        batch.draw(bg, 0, 0, getViewWidth() * 4, getViewHeight() * 2);
        // draw cars
        car1.render(batch);
        car2.render(batch);
        // End the batch to allow the HUD to be displated and use ShaperRenderr
        // draw the cars Hends Up Display
        batch.end();
        car2.renderHUD(this, batch, playTime);

    }

    /**
     * Method to update the state of the RaceState object for 
     *    each time interval delta time
     *     
     * @param deltaTime 
     */
    @Override
    public void update(float deltaTime) {
        countTimer += Gdx.graphics.getDeltaTime();

        // At the beginning of the game, start the countdown
        // and play the count down sound
        if(countTimer > 4 && !countDownOn){
            sm.play();
            countDown.play();
            countDownOn = true;
        }
        
        // Allow Car1 and Car2 to update their state once 
        // the game count down has completed
        if (countTimer > 8) {
            playTime += deltaTime;

            // Update car1 and car2 state
            car1.update(deltaTime, this);
            car2.update(deltaTime, this);
            
            // CHECK FOR WINNER
            // Check to see if car1 or car2 has completed 6 laps
            // if they have, then declare them a winner by transitioning
            // to the Winner State with associate winner
            StateManager gsm = getStateManager();
            if (car1.getLap() == 6) {
                gsm.push(new WinnerState(gsm, carType1, 1));
            } else if (car2.getLap() == 6) {
                gsm.push(new WinnerState(gsm, carType2, 2));
            }

            // Allow each car to determine if it has collided
            // with the opponent
            car1.checkCollision(car2);
            car2.checkCollision(car1);
        }

    }

    /**
     * Method to map the keyboard input into the controls for each car
     */
    @Override
    public void handleInput() {

        // CAR1 ACCELERATOR
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            car1.acceleratorPedal(true);
        } else {
            car1.acceleratorPedal(false);
        }

        // CAR1 TURN LEFT
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            car1.turnLeft(true);
        } else {
            car1.turnLeft(false);
        }

        // CAR1 TURN RIGHT
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            car1.turnRight(true);
        } else {
            car1.turnRight(false);
        }
            
        // CAR1 BRAKE
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            car1.brakePedal(true);
        } else {
            car1.brakePedal(false);
        }

        // CAR1 NITRO
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            car1.nitroPushed(true);
        } else {
            car1.nitroPushed(false);
        }

        // CAR2 ACCELERATOR
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            car2.acceleratorPedal(true);
        } else {
            car2.acceleratorPedal(false);
        }

        // CAR2 TURN LEFT
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            car2.turnLeft(true);
        } else {
            car2.turnLeft(false);
        }

        // CAR2 TURN RIGHT
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            car2.turnRight(true);
        } else {
            car2.turnRight(false);
        }

        // CAR2 BRAKE
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            car2.brakePedal(true);
        } else {
            car2.brakePedal(false);
        }

        // CAR2 NITRO
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            car2.nitroPushed(true);
        } else {
            car2.nitroPushed(false);
        }

        // GAME PAUSE
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
            sm.push(new PauseState(sm, track, carType1, carType2));
        }
    }

    @Override
    public void dispose() {
        car1.dispose();
        car2.dispose();
    }

    
    // RGB constants used in the track features PNG file to
    // mark/map out the different features of the supported tracks
    private static final int RGB_ROAD = -13816531;
    private static final int RGB_GRASS = -14503604;
    private static final int RGB_BARRIER = -1237980;
    private static final int RGB_FINISHLINE = -6075996;
    private static final int RGB_CHECKPOINT1 = -32985;
    private static final int RGB_CHECKPOINT2 = -14066;
    private static final int RGB_CHECKPOINT3 = -3584;
    private static final int RGB_CHECKPOINT4 = -1055568;
    private static final int RGB_CHECKPOINT5 = -4856291;
    private static final int RGB_CHECKPOINT6 = -6694422;
    private static final int RGB_CHECKPOINT7 = -9399618;
    private static final int RGB_CHECKPOINT8 = -8;

    /**
     * Method to turn the track feature that is defined at world position (X,Y)
     *     the track features are loaded from a background image and define
     *     if the location is GRASS, TRACK, BARRIER and other track features
     * 
     * @param posX  - X coordinate of a point in world coordinate
     * @param posY  - Y coordinate of a point in world coordinate
     * @return  - returns the track feature found at (X,Y)
     */    
    public TrackFeature getTrackFeatureAtXY( float posX, float posY) {
        // map from the game coordinate to the track feature map coordinate
        float x = (posX / RaceIt.WIDTH) * trackFeaturesMapWidth;
        float y = (posY / RaceIt.HEIGHT) * trackFeaturesMapHeight;

        // return the track feature found at (x,y) on the trackFeatureMap
        // ensure that if X,Y have fall outside of the map, that an UNKNOWN is 
        // returned.  
        if (x > 0 && y > 0 && x < trackFeaturesMapWidth && y < trackFeaturesMapHeight) {
            return trackFeaturesMap[(int) x + (int) (trackFeaturesMapHeight - y) * trackFeaturesMapWidth];
        } else {
            return TrackFeature.UNKNOWN;
        }
    }
    
    /**
     * Method to return track feature for a point where point is defined 
     *     in the X and Y of a circle. 
     * @param corner
     * @return 
     */
    public TrackFeature getTrackFeatureAtPt( Circle corner) {
        return ( getTrackFeatureAtXY( corner.x, corner.y ));
    }
    
    /**
     * Method to return track feature for a point where point is defined 
     *     in the X and Y of a Vector3
     * @param corner
     * @return 
     */
    public TrackFeature getTrackFeatureAtPt( Vector3 corner) {
        return ( getTrackFeatureAtXY( corner.x, corner.y ));
    }
    
    /**
     * Method to return the most interesting features that are found at the 
     *     4 corners of the car.   The interesting feature is used to 
     *     determine if the car is in contact with Barriers on the track. 
     * 
     * @param carRect - contains the corners of the car
     * @return 
     */
    public TrackFeature getInterestingTrackFeatureCorners(CarRectCorners carRect) {
        TrackFeature feature;
        TrackFeature returnFeature;

        // Get the track feature at each corner of the car and return
        // the highest (most interesting) feature value since they are 
        // ordered in value in terms of interest to the game
        // with BARRIERS being most interesting and causing the car
        // to stopped.
        
        // return the most interesting feature by comparing their values
        // since the features have been order from least to most 
        // interesting in ordinal value order. 
        returnFeature = getTrackFeatureAtPt(carRect.backLeft);
        feature = getTrackFeatureAtPt(carRect.frontLeft);
        if (feature.ordinal() > returnFeature.ordinal()) {
            returnFeature = feature;
        }
        feature = getTrackFeatureAtPt(carRect.frontRight);
        if (feature.ordinal() > returnFeature.ordinal()) {
            returnFeature = feature;
        }
        feature = getTrackFeatureAtPt(carRect.backLeft);
        if (feature.ordinal() > returnFeature.ordinal()) {
            returnFeature = feature;
        }

        return returnFeature;
    }

    /**
     * Method that loads the a TrackFeature PNG and creates a 
     *     Track Features array that is used to determine for any world 
     *     coordinate any significant feature about the loaded track at 
     *     the specific world coordinate.
     * 
     * @param trackFeaturePNG
     * @return boolean whether the track feature map could be loaded.
     */
    public boolean loadTrackFeatureMap(String trackFeaturePNG) {
        TrackFeature feature;

        Image image = Toolkit.getDefaultToolkit().getImage(trackFeaturePNG);

        try {
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false);
            if (grabber.grabPixels()) {
                trackFeaturesMapWidth = grabber.getWidth();
                trackFeaturesMapHeight = grabber.getHeight();

                // create space to store the track feature map
                trackFeaturesMap = new TrackFeature[trackFeaturesMapWidth * trackFeaturesMapHeight];

                // get access to the pixels in RGB for
                int[] data = (int[]) grabber.getPixels();

                // convert the track RGB map to the integer
                // tracek feature array. 
                for (int y = 0; y < trackFeaturesMapHeight; y++) {
                    for (int x = 0; x < trackFeaturesMapWidth; x++) {
                        // pull out the next pixel
                        int imgPixel = data[y * trackFeaturesMapWidth + x];

                        // map it's color to what it is
                        switch (imgPixel) {
                            case RGB_GRASS:
                                feature = TrackFeature.GRASS;
                                break;

                            case RGB_BARRIER:
                                feature = TrackFeature.BARRIER;
                                break;

                            case RGB_ROAD:
                                feature = TrackFeature.ROAD;
                                break;

                            case RGB_FINISHLINE:
                                feature = TrackFeature.FINISHLINE;
                                break;

                            case RGB_CHECKPOINT1:
                                feature = TrackFeature.CHECKPOINT1;
                                break;

                            case RGB_CHECKPOINT2:
                                feature = TrackFeature.CHECKPOINT2;
                                break;

                            case RGB_CHECKPOINT3:
                                feature = TrackFeature.CHECKPOINT3;
                                break;

                            case RGB_CHECKPOINT4:
                                feature = TrackFeature.CHECKPOINT4;
                                break;

                            case RGB_CHECKPOINT5:
                                feature = TrackFeature.CHECKPOINT5;
                                break;

                            case RGB_CHECKPOINT6:
                                feature = TrackFeature.CHECKPOINT6;
                                break;

                            case RGB_CHECKPOINT7:
                                feature = TrackFeature.CHECKPOINT7;
                                break;

                            case RGB_CHECKPOINT8:
                                feature = TrackFeature.CHECKPOINT8;
                                break;

                            default:
                                // default to no impact on car when unknown
                                feature = TrackFeature.ROAD;
                                break;
                        }
                        // assign the feature into track feature array
                        trackFeaturesMap[y * trackFeaturesMapWidth + x] = feature;
                    }
                }
            }

            // track feature map loaded
            return true;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
