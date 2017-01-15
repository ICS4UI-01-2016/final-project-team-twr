/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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
    private Texture title;
    private Rectangle muteButton;
    private Music music;
    private boolean mute;
    // Description instance variables
    private Texture picOfDescriptionButton;
    private Rectangle descriptionButton;
    // How To Play instance variables
    private Texture picOfHowToPlayButton;
    private Rectangle howToPlayButton;
    private BitmapFont font;

    public MenuState(StateManager sm) {
        super(sm);
        // Create the background texture
        bg = new Texture("MenuState.jpg");
        // Create the music play button texture
        musicPlay = new Texture("playMusic.png");
        // Create the music mute button
        musicMute = new Texture("muteMusic.png");
        // Description Button instance variables
        picOfDescriptionButton = new Texture("blackrectangle.png");
        // Create the rectangle behind the description button option 
        descriptionButton = new Rectangle(679, 409, 260, 50);

        picOfHowToPlayButton = new Texture("blackrectangle.png");
        howToPlayButton = new Rectangle(68, 409, 260, 50);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Se the mute boolean to be false
        mute = false;
        // Place the mute button 
        muteButton = new Rectangle(0, 0, 50, 50);
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        music.play();
        font = new BitmapFont();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        // Beginning the drawings
        batch.begin();
        // Drawing the rectangle behind the How To Play option
        batch.draw(picOfHowToPlayButton, howToPlayButton.x, howToPlayButton.y, howToPlayButton.width, howToPlayButton.height);
        // Drawing the rectangle behind the description option
        batch.draw(picOfDescriptionButton, descriptionButton.x, descriptionButton.y, descriptionButton.width, descriptionButton.height);
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());

        // If the mute button is not clicked
        if (!mute) {
            // Draw the playing mute button
            batch.draw(musicPlay, muteButton.x, muteButton.y, muteButton.width, muteButton.height);
        } else {
            // if not, then draw the stopped mute button
            batch.draw(musicMute, muteButton.x, muteButton.y, muteButton.width, muteButton.height);
        }

        // font.setColor(Color.WHITE);
        // font.draw(batch, "PRESS TO PLAY", getViewWidth() / 2, getViewHeight() - 200);
        batch.end();
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        //FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        //parameter.size = 12;
        //BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        //generator.dispose(); // don't forget to dispose to avoid memory leaks!
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

            // If the "Description" button is touch, change the screen to description screen
            if (touch.x >= descriptionButton.x && touch.x <= descriptionButton.x + descriptionButton.width
                    && touch.y >= muteButton.y && touch.y <= descriptionButton.y + descriptionButton.height) {
                StateManager gsm = getStateManager();
                gsm.push(new DescriptionState(gsm));
            }

            // If the "How To Play" button is touch, change the screen to How To Play screen
            if (touch.x >= howToPlayButton.x && touch.x <= howToPlayButton.x + howToPlayButton.width
                    && touch.y >= howToPlayButton.y && touch.y <= howToPlayButton.y + howToPlayButton.height) {
                StateManager gsm = getStateManager();
                gsm.push(new HowToPlayState(gsm));
            }

            // If the "Play" button is touch, change the screen to Choose Player Amount screen
        }
    }

    @Override
    public void dispose() {
        // Dispose the used images
        bg.dispose();
        music.dispose();
        musicPlay.dispose();
        musicMute.dispose();
    }

}
