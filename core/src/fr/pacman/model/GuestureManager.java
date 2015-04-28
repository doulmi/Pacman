package fr.pacman.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class GuestureManager {
	private ArrayList<Vector2> points;
	
	public GuestureManager() {
		points = new ArrayList<Vector2>();
	}
	
	public void touchDown( Vector2 p ) {
		points.clear();
		points.add( p );
	}
	
	public void touchDrag( Vector2 p ) {
		points.add( p );
	}

	public Direction touchUp( Vector2 p ) {
		points.add( p );
		return getGesture();
	}
	
	private Direction getGesture() {
		if ( points.size() < 3 ) {
			return Direction.STOP;
		}
		Vector2 firstPoint = points.get(0);
		Vector2 lastPoint = points.get(points.size()-1);
		double dx = lastPoint.x - firstPoint.x;
		double dy = lastPoint.y - firstPoint.y;
		
        if ( Math.abs(dx) > Math.abs(dy) ) {
        	if ( dx > 0 ) {
        		return Direction.RIGHT;
        	} else {
        		return Direction.LEFT;
        	}
        } else {
        	if ( dy > 0 ) {
        		return Direction.DOWN;
        	} else {
        		return Direction.UP;
        	}
        }
	}
}
