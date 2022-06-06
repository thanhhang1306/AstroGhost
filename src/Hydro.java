import javax.swing.ImageIcon;

public class Hydro extends Character{
	public Hydro() {
		
	}
	public Hydro(int x, int y, ImageIcon k, int x1, int y1) {
	//	super("Corspe Resurector","Ajan",79,15,6,k,x,y,
	//			"Every 15 atk: +1 atk; max 5 stacks", new RangedWeapon(x + 200, y +80, 40,50, 15, new ImageIcon("hydroW1.png")));
		super("Corspe Resurector","Ajan",79,79,15,6,k,x,y,
				"Every 15 atk: +1 atk; max 5 stacks", new RangedWeapon(x1, y1, 40,50, 15, new ImageIcon("hydroW1.png")));
	}
}
