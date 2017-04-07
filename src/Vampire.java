

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character {
	int id;
    DoubleProperty rate = new SimpleDoubleProperty();

	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p);
		imageView = new ImageView[4];
		this.id = id;
		
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(super.character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+(i*height),width,height));
			System.out.println(offsetY+(i*height));
		}
	}

	
	public static Transition makeTranslateTransition(Node node, 
	            double fromX, double toX, double fromY, double toY) {
	        TranslateTransition tt = new TranslateTransition();
	        tt.setFromY(fromY);
	        tt.setToY(toY);
	        tt.setFromX(fromX);
	        tt.setToX(toX);

	        tt.setAutoReverse(false);
	        tt.setDuration(Duration.millis(700));
	        tt.setInterpolator(Interpolator.LINEAR);
	        tt.setNode(node);
	        return tt;
	}
	
	void move(){
		pane.getChildren().removeAll(getCurrentView());
		int t = (int) (Math.random()*4);
		switch(t){
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
		this.play();
		
	}
	
	void moveUp(){
		super.animCurrent = 0;
		pane.getChildren().addAll(getCurrentView());

		if(posY >=50){
			makeTranslateTransition(pane, posX, posX, posY, posY-50).play();
			posY = (posY-50);
		}
	}
	
	void moveLeft(){
		super.animCurrent = 3;
		pane.getChildren().addAll(getCurrentView());

		if(posX >=50){
			makeTranslateTransition(pane, posX, posX-50, posY, posY).play();
			posX = (posX-50);
		}
	}
	
	void moveRight(){
		super.animCurrent = 1;
		pane.getChildren().addAll(getCurrentView());

		if(posX <= 1074){
			makeTranslateTransition(pane, posX, posX+50, posY, posY).play();
			posX = (posX +50);
		}
	}
	
	void moveDown(){
		super.animCurrent = 2;
		pane.getChildren().addAll(getCurrentView());

		if(posY <= 590){
			makeTranslateTransition(pane, posX, posX, posY, posY+50).play();
			posY = (posY+50);
		}
	}

}
