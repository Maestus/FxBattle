
public class Vampire extends Character{
	
	public Vampire(){
		life = 3;
	}
	@Override
	public boolean moveOn() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeShot() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
