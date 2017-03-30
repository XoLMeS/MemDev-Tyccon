package com.mygdx.memdev.desktop;

import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.memdev.MemDev;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		  config.title = "MemDev Tycoon";
	      config.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4*3;
	      config.height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4*3;
	      config.useGL30 = true;
	      config.resizable = false;
		new LwjglApplication(new MemDev(), config);
		System.out.println("'"+config.title + "' started. " + config.width + "x" + config.height);
	}
}
