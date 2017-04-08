import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends Application{
	private static final int COLUMNS  =     3;
	private static final int COUNT    =     3;
	private static final int OFFSET_Y =     0;
	private static final int WALK_RIGHT_OFFSET_Y =    38;
	private static final int WALK_LEFT_OFFSET_Y =    112;
	private static final int WALK_UP_OFFSET_Y =    5;
	private static final int WALK_DOWN_OFFSET_Y =    76;
	private static final int OFFSET_X =     0;
	private static final int WALK_BASE = 0;
	private static final int WIDTH    =    32;
	private static final int HEIGHT   =    32;
	Vampire [] v = new Vampire[10];
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane pane = new Pane();
		pane.setPrefSize(1024,640);
		Scene scene = new Scene(pane);
		
		for(int i = 0; i < 4; i++){
			v[i] = new Vampire(Duration.millis(400), COLUMNS, OFFSET_X, WALK_BASE,
				 				WIDTH, HEIGHT,1,3,0+(i*15),0+(i*15),pane,i);
		}
		
		for(int i = 0; i < 4; i++){
			System.out.println("here");
			pane.getChildren().add(i,v[i].getCurrentView());
		}
		
		final AnimationTimer rectangleAnimation = new AnimationTimer() {

			private long lastUpdate = 0 ;
            @Override
            public void handle(long now) {
                    if (now - lastUpdate >= 650_000_000) {

                		for(int i = 0; i < 4; i++)
                			v[i].move();
                		
                		
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
