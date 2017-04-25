package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Pistoleros extends Character{
	int pos;
	TranslateTransition tt = new TranslateTransition();
	double rectangleSpeed;
	public Pistoleros(int x, int y, int md, int maX, int maY, int oX, int oY, String img, Pane pane, int life,ArrayList<Vampire> v,ReentrantLock l,double rectangleSpeed) {
		super(x, y, md, maX, maY, oX, oY, img, pane, life, v,l);
		this.rectangleSpeed = rectangleSpeed;
		pos =0;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
		/*tt.setFromX(posX);
		tt.setFromY(posY);*/
		/*if(moveY<0)
			currentView =0;
		else if(moveY>0)
			currentView=2;
		else if(moveX>0)
			currentView=1;
		else if(moveX<0)
			currentView=3;*/
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
			getCurrentView().setTranslateX(posX);
			getCurrentView().setTranslateY(posY);
			pos = (pos+1)%3;
			
			
		}
		else{
			/*animation de mort et remove*/
		}
	}
	public void fire(){
		final Shape bullet = new Circle(2, Color.BLACK);
	    p.getChildren().add(bullet);
	    final TranslateTransition bulletAnimation = new TranslateTransition( );
	    bulletAnimation.setNode(bullet);
	    bulletAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p.getChildren().remove(bullet);
            }
        });
	    if(currentView==0){
	    	bulletAnimation.setFromX((2*posX+32)/2);
		    bulletAnimation.setFromY(posY);
	    	bulletAnimation.setToX((2*posX+32)/2);
	    	bulletAnimation.setToY(0);
	    }
	    else if(currentView==1){
	    	bulletAnimation.setFromX(posX+32);
		    bulletAnimation.setFromY((2*posY+32)/2);
	    	bulletAnimation.setToX(maxX);
	    	bulletAnimation.setToY((2*posY+32)/2);
	    }
	    else if(currentView==2){
	    	bulletAnimation.setFromX((2*posX+32)/2);
		    bulletAnimation.setFromY(posY+32);
	    	bulletAnimation.setToX((2*posX+32)/2);
	    	bulletAnimation.setToY(maxY);
	    }
	    else if(currentView==3){
	    	bulletAnimation.setFromX(posX);
		    bulletAnimation.setFromY((2*posY+32)/2);
	    	bulletAnimation.setToX(0);
	    	bulletAnimation.setToY((2*posY+32)/2);
	    }
	    bullet.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable,
                    Bounds oldValue, Bounds newValue) {
            	lock.lock();
                for (int i=0;i<vamp.size();i++) {
                    if (oldValue.intersects(vamp.get(i).posX,vamp.get(i).posY,32,32)){
                    	System.out.println("in that shit");
                    	vamp.get(i).remove();
                    	vamp.remove(i);
                        bulletAnimation.stop();
                        p.getChildren().remove(bullet);
                        break;
                        
                    }
                }
                lock.unlock();
            }
        });
	    bulletAnimation.play();

	    /*
	    for(int i=0;i<vamp.size();i++){
	    	if(currentView==0 && bulletAnimation.getToX()>=vamp.get(i).posX && bulletAnimation.getToX()<=vamp.get(i).posX+32){
	    		bulletAnimation.setToY(vamp.get(i).posY+32);
	    		
	    	    bulletAnimation.play();
	    	}
	    	else if(i==vamp.size()-1)
	    	    bulletAnimation.play();
	    }*/
	}
	public void calme(){
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	update();

            }
        });
	}

}
