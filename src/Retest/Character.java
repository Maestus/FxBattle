package Retest;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public  abstract class Character extends  Sprite {
	protected int life;
	Animation moving;	protected final Image character = new Image("file:../Data/pixelart.png");
	public Character(int x, int y, int md, int maX, int maY, int oX, int oY, String img, Pane pane,int life) {
		super(x, y, md, maX, maY, oX, oY, img, pane);
		imageView = new ImageView[4];
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(character);
			imageView[i].setViewport(new Rectangle2D(0,36*i,32,32));
		}
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
