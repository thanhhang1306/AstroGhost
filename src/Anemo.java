import javax.swing.ImageIcon;

public class Anemo extends Character{

	public Anemo() {
		
	}
	public Anemo(int x, int y, ImageIcon b, int x1, int y1) {
		super("Funeral Bard","Barb",83,83,12,5,b,x,y,
				"Every 15 atk: all enemies atk -3, max 2 stack", new RangedWeapon(x1,y1, 90, 90, 12, new ImageIcon("anemoW1.png")));
	// int x, int y, int w, int h, int a, ImageIcon i
	}
}
