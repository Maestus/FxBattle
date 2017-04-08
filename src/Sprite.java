import javafx.animation.*;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class Sprite {
	protected ImageView [] imageView;//tableau de 4 des différentes directions (Haut bas gauche droite)
   protected final int columns;
    protected  int offsetX;
    protected int offsetY;
    protected int width;//largeur d'une image
    protected int height;//hauteur d'une image
    private int lastIndex;
    protected int animCurrent;//direction du personnage
    protected Pane pane;
    public Sprite(
            Duration duration,
              int columns,
            int offsetX, int offsetY,
            int width,   int height,int dir,Pane p) {
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.animCurrent = dir;
        this.pane = p;
      
    }
    

	public ImageView getCurrentView(){
		return imageView[animCurrent];
	}
    
    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * columns), columns - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            getCurrentView().setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
	public abstract boolean getCol(Character c);
		

}