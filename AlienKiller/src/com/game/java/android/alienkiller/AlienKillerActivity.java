package com.game.java.android.alienkiller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlienKillerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button buttonPlay = (Button) findViewById(R.id.button1);
        buttonPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openPlayGameActivity();
			}
		});
        
        Button buttonConf = (Button) findViewById(R.id.button2);
        buttonConf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openPreferencesActivity();
				
			}
		});
        
        Button buttonAbout = (Button) findViewById(R.id.button3);
        buttonAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openAboutActivity();
				
			}
		});
        
        Button buttonFinish = (Button) findViewById(R.id.button4);
        buttonFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
        

        
    }
    
    private void openPlayGameActivity(){
    	Intent i = new Intent(this, GameActivity.class);
    	startActivity(i);
    }
    
    /**
     * Open the activity About
     */
    private void openAboutActivity(){
    	Intent i = new Intent(this, AboutActivity.class);
    	startActivity(i);
    }
    
    private void openPreferencesActivity() {
    	Intent i = new Intent(this, PreferencesActivity.class);
    	startActivity(i);
		
	}
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.menu, menu);
		return true;
    	
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case R.id.config:
			openPreferencesActivity();
			break;

		default:
			break;
		}
		
		return true;
	}
    
    
}