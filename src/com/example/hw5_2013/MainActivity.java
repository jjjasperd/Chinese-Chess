package com.example.hw5_2013;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.DialogInterface;

public class MainActivity extends Activity {
	ChessView chessView=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_main);
		chessView=(ChessView) findViewById(R.id.chessView);
		Button newGame=(Button)findViewById(R.id.newGame);
		Button playBack=(Button) findViewById(R.id.playBack);
		Button draw=(Button)findViewById(R.id.draw);
		newGame.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chessView.setNewGame();
			}
			
		});
		playBack=(Button)findViewById(R.id.playBack);
		playBack.setOnClickListener(new Button.OnClickListener(){   
            public void onClick(View v) {    
            	//ChessView.c1.getPre_x;
            	//ChessView.c1.getPre_y;
            }    
  
        });    
		/*draw = (Button)findViewById(R.id.draw);
		draw.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				final AlertDialog alertDialog = new AlertDialog.Builder(
						MainActivity.this).create();
				alertDialog.setTitle("draw...");
				alertDialog.setMessage("Do you want to draw?");
				alertDialog.setButton0("Yes", new OnClickListener(){
					public void onClick(View v){
						Toast.makeText(getApplicationContext(),
								"You clicked on Yes", Toast.LENGTH_SHORT)
								.show();
					}

				});
				((Object) alertDialog).setButton1("No", new OnClickListener(){
					public void onClick(View v){
						Toast.makeText(getApplicationContext(),
								"You clicked on No", Toast.LENGTH_SHORT)
								.show();
					}
				});
				alertDialog.show();
			}
		});*/
		
		
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onDestory(){
		chessView.stopThread();
	}

}
