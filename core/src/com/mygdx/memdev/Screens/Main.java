package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;

public class Main implements Screen{
	
	ArrayList<Actor> buttons;
	TextButton comp_memes;
	TextButton uncomp_memes;
	TextButton create_memes;
	TextButton show_news;
	
	public Main(){
		buttons = new ArrayList<Actor>();
		
		comp_memes = Staff.createBTN("Completed", 300, 200);
		comp_memes.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});

		//
		uncomp_memes = Staff.createBTN("Uncompleted", 700, 200);
		uncomp_memes.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

			}
		});

		//
		create_memes = Staff.createBTN("New M.E.M.", 300, 450);
		create_memes.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (Staff.check(x, y, create_memes.getWidth(),
						create_memes.getHeight())) {

					Computer.CompScreen.clearScreen();
					Computer.CompScreen.changeScreen(new CreateMEM_01());
				}
			}
		});

		//
		show_news = Staff.createBTN("News", 700, 450);
		show_news.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		
		buttons.add(comp_memes);
		buttons.add(create_memes);
		buttons.add(show_news);
		buttons.add(uncomp_memes);
		
		}
	
	public ArrayList<Actor> getButtons() {
		// TODO Auto-generated method stub
		return buttons;
	}
	
	
}
