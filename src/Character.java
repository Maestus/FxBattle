
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Character extends Sprite{
	protected int life;
	protected double posX,posY;
	protected final Image character = new Image("file:Data/pixelart.png");
	public Character(Duration duration,  int columns, int offsetX, int offsetY,
			int width, int height, int dir,int life,int posX,int posY,Pane p) {
		
		super(duration, columns, offsetX, offsetY, width, height, dir,p);
		this.life = life;
		this.posX =posX;
		this.posY = posY;
	}
	
	public int getLife(){
		return life;
	}
	
	public boolean getCol(Character c){
		if(c.posX==this.posX && c.posY==this.posY)
			return true;
		else 
			return false;
	}
}
