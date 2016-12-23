/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class Map {

    // Creating the instant variables 
    private Vector2 position;
    private Texture raceTrack;
    private Texture raceTrack2;
    private Texture raceTrack3;
    private Texture raceTrack4;
    private Rectangle bounds;

    /**
     * Constructor for the first race track
     *
     * @param x position of the race track image
     * @param y position of the race track image
     */
    public Map(float x, float y) {
        position = new Vector2(x, y);
        raceTrack = new Texture("");
        raceTrack2 = new Texture("");
        raceTrack3 = new Texture("");
        raceTrack4 = new Texture("");
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
        this.raceTrack = raceTrack;
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
     * @return
     */
    public float getX() {
        return position.x;
    }

    // Getting rid of the images! Saves space!
    public void dispose() {
        raceTrack.dispose();
    }
}
