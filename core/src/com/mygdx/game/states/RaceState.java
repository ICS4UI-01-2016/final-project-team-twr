/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Car;
import com.mygdx.game.CheckPoint;
import com.mygdx.game.RaceIt;
import java.util.ArrayList;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class RaceState extends State {

    // Create the constant variables
    private final Car car1;
    private final Car car2;
    private Texture bg;
    private boolean accelerate;
    private boolean stop;
    private boolean turnLeft;
    private boolean turnRight;
    private float velocity;
    private float speedX;
    private float speedY;
    private int track = 1;
    private ArrayList<CheckPoint> checkPoints;
    private final CheckPoint cp1;
    private final CheckPoint cp2;
    private final CheckPoint cp3;
    private final CheckPoint cp4;
    private final CheckPoint cp5;
    private final CheckPoint cp6;
    private final CheckPoint cp7;
    private final CheckPoint cp8;
    private final CheckPoint cp9;
    private final CheckPoint cp10;
    private final CheckPoint cp11;
    private final CheckPoint cp12;
    private final CheckPoint cp13;
    private final CheckPoint cp14;
    private final CheckPoint cp15;
    private final CheckPoint cp16;
    private final CheckPoint cp17;
    private final CheckPoint cp18;
    private final CheckPoint cp19;
    private final CheckPoint cp20;
    private final CheckPoint cp21;
    private final CheckPoint cp22;
    private final CheckPoint cp23;
    private final CheckPoint cp24;
    private final CheckPoint cp25;
    private final CheckPoint cp26;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    
    
    /**
     * Constructor for the race state
     *
     * @param sm
     */
    public RaceState(StateManager sm) {
        super(sm);
        setCameraView(RaceIt.WIDTH /2, RaceIt.HEIGHT / 2);
        if(track == 1){
            car1 = new Car(600, 400, 2, 270, 207, RaceIt.HEIGHT - 190);
            car2 = new Car(600, 400, 4, 270, 160, RaceIt.HEIGHT - 145);
            bg = new Texture("Track1.jpg");
        } else{
            car1 = new Car(0, 0, 0, 0, 0, 0);
            car2 = new Car(0, 0, 0, 0, 0, 0);
        }
        setCameraPosition(250, 750);
        
        for(int i = 0; i < 13; i++){
            
        }
        cp1 = new CheckPoint(720, 725, 20, 100, Color.WHITE);
        cp2 = new CheckPoint(690, 725, 20, 100, Color.WHITE);
        
        cp3 = new CheckPoint(815, 450, 100, 20, Color.WHITE);
        cp4 = new CheckPoint(815, 480, 100, 20, Color.WHITE);
        
        cp5 = new CheckPoint(550, 450, 100, 20, Color.WHITE);
        cp6 = new CheckPoint(550, 480, 100, 20, Color.WHITE);
        
        cp7 = new CheckPoint(550, 550, 100, 20, Color.WHITE);
        cp8 = new CheckPoint(550, 580, 100, 20, Color.WHITE);
        
        cp9 = new CheckPoint(295, 550, 100, 20, Color.WHITE);
        cp10 = new CheckPoint(295, 580, 100, 20, Color.WHITE);
        
        cp11 = new CheckPoint(295, 320, 100, 20, Color.WHITE);
        cp12 = new CheckPoint(295, 290, 100, 20, Color.WHITE);
        
        cp13 = new CheckPoint(540, 150, 20, 70, Color.WHITE);
        cp14 = new CheckPoint(510, 150, 20, 70, Color.WHITE);
        
        cp15 = new CheckPoint(810, 150, 20, 70, Color.WHITE);
        cp16 = new CheckPoint(780, 150, 20, 70, Color.WHITE);
        
        cp17 = new CheckPoint(810, 50, 20, 70, Color.WHITE);
        cp18 = new CheckPoint(780, 50, 20, 70, Color.WHITE);
        
        cp19 = new CheckPoint(195, 50, 20, 70, Color.WHITE);
        cp20 = new CheckPoint(225, 50, 20, 70, Color.WHITE);
        
        cp21 = new CheckPoint(50, 170, 90, 20, Color.WHITE);
        cp22 = new CheckPoint(50, 200, 90, 20, Color.WHITE);
        
        cp23 = new CheckPoint(50, 690, 90, 20, Color.WHITE);
        cp24 = new CheckPoint(50, 660, 90, 20, Color.WHITE);
        
        cp25 = new CheckPoint(290, 775, 20, 50, Color.WHITE);
        cp26 = new CheckPoint(290, 725, 20, 50, Color.WHITE);
        
        
        checkPoints = new ArrayList<CheckPoint>();
        
        
    }

    // Comment this!
    public void render(SpriteBatch batch) {
        shapeRenderer.setProjectionMatrix(getCombinedCamera());
        // Draw the screen 
        batch.setProjectionMatrix(getCombinedCamera());
        // Begin he drawing 
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth() * 2, getViewHeight() * 2);
        batch.end();
        
        cp1.render(shapeRenderer);
        cp2.render(shapeRenderer);
        cp3.render(shapeRenderer);
        cp4.render(shapeRenderer);
        cp5.render(shapeRenderer);
        cp6.render(shapeRenderer);
        cp7.render(shapeRenderer);
        cp8.render(shapeRenderer);
        cp9.render(shapeRenderer);
        cp10.render(shapeRenderer);
        cp11.render(shapeRenderer);
        cp12.render(shapeRenderer);
        cp13.render(shapeRenderer);
        cp14.render(shapeRenderer);
        cp15.render(shapeRenderer);
        cp16.render(shapeRenderer);
        cp17.render(shapeRenderer);
        cp18.render(shapeRenderer);
        cp19.render(shapeRenderer);
        cp20.render(shapeRenderer);
        cp21.render(shapeRenderer);
        cp22.render(shapeRenderer);
        cp23.render(shapeRenderer);
        cp24.render(shapeRenderer);
        cp25.render(shapeRenderer);
        cp26.render(shapeRenderer);
        
        
        batch.begin();
        car1.render(batch);
        car2.render(batch);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        System.out.println(car1.getX());
        System.out.println(car1.getY());
        moveCameraX(car1.getX());
        if(!(getCameraX() > 250)){
            moveCameraX(250);
        }

        if(!(getCameraX() < 750)){
            moveCameraX(750);
        }
        moveCameraY(car1.getY());
        if(!(getCameraY() < 675)){
            moveCameraY(675);
        }

        if(!(getCameraY() > 225)){
            moveCameraY(225);
        }
        
        car1.update(deltaTime);
        car2.update(deltaTime);
        
        if(!car1.crashed() && !car2.crashed()){
//            car1.collides(car2);
//            car2.collides(car1);
        }
        
        for(int i = 0; i > 26; i++){
            if(!(cp1.getColor()== Color.RED)){
                if(car1.getBounds().overlaps(cp1.getBounds())){
                    cp1.setColor(Color.RED);
                }   
            }
        }
            
    }

    @Override
    public void handleInput() {
        
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            car1.acceleratorPedal(true);
        } else{
            car1.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            car1.turnLeft(true);
        }else{
            car1.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            car1.turnRight(true);
        }else{
            car1.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            car1.brakePedal(true);
        } else{
            car1.brakePedal(false);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            car2.acceleratorPedal(true);
        } else{
            car2.acceleratorPedal(false);
        }
    
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            car2.turnLeft(true);
        }else{
            car2.turnLeft(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            car2.turnRight(true);
        }else{
            car2.turnRight(false);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            car2.brakePedal(true);
        } else{
            car2.brakePedal(false);
        }
    }
        
    @Override
    public void dispose() {
        car1.dispose();
        car2.dispose();
    }
}
