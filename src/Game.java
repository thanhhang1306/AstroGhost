import javax.lang.model.element.Element;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue; 

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	private BufferedImage back;
	private char screen, charac,opening, winLose; 
	private ImageIcon open, cursor, select, character,cSelect, info, black, opening1, opening2,opening3;
	private ImageIcon story, opening4, background, story1,story2,story3,story4,story5,story6,story7,story8, tutorial; 
	private int SCREEN_WIDTH = 1200, SCREEN_HEIGHT = 700, key; 
	private int cursorX = 575, cursorY = 325;
	private boolean selectUp = true, click = false,  type = false, collide = false; 
	private int counter = 0, counter1 = 0, openCount = 0, enemyCount=0,counter2=1, cooldown = 100,cooldown1=100, attack=0, stack = 0; 
	private ArrayList<Character> charList;
	private String playerName;
	private int backX, backY, screena; 
	private Queue<Enemy> enemyList; 
	private Character choosen; 
	private boolean isLeft = false, isRight = false, isUp = false, isDown = false, moving = false, startGame = false, press = false; 
	private ArrayList<Weapon> weaponList, enemyWeaponList;
	private String fileName; 
	private int run = 0, timer = 0,highScore = 0, iScan = 0, score = 0;
	private ImageIcon lose1,lose2,win1,win2;


	public Game() {
		key = -1;
		back=null;
		new Thread(this).start();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		screen = 'A';
		charac = 'Z';
		opening = 'A';
		open = new ImageIcon("opening.gif");
		cursor = new ImageIcon("cursor1.png");
		select = new ImageIcon("arrow.png");
		character = new ImageIcon("rest01.gif");
		cSelect = new ImageIcon("characterselection.png");
		info = new ImageIcon("characterinfo1.png");
		black = new ImageIcon("blackscreen.png");
		opening1 = new ImageIcon("opening01.gif");
		opening2 = new ImageIcon("opening2.png");
		opening3 = new ImageIcon("opening3.gif");
		story = new ImageIcon("storyline.gif");
		background = new ImageIcon("longbackground.png");
		opening4 = new ImageIcon("opening4.gif");
		story1 = new ImageIcon("story1.gif");
		story2 = new ImageIcon("story2.gif");
		story3 = new ImageIcon("story3.gif");
		story4 = new ImageIcon("story4.gif");
		story5 = new ImageIcon("story5.gif");
		story6 = new ImageIcon("story6.gif");
		story7 = new ImageIcon("story7.gif");
		story8 = new ImageIcon("story8.gif");
		tutorial = new ImageIcon("instruc.png");
		lose1 = new ImageIcon("lost1.gif");
		lose2 = new ImageIcon("lost2.gif");
		win1 = new ImageIcon("win1.gif");
		win2 = new ImageIcon("win2.gif");
		charList = getChar();
		playerName = "";
		backX = 0; backY = 0;
		choosen = new Character(); 
		enemyList = new LinkedList<>();
		addEnemies();
		screena= 0;
		winLose = 'N';
		weaponList  = new ArrayList<Weapon>();
		enemyWeaponList = new ArrayList<Weapon>();
		fileName = "saveFile.txt";
		Player.playmusic("music.wav");
		createFile();
		writeToFile();
	}
	
	public void reset(boolean x) {
			charac = 'Z';
			opening = 'A';
			charList = getChar();
			backX = 0; backY = 0;
			addEnemies();
			screena= 0;
			winLose = 'N';
			fileName = "saveFile.txt";
			run = 0; timer = 0;
			isLeft = false; isRight = false;
			isUp = false; isDown = false; 
			moving = false; startGame = false; 
			press = false; 
			counter = 0;
			counter1 = 0; openCount = 0; 
			enemyCount=0;
			counter2=2; 
			cooldown = 100;
			cooldown1=100; 
			attack=0;
			stack = 0; 
			selectUp = true; click = false;
			type = false; collide = false; 
			weaponList.clear();
			enemyWeaponList.clear();
			if(x) {
				screen = 'A';
				playerName = "";
			}
			else if(!x) screen = 'D';
		
			
	}

	public void createFile() {
		try {
			File myFile = new File(fileName);  // C://------
			if(myFile.createNewFile())
				System.out.println("file created " + myFile.getName());
			else System.out.println("file already exist");
		}
		catch(IOException e) {
			System.out.println("an error occured: "); 
			e.printStackTrace();
		}
	}

	public void writeToFile() {
		try {
			FileWriter myWriter = new FileWriter(fileName);
			myWriter.write(100);
			highScore = 100;
			if(score < highScore || highScore == 0) {
				myWriter.write("\n" + score);
			}
			myWriter.close(); 
			System.out.println("succesfully written");
		}
		catch(IOException e) {
			System.out.println("an error occured: "); 
			e.printStackTrace();
		}
	}

	public void readFile() {
		try {
			File file = new File(fileName);
			Scanner sc = new Scanner(file);
			while(sc.hasNext()){
				
	                iScan =sc.nextInt();
	                highScore = iScan;
	                System.out.println("high score: "+ iScan);
	                
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public int getRandom() {
		return (int)(Math.random()*((SCREEN_HEIGHT - 420) + 1) + 200); 
	}

	public void addEnemies() {
		getRandom();
		if(backX > -6500 && enemyList.size() <=1) {
			if(!(counter2%4==0)) {

				enemyList.add(new Hilli(1150,getRandom()));
			}

			else {
				//enemyWeaponList.clear();
				enemyList.add(new Mage(1100, getRandom()));
			}
			counter2++;
		}
		if(backX >= -6499 && enemyList.isEmpty())
			winLose = 'W';
		if(enemyList.isEmpty())
			enemyWeaponList.clear();
	}



	public int randomMovement() {
		int dy; 
		if((enemyList.element().getY() + enemyList.element().getHeight() > SCREEN_HEIGHT - 20)||(enemyList.element().getY() <20))
			collide = !collide;
		if(!collide) 
			dy = 3; 
		else dy = -3;
		return dy; 
	}

	public void moveEnemies() {
		enemyCount++;
		if(enemyCount%5==0) 
			enemyList.element().setDx(-2);
		if(enemyCount%5 ==0)
			enemyList.element().setDy(randomMovement());
	}	

	
	public void checkWin() {
		if(choosen.getHealth() <=0)
			winLose = 'L';
		if(enemyList.isEmpty() && backX <=-6499)// && backX >=-6400)
			winLose = 'W';
		
	}
	public ArrayList<Character> getChar(){
		ArrayList <Character> temp = new ArrayList(); 
		temp.add(new Pyro(170,95, new ImageIcon("pyro.png"),10,10));
		temp.add(new Hydro(170,95,new ImageIcon("hydro.png"),10,10));
		temp.add(new Anemo(170,95, new ImageIcon("anemo.png"),10,10));
		temp.add(new Geo(170,95,new ImageIcon("geo.png"),10,10));
		temp.add(new Electro(170,95,new ImageIcon("electro.png"),10,10));
		temp.add(new Cryo(170,95,new ImageIcon("cryo.png"),10,10));
		temp.add(new Keyo(170,95,new ImageIcon("dendro.png"),10,10));

		temp.add(new Pyro(20,450, new ImageIcon("pyro1.png"),10,10));
		temp.add(new Hydro(20,450,new ImageIcon("hydro1.png"),10,10));
		temp.add(new Anemo(20,450, new ImageIcon("anemo1.png"),10,10));
		temp.add(new Geo(20,450,new ImageIcon("geo1.png"),10,10));
		temp.add(new Electro(20,450,new ImageIcon("electro1.png"),10,10));
		temp.add(new Cryo(20,450,new ImageIcon("cryo1.png"),10,10));
		temp.add(new Keyo(20,450,new ImageIcon("dendro1.png"),10,10));
		return temp;
	}

	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}
	}


	public void paint (Graphics g)
	{
		Graphics2D twoDgraph = (Graphics2D)g;
		if (back==null) {
			back =(BufferedImage) (createImage(getWidth(), getHeight()));
		}
		Graphics g2d = back.createGraphics();
		g2d.clearRect(0, 0, getSize().width, getSize().height); 
		drawScreen(g2d);
		counter++;
		///System.out.println(attack);
		//System.out.println(""+attack);
		//System.out.println(weaponList.size());
		System.out.println(score);
		//This line tells the program to draw everything above. If you delete this, nothing will show up.
		twoDgraph.drawImage(back, 0, 0, null);
	}

	public void drawScreen(Graphics g) {
		//System.out.println(click +" "+ screena);
		switch(screen) {
		case 'A': 
			if(opening == 'A') {
				g.drawImage(black.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				openCount++;
				if(openCount > 300 && openCount <= 1450) {
					g.drawImage(opening1.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				}

				if(openCount > 1450 || click) {
					g.drawImage(opening2.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
					g.setColor(Color.white);
					g.setFont(new Font("Garamond",Font.BOLD,30));
					g.drawString(playerName, 450, 515);
					g.setFont(new Font("Garamond",Font.ITALIC,25));
					g.drawString("Press ENTER to continue", 490, 630);
					type = true;
				}
			}
			if(opening == 'B') {
				type = false;
				boolean startCount = false;

				g.drawImage(opening3.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);

				if(screena == 1) {
					g.drawImage(black.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
					startCount = true;
				}
				if(startCount) openCount++;
				if(screena == 2 || openCount > 200) {
					screen = 'B';
					screena = 0;
				}



			}
			g.setColor(Color.white);
			g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 20));
			g.drawString("Click to skip/continue", 1050, 650);
			g.drawString("Press F2 to skip to the gameplay", 20, 650);
			//	System.out.println(openCount);
			break;
		case 'B': 
			g.drawImage(open.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);

			drawArray(g);
			break;
		case 'C': 
			g.drawImage(character.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);

			break;
		case 'F': 
			g.drawImage(black.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
			openCount++;
			//clickScreen(g);
			if(opening == 'C') {
				if(openCount >=250 && openCount < 500)
					g.drawImage(story.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if (openCount >= 500 && openCount <800)
					g.drawImage(black.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if (openCount >= 800 && openCount <1050)
					g.drawImage(opening4.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >=1050) {
					g.drawImage(story1.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
					opening = 'D';
				}
			}
			if(opening == 'D') {
				switch(screena) {
				case 1: g.drawImage(story3.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 2: g.drawImage(story2.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 3: g.drawImage(story4.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 4: g.drawImage(story5.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 5: g.drawImage(story6.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 6: g.drawImage(story7.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 7: g.drawImage(story8.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;
				case 8: screen = 'D'; break;
				case 0: g.drawImage(story1.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this); break;

				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 20));
			g.drawString("Click to skip/continue", 1050, 650);
			/*		if(openCount >=2580 && openCount < 4200)
					g.drawImage(story3.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >=4200 && openCount < 5700)
					g.drawImage(story2.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >=5700 && openCount < 6800)
					g.drawImage(story4.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >=6800 && openCount < 7330)
					g.drawImage(story5.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
						//printScreen(g, "The others are preparing to leave this planet... It's too dangerous",200,470);
				if(openCount >=7330&& openCount < 11050)
					g.drawImage(story6.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >=11050 && openCount < 12100) 
					g.drawImage(story7.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
//					g.setColor(Color.white);
//						if(openCount >=11700 && openCount < 12020 && !drawOnce) {
//							g.setFont(new Font("Garamond",Font.BOLD,40));
//							printScreen(g,"h",800,190);
//							drawOnce= true;
//						}
//				}
				if(openCount >=12100 && openCount < 13650)
					g.drawImage(story8.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
				if(openCount >= 13650)
					screen = 'D';
				}*/

			break;
		case 'D': 
			g.drawImage(cSelect.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT-30,this);
			g.setColor(Color.white);
			printScreen(g, "Click on the characters to learn about their profile!",250,630);
			//System.out.println(charac);
			break;
		case 'E': 
			counter = 0;
			g.drawImage(info.getImage(), 0, 0, SCREEN_WIDTH,SCREEN_HEIGHT,this);
			g.setColor(Color.white);
			g.setFont(new Font("Lucida Handwriting",Font.BOLD,60));
			switch(charac) {
			case 'A': 
				drawChar(g,0);
				break;
			case 'B': 
				drawChar(g,1);
				break;
			case 'C': 
				drawChar(g,2);
				break;
			case 'D': 
				drawChar(g,3);
				break;
			case 'E':
				drawChar(g,4);
				break;
			case 'F': 
				drawChar(g,5);
				break;
			case 'G': 
				drawChar(g,6);
				break;
			}

			break;
		case 'G': 
			//System.out.println(winLose);
			readFile();
			g.drawImage(background.getImage(), backX, backY, 8000,700,this);
			//System.out.println(charac);
			g.setColor(Color.green);
			g.fillRect(20, 70, choosen.getHealth()*2, 20);

			g.setColor(Color.white);

			g.setFont(new Font("Bodoni MT Condensed", Font.BOLD, 30));
			g.drawRect(20, 70, choosen.getMaxhp()*2, 20);
			g.drawString("Health: ", 20, 60);
			g.drawString(""+choosen.getHealth()+"/"+choosen.getMaxhp(), 110, 60);
			showDetails(g);
			counter++;
			timer = counter/250;
			g.drawString("Time taken: "+ timer, 1000, 60);
			g.drawString("Highscore: "+ highScore, 1000,100);
			switch(charac) {
			case 'A': 
				charList.get(7).drawChar(g,2);
				choosen = charList.get(7);
				break;
			case 'B': 
				charList.get(8).drawChar(g,2);
				choosen = charList.get(8);
				break;
			case 'C': 
				charList.get(9).drawChar(g,2);
				choosen = charList.get(9);
				break;
			case 'D': 
				charList.get(10).drawChar(g,2);
				choosen = charList.get(10);
				break;
			case 'E': 
				charList.get(11).drawChar(g,2);
				choosen = charList.get(11);
				break;
			case 'F': 
				charList.get(12).drawChar(g,2);
				choosen = charList.get(12);
				break;
			case 'G': 
				charList.get(13).drawChar(g,2);
				choosen = charList.get(13);
				break;
			}
			if(startGame) {
				movement();
				drawWeapon(g);

				//winLose();
				//	autoLose();
				checkWin();
				winLose();
				//	System.out.println(collideCharacter(enemyList.element(),choosen));

				if(!enemyList.isEmpty()) {	
					enemyList.element().drawChar(g,1);
					moveEnemies();
					g.setColor(Color.RED);
					//int length;

					((Graphics2D)g).setStroke(new BasicStroke(1));
					if(enemyList.element().getWeapon() instanceof RangedWeapon) {
						g.fillRect(enemyList.element().getX()+15, enemyList.element().getY() -15, enemyList.element().getHealth()*3, 10);
						g.setColor(Color.white);
						g.drawRect(enemyList.element().getX()+15, enemyList.element().getY() -15, enemyList.element().getMaxhp()*3, 10);
					}
					if(enemyList.element().getWeapon() instanceof HoningWeapon) {
						g.fillRect(enemyList.element().getX()+5, enemyList.element().getY() -15, enemyList.element().getHealth()*2, 10);
						g.setColor(Color.white);
						g.drawRect(enemyList.element().getX()+5, enemyList.element().getY() -15, enemyList.element().getMaxhp()*2, 10);
					}
					enemiesBullet(g); 
					enemiesWeapon(g);
					addEnemies();
					collideCharacter();
					specialSkill();
				}
				//System.out.println(""+ enemyList.size());
				cooldown++; cooldown1++;
//				if(enemyList.isEmpty())
//					screen = 'H';
			}
			break;

		case 'H':
			//g.drawString("nice", 100, 100);
			g.drawImage(tutorial.getImage(),0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
			click = false; 
			break;
		case 'W': 
			//System.out.println(counter);
			score = timer;
			writeToFile();
			if(!click) {
				g.drawImage(win1.getImage(), 0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
				g.setColor(Color.white);
				g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 20));
				g.drawString("Click to skip/continue", 1050, 650);
			}
			else {
				g.drawImage(win2.getImage(), 0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
				g.setColor(Color.white);
				g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 80));
				g.drawString(playerName, 370,400);
			}
			break; 
		case 'L':
			if(!click) {
				g.drawImage(lose1.getImage(), 0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
				g.setColor(Color.white);
				g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 20));
				g.drawString("Click to skip/continue", 1050, 650);
			}
			else {
				g.drawImage(lose2.getImage(), 0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
				g.setColor(Color.white);
				g.setFont(new Font("Bodoni MT Condensed", Font.ITALIC, 50));
				g.drawString(playerName, 530,450);
			}
			break;
		}

		//drawWeapon(g);
		//	System.out.println(enemyList.element().getWidth() + " "+ enemyList.element().getHeight());
		//		g.drawImage(enemyList.element().getImg().getImage(), enemyList.element().getX(), enemyList.element().getY(), enemyList.element().getWidth(), enemyList.element().getHeight(), this); 
		//	g.drawImage(choosen.getImg().getImage(), 10,10,10,10,this); 

		g.drawImage(cursor.getImage(), cursorX, cursorY, 30,30,this);

	}

	public void showDetails(Graphics g) {
		if(press) {

			switch(charac) {
			case 'A': 
				g.setColor(Color.RED);
				break;
			case 'B':
				g.setColor(Color.BLUE);
				break;
			case 'C':
				g.setColor(new Color(34,139,34));
				break;
			case 'D':
				g.setColor(Color.GRAY);
				break;
			case 'E':
				g.setColor(new Color(149, 53, 83));
				break;
			case 'F':
				g.setColor(new Color(102,178,255));
				break;
			case 'G':
				g.setColor(Color.PINK);
				break;
			}

			g.fillRoundRect(choosen.getX() + choosen.getHeight()/2 + 11, choosen.getY() - 49, 448, 98,20,20);
			g.fillOval(choosen.getX() + choosen.getHeight()/2 +1, choosen.getY() + 41, 23,23);

			g.setColor(Color.white);
			g.drawRoundRect(choosen.getX() + choosen.getHeight()/2 + 10, choosen.getY() - 50, 450, 100,20,20);
			g.drawOval(choosen.getX() + choosen.getHeight()/2 , choosen.getY() + 40, 25,25);

			g.setFont(new Font("Garamond",Font.PLAIN,20));
			g.drawString("Health: " + choosen.getMaxhp(),choosen.getX()  + choosen.getHeight()/2 +30 , choosen.getY() - 20);
			g.drawString("Attack: " + choosen.getAttack(), choosen.getX() + choosen.getHeight()/2 +200 , choosen.getY() - 20);
			g.drawString("Defense: " + choosen.getAttack(), choosen.getX() + choosen.getHeight()/2 +350 , choosen.getY() - 20);
			g.drawString("Skill: " + choosen.getSpecialSkill(), choosen.getX() + choosen.getHeight()/2 +30 , choosen.getY() +30);
		}


	}
	public void drawChar(Graphics g, int i) {
		charList.get(i).drawChar(g,1);
		g.drawString(Integer.toString(charList.get(i).getHealth()),720,150);
		g.drawString(Integer.toString(charList.get(i).getAttack()),770,250);
		g.drawString(Integer.toString(charList.get(i).getDef()),1050,250);
		g.setFont(new Font("Garamond",Font.BOLD,23));
		g.drawString(charList.get(i).getSpecialSkill(),750,350);
		g.drawString(charList.get(i).getRole(),370,495);
		g.setFont(new Font("Garamond",Font.BOLD,30));
		g.drawString(charList.get(i).getDesc(),193,420);
		g.setColor(Color.white);
		g.drawString("Press left to return to character menu, press right to select character",150,650);

	}
	public void specialSkill() {
		///if(run%2==1) {
		//System.out.println(stack +" " + attack + " " + run);

		//System.out.println(choosen.getAttack());
		if(run==1) {	
		switch(charac) {
			case 'A': 
				if(attack%10==0 && stack < 6) {
					choosen.setAttack(choosen.getAttack()+2);
					choosen.setHealth(choosen.getHealth()-3);
					stack++;
					run=0;
				}
				break;
			case 'B': 
				if(attack%15==0 && stack < 6) {
					choosen.setAttack(choosen.getAttack() + 1);
					stack++;
					run=0;
				}
				break;
			case 'C': 
				if(attack%15==0) {
					choosen.setDef(choosen.getDef()+3);
					run=0;
				}
				break;
			case 'D': 	
				if(attack%10==0) {
					//						int hpIncrease = choosen.getHealth()/10;
					if(choosen.getHealth()/10 + choosen.getHealth() >= choosen.getMaxhp()) {
						choosen.setHealth(choosen.getMaxhp());
						System.out.println("lots of hp");
					}
					if(choosen.getHealth()/10 + choosen.getHealth()< choosen.getMaxhp()) {
						choosen.setHealth(choosen.getHealth()+choosen.getHealth()/10);
						System.out.println("not a lot of hp");
					}
					run=0;
				}
				break;
			case 'E':
				if(attack%20==0) {
					if(20 + choosen.getHealth() >= choosen.getMaxhp()) 
						choosen.setHealth(choosen.getMaxhp());
					else choosen.setHealth(20 + choosen.getHealth());
					run=0;
				}	
				break;
			case 'F':
				if(attack%35==0) {
					choosen.setAttack(choosen.getAttack()*2);
					run=0;
				}
				if(attack%37==0) {
					choosen.setAttack(choosen.getAttack()/2);
					run=0;
				}
				break;
			case 'G':
				if(attack%10==0 && stack < 4) {
					choosen.setAttack(choosen.getAttack()+2);
					choosen.setDef(choosen.getDef()+1);
					if(choosen.getHealth() + 5 > choosen.getMaxhp()) {
						choosen.setHealth(choosen.getMaxhp());
					}
					else choosen.setHealth(choosen.getHealth()+5);

				}
				run=0;
				break;
			
			}       
		

		}
	}

	public boolean collision(Character c, Weapon w, int i){
		//	for(Weapon w: weaponList){

		if(w.getX()+w.getWidth()>=c.getX()+5&&w.getX()<c.getX()-5+c.getWidth()/i
				&& w.getY()<=c.getY()-5+c.getHeight()/i&&w.getY()+w.getHeight()>=c.getY()+5) {
			return true;
		}			
		else return false;

	}

	public void collideCharacter() {
		if(enemyList.element().getX() < choosen.getX() + choosen.getWidth()-200) {
			choosen.setHealth(choosen.getHealth() - 10);
			enemyList.remove();
		}

	}


	public void drawWeapon(Graphics g) {
		if(!enemyList.isEmpty()) {
			for(Weapon w: weaponList){

				//	g.drawImage(w.getI().getImage(),w.getX(),w.getY(),532,234 , this);
				g.drawImage(w.getI().getImage(), w.getX(), w.getY(), w.getWidth(), w.getHeight(), this);

				if(choosen.getWeapon() instanceof RangedWeapon)
					w.setdX(3);
				if(choosen.getWeapon() instanceof HoningWeapon) {
					w.setdX(2);
					if(w.getY() >= enemyList.element().getY() + enemyList.element().getHeight()/4)
						w.setdY(-1);
					else if(w.getY() < enemyList.element().getY() + (enemyList.element().getHeight()*3)/4)
						w.setdY(1);
					
				}
				if(collision(enemyList.element(),w,1)) {
					//enemyList.remove();
					weaponList.remove(w);
					enemiesHealth();
				}
				//System.out.println("oops");
				//weaponList.remove(w);

			}
		}


	}

	public void enemiesBullet(Graphics g) {

		//System.out.println(cooldown1);
		if(enemyList.element().getWeapon() instanceof RangedWeapon) {
			if(cooldown1%200==0) {
				enemyWeaponList.add(new Weapon(enemyList.element().getX() - 20, enemyList.element().getY() + 80, enemyList.element().getWeapon().getWidth(), enemyList.element().getWeapon().getHeight(), enemyList.element().getWeapon().getAtt(), enemyList.element().getWeapon().getI()));
				//cooldown1 = 0;
			}
		}
		if(enemyList.element().getWeapon() instanceof HoningWeapon) {
			if(cooldown1%275==0) {
				enemyWeaponList.add(new Weapon(enemyList.element().getX() - 20, enemyList.element().getY() + 80, enemyList.element().getWeapon().getWidth(), enemyList.element().getWeapon().getHeight(), enemyList.element().getWeapon().getAtt(), enemyList.element().getWeapon().getI()));
				//cooldown1 = 0;
			}
		}
	}

	public void enemiesWeapon(Graphics g) {
		if(!enemyWeaponList.isEmpty()) {
			for(Weapon w: enemyWeaponList){
				g.drawImage(w.getI().getImage(), w.getX(), w.getY(), w.getWidth(), w.getHeight(), this);


				if(enemyList.element().getWeapon() instanceof RangedWeapon) {
					if(moving)w.setdX(-3);
					else w.setdX(-2);
				}
				if(enemyList.element().getWeapon() instanceof HoningWeapon) {
					if(moving)w.setdX(-3);
					else w.setdX(-2);
					if(w.getY() >= choosen.getY() + choosen.getHeight()/4)
						w.setdY(-1);
					else if(w.getY() < choosen.getY() + choosen.getHeight()/4)
						w.setdY(1);
				}
				if(collision(choosen,w,2)) {
					//enemyList.remove();
					enemyWeaponList.remove(w);
					//enemiesHealth();
					cHealth();
				}
			}
		}
	}

	public void winLose() {
		if(winLose=='L')
			screen = 'L';
		if(winLose == 'W')
			screen = 'W';
	}

	public void enemiesHealth() {
		if(enemyList.element().getHealth() > 0) 
			enemyList.element().setHealth(enemyList.element().getHealth() - choosen.getAttack());
		if(enemyList.element().getHealth() <=0)  
			enemyList.remove(); 
	}

	public void cHealth() {
		if(choosen.getHealth() > 0) 
			choosen.setHealth(choosen.getHealth() - (enemyList.element().getAttack()-choosen.getDef()));
		if(choosen.getHealth() <=0)  
			winLose = 'L';
	}
	
	public void drawArray(Graphics g) {
		if (selectUp)
			g.drawImage(select.getImage(), 350, 338, 20, 20,this);
		else 
			g.drawImage(select.getImage(), 350, 396, 20, 20,this);
	}

	public void printScreen(Graphics g, String s, int x, int y) {

		/*	System.out.println(counter1);
		g.setFont(new Font("Garamond",Font.BOLD,30));
		if(counter%100==0 ) {
			g.drawString(s.substring(0,counter1),x,y);
			counter1++;*/
		g.setFont(new Font("Garamond",Font.BOLD,30));
		if (counter%10==0) { 
			if(counter1 <s.length())
				counter1++;
			else if(counter%100==0)
				counter1=0;

		}


		g.drawString(s.substring(0,counter1), x, y);


	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(screen == 'A' ||  screen == 'F' || screen == 'W' || screen == 'L') {
			click = true; 
			if(screen == 'A'&& opening == 'B')
				screena++;

		}
		switch(screen) {

		case 'B': 

			if(e.getX() >= 380 ) {
				if(e.getY()>=300 && e.getY() <=375&&e.getX() <=500) {
					screen = 'F';
					//screen = 'D';
					opening = 'C';
					openCount = 0; 
				}

				else if(e.getX()<=610 && e.getY() >=375 && e.getY() <=425)
					screen = 'C';
			}
			break;
		case 'D': 
			if(e.getY()>30 && e.getY() <280) {
				if(e.getX() > 50 && e.getX() < 295) 
					charac = 'A';
				if(e.getX()>330 & e.getX() < 575) 
					charac = 'B';
				if(e.getX() > 610 && e.getX()<855)
					charac = 'C';
				if(e.getX() > 900 && e.getX() < 1145)
					charac = 'D';

			}
			else if (e.getY() > 330 && e.getY() < 580) {
				if(e.getX() > 160 && e.getX() < 405)
					charac = 'E';
				if(e.getX() > 460 && e.getX() < 705)
					charac = 'F';
				if(e.getX() > 760 && e.getX() < 1005)
					charac = 'G';
			}
			else charac = 'Z';

			if(charac!='Z')
				screen = 'E';
			break;
		case 'F':
			if(opening == 'D')
				screena++;

		}
	}




	@Override
	public void mousePressed(MouseEvent e) {
		int click = 0;
		if(screen == 'G') {
			click++;
			if(e.getX() > choosen.getX() && e.getX() < choosen.getX() + choosen.getWidth() && 
					e.getY() > choosen.getY() && e.getY() < choosen.getY() + choosen.getHeight()) {
				if(!press) {
					press = true;
					startGame = false;
					click=0;
				}
				if(press&&click ==1) {
					press = false;
					startGame = true;
				}
			}



		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_SPACE && (screen == 'C' || screen == 'D'))
			screen = 'B';
		key = e.getKeyCode();
		char charKey = (char)(key);
		//	System.out.println(charKey);
		if (screen == 'A' && type){

			if (playerName.length() < 14){
				if(key == KeyEvent.VK_BACK_SPACE && playerName.length() ==0) {

				}
				else playerName += charKey;

			}
			if(key == KeyEvent.VK_BACK_SPACE && playerName.length() >0) {
				playerName = playerName.substring(0, playerName.length()-2);
			}
			if(key == KeyEvent.VK_ENTER) {
				opening = 'B';
				screena = 0;
				openCount = 0;
			}
		}	
		switch(screen) {
		case 'E': 

			if(key == KeyEvent.VK_LEFT) {
				screen = 'D';
				//charac = 'Z';
			}
			else if (key == KeyEvent.VK_RIGHT)
				screen = 'H';
			break;
		case 'G': 

			if(key == KeyEvent.VK_LEFT && backX <=-1 ) {
				isLeft = true;
				moving = true;
				System.out.println("press"+press);
				if(!press)startGame = true;
			}

			if (key == KeyEvent.VK_RIGHT && backX >= -6500) {
				isRight = true;
				moving = true;
				if(!press)startGame = true;
			}
			//		if(key == KeyEvent.VL_UP && )
			if(key == KeyEvent.VK_UP && choosen.getY() >= 70) {
				isUp = true;
				if(!press)startGame = true;
			}


			if (key == KeyEvent.VK_DOWN && choosen.getY() <=400) {
				isDown = true;
				if(!press)startGame = true;
			}

			if(key==KeyEvent.VK_SPACE) {
				if(choosen.getWeapon() instanceof RangedWeapon) {
					if(cooldown>50) {
						weaponList.add(new Weapon(choosen.getX() + 200, choosen.getY() + 80, choosen.getWeapon().getWidth(), choosen.getWeapon().getHeight(), choosen.getWeapon().getAtt(), choosen.getWeapon().getI()));
						cooldown = 0;
						attack++;
						run=1;

					}
				}
				else if(choosen.getWeapon() instanceof HoningWeapon) {
					if(cooldown>100) {
						weaponList.add(new Weapon(choosen.getX() + 200, choosen.getY() + 80, choosen.getWeapon().getWidth(), choosen.getWeapon().getHeight(), choosen.getWeapon().getAtt(), choosen.getWeapon().getI()));
						cooldown = 0;
						attack++;
						run=1;

					}
				}
				if(!press)startGame = true;
			}
			break;
		case 'H':
			if(key==KeyEvent.VK_RIGHT)
				screen = 'G';
			break;
		case 'L':
			if(key == KeyEvent.VK_A)
				reset(false);
			if(key==KeyEvent.VK_D)
				reset(true);
			break;
		case 'W':
			if(key == KeyEvent.VK_A)
				reset(false);
			if(key==KeyEvent.VK_D)
				reset(true);
			break;
		case 'A':
			if(key == KeyEvent.VK_F2)
				screen = 'D';
		}
		//System.out.println(backX);
	}

	// use substring string.substring(0,i) -- count increase variable 
	public void movement() {
		if(backX <= -6500)
			isRight=false;
		if(backX >=-1)
			isLeft = false; 
		if(choosen.getY() <= 70)
			isUp = false; 
		if(choosen.getY() >=400)
			isDown = false; 
		if(isLeft)	backX +=1;
		if(isRight) backX -=1;
		if(isUp) choosen.setDy(-2);
		if(isDown) choosen.setDy(2);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(isRight);
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) { 

		case KeyEvent.VK_LEFT: isLeft = false; moving = false;break;
		case KeyEvent.VK_RIGHT: isRight = false;moving = false; break;
		case KeyEvent.VK_UP: isUp = false; break; 
		case KeyEvent.VK_DOWN: isDown = false; break; 

		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		cursorX = e.getX();
		cursorY = e.getY();
		if(e.getY() >= 375)
			selectUp = false;
		else selectUp = true;


		//System.out.println("x: " + e.getX() + "y: " + e.getY());
	}
}


