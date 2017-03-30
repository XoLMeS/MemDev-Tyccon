package com.mygdx.memdev.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bar extends Actor{

	public int x, y, width, height, value;
	public Color color = Color.GREEN;
	public Color color2 = Color.YELLOW;
	public Color color3 = Color.RED;
	private BitmapFont font;
	 
	
	String title;

	public Bar(int x, int y, int width, int height, int value, String title) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.value = value;
		this.title = title;
		font = new BitmapFont();
        font.setColor(Color.NAVY);
	}

	public void draw(SpriteBatch batch) {
		Sprite sprite;
		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GRAY);
		pixmap.fill();

		double t = ((double)width - 6) / 100 * value;
		Pixmap pixmap2 = new Pixmap((int) t, height - 4, Pixmap.Format.RGBA8888);
		
		if (value > 60) {
			pixmap2.setColor(color);
		} else if (value > 30) {
			pixmap2.setColor(color2);
		} else {
			pixmap2.setColor(color3);
		}

		pixmap2.fill();

		pixmap.drawPixmap(pixmap2, 3, 2);

		Texture texture = new Texture(pixmap);

		// It's the textures responsibility now... get rid of the pixmap
		pixmap.dispose();
		sprite = new Sprite(texture);
		sprite.setPosition(x, y);
		
		font.draw(batch, title, x-title.length()*font.getScaleX()*9, y+10);
		sprite.draw(batch);
	}
}
