package com.mtz.spaceshiporbit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mtz.spaceshiporbit.SpaceshipOrbit;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//480 800
		config.width = (int)(480);
		config.height = (int)(800);
		config.title = "Spaceship Orbit";
		
		new LwjglApplication(new SpaceshipOrbit(), config);
	}
}
