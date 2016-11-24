package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Grass extends Scrollable
{
	public Rectangle boundingRect;
	
	public Grass(float x, float y, int width, int height, float scrollSpeed)
	{
		super(x, y, width, height, scrollSpeed);
		
		boundingRect = new Rectangle();
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		boundingRect.set(position.x, position.y, width, height);
	}
	
	public boolean collides(Bird bird)
	{
		if(position.y < bird.getY() + bird.getHeight())
			return (Intersector.overlaps(bird.getBoundingCircle(), boundingRect));
		return false;
	}
	
	public void onRestart(float x, float xVel)
	{
		reset(x);
		velocity.x = xVel;
	}
}
