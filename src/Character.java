import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Character {
	private int health, attack, def, x, y, width, height, dx, dy, maxhp;
	private ImageIcon img; 
	private String desc, specialSkill, role;
	private Weapon weapon;
	//private boolean choosen = false; 
	
	/*public boolean isChoosen() {
		return choosen;
	}

	public void setChoosen(boolean choosen) {
		this.choosen = choosen;
	}*/

	public Character() {
		maxhp = 100;
		health = 100; 
		attack = 100; 
		def = 100; 
		x = 0; 
		y = 0;
		width = 0;
		height = 0; 
		dx = 0; 
		dy = 0;
		desc = "";
		specialSkill = "";
	}
	
	public Character(String r, String des, int ma, int hp, int at, int d, ImageIcon i,int x, int y1, String s, Weapon wp) {
		this.maxhp = ma;
		this.health = hp; 
		this.desc = des; 
		this.attack = at; 
		this.def = d;
		this.x = x;
		this.y = y1;
		this.img = i;
		this.role = r;
		width = 350; 
		height = 350; 
		dx = 0; 
		dy = 0;
		this.specialSkill=s;
		this.weapon = wp;
	//	this.weapon = w; , Weapon w
	}
	
	public Character(int hp, int at, ImageIcon i, int max, int x, int y, int w, int h, Weapon wp) {
		this.health = hp; 
		this.maxhp=max;
		this.attack = at;
		this.x = x;
		this.y = y;
		this.img = i;
		this.width = w;
		this.height = h;
		this.weapon = wp;
	}
	
	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
/*	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}*/

	public String getSpecialSkill() {
		return specialSkill;
	}

	public void setSpecialSkill(String specialSkill) {
		this.specialSkill = specialSkill;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		x+=dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		y+=dy;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	public void drawChar(Graphics g, int i) {
		g.drawImage(img.getImage(), x, y, width/i, height/i, null);
	}
	
//	public boolean rangedAttackCollide(Weapon r) {
//		boolean choosen = false;
//		if(r.getX() < x + width && r.getX() + r.getWidth()>x &&
//				r.getY() >y + height && r.getY() +r.getHeight() > y) {
//			choosen = true;
//		}
//		return choosen;
	//}
	
	
}
