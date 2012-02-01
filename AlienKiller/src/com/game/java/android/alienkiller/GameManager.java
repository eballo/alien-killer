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

/**
 * @author nekun
 *
 */
public class GameManager {
	
	public static int GRAPHIC_VECTORIAL = 0;
	public static int GRAPHIC_BITMAP = 1;
	
	public static String GRAPHIC_KEY = "graphics";
	
	
	/**
	 * Method that return the type of graphic set in the settings
	 * 
	 * @return can be 1 or 0
	 */
	public int getTypeGraphycs( Context context){
		Integer valueToReturn = null;
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		valueToReturn = new Integer(sp.getString(GRAPHIC_KEY, "?"));
		return valueToReturn.intValue();
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
				drawablePlayer = getPlayerVectorial();
				break;
			case 1: // BitMap
				drawablePlayer = getPlayerBitmap(context);
				break;
			default :
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
	public Drawable getEnemy(Context context){
		Drawable drawableEnemy= null;
		
		switch (getTypeGraphycs(context)){
			case 0: // Vector
				drawableEnemy = getEnemyVectorial();
				break;
			case 1: // BitMap
				drawableEnemy = getEnemyBitmap(context);
				break;
			default :
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
		dShapeDrawableEnemy.setIntrinsicWidth(10);
		dShapeDrawableEnemy.setIntrinsicHeight(10);
		return dShapeDrawableEnemy;
	}


	/**
	 * Method that returns a Vectorial Player
	 * 
	 * @return a Drawable Vectorial Player
	 */
	private Drawable getPlayerVectorial() {
		ShapeDrawable dShapeDrawablePlayer = new ShapeDrawable(new RectShape());
		dShapeDrawablePlayer.getPaint().setColor(Color.WHITE);
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
	private Drawable getPlayerBitmap(Context context){
		return context.getResources().getDrawable(R.drawable.xwing); 
	}

}