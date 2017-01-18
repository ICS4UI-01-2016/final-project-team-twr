/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.io.File;

/**
 *
 * @author Denis
 */
public class Sound {

    // Create instance variables
    private Music menuMusic;
    private StateManager sm;

    /**
     * Constructor for the sounds class
     */
    public Sound() {
        this.menuMusic = Gdx.audio.newMusic(Gdx.files.internal("data/mymusic.mp3"));
    }

    public void play() {
    }
}