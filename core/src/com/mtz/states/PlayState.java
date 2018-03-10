package com.mtz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.button.PlayStateButtons;
import com.mtz.gameworld.GameWorld;
import com.mtz.inputHandler.PlayStateInput;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class PlayState extends State{

	private Background background;
	private GameWorld gw;
	private PlayStateButtons changeRotation, speedUp, pause;
	private Sprite heart;
	private boolean isPlay;
    private float deltaSlow;
	private int finalScore;
	
	public PlayState(StateManeger sm, Background background) {
		
		super(sm);
		this.background = background;
		gw = new GameWorld();
		
		Gdx.input.setInputProcessor(new PlayStateInput(this));
		
		heart = new Sprite(Loader.heart);
		heart.setBounds(SpaceshipOrbit.width - SpaceshipOrbit.width/24 - SpaceshipOrbit.width/12, SpaceshipOrbit.width/24, SpaceshipOrbit.width/12, SpaceshipOrbit.width/12);
		heart.flip(true, true);

        isPlay = true;
        deltaSlow = 0f;
		finalScore =-1;
		
		changeRotation = new PlayStateButtons(0, SpaceshipOrbit.height-SpaceshipOrbit.width/2, SpaceshipOrbit.width/2, SpaceshipOrbit.width/2, Loader.changeRatation);
		speedUp = new PlayStateButtons(SpaceshipOrbit.width/2, SpaceshipOrbit.height-SpaceshipOrbit.width/2, SpaceshipOrbit.width/2, SpaceshipOrbit.width/2, Loader.speedUp);
		pause = new PlayStateButtons(SpaceshipOrbit.width/20, SpaceshipOrbit.width/20, SpaceshipOrbit.width/10, SpaceshipOrbit.width/10, Loader.pause);
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.setProjectionMatrix(camera.combined);
		
		background.render(batch);
		gw.render(batch);
		
		changeRotation.render(batch);
		speedUp.render(batch);
		pause.render(batch);
		
		batch.begin();
		
		if (gw.getEnemy().getXKord() <= SpaceshipOrbit.width/2 - (SpaceshipOrbit.width / 10/2) && isPlay){
			heart.draw(batch);
			Loader.fontLive.draw(batch, String.valueOf(gw.getLives()), SpaceshipOrbit.width - SpaceshipOrbit.width/24 - SpaceshipOrbit.width/12 *2, SpaceshipOrbit.width/24 + SpaceshipOrbit.width/45);
			Loader.fontScore.draw(batch, String.valueOf(GameWorld.score), this.setScoreToMid(GameWorld.score), SpaceshipOrbit.width/24 + SpaceshipOrbit.width/45);
		}
		batch.end();
		
	}

	@Override
	public void update(float delta) {

		if (gw.getLives() == 0){

			if (finalScore == -1)
				finalScore = GameWorld.score;

			deltaSlow += delta * delta * 1.5f;

			if (deltaSlow > delta) {

				this.getPause().setAlpha(false);
				this.getChangeRotation().setAlpha(false);
				this.getSpeedUp().setAlpha(false);
				this.setPlay(false);

				sm.pushState(new GameOverState(sm, this, finalScore));
				return;
			}

			background.update(delta - deltaSlow);
			gw.update(delta - deltaSlow);

			changeRotation.update(delta - deltaSlow);
			speedUp.update(delta - deltaSlow);
			pause.update(delta - deltaSlow);

			return;

		}

		background.update(delta);
		gw.update(delta);
		
		changeRotation.update(delta);
		speedUp.update(delta);
		pause.update(delta);
		
		if (gw.getEnemyStop()){
			background.setAnim(false);
		}
	}
	
	
	
	public StateManeger getSM() {
		return sm;
	}

	public GameWorld getGw() {
		return gw;
	}
	
	public PlayStateButtons getChangeRotation() {
		return changeRotation;
	}
	
	public PlayStateButtons getSpeedUp() {
		return speedUp;
	}
	
	public PlayStateButtons getPause() {
		return pause;
	}

    public void setPlay(boolean play) {
        isPlay = play;
    }

    @Override
	public void setInputProssesor() {
		
		Gdx.input.setInputProcessor(new PlayStateInput(this));	
		
	}
	
	
	private float setScoreToMid(int score){
		
		int a = 0;
		if (score == 0){
			return SpaceshipOrbit.width/2 - SpaceshipOrbit.width/20 * 1;
		}
		
		while(score != 0){
			score /= 10;
			a++;
		}
		
		return SpaceshipOrbit.width/2 - SpaceshipOrbit.width/20 * a;
	}

	public void reset(){

		gw.reset();

		isPlay = true;
		deltaSlow = 0f;
		finalScore =-1;

		pause.setAlpha(true);
		changeRotation.setAlpha(true);
		speedUp.setAlpha(true);
	}
}
