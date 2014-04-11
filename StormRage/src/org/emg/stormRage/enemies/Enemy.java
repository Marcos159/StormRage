package org.emg.stormRage.enemies;



import org.emg.stormRage.characters.Character;
import org.emg.stormRage.util.Posiciones;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * Clase que representa a todos los enemigos
 * que me pueden atropellar y matar:
 * Coches, gruas y camiones
 * @author Santiago Faci
 *
 */


public class Enemy extends Character {
	float speed;
	float hp=45;
	
	public Enemy(Animation animation, float x, float y, float speed) {
		super(animation, x, y);
		
		
		this.speed = speed;
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
		
	}
}
