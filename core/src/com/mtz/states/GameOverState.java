package com.mtz.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.button.PauseStateButtons;
import com.mtz.inputHandler.GameOverStateInput;
import com.mtz.loader.Loader;
import com.mtz.saveHandler.HighScoreHandler;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class GameOverState extends State{

	PlayState playState;
    Sprite gameOverBackground;

	PauseStateButtons playAgainButton;
	PauseStateButtons mainMenuButton;

	private GlyphLayout glyphLayout;

    private float backAlpha;
	private float buttonWidth;
	private int score;
	private int currentHighScore;
	private HighScoreHandler highScoreHandler;
	private boolean isHighScore, draw;
    private float highScoreY;

	public GameOverState(StateManeger sm, PlayState playState, int score) {
		
		super(sm);
		this.playState = playState;

        gameOverBackground = new Sprite(Loader.gameOverBackground);
        gameOverBackground.setBounds(0, 0, SpaceshipOrbit.width, SpaceshipOrbit.height);
        gameOverBackground.setAlpha(0f);

		buttonWidth = SpaceshipOrbit.width - SpaceshipOrbit.width/5;
		playAgainButton = new PauseStateButtons(SpaceshipOrbit.width/10, SpaceshipOrbit.height/2, buttonWidth, buttonWidth/5, Loader.playAgainButton);
		mainMenuButton = new PauseStateButtons(SpaceshipOrbit.width/10, SpaceshipOrbit.height/2 + buttonWidth/4, buttonWidth, buttonWidth/5, Loader.mainMenuButton);

		glyphLayout = new GlyphLayout();

		this.score = score;
		isHighScore = false;
		draw = false;

        this.backAlpha = 0f;

		initHighScoreHandler();

		Gdx.input.setInputProcessor(new GameOverStateInput(this));
		
	}

	@Override
	public void render(SpriteBatch batch) {

		batch.setProjectionMatrix(camera.combined);

		playState.render(batch);

        batch.begin();

        gameOverBackground.draw(batch);

		if (!draw){
			batch.end();
			return;
		}

		if (!isHighScore){
			glyphLayout.setText(Loader.fontLive, "GAME OVER");
			Loader.fontLive.draw(batch, glyphLayout, (SpaceshipOrbit.width - glyphLayout.width)/2, SpaceshipOrbit.height/9);
		}
		else {
			glyphLayout.setText(Loader.fontLive, "NEW HiGHSCORE");
			Loader.fontLive.draw(batch, glyphLayout, (SpaceshipOrbit.width - glyphLayout.width)/2, SpaceshipOrbit.height/9);
		}

		glyphLayout.setText(Loader.fontGameOverScore, String.valueOf(score));
		Loader.fontGameOverScore.draw(batch, glyphLayout, (SpaceshipOrbit.width - glyphLayout.width)/2, SpaceshipOrbit.height/4);

        highScoreY = SpaceshipOrbit.height/4 + glyphLayout.height;

		glyphLayout.setText(Loader.fontLive, "HiGHSCORE " + String.valueOf(currentHighScore));
		Loader.fontLive.draw(batch, glyphLayout, (SpaceshipOrbit.width - glyphLayout.width)/2, highScoreY + glyphLayout.height*2);

	    batch.end();

		playAgainButton.render(batch);
		mainMenuButton.render(batch);

	}

	@Override
	public void update(float delta) {

        if (backAlpha <= 1f){

            backAlpha += delta * 2f;

            if (backAlpha > 1f){
                gameOverBackground.setAlpha(1f);
				draw = true;
            }
            else
                gameOverBackground.setAlpha(backAlpha);
        }



	}



	
	public StateManeger getSm(){
		return sm;
	}

	public PauseStateButtons getPlayAgainButton() {
		return playAgainButton;
	}

	public void setPlayAgainButton(PauseStateButtons playAgainButton) {
		this.playAgainButton = playAgainButton;
	}

	public PauseStateButtons getMainMenuButton() {
		return mainMenuButton;
	}

	public void setMainMenuButton(PauseStateButtons mainMenuButton) {
		this.mainMenuButton = mainMenuButton;
	}

	public PlayState getPlayState() {
		return playState;
	}

	public void setPlayState(PlayState playState) {
		this.playState = playState;
	}

	public boolean getDraw(){
		return draw;
	}

	private void initHighScoreHandler(){

		highScoreHandler = new HighScoreHandler();

		if (score > highScoreHandler.getHighScore()){

			highScoreHandler.setHighScore(score);
			isHighScore = true;
		}

		currentHighScore = highScoreHandler.getHighScore();
	}

	@Override
	public void setInputProssesor() {
		
		Gdx.input.setInputProcessor(new GameOverStateInput(this));
		
	}

}
