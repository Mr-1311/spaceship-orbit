package com.mtz.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuStateButtons extends Button{
	
	private float degree, degreeControlTime, imageAlpha;
	private Sprite image;
	private boolean click, push;

	public MenuStateButtons(float xKord, float yKord, float width, float height, Texture texture) {
		super(xKord, yKord, width, height, texture);
		
		degree = 10.0f;
		degreeControlTime = 0.0f;
		
		click = false;
		push = false;
		
		image = new Sprite(texture);
		image.flip(false, true);
		image.setBounds(xKord, yKord, width, height);
		image.setOriginCenter();
		image.rotate(-15);
		
		imageAlpha = 1f;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		
		image.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		
		if (click){
			if (imageAlpha <= 0){
				push = true;
			}
			image.setAlpha(imageAlpha);
			
			imageAlpha -= delta * 2;
			if (imageAlpha <= 0){
				imageAlpha = 0f;
			}
		}
		
		if (degreeControlTime > 3.0f){
			degree *= -1;
			degreeControlTime = 0.0f;
		}
		
		image.rotate(degree * delta);
		degreeControlTime += delta;
		
	}
	
	public void reset(){
		
		click = false;
		push = false;
		
		imageAlpha = 1f;
		image.setAlpha(imageAlpha);
		
	}


	
	public void setClick(boolean click){
		this.click = click;
	}
	
	public boolean getPush(){
		return push;
	}
}
