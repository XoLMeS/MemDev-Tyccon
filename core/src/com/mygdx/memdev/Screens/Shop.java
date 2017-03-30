package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.tools.Staff;

public class Shop implements Screen{

	
	ArrayList<Actor> actors;
	TextButton home;
	public Shop(){
		actors = new ArrayList<Actor>();
		
		home = new TextButton("Home", Staff.skin);
		home.setSize(200, 75);
		home.setBounds(0, 0, home.getWidth(), home.getHeight());
		home.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (Staff.check(x, y, home.getWidth(), home.getHeight())) {
					MemDev.changeScreen(0);
				}
			}
		});
		home.setPosition(10, 10);
		
		actors.add(home);
		
	}
	@Override
	public ArrayList<Actor> getButtons() {
		return actors;
	}

}
