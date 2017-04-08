import javafx.application.Application;
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
	private static final int NUMBER = 2;
	Vampire [] v = new Vampire[NUMBER];
	@Override
	public void start(Stage primaryStage) throws Exception {
		Thread [] vampires = new Thread[NUMBER];
		Object sync = new Object();
		Pane pane = new Pane();
		pane.setPrefSize(1024,640);
		Scene scene = new Scene(pane);
	
		for(int i = 0; i < NUMBER; i++){
			v[i] = new Vampire(assets,Duration.millis(400), COLUMNS, OFFSET_X, WALK_BASE,
				 				WIDTH, HEIGHT,1,3,0,(i*32)*2,pane,i, sync);
		}
		
		for(int i = 0; i < NUMBER; i++){
			pane.getChildren().add(i,v[i].getCurrentView());
		}
		
		for(int i = 0; i < NUMBER; i++){
			vampires[i] = new Thread(v[i]);
			vampires[i].start();
		}
		
		
		for(int i = 0; i < NUMBER; i++){
			vampires[i].join();
		}
		
		
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	public static void main(String[]args){
		launch(args);
	}

}
