

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character {
	int id;
	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width,
			int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p);
		imageView = new ImageView[3];
		this.id = id;
		for(int i=0;i<3;i++){
			imageView[i]  = new ImageView(super.character);
			imageView[i].setViewport(new Rectangle2D(offsetX+i*width,offsetY,width,height));
		}
	}

	void move(){
		pane.getChildren().removeAll();
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
		pane.getChildren().addAll(getCurrentView());
		this.play();
		getCurrentView().setTranslateX(posX);
		getCurrentView().setTranslateY(posY);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void moveUp(){
		super.animCurrent = 0;

		if(posY >=50){
			posY = posY-50;
		}
	}
	void moveLeft(){
		super.animCurrent = 3;

		if(posX >=50){
			posX = posX-50;

		}
		

	}
	void moveRight(){
		super.animCurrent = 1;

		if(posX <= 1074){
			posX =posX +50;
			System.out.println("on à bouger");
		}

	}
	void moveDown(){
		super.animCurrent = 2;

		if(posY <= 590){
			posY = posY+50;

		}

	}

}
