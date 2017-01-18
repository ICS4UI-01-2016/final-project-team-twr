/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    private Music menuMusic;
    private Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }

    public void push(State s) {
        states.push(s);
    }

    public void pop() {
        State s = states.pop();
        s.dispose();
    }

    public void set(State s) {
        pop();
        push(s);
    }

    // Music code
    /**
     * Constructor for the music within the game
     */
    public void sound() {
        this.menuMusic = Gdx.audio.newMusic(Gdx.files.internal("data/mymusic.mp3"));
    }

    public void play() {
        this.menuMusic.play();
        if (states.peek() instanceof MenuState && states.peek() instanceof DescriptionState) {
            this.menuMusic.play();
        }
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public void handleInput() {
        states.peek().handleInput();

    }
}
