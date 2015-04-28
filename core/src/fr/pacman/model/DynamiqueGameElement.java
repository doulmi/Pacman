package fr.pacman.model;

import com.badlogic.gdx.math.Vector2;

public abstract class DynamiqueGameElement extends GameElement {
	protected float _velocity;
	protected int upStep = 0;
	protected int downStep = 012;
	protected int leftStep = 0;
	protected int rightStep = 0;
	protected int stepCount;

	protected Direction _direction = Direction.STOP;
	protected Direction _preDir = _direction;
	protected Direction _nextDir = _direction;

	public DynamiqueGameElement(Vector2 pos, World world) {
		super(pos, world);
		setVelocity(0.08f);
	}

	public boolean canUp() {
		Maze maze = this.getWorld().getMaze();
        int intX = Math.round(this.getPosition().x);
        int intY = Math.round(this.getPosition().y) + 1;
        
        if ( intY >= maze.getHeight() ) {
        	intY -= maze.getHeight();
        }
        
        int realY = intX;
        int realX = maze.getHeight() - intY - 1;
        if ( intY >= maze.getHeight() ) {
        	return false;
        }

        GameElement ge = maze.get(realX, realY);

//        if ( ge instanceof Block || ( ge instanceof Vide && ((Vide)ge).isHome() )) return false;
//        return true;
    	if ( ge instanceof Vide && !((Vide)ge).isHome() )  return true;
    	return false;
	}

	public boolean canDown() {
		Maze maze = this.getWorld().getMaze();
		int intX = Math.round(this.getPosition().x);
        int intY = Math.round(this.getPosition().y) - 1;
        
        if ( intY < 0 ) {
        	intY += maze.getHeight();
        }
    	int realY = intX;
        int realX = maze.getHeight() - intY - 1;
    	if ( intY < 0 ) {
    		return false; 
    	}
    	
        GameElement ge = maze.get(realX, realY);
    	
    	if ( ge instanceof Vide && !((Vide)ge).isHome()) return true;	
    	return false;
	}
	
	public boolean canLeft() {
		Maze maze = this.getWorld().getMaze();
		int intX = Math.round(this.getPosition().x) - 1;
        int intY = Math.round(this.getPosition().y);

        int realY = intX;
        int realX = maze.getHeight() - intY - 1;
    	if ( intX < 0 ) {
    		return false;
    	}

    	GameElement ge = maze.get(realX, realY);
    	
//    	if ( ge instanceof Block || ( ge instanceof Vide && ((Vide)ge).isHome() )) return false;
    	if ( ge instanceof Vide && !((Vide)ge).isHome())  return true;
    	return false;
	}
	
	public boolean canRight() {
		Maze maze = this.getWorld().getMaze();
		int intX = Math.round(this.getPosition().x) + 1;
        int intY = Math.round(this.getPosition().y);

        int realY = intX;
        int realX = maze.getHeight() - intY - 1;
		if ( intX >= maze.getWidth() ) {
			return false;
		}

    	GameElement ge = maze.get(realX, realY);
    	
//    	if ( ge instanceof Block || ( ge instanceof Vide && ((Vide)ge).isHome() )) return false;	
//    	return true;
    	if ( ge instanceof Vide && !((Vide)ge).isHome())  return true;
    	return false;
	}
	
	public Direction getDirection() {
		return _direction;
	}
	
	public void setDirection(Direction dir) {
		this._preDir = this._direction;
		this._direction = dir;
	}

	public void setNextDirection(Direction dir) {
		if ( _direction == Direction.STOP ) {
			switch( dir ) {
			case UP: 
				if ( canUp() ) {
					setDirection(dir);
				}
				break;
			case DOWN:
				if ( canDown() ) {
					setDirection(dir);
				}
				break;
			case LEFT:
				if ( canLeft() ) {
					setDirection(dir);
				}
				break;
			case RIGHT:
				if ( canRight() ) {
					setDirection(dir);
				}
				break;
			default: ;
			}
		}
		_nextDir = dir;
	}

	protected void changeDirection( Direction dir ) {
		switch( dir ) {
		case UP: {
            if (this._direction == Direction.DOWN || canUp() ) {
            	setDirection(dir);
            }
			break;
		}
		case DOWN: {
			if (this._direction == Direction.UP || canDown() ) {
				setDirection(dir);
			}
			break;
		}
		case LEFT: {
			if ( this._direction == Direction.RIGHT || canLeft() ) {
				setDirection(dir);
			}
			break;
		}
		case RIGHT : {
			if ( this._direction == Direction.LEFT || canRight() ) {
				setDirection(dir);
			}
			break;
		}
		default:
		}
	}
	
	public abstract void move();
	

	public float getVelocity() {
		return _velocity;
	}

	public void setVelocity(float velocity) {
		this._velocity = velocity;
		this.stepCount = (int) (1 / _velocity);
	}
	
	public Direction getNextDirection() {
		return _nextDir;
	}

	public Direction getPreDir() {
		return _preDir;
	}
}
