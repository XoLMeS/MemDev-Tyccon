package com.mygdx.memdev;

import java.io.File;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.memdev.Screens.CreateMEM_02;
import com.mygdx.memdev.objects.Computer;
import com.mygdx.memdev.tools.Staff;

public class News extends Actor {
	
	public Sprite img;
	public Sprite bgr;
	String descr;
	float pop;
	
	public TextButton butt;
	public int id;
	
	public News(String file) {

		if (files_exists(file)) {
			File news = new File("assets/news/" + file + ".txt");
			img = new Sprite(new Texture("assets/news/" + file + ".png"));
			img.setBounds(0, 0, MemDev.WIDTH/5, MemDev.HEIGHT/5);
			
			Staff.openFile(news);
			pop = Float.valueOf(Staff.nextString());
			descr = Staff.nextString();
			Staff.closeFile();

			butt = new TextButton(descr, Staff.skin);
			butt.setWidth(MemDev.WIDTH/3+200);
			butt.setHeight(400);
			
			butt.setBounds(0, 0, butt.getWidth(), butt.getHeight());
			
			if(pop<=3){
				butt.setColor(0.7f, 0.4f, 0.0f, 1.0f);
			}
			else if(pop <=7){
				butt.setColor(0.7f, 0.7f, 0.7f, 1.0f);
			}
			else {
				butt.setColor(1.0f, 0.7f, 0.0f, 1.0f);
			}
			
			
			butt.addListener(new InputListener() {

				public boolean touchDown(InputEvent event, float x1, float y1,
						int pointer, int button) {

					return true;

				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					if (Staff.check(x, y, butt.getWidth(), butt.getHeight())) {
						Computer.CompScreen.clearScreen();
						Computer.CompScreen.changeScreen(new CreateMEM_02(Computer.getNews().get(id)));
					}
				}
			});

			
		
			
		}
		else {
			// DOTO ALERT	
		}
	}
	

	private boolean files_exists(String file) {
		File check = new File("assets/news/" + file + ".txt");
		File check2 = new File("assets/news/" + file + ".png");
		if (check.exists() && check2.exists()) {
			return true;
		}
		return false;
	}
	

}
