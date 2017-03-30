package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.Mem;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.News;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;

public class CreateMEM_03 implements Screen{
	
	ArrayList<Actor> buttons;

	TextButton back;
	TextButton create;
	News news;
	String title;
	
	public CreateMEM_03(News n, String t) {
		news = n;
		this.title = t;
		buttons = new ArrayList<Actor>();

		back = Staff.createBTN("Back", 200, 200);
		back.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (Staff.check(x, y, back.getWidth(), back.getHeight())) {
					Computer.CompScreen.clearScreen();
					Computer.CompScreen.changeScreen(new CreateMEM_02(news));
				}
			}
		});

		create = Staff.createBTN("Create", MemDev.WIDTH - 550, 200);
		create.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Computer.newMem(new Mem(title));
				Computer.CompScreen.clearScreen();
				Computer.CompScreen.changeScreen(new Main());
			}
		});
		
		Slider slider = new Slider(0, 100, 1, false, Staff.skin);
		slider.setWidth(200);
		slider.setPosition(300, 400);
		buttons.add(create);
		buttons.add(back);
		buttons.add(slider);
	}

	@Override
	public ArrayList<Actor> getButtons() {
		return buttons;
	}

}
