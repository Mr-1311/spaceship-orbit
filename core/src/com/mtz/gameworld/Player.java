package com.mtz.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

import java.util.ArrayList;

public class Player implements GameObject{
	
	private Sprite player, laser;
	private float width, height, xKord, yKord, r, radyan, shotTime, shotSize, startTime;
	public static float angle;
	private ArrayList<Shot> playerShots;
	private Rectangle centerRect;
	private Polygon laserCollisionRect;
	private boolean start, isLaser;

	public Player() {

		start = true;
		isLaser = false;
		startTime = 0f;
		
		angle = 0.95f;
		radyan = (float) Math.toRadians(270);
		
		width = SpaceshipOrbit.width / 10;
		height = width;
		xKord = SpaceshipOrbit.width + SpaceshipOrbit.width/2;
		r = SpaceshipOrbit.width/2 - (width/2);
		yKord = SpaceshipOrbit.height / 2 - r - (width / 2) - height;
		
		player = new Sprite(Loader.player, 0, 0, 50, 50);
		player.setSize(width, height);
		player.setOriginCenter();
		player.setPosition(xKord, yKord);
		
		playerShots = new ArrayList<Shot>();
		shotTime = 0f;
		shotSize = SpaceshipOrbit.width / 40;
		
		centerRect = new Rectangle(SpaceshipOrbit.width / 2 - (float)(SpaceshipOrbit.width / 36 * Math.sqrt(2)), SpaceshipOrbit.height / 2 - height - (float)(SpaceshipOrbit.width / 36 * Math.sqrt(2)), (float)(SpaceshipOrbit.width / 18 * Math.sqrt(2)), (float)(SpaceshipOrbit.width / 18 * Math.sqrt(2)));
	}

	@Override
	public void render(SpriteBatch batch) {
		
		for (int i = 0; i < playerShots.size(); i++) {
			playerShots.get(i).render(batch);
		}

		batch.begin();

		if (isLaser) {
			laser.draw(batch);

		}
		player.draw(batch);

		batch.end();

	}

	@Override
	public void update(float delta) {
		
		if (start && xKord > SpaceshipOrbit.width/2 - (width/2)){
			
			xKord -= (SpaceshipOrbit.width) * delta;
			player.setX(xKord);
			return;
		}
		start = false;
		
		if (startTime < 1){
			startTime += delta;
			return;
		}
		
		xKord = SpaceshipOrbit.width/2 + (float) (r * Math.cos(radyan)) - (width/2);
		yKord = SpaceshipOrbit.height/2 - height + (float) (r * Math.sin(radyan)) - (width/2);
		radyan += angle * delta;
		player.setPosition(xKord, yKord);
		player.rotate((float) (Math.toDegrees(angle) * delta));
		
		for (int i = 0; i < playerShots.size(); i++) {
			playerShots.get(i).update(delta);
		}
		
		shotTime += delta;


		if (isLaser){
			laser.setPosition(player.getX() + player.getWidth() / 2 - laser.getWidth() / 2, player.getY() + player.getHeight() / 2);
			laser.rotate((float) (Math.toDegrees(angle) * delta));
			laserCollisionRect.setPosition(player.getX() + player.getWidth() / 2 - laser.getWidth() / 2, SpaceshipOrbit.height - player.getY() - player.getHeight() / 2);
			laserCollisionRect.rotate(-(float) (Math.toDegrees(angle) * delta));
		}
		else if (shotTime > 0.23){
			shotTime = 0;
			playerShots.add(new Shot(Loader.playerShot, new Vector2(shotPos()), shotSize, shotSize*2, new Vector2(SpaceshipOrbit.width/2 - shotSize/2, SpaceshipOrbit.height/2 - height - shotSize/2), 3f, radyan));
		}
		
		if (!playerShots.isEmpty() && centerRect.overlaps(playerShots.get(0).getColisionRect())){
			playerShots.remove(0);
		}
		
	}
	
	private Vector2 shotPos(){
		
		float x, y, r;
		x = xKord + width/2;
		y = yKord + height/2;
		r = width/2;
		
		return new Vector2((float)(x + r * Math.cos(radyan + Math.toRadians(180))), (float)(y + r * Math.sin(radyan + Math.toRadians(180))));
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public boolean getLaser() {
		return isLaser;
	}

	public void setLaser(boolean laser) {
		isLaser = laser;

		if (laser){
			this.laser = new Sprite(Loader.laser);
			this.laser.setSize(SpaceshipOrbit.width / 24, SpaceshipOrbit.width / 2.5f);
			this.laser.setOrigin(this.laser.getWidth()/2, 0);
			this.laser.rotate(90 + (float) (Math.toDegrees(radyan)));

			laserCollisionRect = new Polygon(new float[]{
					this.laser.getX(), this.laser.getY(),
					this.laser.getX(), this.laser.getY() + this.laser.getHeight(),
					this.laser.getX() + this.laser.getWidth(), this.laser.getY() + this.laser.getHeight(),
					this.laser.getX() + this.laser.getWidth(), this.laser.getY()
			});
			laserCollisionRect.setOrigin(this.laser.getWidth()/2 , 0);
			laserCollisionRect.rotate(90 - (float) (Math.toDegrees(radyan)));
		}
		else {
			this.laser = null;
		}
	}

	public ArrayList<Shot> getPlayerShots(){
		return playerShots;
	}

    public Polygon getLaserCollisionRect() {
        return laserCollisionRect;
    }

    public void setLaserCollisionRect(Polygon laserCollisionRect) {
        this.laserCollisionRect = laserCollisionRect;
    }

    public void reset(){

		startTime = 0f;

		angle = 1f;
		radyan = (float) Math.toRadians(270);

		xKord = SpaceshipOrbit.width/2 - (width/2);
		yKord = SpaceshipOrbit.height/2 - r - (width / 2) - height;
        player = new Sprite(Loader.player, 0, 0, 50, 50);
        player.setSize(width, height);
        player.setOriginCenter();
        player.setPosition(xKord, yKord);

		shotTime = 0f;
		isLaser = false;

		laser = null;
		laserCollisionRect = null;

		playerShots.clear();
	}

}
