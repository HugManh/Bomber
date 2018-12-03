package uet.oop.bomberman.entities.bomb;

import java.io.File;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.*;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;


public class FlameSegment extends Entity {

	protected boolean _last;
        protected Board _board;
        
	/**
	 *
	 * @param x
	 * @param y
	 * @param direction
	 * @param last cho biết segment này là cuối cùng của Flame hay không,
	 * segment cuối có sprite khác so với các segment còn lại
         * @param board
	 */
        
	public FlameSegment(int x, int y, int direction, boolean last,Board board) {
		_x = x;
		_y = y;
		_last = last;
                _board = board;
		switch (direction) {
			case 0:
				if(!last) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_top_last2;
				}
			break;
			case 1:
				if(!last) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_right_last2;
				}
				break;
			case 2:
				if(!last) {
					_sprite = Sprite.explosion_vertical2;
				} else {
					_sprite = Sprite.explosion_vertical_down_last2;
				}
				break;
			case 3: 
				if(!last) {
					_sprite = Sprite.explosion_horizontal2;
				} else {
					_sprite = Sprite.explosion_horizontal_left_last2;
				}
				break;
		}
	}
	
	@Override
	public void render(Screen screen) {
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	@Override
	public void update() 
        {
            //
        }

        /*
        public void Sound(int character)
        {
            File deadthSound = new File("C:\\Users\\think\\Documents\\game\\bom\\res\\sounds\\death.wav");
            File roundEndSound = new File("C:\\Users\\think\\Documents\\game\\bom\\res\\sounds\\round_end.wav");
            Music whenDieMusic = TinySound.loadMusic(deadthSound);
            Music roundEnd = TinySound.loadMusic(roundEndSound);
            
            if(character == 1)
            {
                whenDieMusic.play(false);
                roundEnd.play(false);
            }
            else
            {
                //
            }
        }
        */
        
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi FlameSegment va chạm với Character
            if(e instanceof Bomber) {
                
		((Bomber)e).kill();
            }
            
            if(e instanceof Enemy) {
		((Enemy)e).kill();
	    }
            return true;
	}
	

}