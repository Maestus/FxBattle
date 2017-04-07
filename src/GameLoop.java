import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends Application{
	private static final int COLUMNS  =     3;
	private static final int OFFSET_Y =     0;
	private static final int OFFSET_X =     0;
	private static final int WIDTH    =    32;
	private static final int HEIGHT   =    32;
	Vampire v;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setPrefSize(1024,640);
		Scene scene = new Scene(pane);

		
		v = new Vampire(Duration.millis(500), COLUMNS, OFFSET_X, OFFSET_Y, WIDTH, HEIGHT, 1, 3, 0, 0, pane, 0);
		pane.getChildren().addAll(v.getCurrentView());


		
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                    if (now - lastUpdate >= 1000_000_000) {
                        v.move();
                        lastUpdate = now ;
                    }
            }

		};
		rectangleAnimation.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	public static void main(String[]args){
		launch(args);
	}

}
