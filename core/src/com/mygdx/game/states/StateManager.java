/*
 * The state manager deals with switching between the state within the game!
 * 
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class StateManager {

    // Create instance variables
    private Music song;
    private Stack<State> states;

    /**
     * Method to construct the state manager
     */
    public StateManager() {
        states = new Stack<State>();
    }

    /**
     * Method to move between states (pushing)
     *
     * @param s
     */
    public void push(State s) {
        states.push(s);
    }

    /**
     * Method to move between states (popping)
     */
    public void pop() {
        State s = states.pop();
        s.dispose();
    }

    /**
     * Method to set states
     *
     * @param s
     */
    public void set(State s) {
        pop();
        push(s);
    }

    // Music code
    /**
     * Constructor for the music within the game
     */
    public void play() {
        // Make the music stop
        this.song.stop();
        // Import the file (song)
        this.song = Gdx.audio.newMusic(Gdx.files.internal("RaceMusic.mp3"));
        // Start the music loop
        this.song.setLooping(true);
        // Play the song
        this.song.play();
    }

    /**
     * Play the victory music
     */
    public void end() {
        // Import the music file
        this.song = Gdx.audio.newMusic(Gdx.files.internal("Victory Sound Effect.mp3"));
        // Play the music file 
        this.song.play();
    }

    /**
     * Play the menu music
     */
    public void startMusic() {
        // Import the music file 
        this.song = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        // Start the music loop
        this.song.setLooping(true);
        // Play the music 
        this.song.play();
    }

    /**
     * Stop playing current music
     */
    public void stopMusic() {
        // Stop the music
        this.song.stop();
    }

    /**
     * Method that updates the states
     *
     * @param deltaTime
     */
    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    /**
     *
     * Method that renders the states
     *
     * @param batch
     */
    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    /**
     * Method that handles the input of the states
     */
    public void handleInput() {
        states.peek().handleInput();
    }
}
