/**
 * 
 */
package com.game.java.android.alienkiller.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Game Objects
 * 
 * @author nekun
 *
 */
public class GameObjects {
	
	public static final int MAX_VELOCITY = 20;
	
	private Drawable drawable;
	private View view;
	
	private double posX, posY;
	private double incX, incY;
	private int angle, rotation;
	private int width, height;
	private int radioColision;
	
	
	public GameObjects(View view, Drawable drawable) {
		super();
		this.drawable = drawable;
		this.view = view;
		width = drawable.getIntrinsicWidth();
		height = drawable.getIntrinsicHeight();
		radioColision = ( height + width ) / 4;
		Log.d("GameObject", "width="+width);
		Log.d("GameObject", "height="+height);
	}
	
	public void paintGameObject (Canvas canvas){
		canvas.save();
			
		int x = (int) ( posX + width ) / 2;
		int y = (int) ( posY + height ) / 2;
		
		canvas.rotate((float) angle, (float) x, (float) y);
		
		drawable.setBounds((int)posX, (int) posY, (int) posX + width, (int) posY+ height);
		
		drawable.draw(canvas);
		
		canvas.restore();
		
		
		int rInval = (int) distanceE(0,0, width,height)/2+MAX_VELOCITY;
		
		Display display = ((WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		Log.d("GameObject", "width Display="+width);
		Log.d("GameObject", "height Display="+height);
		
		view.invalidate(0, 0, width, height);
		
	}
	
	public void incrementPos(){
		
		posX += incX;
		
		if(posX >= width){
			posX = 0;
		}
		
		posY += incY;
		
		if(posY >=height){
			posY = 0;
		}
		
		angle = 0;
		
	}
	
	public double distance (GameObjects gameObjects){
		return distanceE(posX, posY, gameObjects.posX, gameObjects.posY);
	}
	
	public boolean checkColision (GameObjects gameObjects){
		return (distance(gameObjects) < radioColision+gameObjects.radioColision);
	}
	
	public static double distanceE(double x, double y, double width, double height) {
		return Math.sqrt((x-width)*(x-width) + (y-height)*(y-height));
	}

	/**
	 * -------------------------------------------
	 * Getters & Setters
	 * -------------------------------------------
	 */

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getIncX() {
		return incX;
	}

	public void setIncX(double incX) {
		this.incX = incX;
	}

	public double getIncY() {
		return incY;
	}

	public void setIncY(double incY) {
		this.incY = incY;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRadioColision() {
		return radioColision;
	}

	public void setRadioColision(int radioColision) {
		this.radioColision = radioColision;
	}

	public static int getMaxVelocity() {
		return MAX_VELOCITY;
	}	

}