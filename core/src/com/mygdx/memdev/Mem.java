package com.mygdx.memdev;

import java.util.ArrayList;

public class Mem {

	private static int freeId = 1;
	public float progress;
	private float difficulty;
	
	private int id;
	
	private String title;
	
	private int news_id;
	
	
	private int likes;
	private ArrayList<String> comments;
	
	public Mem(String title){
		id = freeId;
		freeId++;
		progress = 0;
		this.title = title;
	}
	
	public int getId(){
		return id;
	}
	
	public void addProgress(){
		
	}
	
	
}
