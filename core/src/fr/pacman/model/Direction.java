package fr.pacman.model;

public enum Direction {
	LEFT, RIGHT, DOWN, UP, STOP;
	
	public Direction getReverseDirection() {
		switch( this ) {
		case LEFT : return RIGHT;
		case RIGHT : return LEFT;
		case DOWN : return UP;
		case UP : return DOWN;
		default : return STOP;
		}
	}
}
