package com.mtz.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseStateButtons extends Button{
	
	Sprite texture;

	public PauseStateButtons(float xKord, float yKord, float width, float height, Texture texture) {
		
		super(xKord, yKord, width, height, texture);
		
		this.texture = new Sprite(texture);
		this.texture.setFlip(false, true);
		this.texture.setBounds(xKord, yKord, width, height);
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.begin();
		
		texture.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		
	}

	public Sprite getTexture() {
		return texture;
	}
	
	

}
