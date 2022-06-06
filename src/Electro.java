import javax.swing.ImageIcon;

public class Electro extends Character{
	public Electro() {
		
	}
	public Electro(int x, int y, ImageIcon k, int x1, int y1) {
		super("Funeral Zombie ","Kiki",90,90,7,6,k,x,y,
				"Every 20 atk: +15 hp", new HoningWeapon(x1,y1, 90, 60, 7, new ImageIcon("electroW1.png")));
	}
}
