import javax.swing.ImageIcon;

public class Mage extends Enemy{
	
	public Mage(int x, int y) {
		super(75,75, 15, new ImageIcon("mage.png"), x, y, 160, 200,new HoningWeapon(x -10, y +130, 40, 40, 12, new ImageIcon("fire.png")));
	}
}
