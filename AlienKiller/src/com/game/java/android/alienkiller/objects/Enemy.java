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
public class Enemy extends GameObjects {

	/**
	 * @param view
	 * @param drawable
	 */
	public Enemy(View view, Drawable drawable) {
		super(view, drawable);
	}
	
	public void setRandomVelocity(){
		this.setvX(Math.random()*40 -20);
		this.setvY(Math.random()*40 -20);
	}

}
