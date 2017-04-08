import javafx.animation.Animation;
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

public class Vampire extends Character {
	int id;
	DoubleProperty rate = new SimpleDoubleProperty();
	boolean move_undone;
	Timeline tl;
    TranslateTransition tt = new TranslateTransition();

	public Vampire(Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int posX,int posY,Pane p,int id) {
		super(duration, columns, offsetX, offsetY, width, height, dir, life,posX,posY,p);
		imageView = new ImageView[4];
		this.id = id;
		this.pane = p;
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(super.character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+i*(height+4),width,height));
		}
		tl = new Timeline();
		tl.setCycleCount(Animation.INDEFINITE);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(200), event -> {
                getCurrentView().setViewport(new Rectangle2D(getCurrentView().getViewport().getMinX()+32,getCurrentView().getViewport().getMinY(),width,height));
            
}	));
	}

	
	public Transition makeTranslateTransition(Node node, 
	            double fromX, double toX, double fromY, double toY) {
	        tt.setFromY(fromY);
	        tt.setToY(toY);
	        tt.setFromX(fromX);
	        tt.setToX(toX);

	        tt.setAutoReverse(false);
	        tt.setDuration(Duration.millis(600));
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
			
	}
	
	void moveUp(){
		

		if(posY >=50){
			super.animCurrent = 0;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX, posX, posY, posY-50);
			posY = (posY-50);
			move_undone = false;
		}
	}
	
	void moveLeft(){
		
		if(posX >=50){
			super.animCurrent = 3;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX, posX-50, posY, posY);
			posX = (posX-50);
			move_undone = false;
		}
	}
	
	void moveRight(){
		
		

		if(posX <= 974){
			super.animCurrent = 1;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX, posX+50, posY, posY);
			posX = (posX+50);
			move_undone = false;
		}
	}
	
	void moveDown(){
		

		if(posY <= 590){
			super.animCurrent = 2;
			pane.getChildren().add(id,getCurrentView());
			makeTranslateTransition(getCurrentView(), posX, posX, posY, posY+50);
			posY = (posY+50);
			move_undone = false;
		}
	}


	

}
