package Retest;

import javafx.animation.Animation;
import javafx.scene.layout.Pane;

public  abstract class Character extends  Sprite {
	protected int life;
	Animation moving;
	public Character(int x, int y, int mx, int my, int md, int maX, int maY, int oX, int oY, String img, Pane pane,int life) {
		super(x, y, mx, my, md, maX, maY, oX, oY, img, pane);
		this.life = life;
	}

	
	public boolean isDead(){
		if(life<=0)
			return true;
		else
			return false;
	}
	
	public void getHurt(int damage){
		this.life -= damage;
	}
}
