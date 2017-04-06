import java.util.ArrayList;

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
	private static final int COUNT    =     3;
	private static final int OFFSET_X =     94;
	private static final int WALK_RIGHT_OFFSET_Y =    38;
	private static final int WALK_LEFT_OFFSET_Y =    112;
	private static final int WALK_UP_OFFSET_Y =    5;
	private static final int WALK_DOWN_OFFSET_Y =    76;
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
				OFFSET_X, WALK_RIGHT_OFFSET_Y,
				WIDTH, HEIGHT,2,3,0,0,pane,0);
		pane.getChildren().addAll(v.getCurrentView());
		final LongProperty lastUpdateTime = new SimpleLongProperty();

		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub

				if (lastUpdateTime.get() > 0) {
					final double elapsedSeconds = (now - lastUpdateTime.get()) / 1_000_000_000.0 ;
					v.move();
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
