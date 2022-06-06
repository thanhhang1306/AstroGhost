import javax.swing.ImageIcon;

public class Cryo extends Character{
	public Cryo() {
		
	}
	public Cryo(int x, int y, ImageIcon c, int x1, int y1) {
		super("Grave Digger", "Hoot",71,71,16,3,c,x,y,
				"Every 35 atk: x2 atk for 1 hit",new RangedWeapon(x1, y1, 70, 50, 16, new ImageIcon("cryoW1.png")));
	}
}
