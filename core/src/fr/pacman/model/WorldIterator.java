package fr.pacman.model;

import java.util.Iterator;

public class WorldIterator implements Iterator<GameElement> {
	private World _world;
	private Iterator<GameElement> _mazeIterator;
	private Iterator<Fontome> _fontomes;
	int _i;

	public WorldIterator(World world) {
		this._world = world;
		this._mazeIterator = this._world.getMaze().iterator();
		this._fontomes = this._world.getFontomes().iterator();
		this._i = 0; /* 0 = maze, 1 = fontomes, 2 = pacman */
	}

	@Override
	public boolean hasNext() {
		return (this._i < 2);
	}

	@Override
	public GameElement next() {
		if(_i == 0) {
			if (!this._mazeIterator.hasNext()) {
				_i = 1; // on passe à fontomes 
			}
		} else if (_i == 1) {
			if ( !this._fontomes.hasNext() ) {
				_i = 2; //on passe a pacman
			}
		} else {
			_i ++;
		}

		switch(this._i) {
		case 0 : return this._mazeIterator.next();
		case 1 : return this._fontomes.next();
		case 2 : return this._world.getPacman();
		default : return null;	/*cas erreur*/
		}
	}

	@Override
	public void remove() {
	}
}
