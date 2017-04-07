import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends Application{
	private static final int COLUMNS  =     3;
	    private static final int OFFSET_X =     0;
	    private static final int WALK_BASE = 0;
	    private static final int WIDTH    =    32;
	    private static final int HEIGHT   =    32;
	Vampire v;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setPrefSize(1024,640);
		Scene scene = new Scene(pane);
		v = new Vampire(Duration.millis(500),COLUMNS,
				OFFSET_X, WALK_BASE,
				WIDTH, HEIGHT,0,3,0,0,pane,0);
		pane.getChildren().add(0,v.getCurrentView());
		final LongProperty lastUpdateTime = new SimpleLongProperty();
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub

				if (lastUpdateTime.get() > 0) {
					pane.getChildren().remove(0);
					v.move();
					v.play();
					pane.getChildren().add(0,v.getCurrentView());
					
				}
                lastUpdateTime.set(now);
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
