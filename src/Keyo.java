import javax.swing.ImageIcon;

public class Keyo extends Character{
	public Keyo() {
		
	}
	public Keyo(int x, int y, ImageIcon j, int x1, int y1) {
		super("Funeral Flowerist","Key",75,75,15,5,j,x,y,
				"10 turns, +5hp/+2atk/+1def, max 3 stacks",new RangedWeapon(x1, y1, 70, 70, 15, new ImageIcon("dendroW1.png")));
	}
}
