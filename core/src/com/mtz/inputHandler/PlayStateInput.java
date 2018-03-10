package com.mtz.inputHandler;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mtz.loader.Loader;
import com.mtz.states.PauseState;
import com.mtz.states.PlayState;

import java.util.ArrayList;

public class PlayStateInput implements InputProcessor{
	
	private PlayState playState;
	private ArrayList<Vector2> touchList;
	
	public PlayStateInput(PlayState playState) {
		this.playState = playState;
		touchList = new ArrayList<Vector2>();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
			playState.getGw().getPlayer().setAngle(-playState.getGw().getPlayer().getAngle());
			break;
		case Input.Keys.RIGHT:
			playState.getGw().getPlayer().setAngle(4 * playState.getGw().getPlayer().getAngle());
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.RIGHT){
			playState.getGw().getPlayer().setAngle(playState.getGw().getPlayer().getAngle() / 4);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
				
		if (playState.getChangeRotation().getCollisionRect().contains(screenX, screenY)){
			playState.getGw().getPlayer().setAngle(-playState.getGw().getPlayer().getAngle());
			playState.getChangeRotation().getTexture().setTexture(Loader.changeRatationPressed);
			touchList.add(new Vector2(screenX, screenY));
		}
		
		if (playState.getSpeedUp().getCollisionRect().contains(screenX, screenY)){
			playState.getGw().getPlayer().setAngle(4 * playState.getGw().getPlayer().getAngle());
			playState.getSpeedUp().getTexture().setTexture(Loader.speedUpPressed);
			touchList.add(new Vector2(screenX, screenY));
		}
		
		if (playState.getPause().getCollisionRect().contains(screenX, screenY)){

			this.playState.getPause().setAlpha(false);
			this.playState.getChangeRotation().setAlpha(false);
			this.playState.getSpeedUp().setAlpha(false);
            this.playState.setPlay(false);

			playState.getSM().pushState(new PauseState(playState.getSM(), playState));
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		for (int i = 0; i < touchList.size(); i++) {

			if (!touchList.isEmpty() && playState.getSpeedUp().getCollisionRect().contains(touchList.get(i).x, touchList.get(i).y)) {
				playState.getGw().getPlayer().setAngle(playState.getGw().getPlayer().getAngle() / 4);
				playState.getSpeedUp().getTexture().setTexture(Loader.speedUp);
				touchList.remove(i);
			}

			if (!touchList.isEmpty() && playState.getChangeRotation().getCollisionRect().contains(touchList.get(i).x, touchList.get(i).y)) {
				playState.getChangeRotation().getTexture().setTexture(Loader.changeRatation);
				touchList.remove(i);
			}
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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
