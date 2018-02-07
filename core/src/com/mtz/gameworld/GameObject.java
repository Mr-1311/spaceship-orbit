package com.mtz.gameworld;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {

	
	public void render(SpriteBatch batch);
	public void update(float delta);
	
	
}
