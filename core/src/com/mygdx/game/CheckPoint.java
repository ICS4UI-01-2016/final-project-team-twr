/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class CheckPoint {

    private float x;
    private float y;
    private float width;
    private float height;
    private Color color;
    private Rectangle bounds; 

    public CheckPoint(float x, float y, float width, float height, Color color) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        bounds = new Rectangle(x, y, width, height);

    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }
    
    public void setColor(Color color){
        this.color = color;
    }
}
