package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character implements Runnable{
	protected final Image character = new Image("file:Data/pixelart.png");
	protected int id;
	ArrayList<Vampire> vamp;
	ReentrantLock lock;
	TranslateTransition tt = new TranslateTransition();
	double last_x, last_y;
	
	public Vampire(int x, int y, int md, int maX, int maY, int oX, int oY, String img, Pane pane,
			int life,int id,ArrayList<Vampire> v,	ReentrantLock l) {
		super(x, y,md, maX, maY, oX, oY, img, pane, life);
		this.life =life;
		this.id = id;
		currentView=1;
		vamp = v;
		lock = l;
		
	}
	public int getId(){
		return id;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("in lock"+id);
		int choice = (int) (Math.random()*4);
		switch(choice){
		case 0:
			moveUp();
			break;
		case 1:
			moveDown();
			break;
		case 2:
			moveLeft();
			break;
		case 3:
			moveRight();
			break;
		}

		for(int i=0;i<vamp.size();i++){
			if(vamp.get(i).getId() != id && checkCollide(vamp.get(i))){
				moveX=0;
				moveY=0;
				System.out.println("collision entre "+id+ " et "+vamp.get(i).getId());
			}
		}
		System.out.println("sors");
		lock.unlock();

	}

	public void moveUp(){
		moveY -= moveDist;
	}
	public void moveDown(){
		moveY += moveDist;
	}
	public void moveLeft(){
		moveX -= moveDist;
	}
	public void moveRight(){
		moveX += moveDist;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		remove();
		last_x = posX;
		last_y = posY;
		if(moveY<0)
			currentView =0;
		else if(moveY>0)
			currentView=2;
		else if(moveX>0)
			currentView=1;
		else if(moveX<0)
			currentView=3;
		if(life !=0){
			posX +=moveX;
			posY +=moveY;
			if(posX<0)
				posX=0;
			if(posY<0)
				posY=0;
			if(posX>maxX-32)
				posX =maxX-32;
			if(posY>maxY-32)
				posY = maxY-32;
			moveX=0;
			moveY=0;
			
		add();
		}
		
		else{
			/*animation de mort et remove*/
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			private long lastUpdate = 0 ;
			@Override
			public void handle(long now) {
				if (now - lastUpdate >= 650_000_000) {
					move();
					Platform.runLater(new Runnable() {
			            @Override public void run() {
			            	update();
			        		tt.setFromX(last_x);
			        		tt.setFromY(last_y);
			            	tt.setToX(posX);
			    			tt.setToY(posY);
			    			tt.setNode(getCurrentView());
			    			tt.setAutoReverse(false);
			    			tt.setDuration(Duration.millis(550));
			    			tt.setInterpolator(Interpolator.LINEAR);
			    			tt.play();
			            }
			        });
					lastUpdate = now ;
				}
			}
		};
		rectangleAnimation.start();
	
	}

}
