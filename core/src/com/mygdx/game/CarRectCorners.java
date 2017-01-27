/*  
 * This calss deals with the corners of the car, for player 1 and player 2! This class deals with intersections
 * between objects as well.
 * 
 * 
 */
package com.mygdx.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

/**
 * @author whitb0039, richj0985, and tatad6701
 */

/*
 * CarRectCorners class - class used to hold and compute all points 
 *     along the outside of edge of the car.   The points are used
 *     to determine when the car is contact with the opponent car
 *     or other objects on the track and in the world.
 */
public class CarRectCorners {

    private int carWidth;
    private int carHeight;
    public Circle front;       // front of car (in middle)
    public Circle right;       // right sode of car (in middle)
    public Circle left;        // left side of car (in middle)
    public Circle back;        // back side of car (in middle)
    public Circle frontLeft;   // front left corner of the car
    public Circle frontRight;  // front rightt corner of the car
    public Circle backLeft;    // back left corner of the car 
    public Circle backRight;   // back right corner of the car

    /**
     * Constructor method for the car rectangle corners
     *
     * @param width
     * @param height
     */
    public CarRectCorners(int width, int height) {
        //
        // Initialize the circles that will be used to hold the points 
        // along the ended of the car.   The circles are given 
        // a small radius to allow the circles to be tested with
        // the overlap method in order to determine when the car
        // has contact with other objects in the world
        front = new Circle(0, 0, 8);
        back = new Circle(0, 0, 8);
        left = new Circle(0, 0, 8);
        right = new Circle(0, 0, 8);
        frontLeft = new Circle(0, 0, 8);
        frontRight = new Circle(0, 0, 8);
        backLeft = new Circle(0, 0, 8);
        backRight = new Circle(0, 0, 8);

        // Capture the width and height of the car to be used
        // when updating the car corner.
        carWidth = width;
        carHeight = height;

        // compute the initial corners at position 0,0
        Vector3 pos = new Vector3(0, 0, 0);
        updateCornerPos(pos, 0);
    }

    /**
     * Method used to update all of the computed points on the outside edges of
     * the car. These points are used to determine collisions and contact of the
     * car with the opposing car or with objects on the track.
     *
     * @param carPos - position of the car in world in terms of center of the
     * car
     * @param rotation - current rotation value of car in world (0-360)
     */
    public void updateCornerPos(Vector3 carPos, float rotation) {
        //
        // compute the car's 4 corners.   The points will 
        // be used to determine intersection with 
        // the opponent car and also with objects on the track.
        //
        backLeft.x = carPos.x - carWidth / 2;
        backLeft.y = carPos.y - carHeight / 2;

        backRight.x = carPos.x + carWidth / 2;
        backRight.y = carPos.y - carHeight / 2;

        frontLeft.x = carPos.x - carWidth / 2;
        frontLeft.y = carPos.y + carHeight / 2;

        frontRight.x = carPos.x + carWidth / 2;
        frontRight.y = carPos.y + carHeight / 2;

        //
        // Compute a center points on the front, 
        // back, right and left sides of the car
        // The points are used to determine the rough
        // 4 outside edges of the car
        //
        back.x = carPos.x;
        back.y = carPos.y - carHeight / 2;

        front.x = carPos.x;
        front.y = carPos.y + carHeight / 2;

        right.x = carPos.x + carWidth / 2;
        right.y = carPos.y;

        left.x = carPos.x - carWidth / 2;
        left.y = carPos.y;

        //
        // Adjust all points on the car based on the current rotation 
        // of the car.   The allowed rotations of the car are
        // 0-360 direction on the track.
        //
        backLeft = rotateCircleAroundCarCentre(backLeft, rotation, carPos);
        backRight = rotateCircleAroundCarCentre(backRight, rotation, carPos);
        frontLeft = rotateCircleAroundCarCentre(frontLeft, rotation, carPos);
        frontRight = rotateCircleAroundCarCentre(frontRight, rotation, carPos);

        front = rotateCircleAroundCarCentre(front, rotation, carPos);
        back = rotateCircleAroundCarCentre(back, rotation, carPos);
        right = rotateCircleAroundCarCentre(right, rotation, carPos);
        left = rotateCircleAroundCarCentre(left, rotation, carPos);
    }

    /**
     * Method used to compute new coordinates of a circle/point in the world
     * based on the current rotation of the car in the world
     *
     * @param circle - point to be rotated around the center of the car
     * @param rotation - current rotation of the car in the world
     * @param carCentre - x,y coordinates of the center of the car in the world
     */
    private Circle rotateCircleAroundCarCentre(Circle circle, float rotation, Vector3 carCentre) {
        rotation = rotation * (float) Math.PI / 180f;

        double s = Math.sin((double) rotation);
        double c = Math.cos((double) rotation);

        // translate point back to origin:
        circle.x -= carCentre.x;
        circle.y -= carCentre.y;

        // rotate point
        float xnew = (int) ((double) circle.x * c - (double) circle.y * s);
        float ynew = (int) ((double) circle.x * s + (double) circle.y * c);

        // translate point back:
        circle.x = xnew + carCentre.x;
        circle.y = ynew + carCentre.y;

        return circle;
    }
}
