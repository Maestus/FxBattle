import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends Application{
	private static final int COLUMNS  =     3;
	private static final int OFFSET_X =     0;
	private static final int OFFSET_X_PLAYER = 96;
	private static final int OFFSET_Y_PLAYER = 0;
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
		Pistoleros player = new Pistoleros(Duration.millis(400),COLUMNS,OFFSET_X_PLAYER,OFFSET_Y_PLAYER,WIDTH,HEIGHT,1,3,0,0,pane,4);
		for(int i = 0; i < 4; i++){
			v[i] = new Vampire(Duration.millis(400), COLUMNS, OFFSET_X, WALK_BASE,
				 				WIDTH, HEIGHT,1,3,0+(i*15),0+(i*15),pane,i);
		}
		
		for(int i = 0; i < 4; i++){
			pane.getChildren().add(i,v[i].getCurrentView());
		}
		pane.getChildren().add(4,player.getCurrentView());

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
		scene.setOnKeyPressed(keyEvent -> {

            if((keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() ==  KeyCode.LEFT || keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP)) {

                pane.getChildren().remove(player.getId());
                player.moveRight(10);
            }
        });
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	public static void main(String[]args){
		launch(args);
	}

}
