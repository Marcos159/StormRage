package org.emg.stormRage.managers;




import java.util.Iterator;

import org.emg.stormRage.StormRage;
import org.emg.stormRage.characters.Player;
import org.emg.stormRage.util.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SpriteManager {
	Player player;
	StormRage game;
	
	long momentoUltimoAtaque;
	long momentoUltimaSkill;
	public static String dialogo="";
	private Texture shield;
	public SpriteManager(StormRage game) {
		Util.npcText = false;
		shield = new Texture("characters/shield.png");
		player = new Player(ResourceManager.getAtlas("characters")
				.findRegion("pj_Move_up1"), 100, 100, 200, 100, 20, 20);
		this.game=game;
			
	}
	
	public void render(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		game.font.draw(game.batch, "dialogo", 100, 150 );
		game.font.draw(game.batch, dialogo, 100, 150 );
		player.render(batch);
		if(!player.damage){
			batch.draw(shield, player.position.x, player.position.y);
		}
		batch.end();
	}
		
	
	public void update(float dt) {
		handleInput(dt);
		checkCollisions();
		
		player.update(dt);
		
	}
	
	private void handleInput(float dt) {
		//despues de cada ataque hay un poqueño cooldown por el que no se puede mover ni realizar otro ataque
		//el cooldown es la velocidad de ataque de cada pj
		//Si el ataque da a algun enemigo este retrocede un poco
		if(Gdx.input.isKeyPressed(Keys.UP)){
			player.state = Player.State.UP;
			player.posicion = Player.Posicion.UP;
			player.movimiento = true;
			System.out.println("MOVIMIENTO UP");
			player.move(new Vector2(0,dt));	
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)){
			player.state = Player.State.DOWN;
			player.posicion = Player.Posicion.DOWN;
			
			player.movimiento = true;
			player.move(new Vector2(0,-dt));
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			player.state = Player.State.LEFT;
			player.posicion = Player.Posicion.LEFT;
			System.out.println("MOVIMIENTO LEFT");
			player.movimiento = true;
			player.move(new Vector2(-dt,0));
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			player.state = Player.State.RIGHT;
			player.posicion = Player.Posicion.RIGHT;
			player.movimiento = true;
			player.move(new Vector2(dt,0));
		}else if(Gdx.input.isKeyPressed(Keys.Q)){
			if (TimeUtils.nanoTime() - momentoUltimaSkill > Player.cooldown*1000000000){
				player.mainSkill();
				ResourceManager.getSound("shieldSound").play();
				momentoUltimaSkill = TimeUtils.nanoTime();
			}
		}else if (TimeUtils.nanoTime() - momentoUltimoAtaque > Player.attackSpeed*100000000){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				switch (player.posicion){
				case UP:
					player.state = Player.State.ATTACK_UP;
					player.posicion = Player.Posicion.UP;
					ResourceManager.getSound("attackLink").play();
					momentoUltimoAtaque = TimeUtils.nanoTime();
					break;
				case DOWN:
					player.state = Player.State.ATTACK_DOWN;
					player.posicion = Player.Posicion.DOWN;
					ResourceManager.getSound("attackLink").play();
					momentoUltimoAtaque = TimeUtils.nanoTime();	
					break;
				case RIGHT:
					player.state = Player.State.ATTACK_RIGHT;
					player.posicion = Player.Posicion.RIGHT;
					ResourceManager.getSound("attackLink").play();
					momentoUltimoAtaque = TimeUtils.nanoTime();
					break;
				case LEFT:
					player.state = Player.State.ATTACK_LEFT;
					player.posicion = Player.Posicion.LEFT;
					ResourceManager.getSound("attackLink").play();
					momentoUltimoAtaque = TimeUtils.nanoTime();	
					break;
				}
			}
		}
		//Cuando no pulsamos tecla de movimiento
		else{
			if(player.movimiento){
				switch (player.posicion){
				case UP:
					player.state = Player.State.IDLE_UP;
					break;
				case DOWN:
					player.state = Player.State.IDLE_DOWN;
					break;
				case RIGHT:
					player.state = Player.State.IDLE_RIGHT;
					break;
				case LEFT:
					player.state = Player.State.IDLE_LEFT;
					break;
				}
			}
		}
		
		
		
		if(Util.npcText == true){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				dialogo = "";
				Util.npcText = false;
			}
		}
		
	
	}
	
	
	private void checkCollisions() {
	
		
	}
}
