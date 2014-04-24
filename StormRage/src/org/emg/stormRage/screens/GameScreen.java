package org.emg.stormRage.screens;

import org.emg.stormRage.StormRage;

import org.emg.stormRage.managers.LevelManager;
import org.emg.stormRage.managers.ResourceManager;
import org.emg.stormRage.managers.SpriteManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
	
	StormRage game;
	LevelManager levelManager;
	SpriteManager spriteManager;
	
	public GameScreen(StormRage game) {
		this.game=game;
		
		ResourceManager.loadAllResources();
		
		Texture.setEnforcePotImages(false);
		
		spriteManager = new SpriteManager(game);
		levelManager = new LevelManager(spriteManager);
		
		levelManager.loadMap();
	}
	
	@Override
	public void show() {
		
		
	}
	
	@Override
	public void render(float dt) {
		spriteManager.update(dt);
		spriteManager.render(game.batch);
	}
	
	public void terminarPartida(){
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		spriteManager.resize(width, height);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
