import javax.swing.ImageIcon;

public class Pyro extends Character{
	public Pyro() {
		
	}
	public Pyro(int x, int y, ImageIcon v, int x1, int y1) {
		super("Funeral Director", "Lily",70,70,12,3,v,x,y,
				"Every 10 atk: -3 hp & +2 atk; max 5 stacks",new RangedWeapon(x1, y1, 60, 60, 20, new ImageIcon("pyroW1.png")));
	}
}
