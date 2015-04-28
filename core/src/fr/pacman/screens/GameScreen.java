package fr.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import fr.pacman.model.Direction;
import fr.pacman.model.GuestureManager;
import fr.pacman.model.World;
import fr.pacman.view.TextureFactory;
import fr.pacman.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor {
	private WorldRenderer _renderer;
	private World _world;
	private GuestureManager _guesture;
	private Stage infoStage;
	private Label scoreLabel;
	
	public GameScreen() {
		this._world = new World();
		this._renderer = new WorldRenderer(_world);
		this._guesture = new GuestureManager();
	}
	
	public void init() {
		this._world.init();
		TextureFactory.reset(_world);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		this._renderer.render(delta);
		
		_world.update(delta);
        
        scoreLabel.setText("Score: " + _world.getScore());
        infoStage.act();
        infoStage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		this._renderer.setPpuX(width/(float)this._world.getWidth());
		this._renderer.setPpuY((height*0.9f)/(float)this._world.getHeight());
	}

	@Override
	public void show() {
        Gdx.input.setInputProcessor(this);
		infoStage = new Stage();
		LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
		scoreLabel = new Label("Score: " + 0, labelStyle);
		scoreLabel.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.03f);
		
		infoStage.addActor(scoreLabel);
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
	public World getWorld() {
		return _world;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch( keycode ) {
		case Keys.LEFT : {
			_world.getPacman().setNextDirection(Direction.LEFT); 
			break;
		}
		case Keys.RIGHT : {
			_world.getPacman().setNextDirection(Direction.RIGHT); break;
		}
		case Keys.UP : {
			_world.getPacman().setNextDirection(Direction.UP); break;
		}
		case Keys.DOWN : {
			_world.getPacman().setNextDirection(Direction.DOWN); break;
		}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		_guesture.touchDown(new Vector2(screenX, screenY));
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Direction dir = _guesture.touchUp(new Vector2(screenX, screenY));
        if ( dir != Direction.STOP ) {
            _world.getPacman().setNextDirection( dir );
        }
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		_guesture.touchDrag(new Vector2(screenX, screenY));
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
