/**
 * 
 */
package com.game.java.android.alienkiller.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Game Objects
 * 
 * @author nekun
 *
 */
public class GameObjects {
	
	public static final int MAX_VELOCITY = 20;
	
	protected Drawable drawable;
	protected View view;
	
	protected double x, y;
	protected double vX, vY;
	protected int angle, rotation;
	protected int width, height;
	protected int radioColision;
	
	public GameObjects(View view, Drawable drawable) {
		this.drawable = drawable;
		this.view = view;
		width = drawable.getIntrinsicWidth();
		height = drawable.getIntrinsicHeight();
		radioColision = ( height + width ) / 4;
	}
	
	/**
	 * Paint the info in the screen, is useful for debug.
	 * 
	 * @param canvas
	 */
	public void paintPosition(Canvas canvas){
		
		//Definimos el pincel de dibujo
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawText("x:"+x, (int)(x+width+10), (int)(y+height), p);
		canvas.drawText("y:"+y, (int)(x+width+10), (int)(y+height+10), p);
		
		canvas.drawText("vX:"+vX,(int)(x+width+10), (int)(y+height+20), p);
		canvas.drawText("vY:"+vY,(int)(x+width+10), (int)(y+height+30), p);
		
		//canvas.drawText("View x:"+view.getWidth(), view.getWidth()-100, view.getHeight()-30, p);
		//canvas.drawText("View y:"+view.getHeight(), view.getWidth()-100, view.getHeight()-20, p);
		//canvas.drawText("width x:"+width, view.getWidth()-100, view.getHeight()-50, p);
		//canvas.drawText("height y:"+height, view.getWidth()-100, view.getHeight()-40, p);
		
		
	}
	
	public void paintGameObject (Canvas canvas){

		canvas.save();
		//canvas.rotate((float) angle, (float) x, (float) y);
		
		drawable.setBounds((int)x, (int) y, (int) x + width, (int) y+ height);
		drawable.draw(canvas);
		
		//paintPosition(canvas);
		canvas.restore();
		
		int rInval = (int) distanceE(0,0, width,height)/2+MAX_VELOCITY;
		view.invalidate((int)(x-rInval), (int)(y-rInval),(int)(x+rInval),(int)(y+rInval));
		
	}
	
	public double distance (GameObjects gameObjects){
		return distanceE(x, y, gameObjects.x, gameObjects.y);
	}
	
	public boolean checkColision (GameObjects gameObjects){
		boolean valueToReturn= (distance(gameObjects) < radioColision+gameObjects.radioColision);
		Log.d("GameObject","CheckColision: "+valueToReturn);
		return valueToReturn;
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

	public double getX() {
		return x;
	}

	public void setX(double x) {
		Log.d("GameObjects", "X:"+x);
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		Log.d("GameObjects", "Y:"+y);
		this.y = y;
	}

	public double getvX() {
		return vX;
	}

	public void setvX(double vX) {
		this.vX = vX;
	}

	public double getvY() {
		return vY;
	}

	public void setvY(double vY) {
		this.vY = vY;
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