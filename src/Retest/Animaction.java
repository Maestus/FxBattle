package Retest;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration; 

public class Animaction extends Transition{
	protected Sprite sprite;
	protected int duration;
	protected int x_dep, x_arr, y_dep, y_arr; 
	TranslateTransition translatetransition = new TranslateTransition();
    Timeline tl = new Timeline();
	
  
 
	public Animaction(Sprite _sprite, int _duration) {
		this.sprite = _sprite;
	 	this.duration = _duration;
	 	setCycleDuration(Duration.millis(this.duration));
	 	setAutoReverse(false);
	 	
	 	tl.setCycleCount(Animation.INDEFINITE);
	 	tl.getKeyFrames().add(new KeyFrame(Duration.millis(200), event -> {
	             sprite.getCurrentView().setViewport(new Rectangle2D(sprite.getCurrentView().getViewport().getMinX()+32,sprite.getCurrentView().getViewport().getMinY(),sprite.getCurrentView().getFitWidth(),sprite.getCurrentView().getFitHeight()));
	         
	 }	));
	 	
	}
	
	protected void interpolate(double k) { 
		translatetransition.setFromY(sprite.posY);
		translatetransition.setToY(sprite.moveY);
		translatetransition.setFromX(sprite.posX);
		translatetransition.setToX(sprite.moveX);

		translatetransition.setAutoReverse(false);
		translatetransition.setDuration(Duration.millis(duration));
		translatetransition.setInterpolator(Interpolator.LINEAR);
		translatetransition.setNode(sprite.getCurrentView());
		translatetransition.setOnFinished(actionEvent -> {
			tl.stop();
			sprite.getCurrentView().setViewport(new Rectangle2D(0,sprite.getCurrentView().getViewport().getMinY(),sprite.getCurrentView().getFitWidth(),sprite.getCurrentView().getFitHeight()));
		});
		if (tl.getStatus() != Animation.Status.RUNNING) {
			tl.play();
		}
		translatetransition.play();
	}  
}
