package uet.oop.bomberman.entities.tile.destroyable;

import java.io.File;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

/**
 * Đối tượng cố định có thể bị phá hủy
 */
public class DestroyableTile extends Tile {

	private final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
	private int _animate = 0;
	protected boolean _destroyed = false;
	protected int _timeToDisapear = 20;
	protected Sprite _belowSprite = Sprite.grass; //default
	
	public DestroyableTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(_destroyed) {
			if(_animate < MAX_ANIMATE) _animate++; else _animate = 0; //reset animation
			if(_timeToDisapear > 0) 
				_timeToDisapear--;
			else
				remove();
		}
                else {
                  
                }
	}

	public boolean isDestroyed() {
		return _destroyed;
	}
	
	public void destroy() {
		_destroyed = true;
	}
	
        private void BrickFallingSound()
        {
            File file = new File("C:\\Users\\think\\Documents\\game\\bom\\res\\sounds\\brickfall.wav");
            Music falling = TinySound.loadMusic(file);
            falling.play(false);
        }
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Flame)
                {
                    destroy();
                    BrickFallingSound();
                }
			
                
			
		return false;
	}
	
	public void addBelowSprite(Sprite sprite) {
		_belowSprite = sprite;
	}
	
	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
		int calc = _animate % 30;
		
		if(calc < 10) {
			return normal;
		}
			
		if(calc < 20) {
			return x1;
		}
			
		return x2;
	}
	
}
