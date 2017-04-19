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
	DoubleProperty rate = new SimpleDoubleProperty();
	boolean move_undone;
	Timeline tl;
    TranslateTransition tt = new TranslateTransition();

	public Vampire( Game_assets _assets, Duration duration, int columns, int offsetX, int offsetY, int width, int height, int dir, int life,int pos_x,int pos_y,Pane p,int id, Object sync) {
		super(_assets, id, duration, columns, offsetX, offsetY, pos_x, pos_y, width, height, dir, life,p);
		
		synchron = sync;
		assets.elements.put(id, this);
		imageView = new ImageView[4];
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
	
	void move() throws InterruptedException{
		synchronized (synchron) {
			System.out.println(id + " je bouge.");
			pane.getChildren().remove(id);
			for(int i=0;i<4;i++){
				imageView[i].setTranslateX(pos_x);
				imageView[i].setTranslateY(pos_y);

			}
			int t;
			move_undone = true;
			while(move_undone){
				if(!all_direction_tried()){
					t = (int) (Math.random()*4);
					switch(t){
						case 0: 
							if(!direction_tried[0]){
								direction_tried[0] = true;
								moveUp();
								break;
							}
						case 1: 
							if(!direction_tried[1]){
								direction_tried[1] = true;
								moveDown();
								break;
							}
						case 2: 
							if(!direction_tried[2]){
								direction_tried[2] = true;
								moveLeft();
								break;
							}
						case 3: 
							if(!direction_tried[3]){
								direction_tried[3] = true;
								moveRight();
								break;
							}
					}
				} else {
					System.out.println("Attente");
					cant_move.await();
				}
			}
			direction_tried[0] = false;
			direction_tried[1] = false;
			direction_tried[2] = false;
			direction_tried[3] = false;
			cant_move.signal();
		}
		
	}
	
	void moveUp(){

		if(pos_y >=50){
			if(!check_collide_move(id, 0, -50)){
				super.animCurrent = 0;
				pane.getChildren().add(id,getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x, pos_y, pos_y-50);
				pos_y = (pos_y-50);
				move_undone = false;
			} else {
				System.out.println("Laisse beton");
			}
		}
	}
	
	void moveLeft(){
		
		if(pos_x >=50){
			if(!check_collide_move(id, -50, 0)){
				super.animCurrent = 3;
				pane.getChildren().add(id,getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x-50, pos_y, pos_y);
				pos_x = (pos_x-50);
				move_undone = false;
			} else {
				System.out.println("Laisse beton");
			}
		}
	}
	
	void moveRight(){
	
		if(pos_x <= 317){
			if(!check_collide_move(id, 50, 0)){
				super.animCurrent = 1;
				pane.getChildren().add(id,getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x+50, pos_y, pos_y);
				pos_x = (pos_x+50);
				move_undone = false;
			} else {
				System.out.println("Laisse beton");
			}
		}
	}
	
	void moveDown(){
		
		if(pos_y <= 317){
			if(!check_collide_move(id, 0, 50)){
				super.animCurrent = 2;
				pane.getChildren().add(id,getCurrentView());
				makeTranslateTransition(getCurrentView(), pos_x, pos_x, pos_y, pos_y+50);
				pos_y = (pos_y+50);
				move_undone = false;
			} else {
				System.out.println("Laisse beton");
			}
		}
	}


	@Override
	public void run() {
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                    if (now - lastUpdate >= 250_000_000) {
                    	try{
                    		move();
                    	} catch(Exception e) {
                    		e.getMessage();
                    	}
                		lastUpdate = now ;
                    }
            }

		};
		rectangleAnimation.start();
		
	}

	

}
