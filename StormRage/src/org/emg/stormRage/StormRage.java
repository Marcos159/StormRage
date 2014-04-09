package org.emg.stormRage;



import org.emg.stormRage.managers.SpriteManager;
import org.emg.stormRage.screens.MainMenuScreen;
import org.emg.stormRage.util.Constants;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StormRage extends Game{
	public SpriteBatch batch;
	public BitmapFont font;
	OrthographicCamera camera;
	public boolean paused;
	
	public enum GameState {
		START, RESUME;
	}
	public GameState gameState; 
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Constants.SCREEN_WIDTH, 
			Constants.SCREEN_HEIGHT);
		camera.update();
		
		setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
	
}
