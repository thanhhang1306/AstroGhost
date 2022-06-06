import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Weapon {
	private int att; 
	private int x, y, width, height;
	private ImageIcon i;
	private boolean collide;
	
	public Weapon() {
		att = 100; 
		x = 10; 
		y = 10; 
		width = 5; 
		height = 5; 
		collide = false;
	}
	
	public Weapon(int x, int y, int w, int h, int a, ImageIcon i) {
		this.x = x; 
		this.y = y;
		this.width = w; 
		this.height = h;
		this.att = a; 
		collide = false;
		this.i = i;
	}

	public boolean isCollide() {
		return collide;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
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

	public ImageIcon getI() {
		return i;
	}

	public void setI(ImageIcon i) {
		this.i = i;
	}
	
	public void setdX(int dx) {
		x+=dx;
	}
	
	public void setdY(int dy) {
		y+=dy;
	}
	
	public void drawWeapon(Graphics g) {
		g.drawImage(i.getImage(), x, y, width, height, null);
	}
	
	public void rangedWeaponCollide(Enemy e) {
		if(x + width> e.getX() && x < e.getX() + e.getWidth() &&
				y + height> e.getY() && y < e.getY() + e.getHeight()) {
			collide = true;
		}
	}
	
}
