package fr.pacman.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MazeIterator implements Iterator<GameElement> {

	private Maze _maze;
	int _i, _j;

	public MazeIterator(Maze maze) {
		this._maze = maze;
		_i = _j = 0;
	}

	@Override
	public boolean hasNext() {
		return (_i < this._maze.getHeight()) && (_j < this._maze.getWidth());
	}

	@Override
	public GameElement next() {
		if (!this.hasNext())
			throw new NoSuchElementException("No more game elements");
		GameElement gameElement;
		do {
			gameElement = this._maze.get(_i, _j);
			_j = (_j + 1) % this._maze.getWidth();
			if (_j == 0) {
				_i++;
			}
		} while (gameElement == null && this.hasNext());
		return gameElement;
	}

	@Override
	public void remove() {
	}
}
