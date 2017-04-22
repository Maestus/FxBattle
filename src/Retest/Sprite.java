package Retest;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite {
	protected double posX;
	protected double posY;
	protected double moveX;
	protected double moveY;
	protected int maxX;
	protected int maxY;
	protected int moveDist;
	protected  int offsetX,offsetY;
	protected int currentView;
	ImageView [] imageView ;
	String image;
	Pane p;
	
	public Sprite(int x,int y,int mx,int my,int md,int maX,int maY,int oX,int oY,String img,Pane pane){
		posX = x;
		posY = y;
		moveX = mx;
		moveY = my;
		maxX = maX;
		maxY = maY;
		moveDist = md;
		offsetX =oX;
		offsetY = oY;
		image = img;
		p =pane;
	}
	public ImageView getCurrentView(){
		return imageView[currentView];
	}
	public void add(){
		p.getChildren().add(getCurrentView());
	}
	public void remove(){
		p.getChildren().remove(getCurrentView());
	}
	public boolean checkCollide(Sprite sp){
		if(sp.imageView[sp.currentView].intersects(this.posX+this.moveX,this.posY+this.moveY,imageView[currentView].getFitWidth(),imageView[currentView].getFitHeight()))
			return true;
		else
			return false;
	}
	public abstract void move();
	public abstract void update();
}
