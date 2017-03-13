
public class Pistolero extends Case{

	public boolean Shoot(Case c){
		return c.takeShot();
	}
	@Override
	public boolean move(Case c) {
		// TODO Auto-generated method stub
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
