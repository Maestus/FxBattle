import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends Application{
	Game_assets assets = new Game_assets();
	private static final int COLUMNS  =     3;
	private static final int OFFSET_X =     0;
	private static final int WALK_BASE = 0;
	private static final int WIDTH    =    32;
	private static final int HEIGHT   =    32;
	private static final int NUMBER = 4;
	Vampire [] v = new Vampire[NUMBER];
	@Override
	public void start(Stage primaryStage) throws Exception {
		Thread [] vampires = new Thread[NUMBER];
		Object sync = new Object();
		Pane pane = new Pane();
		pane.setPrefSize(400,400);
		Scene scene = new Scene(pane);
		Thread [] t = new Thread[NUMBER];
		ReentrantLock lock = new ReentrantLock();
		Condition cond = lock.newCondition();
		

		Runnable [] task =new Runnable[NUMBER];
		for(int i=0;i<NUMBER;i++){
			Vampire vamp = new Vampire(assets,Duration.millis(400), COLUMNS, OFFSET_X, WALK_BASE,
					WIDTH, HEIGHT,1,3,0,(i*32)*2,pane,i, sync,lock,cond);
			pane.getChildren().add(vamp.getCurrentView());
			task[i] =  () -> { 
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(() -> { 
					vamp.move();
				}); 
			};
			v[i] = vamp;
		}
		for(int i=0;i<NUMBER;i++){
			new Thread(task[i]).start();
		}
		/*for(int i = 0; i < NUMBER; i++){
			vampires[i] = new Thread(v[i]);
			vampires[i].start();
			System.out.println("i : "+i);
		}
		for(int i=0;i<NUMBER;i++){
			vampires[i].join();
			System.out.println("i 2"+i);
		}
		 */



		primaryStage.setScene(scene);
		primaryStage.show();


	}
	public static void main(String[]args){
		launch(args);
	}

}