package fr.pacman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import fr.pacman.screens.ChargementScreen;
import fr.pacman.screens.FinScreen;
import fr.pacman.screens.GameScreen;

public class PacmanGame extends Game {
	//0 : chargement, 1: game, 2: fin
	private int state = 0;
    private GameScreen mainScreen;
    private ChargementScreen chargementScreen;
    private FinScreen finScreen;
    private AssetManager manager;

	@Override
	public void create () {
		manager = new AssetManager();
		chargementScreen = new ChargementScreen();
		mainScreen = new GameScreen();
		this.setScreen(chargementScreen);
		finScreen = new FinScreen(this);
	}
	
	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void render() {
		super.render();
		if ( state == 0 ) {
			if ( chargementScreen.finishLoading() ) {
                state = 1;
                this.setScreen(mainScreen);
            }
		} else if ( state == 1 ) {
			if ( mainScreen.getWorld().isGameover() ) {
                state = 2;
			}
		} else {
			finScreen.setScore(mainScreen.getWorld().getScore());
			this.setScreen(finScreen);
		}
	}
	
	public void restart() {
		mainScreen.init();
		state = 1;
		setScreen(mainScreen);
	}
}
