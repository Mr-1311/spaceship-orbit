package com.mtz.inputHandler;

import com.badlogic.gdx.InputProcessor;
import com.mtz.states.GameOverState;


public class GameOverStateInput implements InputProcessor{
	
	private GameOverState gameOverState;
	
	public GameOverStateInput(GameOverState gameOverState) {
		this.gameOverState = gameOverState;
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

		if (!gameOverState.getDraw())
			return false;

		if (gameOverState.getPlayAgainButton().getCollisionRect().contains(screenX, screenY)){

			gameOverState.getPlayState().reset();
			gameOverState.getSm().popState();

		}

		if (gameOverState.getMainMenuButton().getCollisionRect().contains(screenX, screenY)) {

			gameOverState.getSm().popState();
			gameOverState.getSm().popState();

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
