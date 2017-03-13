
public class Board {
	private int taille;
	private Case [][] board;
	
	public Board(int t){
		taille = t;
		board = new Case [t][t];
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Case  getBoard(int i,int j) {
		return board[i][j];
	}

	
}
