/*********************************************************************
*                                                                    *
*      AIME by Corentin "The Rock" Larroche and Pierre Vennin        *
*                      Côté Smartphone                               *
*                                                                    *
**********************************************************************/

package com.mines.aime;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import ca.weblite.codename1.net.Socket;

import java.io.IOException;
import com.codename1.sensors.SensorListener;
import com.codename1.sensors.SensorsManager;
import com.codename1.ui.Toolbar;

// Il faut intégrer 2 librairies au format cn1lib : codenambe-sensors et CN1Socket
//No judgment sur le code oh, je suis pas un geek de MSI moi... J'ai essayé de faire le plus compact possible.

public class Aime{

	private final String HOST = "192.168.0.14";
	private final int PORT = 9999;

	private Form current;
	private Resources theme;


	public void init(Object context) {
		theme = UIManager.initFirstTheme("/theme");
		Toolbar.setGlobalToolbar(true);
	}

	public void start() {
		if(current != null){
			current.show();
			return;
		}

		try {
			final Socket sock = new Socket(HOST, PORT);
			while (!sock.isConnected()){
				sock.connect();
			}
			SensorsManager accelerometer = SensorsManager.getSenorsManager(SensorsManager.TYPE_ACCELEROMETER); 
			accelerometer.registerListener(new SensorListener() {
				//C'est crado à la volée hein comme ça ? Hummm :-)
				public void onSensorChanged(long time, float x, float y, float z){
					String sample = Long.toString(time) + ";"  + Float.toString(x) + ";"  + Float.toString(y) + ";"  + Float.toString(z) + "\n";
					//y'a sûrement moyen de gagner en fréquence ici : peut-être faire un thread par paquet ?
					//Je sais pas si ça se fait...
					try {
						sock.getOutputStream().write(sample.getBytes());
					} catch (IOException e) {
						//Ballec
					}
				}
			}); 
		}catch (IOException e) {

		}


	}


	public void stop() {
		current = Display.getInstance().getCurrent();
		if(current instanceof Dialog) {
			((Dialog)current).dispose();
			current = Display.getInstance().getCurrent();
		}
	}

	public void destroy() {
	}



}