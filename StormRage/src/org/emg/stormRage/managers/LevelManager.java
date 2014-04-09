package org.emg.stormRage.managers;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;



public class LevelManager {

	SpriteManager spriteManager;
	public static int currentLevel;
	
	// Info del LevelManager
	public static final String LEVEL_DIR = "levels";
	public static final String LEVEL_PREFIX = "level";
	public static final String LEVEL_EXTENSION = ".tmx";
	// Mapa del nivel actual
	public static TiledMap map;
		
	public LevelManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
		currentLevel =1;
	}
	
	public static String getCurrentLevelName() {
		return LEVEL_PREFIX + LevelManager.currentLevel;
	}
	
	public static String getCurrentLevelPath() {
		return LEVEL_DIR + "/" + LevelManager.getCurrentLevelName() + LEVEL_EXTENSION;
	}
	
	/**
	 * Carga el mapa de la pantalla actual
	 */
	public static void loadMap() {
		
		LevelManager.map = new TmxMapLoader().load(LevelManager.getCurrentLevelPath());
		TiledMapManager.collisionLayer = (TiledMapTileLayer) LevelManager.map.getLayers().get("terrain");
		TiledMapManager.objectLayer = (MapLayer) LevelManager.map.getLayers().get("objects");
		
	}
	
	
	
}
