package com.kilobolt.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kilibolt.screens.GameScreen;
import com.kilobolt.zbhelpers.AssetLoader;

public class ZBGame extends Game
{
	@Override
	public void create()
	{
		Gdx.app.log("ZBGame", "Game Created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}
}
