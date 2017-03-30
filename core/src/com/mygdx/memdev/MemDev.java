package com.mygdx.memdev;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.memdev.Screens.Room;
import com.mygdx.memdev.Screens.Screen;
import com.mygdx.memdev.Screens.Shop;
import com.mygdx.memdev.objects.Background;
import com.mygdx.memdev.tools.Staff;

public class MemDev extends ApplicationAdapter {

	public static int GAME_SPEED = 1;
	public static int current_actor = -1;
	public static boolean bg_active = false;
	SpriteBatch batch;
	Sprite fon;

	private TextureAtlas textureAtlas;
	private Sprite sprite2;
	private int currentFrame = 1;
	private String currentAtlasKey = new String("0001");

	static Stage stage;
	public static Stage background_stage;
	public static int WIDTH;
	public static int HEIGHT;
	public static float scale;
	Hero hero;
	
	public static int current_screen;
	public static ArrayList<Screen> screens;

	@Override
	public void create() {
		Staff.getInst();
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		scale = (float)WIDTH/1920;
		stage = new Stage();
		background_stage = new Stage();
		Background bg = new Background();
		background_stage.addActor(bg);
		background_stage.addActor(bg.exit);

		Gdx.input.setInputProcessor(stage);
		//ADD CLOCK

		

		batch = new SpriteBatch();
		fon = new Sprite(new Texture("assets/fon.png"));
		fon.setBounds(0, 0, WIDTH, HEIGHT);
		System.out.println(scale);
		
		
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheet.atlas"));
		AtlasRegion region = textureAtlas.findRegion("0001");
		sprite2 = new Sprite(region);
		sprite2.setPosition(500, 500);
		sprite2.scale(1.5f);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				currentFrame++;
				if (currentFrame > 20)
					currentFrame = 1;
				currentAtlasKey = String.format("%04d", currentFrame);
				sprite2.setRegion(textureAtlas.findRegion(currentAtlasKey));
			}
		}, 0, 1 / 10.0f);

	

		hero = new Hero();
		
		screens = new ArrayList<Screen>();
		screens.add(new Room());
		screens.add(new Shop());
		current_screen = 0;
		for(Actor a:screens.get(current_screen).getButtons()){
			stage.addActor(a);
		}
		
	}

	@Override
	public void render() {

		update_info();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		
		if(current_screen == 0){
			fon.draw(batch);
			drawClock();
		}
		
		for(Actor b:screens.get(current_screen).getButtons()){
			//b.draw(batch, 1);
		}
		
		batch.end();


		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());
		
		
	
		if (bg_active) {
			Gdx.input.setInputProcessor(background_stage);
			background_stage.act(Gdx.graphics.getDeltaTime());
			background_stage.draw();
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		background_stage.dispose();
	}

	private void update_info() {
		

		ms += 1 * MemDev.GAME_SPEED;
		if (ms >= 60) {
			secs++;
			ms = 0;
			if (secs >= 60) {
				mins++;
				secs = 0;
				if (mins == 60) {
					hours++;
					mins = 0;
					if (hours == 24) {
						hours = 0;
					}
				}
			}
		}
	}

	private void drawClock() {
		Sprite sprite;
		Pixmap pixmap = new Pixmap(WIDTH / 20, HEIGHT / 22,
				Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fill();
		pixmap.setColor(Color.RED);
		pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());

		Texture texture = new Texture(pixmap);

		sprite = new Sprite(texture);
		sprite.setPosition(WIDTH / 2, HEIGHT / 2+200);

		sprite.draw(batch);
		String z1 = "", z2 = "", z3 = "";

		if (hours < 10)
			z1 = "0";
		if (mins < 10)
			z2 = "0";
		if (secs < 10)
			z3 = "0";
		Staff.font.setColor(Color.GREEN);
		Staff.font.draw(batch, z1 + hours + ":" + z2 + mins + ":" + z3 + secs,
				sprite.getX() + sprite.getWidth() / 10,
				sprite.getY() + sprite.getHeight() / 2+8);

	}
	
	public static void changeScreen(int s){
		stage.getActors().clear();
		current_screen = s;
		stage = new Stage();
		for(Actor a:screens.get(current_screen).getButtons()){
			stage.addActor(a);
		}
	}

	// FOR GLOBAL CLOCK
	private int ms = 0;
	private int secs = 0;
	private int mins = 0;
	private int hours = 0;
}
