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
 * @author whitb0039, richj0985, and tatad6701
 */
public class Car {
    private float rotation;
    private Vector3 origin;
    private Vector3 position;
    private Vector3 velocity;
    private TextureRegion carPic;
    private Rectangle bounds;
    
    public Car(int x, int y){
        position = new Vector3(x,y,0);
        System.out.println("HERE");
        carPic = new TextureRegion(new Texture("lamborghiniblack.png"));
        bounds = new Rectangle(position.x,position.y, carPic.getRegionWidth(), carPic.getRegionHeight());
    }
    
    public void update(float deltaTime){
        bounds.setPosition(position.x, position.y);
    }
    
    public void render(SpriteBatch batch){
        batch.draw(carPic, position.x, position.y, carPic.getRegionWidth() / 5, carPic.getRegionHeight() / 5, carPic.getRegionWidth() / 6, carPic.getRegionHeight() / 6, 1, 1, rotation);
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
    
    public void turn (float degree){
        rotation += degree;
    }
    
    public float getRotation(){
        while(rotation > 360 ){
            rotation = rotation - 360;
        }
        
        while(rotation < 0){
            rotation = rotation + 360;
        }
        return rotation;
    }
    
    public void drive(float xSpeed, float ySpeed){
        position.x += xSpeed;
        position.y += ySpeed;
    }
    
    public void intersection(){
        
    }
}
