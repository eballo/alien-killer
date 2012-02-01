/**
 * 
 */
package com.game.java.android.alienkiller;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.game.java.android.alienkiller.objects.Enemy;
import com.game.java.android.alienkiller.objects.GameObjects;
import com.game.java.android.alienkiller.objects.Monster;
import com.game.java.android.alienkiller.objects.Player;

/**
 * 
 * The GameView class has 3 main functions: 
 * 
 *  
 *  
 * @author nekun
 *
 */
public class GameView extends View {
	
	//GameObjects Vector
	private Vector<GameObjects> gameObjectsVector;
	private int numEnemyes = 5;
	private int numFragments = 3;
	
	//player
	private GameObjects player;
	private int turnPlayer;
	private float accelerationPlayer;
	
	private static final int CONSTANT_TURN_PLAYER = 5;
	private static final float CONSTANT_ACCELERATION_PLAYER = 0.5f;
	
	private long lastProcess = 0;
	private static int TIME_TO_PROCESS = 50;
	private ThreadGame thread;
	
	private float mX=0, mY=0;
	private boolean fire = false;

	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		GameManager gameManager = new GameManager();
		Drawable drawablePlayer, drawableEnemy, drawableWepon;
		
		drawableEnemy = gameManager.getEnemy(context);
		drawablePlayer = gameManager.getPlayer(this.getContext());
		
		gameObjectsVector = new Vector<GameObjects>();
		
		for (int i = 0; i < numEnemyes; i++) {
			
			GameObjects enemyObjects = new Monster(this, drawableEnemy);
			enemyObjects.setIncX(Math.random()*4-2);
			enemyObjects.setIncY(Math.random()*4-2);
			enemyObjects.setAngle((int) (Math.random()*360));
			enemyObjects.setRotation((int)(Math.random()*8-4));
			gameObjectsVector.add(enemyObjects);
			
		}
		
		player = new Player(this, drawablePlayer);
		
		gameObjectsVector.add(player);
		
		GameManager gm = new GameManager();
		
		System.out.println(gm.getTypeGraphycs(context));
		
		thread = new ThreadGame();
		thread.start();
		
	}
	
	protected void refreshFisic(){
		long now = System.currentTimeMillis();
		
		if(lastProcess + this.TIME_TO_PROCESS > now ){
			return;
		}
		
		double retard = (now - lastProcess) / TIME_TO_PROCESS;
		
		player.setAngle((int) (player.getAngle()+turnPlayer*retard));
		
		double pIncX = player.getIncX() + accelerationPlayer * Math.cos(Math.toRadians(player.getAngle())*retard);
		double pIncY = player.getIncY() + accelerationPlayer * Math.sin(Math.toRadians(player.getAngle())*retard);
		
		if(GameObjects.distanceE(0, 0, pIncX, pIncY)<= GameObjects.getMaxVelocity()){
			player.setIncX(pIncX);
			player.setIncY(pIncY);
		}
		player.incrementPos();
		
		for(int i=0; i< gameObjectsVector.size(); i++){
			GameObjects enemy = (GameObjects) gameObjectsVector.get(i);
			enemy.setIncX((double)Math.random()*4-2);
			enemy.setIncY((double)Math.random()*4-2);
			enemy.setAngle((int) (Math.random()*360));
			enemy.setRotation((int)(Math.random()*8-4));
			enemy.incrementPos();
		}
		lastProcess = now;

	}
	
	public boolean onTouchEvent (MotionEvent event){
		super.onTouchEvent(event);
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			fire = true;
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = Math.abs(x - mX);
			float dy = Math.abs(y - mY);
			if(dy<6 && dx>6){
				turnPlayer = Math.round((x - mX)/2);
				fire = false;
			}else if(dx<6 && dy>6){
				accelerationPlayer = Math.round((mY - y) /25);
				fire = false;
			}
			break;
		case MotionEvent.ACTION_UP:
			turnPlayer = 0;
			accelerationPlayer = 0;
			if (fire){
			}
			break;
		}
		mX = x; 
		mY = y;
		return true;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		for (GameObjects gameObjects: gameObjectsVector) {
			gameObjects.setPosX(Math.random()*(w - gameObjects.getWidth()));
			gameObjects.setPosY(Math.random()*(h - gameObjects.getHeight()));
			
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for(GameObjects gameObjects: gameObjectsVector){
			gameObjects.paintGameObject(canvas);
		}
	}
	
	class ThreadGame extends Thread {

		@Override
		public void run() {
			while(true){
				refreshFisic();
			}
		}
		
	}
	
}

