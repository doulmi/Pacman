package fr.pacman.model;

import com.badlogic.gdx.math.Vector2;

public class FontomeFactory {
	private static FontomeFactory _instance = null;
	private int i = -1;
	
	private FontomeFactory() { };

	public static FontomeFactory getInstance() {
		if ( _instance == null ) {
			_instance = new FontomeFactory();
		}
		return _instance;
	}
	
	public Fontome createFontome(Vector2 pos, World world) {
		i ++;
		if ( i >= 3 ) {
			i = 0;
		}
		if ( i == 0 ) {
			return new Fontome1(pos, world);
		} else if ( i == 1 ) {
			return new Fontome2(pos, world);
		} else {
			return new Fontome3(pos, world);
		}
	}
}
