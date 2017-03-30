package com.mygdx.memdev.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.memdev.MemDev;
import com.mygdx.memdev.tools.Staff;

public class Background extends Actor{
	
	
	 Sprite sprite;
	 public TextButton exit;
	
	public Background() {
		 Pixmap pixmap;
		 Texture texture;
		
		 pixmap = new Pixmap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);  
	     pixmap.setColor(0,0,0,0.7f);
	     pixmap.fill();
	        
	     texture = new Texture(pixmap);
	        
	     pixmap.dispose();
	        
	     sprite = new Sprite(texture);
	     
	     setBounds(0,0, texture.getWidth(), texture.getHeight());
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					
					//System.out.println("BG size: "+MemDev.background_stage.getActors().size);
					return true;
				}
			});
	   
	     
	     TextButtonStyle textButtonStyle = new TextButtonStyle();
			textButtonStyle.font = Staff.font;
			textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
					new Texture("assets/off_01.png")));
			textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(
					new Texture("assets/off_02.png")));
			
	     exit = new TextButton("",textButtonStyle);
	     exit.setWidth(70);
	     exit.setHeight(70);
	     exit.setBounds(0, 0, 70, 70);
	    
	     exit.setPosition(10, 10);
	     exit.setTouchable(Touchable.enabled);
	     exit.addListener(new InputListener() {

				public boolean touchDown(InputEvent event, float x1, float y1,
						int pointer, int button) {

					return true;

				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					if (Staff.check(x, y, exit.getWidth(), exit.getHeight())) {
						MemDev.bg_active = false;
						int i = MemDev.background_stage.getActors().size;
						for(int j=2; j<i;j++){
							MemDev.background_stage.getActors().removeIndex(2);
						}
					}
				}
			});
	     
	}
	
	@Override
	public void draw(Batch batch, float alpha) {	
	     sprite.draw(batch);
	     exit.draw(batch, alpha);
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	
}
