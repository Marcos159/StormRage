package org.emg.stormRage.managers;

import org.emg.stormRage.StormRage;
import org.emg.stormRage.characters.Player;
import org.emg.stormRage.enemies.Enemy;
import org.emg.stormRage.enemies.Ghost;
import org.emg.stormRage.quests.Quest1;
import org.emg.stormRage.quests.Quest2;
import org.emg.stormRage.util.Constants;
import org.emg.stormRage.util.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class SpriteManager {

	StormRage game;
	
	long momentoUltimoAtaque;
	long momentoUltimaSkill;
	
	public static String dialogo="";
	
	//PLAYER
	Player player;
	private Texture shield;
	
	//ENIMIGOS
	public  Array<Ghost> ghosts;
	public  Array<Enemy> enemies;
	
	//NPCS
	public  Array<Quest1> quests1;
	public  Array<Quest2> quests2;
	
	
	// Pool de rectángulos (mejora la eficiencia si se trabaja con muchos)
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject () {
			return new Rectangle();
		}
	};
	
	public OrthographicCamera camera;
	public static float CAMERA_OFFSET = 0;
	public SpriteBatch batch;
	OrthogonalTiledMapRenderer mapRenderer;
	
	public SpriteManager(StormRage game) {
		Util.npcText = false;
		shield = new Texture("characters/shield.png");
		
		player = new Player(ResourceManager.getAtlas("characters")
				.findRegion("pj_Move_up1"), 100, 100, 200, 100, 20, 20);
		
		ghosts = new Array<Ghost>();
		enemies = new Array<Enemy>();
		
		quests1 = new Array<Quest1>();
		quests2 = new Array<Quest2>();
		
		this.game=game;
		
		// Crea una cámara 
		camera = new OrthographicCamera();
		//TODO ESTO
		camera.setToOrtho(false, 32, 32);
		camera.zoom = 1 / 2f;
		camera.update();	
		mapRenderer = new OrthogonalTiledMapRenderer(new TmxMapLoader().load(LevelManager.getCurrentLevelPath()));
		batch = mapRenderer.getSpriteBatch();
			
	}
	
	public void render(SpriteBatch batch) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Fija la cámara para seguir al personaje en el centro de la pantalla
		camera.position.set(player.position.x, player.position.y + 100, 0);
		
		camera.update();
		mapRenderer.setView(camera);
		mapRenderer.render();
				
		batch.begin();		
		game.font.draw(game.batch, "dialogo", 100, 150 );
		game.font.draw(game.batch, dialogo, 100, 150 );
		
		//Player
		player.render(batch);
		if(!player.damage){
			batch.draw(shield, player.position.x, player.position.y);
		}
		
		//ENEMIGOS
		for (Enemy enemy : enemies)
			enemy.render(batch);
		for (Ghost ghost : ghosts)
			ghost.render(batch);
		
		//NPCS
		for (Quest1 quest1 : quests1)
			quest1.render(batch);
		for (Quest2 quest2 : quests2)
			quest2.render(batch);
		
		batch.end();
	}
		
	
	public void update(float dt) {
		handleInput(dt);
		checkCollisions(dt);
		updatenpcCamara(dt);
		player.update(dt);
		
		for (Enemy enemy : enemies)
			enemy.update(dt);
		for (Ghost ghost : ghosts)
			ghost.update(dt);
		
		for (Quest1 quest1 : quests1)
			quest1.update(dt);
		for (Quest2 quest2 : quests2)
			quest2.update(dt);
		
	}
	
	public void updatenpcCamara(float dt){
		/*// Comprueba el estado de los enemigos
				for (Enemy enemy : LevelManager.enemies) {
					
					// Si la cámara no los enfoca no se actualizan
					if (!game.gameRenderer.camera.frustum.pointInFrustum(new Vector3(enemy.position.x, enemy.position.y, 0)))
						continue;
				
					if (enemy.isAlive)
						enemy.update(dt);
					else
						LevelManager.enemies.removeValue(enemy, true);
				}*/
	}
	
	private void handleInput(float dt) {
		if(!player.root){
			if(Gdx.input.isKeyPressed(Keys.UP)){
				player.state = Player.State.UP;
				player.posicion = Player.Posicion.UP;
				System.out.println("MOVIMIENTO UP");
				player.move(new Vector2(0,dt));	
			}
			else if(Gdx.input.isKeyPressed(Keys.DOWN)){
				player.state = Player.State.DOWN;
				player.posicion = Player.Posicion.DOWN;
				player.move(new Vector2(0,-dt));
			}
			else if(Gdx.input.isKeyPressed(Keys.LEFT)){
				player.state = Player.State.LEFT;
				player.posicion = Player.Posicion.LEFT;
				System.out.println("MOVIMIENTO LEFT");
				player.move(new Vector2(-dt,0));
			}
			else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				player.state = Player.State.RIGHT;
				player.posicion = Player.Posicion.RIGHT;
				player.move(new Vector2(dt,0));
			}else if(Gdx.input.isKeyPressed(Keys.Q)){
				if (TimeUtils.nanoTime() - momentoUltimaSkill > Player.cooldown*1000000000){
					player.mainSkill();
					ResourceManager.getSound("shieldSound").play();
					momentoUltimaSkill = TimeUtils.nanoTime();
				}
			
			}
			else{
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
		if (TimeUtils.nanoTime() - momentoUltimoAtaque > Player.attackSpeed*100000000){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				player.root=true;
				Timer.schedule(new Task(){
				    @Override
				    public void run() {
				    	System.out.println("TERMINO");
				    	player.root = false;
				    }
				}, 1);
				switch (player.posicion){
				case UP:
					player.state = Player.State.ATTACK_UP;
					player.posicion = Player.Posicion.UP;
					ResourceManager.getSound("attackLink").play();
					System.out.println("Ataque up");
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
		
		if(Util.npcText){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				dialogo = "";
				Util.npcText = false;
			}
		}
		
	}

	
	private void checkCollisions(float dt) {
		Array<Rectangle> tiles = new Array<Rectangle>();
		
		// Recoge las celdas de la posición del jugador	
		int startX = (int) (player.position.x + player.speed);
		int endX = (int) (player.position.x + Constants.BRICK_WIDTH + player.speed);
		int startY = (int) (player.position.y + player.speed);
		int endY = (int) (player.position.y + Constants.BRICK_HEIGHT + player.speed);
		getTilesPosition(startX, startY, endX, endY, tiles);
		// Comprueba las colisiones con el jugador
		for (Rectangle tile : tiles) {
			if (tile.overlaps(player.rect)) {
				switch (player.posicion) {
				case LEFT:
					player.position.x = tile.getX();
					break;
				case RIGHT:
					player.position.x = tile.getX() - Constants.BRICK_WIDTH - player.speed;
					break;
				case UP:
					player.position.y = tile.getY() - Constants.BRICK_HEIGHT - player.speed;
					break;
				case DOWN:
					player.position.y = tile.getY() + player.speed + 1;
					break;
				default:
				}
				
			}
		}
		
	}
	
	/**
	 * Obtiene la lista de celdas de interés en las que está situado el jugador
	 * Ahora mismo sólo comprueba las celdas de las casas
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param tiles
	 */
	private void getTilesPosition(int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
		
		tiles.clear();
	
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				int xCell = (int) (x / TiledMapManager.collisionLayer.getTileWidth());
				int yCell = (int) (y / TiledMapManager.collisionLayer.getTileHeight());
				Cell cell = TiledMapManager.collisionLayer.getCell(xCell, yCell);
				
				// Si es un bloque se añade para comprobar colisiones
				if ((cell != null) && (cell.getTile().getProperties().containsKey("block"))) {
					Rectangle rect = rectPool.obtain();
					rect.set((int) (Math.ceil(x / 32f) * 32), (int) (Math.ceil(y / 32f) * 32), 0, 0);
					tiles.add(rect);
				}
			}
		}
	}
public void resize(int width, int height) {
		
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
	}
}
