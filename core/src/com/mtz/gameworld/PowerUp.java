package com.mtz.gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

/**
 * Created by metin on 12.07.2017.
 */

public class PowerUp implements GameObject{

    private Sprite powerUp;
    private float xKord, yKord;
    private Rectangle colisionRect;

    public PowerUp(Texture powerUp){

        yKord = SpaceshipOrbit.height/2 - SpaceshipOrbit.width / 18 - ((SpaceshipOrbit.width - (SpaceshipOrbit.width / 10 * 2)) / 2) + ((float) (Math.random() * (((SpaceshipOrbit.width - (SpaceshipOrbit.width / 10 * 2)) - SpaceshipOrbit.width / 18) - SpaceshipOrbit.width/14)));

        if (yKord > SpaceshipOrbit.height/2 - ((SpaceshipOrbit.width / 10)/2) - SpaceshipOrbit.width / 10 && yKord < SpaceshipOrbit.height/2 - ((SpaceshipOrbit.width / 10)/2)){

            if (yKord < (SpaceshipOrbit.height/2 - 2*((SpaceshipOrbit.width / 10)/2))){
                yKord -= SpaceshipOrbit.width / 13;
            }
            yKord += SpaceshipOrbit.width / 13;

        }

        xKord = SpaceshipOrbit.width;

        colisionRect = new Rectangle(xKord, yKord, SpaceshipOrbit.width/14, SpaceshipOrbit.width/14);

        this.powerUp = new Sprite(powerUp);
        this.powerUp.flip(true, false);
        this.powerUp.setBounds(xKord, yKord, SpaceshipOrbit.width/14, SpaceshipOrbit.width/14);
        this.powerUp.setOriginCenter();

    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();

        powerUp.draw(batch);

        batch.end();

    }

    @Override
    public void update(float delta) {

        xKord -= (SpaceshipOrbit.width / 8) * delta;
        powerUp.setX(xKord);
        powerUp.rotate(-80 * delta);

        colisionRect.setX(xKord);

    }

    public Rectangle getColisionRect(){
        return colisionRect;
    }
}
