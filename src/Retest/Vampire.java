package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character{
	protected final Image character = new Image("file:/Users/lucaslabadens/Documents/Master 1 informatique/Interface/FxBattle/Data/pixelart.png");
	protected int id;
	TranslateTransition tt = new TranslateTransition();

	public Vampire(int x, int y, int md, int maX, int maY, int oX, int oY, String img, Pane pane,
			int life,int id,ArrayList<Vampire> v,	ReentrantLock l) {
		super(x, y,md, maX, maY, oX, oY, img, pane, life,v,l);
		this.life =life;
		this.id = id;
		currentView=1;
		vamp = v;
		
	}
	public int getId(){
		return id;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		lock.lock();
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
			}
		}
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
		tt.setFromX(posX);
		tt.setFromY(posY);
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
			tt.setToX(posX);
			tt.setDuration(Duration.millis(240));
			tt.setToY(posY);
			tt.setNode(getCurrentView());
			tt.play();
		}
		else{
			/*animation de mort et remove*/
  		}
	}
	public void calme(){
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	update();

            }
        });
	}


}
