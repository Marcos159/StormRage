package org.emg.stormRage.enemies;

import org.emg.stormRage.characters.Character;
import org.emg.stormRage.util.Posiciones;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Ghost extends Character {
	float speed;
	float hp=19;
	
	public Ghost(Animation animation, float x, float y, float speed) {
		super(animation, x, y);
	
		this.speed = speed;
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
		//TODO COMPROBAR ESTO LAS POSICIONES Y
			/*
			 if(position.x > Posiciones.posX){
				position.add(new Vector2(-dt * speed,-dt * speed ));
				rect.x = position.x;
				rect.y = position.y;
			}
			 */
			if(position.x > Posiciones.posX){
				position.add(new Vector2(-dt * speed,0 ));
				rect.x = position.x;
				rect.y = position.y;
			}
			if(position.x< Posiciones.posX){
				position.add(new Vector2(dt * speed,0 ));
				rect.x = position.x;
				rect.y = position.y;
			}
			if(position.y > Posiciones.posY){
				position.add(new Vector2(0, -dt * speed));
				rect.x = position.x;
				rect.y = position.y;
			}
			if(position.y< Posiciones.posY){
				position.add(new Vector2(0,dt * speed ));
				rect.x = position.x;
				rect.y = position.y;
			}
	}
}
