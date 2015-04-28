package fr.pacman.model;

import com.badlogic.gdx.math.Vector2;

public class Vide extends GameElement {
	//0: no gomme, 1: normal gomme, 2: superGomme
	private int gommeState;
	private boolean intersection;
	private boolean isHome;

	public Vide(Vector2 pos, World world, boolean intersection) {
		super(pos, world);
		this.intersection = intersection;
		gommeState = 1;
		isHome = false;
	}
	
	public void setSuperGomme() {
		gommeState = 2;
	}
	
	public boolean hasSuperGomme() {
		return gommeState == 2;
	}
	
	public boolean hasNormalGomme() {
		return gommeState == 1;
	}
	
	public void setGomme() {
		gommeState = 1;
	}
	
	public void clearGomme() {
		gommeState = 0;
	}
	
	public boolean hasGomme() {
		return gommeState == 0;
	}

	public boolean isIntersection() {
		return intersection;
	}

	public boolean isHome() {
		return isHome;
	}

	public void setHome() {
		this.isHome = true;
	}
}
