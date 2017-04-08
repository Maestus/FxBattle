import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Sprite {
	Game_assets assets;
	int id;
	protected ImageView [] imageView;//tableau de 4 des différentes directions (Haut bas gauche droite)
   protected final int columns;
    protected  int offsetX;
    protected int offsetY;
    protected int pos_x, pos_y;
    protected int width;//largeur d'une image
    protected int height;//hauteur d'une image
    protected int animCurrent;//direction du personnage
    protected Pane pane;
   
    public Sprite( Game_assets _assets, int id,
            Duration duration,
              int columns,
            int offsetX, int offsetY, int _pos_x, int _pos_y,
            int width,   int height,int dir,Pane p) {
    	this.id = id;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.animCurrent = dir;
        this.pane = p;
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;   
        assets = _assets;
    }
    
    public Sprite(int id,
              int columns,
            int offsetX, int offsetY, int _pos_x, int _pos_y,
            int width,   int height) {
    	this.id = id;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;  
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;   
    }
        

	public ImageView getCurrentView(){
		return imageView[animCurrent];
	}
    

	public Rectangle2D getBounds(){
		return new Rectangle2D(pos_x, pos_y, width, height);
	}
	
	public boolean collide(Rectangle2D _s){
		return _s.intersects(this.getBounds());
	}
	
	
	
}