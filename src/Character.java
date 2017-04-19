
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Character extends Sprite{
	final Lock lock = new ReentrantLock();
	final Condition cant_move  = lock.newCondition(); 
	boolean [] direction_tried = new boolean[4];
	protected int life;
	protected final Image character = new Image("file:Data/pixelart.png");
	public Character(Game_assets _assets, int id, Duration duration,  int columns, int offsetX, int offsetY, int _pos_x, int _pos_y,
			int width, int height, int dir,int life,Pane p) {
		
		super(_assets, id, duration, columns, offsetX, offsetY, _pos_x, _pos_y, width, height, dir,p);
		this.life = life;
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
			   System.out.println("ID : " + valeur.id + " X : " + valeur.pos_x + " Y : " + valeur.pos_y);
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
