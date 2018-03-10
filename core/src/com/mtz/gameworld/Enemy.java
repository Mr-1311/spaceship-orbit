package com.mtz.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

import java.util.ArrayList;

public class Enemy implements GameObject{
	
	private float xKord, yKord, width, height, shotSize, r, circleXkord, circleYkord, startTime;
	private EnemyShotHandler enemyShotHandler;
	private int hit;
	private Texture enemy;
	private ArrayList<Shot> enemyShots;
	public boolean stop;
	private Vector2 shotPosition;
	private Circle collisionCircle;
	
	public Enemy() {

		
		width = SpaceshipOrbit.width / 10;
		height = width;
		xKord = SpaceshipOrbit.width + SpaceshipOrbit.width/2;
		yKord = SpaceshipOrbit.height/2 - (height/2) - height;
		
		r = (SpaceshipOrbit.width - (width * 2)) / 2;
		
		circleXkord = SpaceshipOrbit.width + width + width/2;
		circleYkord = SpaceshipOrbit.height/2 - height - r;

		shotSize =SpaceshipOrbit.width/30;
		shotPosition = new Vector2(SpaceshipOrbit.width/2 - shotSize/2, SpaceshipOrbit.height/2 - height - shotSize/2);
		
		enemy = Loader.enemy;
		enemyShots = new ArrayList<Shot>();
		
		collisionCircle = new Circle(shotPosition, r);
		
		stop = false;
		startTime = 0f;
		
		hit = 0;

		enemyShotHandler = new EnemyShotHandler(enemyShots, shotPosition, shotSize);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		
		enemyShotHandler.render(batch);
		
		batch.begin();
			
		batch.draw(enemy, xKord, yKord, width, height);
		batch.draw(Loader.circle, circleXkord, circleYkord, 2*r, 2*r);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		
		if (xKord > SpaceshipOrbit.width/2 - (width/2)){
			
			xKord -= (SpaceshipOrbit.width) * delta;
			circleXkord -= (SpaceshipOrbit.width) * delta;
			
			return;
		}
		
		stop = true;
		
		if (startTime < 0.5f){
			startTime += delta;
			return;
		}

		enemyShotHandler.update(delta);

		if (!enemyShots.isEmpty() && !collisionCircle.contains(enemyShots.get(0).getPosition())){
			enemyShots.remove(0);
			hit++;
		}
	}
	
	public ArrayList<Shot> getEnemyShots(){
		return enemyShots;
	}
	
	public float getXKord(){
		return xKord;
	}
	
	public int getHit(){
		return hit;
	}

	public  void reset() {

		startTime = 0f;
		hit = 0;

		enemyShots.clear();

		enemyShotHandler.reset();

	}
}
