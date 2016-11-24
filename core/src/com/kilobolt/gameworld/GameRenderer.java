package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;

public class GameRenderer
{
	private GameWorld world;
	private Bird bird;
	private ScrollHandler scrollHandler;
	private Grass grass1, grass2;
	private Pipe pipe1, pipe2, pipe3;

	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY)
	{
		this.world = world;
		bird = world.getBird();
		scrollHandler = world.getScrollHandler();
		grass1 = scrollHandler.getGrass1();
		grass2 = scrollHandler.getGrass2();
		pipe1 = scrollHandler.getPipe1();
		pipe2 = scrollHandler.getPipe2();
		pipe3 = scrollHandler.getPipe3();

		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);
		// Background
		shapeRenderer.setColor(55 / 255f, 80 / 255f, 100 / 255f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Grass
		shapeRenderer.setColor(111 / 255f, 186 / 255f, 45 / 255f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Dirt
		shapeRenderer.setColor(147 / 255f, 80 / 255f, 27 / 255f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);
		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();
		batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

		drawGrass();
		drawPipes();

		batcher.enableBlending();

		drawSkulls();
		if (bird.shouldFlap())
			batcher.draw(AssetLoader.birdAnim.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth() / 2f, bird.getHeight() / 2f, bird.getWidth(),
					bird.getHeight(), 1, 1, bird.getRotation());
		else
			batcher.draw(AssetLoader.bird, bird.getX(), bird.getY(), bird.getWidth() / 2f, bird.getHeight() / 2f, bird.getWidth(), bird.getHeight(), 1, 1,
					bird.getRotation());
		
		String message;
		TextBounds bounds;
		if(world.isReady())
		{
			message = "Touch";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 40);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 41);
			
			message = "To";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 0);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 1);
			
			message = "Start";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 + 40);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 + 41);
		}
		else if(world.isGameOver())
		{
			message = "Game Over";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 80);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 81);
			
			message = "Touch to";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 40);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 41);
			
			message = "try again";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 20);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 21);
		}
		else if(world.isHighScore())
		{
			message = "Game Over";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 80);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 81);
			
			message = "Touch to";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 40);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 41);
			
			message = "try again";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 - 20);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 - 21);
			
			message = "High Score!";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 + 39);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 + 40);
			
			message = GameWorld.getScore()+"";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 204/2 + 69);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 204/2 + 70);
		}
		else
		{
			message = GameWorld.getScore()+"";
			bounds = AssetLoader.font.getBounds(message);
			AssetLoader.shadow.draw(batcher, message, (136/2) - bounds.width/2, 12);
			AssetLoader.font.draw(batcher, message, (136/2) - bounds.width/2 - 1, 11);
		}
		
		batcher.end();
	}

	private void drawSkulls()
	{
		batcher.draw(AssetLoader.skullUp, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(AssetLoader.skullDown, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(AssetLoader.skullUp, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(AssetLoader.skullDown, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

		batcher.draw(AssetLoader.skullUp, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(AssetLoader.skullDown, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
	}

	private void drawPipes()
	{
		batcher.draw(AssetLoader.bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
		batcher.draw(AssetLoader.bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45, pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

		batcher.draw(AssetLoader.bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
		batcher.draw(AssetLoader.bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45, pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

		batcher.draw(AssetLoader.bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
		batcher.draw(AssetLoader.bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45, pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
	}

	private void drawGrass()
	{
		batcher.draw(AssetLoader.grass, grass1.getX(), grass1.getY(), grass1.getWidth(), grass1.getHeight());
		batcher.draw(AssetLoader.grass, grass2.getX(), grass2.getY(), grass2.getWidth(), grass2.getHeight());
	}
}
