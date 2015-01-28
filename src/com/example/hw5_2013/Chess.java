package com.example.hw5_2013;

import android.util.Log;

 
public class Chess{
	
	public enum Suits{Red,Black};
	private int pre_x;
	private int pre_y;
	private int x;
	private int y;
	private int index ;
	private boolean isChosen=false;
	private String name=null;
	private String Rank=null;
	private boolean isAlive=true;
	private Suits suit=null;
	public Chess(String name,int x,int y){
		super();
		this.x=x;
		this.y=y;
		this.name=name;
		switch(name.charAt(0)){
		case 'b':
			this.suit = Suits.Black;
			break;
		case 'r':
			this.suit = Suits.Red;
			break;
		}
	}
	
	public void setPre_x(int pre_x){
		this.pre_x=pre_x;
	}
	
	public void setPre_y(int pre_y){
		this.pre_y=pre_y;
	}
	
	public Suits getSuit(){
		return suit;
	}
	
	public void setRank(String Rank){
		this.Rank=Rank;
	}
	
	public String getRank(){
		return Rank;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	
	public void setAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	
	public boolean getAlive(){
		return isAlive;
	}
	
	public int getPre_x(){
		return pre_x;
	}
	
	public int getPre_y(){
		return pre_y;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public float getPositionX(){
		return (float) (x*63.5+19.0);
	}
	public float getPositionY(){
		return (float) ((y*71.5)+200.0);
	}
	public void setId(int index){
		this.index=index;
	}
	public int getId(){
		return index;
	}
	
	public void setChosen(boolean flag){
		isChosen=flag;
	}
	
	public boolean isChosen(){
		return isChosen;
	}
	
	public void dead(){
		this.setChosen(false);
		this.setAlive(false);
	}
}
