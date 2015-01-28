package com.example.hw5_2013;

import java.util.ArrayList;
import android.util.Log;

public class ChessGroup {
	private ArrayList<Chess> chesses=null;
	private String color=null;
	private int index=-1;
	private int numTaken=0;
	
	public ChessGroup(){
		super();
		this.index = -1;
		this.chesses = new ArrayList<Chess>();
		this.color = new String();
		this.numTaken = 0;
	}
	
	public ChessGroup(String color){
		this.numTaken = 0;
		this.index = -1;
		this.color = color;
		this.chesses = new ArrayList<Chess>();
		iniChessGroup();
	}
	
	public void iniChessGroup(){
		if(color.equals("black")){
			
			
			for(int i=0;i<2;i++){
				Chess c=new Chess("black_pao",6*i+1,2);
				c.setId(index);
				c.setRank("pao");
				index++;
				chesses.add(c);
			}
			for(int j = 0; j< 2; j++){
				// their position are: (1,0) and (7,0)
				Chess c = new Chess("black_ma",6*j+1,0);
				c.setId(index);
				c.setRank("ma");
				chesses.add(c);
				index++;
			}
			
			
			
			for(int j = 0; j< 2; j++){
				// their position are: (3,0) and (5,0)
				Chess c = new Chess("black_shi", j*2+3, 0);
				c.setId(index);
				c.setRank("shi");
				chesses.add(c);
				index++;
			}
			
			for(int j = 0; j< 2; j++){
				// their position are: (0,0) and (8,0)
				Chess c = new Chess("black_ju", 8*j, 0);
				c.setId(index);
				c.setRank("ju");
				chesses.add(c);
				index++;
			}
			
			for(int j = 0; j< 2; j++){
				// their position are: (2,0) and (6,0)
				Chess c = new Chess("black_xiang", j*4+2, 0);
				c.setId(index);
				c.setRank("xiang");
				chesses.add(c);
				index++;
			}
			
			for(int i=0;i<5;i++){
				Chess c=new Chess("black_bing",i*2,3);
				c.setId(index);
				c.setRank("bing");
				index++;
				chesses.add(c);
			}
			/*
			 * For black jiang
			 */
			Chess c = new Chess("black_jiang",4,0);
			c.setId(index);
			c.setRank("jiang");
			chesses.add(c);
			index ++;			
		}
		if(color.equals("red")){
			
			for(int j = 0; j< 2; j++){
				// their position are: (0,9) and (8,9)
				Chess c = new Chess("red_ju", 8*j, 9);
				c.setId(index);
				c.setRank("ju");
				chesses.add(c);
				index ++;
			}
			for(int j = 0; j< 2; j++){
				// their position are: (1,9) and (7,9)
				Chess c = new Chess("red_ma", 6*j+1, 9);
				c.setId(index);
				c.setRank("ma");
				chesses.add(c);
				index ++;
			}
			for(int j = 0; j< 2; j++){
				// their position are: (1,7) and (7,7)
				Chess c = new Chess("red_pao", 6*j+1, 7);
				c.setId(index);
				c.setRank("pao");
				chesses.add(c);
				index ++;
			}
			for(int j = 0; j< 2; j++){
				// their position are: (3,9) and (5,9)
				Chess c = new Chess("red_shi", j*2+3, 9);
				c.setId(index);
				c.setRank("shi");
				chesses.add(c);
				index ++;
			}
			for(int j = 0; j< 2; j++){
				// their position are: (2,9) and (6,9)
				Chess c = new Chess("red_xiang", j*4+2, 9);
				c.setId(index);
				c.setRank("xiang");
				chesses.add(c);
				index ++;
			}
			for(int j = 0; j< 5; j++){
				// their position are: (0,6),(2,6),(4,6),(6,6),(8,6)
				Chess c = new Chess("red_bing", j*2 , 6);
				c.setId(index);
				c.setRank("bing");
				chesses.add(c);
				index++;
			}
			
			Chess c = new Chess("red_jiang",4,9);
			c.setId(index);
			c.setRank("jiang");
			chesses.add(c);
			index ++;
		}
		
		
	}
	
	public ArrayList<Chess> getChessGroup(){
		return chesses;
	}
	
	public void setIndex(int index){
		this.index=index;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getNumTaken(){
		return numTaken;
	}
	public void CalIndex(){
		this.index=-1;
		this.numTaken=0;
		for(Chess c:this.chesses){
			if(c.isChosen()){
				this.index=this.chesses.indexOf(c);
				this.numTaken++;
			}
		}
	}
	
	public boolean isAlive(){
		boolean flag=true;
		for(Chess c:this.chesses){
			if(c.getRank().equals("jiang")){
				if(!c.getAlive()){
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean findChosen(String rank){
		for(Chess c:this.chesses){
			if(c.getRank().equals(rank))
				Log.i("ChessMove", rank+": "+c.isChosen());
		}
		return false;
	}
	
	public void disableChosen(){

		for(Chess c:this.chesses){
			if(c.isChosen()){
				c.setChosen(false);
			}
		}
		this.setIndex(-1);
	}
	
}
