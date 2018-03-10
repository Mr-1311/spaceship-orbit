package com.mtz.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public abstract class State {
	
	protected OrthographicCamera camera;
	protected StateManeger sm;
	
	
	public State(StateManeger sm) {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, SpaceshipOrbit.width, SpaceshipOrbit.height);
		this.sm = sm;
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta); 
	public abstract void setInputProssesor();
		
	
}
