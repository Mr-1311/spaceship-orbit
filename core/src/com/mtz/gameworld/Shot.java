package com.mtz.gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shot {
	
	private Sprite shot;
	private Vector2 position;
	private float speed, deltaX, deltaY;
	private Rectangle colisionRect;
	
	public Shot(Texture image, Vector2 position, float width, float height, Vector2 direction, float speed) {

		shot = new Sprite(image);
		shot.setBounds(position.x, position.y, width, height);
		this.position = position;
		this.speed = speed;
		deltaX = direction.x - position.x;
		deltaY = direction.y - position.y;
		colisionRect = new Rectangle(position.x, position.y, width, height);

	}

	public Shot(Texture image, Vector2 position, float width, float height, Vector2 direction, float speed, float angle) {

		shot = new Sprite(image);
		shot.setBounds(position.x, position.y, width, height);
		shot.setOrigin(shot.getWidth()/2, 0);
		shot.rotate(90 + (float) (Math.toDegrees(angle)));
		this.position = position;
		this.speed = speed;
		deltaX = direction.x - position.x;
		deltaY = direction.y - position.y;
		colisionRect = new Rectangle(position.x, position.y, width, width);
	}
	
	public void render (SpriteBatch batch){
		
		batch.begin();

		shot.draw(batch);

		batch.end();
	}
	
	public void update (float delta){
		
		position.x += deltaX * delta * speed;
		position.y += deltaY * delta * speed;

		shot.setPosition(position.x, position.y);
		
		colisionRect.setPosition(position);
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getColisionRect(){
		return colisionRect;
	}
}
