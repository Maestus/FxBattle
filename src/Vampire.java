

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character {
	int id;
	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width,
			int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p);
		imageView = new ImageView[4];
		this.id = id;
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(super.character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+i*(height+3),width,height));
		}
	}

	void move(){
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

		getCurrentView().setTranslateX(posX);
		getCurrentView().setTranslateY(posY);
		
		
	}
	
	void moveUp(){
		super.animCurrent = 0;
		if(posY >=50){
			posY = posY-50;
		}
		this.play();
	}
	void moveLeft(){
		super.animCurrent = 3;

		if(posX >=50){
			posX = posX-50;

		}
		this.play();


	}
	void moveRight(){
		super.animCurrent = 1;

		if(posX <= 1074){
			posX =posX +50;
		}

	}
	void moveDown(){
		super.animCurrent = 2;

		if(posY <= 590){
			posY = posY+50;

		}

	}

}
