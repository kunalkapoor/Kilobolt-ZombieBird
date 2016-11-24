package com.kilibolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbhelpers.InputHandler;

public class GameScreen implements Screen
{
	private GameWorld world;
	private GameRenderer renderer;
	
	private float runTime;
	
	public GameScreen()
	{
		int screenWidth = Gdx.graphics.getWidth();
		int screenHeight = Gdx.graphics.getHeight();
		int gameWidth = 136;
		int gameHeight = screenHeight / (screenWidth / gameWidth);
		
		int midPointY = gameHeight / 2;
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, gameHeight, midPointY);
		Gdx.input.setInputProcessor(new InputHandler(world));
	}
	
	@Override
	public void render(float delta)
	{
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void show()
	{
		Gdx.app.log("ZBGame", "Show");
	}

	@Override
	public void hide()
	{
		Gdx.app.log("ZBGame", "Hide");
	}

	@Override
	public void pause()
	{
		Gdx.app.log("ZBGame", "Pause");
	}

	@Override
	public void resume()
	{
		Gdx.app.log("ZBGame", "Resume");
	}

	@Override
	public void dispose()
	{
		Gdx.app.log("ZBGame", "Dispose");
	}

}
