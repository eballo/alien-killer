/**
 * 
 */
package com.game.java.android.alienkiller.objects;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @author nekun
 *
 */
public class Player extends GameObjects {
	
	/**
	 * @param view
	 * @param drawable
	 */
	public Player(View view, Drawable drawable) {
		super(view, drawable);
	}

	//Initialize the player in the middle of the screen
	public void setInitialize(int w, int h){
		setX(( w - width) / 2);
		setY(( h - height) / 2);
	}
	
	public void incrementPosition(){
		x += vX;
		y += vY;
		
		if (x < 0) {
			x = 0;
		}
		if(x > width - view.getWidth()){
			x = width - view.getWidth();
		}
		
		if (y < 0) {
			y = 0;
		}
		if (y > view.getHeight() - height) {
			y = view.getHeight() - height;
		}
		
	}
	

}