
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Character extends Sprite{
	protected int life;
	protected double posX,posY;
	protected Timeline tl = new Timeline();
	protected int id;
    protected TranslateTransition tt = new TranslateTransition();
	protected final Image character = new Image("file:Data/pixelart.png");
	public Character(Duration duration,  int columns, int offsetX, int offsetY,
			int width, int height, int dir,int life,int posX,int posY,Pane p,int id ) {
		
		super(duration, columns, offsetX, offsetY, width, height, dir,p);
		this.life = life;
		this.posX =posX;
		this.posY = posY;
		this.id= id;
		imageView = new ImageView[4];
		for(int i=0;i<4;i++){
			imageView[i]  = new ImageView(character);
			imageView[i].setViewport(new Rectangle2D(offsetX,offsetY+i*(height+4),width,height));
		}
		tl = new Timeline();
		tl.setCycleCount(1);
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(200), event -> {if(getCurrentView().getViewport().getMinX()!=96)
            getCurrentView().setViewport(new Rectangle2D(getCurrentView().getViewport().getMinX()+32,getCurrentView().getViewport().getMinY(),width,height));
			else
                getCurrentView().setViewport(new Rectangle2D(offsetX,getCurrentView().getViewport().getMinY(),width,height));
            
		}));
	}
	
	public int getLife(){
		return life;
	}
	public int getId(){
		return id;
	}
	public double getX(){
		return posX;
	}
	public double getY(){
		return posY;
	}
	
	abstract void moveUp(int depl);
	abstract void moveDown(int depl);
	abstract void moveLeft(int depl);
	abstract void moveRight(int depl);

	public boolean getCol(Character c){
		if(c.posX==this.posX && c.posY==this.posY)
			return true;
		else 
			return false;
	}
}
