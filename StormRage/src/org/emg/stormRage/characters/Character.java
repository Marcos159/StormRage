package org.emg.stormRage.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character {

	public Vector2 position;
	float speed;
	
	Animation animation;
	TextureRegion currentFrame;
	float stateTime;
	public Rectangle rect;
	
	public Character(Animation animation, float x, float y) {
		
		this.animation = animation;
		position = new Vector2(x, y);
		speed = 100f;
		rect = new Rectangle(x, y,
				animation.getKeyFrame(0).getRegionWidth(),
				animation.getKeyFrame(0).getRegionHeight());
	}
	
	public Character(TextureRegion texture, float x, float y) {
		
		currentFrame = texture;
		position = new Vector2(x, y);
		speed = 100f;
		rect = new Rectangle(x, y,
				currentFrame.getRegionWidth(),
				currentFrame.getRegionHeight());
	}
	
	public void render(SpriteBatch batch) {
		
		batch.draw(currentFrame, position.x, position.y);
	}
	
	public void update(float dt) {
		
		stateTime += dt;
		if (animation != null)
			currentFrame = animation.getKeyFrame(stateTime, true);
	}
}
