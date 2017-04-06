import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Sprite extends Transition{
	private final ImageView [] imageView;//tableau de 4 des différentes directions (Haut bas gauche droite)
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;//largeur d'une image
    private final int height;//hauteur d'une image
    private int lastIndex;
    private int animCurrent;//direction du personnage

    public Sprite(
            ImageView [] imageView,
            Duration duration,
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height,int dir) {
        this.imageView = imageView;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.animCurrent = dir;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * columns), columns - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView[animCurrent].setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
	

}
