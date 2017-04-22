package Retest;

import javafx.scene.layout.Pane;

public class Vampire extends Character{

	public Vampire(int x, int y, int mx, int my, int md, int maX, int maY, int oX, int oY, String img, Pane pane,
			int life) {
		super(x, y, mx, my, md, maX, maY, oX, oY, img, pane, life);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
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
		moveX -= moveDist;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

		if(life !=0){
			posX +=moveX;
			posY +=moveY;
			if(posX<0)
				posX=0;
			if(posY<0)
				posY=0;
			if(posX>maxX)
				posX =maxX;
			if(posY>maxY)
				posY = maxY;
			p.getChildren().add(getCurrentView());
			getCurrentView().setTranslateX(posX);
			getCurrentView().setTranslateY(posY);
		}
		else{
			/*animation de mort et remove*/
		}
	}

}
