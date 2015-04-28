package fr.pacman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.pacman.model.GameElement;
import fr.pacman.model.PacGomme;
import fr.pacman.model.Pacman;
import fr.pacman.model.SuperPacGomme;
import fr.pacman.model.Vide;
import fr.pacman.model.World;

public class WorldRenderer {
	private SpriteBatch _spriteBatch;
	private float _ppuX;
	private float _ppuY;
	private World _world;

	public WorldRenderer( World world ) {
		this._world = world;
		this._spriteBatch = new SpriteBatch();
		this._ppuX = 1;
		this._ppuY = 1;
	}
	
	public void setWorld( World world ) {
		this._world = world;
	}
	
	public void render(float delta) {
		TexturePacman texturePacman = (TexturePacman) TextureFactory.getInstance(_world).getTexturable(Pacman.class);
		texturePacman.render(delta);

		this._spriteBatch.begin();
		for ( GameElement element : this._world ) {
			Texture texture = null;
			if ( element instanceof Vide ) {
				Vide v = (Vide)element;
				if ( v.hasSuperGomme() ) {
					TextureSuperPacGomme textureGomme = (TextureSuperPacGomme) TextureFactory.getInstance(_world).getTexturable(SuperPacGomme.class);
					textureGomme.render(delta);
					
					texture = TextureFactory.getInstance(_world).getTexture(SuperPacGomme.class);
				} else if ( v.hasNormalGomme() ) {
					texture = TextureFactory.getInstance(_world).getTexture(PacGomme.class);
				} else {
					texture = TextureFactory.getInstance(_world).getTexture(Vide.class);
				}
			} else {
				texture = TextureFactory.getInstance(_world).getTexture(element.getClass());
			}

			this._spriteBatch.draw(
                texture,
				element.getPosition().x * element.getWidht() * this._ppuX,
				element.getPosition().y * element.getHeight() * this._ppuY + Gdx.graphics.getHeight() * 0.1f,
				element.getWidht() * this._ppuX,
				element.getHeight() * this._ppuY
			);
		}
		this._spriteBatch.end();
	}

	public void setPpuX(float ppuX) {
		this._ppuX = ppuX;
	}

	public void setPpuY(float ppuY) {
		this._ppuY = ppuY;
	}
	
	public float getPpuy() {
		return this._ppuY;
	}
	
	public float getPpuX() {
		return this._ppuX;
	}
}
