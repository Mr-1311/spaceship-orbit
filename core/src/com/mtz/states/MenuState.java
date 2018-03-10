package com.mtz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.inputHandler.MenuStateInput;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class MenuState extends State{
	
	Background background;

	private float playXkord, playYkord, playWidth, playHeight, logoXkord, logoYkord, logoWidth, logoHeight, logoAlpha, playAlpha;
	private Sprite logo;
	private Sprite play;
	private boolean click, playAlphaDirection;
	
	public MenuState(StateManeger sm) {
		
		super(sm);
		
		logoWidth = SpaceshipOrbit.width-SpaceshipOrbit.width/11;
		logoHeight = (float) (logoWidth/3.4);
		logoXkord = SpaceshipOrbit.width/2 - logoWidth/2;
		logoYkord = SpaceshipOrbit.height / 7;
		
		logo = new Sprite(Loader.logo);
		logo.setBounds(logoXkord, logoYkord, logoWidth, logoHeight);
		logo.flip(false, true);
		logo.setOriginCenter();
		logoAlpha = 1f;

		playWidth = SpaceshipOrbit.width - SpaceshipOrbit.width/3;
		playHeight = (float) (playWidth/10.5);
		playXkord = (SpaceshipOrbit.width-playWidth)/2;
		playYkord = (float) (SpaceshipOrbit.height/1.3);

		play = new Sprite(Loader.play);
		play.setBounds(playXkord, playYkord, playWidth, playHeight);
		play.flip(false, true);
		playAlpha = 1f;
		playAlphaDirection = true;

		background = new Background(camera);
		
		click = false;

		Gdx.input.setInputProcessor(new MenuStateInput(this));
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		batch.setProjectionMatrix(camera.combined);
		
		background.render(batch);

		batch.begin();
		
		logo.draw(batch);
		play.draw(batch);
		
		batch.end();
		
	}

	@Override
	public void update(float delta) {
		
		background.update(delta);

		background.setAnim(false);
		
		if (click){
			
			background.setAnim(true);
			
			logoAlpha -= delta * 2;
			playAlpha -= delta * 2;

			if (playAlpha <= 0){
				playAlpha = 0f;
			}
			if (logoAlpha <= 0){
				logoAlpha = 0f;
				logo.setAlpha(logoAlpha);
				reset();
				sm.pushState(new PlayState(getSM(), background));
			}

			logo.setAlpha(logoAlpha);
			play.setAlpha(playAlpha);
			return;
		}
		if (playAlpha > 0.25f && playAlphaDirection)
			playAlpha -= delta * 1;
		else if (playAlpha < 1){
			playAlphaDirection = false;
			playAlpha += delta * 1;
			if (playAlpha >= 1){
				playAlpha = 1f;
				playAlphaDirection = true;
			}
		}
		play.setAlpha(playAlpha);
	}

	private void reset() {

		logoAlpha = 1f;
		playAlpha = 1f;
		playAlphaDirection = true;

		click = false;

	}


	public StateManeger getSM(){
		return sm;
	}
	
	public Background getBack(){
		return background;
	}
	
	public void setClick(boolean click) {
		this.click = click;
	}

	@Override
	public void setInputProssesor() {
		
		Gdx.input.setInputProcessor(new MenuStateInput(this));
		
	}

}
