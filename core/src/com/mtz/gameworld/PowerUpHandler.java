package com.mtz.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

/**
 * Created by metin on 12.07.2017.
 */

public class PowerUpHandler implements GameObject{

    private Sprite bar;
    private PowerUp powerUp;
    private int powerUpNumber, currentScore;
    private boolean isActive;
    private float timer;
    private float twoxTimer, laserTimer, slowTimer;

    public PowerUpHandler(){

        powerUp = null;
        powerUpNumber = -1;
        currentScore = GameWorld.score;

        bar = null;

        timer = 0f;
        twoxTimer = 7f;
        laserTimer = 9f;
        slowTimer = 11f;

        isActive = false;

    }

    @Override
    public void render(SpriteBatch batch) {

        if (powerUp != null){

            powerUp.render(batch);

        }

        batch.begin();

        if (isActive){
            if (powerUpNumber == 0)
                bar.draw(batch);
            else if (powerUpNumber == 2)
                bar.draw(batch);
            else if (powerUpNumber == 3)
                bar.draw(batch);
        }

        batch.end();

    }

    @Override
    public void update(float delta) {

        if (powerUpNumber == -1){

            isScoreChange();
            return;
        }

        if (powerUp != null){
            powerUp.update(delta);
        }

        powerUp(delta);

        if (isActive){
            if (powerUpNumber == 0) {
                bar.setSize(bar.getWidth() - (SpaceshipOrbit.width / 2 / twoxTimer) * delta, bar.getHeight());
            }
            else if (powerUpNumber == 2) {
                bar.setSize(bar.getWidth() - (SpaceshipOrbit.width / 2 / laserTimer) * delta, bar.getHeight());
            }
            else if (powerUpNumber == 3) {
                bar.setSize(bar.getWidth() - (SpaceshipOrbit.width / 2 / slowTimer) * delta, bar.getHeight());
            }
        }

        if (powerUp != null && powerUp.getColisionRect().x + powerUp.getColisionRect().getWidth() <= 0){
            powerUp = null;
            powerUpNumber = -1;
        }
    }

    private void isScoreChange(){

        if (GameWorld.score > currentScore){

            currentScore = GameWorld.score;
            createPowerUp();

        }
    }

    private void createPowerUp(){

        if (GameWorld.score > 3){

            if ((int)(Math.random() * 5) == 0){

                powerUpNumber = (int) (Math.random() * 4);

                if (powerUpNumber == 0){
                    powerUp = new PowerUp(Loader.twoxPowerUp);
                    bar = new Sprite(Loader.twoxTimeBar);
                    bar.setBounds(SpaceshipOrbit.width / 4, SpaceshipOrbit.width/12 + SpaceshipOrbit.width/24 + SpaceshipOrbit.width / 15, SpaceshipOrbit.width / 2, SpaceshipOrbit.width / 30);
                }
                if (powerUpNumber == 1){
                    powerUp = new PowerUp(Loader.livePowerUp);
                }
                if (powerUpNumber == 2){
                    powerUp = new PowerUp(Loader.laserPowerUp);
                    bar = new Sprite(Loader.laserTimeBar);
                    bar.setBounds(SpaceshipOrbit.width / 4, SpaceshipOrbit.width/12 + SpaceshipOrbit.width/24 + SpaceshipOrbit.width / 15, SpaceshipOrbit.width / 2, SpaceshipOrbit.width / 30);
                }
                if (powerUpNumber == 3){
                    powerUp = new PowerUp(Loader.slowPowerUp);
                    bar = new Sprite(Loader.slowTimeBar);
                    bar.setBounds(SpaceshipOrbit.width / 4, SpaceshipOrbit.width/12 + SpaceshipOrbit.width/24 + SpaceshipOrbit.width / 15, SpaceshipOrbit.width / 2, SpaceshipOrbit.width / 30);
                }
            }
        }
    }

    private void powerUp (float delta){

        if (isActive){

            timer +=delta;

            if (powerUpNumber == 0){
                GameWorld.factor = 2;
                if (timer >= twoxTimer){
                    GameWorld.factor = 1;
                    bar = null;
                    timer = 0f;
                    powerUpNumber = -1;
                    isActive = false;
                }
            }
            if (powerUpNumber == 1){
                GameWorld.live++;
                powerUpNumber = -1;
                isActive = false;
            }
            if (powerUpNumber == 2){
                GameWorld.isLaser = true;
                if (timer >= laserTimer){
                    GameWorld.isLaser = false;
                    bar = null;
                    timer = 0f;
                    powerUpNumber = -1;
                    isActive = false;
                }
            }
            if (powerUpNumber == 3){
                EnemyShotHandler.slow = 0.4f;
                if (timer >= slowTimer){
                    EnemyShotHandler.slow = 1f;
                    bar = null;
                    timer = 0f;
                    powerUpNumber = -1;
                    isActive = false;
                }
            }
        }

    }

    public void reset(){

        powerUp = null;
        powerUpNumber = -1;
        currentScore = GameWorld.score;

        timer = 0f;

        bar = null;

        isActive = false;
    }

    public void setPowerUpNumber(int powerUpNumber){
        this.powerUpNumber = powerUpNumber;
    }

    public int getPowerUpNumber(){
        return powerUpNumber;
    }

    public void setPowerUp(PowerUp powerUp){
        this.powerUp = powerUp;
    }

    public PowerUp getPowerUp(){
        return powerUp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
