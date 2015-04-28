package fr.pacman.view;

import com.badlogic.gdx.graphics.Texture;

public class TextureUnique implements iTexturable {
	private Texture _texture;

    public TextureUnique (Texture texture) {
        _texture = texture;
    }

    public Texture getTexture () {
        return _texture;
    }
}
