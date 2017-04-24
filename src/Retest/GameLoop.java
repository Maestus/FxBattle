package Retest;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameLoop extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setPrefSize(400,400);
		Scene scene = new Scene(pane);

		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		ReentrantLock lock = new ReentrantLock();
		for(int i=0;i<4;i++){
			vamp.add(new Vampire(i*36,i*36,10*(i+1),400,400,0,0,"file:/Data/pixelart.png",pane,100,i,vamp,lock));
			vamp.get(i).add();
			vamp.get(i).update();

		}
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			private long lastUpdate = 0 ;
			@Override
			public void handle(long now) {
				if (now - lastUpdate >= 650_000_000) {
					for(int i=0;i<vamp.size();i++){

					vamp.get(i).update();
					}
					lastUpdate = now ;
				}
			}
		};rectangleAnimation.start();
		Thread [] t = new Thread[vamp.size()];
		for(int i=0;i<vamp.size();i++){
			t[i] = new Thread(vamp.get(i));
			t[i].start();
		}
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[]args){
		launch(args);
	}

}
