package com.mtz.spaceshiporbit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.loader.Loader;
import com.mtz.states.MenuState;
import com.mtz.states.StateManeger;

public class SpaceshipOrbit extends Game {
	SpriteBatch batch;
	StateManeger sm;

	public static int width;
	public static int height;

	public static boolean isMusicOn, isSoundOn;
	
	public void create () {

		batch = new SpriteBatch();

		Loader.load();
		Loader.music.play();
		
		isMusicOn = true;
		isSoundOn = true;

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		sm = new StateManeger();
		sm.pushState(new MenuState(sm));
	}

	public void render () {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sm.peekState().render(batch);
		sm.peekState().update(Gdx.graphics.getDeltaTime());

	}
	
	public void dispose () {
		
		batch.dispose();
		Loader.dispose();
		
	}
}
