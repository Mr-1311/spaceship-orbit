package com.mtz.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class Loader {
	
	public static Texture background, closeStars0, closeStars1, closeStars2, closeStars3,farStars0, farStars1, farStars2, 
	enemy, shot, shot1, shot2, shot3, player, playerShot, play, logo, circle, changeRatation, speedUp, changeRatationPressed, speedUpPressed,
	pause, start, heart, PauseStateBackground, resume, mainMenu, soundOn, soundOff, musicOn, musicOff, gameOverBackground,
	playAgainButton, mainMenuButton, livePowerUp, twoxPowerUp, twoxTimeBar, laser, laserPowerUp, laserTimeBar, slowPowerUp, slowTimeBar;
	public static BitmapFont fontLive, fontScore, fontGameOverScore;
	public static Animation<TextureRegion> farStars, closeStars;
	public static Music music;
	public static Sound shotSound;
	
	public static void load() {
		logo = new Texture(Gdx.files.internal("logo.png"));
		play = new Texture(Gdx.files.internal("play.png"));
		playerShot = new Texture(Gdx.files.internal("playerShot.png"));
		player = new Texture(Gdx.files.internal("tri.png"));
		circle = new Texture(Gdx.files.internal("circle.png"));
		changeRatation = new Texture(Gdx.files.internal("changeRotation.png"));
		speedUp = new Texture(Gdx.files.internal("speedUp.png"));
		changeRatationPressed = new Texture(Gdx.files.internal("changeRotationPressed.png"));
		speedUpPressed = new Texture(Gdx.files.internal("speedUpPressed.png"));
		pause = new Texture(Gdx.files.internal("pause.png"));
		start = new Texture(Gdx.files.internal("start.png"));
		heart = new Texture(Gdx.files.internal("heart.png"));
		background = new Texture(Gdx.files.internal("backgroundSpace.png"));
		enemy = new Texture(Gdx.files.internal("enemy.png"));
		shot = new Texture(Gdx.files.internal("shot.png"));
		shot1 = new Texture(Gdx.files.internal("shot1.png"));
		shot2 = new Texture(Gdx.files.internal("shot2.png"));
		shot3 = new Texture(Gdx.files.internal("shot3.png"));
		PauseStateBackground = new Texture(Gdx.files.internal("pauseStateBackground.png"));
		resume = new Texture(Gdx.files.internal("resume.png"));
		mainMenu = new Texture(Gdx.files.internal("mainMenu.png"));
		soundOn = new Texture(Gdx.files.internal("soundOn.png"));
		soundOff = new Texture(Gdx.files.internal("soundOff.png"));
		musicOn = new Texture(Gdx.files.internal("musicOn.png"));
		musicOff = new Texture(Gdx.files.internal("musicOff.png"));
		playAgainButton = new Texture(Gdx.files.internal("playAgainButton.png"));
		mainMenuButton = new Texture(Gdx.files.internal("mainMenuButton.png"));
		closeStars0 = new Texture(Gdx.files.internal("closeStars0.png"));
		closeStars1 = new Texture(Gdx.files.internal("closeStars1.png"));
		closeStars2 = new Texture(Gdx.files.internal("closeStars2.png"));
		closeStars3 = new Texture(Gdx.files.internal("closeStars3.png"));
		farStars0 = new Texture(Gdx.files.internal("farStars0.png"));
		farStars1 = new Texture(Gdx.files.internal("farStars1.png"));
		farStars2 = new Texture(Gdx.files.internal("farStars2.png"));
		livePowerUp = new Texture(Gdx.files.internal("livePowerUp.png"));
		twoxPowerUp = new Texture(Gdx.files.internal("2xPowerUp.png"));
		twoxTimeBar = new Texture(Gdx.files.internal("2xTimeBar.png"));
		laser = new Texture(Gdx.files.internal("laser.png"));
		laserPowerUp = new Texture(Gdx.files.internal("laserPowerUp.png"));
		laserTimeBar = new Texture(Gdx.files.internal("laserTimeBar.png"));
		slowPowerUp = new Texture(Gdx.files.internal("slowPowerUp.png"));
		slowTimeBar = new Texture(Gdx.files.internal("slowTimeBar.png"));
		gameOverBackground = new Texture(Gdx.files.internal("gameOverBackground.png"));
		farStars = new Animation<TextureRegion>(0.1f, new TextureRegion(farStars0), new TextureRegion(farStars1), new TextureRegion(farStars2), new TextureRegion(farStars1));
		farStars.setPlayMode(PlayMode.LOOP);
		closeStars = new Animation<TextureRegion>(0.1f, new TextureRegion(closeStars0), new TextureRegion(closeStars1), new TextureRegion(closeStars2), new TextureRegion(closeStars3), new TextureRegion(closeStars2), new TextureRegion(closeStars1));
		closeStars.setPlayMode(PlayMode.LOOP);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();

		int fontSize;

		if (Gdx.graphics.getWidth() < Gdx.graphics.getHeight())
			fontSize = Gdx.graphics.getWidth();
		else
			fontSize = Gdx.graphics.getHeight();


		parameter.size = fontSize / 10;
		parameter.flip = true;
		fontLive = generator.generateFont(parameter);
		parameter.size = fontSize / 6;
		fontScore = generator.generateFont(parameter);
		parameter.size = fontSize / 3;
		fontGameOverScore = generator.generateFont(parameter);
		generator.dispose();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("Space-Cube.wav"));
		music.setLooping(true);

		shotSound = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
	}
	
	public static void dispose(){
		logo.dispose();
		play.dispose();
		player.dispose();
		circle.dispose();
		changeRatation.dispose();
		speedUp.dispose();
		pause.dispose();
		start.dispose();
		heart.dispose();
		changeRatationPressed.dispose();
		speedUpPressed.dispose();
		background.dispose();
		enemy.dispose();
		shot.dispose();
		shot1.dispose();
		shot2.dispose();
		shot3.dispose();
		PauseStateBackground.dispose();
		resume.dispose();
		mainMenu.dispose();
		soundOn.dispose();
		soundOff.dispose();
		musicOn.dispose();
		musicOff.dispose();
		closeStars0.dispose();
		closeStars1.dispose();
		closeStars2.dispose();
		closeStars3.dispose();
		farStars0.dispose();
		farStars1.dispose();
		farStars2.dispose();
		livePowerUp.dispose();
		twoxPowerUp.dispose();
		twoxTimeBar.dispose();
		laser.dispose();
		laserPowerUp.dispose();
		laserTimeBar.dispose();
		slowTimeBar.dispose();
		slowPowerUp.dispose();
		playAgainButton.dispose();
		mainMenuButton.dispose();
		gameOverBackground.dispose();
		music.dispose();
		shotSound.dispose();
	}
}
