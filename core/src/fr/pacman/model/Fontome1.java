package fr.pacman.model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Fontome1 extends Fontome {
    Random r = new Random();
    private boolean up = true;
	public Fontome1(Vector2 pos, World world) {
		super(pos, world);
		roundTimeCount = 12;
	}

	@Override
	public void chooseDirection(ArrayList<Direction> dirs) {
		Direction randomDir = dirs.get( r.nextInt(dirs.size()) );
		setNextDirection(randomDir);
    }

	@Override
	public void preMoveAction() {
		if ( getDirection() == Direction.STOP ) {
			if ( roundTime == roundTimeCount - 3 ) {
                setDirection(Direction.RIGHT);
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
