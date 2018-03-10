package com.mtz.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayStateButtons extends Button{
	
	private Sprite texture;
	private float alpha;
	private boolean flag;

	public PlayStateButtons(float xKord, float yKord, float width, float height, Texture texture) {
		
		super(xKord, yKord, width, height, texture);
		
		this.texture = new Sprite(texture);
		this.texture.setFlip(false, true);
		this.texture.setBounds(xKord, yKord, width, height);

        this.flag = true;

		alpha = 0f;
		
		this.texture.setAlpha(alpha);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.begin();
		
		texture.draw(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		if (flag){
		    if (alpha < 1){
			    alpha += delta;
			    if (alpha > 1)
				    alpha = 1;
			    texture.setAlpha(alpha);
		    }
		}
	}

	public void setAlpha(boolean show){

        if(show){
            flag = true;
            texture.setAlpha(1f);
        }
        else {
            flag = false;
            texture.setAlpha(0f);
        }
    }

	public Sprite getTexture() {
		return texture;
	}

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
