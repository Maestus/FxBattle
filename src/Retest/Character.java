package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public  abstract class Character extends  Sprite {
	protected int life;
	ArrayList<Vampire> vamp;
	Animation moving;	protected final Image character = new Image("file:../Data/pixelart.png");
	ReentrantLock lock;

	public Character(int x, int y, int md, int maX, int maY, int oX, int oY, String img, Pane pane,int life,ArrayList<Vampire> v,	ReentrantLock lock
) {
		super(x, y, md, maX, maY, oX, oY, img, pane);
		imageView = new ImageView[4];
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(character);
			imageView[i].setViewport(new Rectangle2D(oX,36*i,32,32));
		}
		vamp= v;
		this.life = life;
		this.lock = lock;

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
