package com.mygdx.memdev.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.memdev.Screens.Room;
import com.mygdx.memdev.Screens.Screen;

public class Staff {
	

	public static Staff instance = null;

	public static Staff getInst() {
		if (instance == null)
			instance = new Staff();
		return instance;
	}

	static TextButtonStyle textButtonStyle;
	public static BitmapFont font;
	public static Skin skin = new Skin(
			Gdx.files.internal("assets/data/uiskin.json"));

	static BufferedReader br = null;
	static FileReader fr = null;

	private Staff() {

		font = new BitmapFont();
		font.setColor(Color.NAVY);

		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
				new Texture("assets/btn_01.png")));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(
				new Texture("assets/btn_02.png")));
		
		
	}

	public static TextButton createBTN(String title, int x, int y) {
		TextButton btn = new TextButton(title, textButtonStyle);
		btn.setPosition(x, y);
		btn.setTouchable(Touchable.enabled);
		btn.setBounds(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());

		return btn;
	}

	public static boolean check(float x, float y,float width, float height) {
		if (x>0 && y >0 && x <= width && y <= height) {
			return true;
		}
		return false;
	}

	public static void openFile(File file) {
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);

	}

	public static void closeFile() {
		if (br != null)
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (fr != null)
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static String nextString(){
		String toSend = null;
		if(br!=null){
			try {
				toSend = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return toSend;
	}
}
