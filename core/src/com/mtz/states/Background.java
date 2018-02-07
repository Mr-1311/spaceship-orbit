package com.mtz.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class Background {
	OrthographicCamera camera;
	float animationStateTime, farStarsXKord0, farStarsXKord1, closeStarsXKord0, closeStarsXKord1, speed;
	boolean anim;
	
	public Background(OrthographicCamera camera) {
		this.camera = camera;
		animationStateTime = 0;
		farStarsXKord0 = 0;
		farStarsXKord1 = SpaceshipOrbit.width;
		closeStarsXKord0 = 0;
		closeStarsXKord1 = SpaceshipOrbit.width;
		speed = SpaceshipOrbit.width / 10;
	}
	
	public void render(SpriteBatch batch) {
		
		batch.begin();
		
		batch.draw(Loader.background, 0, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);
		batch.draw(Loader.farStars.getKeyFrame(animationStateTime), farStarsXKord0, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);
		batch.draw(Loader.farStars.getKeyFrame(animationStateTime), farStarsXKord1, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);
		batch.draw(Loader.closeStars.getKeyFrame(animationStateTime), closeStarsXKord0, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);
		batch.draw(Loader.closeStars.getKeyFrame(animationStateTime), closeStarsXKord1, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);

		batch.end();
	}

	
	public void update(float delta) {
			
			if(anim){
				animationStateTime += delta;
				farStarsXKord0 -= 0.7 * (SpaceshipOrbit.width) * delta;
				farStarsXKord1 -= 0.7 * (SpaceshipOrbit.width) * delta;
				closeStarsXKord0 -= (SpaceshipOrbit.width) * delta;
				closeStarsXKord1 -= (SpaceshipOrbit.width) * delta;
				if(farStarsXKord0 < -SpaceshipOrbit.width)
					farStarsXKord0 = SpaceshipOrbit.width;
				if(farStarsXKord1 < -SpaceshipOrbit.width)
					farStarsXKord1 = SpaceshipOrbit.width;
				if(closeStarsXKord0< -SpaceshipOrbit.width)
					closeStarsXKord0 = SpaceshipOrbit.width;
				if(closeStarsXKord1 < -SpaceshipOrbit.width)
					closeStarsXKord1 = SpaceshipOrbit.width;
			}
			else{
				animationStateTime += delta;
				farStarsXKord0 -= 0.7 * speed*delta;
				farStarsXKord1 -= 0.7 * speed*delta;
				closeStarsXKord0 -= speed*delta;
				closeStarsXKord1 -= speed*delta;
				if(farStarsXKord0 < -SpaceshipOrbit.width)
					farStarsXKord0 = SpaceshipOrbit.width;
				if(farStarsXKord1 < -SpaceshipOrbit.width)
					farStarsXKord1 = SpaceshipOrbit.width;
				if(closeStarsXKord0< -SpaceshipOrbit.width)
					closeStarsXKord0 = SpaceshipOrbit.width;
				if(closeStarsXKord1 < -SpaceshipOrbit.width)
					closeStarsXKord1 = SpaceshipOrbit.width;
			}
	}
	
	public void setAnim (boolean anim){
		this.anim = anim;
	}
	
	
	
}
