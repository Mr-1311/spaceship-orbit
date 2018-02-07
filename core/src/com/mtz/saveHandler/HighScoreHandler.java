package com.mtz.saveHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by metin on 08.07.2017.
 */

public class HighScoreHandler {

    public Preferences pref;

    public HighScoreHandler (){

        pref = Gdx.app.getPreferences("spaceship orbit");

        if (!pref.contains("highscore")){

            pref.putInteger("highscore", 0);
            pref.flush();

        }

    }

    public void setHighScore(int highScore){

        pref.putInteger("highscore", highScore);
        pref.flush();

    }

    public int getHighScore(){

        return pref.getInteger("highscore");

    }
}
