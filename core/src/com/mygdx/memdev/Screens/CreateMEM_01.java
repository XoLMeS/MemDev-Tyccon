package com.mygdx.memdev.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.News;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;

public class CreateMEM_01 implements Screen {

	ArrayList<Actor> buttons;

	TextButton back;
	TextButton next;

	public CreateMEM_01() {
		buttons = new ArrayList<Actor>();

		back = new TextButton("Back",Staff.skin);
		back.setWidth(150);
		back.setHeight(100);
		back.setX(10);
		back.setY(MemDev.HEIGHT-170);
		back.setColor(Color.RED);
		back.addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (Staff.check(x, y, back.getWidth(), back.getHeight())) {
					Computer.CompScreen.clearScreen();
					Computer.CompScreen.changeScreen(new Main());
				}
			}
		});

		
		buttons.add(back);
		
		
	
		ArrayList<News> news = Computer.getNews();
		
		Table container = new Table();
		container.setFillParent(true);
	    Table table = new Table();
	    table.pad(5).defaults().expandX().space(5);
	    
	    ScrollPane pane = new ScrollPane(table);
	    container.add(pane).width(MemDev.WIDTH-400).height(MemDev.HEIGHT-190);
	    container.row();
	    container.setBounds(0,30,MemDev.WIDTH-200,MemDev.HEIGHT-200);
	    
	    int id = 0;
		for(News n: news){
			n.id = id;
			id++;

			
			//Slider slider = new Slider(0, 100, 1, false, Staff.skin);
			//table.add(slider);
			table.row();
			table.add(n.butt).size(MemDev.WIDTH/3+200, MemDev.HEIGHT/4);
			
			table.add(new Image(n.img)).size(MemDev.WIDTH/5, MemDev.HEIGHT/5);
			
			table.row();
		}
	
		
		
		buttons.add(container);
		
		
	}

	@Override
	public ArrayList<Actor> getButtons() {
		return buttons;
	}

}
