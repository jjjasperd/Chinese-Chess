package com.example.hw5_2013;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class ChessView extends View {
	List<Chess>redChess=null;
	List<Chess>blackChess=null;
	ChessGroup red=null;
	ChessGroup black=null;
	ChosenFlag chosenFlag=null;
	Context context;
	private boolean RedPlay=true;
	private boolean gameContinue=false;
	private boolean newG=false;//For new game
	private boolean flag=false;//for runnable thread
	Canvas canvas=null;
	boolean chessChosen=false;
	boolean GameOver=false;
	Paint paint=null;
	float boardHeight=0;
	float boardWidth=0;
	float WinHeight=0;
	float WinWidth=0;
	private ChessBoard cb=null;
	Display display=null;
	
	public ChessView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint=new Paint();
		this.context=context;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);		
		display = wm.getDefaultDisplay();
		flag=true;
		cb=new ChessBoard(9,10);	
		new Thread(ChessViewRunnable).start();
		 
	}
	
	
	
	public ChessView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub 
		paint=new Paint();
		this.context=context;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);		
		display = wm.getDefaultDisplay();
		//WinHeight=display.getHeight();
		//WinWidth=display.getWidth();
		flag=true;
		cb=new ChessBoard(9,10);
		new Thread(ChessViewRunnable).start();
	 
	}
	
	public void newGame(){
		if(red!=null){
			red.getChessGroup().removeAll(redChess);
		}
		if(black!=null){
			black.getChessGroup().removeAll(blackChess);
		}
		iniChess();
		chosenFlag=new ChosenFlag();
	}
	
	
	public void setNewGame(){
		newG=true;
	}
	
	public void drawChessBoard(Canvas canvas){
		Bitmap bm=BitmapFactory.decodeResource(getResources(),R.drawable.main);
		Matrix matrix=new Matrix();
		matrix.postScale(0.62f, 0.7f);
		canvas.setMatrix(matrix);
		
		canvas.drawBitmap(bm,15,220, null);
		boardHeight=bm.getHeight();
		boardWidth=bm.getWidth();
		
		Log.i("Screen:", "boardHeight="+boardHeight/11+" boardWidth"+boardWidth/10);
		 
	}
	
	public void stopThread(){
		flag=false;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);	
		drawChessBoard(canvas);
		
		if(newG){//For new game
			newGame();
			Log.i("ChessMove","newgame");
			newG=false;
		}
		
		if(gameContinue&&!GameOver){
			drawChess(canvas);
		}
		if(chessChosen){
			paint.setStyle(Style.STROKE);		 
			paint.setColor(Color.DKGRAY);
			paint.setStrokeWidth(5);
			canvas.drawRect(chosenFlag.getPosX()+10, chosenFlag.getPosY()+5, chosenFlag.getPosX()+75, chosenFlag.getPosY()+75, paint);
		}
		
	}
	
	 
	
	
	public void drawChess(Canvas canvas){
		Bitmap bm=null;
		int pic;
		Matrix matrix=new Matrix();
		matrix.postScale(0.85f, 0.85f);
		canvas.setMatrix(matrix);
		if(!redChess.isEmpty()){
			for(Chess chess:redChess){
				if(chess.getAlive()){
					pic=getResources().getIdentifier(chess.getName(), "drawable",context.getPackageName());
					bm=BitmapFactory.decodeResource(getResources(), pic);
					canvas.drawBitmap(bm, chess.getPositionX(), chess.getPositionY(), null);
				}
			}
		}
		if(!blackChess.isEmpty()){
			for(Chess chess:blackChess){
				if(chess.getAlive()){
					pic=getResources().getIdentifier(chess.getName(), "drawable",context.getPackageName());
					bm=BitmapFactory.decodeResource(getResources(), pic);
					canvas.drawBitmap(bm, chess.getPositionX(), chess.getPositionY(), null);
				}
			}
		}
	}
	
	
	public void iniChess(){
		red=new ChessGroup("red");
		redChess=red.getChessGroup();
		black=new ChessGroup("black");
		blackChess=black.getChessGroup();	
		RedPlay=true;
		cb=new ChessBoard(9,10);
		cb.SetPositionTaken(red);
		cb.SetPositionTaken(black);
		GameOver=false;
		gameContinue=true;
	}

	
	
	
	
	
	
	
	public boolean findChosen(ChessGroup cg,float x,float y){
		 for(Chess c:cg.getChessGroup()){
			if(c.getAlive()){
			//black.findChosen("pao");
			//Log.i("chess x", c.getName()+" x="+c.getPositionX()+"chess y="+c.getPositionY());
				if(Math.abs(c.getPositionX()*18/20-x)<30&&Math.abs(c.getPositionY()*18/20-y)<30){//the canvas set matrix (0.85f,0.85f)				
					cg.disableChosen();
					c.setChosen(true);
					//Toast.makeText(getContext(), c.getRank()+"is chosen", Toast.LENGTH_SHORT).show();
					chosenFlag.setX(c.getX());
					chosenFlag.setY(c.getY());
					chessChosen=true;	
					//Toast.makeText(getContext(), "touch x="+x+"y="+y,Toast.LENGTH_SHORT).show();
					//Toast.makeText(context,"X"+c.getPositionX()+" Y="+c.getPositionY(),Toast.LENGTH_SHORT).show();
					return true;
				}
			}
		}
		return false;
	}
	
	
	public int[] convertIndex(float mx, float my){
		int[] back= new int[2];
		double start_x=19.0;
		double start_y=200.0;
		double uni_x=63.5*18/20;
		double uni_y=71.6*17/20;
		double index_x=-1;
		double index_y=-1;
		if(mx>start_x&&my>start_y){
			index_x=(mx-start_x)/uni_x;
			index_y=(my-start_y)/uni_y;
			if(index_x>9||index_y>10){
				back[0]=-1;
				back[1]=-1;
			}
			else{
				back[0]=(int)index_x;
				back[1]=(int)index_y;
			}
		//	Toast.makeText(getContext(), "index_x="+(int)index_x+"index_y"+(int)index_y, Toast.LENGTH_SHORT).show();
		}	
		return back;
	}
	
	
	public void ChessMove(float x,float y){
		int [] tmp=new int[2];
		tmp=convertIndex(x,y);
		if(tmp[0]!=-1&&tmp[1]!=-1){
			if(RedPlay){
				red.CalIndex();
				if(red.getIndex()!=-1){
					Chess c = redChess.get(red.getIndex());
					int old_x=c.getX();
					int old_y=c.getY();
					ChessRule.AllRules(cb, c,tmp[0],tmp[1]);
					red.disableChosen();
					if(old_x!=c.getX()||old_y!=c.getY()){
						RedPlay=false;
					}
					chosenFlag.setX(-10);
					chosenFlag.setY(-10);	
				}
			}
			else{
				black.CalIndex();				
				if(black.getIndex()!=-1){
					Chess c=blackChess.get(black.getIndex());
					int old_x=c.getX();
					int old_y=c.getY();
					ChessRule.AllRules(cb, c,tmp[0],tmp[1]);
					black.disableChosen();
					if(old_x!=c.getX()||old_y!=c.getY()){
						RedPlay=true;
					}				
					chosenFlag.setX(-10);
					chosenFlag.setY(-10);
				}
			}
		}
	}
	
	
	
	public void ChessDestory(ChessGroup cg1,ChessGroup cg2){
			cg1.CalIndex();
			cg2.CalIndex();
			if(cg1.getNumTaken()>1){
				cg1.disableChosen();
			}
			if(cg2.getNumTaken()>1){
				cg2.disableChosen();
			}
			if(cg1.getNumTaken()==1&&cg2.getNumTaken()==1){
				Chess c1=cg1.getChessGroup().get(cg1.getIndex());
				Chess c2=cg2.getChessGroup().get(cg2.getIndex());
				int x=c2.getX();
				int y=c2.getY();
				int old_x=c1.getX();
				int old_y=c1.getY();
				//Toast.makeText(getContext(), "c1.getRank "+c1.getRank(),Toast.LENGTH_SHORT).show();
				if(c1.getRank().equals("pao")){
					ChessRule.cannonRule(cb, c1, c2);
				}
				else{
					//Toast.makeText(getContext(), "c1.getRank "+c1.getRank(),Toast.LENGTH_SHORT).show();
					ChessRule.AllRules(cb, c1,x,y);
				}
				 
				if(old_x!=c1.getX()||old_y!=c1.getY()){
					if(RedPlay)
						RedPlay=false;
					else
						RedPlay=true;
					//Toast.makeText(getContext(), "c2="+c2.getRank()+" is dead",Toast.LENGTH_SHORT).show();
					c2.dead();
				}
			}
			cg1.disableChosen();
			cg2.disableChosen();
	}
	
	
	
	public void MoveMent(float x,float y){
		if(RedPlay){
			if(!findChosen(black,x,y)){
				ChessMove(x,y);
			}
			else
				ChessDestory(red,black);
		}
		
		else{
			Log.i("ChessMove","Movement");
			 
			if(!findChosen(red,x,y)){
				
				
				//Toast.makeText(getContext(),"chess Move", Toast.LENGTH_SHORT).show();
				ChessMove(x,y);
				Log.i("ChessMove","Movement finish");
			}
			else
				ChessDestory(black,red);
		}
		
	}
		
	
	
	
	private Runnable ChessViewRunnable=new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(flag){
				try{
					postInvalidate();
					Thread.sleep(20);
				}catch(InterruptedException e){
					Log.i("update","error in update");
				}
			}
		}
		
	};
	
	
	 
	private class ChosenFlag{
		private int x=-10;
		private int y=-10;
		ChosenFlag(){
			super();
		}
		
		public void setX(int x){
			this.x=x;
		}
		public void setY(int y){
			this.y=y;
		}
		 
		
		public float getPosX(){
			return (float) (x*63.5+19.0);
		}
		
		public float getPosY(){
			return (float) ((y*71.5)+200.0);
		}
		
	}
	
	
	
	@SuppressLint("ShowToast")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(gameContinue){
			//Toast.makeText(getContext(),"RedPlay: "+RedPlay, Toast.LENGTH_SHORT).show();
			if(RedPlay){
				//Toast.makeText(getContext(),"RedPlay", Toast.LENGTH_SHORT).show();
				if(!findChosen(red,event.getRawX(),event.getRawY())){
					MoveMent(event.getRawX(),event.getRawY());
				//	Toast.makeText(getContext(), "x="+event.getRawX()+",y="+event.getRawY(),1).show();
				}	
				 
			}
			else{
				if(!findChosen(black,event.getRawX() ,event.getRawY())){
					//black.findChosen("pao");
					MoveMent(event.getRawX(),event.getRawY());
				
				//	Toast.makeText(getContext(), "x="+event.getRawX()+",y="+event.getRawY(),Toast.LENGTH_SHORT).show();
				}	 
			}
			
			
		}		
		 
		return super.onTouchEvent(event);
	}
	
	
	
	 
}
