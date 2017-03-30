package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.News;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;

public class CreateMEM_02 implements Screen {

	ArrayList<Actor> buttons;

	TextButton back;
	TextButton next;
	News news;
	TextField title;
	public CreateMEM_02(News n) {
		
		news = n;
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
					Computer.CompScreen.changeScreen(new CreateMEM_01());
				}
			}
		});

		next = Staff.createBTN("Next", MemDev.WIDTH - 550, 200);
		next.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if (Staff.check(x, y, next.getWidth(), next.getHeight())) {
					Computer.CompScreen.clearScreen();
					Computer.CompScreen.changeScreen(new CreateMEM_03(news,title.getText()));
				}
			}
		});
		
	
		title = new TextField("", Staff.skin);
		title.setWidth(MemDev.WIDTH/4);
		title.setPosition(MemDev.WIDTH / 2 - title.getWidth() / 2,
				MemDev.HEIGHT / 2 + 100);
		
		Label label = new Label("Enter a title for your new M.E.M.", Staff.skin);
		label.setPosition(MemDev.WIDTH / 2 - label.getWidth() / 2, title.getY()+50);
		
		buttons.add(next);
		buttons.add(back);
		buttons.add(title);
		buttons.add(label);
	}

	@Override
	public ArrayList<Actor> getButtons() {
		return buttons;
	}

}
