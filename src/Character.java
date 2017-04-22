import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Character extends Sprite{
	final Lock lock = new ReentrantLock();
	final Condition cant_move  = lock.newCondition(); 
	boolean [] direction_tried = new boolean[4];
	protected int life;
	protected final Image character = new Image("file:Data/pixelart.png");
	Timeline tl;
    TranslateTransition tt = new TranslateTransition();
	public Character(Game_assets _assets, int id, Duration duration,  int columns, int offsetX, int offsetY, int _pos_x, int _pos_y,
			int width, int height, int dir,int life,Pane p) {
		
		super(_assets, id, duration, columns, offsetX, offsetY, _pos_x, _pos_y, width, height, dir,p);
		this.life = life;
		imageView = new ImageView[4];
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+i*(height+4),width,height));
		}
		tl = new Timeline();
		tl.setCycleCount(Animation.INDEFINITE);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(200), event -> {
                getCurrentView().setViewport(new Rectangle2D(getCurrentView().getViewport().getMinX()+32,getCurrentView().getViewport().getMinY(),width,height));
            
}	));
	}
	
	public Character(int id,  int columns, int offsetX, int offsetY, int _pos_x, int _pos_y, int width, int height) {
		
		super(id, columns, offsetX, offsetY, _pos_x, _pos_y, width, height);
	}
	 	
	public int getLife(){
		return life;
	}
	
	public boolean check_collide_move(int id, int add_x, int add_y){
		Sprite self = assets.elements.get(id);
		int x = self.pos_x + add_x;
		int y = self.pos_y + add_y;
		Set<Integer> cles = assets.elements.keySet();
		Iterator<Integer> it = cles.iterator();
		while (it.hasNext()){
		   Integer cle = it.next();
		   if(cle != this.id){
			   Sprite valeur = assets.elements.get(cle);
			   //System.out.println("ID : " + valeur.id + " X : " + valeur.pos_x + " Y : " + valeur.pos_y);
			   if(valeur.collide(new Rectangle2D(x, y, width, height)))
				   return true;
		   }
		}
		return false;
	}
	
	public boolean all_direction_tried(){
		for(int i =0; i < 4; i++)
			if(direction_tried[i] == false)
				return false;
		return true;
	}
	
}