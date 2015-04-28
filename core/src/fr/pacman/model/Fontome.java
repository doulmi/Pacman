package fr.pacman.model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public abstract class Fontome extends DynamiqueGameElement {
	protected int preMove;
	protected int currentMove = 0;
	protected boolean escape;
	private int liveStep = 0;
	private static final int liveStepCount = 500;
	private boolean _live;
    protected int roundTimeCount = 0;
    protected int roundTime = 0;
    protected Vector2 initPos;
    protected Random r = new Random();

	public Fontome(Vector2 pos, World world) {
		super(pos, world);
		initPos = pos;
		escape = false;
		_live = true;
		setVelocity(0.06f);
	}
	
    public void update(float delta) {
    	if ( roundTime <= roundTimeCount ) {
            preMoveAction();
    	} else {
            if ( this._live ) {
                move();
            } else {
                liveStep ++;
                if ( liveStep == liveStepCount ) {
                    liveStep = 0;
                    _live = true;
                    escape = false;
                }
            }
    	}
	}
    
    
    @Override
	public void move() {
    	Vector2 pos = getPosition();
        switch( _direction ) {
        case LEFT: {
        	if ( leftStep <= stepCount ) {
                this.setPosition(pos.add(-_velocity, 0)); 
                leftStep ++;
                if ( leftStep == stepCount ) {
                	leftStep = 0;
                	setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
                	if ( ! canLeft() ) {
                    	setDirection(Direction.STOP);
            		}
                	if ( _nextDir == Direction.LEFT ) {
                        int intX = Math.round(this.getPosition().x) - 1;
                        if ( intX < 0 ) {
                            intX += this.getWorld().getWidth(); 
                            setPosition(new Vector2(intX, this.getPosition().y));
                        } 
                	}
        			tryToChangeDirection();
                }
        	}
            break;
        }
        case RIGHT: {
        	if ( rightStep <= stepCount ) {
        		this.setPosition(pos.add(_velocity, 0));
        		rightStep ++;
        		if ( rightStep == stepCount ) {
        			rightStep = 0;
        			setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
                    if ( ! canRight() ) {
                    	setDirection(Direction.STOP);
                    } 
        			if ( _nextDir == Direction.RIGHT ) {
                        int intX = Math.round(this.getPosition().x) + 1;

                        if ( intX >= getWorld().getWidth() ) {
                            intX -= getWorld().getWidth();
                            setPosition(new Vector2(intX, this.getPosition().y));
                        } 
        			}
        			tryToChangeDirection();
        		}
        	}
        	break;
        }
        case UP: {
        	if ( upStep <= stepCount ) {
        		this.setPosition(pos.add(0, _velocity));
        		upStep ++;
        		if ( upStep == stepCount ) {
        			upStep = 0;
        			setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
                    if ( ! canUp() ) {
                    	setDirection(Direction.STOP);
                    }
        			if ( _nextDir == Direction.UP ) {
        				int intY = Math.round(this.getPosition().y) + 1;

        				if ( intY >= this.getWorld().getHeight() ) {
        					intY -= this.getWorld().getHeight();
        					setPosition(new Vector2(this.getPosition().x, intY));
        				} 
        			}
        			tryToChangeDirection();
        		}
        	}
        	break;
        }
        case DOWN: {
        	if ( downStep < stepCount ) {
        		this.setPosition(pos.add(0, -_velocity));
        		downStep ++;
        		if ( downStep == stepCount ) {
        			downStep = 0;
        			setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
                    if ( ! canDown() ) {
                    	setDirection(Direction.STOP);
                    }
        			if ( _nextDir == Direction.DOWN ) {
        				int intY = Math.round(this.getPosition().y) - 1;

        				if ( intY < 0 ) {
        					intY += this.getWorld().getHeight();
        					setPosition(new Vector2(this.getPosition().x, intY));
        				} 
        			} 
        			tryToChangeDirection();
        		}   
            }
        	break;
        }
        default : 
        }	
	}
    
    protected void up() {
        Vector2 pos = getPosition();
        this.setPosition(pos.add(0, _velocity));
        upStep ++;
        if ( upStep == stepCount ) {
            upStep = 0;
            setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
            setDirection(Direction.STOP);
        }
    }
    
    protected void down() {
        Vector2 pos = getPosition();
        this.setPosition(pos.add(0, -_velocity));
        downStep ++;
        if ( downStep == stepCount ) {
            downStep = 0;
            setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
            setDirection(Direction.STOP);
        }
    }
    
    protected void left() {
        Vector2 pos = getPosition();
        this.setPosition(pos.add(-_velocity, 0)); 
        leftStep ++;
        if ( leftStep == stepCount ) {
            leftStep = 0;
            setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
            setDirection(Direction.STOP);
    	}
    }
    
    protected void right() {
        Vector2 pos = getPosition();
        this.setPosition(pos.add(_velocity, 0));
        rightStep ++;
        if ( rightStep == stepCount ) {
            rightStep = 0;
            setPosition(new Vector2(Math.round(pos.x), Math.round(pos.y)));
            setDirection(Direction.STOP);
        }
    }

	private void tryToChangeDirection() {
    	ArrayList<Direction> dirs = new ArrayList<Direction>();

		if ( canDown() ) {
			dirs.add(Direction.DOWN);
		}
		
		if ( canUp() ) {
			dirs.add(Direction.UP);
		}
		
		if ( canLeft() ) {
			dirs.add(Direction.LEFT);
		}
		
		if ( canRight()) {
			dirs.add(Direction.RIGHT);
		}
		
		dirs.remove(_preDir.getReverseDirection());
		dirs.remove(_direction.getReverseDirection());
		
		
		if ( dirs.size() > 1 ) {
            if ( isEscape() ) {
                escapeDirection(dirs);
            } else {
                chooseDirection(dirs);
            }
    		changeDirection(_nextDir);
    	} else if ( dirs.size() == 1 ){
            setNextDirection(dirs.get(0));
            changeDirection(_nextDir);
		}
	}
	
	
	public void escapeDirection(ArrayList<Direction> dirs) {
		Vector2 pacPos = getWorld().getPacman().getPosition();
		Vector2 selfPos = getPosition();
		
		Direction bestDir = Direction.STOP;
		float bestDistance = .0f;
		for ( Direction dir : dirs ) {
			switch( dir ) {
			case RIGHT:
				if ( pacPos.x > selfPos.x ) {
					float distance = pacPos.x - selfPos.x;
					if ( distance > bestDistance ) {
						bestDir = Direction.RIGHT;
					}
				}
				break;
			case LEFT:
				if ( pacPos.x < selfPos.x ) {
					float distance = selfPos.x - pacPos.x;
					if ( distance >= bestDistance ) {
						bestDir = Direction.LEFT;
					}
				}
				break;
			case UP:
				if ( pacPos.y > selfPos.y ) {
				float distance = pacPos.y - selfPos.y;
					if ( distance > bestDistance ) {
						bestDir = Direction.UP;
					}
				}
				break;
			case DOWN:
				if ( pacPos.y < selfPos.x ) {
                    float distance = selfPos.y - pacPos.y;
					if ( distance >= bestDistance ) {
						bestDir = Direction.DOWN;
					}
				}
				break;
			default: ;
			}
		}
		if ( bestDir == Direction.STOP ) {
			bestDir = dirs.get(r.nextInt(dirs.size()));
		}
		setNextDirection(bestDir);	
	}

	abstract public void chooseDirection(ArrayList<Direction> dirs);
    abstract public void preMoveAction();

	public boolean isEscape() {
		return escape;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	
	public void dead() {
		_live = false;
		liveStep = 0;
	}
	
	public boolean isLive() {
		return _live;
	}
}
