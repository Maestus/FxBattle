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
	
	
  
 
	public Animaction(Sprite _sprite, int _duration) {
		this.sprite = _sprite;
	 	this.duration = _duration;
	 	setCycleDuration(Duration.millis(this.duration));
	 	
	 	
	 	
	}
	
	protected void interpolate(double k) { 
		
	}  
}
