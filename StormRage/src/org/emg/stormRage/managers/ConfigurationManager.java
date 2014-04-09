package org.emg.stormRage.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Clase que gestiona la configuración del juego
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class ConfigurationManager {

	private Preferences prefs;
	
	public ConfigurationManager() {
		
	}
	
	public boolean isSoundEnabled() {
		
		return prefs.getBoolean("sound");
	}
	
}
