

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character {
	int id;
	DoubleProperty rate = new SimpleDoubleProperty();
	TranslateTransition t;

	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p);
		imageView = new ImageView[4];
		this.id = id;

		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(super.character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+i*(height+3),width,height));
		}
		t = new TranslateTransition();
		makeTranslateTransition(t,posX, posX, posY, posY);
	}


	public  void makeTranslateTransition( TranslateTransition tt,
			double fromX, double toX, double fromY, double toY) {
		
		tt.setFromY(fromY);
		tt.setToY(toY);
		tt.setFromX(fromX);
		tt.setToX(toX);

		tt.setAutoReverse(false);
		tt.setDuration(Duration.millis(1000));
		tt.setInterpolator(Interpolator.LINEAR);
		tt.setNode(imageView[animCurrent]);
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
		

	}

	void moveUp(){
	
		if(posY >=50){
			if(super.animCurrent !=0){
				super.animCurrent = 0;
				
			}
			pane.getChildren().addAll(getCurrentView());
			makeTranslateTransition(t,posX, posX, posY, posY-50);
			System.out.println("Haut "+posX+ " "+posY);

			posY = (posY-50);
			t.play();
		}
		else{
			pane.getChildren().addAll(getCurrentView());

		}
	}

	void moveLeft(){
	
		if(posX >=50){
			if(super.animCurrent !=3){
				super.animCurrent = 3;
				
			}
			pane.getChildren().addAll(getCurrentView());
			makeTranslateTransition(t,posX, posX-50, posY, posY);
			System.out.println("gauche "+posX+ " "+posY);

			posX = (posX-50);
			t.play();

		}
		else{
			pane.getChildren().addAll(getCurrentView());

		}

	}

	void moveRight(){
		
		if(posX <= 5074){
			if(super.animCurrent !=1){
				super.animCurrent = 1;
				
			}
			pane.getChildren().addAll(getCurrentView());
			makeTranslateTransition(t,posX, posX+50, posY, posY);
			System.out.println("droite" +posX+ " "+posY);

			posX = (posX +50);
			t.play();
		}
		else{
			pane.getChildren().addAll(getCurrentView());

		}
	}

	void moveDown(){
		
		if(posY <= 590){
			if(animCurrent != 2){
				super.animCurrent = 2;
				
			}
			pane.getChildren().addAll(getCurrentView());
			makeTranslateTransition(t,posX, posX, posY, posY+50);
			System.out.println("bas" +posX+ " "+posY);

			posY = (posY+50);
			t.play();

		}
		else{
			pane.getChildren().addAll(getCurrentView());

		}
	}

}
