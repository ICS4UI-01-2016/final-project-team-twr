/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

/**
 *
 * @author brayden
 */
public class CheckPoint {

    private float x;
    private float y;
    private float width;
    private float height;
    private Color color;

    public CheckPoint(float x, float y, float width, float height, Color color) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

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

}
