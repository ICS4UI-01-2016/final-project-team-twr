/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class Map {

    // Creating the instant variables 
    private Vector2        position;
    private Texture        raceTrack;
    private Texture        raceTrack2;
    private Texture        raceTrack3;
    private Texture        raceTrack4;
    private Rectangle      bounds;
    private TrackFeature[] boundaryMap;
    private int            boundaryMapWidth;
    private int            boundaryMapHeight;
   
    // constants to determine boundary features from PNG image
    private static final int RGB_ROAD    = 123;
    private static final int RGB_GRASS   = 456;
    private static final int RGB_BARRIER = 789;

    public  enum TrackFeature { ROAD, GRASS, BARRIER }; 

    /**
     * Constructor for the first race track
     *
     * @param x position of the race track image
     * @param y position of the race track image
     */
    public Map(float x, float y) {
        position   = new Vector2(x, y);
        raceTrack  = new Texture("Track1.jpg");
        raceTrack2 = new Texture("Track2.jpg");
        raceTrack3 = new Texture("");
        raceTrack4 = new Texture("");

        loadBoundaryMap( "Track1-Boundaries" );
    }

    /**
     * Method to set the images of the track into the game
     *
     * @param raceTrack is the first track of the game
     * @param racetrack2 is the second track of the game
     * @param racetrack3 is the third track of the game
     * @param racetrack4 is the fourth rack of the game
     */
    public void setImage(Texture raceTrack, Texture racetrack2, Texture racetrack3, Texture racetrack4) {
        this.raceTrack  = raceTrack;
        this.raceTrack2 = raceTrack2;
        this.raceTrack3 = raceTrack3;
        this.raceTrack4 = raceTrack4;
    }

    /**
     * Render method for the map!
     *
     * @param batch used for drawing
     */
    public void render(SpriteBatch batch) {
        batch.draw(raceTrack, position.x, position.y);
    }

    /**
     * Method that gets the X coordinate of the image, the race track
     *
     * @return the x position of the image
     */
    public float getX() {
        return position.x;
    }

   /**
     * Method that loads the boundaries array based on a boundary PNG
     * 
     * @param boundaryPNG
     * @return boolean whether the boundary map could be loaded.
     */
    public boolean loadBoundaryMap( String boundaryPNG ) {
        TrackFeature feature;
        
        Image image = Toolkit.getDefaultToolkit().getImage( boundaryPNG );
         
        try {            
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false);          
            if (grabber.grabPixels()) {
                int boundaryMapWidth  = grabber.getWidth();
                int boundaryMapHeight = grabber.getHeight();

                // get access to the pixels in RGB for
                int[] data = (int[]) grabber.getPixels();
                     
                // convert the boundary RGB map to the integer
                // boundary array. 
                for ( int x=0; x< boundaryMapWidth; x++ ){
                    for ( int y=0; y< boundaryMapHeight; y++ ){
                        switch ( data[x + y * boundaryMapWidth] ) {
                            case RGB_GRASS:
                                feature = TrackFeature.GRASS;
                                break;

                            case RGB_BARRIER:
                                feature = TrackFeature.BARRIER;
                                break;

                            case RGB_ROAD:
                            default: 
                                feature = TrackFeature.ROAD;
                                break;
                        }
                        // assign the feature into boundary array
                        boundaryMap[x + y * boundaryMapWidth] = feature;
                    }
                }
            }
            
            // boundary map loaded
            return true;
        }
        catch (InterruptedException e1) {
            e1.printStackTrace();
            return false;
        }
    }
}
