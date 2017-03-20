
public class Pistolero extends Character{
	
	public Pistolero(){
		life = 3;
	}
	public boolean Shoot(Case c){
		return c.takeShot();
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
		life--;
		return true;
	}
	
}
