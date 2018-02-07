package com.mtz.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class GameWorld implements GameObject{
	
	private Enemy enemy;
	private Player player;
	private PowerUpHandler powerUpHandler;
	private Sprite start;
	private float alpha;
	public static int live, score, factor;
	public static boolean isLaser;
	private boolean alphaFlag, startFlag;
	
	public GameWorld() {
		player = new Player();
		enemy = new Enemy();

		powerUpHandler = new PowerUpHandler();

		alpha = 0f;
		alphaFlag = true;
		startFlag = true;
		start = new Sprite(Loader.start);
		start.setFlip(false, true);
		start.setAlpha(alpha);
		start.setBounds(SpaceshipOrbit.width/2 - SpaceshipOrbit.width/4, SpaceshipOrbit.height/2 - SpaceshipOrbit.width / 5, SpaceshipOrbit.width/2, SpaceshipOrbit.width/4);
		
		live = 3;
		score = 0;
		factor = 1;
		isLaser = false;
	}

	@Override
	public void render(SpriteBatch batch) {
		
		player.render(batch);
		enemy.render(batch);
		powerUpHandler.render(batch);
		
		batch.begin();
		
		if (enemy.getXKord() <= SpaceshipOrbit.width/2 - (SpaceshipOrbit.width / 10/2)){
			
			start.draw(batch);
			
		}
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		
		enemy.update(delta);
		player.update(delta);

		if (powerUpHandler.getPowerUp() != null){
			for (int i = 0; i < player.getPlayerShots().size(); i++){
				try {
				if (player.getPlayerShots().get(i).getColisionRect().overlaps(powerUpHandler.getPowerUp().getColisionRect())){
					player.getPlayerShots().remove(i);
					powerUpHandler.setActive(true);
					powerUpHandler.setPowerUp(null);
				}}
				catch (Exception e){
					// TODO: handle exception
				}
			}
		}

		powerUpHandler.update(delta);

		if (isLaser != player.getLaser())
			player.setLaser(isLaser);

		if (isLaser){
            for (int j = 0; j < enemy.getEnemyShots().size(); j++) {
                try{
                    if (isCollision(player.getLaserCollisionRect(), enemy.getEnemyShots().get(j).getColisionRect())){
                        enemy.getEnemyShots().remove(j);
                        score += 1 * factor;
                    }}
                catch (Exception e) {
                    // TODO: handle exception
                }
            }
		}

		for (int i = 0; i < player.getPlayerShots().size(); i++) {
			for (int j = 0; j < enemy.getEnemyShots().size(); j++) {
				try{
				if (player.getPlayerShots().get(i).getColisionRect().overlaps(enemy.getEnemyShots().get(j).getColisionRect())){
					player.getPlayerShots().remove(i);
					enemy.getEnemyShots().remove(j);
					score += 1 * factor;
				}}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		if (startFlag && enemy.getXKord() <= SpaceshipOrbit.width/2 - (SpaceshipOrbit.width / 10/2)){
		
			if (alphaFlag)
				alpha += delta*1.5;
			else{
				alpha -= delta*4;
			}
			if (alpha >= 1){
				alpha = 0.99f;
				alphaFlag = false;
			}
			if (alpha <= 0){
				alpha = 0;
				startFlag = false;
			}
			
			start.setAlpha(alpha);
		
		}
	}
	
	public boolean getEnemyStop(){
		return enemy.stop;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	public int getLives(){
		if (live - enemy.getHit() < 0)
			return 0;
		return live - enemy.getHit();
	}

    private boolean isCollision(Polygon p, Rectangle r) {
        Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width,
                r.height, 0, r.height });
        rPoly.setPosition(r.x, SpaceshipOrbit.height - r.y);
        if (Intersector.overlapConvexPolygons(rPoly, p))
            return true;
        return false;
    }

	public void reset() {

		alpha = 0f;
		alphaFlag = true;
		startFlag = true;

		score = 0;
		factor = 1;
		isLaser = false;

		enemy.reset();
		player.reset();
		powerUpHandler.reset();

	}
}
