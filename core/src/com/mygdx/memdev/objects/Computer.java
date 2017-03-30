package com.mygdx.memdev.objects;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.memdev.Mem;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.News;
import com.mygdx.memdev.Screens.Main;
import com.mygdx.memdev.Screens.Screen;
import com.mygdx.memdev.tools.Staff;

public class Computer extends Actor {
	
	public static Screen screen;

	static ArrayList<Mem> completed_memes;
	static ArrayList<Mem> uncompleted_memes;
	
	static int current = -1;
	
	static ArrayList<News> news;
	Sprite texture;
	float actorX = 100, actorY = 300;

	CompScreen cs;

	public Computer() {
		
		completed_memes = new ArrayList<Mem>();
		uncompleted_memes = new ArrayList<Mem>();
		
		news = new ArrayList<News>();
		loadNews();
		
		texture = new Sprite(new Texture(
				Gdx.files.internal("assets/computer.png")));
		texture.setBounds(0, 0,
				MemDev.WIDTH / 10, MemDev.HEIGHT / 8);
		texture.setPosition(actorX, actorY);
		setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
		addListener(new InputListener() {

			public boolean touchDown(InputEvent event, float x1, float y1,
					int pointer, int button) {

				return true;

			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				//if (Staff.check(x, y, actorX, actorY, texture.getWidth(), texture.getHeight())) {

					cs = new CompScreen();
					MemDev.background_stage.addActor(cs);
					Main m = new Main();
					CompScreen.changeScreen(m);
					MemDev.bg_active = true;

				//}
			}
		});
	}

	@Override
	public void draw(Batch batch, float alpha) {
		texture.draw(batch);
	}
	
	public void loadNews(){
		File dir = new File("assets/news");
		File[] files = dir.listFiles();
		for(File f:files){
			if(f.getName().contains(".txt")){
				System.out.println(f.getName());
				news.add(new News(f.getName().replace(".txt", "")));
			}
		}
	}
	
	public static ArrayList<News> getNews(){
		return news;
	}
	
	public static void newMem(Mem m){
		System.out.println("New M.E.M. #" + m.getId());
		if(current == -1){
			current = m.getId();
		}
		uncompleted_memes.add(m);
	}
	
	public Mem find(int id){
		for(Mem m:uncompleted_memes){
			if(m.getId() == id){
				return m;
			}
		}
		for(Mem m: completed_memes){
			if(m.getId() == id){
				return m;
			}
		}
		return null;
	}
	
	public void success(){
		for(Mem m:uncompleted_memes){
			if(m.getId() == current){
				
					m.addProgress();
					if(m.progress>=100.0){
						current = -1;
						completed_memes.add(m);
						uncompleted_memes.remove(m);
						// DOTO ALERT
					}
				
			}
		}
	}

	public static class CompScreen extends Actor {

		static ArrayList<Actor> btns;
		Sprite texture;

		public CompScreen() {
			texture = new Sprite(new Texture(
					Gdx.files.internal("assets/screen01.png")));
			texture.setBounds(0, 0, MemDev.WIDTH, MemDev.HEIGHT);

		

			setTouchable(Touchable.disabled);
		}


		float actorX = 0, actorY = 0;

		@Override
		public void draw(Batch batch, float alpha) {
			texture.draw(batch);
			for(Actor b:btns){
				b.draw(batch, 1);
			}
		}
		

		public static void clearScreen() {
			int i = MemDev.background_stage.getActors().size;
			for (int j = 3; j < i; j++) {
				MemDev.background_stage.getActors().removeIndex(3);
			}
		}
		
		public static void changeScreen(Screen scr){
			screen = scr;
			btns = scr.getButtons();
			for(Actor b:btns){
				MemDev.background_stage.addActor(b);
			}
		}
		

	}
}
