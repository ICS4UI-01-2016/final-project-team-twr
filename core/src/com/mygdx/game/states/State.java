/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public abstract class State {

    // Create the constants for the camera and the state manager
    private OrthographicCamera cam1;
    private StateManager sm;

    public State(StateManager sm) {
        // Assign the state manager and create the camera
        this.sm = sm;
        cam1 = new OrthographicCamera();
    }

    // Create all of the abstract methods for a state
    public abstract void render(SpriteBatch batch);

    public abstract void update(float DeltaTime);

    public abstract void handleInput();

    public abstract void dispose();

    /**
     * return the orthographic camera
     *
     * @return the camera
     */
    public OrthographicCamera getOrthographicCamera() {
        return cam1;
    }

    /**
     * return the state manager
     *
     * @return state manager
     */
    public StateManager getStateManager() {
        return sm;
    }

    /**
     * set the camera's current view
     *
     * @param width the width of the camera
     * @param height the heigh of the camera
     */
    public void setCameraView(float width, float height) {
        cam1.setToOrtho(false, width, height);
        cam1.update();
    }

    /**
     * set the camera's current position
     *
     * @param x the x value of the camera
     * @param y the y value of the camera
     */
    public void setCameraPosition(float x, float y) {
        cam1.position.x = x;
        cam1.position.y = y;
        cam1.update();
    }

    /**
     * return the combined camera
     *
     * @return the combined camera
     */
    public Matrix4 getCombinedCamera() {
        return cam1.combined;
    }

    /**
     * move the camera's x position
     *
     * @param x the x position
     */
    public void moveCameraX(float x) {
        cam1.position.x = x;
        cam1.update();
    }

    /**
     * move the camera's y position
     *
     * @param y the y position
     */
    public void moveCameraY(float y) {
        cam1.position.y = y;
        cam1.update();
    }

    /**
     * get the camera's x position
     *
     * @return the camera's x position
     */
    public float getCameraX() {
        return cam1.position.x;
    }

    /**
     * get the camera's y position
     *
     * @return the camera's y position
     */
    public float getCameraY() {
        return cam1.position.y;
    }

    /**
     * get the camera's view width
     * @return the camera's view width
     */
    public float getViewWidth() {
        return cam1.viewportWidth;
    }

    /**
     * get the camera's view height
     * @return the camera's view height
     */
    public float getViewHeight() {
        return cam1.viewportHeight;
    }

    /**
     * unproject the touch coordinates
     * @param touch the touch coordinates
     */
    public void unproject(Vector3 touch) {
        cam1.unproject(touch);
    }
}
