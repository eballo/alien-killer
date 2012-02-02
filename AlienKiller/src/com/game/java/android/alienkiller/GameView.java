/**
 * 
 */
package com.game.java.android.alienkiller;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.game.java.android.alienkiller.objects.Enemy;
import com.game.java.android.alienkiller.objects.GameObjects;
import com.game.java.android.alienkiller.objects.Monster;
import com.game.java.android.alienkiller.objects.Player;

/**
 * 
 * The GameView class that initialize the view of the game
 * 
 * Create Players and Enemies and begin the logic of the game
 *  
 *  
 * @author nekun
 *
 */
public class GameView extends View {
	
	private boolean pause = false;
	private boolean running = true;
	
	private GameManager gameManager = new GameManager();
	
	/**
	 * GameObjects Vector (Player, Enemies, Wepons)
	 */
	private Vector<GameObjects> gameObjectsVector;
	private int numEnemies = 5;
	
	/**
	 * Player
	 */
	private Player player;
	
	
	/**
	 *  THREAD 
	 */
	// Thread is the responsable of the game process
	private ThreadGame thread = new ThreadGame();
	// Period of time of every process (ms)
	private long lastProcess = 0;
	private static int TIME_TO_PROCESS = 100;
	

	/**
	 * GameView
	 * 
	 * @param context
	 * @param attrs
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		Drawable drawablePlayer, drawableEnemy; //, drawableWepon;
		
		drawableEnemy = gameManager.getEnemy(context);
		drawablePlayer = gameManager.getPlayer(this.getContext());
		
		//Initialize the vector of GameObjects ( Players, Enemys, Wepons )
		gameObjectsVector = new Vector<GameObjects>();
		
		//Create the enemies
		for (int i = 0; i < numEnemies; i++) {
			
			GameObjects enemyObjects = new Monster(this, drawableEnemy);
			gameObjectsVector.add(enemyObjects);
		}
		
		//Create the player
		player = new Player(this, drawablePlayer);
		gameObjectsVector.add(player);
		
	}
	
	protected synchronized void updateFisic(){
		long now = System.currentTimeMillis();
		
		if(lastProcess + GameView.TIME_TO_PROCESS > now ){
			return;
		}
		
//		double retard = (now - lastProcess) / TIME_TO_PROCESS;
//		
//		double pIncX = player.getvX() + accelerationPlayer * Math.cos(Math.toRadians(player.getAngle())*retard);
//		double pIncY = player.getvY() + accelerationPlayer * Math.sin(Math.toRadians(player.getAngle())*retard);
//		
//		if(GameObjects.distanceE(0, 0, pIncX, pIncY)<= GameObjects.getMaxVelocity()){
//			player.setvX(pIncX);
//			player.setvY(pIncY);
//		}
//		player.incrementPosition();
		
		for(GameObjects enemy : gameObjectsVector){
			if(enemy instanceof Enemy){
				((Enemy) enemy).setRandomVelocity();
				((Monster) enemy).incrementPosition();
			}
		}
		
		lastProcess = now;

	}

	/* (non-Javadoc)
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 * width : real with of the screen
	 * height : real heigh of the screen
	 * 
	 */
	@Override
	protected void onSizeChanged(int width, int height, int old_width, int old_height) {
		super.onSizeChanged(width, height, old_width, old_height);
		
		Log.d("DimensionScreen", "width:"+width);
		Log.d("DimensionScreen", "height:"+height);
		
		player.setInitialize(width,height);
		
		//Initialize enemy Objects
		//Check distance between player and enemy. 
		//we don't want to begin the game and die
		for (GameObjects enemy: gameObjectsVector) {
			if( enemy instanceof Enemy){
				do{
					Log.d("GameView", "is a Instance of Enemy");
					//set a random position to the enemies
					enemy.setX(Math.random()*(width - enemy.getWidth()));
					enemy.setY(Math.random()*(height - enemy.getHeight()));
				} while (enemy.distance(player) < (width + height) / 5);
			}
		}
		
		thread.start();
	}

	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected synchronized void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		
		player.paintGameObject(canvas);
		
		for(GameObjects enemy: gameObjectsVector){
			if(enemy instanceof Enemy){
				enemy.paintGameObject(canvas);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	public boolean onTouchEvent (MotionEvent event){
		super.onTouchEvent(event);
		//float x = event.getX();
		//float y = event.getY();
		
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			//TODO action_down
			break;
		case MotionEvent.ACTION_MOVE:
			//TODO action_move
			break;
		case MotionEvent.ACTION_UP:
			//TODO action_up
			break;
		}
		return true;
	}
	
	/**
	 * 
	 * Therad Class for the ThreadGame
	 * 
	 * Will refresh the fisics of the game every time.
	 * 
	 * @author nekun
	 *
	 */
	class ThreadGame extends Thread {

		@Override
		public void run() {
			while(running){
				updateFisic();
				while(pause){
					try{
						wait();
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
	}
	
	public void setPause(boolean pause){
		this.pause = pause;
	}
	
	public void onResume(boolean pause){
		this.pause= pause;
	}
	
}