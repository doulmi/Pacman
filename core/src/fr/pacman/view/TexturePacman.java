package fr.pacman.view;

import com.badlogic.gdx.graphics.Texture;

import fr.pacman.model.Direction;
import fr.pacman.model.Pacman;

public class TexturePacman implements iTexturable {
    private Pacman _pacman;
    private double _deltaT;
    private double _seuil;
    private Texture _textureLEFT, _textureRIGHT, _textureUP, _textureDOWN,
    				_textureLEFT2, _textureRIGHT2, _textureUP2, _textureDOWN2,
    				_textureFull ;

    public TexturePacman (Pacman pacman, double seuil) {
        _pacman = pacman;
        _seuil = seuil;
        _deltaT = 0.0;
        _textureFull = new Texture("pacman-3.png");
        _textureLEFT = new Texture("pacmanLeft.png");
        _textureLEFT2 = new Texture("pacmanLeft-2.png");
        _textureRIGHT = new Texture("pacmanRight.png");
        _textureRIGHT2 = new Texture("pacmanRight-2.png");
        _textureUP = new Texture("pacmanUp.png");
        _textureUP2 = new Texture("pacmanUp-2.png");
        _textureDOWN = new Texture("pacmanDown.png");
        _textureDOWN2 = new Texture("pacmanDown-2.png");
    }
    

    public void render( double delta ) {
    	_deltaT += delta;
    	if ( _deltaT > _seuil ) {
    		_deltaT = 0.0;
    	}
    }

    public Texture getTexture() {
        if (_pacman.getDirection() == Direction.LEFT) {
        	if ( _deltaT < _seuil * 0.5 ) {
        		return _textureLEFT2;
        	} else {
                return _textureLEFT;
        	}
        } else if (_pacman.getDirection() == Direction.RIGHT) {
        	if ( _deltaT < _seuil * 0.5 ) {
        		return _textureRIGHT2;
        	} else {
                return _textureRIGHT;
        	}
        } else if (_pacman.getDirection() == Direction.UP ) {
        	if ( _deltaT < _seuil * 0.5 ) {
        		return _textureUP2;
        	} else {
                return _textureUP;
        	}
        } else if (_pacman.getDirection() == Direction.DOWN ) {
        	if ( _deltaT < _seuil * 0.5 ) {
        		return _textureDOWN2;
        	}  else {
                return _textureDOWN;
        	}
        } else {
        	switch ( _pacman.getPreDir() ) {
        	case DOWN: return _textureDOWN;
        	case UP: return _textureUP;
        	case LEFT: return _textureLEFT;
        	case RIGHT: return _textureRIGHT;
        	default : return _textureFull;
        	}
        }
    }
}
