package com.mygdx.memdev;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends Actor{
	
	// STATS
	
	private int health,
				hunger,
				stamina,
				lvl;
				
				
	public Hero(){
		health = 100;
		hunger = 100;
		stamina = 100;
		lvl = 0;
	}
	
	
	// GET
	//
	public int getHP(){
		return health;
	}
	
	public int getLVL(){
		return lvl;
	}
	
	public int getStamina(){
		return stamina;
	}
	
	public int getHunger(){
		return hunger;
	}
	
	// SET
	//
	public void setLVL(int lvl){
		if(lvl>=0)
			this.lvl = lvl;
	}
	
	public void setHunger(int hung){
		if(hung>=0)
			this.hunger = hung;
	}
	
	public void setHP(int hp){
		if(hp>0)
			this.health = hp;
	}
	
	public void setStamina(int stamina){
		if(stamina>=0)
			this.stamina = stamina;
	}
	
	
	

}
