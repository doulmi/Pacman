package fr.pacman.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

public class World implements Iterable<GameElement>{
	private Pacman _packman;
	private ArrayList<Fontome> _fontomes;
	private Maze _maze;
	private int _score;
	private int escapeStep = 0;
	private static final int espaceStepCount = 700;
	
	public World() {
		init();
	}
	
	public void init() {
		this._packman = new Pacman(new Vector2(1,1), this);
		this._fontomes = new ArrayList<Fontome>();
		this._maze = new Maze(this);
		this._score = 0;
	}
	
	public void addFontome( Fontome f ) {
		_fontomes.add(f);
	}
	
	public ArrayList<Fontome> getFontomes() {
		return _fontomes;
	}

	public void scoreAdd( int scoreAdd ) {
		this._score += scoreAdd;
	}

	public void setScore( int score ) {
		this._score = score;
	}
	public int getScore() {
		return _score;
	}
	public int getHeight() {
		return _maze.getHeight();
	}
	
	public int getWidth() {
		return _maze.getWidth();
	}
	
	public Pacman getPacman() {
		return _packman;
	}
	
	public Maze getMaze() {
		return _maze;
	}
	
	public int getGommeNum() {
		return _maze.getGommeNum();
	}

	public void decreaseGommeNum() {
		_maze.decreaseGommeNum();
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new WorldIterator(this);
	}

	public void fontameEscape() {
		escapeStep = 1;
		for ( Fontome f : _fontomes ) {
			f.setEscape(true);
		}
	}
	
	public boolean isGameover() {
		return ! this.getPacman().isLive() || _maze.getGommeNum() == 0;
	}

	public void update(float delta) {
		getPacman().update(delta * 0.1f);
        for ( Fontome f : getFontomes() ) {
            f.update(delta);
        }
		if ( escapeStep > 0 ) {
			escapeStep ++;
            if ( escapeStep == espaceStepCount ) {
                endEscape();
            }
		}
	}
	
	public void endEscape() {
        for ( Fontome f : getFontomes() ) {
            f.setEscape(false);
        }
        getPacman().resetFontomeEaten();
	}
}