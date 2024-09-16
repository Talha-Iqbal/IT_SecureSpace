/*
 * Name:Talha Iqbal
 * Date: 2020-06-07
 * Controls: Arrow keys for movement, z for bomb, x to shoot
 * Known Bugs: running into the edges of walls or into locked doors diagonally causes game to crash. if two non linear monsters move on the same location and the player
 *		leaves the room they merge into oneor game crashes. placing a bomb and leaving causes the bomb to spawn in the next map
 */
package it_securespace;

//Libraries
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;


public class IT_SecureSpace extends JFrame implements MouseListener, KeyListener
{
	// ********************************************* Instance Variables *********************************************
	//Map components
	private Map map;
	
	
	//Item components
	private Items items;
	
	
	//Player components
	private int result;
	private Player player;
	private boolean up = false, down = false, left = false, right = false;//Movemenet
	private Timer resultCheckTimer;
	
	
	//Monster components
	private ArrayList <Monster> monsters;
	
	//Enemy components
	private Enemies enemiesMap;
	private Timer monsterMovementTimer;//move monsters
	private Timer monsterMapTimer;//Update current monsters map
	
	
	//Bullets
	private ArrayList <Bullet> bullets;
	private Timer bulletMovementTimer;//move bullets
	
	//Explosives
	private ArrayList <Explosive> explosives;
	
	
	//Others
	private Timer repaintTimer;//repaint
	int currentMap = 1;
	
	
	
	
	
	// ********************************************* Constructor *********************************************

