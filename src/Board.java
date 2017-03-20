
public class Board {
	private int taille;
	private Case [][] board;
	public Board(int t,int vampire){
		taille = t;
		board = new Case [t][t];
		int x = (int) (Math.random()*(t-1));
		int y = (int) (Math.random()*(t-1));

		for(int i =0;i<t;i++){
			for(int j=0;j<t;j++){
				if(i==x && j==y)
					board[i][j] = new Pistolero();
				else
					board[i][j] = new Empty();
			}
		}
		for(int i=0;i<vampire;vampire++){
			int xv,yv;
			do{
				xv = (int) (Math.random()*(t-1));
				yv = (int) (Math.random()*(t-1));
			}while(!board[xv][yv].isEmpty());
			board[xv][yv] = new Vampire();
		}
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
