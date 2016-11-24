package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;

public class GameWorld
{
	private Bird bird;
	private ScrollHandler scrollHandler;
	private int midPointY;
	
	private static int score;
	private GameState gameState;
	
	public enum GameState
	{
		READY, RUNNING, GAMEOVER, HIGHSCORE
	}
	
	public GameWorld(int midPointY)
	{
		gameState = GameState.READY;
		bird = new Bird(33, midPointY-5, 17, 12);
		scrollHandler = new ScrollHandler(midPointY + 66);
		this.midPointY = midPointY;
	}
	
	public void update(float delta)
	{
		switch(gameState)
		{
			case READY:
				updateReady(delta);
				break;
			case RUNNING:
				updateRunning(delta);
				break;
			case GAMEOVER:
				break;
		}
	}
	
	private void updateReady(float delta)
	{
		
	}

	public void updateRunning(float delta)
	{
		if(delta > 0.15f)
			delta = 0.15f;
		
		bird.update(delta);
		scrollHandler.update(delta);
		
		if(bird.isAlive())
		{
			if(scrollHandler.collidesWithPipes(bird))
			{
				AssetLoader.dead.play();
				scrollHandler.stop();
				bird.die();
				gameState = GameState.GAMEOVER;
				
				if(score > AssetLoader.getHighScore())
				{
					AssetLoader.setHighScore(score);
					gameState = GameState.HIGHSCORE;
				}
			}
			else if(scrollHandler.collidesWithGrass(bird))
			{
				AssetLoader.dead.play();
				scrollHandler.stop();
				bird.die();
				bird.decelerate();
				gameState = GameState.GAMEOVER;
				
				if(score > AssetLoader.getHighScore())
				{
					AssetLoader.setHighScore(score);
					gameState = GameState.HIGHSCORE;
				}
			}
		}
	}
	
	public Bird getBird()
	{
		return bird;
	}

	public ScrollHandler getScrollHandler()
	{
		return scrollHandler;
	}

	public static int getScore()
	{
		return score;
	}

	public static void addScore(int increment)
	{
		score += increment;
	}
	
	public boolean isReady()
	{
		return gameState == GameState.READY;
	}
	
	public void start()
	{
		gameState = GameState.RUNNING;
	}
	
	public void restart()
	{
		gameState = GameState.READY;
		score = 0;
		bird.onRestart(midPointY - 5);
		scrollHandler.onRestart();
	}
	
	public boolean isGameOver()
	{
		return gameState == GameState.GAMEOVER;
	}
	
	public boolean isHighScore()
	{
		return gameState == GameState.HIGHSCORE;
	}
}
