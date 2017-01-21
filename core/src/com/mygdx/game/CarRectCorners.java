/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import java.lang.Math;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class CarRectCorners {
    public  Vector3 frontLeft;
    public  Vector3 frontRight;
    public  Vector3 backLeft;
    public  Vector3 backRight;
    public  Vector3 front;
    public  Vector3 right;
    public  Vector3 left;
    public  Vector3 back;
    private int carWidth;
    private int carHeight;
//    public Rectangle front;
//    public Rectangle back;
//    public Rectangle left;
//    public Rectangle right;
    public Circle frontC;
    public Circle backC;
    public Circle leftC;
    public Circle rightC;
    public Circle frontLeftCornerC;
    public Circle backLeftCornerC;
    public Circle frontRightCornerC;
    public Circle backRightCornerC;
    
    public CarRectCorners( int width, int height ) {
        front     = new Vector3(0,0,0);
        back    = new Vector3(0,0,0);
        left  = new Vector3(0,0,0);
        right = new Vector3(0,0,0);
        
        frontLeft     = new Vector3(0,0,0);
        frontRight    = new Vector3(0,0,0);
        backLeft  = new Vector3(0,0,0);
        backRight = new Vector3(0,0,0);
       
        frontC = new Circle(0,0,8); 
        backC = new Circle(0,0,8); 
        leftC = new Circle(0,0,8);
        rightC = new Circle(0,0,8);
        
        frontLeftCornerC = new Circle(0,0,8); 
        backLeftCornerC = new Circle(0,0,8); 
        frontRightCornerC = new Circle(0,0,8);
        backRightCornerC = new Circle(0,0,8);
        
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
        
        backLeftCornerC.x = carPos.x - carWidth/2;
        backLeftCornerC.y = carPos.y - carHeight/2;

        backRightCornerC.x = carPos.x + carWidth/2;
        backRightCornerC.y = carPos.y - carHeight/2;

        frontLeftCornerC.x = carPos.x - carWidth/2;
        frontLeftCornerC.y = carPos.y + carHeight/2;

        frontRightCornerC.x = carPos.x + carWidth/2;
        frontRightCornerC.y = carPos.y + carHeight/2;
        
        frontC.x = carPos.x;
        frontC.y = carPos.y + carHeight/2;
        
        backC.x = carPos.x;
        backC.y = carPos.y - carHeight/2;

        leftC.x = carPos.x - carWidth/2;
        leftC.y = carPos.y;
        
        rightC.x = carPos.x + carWidth/2;
        rightC.y = carPos.y;


   
        // with new cars corners computed, rotate them according 
        // to the cars current rotation
        backLeft  = rotatePoint( backLeft, rotation,  carPos);
        backRight = rotatePoint( backRight, rotation, carPos);
        frontLeft     = rotatePoint( frontLeft, rotation,  carPos );
        frontRight    = rotatePoint( frontRight, rotation, carPos);
        
        backLeftCornerC  = rotateCircleAroundCarCentre( backLeftCornerC, rotation,  carPos);
        backRightCornerC = rotateCircleAroundCarCentre( backRightCornerC, rotation, carPos);
        frontLeftCornerC     = rotateCircleAroundCarCentre( frontLeftCornerC, rotation,  carPos );
        frontRightCornerC   = rotateCircleAroundCarCentre( frontRightCornerC, rotation, carPos);
        
        
        frontC    = rotateCircleAroundCarCentre( frontC, rotation,  carPos);
        backC     = rotateCircleAroundCarCentre( backC, rotation, carPos);
        leftC     = rotateCircleAroundCarCentre( leftC, rotation,  carPos );
        rightC    = rotateCircleAroundCarCentre( rightC, rotation, carPos);
    }
    
//    public Rectangle getFront(){
//        return front = new Rectangle(frontLeft.x, frontLeft.y, carWidth, 10);
//    }
//    
//    public Rectangle getBack(){
//        return back = new Rectangle(backLeft.x, backLeft.y, carWidth, 10);
//    }
//    
//    public Rectangle getLeft(){
//        return left = new Rectangle(backLeft.x, backLeft.y, 10, carHeight);
//    }
//        
//    public Rectangle getRight(){
//        return right = new Rectangle(backRight.x, backRight.y, 10, carHeight);
//    }
    
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
     
    private Circle rotateCircleAroundCarCentre( Circle circle, float rotation, Vector3 carCentre ) {
        rotation = rotation * (float)Math.PI / 180f;
        
        double s = Math.sin( (double)rotation );
        double c = Math.cos( (double)rotation );

        // translate point back to origin:
        circle.x -= carCentre.x;
        circle.y -= carCentre.y;

        // rotate point
        float xnew = (int) ((double)circle.x * c - (double)circle.y * s);
        float ynew = (int) ((double)circle.x * s + (double)circle.y * c);

        // translate point back:
        circle.x = xnew + carCentre.x;
        circle.y = ynew + carCentre.y;

        return circle;
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
    