package org.emg.stormRage.screens;

import org.emg.stormRage.StormRage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen implements Screen {

	StormRage game;
	Stage stage;
	private Texture background1;
	
	public MainMenuScreen(StormRage game) {
		this.game = game;
		 Texture.setEnforcePotImages(false);
		background1 = new Texture("others/backgroundforest.png");

	}
	
	@Override
	public void show() {
		
		
	}
	
	@Override
	public void render(float dt) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Muestra un menú de inicio
		game.batch.begin();
		game.batch.draw(background1, 0, 0);
		game.font.draw(game.batch, "Bienvenido a StormRage!!!!", 100, 150);
		game.font.draw(game.batch, "Pulsa para empezar", 100, 130);
		game.font.draw(game.batch, "Pulsa 'ESCAPE' para SALIR", 100, 110);
		game.batch.end();
		/*
		 * Si el usuario toca la pantalla se inicia la partida
		 */
		if (Gdx.input.isTouched()) {
			System.out.println("TOQUE");
			game.setScreen(new GameScreen(game));
			dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
		
	}
	
	
	
	@Override
	public void dispose() {
		
		
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
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
