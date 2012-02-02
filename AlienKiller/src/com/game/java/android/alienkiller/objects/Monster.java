/**
 * 
 */
package com.game.java.android.alienkiller.objects;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * This is the especific class for the enemy of type Monster
 * 
 * @author nekun
 *
 */
public class Monster extends Enemy {

	/**
	 * @param view
	 * @param drawable
	 */
	public Monster(View view, Drawable drawable) {
		super(view, drawable);
		setvX(Math.random()*4-2);
		setvY(Math.random()*4-2);
		setAngle((int) (Math.random()*360));
		setRotation((int)(Math.random()*8-4));
	}
	
	public void incrementPosition(){
		x = x + vX;
		y = y + vY;
		
		Log.d("position", "x: "+x);
		Log.d("position", "y: "+y);
		
		if (x < 0) {
			x = view.getWidth();
		}
		if(x > (view.getWidth() - width)){
			x = 0;
		}
		
		if (y < 0) {
			y = view.getHeight() - height;
		}
		if (y > (view.getHeight() - height)) {
			y = 0;
		}
		
	}

}
