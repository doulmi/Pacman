package fr.pacman.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Pacman extends DynamiqueGameElement {
	private static final Music eatGhostMusic = Gdx.audio.newMusic(Gdx.files.internal("Pacman_Opening_Song_Sound_Effect.mp3"));
	private static final Music dieMusic = Gdx.audio.newMusic(Gdx.files.internal("Pacman_Dies_Sound_Effect.mp3"));
	private static final Music intermissionMusic = Gdx.audio.newMusic(Gdx.files.internal("Pacman_Intermission_Sound_Effect.mp3"));
	private static final Music walkMusic = Gdx.audio.newMusic(Gdx.files.internal("Pacman_Waka_Waka_Sound_Effect.mp3"));

	private boolean _live;
	private int _fontomeEaten;
	private static final int FONTOME_BASE_SCORE = 20;
	
	public Pacman(Vector2 pos, World world) {
		super(pos, world);
		
		_live = true;
		_fontomeEaten = 0;
	}

	public void update(float delta) {
		move();
		eat();
	}

	
	public void eat() {
		Maze maze = this.getWorld().getMaze();
        int intX = Math.round(this.getPosition().x);
        int intY = Math.round(this.getPosition().y);

        int realY = intX;
        int realX = maze.getHeight() - intY - 1;	
        GameElement ge = maze.get(realX, realY);

        if ( ge instanceof Vide ) {
        	Vide v = (Vide)ge;
        	if ( v.hasSuperGomme() ) {
        		this.getWorld().fontameEscape();
        		this.getWorld().decreaseGommeNum();
        	} else if ( v.hasNormalGomme() ) {
        		this.getWorld().scoreAdd(1);
        		this.getWorld().decreaseGommeNum();
        		walkMusic.play();
        	}
        	v.clearGomme();
        }
        
        for ( Fontome f : getWorld().getFontomes() ) {
        	Vector2 fPos = f.getPosition();
        	if ( Math.abs(fPos.x - this.getPosition().x) < 0.5 && Math.abs(fPos.y - this.getPosition().y) < 0.5 ) {
        		if ( f.isLive() ) {
                    if ( f.isEscape() ) {
                    	eatGhostMusic.play();
                        _fontomeEaten ++;
                        
                        LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
                		Label label = new Label("Loading...", labelStyle);
                		label.setPosition(200, 250);

                        this.getWorld().scoreAdd(_fontomeEaten * FONTOME_BASE_SCORE);
                        f.dead();
                    } else {
                    	intermissionMusic.play();
                        this.dead();
                        dieMusic.play();
                    }
        		}
        	}
        }
	}

	@Override
	public void move()  {
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
                    changeDirection(_nextDir);
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
                    changeDirection(_nextDir);
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
                    changeDirection(_nextDir);
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
                    changeDirection(_nextDir);
        		}   
            }
        	break;
        }
        default : 
        }
	}
	
	public void dead() {
		this._live = false;
	}
	
	public boolean isLive() {
		return this._live;
	}
	
	public void resetFontomeEaten() {
		_fontomeEaten = 0;
	}
}
