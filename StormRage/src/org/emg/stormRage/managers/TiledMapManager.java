package org.emg.stormRage.managers;

import java.util.Iterator;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

/**
 * Contiene métodos para trabajar con TiledMaps
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class TiledMapManager {

	// keys que identifican los tipos de tiles
	public static final String QUEST1 = "quest1";
	public static final String QUEST2 = "quest2";
	public static final String ENEMY = "enemy";
	public static final String GHOST = "ghost";
	public static final String BLOCK = "block";
	public static final String BLOCKED = "blocked";
	public static final String CHEST = "chest";
	public static final String ANIMATION = "animation";
	public static final String MOBILE = "mobile";
	public static final String WATER_UP = "water_up";
	public static final String WATER_DOWN = "water_down";
	
	public static TiledMapTileLayer collisionLayer;
	public static MapLayer objectLayer;
			
	
	/**
	 * Coloca tiles animados
	 * @param animationString Key que identifica al tipo de tile de animación
	 * @param n Número de tiles que componen la animación
	 */
	public static void animateTiles(String animationString, int n) {
		
		// Localiza los tiles anotados como animaciones en el tileset
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(n);
		Iterator<TiledMapTile> tiles = LevelManager.map.getTileSets().getTileSet("tileset").iterator();
		while (tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			if ((tile.getProperties().containsKey(ANIMATION)) && (tile.getProperties().get(ANIMATION, String.class).equals(animationString))) {
				frameTiles.add((StaticTiledMapTile) tile);
			}
		}
		
		// Crea un tile animado y le asigna las propiedades de todos los tiles que forman la animación
		AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(1 / 4f, frameTiles);
		// El Tile animado tiene que heredar todas las propiedades de los tiles estáticos que lo forman
		for (TiledMapTile tile : frameTiles)
			animatedTile.getProperties().putAll(tile.getProperties());
		
		// Coloca el tile animado donde haya un tile del mismo tipo pero estático
		for (int x = 0; x < collisionLayer.getWidth(); x++) {
			for (int y = 0; y < collisionLayer.getHeight(); y++) {
				Cell cell = collisionLayer.getCell(x, y);
				if (cell == null)
					continue;
				if (cell.getTile().getProperties().containsKey(ANIMATION) && 
					cell.getTile().getProperties().get(ANIMATION, String.class).equals(animationString)) {
					cell.setTile(animatedTile);
				}
			}
		}
	}
	
}
