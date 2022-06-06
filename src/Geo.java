import javax.swing.ImageIcon;

public class Geo extends Character{
	public Geo() {
		
	}
	public Geo(int x, int y, ImageIcon h, int x1, int y1) {
		super("Funeral Consultant", "Matt",80,80,5,7,h,x,y,
				"Every 15 atk: heals 10% of current hp",new HoningWeapon(x1,y1, 100, 70, 5, new ImageIcon("geoW1.png")));
	}
}
