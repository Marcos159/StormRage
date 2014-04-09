package org.emg.stormRage.screens;

import java.util.List;

import org.emg.stormRage.StormRage;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GameOverScreen implements Screen {
	
	
	
	StormRage game;
	Stage stage;

	private boolean done;
	
	public GameOverScreen(StormRage juego) {
		this.game = juego;
		
		
		done = false;
	}
	
	private void loadScreen() {
			
		
	}


	@Override
	public void render(float dt) {
		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		loadScreen();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		game.dispose();
	}
}

