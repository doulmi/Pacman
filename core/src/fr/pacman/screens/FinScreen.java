package fr.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.pacman.PacmanGame;

public class FinScreen implements Screen {
	private Stage stage;
	private Table table;
	private PacmanGame _game;
	private Skin skin;
	Label label;
	
	public FinScreen(PacmanGame game) {
		stage = new Stage();
		table = new Table();
		createBasicSkin();
		_game = game;
		LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
		label = new Label("Score: 0", labelStyle);
		TextButton restartBtn = new TextButton("Restart", skin);
		
		restartBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				_game.restart();
			}
		});
		table.add( label ).row();
		table.add( new Label(" ", labelStyle )).row();
		table.add( new Label(" ", labelStyle )).row();
		table.add( restartBtn ).row();
		
		table.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);	
	}
	
	public void setScore(int score) {
		label.setText("Score: " + score);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);	
	}
	
	private void createBasicSkin(){
		  //Create a font
		  BitmapFont font = new BitmapFont();
		  font.setScale(1);
		  skin = new Skin();
		  skin.add("default", font);
		 
		  //Create a texture
		  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		  pixmap.setColor(Color.WHITE);
		  skin.add("background",new Texture(pixmap));
		 
		  //Create a button style
		  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		  textButtonStyle.up = skin.newDrawable("background", Color.BLACK);
		  textButtonStyle.down = skin.newDrawable("background", Color.BLACK);
		  textButtonStyle.down = skin.newDrawable("background", Color.BLACK);
		  textButtonStyle.checked = skin.newDrawable("background", Color.BLACK);
		  textButtonStyle.over = skin.newDrawable("background", Color.BLACK);
		  textButtonStyle.font = skin.getFont("default");
		  skin.add("default", textButtonStyle);
	}
		 

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();	
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
	}

}
