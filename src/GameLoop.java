import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
	boolean droit;

	Vampire [] v = new Vampire[10];
	private boolean gauche;
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
			v[i].run();
		}
		pane.getChildren().add(4,player.getCurrentView());
		final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
		final LongProperty lastUpdateTime = new SimpleLongProperty();
		final AnimationTimer rectangleAnimation = new AnimationTimer() {
			private long lastUpdate = 0 ;

			public void handle(long timestamp) {

				if (timestamp - lastUpdate >= 600_000_000 ) {
					if(droit || gauche){

						if(droit){

							player.tt.setFromY(player.posY);
							player.tt.setToY(player.posY);
							player.tt.setFromX(player.posX);
							player.tt.setToX(player.posX+30);
							player.posX+=30;

						}
						if(gauche){
							player.tt.setFromY(player.posY);
							player.tt.setToY(player.posY);
							player.tt.setFromX(player.posX);
							player.tt.setToX(player.posX-30);
							player.posX-=30;
						}
						player.tt.setDuration(Duration.millis(600));
						player.tt.setInterpolator(Interpolator.LINEAR);
						player.tt.setOnFinished(actionEvent -> {
							player.tl.stop();
							player.getCurrentView().setViewport(new Rectangle2D(player.offsetX,player.getCurrentView().getViewport().getMinY(),player.width,player.height));
						});
						player.tt.setNode(player.getCurrentView());
						player.tl.play();
						player.tt.play();

						System.out.println(player.posX);
						/*for(int i=0;i<4;i++){
							player.imageView[i].setTranslateX(player.posX);
							player.imageView[i].setTranslateX(player.posY);
						}*/	
						lastUpdate = timestamp;

					}
				}
				lastUpdateTime.set(timestamp);
			}
		};
		rectangleAnimation.start();
		scene.setOnKeyPressed(keyEvent -> {
			pane.getChildren().remove(player.getCurrentView());

			if(keyEvent.getCode() == KeyCode.RIGHT ) {
				droit = true;
				player.animCurrent = 1;   
				player.getCurrentView().setTranslateX(player.posX);

			}
			else if(keyEvent.getCode() ==  KeyCode.LEFT){
				gauche =true;
				player.animCurrent = 3;
				player.getCurrentView().setTranslateX(player.posX);
			}
			pane.getChildren().add(player.getCurrentView());


		});
		scene.setOnKeyReleased(keyEvent->{
			if((keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() ==  KeyCode.LEFT || keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP)) {
				player.getCurrentView().setTranslateX(player.posX);

				droit =false;
				gauche =false;
				player.tl.stop();
			}
		});
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	public static void main(String[]args){
		launch(args);
	}

}
