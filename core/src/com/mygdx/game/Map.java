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
 * @author tatad6701
 */
public class Map {

    // Creating the instant variables 
    private Vector2 position;
    private Texture raceTrack;
    private Rectangle bounds;


    /**
     * Constructor for the first race track
     * @param x position of the race track image
     * @param y position of the race track image
     */
    public Map(float x, float y) {
        position = new Vector2(x, y);
        raceTrack = new Texture("");
    }

    public void setImage(Texture raceTrack) {    
        this.raceTrack = raceTrack;
    }

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
