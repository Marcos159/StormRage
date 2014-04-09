package org.emg.stormRage.quests;

import org.emg.stormRage.StormRage;
import org.emg.stormRage.characters.Character;
import org.emg.stormRage.characters.Player;
import org.emg.stormRage.characters.Player.State;
import org.emg.stormRage.managers.SpriteManager;
import org.emg.stormRage.util.Util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Quest1 extends Character {
	float speed;
	
	public enum State{
		CANCEL, ACTIVE, COMPLETE, FINISHED
	}
	
	public State state;
	
	public Quest1(Animation animation, float x, float y, float speed) {
		super(animation, x, y);
		state = Quest1.State.CANCEL;
		this.speed = speed;
	}
	
	public void dialogoInicio(){
		String dialogo ="Ufff... Estoy helado con esta camisa pero es la unica defensa que tengo cuando salgo al bosque." +
				"Espera... Tengo una idea. Cuando salgas al bosque intenta buscar mis dados de la suerte. Si me los traes te daré mi " +
				"camisa y esta te protegerá en tus aventuras.";
		SpriteManager.dialogo = dialogo;
		
		state = Quest1.State.ACTIVE;
		
		Util.npcText = true;
	}
	
	public void controlador(){
		switch (state) {
		case CANCEL:
			dialogoInicio();
			break;
		case ACTIVE:
			//No hay q hacer nada
			break;
		case COMPLETE:
			break;
		case FINISHED:
			break;
		default:
			dialogoInicio();
		}
			
	}
	
	@Override
	public void update(float dt){
		super.update(dt);
				
		
	}
}
