/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.RaceIt;

/**
 *
 * @author whitb0039, richj0985, and tatad6701
 */
public class MenuState extends State {
    // Create the instance variables 

    private StateManager sm;
    private Texture bg;
    private Texture musicPlay;
    private Texture musicMute;
    private Rectangle muteButton;
    private Music music;
    private boolean mute;

    public MenuState(StateManager sm) {
        super(sm);
        bg = new Texture("bg.jpg");
        musicPlay = new Texture("playMusic.png");
        musicMute = new Texture("muteMusic.png");
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        mute = false;

        muteButton = new Rectangle(0, 0, 50, 50);

        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        music.play();
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        if (!mute) {
            batch.draw(musicPlay, muteButton.x, muteButton.y, muteButton.width, muteButton.height);
        } else {
            batch.draw(musicMute, muteButton.x, muteButton.y, muteButton.width, muteButton.height);
        }
        batch.end();
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        unproject(touch);
        if (Gdx.input.justTouched()) {
            if (touch.x >= muteButton.x && touch.x <= muteButton.x + muteButton.width
                    && touch.y >= muteButton.y && touch.y <= muteButton.y + muteButton.height) {
                if (mute) {
                    music.play();
                    mute = false;
                } else {
                    music.pause();
                    mute = true;
                }
            }
        }

        //sm = getStateManager();
        //sm.push(new ChooseAmountPlayersState(sm));

    }

    @Override
    public void dispose() {
        bg.dispose();
        music.dispose();
    }
}
