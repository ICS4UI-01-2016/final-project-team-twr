/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import java.lang.Math;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author richarb
 */
public class CarRectCorners {
    public  Vector3 frontLeft;
    public  Vector3 frontRight;
    public  Vector3 backLeft;
    public  Vector3 backRight;
    private int carWidth;
    private int carHeight;
    
    public CarRectCorners( int width, int height ) {
        frontLeft     = new Vector3(0,0,0);
        frontRight    = new Vector3(0,0,0);
        backLeft  = new Vector3(0,0,0);
        backRight = new Vector3(0,0,0);
       
        carWidth  = width;
        carHeight = height;
        
        // compute the initial corners at position 0,0
        Vector3 pos = new Vector3(0,0,0);
        updateCornerPos( pos, 0);   
    }

   
                
    public void updateCornerPos( Vector3 carPos, float rotation) {
        // compute the car's 4 corners without rotation
        // then rotate them and then offset them
        // based on the new cars position
        backLeft.x = carPos.x - carWidth/2;
        backLeft.y = carPos.y - carHeight/2;

        backRight.x = carPos.x + carWidth/2;
        backRight.y = carPos.y - carHeight/2;

        frontLeft.x = carPos.x - carWidth/2;
        frontLeft.y = carPos.y + carHeight/2;

        frontRight.x = carPos.x + carWidth/2;
        frontRight.y = carPos.y + carHeight/2;
   
        // with new cars corners computed, rotate them according 
        // to the cars current rotation
        backLeft  = rotatePoint( backLeft, rotation,  carPos);
        backRight = rotatePoint( backRight, rotation, carPos);
        frontLeft     = rotatePoint( frontLeft, rotation,  carPos );
        frontRight    = rotatePoint( frontRight, rotation, carPos);
    }
    
     private Vector3 rotatePoint( Vector3 pt, float rotation, Vector3 origin ) {
        rotation = rotation * (float)Math.PI / 180f;
        
        double s = Math.sin( (double)rotation );
        double c = Math.cos( (double)rotation );

        // translate point back to origin:
        pt.x -= origin.x;
        pt.y -= origin.y;

        // rotate point
        float xnew = (int) ((double)pt.x * c - (double)pt.y * s);
        float ynew = (int) ((double)pt.x * s + (double)pt.y * c);

        // translate point back:
        pt.x = xnew + origin.x;
        pt.y = ynew + origin.y;

        return pt;
    }
    
/*
    POINT rotate_point(float cx,float cy,float angle,POINT p){
        float s = sin(angle);
        float c = cos(angle);

        // translate point back to origin:
        p.x -= cx;
        p.y -= cy;

        // rotate point
        float xnew = p.x * c - p.y * s;
        float ynew = p.x * s + p.y * c;

        return p;
    }
*/
     
}
    