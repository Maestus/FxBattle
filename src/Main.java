

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static Image personnage;
    private static Animation avancer_droite, avancer_gauche, avancer_bas, avancer_haut;
    private static boolean droite, gauche, bas, haut;

    private static final int COLUMNS  =     3;
    private static final int COUNT    =     3;
    private static final int OFFSET_X =     94;
    private static final int WALK_RIGHT_OFFSET_Y =    38;
    private static final int WALK_LEFT_OFFSET_Y =    112;
    private static final int WALK_UP_OFFSET_Y =    5;
    private static final int WALK_DOWN_OFFSET_Y =    76;
    private static final int WIDTH    =    32;
    private static final int HEIGHT   =    32;
    double x , y;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(1024,640);
        x=0;
        y=0;
        personnage = new Image("file:Data/pixelart.png");
        final ImageView courir_vers_la_droite = new ImageView(personnage);
        courir_vers_la_droite.setViewport(new Rectangle2D(OFFSET_X, WALK_RIGHT_OFFSET_Y, WIDTH, HEIGHT));

        final ImageView courir_vers_la_gauche = new ImageView(personnage);
        courir_vers_la_gauche.setViewport(new Rectangle2D(OFFSET_X, WALK_LEFT_OFFSET_Y, WIDTH, HEIGHT));

        final ImageView courir_vers_le_bas = new ImageView(personnage);
        courir_vers_le_bas.setViewport(new Rectangle2D(OFFSET_X, WALK_DOWN_OFFSET_Y, WIDTH, HEIGHT));

        final ImageView courir_vers_le_haut = new ImageView(personnage);
        courir_vers_le_haut.setViewport(new Rectangle2D(OFFSET_X, WALK_UP_OFFSET_Y, WIDTH, HEIGHT));


        avancer_droite = new SpriteAnimation(
                courir_vers_la_droite,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, WALK_RIGHT_OFFSET_Y,
                WIDTH, HEIGHT
        );

        avancer_gauche = new SpriteAnimation(
                courir_vers_la_gauche,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, WALK_LEFT_OFFSET_Y,
                WIDTH, HEIGHT
        );

        avancer_bas = new SpriteAnimation(
                courir_vers_le_bas,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, WALK_DOWN_OFFSET_Y,
                WIDTH, HEIGHT
        );

        avancer_haut = new SpriteAnimation(
                courir_vers_le_haut,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, WALK_UP_OFFSET_Y,
                WIDTH, HEIGHT
        );


        final double rectangleSpeed = 100 ;
        final double minY = 0 ;
        final double minX = 0 ;
        final double maxX = pane.getPrefWidth() - 50 ;
        final double maxY = pane.getPrefHeight() - 50 ;

        final DoubleProperty rectangleVelocity = new SimpleDoubleProperty();
        final LongProperty lastUpdateTime = new SimpleLongProperty();
        final AnimationTimer rectangleAnimation = new AnimationTimer() {
            public void handle(long timestamp) {
                if (lastUpdateTime.get() > 0) {
                    final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0 ;
                    final double delta = elapsedSeconds * rectangleVelocity.get();
     
                    if(droite) {
                        courir_vers_la_droite.setTranslateY(y);
                        x = Math.max(minX, Math.min(maxX, x + delta));
                        courir_vers_la_droite.setTranslateX(x);
                    } else if(gauche) {
                        courir_vers_la_gauche.setTranslateY(y);
                        x = Math.min(maxX, Math.min(maxX, x - delta));
                        courir_vers_la_gauche.setTranslateX(x);
                    } else if(haut) {
                        courir_vers_le_haut.setTranslateX(x);
                        y = Math.max(minY, Math.min(maxY, y - delta));
                        courir_vers_le_haut.setTranslateY(y);
                    } else if(bas) {
                        courir_vers_le_bas.setTranslateX(x);
                        y= Math.max(minY, Math.min(maxY, y + delta));
                        courir_vers_le_bas.setTranslateY(y);
                    }
                }
                lastUpdateTime.set(timestamp);
            }
        };
        rectangleAnimation.start();


        pane.getChildren().add(0,courir_vers_la_droite);

        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(keyEvent -> {

            if((keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() ==  KeyCode.LEFT || keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.UP)) {

                pane.getChildren().remove(0);

                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    droite = true;
                    gauche = false;
                    bas = false;
                    haut = false;
                    pane.getChildren().add(courir_vers_la_droite);
                    move_droite();
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    droite = false;
                    gauche = true;
                    bas = false;
                    haut = false;
                    pane.getChildren().add(courir_vers_la_gauche);
                    move_gauche();
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    droite = false;
                    gauche = false;
                    haut = true;
                    bas = false;
                    pane.getChildren().add(courir_vers_le_haut);
                    move_haut();
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    droite = false;
                    gauche = false;
                    bas = true;
                    haut = false;
                    pane.getChildren().add(courir_vers_le_bas);
                    move_bas();
                }
              rectangleVelocity.set(rectangleSpeed);
            }
        });

        scene.setOnKeyReleased(keyEvent ->  {

            if (keyEvent.getCode() == KeyCode.RIGHT) {
                avancer_droite.stop();
            } else if(keyEvent.getCode() == KeyCode.LEFT){
                avancer_gauche.stop();
            }
           rectangleVelocity.set(0);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void move_droite() {
        avancer_droite.play();
    }

    public static void move_gauche(){
        avancer_gauche.play();
    }

    public static void move_haut(){
        avancer_haut.play();
    }

    public static void move_bas(){
        avancer_bas.play();
    }

}