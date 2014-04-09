package org.emg.stormRage.managers;

import java.util.HashMap;
import java.util.Map;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceManager {
	private static Map<String, Texture> textures = new HashMap<String, Texture>();
	private static Map<String, Animation> animations =
			new HashMap<String, Animation>();
		private static Map<String, TextureAtlas> atlas =
			new HashMap<String, TextureAtlas>();
		private static Map<String, Sound> sounds =
			new HashMap<String, Sound>();
		
		private static Map<String, Music> musics =
				new HashMap<String, Music>();
		
		public static void loadAllResources() {		
			//Cargamos Texturas
			
			
			//Cargamos animaciones
			loadResource("characters", new TextureAtlas(
					Gdx.files.internal("characters/characters.pack")));
			
			animations.put("player_Move_up",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Move_up1"),
					getAtlas("characters").findRegion("pj_Move_up2")));
			animations.put("player_Atack_up",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Atack_up1"),
					getAtlas("characters").findRegion("pj_Atack_up2"),
					getAtlas("characters").findRegion("pj_Atack_up3"),
					getAtlas("characters").findRegion("pj_Atack_up4")));
			animations.put("player_Idle_up",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Idle_up1")));
			
			animations.put("player_Move_down",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Move_down1"),
					getAtlas("characters").findRegion("pj_Move_down2")));
			animations.put("player_Atack_down",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Atack_down1"),
					getAtlas("characters").findRegion("pj_Atack_down2"),
					getAtlas("characters").findRegion("pj_Atack_down3"),
					getAtlas("characters").findRegion("pj_Atack_down4")));
			animations.put("player_Idle_down",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Idle_down1")));
			
			animations.put("player_Move_left",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Move_left1"),
					getAtlas("characters").findRegion("pj_Move_left2")));
			animations.put("player_Atack_left",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Atack_left1"),
					getAtlas("characters").findRegion("pj_Atack_left2"),
					getAtlas("characters").findRegion("pj_Atack_left3"),
					getAtlas("characters").findRegion("pj_Atack_left4")));
			animations.put("player_Idle_left",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Idle_left1")));
			
			animations.put("player_Move_right",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Move_right1"),
					getAtlas("characters").findRegion("pj_Move_right2")));
			animations.put("player_Atack_right",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Atack_right1"),
					getAtlas("characters").findRegion("pj_Atack_right2"),
					getAtlas("characters").findRegion("pj_Atack_right3"),
					getAtlas("characters").findRegion("pj_Atack_right4")));
			animations.put("player_Idle_right",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Idle_right1")));
			animations.put("player_Shield",
					new Animation(0.25f, 
					getAtlas("characters").findRegion("pj_Idle_right1")));
			//Cargamos sonidos
			ResourceManager.loadResource("attackLink", Gdx.audio.newSound(Gdx.files.internal("sounds/attackLink.mp3")));
			ResourceManager.loadResource("shieldSound", Gdx.audio.newSound(Gdx.files.internal("sounds/shieldSound.mp3")));
		
		}

	
		private static void loadResource(String name, TextureAtlas textureAtlas) {
				
				atlas.put(name, textureAtlas);
			}
		
		
		public static TextureAtlas getAtlas(String name) {
			
			return atlas.get(name);
		}

		public static Map<String, Sound> getSounds() {
			return sounds;
		}
		
		public static Animation getAnimation(String name) {
			
			return animations.get(name);
		}
		/**
		 * Carga un recurso de imagen en memoria
		 * @param name
		 * @param resource
		 */
		public static void loadResource(String name, Texture resource) {
			
			textures.put(name, resource);
		}
		/**
		 * Obtiene un recurso de imagen de memoria
		 * @param name
		 * @return
		 */
		public static Texture getTexture(String name) {
			
			return textures.get(name);
		}
		/**
		 * Carga un recurso de sonido en memoria
		 * @param name
		 * @param sound
		 */
		public static void loadResource(String name, Music music) {
			
			musics.put(name, music);
		}
		/**
		 * Obtiene un recurso de sonido de memoria
		 * @param name
		 * @return
		 */
		public static Music getMusic(String name) {
			
			return  musics.get(name);
		}
		
		/**
		 * Carga un recurso de sonido en memoria
		 * @param name
		 * @param sound
		 */
		public static void loadResource(String name, Sound sound) {
			
			sounds.put(name, sound);
		}
		/**
		 * Obtiene un recurso de sonido de memoria
		 * @param name
		 * @return
		 */
		public static Sound getSound(String name) {
			
			return sounds.get(name);
		}
	
}
