package org.emg.stormRage.quests;

import org.emg.stormRage.StormRage;
import org.emg.stormRage.characters.Character;
import org.emg.stormRage.characters.Player;
import org.emg.stormRage.characters.Player.State;
import org.emg.stormRage.managers.SpriteManager;
import org.emg.stormRage.util.Util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Quest2 extends Character {
	float speed;
	
	public enum State{
		CANCEL, ACTIVE, COMPLETE, FINISHED
	}
	
	public State state;
	
	public Quest2(Animation animation, float x, float y, float speed) {
		super(animation, x, y);
		state = Quest2.State.CANCEL;
		this.speed = speed;
	}
	
	public void dialogoInicio(){
		String dialogo ="Ohhh, Que quieres? Tengo demasiados problemas en la cabeza. Un malvado dragón me lanzó un " +
				"maleficio y me convirtió en esta especie de rata mal oliente. He consultado con los magos mas poderosos" +
				"pero me han dicho que no hay solución facil, solo me puedo curar con una de las escamas del dragón." +
				"Si pudieras derrotarlo y traerme una de sus escamas te estaria eternamente agradecido.";
		SpriteManager.dialogo = dialogo;
		
		state = Quest2.State.ACTIVE;
		
		Util.npcText = true;
	}
	
	public void dialogoFin(){
		String dialogo ="Oh, si siii... Me la has traido. Espero que no tarden mucho en preparar el antidoto. ¿Hueles eso? espera, soy yo." +
				"No puedo seguir aguantando este olor.";
		SpriteManager.dialogo = dialogo;
		
		state = Quest2.State.ACTIVE;
		
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
