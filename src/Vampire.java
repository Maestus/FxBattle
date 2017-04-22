import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character implements Cloneable, Runnable{
	Object synchron;
	ReentrantLock lock ;
	Condition cond ;
	DoubleProperty rate = new SimpleDoubleProperty();
	boolean move_undone;

	public Vampire( Game_assets _assets, Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int pos_x,int pos_y,Pane p,int id, Object sync,ReentrantLock l,Condition c) {
		super(_assets, id, duration, columns, offsetX, offsetY, pos_x, pos_y, width, height, dir, life,p);

		synchron = sync;
		assets.elements.put(id, this);
		this.pane = p;
		lock = l;
		cond =c;
	}


	public Vampire(int columns, int offsetX, int offsetY, int width, int height,int pos_x,int pos_y,int id) {
		super(id, columns, offsetX, offsetY, pos_x, pos_y, width, height);
	}


	@Override
	public Sprite clone(){
		return new Vampire(columns, offsetX, offsetY, pos_x, pos_y, width, height, id);
	}

	public Transition makeTranslateTransition(Node node, 
			double fromX, double toX, double fromY, double toY) {
		tt.setFromY(fromY);
		tt.setToY(toY);
		tt.setFromX(fromX);
		tt.setToX(toX);

		tt.setAutoReverse(false);
		tt.setDuration(Duration.millis(245));
		tt.setInterpolator(Interpolator.LINEAR);
		tt.setNode(node);
		tt.setOnFinished(actionEvent -> {
			tl.stop();
			getCurrentView().setViewport(new Rectangle2D(0,getCurrentView().getViewport().getMinY(),width,height));
		});
		if (tl.getStatus() != Animation.Status.RUNNING) {
			tl.play();
		}

		// Start the translation animation.
		tt.play();
		return tt;
	}

	void move() {
		while(true){
		System.out.println("before move+"+id);
		lock.lock();
		pane.getChildren().remove(getCurrentView());
		for(int i=0;i<4;i++){
			imageView[i].setTranslateX(pos_x);
			imageView[i].setTranslateY(pos_y);

		}
		int t;
		t = (int) (Math.random()*4);
		switch(t){
		case 0: 
				moveUp();
				break;
		case 1: 
				moveDown();
				break;
		case 2: 
				direction_tried[2] = true;
				moveLeft();
				break;
		case 3: 
				moveRight();
				break;
		}
		lock.unlock();

		}

	}

	void moveUp(){

		if(pos_y >=50){
			if(!check_collide_move(id, 0, -50)){
				super.animCurrent = 0;
				pane.getChildren().add(getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x, pos_y, pos_y-50);
				pos_y = (pos_y-50);
				move_undone = false;
			} else {
				pane.getChildren().add(getCurrentView());

				System.out.println("Laisse beton up");
			}
		}
	}

	void moveLeft(){

		if(pos_x >=50){
			if(!check_collide_move(id, -50, 0)){
				super.animCurrent = 3;
				pane.getChildren().add(getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x-50, pos_y, pos_y);
				pos_x = (pos_x-50);
				move_undone = false;
			} else {
				pane.getChildren().add(getCurrentView());

				System.out.println("Laisse beton left");
			}
		}
	}

	void moveRight(){

		if(pos_x <= 317){
			if(!check_collide_move(id, 50, 0)){
				super.animCurrent = 1;
				pane.getChildren().add(getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x+50, pos_y, pos_y);
				pos_x = (pos_x+50);
				move_undone = false;
			} else {
				pane.getChildren().add(getCurrentView());

				System.out.println("Laisse beton");
			}
		}
	}

	void moveDown(){

		if(pos_y <= 317){
			if(!check_collide_move(id, 0, 50)){
				super.animCurrent = 2;
				pane.getChildren().add(getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x, pos_y, pos_y+50);
				pos_y = (pos_y+50);
				move_undone = false;
			} else {
				pane.getChildren().add(getCurrentView());

				System.out.println("Laisse beton");
			}
		}
	}


	@Override
	public void run() {
		boolean running = true;
		System.out.println("run begin"+id);
		if( running){
			final AnimationTimer rectangleAnimation = new AnimationTimer() {

				private long lastUpdate = 0 ;
				@Override
				public void handle(long now) {
					if (now - lastUpdate >= 250_000_000) {
						System.out.println("id in handle"+id);
						move();
						lastUpdate = now ;
					}
				}
			};
			try{
				rectangleAnimation.start();
			}
			catch(Exception e ){
				System.out.println(e.getMessage());
			}
		}

	}






}