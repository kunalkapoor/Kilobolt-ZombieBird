package com.kilobolt.gameobjects;

import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbhelpers.AssetLoader;

public class ScrollHandler
{
	private Grass grass1, grass2;
	private Pipe pipe1, pipe2, pipe3;
	
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 49;
	
	public ScrollHandler(float yPos)
	{
		grass1 = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		grass2 = new Grass(grass1.getTailX(), yPos, 143, 11, SCROLL_SPEED);
		
		pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
		pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
		pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
	}
	
	public void update(float delta)
	{
		grass1.update(delta);
		grass2.update(delta);
		pipe1.update(delta);
		pipe2.update(delta);
		pipe3.update(delta);
		
		if(!grass1.isVisible) grass1.reset(grass2.getTailX());
		if(!grass2.isVisible) grass2.reset(grass1.getTailX());
		if(!pipe1.isVisible) pipe1.reset(pipe3.getTailX() + PIPE_GAP);
		if(!pipe2.isVisible) pipe2.reset(pipe1.getTailX() + PIPE_GAP);
		if(!pipe3.isVisible) pipe3.reset(pipe2.getTailX() + PIPE_GAP);
	}
	
	public void stop()
	{
		grass1.stop();
		grass2.stop();
		pipe1.stop();
		pipe2.stop();
		pipe3.stop();
	}

	public boolean collidesWithPipes(Bird bird)
	{
		if(!pipe1.isScored() && pipe1.getX() + pipe1.getWidth() / 2 < bird.getX() + bird.getWidth())
		{
			GameWorld.addScore(1);
			pipe1.setScored(true);
			AssetLoader.coin.play();
		}
		else if(!pipe2.isScored() && pipe2.getX() + pipe2.getWidth() / 2 < bird.getX() + bird.getWidth())
		{
			GameWorld.addScore(1);
			pipe2.setScored(true);
			AssetLoader.coin.play();
		}
		else if(!pipe3.isScored() && pipe3.getX() + pipe3.getWidth() / 2 < bird.getX() + bird.getWidth())
		{
			GameWorld.addScore(1);
			pipe3.setScored(true);
			AssetLoader.coin.play();
		}
		
		return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3.collides(bird));
	}
	
	public boolean collidesWithGrass(Bird bird)
	{
		return (grass1.collides(bird) || grass2.collides(bird));
	}
	
	public Grass getGrass1()
	{
		return grass1;
	}

	public Grass getGrass2()
	{
		return grass2;
	}

	public Pipe getPipe1()
	{
		return pipe1;
	}

	public Pipe getPipe2()
	{
		return pipe2;
	}

	public Pipe getPipe3()
	{
		return pipe3;
	}

	public void onRestart()
	{
		grass1.onRestart(0, SCROLL_SPEED);
		grass2.onRestart(grass1.getTailX(), SCROLL_SPEED);
		pipe1.onRestart(210, SCROLL_SPEED);
		pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
		pipe3.onRestart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
	}
}
