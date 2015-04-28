package fr.pacman.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Fontome3 extends Fontome {
    private boolean up = true;
	public Fontome3(Vector2 pos, World world) {
		super(pos, world);
		roundTimeCount = 24;
	}

	@Override
	public void chooseDirection(ArrayList<Direction> dirs) {
		int randomStrategy = r.nextInt(2);
		if ( randomStrategy == 0 ) {
			chooseStrategy1(dirs);
		} else {
			chooseStrategy2(dirs);
		}
	}

	private void chooseStrategy1(ArrayList<Direction> dirs) {
		Direction randomDir = dirs.get( r.nextInt(dirs.size()) );
		setNextDirection(randomDir);	
	}

	private void chooseStrategy2(ArrayList<Direction> dirs) {
		Vector2 pacPos = getWorld().getPacman().getPosition();
		Vector2 selfPos = getPosition();
		
		Direction bestDir = Direction.STOP;
		float bestDistance = Float.MAX_VALUE;
		for ( Direction dir : dirs ) {
			switch( dir ) {
			case RIGHT:
				if ( pacPos.x > selfPos.x ) {
					float distance = pacPos.x - selfPos.x;
					if ( distance < bestDistance ) {
						bestDir = Direction.RIGHT;
					}
				}
				break;
			case LEFT:
				if ( pacPos.x < selfPos.x ) {
					float distance = selfPos.x - pacPos.x;
					if ( distance <= bestDistance ) {
						bestDir = Direction.LEFT;
					}
				}
				break;
			case UP:
				if ( pacPos.y > selfPos.y ) {
				float distance = pacPos.y - selfPos.y;
					if ( distance < bestDistance ) {
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

	@Override
	public void preMoveAction() {
		if ( getDirection() == Direction.STOP ) {
			if ( roundTime == roundTimeCount - 3 ) {
				setDirection(Direction.UP);
                roundTime ++;
			} else if ( roundTime == roundTimeCount - 2 ) {
				setDirection(Direction.UP);
                roundTime ++;
			} else if ( roundTime == roundTimeCount - 1 ) {
				roundTime ++;
			} else {
                if ( up ) {
                    up = false;
                    setDirection(Direction.DOWN);
                } else {
                    up = true;
                    setDirection(Direction.UP);
                    roundTime ++;
                }
			}
		} else if ( getDirection() == Direction.UP ) {
            up();
		} else if ( getDirection() == Direction.DOWN ) {
            down();
		} else if ( getDirection() == Direction.LEFT ) {
			left();
		} else {
			right();
		}
	}
}
