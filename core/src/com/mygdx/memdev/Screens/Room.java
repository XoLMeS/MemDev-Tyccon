package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.objects.Clock;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;
import com.mygdx.memdev.ui.Bar;

public class Room implements Screen{
	
	Bar health_bar;
	Bar stamina;
	Bar hunger;
	Computer comp;
	Clock clock_01;
	TextButton shop;
	
	ArrayList<Actor> actors;
	public Room(){
		actors = new ArrayList<Actor>();
		
		health_bar = new Bar(MemDev.WIDTH - MemDev.WIDTH / 10, MemDev.HEIGHT - 50, 128, 8, 100,
				"Health");
		stamina = new Bar(MemDev.WIDTH - MemDev.WIDTH / 10, MemDev.HEIGHT - 70, 128, 8, 100,
				"Stamina");
		hunger = new Bar(MemDev.WIDTH - MemDev.WIDTH / 10, MemDev.HEIGHT - 90, 128, 8, 100,
				"Hunger ");
		
		comp = new Computer();
		
		clock_01 = new Clock((float) 600,(float) 400);
		
		shop = new TextButton("Shop", Staff.skin);
		shop.setSize(200, 75);
		shop.setBounds(0, 0, shop.getWidth(), shop.getHeight());
		shop.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (Staff.check(x, y, shop.getWidth(), shop.getHeight())) {
					MemDev.changeScreen(1);
				}
			}
		});
		shop.setPosition(MemDev.WIDTH-210, 10);
		
		actors.add(health_bar);
		actors.add(stamina);
		actors.add(hunger);
		actors.add(comp);
		actors.add(clock_01);
		actors.add(shop);
	
	}
	
	
	@Override
	public ArrayList<Actor> getButtons() {
		return actors;
	}

}
