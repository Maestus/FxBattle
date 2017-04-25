package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameLoop extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setPrefSize(400,400);
		Scene scene = new Scene(pane);
        final double rectangleSpeed = 50 ;

		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		ReentrantLock lock = new ReentrantLock();

		Pistoleros pist = new Pistoleros(30,50,10,400,400,96,0,"file:/Data/pixelart.png",pane,100,vamp,lock,rectangleSpeed);
		for(int i=0;i<4;i++){
			vamp.add(new Vampire(i*36,i*36,40,400,400,0,0,"file:/Data/pixelart.png",pane,100,i,vamp,lock));
			vamp.get(i).add();
			vamp.get(i).update();

		}
		final AnimationTimer animaction = new AnimationTimer() {

			private long lastUpdate = 0 ;
			@Override
			public void handle(long now) {
				if (now - lastUpdate >= 550_000_000) {
					for(int i=0;i<vamp.size();i++){
					Vampire sub =vamp.get(i);
	            	sub.move();

					sub.calme();
					}
					lastUpdate = now ;
				}
			}
		};
		pist.add();
		 final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
	        final LongProperty lastUpdateTime = new SimpleLongProperty();
	        final AnimationTimer rectangleAnimation = new AnimationTimer() {
	            public void handle(long timestamp) {
	                if (lastUpdateTime.get() > 0) {
	                    final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0 ;
	                    final double delta = elapsedSeconds * rectangleVelocity.get();
	     
	                    pist.calme();
	                }
	                lastUpdateTime.set(timestamp);
	            }
	        };
	        rectangleAnimation.start();
		animaction.start();
		scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.RIGHT) {
			 pist.moveRight();
			 
            }
            else if (keyEvent.getCode() == KeyCode.LEFT) {
			 pist.moveLeft();

            }            
            else if (keyEvent.getCode() == KeyCode.UP) {
   			 pist.moveUp();

            }
            else if (keyEvent.getCode() == KeyCode.DOWN) {
      			pist.moveDown();

            }
            else if(keyEvent.getCode()==KeyCode.SPACE){
            	pist.fire();
            }
            for(int i=0;i<4;i++){
    			pist.imageView[i].setViewport(new Rectangle2D(pist.offsetX+pist.pos*32,36*i,32,32));
    		}
            rectangleVelocity.set(rectangleSpeed);
           
		 } );
		scene.setOnKeyReleased(keyEvent ->{
			pist.remove();
		});
		
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	 	
		public static void main(String[]args){
			launch(args);
		}

	}
