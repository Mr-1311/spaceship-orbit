package com.mtz.gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mtz.loader.Loader;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

import java.util.ArrayList;

/**
 * Created by metin on 09.07.2017.
 */

public class EnemyShotHandler implements GameObject{

    public static float slow;

    private ArrayList<Shot> enemyShots;
    private Texture shot, shot1, shot2, shot3;
    private Vector2 shotPosition;
    private float shotSize, r , height;

    private float angle;

    private float shotTime;
    private int attackType, currentScore, attackAmount, shotAngle;
    private boolean isAttackable, isAttackTypeChange;

    public EnemyShotHandler(ArrayList<Shot> enemyShots, Vector2 shotPosition, float shotSize) {

        this.shot = Loader.shot;
        this.shot1 = Loader.shot1;
        this.shot2 = Loader.shot2;
        this.shot3 = Loader.shot3;

        this.enemyShots = enemyShots;

        this.shotPosition = shotPosition;
        this.shotSize = shotSize;
        height = SpaceshipOrbit.width / 10;
        r = (SpaceshipOrbit.width - (height * 2)) / 2;

        shotTime = 0f;
        attackType = -1;
        currentScore = 0;
        isAttackable = false;
        isAttackTypeChange = false;

        slow = 1f;
    }

    @Override
    public void render(SpriteBatch batch) {

        for (int i = 0; i < enemyShots.size(); i++) {
            enemyShots.get(i).render(batch);
        }

    }

    @Override
    public void update(float delta) {

        for (int i = 0; i < enemyShots.size(); i++) {
            enemyShots.get(i).update(delta * slow);
        }

        if (isAttackTypeChange){
            shotTime +=delta;
            if (shotTime < 1f)
                return;

            shotTime = 0f;
            isAttackTypeChange = false;
        }

        shotTime += delta;
        setAttackType();

        if (attackType == 0){

            if (isAttackable || enemyShots.isEmpty()) {
                isAttackable = true;

                if (shotTime > 0.85f) {
                    shotTime = 0;
                    enemyShots.add(new Shot(shot1, new Vector2(shotPosition), shotSize, shotSize, new Vector2(shotDirection0()), calculateShotSpeed()));
                    attackAmount--;
                }
            }


            if (attackAmount == 0){
                attackType = -1;
                isAttackable = false;
            }
        }
        else if (attackType == 1){

            if (isAttackable || enemyShots.isEmpty()) {
                isAttackable = true;

                if (shotTime > 1.1f) {
                    shotTime = 0;
                    enemyShots.add(new Shot(shot2, new Vector2(shotPosition), shotSize, shotSize, new Vector2(shotDirection1()), calculateShotSpeed()));
                    attackAmount--;
                }
            }


            if (attackAmount == 0){
                attackType = -1;
                isAttackable = false;
            }
        }
        else if (attackType == 2){

            if (isAttackable || enemyShots.isEmpty()) {
                isAttackable = true;

                if (shotTime > 1.1f) {
                    shotTime = 0;
                    enemyShots.add(new Shot(shot3, new Vector2(shotPosition), shotSize, shotSize, new Vector2(shotDirection2()), calculateShotSpeed()));
                    attackAmount--;
                }
            }


            if (attackAmount == 0){
                attackType = -1;
                isAttackable = false;
            }
        }
        else if (attackType == -1){

            if (shotTime > 1.6f){
                shotTime = 0;
                enemyShots.add(new Shot(shot, new Vector2(shotPosition), shotSize, shotSize, new Vector2(shotDirection()), calculateShotSpeed(), this.angle));
            }
        }

    }


    private void setAttackType (){

        if (attackType == -1) {

            if (GameWorld.score != currentScore) {

                currentScore = GameWorld.score;

                if ((int) (Math.random() * 7) == 0) {

                    isAttackTypeChange = true;
                    shotTime = 0f;

                    int attack = (int) (Math.random() * 3);
                    shotAngle = (int) (Math.random() * 360);


                    if (attack == 0) {

                        attackAmount = 3 + (int) (Math.random() * 3);
                        attackType = 0;

                    }
                    if (attack == 1) {

                        attackAmount = 6 + (int) (Math.random() * 3);
                        attackType = 1;

                    }
                    if (attack == 2) {

                        attackAmount = 4;
                        attackType = 2;
                    }
                }
            }
        }

    }

    private Vector2 shotDirection(){

        float angle = (float) Math.toRadians((int)(Math.random() * 360));
        this.angle = angle;
        float x, y;

        x = (float) (SpaceshipOrbit.width/2 + r * Math.cos(angle));
        y = (float) (SpaceshipOrbit.height/2 - height + r * Math.sin(angle));

        return new Vector2(x, y);
    }
    private Vector2 shotDirection0(){

        float angle = (float) Math.toRadians((int)(Math.random() * 40) + shotAngle);
        this.angle = angle;
        float x, y;

        x = (float) (SpaceshipOrbit.width/2 + r * Math.cos(angle));
        y = (float) (SpaceshipOrbit.height/2 - height + r * Math.sin(angle));

        return new Vector2(x, y);
    }
    private Vector2 shotDirection1(){

        int angleJump;

        if (attackAmount % 2 == 0)
            angleJump = 52;
        else
            angleJump = -53;

        float angle = (float) Math.toRadians(shotAngle + angleJump);
        this.angle = angle;
        float x, y;

        x = (float) (SpaceshipOrbit.width/2 + r * Math.cos(angle));
        y = (float) (SpaceshipOrbit.height/2 - height + r * Math.sin(angle));

        return new Vector2(x, y);
    }
    private Vector2 shotDirection2(){

        float angle = (float) Math.toRadians(shotAngle + (90 * (4 - attackAmount)));
        this.angle = angle;
        float x, y;

        x = (float) (SpaceshipOrbit.width/2 + r * Math.cos(angle));
        y = (float) (SpaceshipOrbit.height/2 - height + r * Math.sin(angle));

        return new Vector2(x, y);
    }

    private float calculateShotSpeed() {

        //init shotspeed 0.4
        //ss increase to 0.8 between score 10 - 100
        //ss increase to 0.85 between score 100 - 150
        //ss increase to 0.9 between score 150 - 200
        //ss increase to 0.95 between score 200 - 250

        if (GameWorld.score < 100)
            return 0.4f + ((0.4f / 100) * GameWorld.score);

        if (GameWorld.score < 150)
            return 0.8f + ((0.05f / 50) * (GameWorld.score - 99));

        if (GameWorld.score < 200)
            return 0.85f + ((0.05f / 50) * (GameWorld.score - 149));

        if (GameWorld.score < 300)
            return 0.9f + ((0.05f / 100) * (GameWorld.score - 199));

        return 0.95f;

    }

    public void reset(){

        shotTime = 0f;
        attackType = -1;
        currentScore = 0;
        isAttackable = false;
        isAttackTypeChange = false;

        slow = 1f;
    }

}
