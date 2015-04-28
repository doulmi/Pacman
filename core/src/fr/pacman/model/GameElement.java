package fr.pacman.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.pacman.view.TextureFactory;

public abstract class GameElement {
	public static final float SIZE = 1f;
	private Vector2 _position;
	private World _world;
	
	public GameElement( Vector2 pos, World world ) {
		_position = pos;
		this._world = world;
	}

	public Vector2 getPosition() {
		return _position;
	}
	
	public void setPosition(Vector2 newPos) {
		this._position = newPos;
	}
	
	public float getWidht() {
		return SIZE;
	}
	
	public float getHeight() {
		return SIZE;
	}
	
	public Texture getTexture() {
		return TextureFactory.getInstance(_world).getTexture(this.getClass());
	}

	public World getWorld() {
		return _world;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(_position.x - SIZE / 2, _position.y - SIZE / 2, SIZE, SIZE);
	}
}
