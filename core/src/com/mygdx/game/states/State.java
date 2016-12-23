/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public abstract class State {
    
    // Create the constants for the camera and the state manager
    private OrthographicCamera cam;
    private StateManager sm;
    
    public State(StateManager sm){
        // Assign the state manager and create the camera
        sm = sm;
        cam = new OrthographicCamera();
    }
    
    // Create all of the abstract methods for a state
    public abstract void render(SpriteBatch batch);
    public abstract void update(float DeltaTime);
    public abstract void handleInput();
    public abstract void dispose();
    
    /**
     * return the orthographic camera
     * @return the camera
     */
    public OrthographicCamera getOrthographicCamera(){
        return cam;
    }
    
    /**
     * return the state manager
     * @return state manager
     */
    public StateManager getStateManager(){
        return sm;
    }
    
    /**
     * set the camera's current view
     * @param width the width of the camera
     * @param height the heigh of the camera
     */
    public void setCameraView(float width, float height){
        cam.setToOrtho(false, width, height);
        cam.update();
    }
    
    /**
     * set the camera's current position
     * @param x the x value of the camera
     * @param y the y value of the camera
     */
    public void setCameraPosition(float x, float y){
        cam.position.x = x;
        cam.position.y = y;
        cam.update();
    }
    
    /**
     * return the combined camera
     * @return the combined camera
     */
    public Matrix4 getCombinedCamera(){
        return cam.combined;
    }
    
    public void moveCameraX(float x){
        cam.position.x = x;
        cam.update();
    }
    
    public void moveCameraY(float y){
        cam.position.y = y;
        cam.update();
    }
    
    public float getCameraX(){
        return cam.position.x;
    }
    
    public float getCameraY(){
        return cam.position.y;
    }
    
    public float getViewWidth(){
        return cam.viewportWidth;
    }
    
    public float getViewHeight(){
        return cam.viewportHeight;
    }
    
    
}
