import javax.swing.ImageIcon;

public class Hilli extends Enemy{
	public Hilli() {
		
	}
	
	public Hilli(int x, int y) {
		super(30,30, 8, new ImageIcon("move1.gif"),x, y, 120, 170,new RangedWeapon(x - 20, y + 10, 20, 20, 8, new ImageIcon("rock.png")));
		//super(50, 8, i, x, y, 50, 75);
	}
	/*public Hilli(int x. int y) {
		super(50,10,"hilli");
		//int hp, int at, ImageIcon i, int x, int 
	}*/
}
