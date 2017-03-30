package com.mygdx.memdev.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.memdev.MemDev;

public class Clock extends Actor {

	Texture texture = new Texture(Gdx.files.internal("assets/clock.png"));
	float actorX = 600, actorY = 300;

	// current time, changes every frame*speed
	private int ms = 0;
	private int secs = 0;
	private int mins = 0;
	private int hours = 0;

	private boolean active = false; // active means the clock is ringing
	private boolean zoom = false; // if clock is not active, player zooms clock
									// and in zoom mode he can set time or
									// activate or deactivate it

	// if set -> clock will not ring, else it will in ring in set time
	private boolean set = false;
	private int set_mins = 0;
	private int set_hours = 0;

	private int actorId = -1; // just to know position in main array of actors

	public Clock(float x, float y) {
		actorX = x;
		actorY = y;
		setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (!active) {
					((Clock) event.getTarget()).zoom = true;
					MemDev.bg_active = true;
					MemDev.current_actor = getActorId();
					BigClock bc = new BigClock();
					bc.setTouchable(Touchable.enabled);
					MemDev.background_stage.addActor(bc);
				} else {
					active = false;
				}

				return true;
			}
		});
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, actorX, actorY);
	}

	@Override
	public void act(float delta) {
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
					if (hours == 12) {
						hours = 0;
					}
				}
			}
		}

		if (set && set_hours == hours && set_mins == mins) {
			active = true;
		}

	}

	public void setAlarmTime(int mins, int hours) {
		this.set_mins = mins;
		this.set_hours = hours;
	}

	public void setTime(int mins, int hours) {
		this.mins = mins;
		this.hours = hours;
	}

	// activates and deactivates the alarm
	public void set_button() {
		if (set)
			set = false;
		else {
			set = true;
		}
	}

	public int getActorId() {
		return actorId;
	}

	public class BigClock extends Actor {
		Texture texture = new Texture(
				Gdx.files.internal("assets/big_clock.png"));
		float actorX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2,
				actorY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2;

		public BigClock() {
			setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}
			});
		}

		@Override
		public void draw(Batch batch, float alpha) {
			batch.draw(texture, actorX, actorY);
		}
	}
}