    /**
     *
     * @throws FileNotFoundException
     */
	public IT_SecureSpace () throws FileNotFoundException// ----------- Constructor Method -----------
	{		
		//Keyboard input setup
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		//Initalise Objects
		bullets = new ArrayList<>();
		
		explosives = new ArrayList<>();
		
		map = new Map ();
		items = new Items ();
		player = new Player ();
		enemiesMap = new Enemies ();
		
		monsters = new ArrayList <> ();
		monsters = enemiesMap.getMonsters();
		
		
		bulletMovementTimer = new Timer (5, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			if (!bullets.isEmpty())
			{
				for (int i = 0; i < bullets.size(); i ++)
				{
					bullets.get(i).moveBullet (map, items, bullets, monsters);
				}
			}
		}
		});
		
		
		monsterMovementTimer = new Timer (20, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			if (monsters.size() > 0 && currentMap == map.getCurrentMapNum())
			{
				for (int i = 0; i < monsters.size(); i++)
					monsters.get(i).move(map, items, player, enemiesMap, monsters);
			}
			
		}
		});
		monsterMapTimer = new Timer (10, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			if (currentMap != map.getCurrentMapNum())
			{
				if (currentMap < map.getCurrentMapNum())
				{
					enemiesMap.nextLevel();
				}
				else
				{
					enemiesMap.prevLevel();
				}
				
				monsters = enemiesMap.getMonsters();
				currentMap = map.getCurrentMapNum();
			}
			
			
			
		}
		});
		
		resultCheckTimer = new Timer (30, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			result = player.result();
		}
		});
		repaintTimer = new Timer (10, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			repaint();
		}
	});
		
		//start timers
		bulletMovementTimer.start();
		resultCheckTimer.start();
		monsterMovementTimer.start();
		monsterMapTimer.start();
		repaintTimer.start();
		
		
		
		//Drawing area
		DrawArea board = new DrawArea(550, 680); // Area for Map to be displayed
		
		board.addMouseListener(this);//add mouse listener
		board.addKeyListener(this);//add mouse listener
		
		setContentPane(board);//set Content pane
		
		pack();//Pack components
		
		//Window Properties
		setTitle("Secure Space");
		setSize(1650, 1000);
		setBackground (Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// Center window
	}

	
	
	
	// ********************************************* DrawArea *********************************************
	class DrawArea extends JPanel 
	{

		public DrawArea(int width, int height) 
		{
			this.setSize(new Dimension(width, height)); // size
		}

		public void paintComponent(Graphics g) 
		{
			if (result == 0)
			{
				map.print(g, player);
				items.print(g);
				player.print(g);

				if (monsters.size() > 0)
				{
					for (int i = 0; i < monsters.size(); i++)
					{
						monsters.get(i).print(g);
					}
				}

				if (!bullets.isEmpty())
				{
					for (int i = 0; i < bullets.size(); i++)
					{
						bullets.get(i).print(g);
					}
				}

				if (!explosives.isEmpty())
					for (int i = 0; i < explosives.size(); i++)
						explosives.get(i).print(g);
			}
			else
			if (result < 0)
			{
				g.setColor(Color.red);
				g.fillRect(0, 0, 2000, 2000);
				g.setColor(Color.black);
				g.setFont(new Font("TimesRoman", Font.BOLD, 40));
				g.drawString("HaHa, You Lose.", 700, 450);
			}
			else
			if (result > 0)
			{
				g.setColor(Color.green);
				g.fillRect(0, 0, 2000, 2000);
				g.setColor(Color.black);
				g.setFont(new Font("TimesRoman", Font.BOLD, 40));
				g.drawString("You WIN", 700, 450);
			}
			
			
			
		}
	}
	
	
	
	
	// ********************************************* Mouse Listener Methods *********************************************
	public void mouseClicked(MouseEvent e) // ----------- mouseClicked Method -----------
	{
		if (map.getatShop())
		{
			//Coordinates
			int x = e.getX();
			int y = e.getY();

			if (x >= 300 && x <= 340) // buttons x coordinate
			{
				if (y >= 580 && y <= 600)//buy bullets
				{
					if (player.getCoins() >= 2)
					{
						player.addBullet();
						player.removeCoins(2);
					}
				}

				if (y >= 610 && y <= 630)//buy Explosives
				{
					if (player.getCoins() >= 3)
					{
						player.addExplosive();
						player.removeCoins(3);
					}
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) // ----------- mousePressed Method -----------
	{
		
	}
	
	public void mouseReleased(MouseEvent e) // ----------- mouseReleased Method -----------
	{
		
	}

	public void mouseEntered(MouseEvent e) // ----------- mouseEntered Method -----------
	{
		
	}

	public void mouseExited(MouseEvent e) // ----------- mouseExited Method -----------
	{
		
	}
	
	
	// ********************************************* Key Listener Methods *********************************************
	public void keyTyped(KeyEvent e) // ----------- keyTyped Method -----------
	{
		
	}

	public void keyPressed(KeyEvent e) // ----------- keyPressed Method -----------
	{
		//Decleration of variables
		int key = e.getKeyCode();
		
		//Find what keys are pressed
		if (key == KeyEvent.VK_UP)
			up = true;
		
		if (key == KeyEvent.VK_DOWN)
			down = true;
		
		if (key == KeyEvent.VK_LEFT)
			left = true;
		
		if (key == KeyEvent.VK_RIGHT)
			right = true;
		
		
		//Move accourding to keys
		//Diagonal
		if (up && left)
			player.moveUpLeft(map, items, enemiesMap);
		else
		if (up && right)
			player.moveUpRight(map, items, enemiesMap);
		else
		if (down && left)
			player.moveDownLeft(map, items, enemiesMap);
		else
		if (down && right)
			player.moveDownRight(map, items, enemiesMap);
		else
		if (up)
			player.moveUp(map, items, enemiesMap);
		else
		if (down)
			player.moveDown(map, items, enemiesMap);
		else
		if (left)
			player.moveLeft(map, items, enemiesMap);
		else
		if (right)
			player.moveRight(map, items, enemiesMap);
			
			
	}

	public void keyReleased(KeyEvent e)// ----------- keyReleased Method -----------
	{
		//Decleration of variables
		int key = e.getKeyCode();

		//Find what keys are Released
		if (key == KeyEvent.VK_UP)
		{
			player.up();
			up = false;
		}

		if (key == KeyEvent.VK_DOWN)
		{
			player.down();
			down = false;
		}

		if (key == KeyEvent.VK_LEFT)
		{
			player.left();
			left = false;
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			player.right();
			right = false;
		}	

		if (key == KeyEvent.VK_X)
		{
			if (player.getBullets() > 0)
			{
				Bullet bullet = new Bullet(player.getDirection(), player.getx(), player.gety());
				bullets.add(bullet);
				player.removeBullet ();
			}
		}
		if (key == KeyEvent.VK_Z)
		{
			if (player.getExplosives() > 0)
			{
				Explosive explosive = new Explosive(player.getx(), player.gety(), items, player, monsters, explosives);
				explosives.add(explosive);
				player.removeExplosive();
			}
		}
		
	}
	
	
	
	
	// ********************************************* ActionListener Methods *********************************************
		
	
	
	
	// ********************************************* Main Method *********************************************
	public static void main(String[] args) throws FileNotFoundException // ----------- Main Method -----------
	{
		IT_SecureSpace window = new IT_SecureSpace();
		
		window.setVisible(true);
		window.setResizable(true);
	}
}


