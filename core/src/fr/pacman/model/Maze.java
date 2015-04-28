package fr.pacman.model;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

public class Maze implements Iterable<GameElement>{
	private int height;
	private int width;
	private World world;
	private int gommeNum;
	
	public static class MazeCOR {
		private static MazeCOR _instance = null;

		private MazeCOR() { } 

		public static MazeCOR getCOR() {
			if ( _instance == null ) {
				_instance = new MazeCOR();
			}
			return _instance;
		}
		
		public GameElement build(World world, int elementType, int x, int y ) {
			int realX = y;
			int realY = laby1.length - x - 1;
			switch( elementType ) {
			case 0: return new Block(new Vector2(realX, realY), world);
			case 1: return new Vide(new Vector2(realX, realY), world, false);
            case 2: return new Vide(new Vector2(realX, realY), world, true);
			case 3: {
				return FontomeFactory.getInstance().createFontome(new Vector2(realX, realY), world);//return new Fontome3(new Vector2(realX, realY), world);
			}
			case 4: {
				Vide v = new Vide(new Vector2(realX, realY), world, false); 
				v.setHome();
				v.clearGomme();
				return v;
			}
			case 5: {
				Vide v = new Vide(new Vector2(realX, realY), world, false); 
				v.setSuperGomme();
				return v;
			}
			default: return new Vide(new Vector2(realX, realY), world, true); //cas erreur
			}
		}
	}

	/* 0 : mur, 1 : vide, 2 : intersection, 3 : barriere fantomes, 4 : chez fantome, 5 : super pellet */
	private static int[][] laby1 = new int[][] {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 5, 0},
		{0, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 1, 1, 1, 1, 1, 2, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 2, 1, 1, 1, 1, 1, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 4, 4, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 4, 4, 4, 4, 4, 4, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 4, 3, 3, 3, 4, 4, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 5, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 1, 1, 5, 1, 1, 2, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
		{0, 1, 1, 1, 0, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 1, 1, 1, 0},
		{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
		{0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
		{0, 1, 1, 2, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	private GameElement[][] laby2;

	
	public Maze(World world) {
		this.world = world;
		this.gommeNum = 0;
		this.init();
	}
	
	private void init() {
		this.height = laby1.length;
		this.width = laby1[0].length;
		this.laby2 =  new GameElement[this.height][this.width];
		
		int x = 0, y = 0;
		for ( int[] t : laby1 ) {
			for( int elementType : t ) {
				if ( elementType == 2 || elementType == 1 || elementType == 5 ) {
					gommeNum ++;
				}
				GameElement element = MazeCOR.getCOR().build(
					this.world,
					elementType,
					x,
					y
				);
				
				if ( elementType == 3 ) {
					this.world.addFontome((Fontome) element);
				}

				this.laby2[x][y] = element;
				y = ( ++y % this.width );
			}
			x ++;
		}
	}
	
	public int getGommeNum() {
		return gommeNum;
	}

	public void setGommeNum(int gommeNum) {
		this.gommeNum = gommeNum;
	}
	
	public void decreaseGommeNum() {
		this.gommeNum --;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public GameElement get( int x, int y ) {
		return this.laby2[x][y];
	}
	
	@Override
	public Iterator<GameElement> iterator() {
		return new MazeIterator(this);
	}
}
