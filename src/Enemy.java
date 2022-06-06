import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Character{

	
	public Enemy() {
		
	}
	
	public Enemy(int hp, int at, ImageIcon i, int x, int y, Weapon w) {
		super("","",hp,hp,at,0,i,x,y,"", w);
		
	}
		

	
	//public Character(int hp, int at, ImageIcon i, int x, int y, int w, int h) {
	public Enemy(int max, int hp, int at, ImageIcon i, int x, int y, int w, int h, Weapon wp) {
		super(hp, at, i, max,x, y, w, h, wp);
	}
	
	public Enemy(int x, int y, Weapon wp) {
		super(50,8,new ImageIcon("hilli.png"), 50, x,y, 100, 10, wp);
	}
//
//	public int getHp() {
//		return hp;
//	}
//
//	public void setHp(int hp) {
//		this.hp = hp;
//	}
//
//	public int getAtt() {
//		return att;
//	}
//
//	public void setAtt(int att) {
//		this.att = att;
//	}
//
//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public void setWidth(int width) {
//		this.width = width;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public void setHeight(int height) {
//		this.height = height;
//	}
//
//	public ImageIcon getI() {
//		return i;
//	}
//
//	public void setI(ImageIcon i) {
//		this.i = i;
//	}
//	
//	public void drawEm(Graphics g) {
//		g.drawImage(i.getImage(), x, y, width, height, null);
//	}
}

