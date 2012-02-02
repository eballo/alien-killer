/**
 * 
 */
package com.game.java.android.alienkiller;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author nekun
 *
 */
public class GameActivity extends Activity {
	private GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		gameView = (GameView)findViewById(R.id.GameView);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	protected void onPause(){
		super.onPause();
		gameView.setPause(true);
	}
	
	protected void onResume(){
		super.onResume();
		gameView.setPause(false);
	}

}