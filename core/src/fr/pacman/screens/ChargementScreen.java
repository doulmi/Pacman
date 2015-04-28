package fr.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import fr.pacman.PacmanGame;

public class ChargementScreen implements Screen {
	private static final Music beginMusic = Gdx.audio.newMusic(Gdx.files.internal("Pacman_Opening_Song_Sound_Effect.mp3"));
	private Stage stage;
	private Label label;
	private int step = 50;
	private int currentStep;

	public ChargementScreen() {
		stage = new Stage();
	}
	@Override
	public void show() {
		LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
		label = new Label("Loading...", labelStyle);
		label.setPosition(200, 250);
		stage.addActor(label);
	}

	@Override
	public void render(float delta) {
		currentStep ++;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();	
	}
	
	public boolean finishLoading() {
		beginMusic.play();
		return currentStep >= step;
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
