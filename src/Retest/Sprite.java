package Retest;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Sprite extends Transition{
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
	private int lastIndex;
	public Sprite(int x,int y,int md,int maX,int maY,int oX,int oY,String img,Pane pane){
		posX = x;
		posY = y;
		moveX = 0;
		moveY = 0;
		maxX = maX;
		maxY = maY;
		currentView = 0;
		moveDist = md;
		this.setInterpolator(Interpolator.LINEAR);
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
		
		if(posX<=sp.posX && posX+32>sp.posX || sp.posX<=posX && sp.posX+32 > posX){
			if(posY >sp.posY+32 && sp.posY+moveY <= sp.posY)
				return true;
			else if(posY+32 <sp.posY && posY+moveY+32 >=sp.posY)
				return true;
		}
		else if(posY<=sp.posY && posY+32>sp.posY || sp.posY<=posY && sp.posY+32 > posY){

			if(posX >sp.posX+32 && sp.posX+moveX <= sp.posX)
				return true;
			else if(posX+32 <sp.posX && posX+moveX+32 >=sp.posX)
				return true;
		}
			return false;
	}
	public void interpolate(double k){
		final int index = Math.min((int) Math.floor(k * 3), 3 - 1);
        if (index != 3) {
            final int x = (index % 3) * 36  + offsetX;
            final int y = (index / 3) * 36 + offsetY;
            getCurrentView().setViewport(new Rectangle2D(x, y, 32, 32));
            lastIndex = index;
        }
	
	}
	public Rectangle2D getBounds(){
		return new Rectangle2D(posX,posY,32,32);
	}
	public abstract void move();
	public abstract void update();
}
