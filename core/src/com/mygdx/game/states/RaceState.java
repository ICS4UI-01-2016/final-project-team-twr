/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class RaceState extends State {

    // Create the constant variables
    private final Car car1;
    private final Car car2;
    private Texture bg;

    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private float velocity;
    private float speedX;
    private float speedY;
    private int track;
    private int carType1;
    private int carType2;
    private float PlayTime;
    private float countTimer;

    private TrackFeature[] boundaryMap;
    private int boundaryMapWidth;
    private int boundaryMapHeight;

    private StateManager sm;
    private Sound countDown;
    private boolean countDownOn = false;

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
        setCameraView(RaceIt.WIDTH / 4, RaceIt.HEIGHT / 2);
        this.track = track;
        this.carType1 = car1Type;
        this.carType2 = car2Type;
        PlayTime = 0.0f;
        countTimer = 0;
        this.sm = sm;
        countDown = Gdx.audio.newSound(Gdx.files.internal("321.mp3"));
        if (track == 1) {
            car1 = new Car(600, 400, carType2, 270, 220, 800);
            car2 = new Car(600, 400, carType1, 270, 220, 750);
            bg = new Texture("Track1.jpg");
            loadBoundaryMap("Track1-boundaries.png");
        } else {
            car1 = new Car(600, 400, carType2, 0, 855, 535);
            car2 = new Car(600, 400, carType1, 0, 930, 535);
            bg = new Texture("Track2.1.jpg");
            loadBoundaryMap("Track2.1-boundaries.png");
        }

    }

    public void moveCamView(SpriteBatch batch, Car car) {
        // Update the camera view
        moveCameraX(car.getX());
        //System.out.println( "cam(" + getCameraX(camera) + "," + getCameraY(camera) + "+" );
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

    // Comment this!
    public void render(SpriteBatch batch) {

        setCameraView(RaceIt.WIDTH / 4, RaceIt.HEIGHT / 2);

        //
        // Render the view for Car1
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
        car1.renderHUD(this, batch, PlayTime);

        //
        // Render the view for Car2
        //
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
        car2.renderHUD(this, batch, PlayTime);

    }

    @Override
    public void update(float deltaTime) {
        countTimer += Gdx.graphics.getDeltaTime();
        if(countTimer > 0 && !countDownOn){
            sm.play();
            countDown.play();
            countDownOn = true;
        }
        
        if (countTimer > 6) {
            PlayTime += deltaTime;

            car1.update(deltaTime, this);
            car2.update(deltaTime, this);
            StateManager gsm = getStateManager();

            if (car1.getLap() == 6) {
                gsm.push(new WinnerState(gsm, carType1, 1));
            } else if (car2.getLap() == 6) {
                gsm.push(new WinnerState(gsm, carType2, 2));
            }

            car1.checkCollision(car2);
            car2.checkCollision(car1);
        }

    }

    @Override
    public void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            car1.acceleratorPedal(true);

        } else {
            car1.acceleratorPedal(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            car1.turnLeft(true);
        } else {
            car1.turnLeft(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            car1.turnRight(true);
        } else {
            car1.turnRight(false);
        }
            
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            car1.brakePedal(true);
        } else {
            car1.brakePedal(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            car1.nitroPushed(true);
        } else {
            car1.nitroPushed(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            car2.acceleratorPedal(true);
        } else {
            car2.acceleratorPedal(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            car2.turnLeft(true);
        } else {
            car2.turnLeft(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            car2.turnRight(true);
        } else {
            car2.turnRight(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            car2.brakePedal(true);
        } else {
            car2.brakePedal(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            car2.nitroPushed(true);
        } else {
            car2.nitroPushed(false);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
            sm.push(new PauseState(sm, track, carType1, carType2));
        }
    }

    @Override
    public void dispose() {
        car1.dispose();
        car2.dispose();
    }

// constants to determine boundary features from PNG image
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

    public TrackFeature getTrackFeatureAtPt(Vector3 corner) {
        // map from the game coordinate to the boundary map coordinate
        float x = (corner.x / RaceIt.WIDTH) * boundaryMapWidth;
        float y = (corner.y / RaceIt.HEIGHT) * boundaryMapHeight;

        if (x > 0 && y > 0 && x < boundaryMapWidth && y < boundaryMapHeight) {
            return boundaryMap[(int) x + (int) (boundaryMapHeight - y) * boundaryMapWidth];
        } else {
            return TrackFeature.UNKNOWN;
        }
    }

    public TrackFeature getInterestingTrackFeatureCorners(CarRectCorners carRect) {
        TrackFeature feature;
        TrackFeature returnFeature;

        // Get the track feature at each corner of the car and return
        // the highest feature value since they are ordered in most 
        // interesting order
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
     * Method that loads the boundaries array based on a boundary PNG
     *
     * @param boundaryPNG
     * @return boolean whether the boundary map could be loaded.
     */
    public boolean loadBoundaryMap(String boundaryPNG) {
        TrackFeature feature;

        Image image = Toolkit.getDefaultToolkit().getImage(boundaryPNG);

        try {
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false);
            if (grabber.grabPixels()) {
                boundaryMapWidth = grabber.getWidth();
                boundaryMapHeight = grabber.getHeight();

                // create space to store the boundary map
                boundaryMap = new TrackFeature[boundaryMapWidth * boundaryMapHeight];

                // get access to the pixels in RGB for
                int[] data = (int[]) grabber.getPixels();

                // convert the boundary RGB map to the integer
                // boundary array. 
                for (int y = 0; y < boundaryMapHeight; y++) {
                    for (int x = 0; x < boundaryMapWidth; x++) {
                        // pull out the next pixel
                        int imgPixel = data[y * boundaryMapWidth + x];

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
                        // assign the feature into boundary array
                        boundaryMap[y * boundaryMapWidth + x] = feature;
                    }
                }
                for (int y = 0; y < boundaryMapHeight; y += 30) {
                    for (int x = 0; x < boundaryMapWidth; x += 30) {
                        TrackFeature imgPixel = boundaryMap[x + y * boundaryMapWidth];
                        if (imgPixel == TrackFeature.GRASS) {
                            System.out.print("x");
                        } else {
                            System.out.print(".");
                        }

                    }
                    System.out.println("x");
                }
                int test = 1;    // need a line to put breakpoint on
            }

            // boundary map loaded
            return true;
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
