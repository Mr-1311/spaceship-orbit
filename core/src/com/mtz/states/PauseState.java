package com.mtz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.button.PauseStateButtons;
import com.mtz.inputHandler.PauseStateInput;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class PauseState extends State{
	
	private PlayState playState;
	private PauseStateButtons resume, mainMenu, sound, music;
	private float backXkord, backYkord, backWidth, backHeight;
	
	public PauseState(StateManeger sm, PlayState playState) {
		
		super(sm);
		
		this.playState = playState;
		
		resume = new PauseStateButtons((SpaceshipOrbit.width-SpaceshipOrbit.width/4.8f)/2f, SpaceshipOrbit.height/4f+SpaceshipOrbit.height/18f, SpaceshipOrbit.width/4.8f, SpaceshipOrbit.height/6.4f, Loader.resume);
		mainMenu = new PauseStateButtons((SpaceshipOrbit.width-SpaceshipOrbit.width/1.6f)/2f, SpaceshipOrbit.height/4f+SpaceshipOrbit.height/6.4f+(SpaceshipOrbit.height/18f)*2, SpaceshipOrbit.width/1.6f, SpaceshipOrbit.height/16, Loader.mainMenu);
		music = new PauseStateButtons(((((SpaceshipOrbit.width/1.2f)/2) - SpaceshipOrbit.height/16)/2) + SpaceshipOrbit.width/12, SpaceshipOrbit.height/16+SpaceshipOrbit.height/4f+SpaceshipOrbit.height/6.4f+(SpaceshipOrbit.height/18f)*3, SpaceshipOrbit.height/16, SpaceshipOrbit.height/16, (SpaceshipOrbit.isMusicOn)? Loader.musicOn: Loader.musicOff);
		sound = new PauseStateButtons(((((SpaceshipOrbit.width/1.2f)/2) - SpaceshipOrbit.height/16)/2) + SpaceshipOrbit.width/12 + (SpaceshipOrbit.width/1.2f)/2, SpaceshipOrbit.height/16+SpaceshipOrbit.height/4f+SpaceshipOrbit.height/6.4f+(SpaceshipOrbit.height/18f)*3, SpaceshipOrbit.height/16, SpaceshipOrbit.height/16, (SpaceshipOrbit.isSoundOn)? Loader.soundOn: Loader.soundOff);

		backWidth = SpaceshipOrbit.width;// - SpaceshipOrbit.width/11;
		backHeight = SpaceshipOrbit.height - SpaceshipOrbit.height/8;
		backXkord = (SpaceshipOrbit.width - backWidth) / 2;
		backYkord = (SpaceshipOrbit.height - backHeight) / 2;

		Gdx.input.setInputProcessor(new PauseStateInput(this));
		
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.setProjectionMatrix(camera.combined);
		
		playState.render(batch);
		
		batch.begin();
		
		batch.draw(Loader.PauseStateBackground, backXkord, backYkord, backWidth, backHeight);
		
		batch.end();

		resume.render(batch);
		mainMenu.render(batch);
		music.render(batch);
		sound.render(batch);
	}

	@Override
	public void update(float delta) {
		
	}
	
	public StateManeger getSM() {
		return sm;
	}
	
	public PauseStateButtons getResume() {
		return resume;
	}

	public PauseStateButtons getMainMenu() {
		return mainMenu;
	}

	public PauseStateButtons getSound() {
		return sound;
	}

	public PauseStateButtons getMusic() {
		return music;
	}

	public PlayState getPlayState() {
		return playState;
	}

	@Override
	public void setInputProssesor() {
		
		Gdx.input.setInputProcessor(new PauseStateInput(this));	
		
	}
}
