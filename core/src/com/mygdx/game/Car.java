/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author tatad6701
 */
public class Car {
    private Vector3 position;
    private Vector3 velocity;
    private Texture carPic;
    private Rectangle bounds;
    private final float HORIZONTAL = 100;
    private final float VERTICAL = 100;
    
    public Car(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(HORIZONTAL,0,0);
        carPic = new Texture("lamborghiniblack.png");
        bounds = new Rectangle(position.x,position.y, carPic.getWidth(), carPic.getHeight());
    }
    
    public void update(float deltaTime){
        // add gravity
        velocity.y += HORIZONTAL;
        // scaling velocity by time
        velocity.scl(deltaTime);
        // adding velocity to position
        position.add(velocity);
        // unscale velocity
        velocity.scl(1/deltaTime);
        
        bounds.setPosition(position.x, position.y);
    }
    
    public void render(SpriteBatch batch){
        batch.draw(carPic, position.x, position.y);
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
    
    public void dispose(){
        carPic.dispose();
    }
    
}
