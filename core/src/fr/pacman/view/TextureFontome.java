package fr.pacman.view;

import com.badlogic.gdx.graphics.Texture;

import fr.pacman.model.Fontome;
import fr.pacman.model.Fontome1;
import fr.pacman.model.Fontome2;
import fr.pacman.model.Fontome3;

public class TextureFontome implements iTexturable {
	private Fontome _f;
	private Texture _textureGhost1, _textureGhost2, _textureGhost3, _textureGhost4, 
        _textureDead, _textureEscaping;
	
	public TextureFontome( Fontome f ) {
		this._f = f;
		_textureGhost1 = new Texture("ghost1.png");
		_textureGhost2 = new Texture("ghost2.png");
		_textureGhost3 = new Texture("ghost3.png");
		_textureGhost4 = new Texture("ghost4.png");
		_textureDead = new Texture("ghostDead.png");
		_textureEscaping = new Texture("ghostEscaping.png");
	}

	@Override
	public Texture getTexture() {
		if ( ! _f.isLive() ) {
			return _textureDead;
		}

		if ( _f.isEscape() ) {
			return _textureEscaping;
		}
		
		if ( _f instanceof Fontome1 ) {
			return _textureGhost1;
		}
		
		if ( _f instanceof Fontome2 ) {
			return _textureGhost2;
		}
		
		if ( _f instanceof Fontome3 ) {
			return _textureGhost3;
		}
		
		return _textureGhost4;
	}

}
