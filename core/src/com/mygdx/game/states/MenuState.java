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
    private Rectangle muteButton;
    // Music instance variable
    public Music music;
    private boolean mute;
    // Description option instance variables
    private Texture picOfDescriptionButton;
    private Rectangle descriptionButton;
    // How To Play option instance variables
    private Texture picOfHowToPlayButton;
    private Rectangle howToPlayButton;
    // Play option instance variables
    private Texture picOfPlayButton;
    private Rectangle playButton;
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
        picOfDescriptionButton = new Texture("blackrectangle1.png");
        // Create the rectangle behind the description button option 
        descriptionButton = new Rectangle(721, 409, 175, 50);
        // Create the picture of the black rectangle 
        picOfHowToPlayButton = new Texture("blackrectangle1.png");
        // Create the rectangle button for the how to play option
        howToPlayButton = new Rectangle(110, 409, 175, 50);
        // Create the picture of the rectangle button for the play option
        picOfPlayButton = new Texture("blackrectangle1.png");
        // Create the rectangle of the play button
        playButton = new Rectangle(419, 409, 176, 50);
        setCameraView(RaceIt.WIDTH, RaceIt.HEIGHT);
        // Se the mute boolean to be false
        mute = false;
        // Place the mute button 
        muteButton = new Rectangle(0, 0, 50, 50);
        // Create an if statement saying to play only once!
        font = new BitmapFont();

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        // Beginning the drawings
        batch.begin();
        // Drawing the rectangle behind the Play option
        batch.draw(picOfPlayButton, playButton.x, playButton.y, playButton.width, playButton.height);
        // Drawing the rectangle behind the How To Play option
        batch.draw(picOfHowToPlayButton, howToPlayButton.x, howToPlayButton.y, howToPlayButton.width, howToPlayButton.height);
        // Drawing the rectangle behind the description option
        batch.draw(picOfDescriptionButton, descriptionButton.x, descriptionButton.y, descriptionButton.width, descriptionButton.height);
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());


        // If the mute button is not clicked


        // font.setColor(Color.WHITE);
        // font.draw(batch, "PRESS TO PLAY", getViewWidth() / 2, getViewHeight() - 200);
        batch.end();
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        //FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        //parameter.size = 12;
        //BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        //generator.dispose(); // don't forget to dispose to avoid memory leaks!
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
//        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
//        parameter.size = 12;
//        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
//        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    @Override
    public void update(float DeltaTime) {
    }

    @Override
    public void handleInput() {
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        unproject(touch);
        if (Gdx.input.justTouched()) {

            // If the "Description" button is touch, change the screen to description screen
            if (touch.x >= descriptionButton.x && touch.x <= descriptionButton.x + descriptionButton.width
                    && touch.y >= descriptionButton.y && touch.y <= descriptionButton.y + descriptionButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to DescriptionState
                gsm.push(new DescriptionState(gsm));
                // Allow for the music to continue playing
                //music.play();
            }

            // If the "How To Play" button is touch, change the screen to How To Play screen
            if (touch.x >= howToPlayButton.x && touch.x <= howToPlayButton.x + howToPlayButton.width
                    && touch.y >= howToPlayButton.y && touch.y <= howToPlayButton.y + howToPlayButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to HowToPlayState
                gsm.push(new HowToPlayState(gsm));
                // Allow for the music to continue playing
                //music.play();
            }

            // If the "Play" button is touch, change the screen to Choose Player Amount screen
            if (touch.x >= playButton.x && touch.x <= playButton.x + playButton.width
                    && touch.y >= playButton.y && touch.y <= playButton.y + playButton.height) {
                StateManager gsm = getStateManager();
                // Change the state to ChooseAmountPlayersState
                gsm.push(new CarChoiceState(gsm));
                // Allow for the music to continue playing
                //music.play();
            }
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
