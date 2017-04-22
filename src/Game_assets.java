import java.util.HashMap;

public class Game_assets {
	HashMap <Integer, Sprite> elements = new HashMap<Integer, Sprite>();
	Sprite joueur; 
	
	public void set_joueur_asset(Sprite _joueur){
		joueur = _joueur;
	}
}
