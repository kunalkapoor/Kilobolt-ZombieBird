package com.kilobolt.zbhelpers;

import com.badlogic.gdx.InputProcessor;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameworld.GameWorld;

public class InputHandler implements InputProcessor
{
	private GameWorld gameWorld;
	private Bird bird;
	
	public InputHandler(GameWorld gameWorld)
	{
		this.gameWorld = gameWorld;
		bird = gameWorld.getBird();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(gameWorld.isGameOver() || gameWorld.isHighScore())
			gameWorld.restart();
		
		if(gameWorld.isReady())
			gameWorld.start();
		
		bird.onClick();
		
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
