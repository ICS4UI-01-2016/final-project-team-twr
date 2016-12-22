/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author tatad6701
 */
public class Car {
    private float rotation;
    private Vector3 origin;
    private Vector3 position;
    private Vector3 velocity;
    private TextureRegion carPic;
    private Rectangle bounds;
    private final float HORIZONTAL = 100;
    private final float VERTICAL = 100;
    
    public Car(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(HORIZONTAL,0,0);
        System.out.println("HERE");
        carPic = new TextureRegion(new Texture("lamborghiniblack.png"));
        bounds = new Rectangle(position.x,position.y, carPic.getRegionWidth(), carPic.getRegionHeight());
    }
    
    public void update(float deltaTime){
//        // add gravity
//        velocity.y += HORIZONTAL;
//        // scaling velocity by time
//        velocity.scl(deltaTime);
//        // adding velocity to position
//        position.add(velocity);
//        // unscale velocity
//        velocity.scl(1/deltaTime);
        
        bounds.setPosition(position.x, position.y);
    }
    
    public void render(SpriteBatch batch){
        batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 2, carPic.getRegionHeight() / 2, carPic.getRegionWidth() / 3, carPic.getRegionHeight() / 3, 1, 1, rotation);
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
        carPic.getTexture().dispose();
    }
    
    public void rotate(float degree){
        rotation += degree;
    }
    
}
