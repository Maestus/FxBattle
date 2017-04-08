import javafx.animation.Interpolator;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Vampire extends Character {
	boolean move_undone;
	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p,id);
		move_undone =false;
		
	}

	
	
	public void makeTranslateTransition(Node node, double toX, double toY) {
        tt.setFromY(posY);
        tt.setToY(toY);
        tt.setFromX(posX);
        tt.setToX(toX);

        tt.setAutoReverse(false);
        tt.setDuration(Duration.millis(600));
        tt.setInterpolator(Interpolator.LINEAR);
        tt.setNode(node);
        tt.setOnFinished(actionEvent -> {
            tl.stop();
            getCurrentView().setViewport(new Rectangle2D(offsetX,getCurrentView().getViewport().getMinY(),width,height));
        });
            tl.play();
        tt.play();

	
	}
	
	void move(){
		pane.getChildren().remove(id);
		for(int i=0;i<4;i++){
			imageView[i].setTranslateX(posX);
			imageView[i].setTranslateY(posY);

		}
		int t;
		move_undone = true;
		while(move_undone){
			t = (int) (Math.random()*4);
			switch(t){
				case 0: 
					moveUp(50);
					break;
				case 1: 
					moveDown(50);
					break;
				case 2: 
					moveLeft(50);
					break;
				case 3: 
					moveRight(50);
					break;
			}
		}
			
	}
	
	void moveUp(int depl){
		if(posY >=depl){
			super.animCurrent = 0;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX,posY-50);
			posY = (posY-50);
			move_undone = false;
		}
	}
	
	void moveLeft(int depl){
		if(posX >=depl){
			super.animCurrent = 3;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX-50, posY);
			posX = (posX-50);
			move_undone = false;
		}
	}
	
	void moveRight(int depl){
		if(posX <= 1024-depl){
			super.animCurrent = 1;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX+50,  posY);
			posX = (posX+50);
			move_undone = false;
		}
	}
	
	void moveDown(int depl){
		if(posY <= 640-depl){
			super.animCurrent = 2;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(),  posX, posY+50);
			posY = (posY+50);
			move_undone = false;
		}
	}


	

}
