package com.mtz.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Button {
	
	protected float xKord, yKord, width, height;
	protected Texture texture;
	protected Rectangle collisionRect; 
	
	public Button(float xKord, float yKord, float width, float height, Texture texture) {
		
		this.xKord = xKord;
		this.yKord = yKord;
		this.width = width;
		this.height = height;
		
		this.texture = texture;
		
		collisionRect = new Rectangle(xKord, yKord, width, height);
		
	}
	
	public abstract void render(SpriteBatch batch);
	
	
	public abstract void update(float delta);
	
	public Rectangle getCollisionRect(){
		return collisionRect;
	}
		
}
