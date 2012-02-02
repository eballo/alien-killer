/**
 * 
 */
package com.game.java.android.alienkiller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * This class is the class that will give a new player, and a new enemy
 * 
 *  - Have the logic to chose the graphics and the enemy level.
 * 	- Have the logic to know the type of Graphics and if the music is on.
 * 
 * @author nekun
 *
 */
public class GameManager {
	
	public static String GRAPHIC_KEY = "graphics";
	public static String MUSIC_KEY = "music";
	
	/**
	 * Method that return the type of graphic set in the settings
	 * 
	 * @return can be 1 or 0
	 */
	public int getTypeGraphycs (Context context){
		Integer valueToReturn = null;
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		valueToReturn = new Integer(sp.getString(GRAPHIC_KEY, "?"));
		return valueToReturn.intValue();
	}
	
	/**
	 * Method that return true/false if the music is on in the settings
	 * 
	 * true = music on
	 * false = music off
	 * 
	 * @return can be true/false
	 */
	public boolean isMusicOn (Context context){
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		return sp.getBoolean(MUSIC_KEY, true);
	}

	
	/**
	 * Method that returns a new player cheking the type of Graphics selected
	 * 
	 * can be a vector or bitmap player
	 * 
	 * @param context
	 * @return a drawable player
	 */
	public Drawable getPlayer(Context context){
		Drawable drawablePlayer= null;
		
		switch (getTypeGraphycs(context)){
			case 0: // Vector
				Log.d("GameManager", "Player Vectorial");
				drawablePlayer = getPlayerVectorial();
				break;
			case 1: // BitMap
				Log.d("GameManager", "Player BitMap");
				drawablePlayer = getPlayerBitmap(context);
				break;
			default :
				Log.d("GameManager", "Player Bitmap - Default");
				drawablePlayer = getPlayerBitmap(context);
				break;
		}
		
		return drawablePlayer;
		
	}
	
	/**
	 * Method that returns a new Enemy cheking the type of the Graphics selected
	 * 
	 * can be vector or bitmap enemy
	 * 
	 * @param context
	 * @return
	 */
	//TODO enemy level 
	public Drawable getEnemy(Context context){
		Drawable drawableEnemy= null;
		
		switch (getTypeGraphycs(context)){
			case 0: // Vector
				Log.d("GameManager", "Enemy Vectorial");
				drawableEnemy = getEnemyVectorial();
				break;
			case 1: // BitMap
				Log.d("GameManager", "Enemy Bitmap");
				drawableEnemy = getEnemyBitmap(context);
				break;
			default :
				Log.d("GameManager", "Enemy Bitmap - default");
				drawableEnemy = getEnemyBitmap(context);
				break;
		}
		
		return drawableEnemy;
		
	}
	
	private Drawable getEnemyBitmap(Context context) {
		return context.getResources().getDrawable(R.drawable.monster); 
	}


	/**
	 * Method that return a vectorial enemy
	 * 
	 * @return a drawable vectorial enemy
	 */
	private Drawable getEnemyVectorial() {
		ShapeDrawable dShapeDrawableEnemy = new ShapeDrawable(new OvalShape());
		dShapeDrawableEnemy.getPaint().setColor(Color.WHITE);
		dShapeDrawableEnemy.getPaint().setStyle(Style.STROKE);
		dShapeDrawableEnemy.setIntrinsicWidth(20);
		dShapeDrawableEnemy.setIntrinsicHeight(20);
		return dShapeDrawableEnemy;
	}


	/**
	 * Method that returns a Vectorial Player
	 * 
	 * @return a Drawable Vectorial Player
	 */
	private Drawable getPlayerVectorial() {
		ShapeDrawable dShapeDrawablePlayer = new ShapeDrawable(new RectShape());
		dShapeDrawablePlayer.getPaint().setColor(Color.RED);
		dShapeDrawablePlayer.getPaint().setStyle(Style.STROKE);
		dShapeDrawablePlayer.setIntrinsicHeight(20);
		dShapeDrawablePlayer.setIntrinsicWidth(30);
		return dShapeDrawablePlayer;
	}


	/**
	 * Method that returns a BitMap player
	 * 
	 * @param context The context
	 * @return return a Drawable BITMAP player
	 */
	//TODO should chose the ship
	private Drawable getPlayerBitmap(Context context){
		return context.getResources().getDrawable(R.drawable.xwing); 
	}

}