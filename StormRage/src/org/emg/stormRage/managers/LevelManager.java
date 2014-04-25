package org.emg.stormRage.managers;

import org.emg.stormRage.enemies.Enemy;
import org.emg.stormRage.enemies.Ghost;
import org.emg.stormRage.quests.Quest1;
import org.emg.stormRage.quests.Quest2;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;



public class LevelManager {

	SpriteManager spriteManager;
	public static int currentLevel;
	// Info del LevelManager
	public static final String LEVEL_DIR = "levels";
	public static final String LEVEL_PREFIX = "levelredim";
	public static final String LEVEL_EXTENSION = ".tmx";
	// Mapa del nivel actual
	public static TiledMap map;
	
	
	
	public static String getCurrentLevelName() {
		return LEVEL_PREFIX;
	}
	
	public static String getCurrentLevelPath() {
		return LEVEL_DIR + "/" + LevelManager.getCurrentLevelName() + LEVEL_EXTENSION;
	}
	
	public LevelManager(SpriteManager spriteManager) {
		this.spriteManager = spriteManager;
		
	}
	
	/**
	 * Carga el mapa de la pantalla actual
	 */
	public void loadMap() {
		map = new TmxMapLoader().load(LevelManager.getCurrentLevelPath());
		TiledMapManager.collisionLayer = (TiledMapTileLayer) LevelManager.map.getLayers().get("terrain");
		TiledMapManager.objectLayer = (MapLayer) LevelManager.map.getLayers().get("objects");
		
		loadEnemies();
		loadNpcs();
	}
	
	/**
	 * Carga los enemigos del nivel actual
	 */
	private void loadEnemies() {
		
		Enemy enemy = null;
		Ghost ghost = null;
		
		// Carga los objetos móviles del nivel actual
		for (MapObject object : LevelManager.map.getLayers().get("objects").getObjects()) {
			
			if (object instanceof RectangleMapObject) {
				RectangleMapObject rectangleObject = (RectangleMapObject) object;
				if (rectangleObject.getProperties().containsKey(TiledMapManager.ENEMY)) {
					Rectangle rect = rectangleObject.getRectangle();	
					enemy = new Enemy(ResourceManager.getAnimation("enemy"), rect.x , rect.y, 90);
					//enemy.position.set(rect.x, rect.y);
					spriteManager.enemies.add(enemy);
				}
				if (rectangleObject.getProperties().containsKey(TiledMapManager.GHOST)) {
					Rectangle rect = rectangleObject.getRectangle();
					ghost = new Ghost(ResourceManager.getAnimation("fantasma"), rect.x , rect.y, 90);
					spriteManager.ghosts.add(ghost);
					System.out.println("Carga");
				}
			}
		}
	}
	/**
	 * Carga los npc del nivel actual
	 */
	private void loadNpcs() {
		
		Quest1 quest1 = null;
		Quest2 quest2 = null;
		
		// Carga los objetos móviles del nivel actual
		for (MapObject object : LevelManager.map.getLayers().get("objects").getObjects()) {
			if (object instanceof RectangleMapObject) {
				RectangleMapObject rectangleObject = (RectangleMapObject) object;
				if (rectangleObject.getProperties().containsKey(TiledMapManager.QUEST1)) {
					Rectangle rect = rectangleObject.getRectangle();
					quest1 = new Quest1(ResourceManager.getAnimation("quest1"), rect.x , rect.y, 90);
					spriteManager.quests1.add(quest1);
				}
				if (rectangleObject.getProperties().containsKey(TiledMapManager.QUEST2)) {
					Rectangle rect = rectangleObject.getRectangle();
					quest2 = new Quest2(ResourceManager.getAnimation("quest2"), rect.x , rect.y, 90);
					spriteManager.quests2.add(quest2);
				}
			}
		}
	}
	
	
}
