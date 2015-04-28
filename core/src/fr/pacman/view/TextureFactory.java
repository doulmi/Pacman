package fr.pacman.view;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

import fr.pacman.model.Block;
import fr.pacman.model.Fontome;
import fr.pacman.model.Fontome1;
import fr.pacman.model.Fontome2;
import fr.pacman.model.Fontome3;
import fr.pacman.model.PacGomme;
import fr.pacman.model.Pacman;
import fr.pacman.model.SuperPacGomme;
import fr.pacman.model.Vide;
import fr.pacman.model.World;

public class TextureFactory {
	private static TextureFactory _instance = null;
	private HashMap<Class<?>, iTexturable> _textures;
	
	private TextureFactory(World world) {
		_textures = new HashMap<Class<?>, iTexturable>();
		_textures.put(Pacman.class, new TexturePacman(world.getPacman(), 0.2));
		_textures.put(Block.class, new TextureUnique(new Texture("bloc.png")));
		_textures.put(Fontome.class, new TextureUnique(new Texture("ghost1.png")));
		_textures.put(Vide.class, new TextureUnique(new Texture("dark.png")));
		_textures.put(PacGomme.class, new TextureUnique(new Texture("pellet.png")));
		_textures.put(SuperPacGomme.class, new TextureSuperPacGomme(2.4));
        _textures.put(Fontome1.class, new TextureFontome(world.getFontomes().get(0)));
		_textures.put(Fontome2.class, new TextureFontome(world.getFontomes().get(1)));
		_textures.put(Fontome3.class, new TextureFontome(world.getFontomes().get(2)));
    }
	
	public static TextureFactory getInstance(World world) {
		if ( _instance == null ) {
			_instance = new TextureFactory(world);
		}
		return _instance;
	}
	
	public Texture getTexture(Class<?> c) {
		return _textures.get(c).getTexture();
	}
	
	public iTexturable getTexturable(Class<?> c) {
		return _textures.get(c);
	}
	
	public Texture getTexturePacman() {
		return _textures.get(Pacman.class).getTexture();
	}
	
	public Texture getTextureBloc() {
		return _textures.get(Block.class).getTexture();
	}

	public static void reset(World world) {
		_instance = null;
    }
}
