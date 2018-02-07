package com.mtz.inputHandler;

import com.badlogic.gdx.InputProcessor;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;
import com.mtz.states.PauseState;

public class PauseStateInput implements InputProcessor{
	
	private PauseState pauseState;
	
	public PauseStateInput(PauseState pauseState) {
		
		this.pauseState = pauseState;
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (pauseState.getResume().getCollisionRect().contains(screenX, screenY)){

            pauseState.getPlayState().getPause().setAlpha(true);
			pauseState.getPlayState().getChangeRotation().setAlpha(true);
			pauseState.getPlayState().getSpeedUp().setAlpha(true);
            pauseState.getPlayState().setPlay(true);

			pauseState.getSM().popState();
		}
		
		if (pauseState.getMainMenu().getCollisionRect().contains(screenX, screenY)){
			pauseState.getSM().popState();
			pauseState.getSM().popState();		
		}
		
		if (pauseState.getMusic().getCollisionRect().contains(screenX, screenY)){
			
			if (SpaceshipOrbit.isMusicOn){
				Loader.music.pause();
				pauseState.getMusic().getTexture().setTexture(Loader.musicOff);
				SpaceshipOrbit.isMusicOn = false;
			}
			
			else{
				Loader.music.play();
				pauseState.getMusic().getTexture().setTexture(Loader.musicOn);
				SpaceshipOrbit.isMusicOn = true;
			}
		}
		
		if (pauseState.getSound().getCollisionRect().contains(screenX, screenY)){
			
			if (SpaceshipOrbit.isSoundOn){
				pauseState.getSound().getTexture().setTexture(Loader.soundOff);
				SpaceshipOrbit.isSoundOn = false;
			}
			
			else{
				pauseState.getSound().getTexture().setTexture(Loader.soundOn);
				SpaceshipOrbit.isSoundOn = true;
			}
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
