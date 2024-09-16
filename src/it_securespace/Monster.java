/*
 * Name:Talha Iqbal
 * Date: 2020-06-14
 */
package it_securespace;

//Libraries
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Monster extends Character
{
	// ********************************************* Instance Variables *********************************************
	private int walk;
	private int range;
	
	
	
	
	// ********************************************* Constructors *********************************************
	public Monster(int lives, int x, int y, int walk)
	{
		super(1, 3, x, y);
		this.walk = walk;
		
		if (walk == 1)
			direction = "F";
		else
		if (walk == 2)
			direction = "L";
		else
		if (walk == 3)
		{
			direction = "F";
			range = (int) (Math.random() * 500 + 300);
		}
			
		
		//Walk; 1: up-down, 2: right-left, 3:Follow
		
		
		try
		{	
			if (walk == 0)
			{
				speed = 4;
				lives = 9;
				range = 1000;
				
				character = ImageIO.read(new File("Images/Boss/bossL.png")); // load file into Image object

				moves[0] = ImageIO.read(new File("Images/Boss/bossF.png")); // load file into Image object
				moves[1] = ImageIO.read(new File("Images/Boss/bossWF1.png")); // load file into Image object
				moves[2] = ImageIO.read(new File("Images/Boss/bossWF2.png")); // load file into Image object
				moves[3] = ImageIO.read(new File("Images/Boss/bossB.png")); // load file into Image object
				moves[4] = ImageIO.read(new File("Images/Boss/bossWB1.png")); // load file into Image object
				moves[5] = ImageIO.read(new File("Images/Boss/bossWB2.png")); // load file into Image object
				moves[6] = ImageIO.read(new File("Images/Boss/bossL.png")); // load file into Image object
				moves[7] = ImageIO.read(new File("Images/Boss/bossWL1.png")); // load file into Image object
				moves[8] = ImageIO.read(new File("Images/Boss/bossWL2.png")); // load file into Image object
				moves[9] = ImageIO.read(new File("Images/Boss/bossR.png")); // load file into Image object
				moves[10] = ImageIO.read(new File("Images/Boss/bossWR1.png")); // load file into Image object
				moves[11] = ImageIO.read(new File("Images/Boss/bossWR2.png")); // load file into Image object
			}
			else
			{
				if (this.walk == 1 && "F".equals(direction))
					character = ImageIO.read(new File("Images/Monster/monsterF.png")); // load file into Image object
				else if (this.walk == 1 && "B".equals(direction))
					character = ImageIO.read(new File("Images/Monster/monsterB.png")); // load file into Image object
				else if (this.walk == 2 && "L".equals(direction))
					character = ImageIO.read(new File("Images/Monster/monsterL.png")); // load file into Image object
				else if (this.walk == 2 && "R".equals(direction))
					character = ImageIO.read(new File("Images/Monster/monsterR.png")); // load file into Image object
				
				moves[0] = ImageIO.read(new File("Images/Monster/monsterF.png")); // load file into Image object
				moves[1] = ImageIO.read(new File("Images/Monster/monsterWF1.png")); // load file into Image object
				moves[2] = ImageIO.read(new File("Images/Monster/monsterWF2.png")); // load file into Image object
				moves[3] = ImageIO.read(new File("Images/Monster/monsterB.png")); // load file into Image object
				moves[4] = ImageIO.read(new File("Images/Monster/monsterWB1.png")); // load file into Image object
				moves[5] = ImageIO.read(new File("Images/Monster/monsterWB2.png")); // load file into Image object
				moves[6] = ImageIO.read(new File("Images/Monster/monsterL.png")); // load file into Image object
				moves[7] = ImageIO.read(new File("Images/Monster/monsterWL1.png")); // load file into Image object
				moves[8] = ImageIO.read(new File("Images/Monster/monsterWL2.png")); // load file into Image object
				moves[9] = ImageIO.read(new File("Images/Monster/monsterR.png")); // load file into Image object
				moves[10] = ImageIO.read(new File("Images/Monster/monsterWR1.png")); // load file into Image object
				moves[11] = ImageIO.read(new File("Images/Monster/monsterWR2.png")); // load file into Image object
			}
			
		}
		catch (IOException ex)
		{
			Logger.getLogger(Monster.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	public int getWalk ()
	{
		return walk;
	}
	
	
	
	// ********************************************* Settters *********************************************
	public void move (Map map, Items items, Player player, Enemies enemiesMap, ArrayList <Monster> monsters)
	{
		int x = player.getx()+25;
		int y = player.gety()+25;
		
		if (walk == 1)
			walk1 (map, items, enemiesMap);
		else
		if (walk == 2)
			walk2 (map, items, enemiesMap);
		else
		if (walk == 3 || walk == 0)
			walk3 (map, items, enemiesMap, x, y);
		
		lifeCheck(player, monsters);
		enemiesMap.save(monsters);
	}
	
	
	public void walk1 (Map map, Items items, Enemies enemiesMap)
	{
		if (direction.charAt(0) == 'F')
		{
			super.moveUp(map, items, enemiesMap);

			//wall infront
			if (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
				|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y')
			{
				//move back
				while (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
					|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y')
				{
					ypos += 1;
				}
				direction = "B";
			}
			
			//Doors infront
			if (map.getElement(xpos+5, ypos) == 'L' || map.getElement(xpos+45, ypos) == 'L'
				|| map.getElement(xpos+5, ypos) == 'D' || map.getElement(xpos+45, ypos) == 'D')
			{
				//move back
				while (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'D')
				{
					ypos += 1;
				}
				direction = "B";
			}
			
			//Monster infront
			if (enemiesMap.getElement2(ypos/50-1, xpos/50) == '1' || enemiesMap.getElement2(ypos/50-1, xpos/50) == '2'
				|| enemiesMap.getElement2(ypos/50-1, xpos/50) == '0')
			{
				//move back
				while (enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '1' || enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '2'
					|| enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '0')
				{
					ypos += 1;
				}
				direction = "B";
			}
		}
		
		else
		{
			super.moveDown(map, items, enemiesMap);
			
			//if wall behind
			if (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
				|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y')
			{
				//move Up
				while (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
					|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y')
				{
					ypos -= 1;
				}
				
				direction = "F";
			}
			
			
			//Doors behind
			if (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
				|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L')
			{
				//move Up
				while (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
					|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L')
				{
					ypos -= 1;
				}

				direction = "F";
			}
			
			//Monster behind
			if (enemiesMap.getElement2(ypos/50+1, xpos/50) == '1' || enemiesMap.getElement2(ypos/50+1, xpos/50) == '2'
				|| enemiesMap.getElement2(ypos/50+1, xpos/50) == '0')
			{
				//move Up
				while (enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '1' || enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '2'
					|| enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '0')
				{
					ypos -= 1;
				}

				direction = "F";
			}
			
			
		}
		
	}
	
	public void walk2 (Map map, Items items, Enemies enemiesMap)
	{
		if (direction.charAt(0) == 'L')
		{
			//Monster beside
			if (enemiesMap.getElement2(ypos / 50, xpos / 50 -1) == '1' || enemiesMap.getElement2(ypos / 50, xpos / 50 -1) == '2'
				|| enemiesMap.getElement2(ypos / 50, xpos / 50 -1) == '0')
			{
				direction = "R";
			}
			else
			{
				super.moveLeft(map, items, enemiesMap);

				//if wall beside
				if (map.getElement(xpos, ypos) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
					|| map.getElement(xpos, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
				{
					//move right
					while (map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
						|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
					{
						xpos += 1;
					}

					direction = "R";
				}

				//Door beside
				if (map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
					|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
				{
					//move right
					while (map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
						|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
					{
						xpos += 1;
					}

					direction = "R";
				}
			}
		}
		
		else
		{
			
			//Monster Beside
			if (enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '1' || enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '2'
				|| enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '0')
			{
				direction = "L";
			}
			else
			{
				super.moveRight(map, items, enemiesMap);

				//If wall beside
				if (map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
					|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
				{
					//move left
					while (map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
						|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
					{
						xpos -= 1;
					}

					direction = "L";
				}

				//Door beside
				if (map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
					|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
				{
					//move left
					while (map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
						|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
					{
						xpos -= 1;
					}

					direction = "L";
				}

			}
		}
		
	}
	
	public void walk3 (Map map, Items items, Enemies enemiesMap, int x1, int y1)
	{
		//Midpoint of monster
		int x2 = xpos+25;
		int y2 = ypos+25;
		
		
		if ( Math.abs(Math.sqrt(  Math.pow(x2-x1 , 2) + Math.pow(y2-y1, 2)  )) <= range)
		{
			//Straight line movement
			if (y2 == y1 && x2 > x1)//person to the left
			{
				//Monster beside
				if ( ! (enemiesMap.getElement2(ypos / 50, xpos / 50 - 1) == '1' || enemiesMap.getElement2(ypos / 50, xpos / 50 - 1) == '2'
					|| enemiesMap.getElement2(ypos / 50, xpos / 50 - 1) == '0'))
				{
					super.moveLeft(map, items, enemiesMap);

					//wall beside
					if (map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
						|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
					{
						//move right
						while (map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
							|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
						{
							xpos += 1;
						}
					}

					//Door beside
					if (map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
						|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
					{
						//move right
						while (map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
							|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
						{
							xpos += 1;
						}
					}
				}
				
			}
			
			else
			if (y2 == y1 && x2 < x1)// person to the right
			{
				//Monster Beside
				if ( ! (enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '1' || enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '2'
					|| enemiesMap.getElement2(ypos / 50, xpos / 50 + 1) == '0'))
				{
					super.moveRight(map, items, enemiesMap);

					//If wall beside
					if (map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
						|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
					{
						//move left
						while (map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
							|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
						{
							xpos -= 1;
						}
					}

					//Door beside
					if (map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
						|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
					{
						//move left
						while (map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
							|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
						{
							xpos -= 1;
						}
					}
				}
			}
			
			else
			if (x2 == x1 && y2 > y1)//person above
			{
				//Monster infront
				if ( ! (enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '1' || enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '2'
					|| enemiesMap.getElement2(ypos / 50 - 1, xpos / 50) == '0'))
				{
					super.moveUp(map, items, enemiesMap);

					//wall infront
					if (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
						|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y')
					{
						//move back
						while (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
							|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y')
						{
							ypos += 1;
						}
					}

					//Doors infront
					if (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
						|| map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'D')
					{
						//move back
						while (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
							|| map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'D')
						{
							ypos += 1;
						}
					}
				}
			}
			
			else
			if (x2 == x1 && y2 < y1)//person below
			{
				//Monster behind
				if ( ! (enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '1' || enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '2'
					|| enemiesMap.getElement2(ypos / 50 + 1, xpos / 50) == '0'))
				{
					super.moveDown(map, items, enemiesMap);

					//if wall behind
					if (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
						|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y')
					{
						//move Up
						while (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
							|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y')
						{
							ypos -= 1;
						}
					}

					//Doors behind
					if (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
						|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L')
					{
						//move Up
						while (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
							|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L')
						{
							ypos -= 1;
						}
					}
				}
			}
			
			
			else
			if (x2 < x1 && y2 > y1)//1st quadrant - forward right walk
			{
				super.moveUpRight(map, items, enemiesMap);

				//Door blocking movement
				if (map.getElement(xpos + 5, ypos) == 'D' || items.getElement(xpos + 5, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos) == 'D' || items.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos + 5) == 'D' || items.getElement(xpos + 45, ypos + 5) == 'L'
					|| map.getElement(xpos + 45, ypos + 45) == 'D' || items.getElement(xpos + 45, ypos + 45) == 'L')
				{
					//Previous position
					int prevX = xpos - (speed / 2 + 1);
					int prevY = ypos + (speed / 2 + 1);

					//blocking movement
					while (map.getElement(xpos + 5, ypos) == 'D' || items.getElement(xpos + 5, ypos) == 'L'
						|| map.getElement(xpos + 45, ypos) == 'D' || items.getElement(xpos + 45, ypos) == 'L'
						|| map.getElement(xpos + 45, ypos + 5) == 'D' || items.getElement(xpos + 45, ypos + 5) == 'L'
						|| map.getElement(xpos + 45, ypos + 45) == 'D' || items.getElement(xpos + 45, ypos + 45) == 'L')
					{
						if (map.getElement(prevX + 5, ypos) == 'D' || items.getElement(prevX + 5, ypos) == 'L'
							|| map.getElement(prevX + 45, ypos) == 'D' || items.getElement(prevX + 45, ypos) == 'L')
						{
							ypos += 1;
						}

						if (map.getElement(xpos + 45, prevY + 5) == 'D' || items.getElement(xpos + 45, prevY + 5) == 'L'
							|| map.getElement(xpos + 45, prevY + 45) == 'D' || items.getElement(xpos + 45, prevY + 45) == 'L')
						{
							xpos -= 1;
						}
					}
				}
			}
			
			else
			if (x2 > x1 && y2 > y1)//2nd quadrant - forward left walk
			{
				super.moveUpLeft(map, items, enemiesMap);

				//Doors blocking movement
				if (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 5, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
					|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
				{
					//Previous position
					int prevX = xpos + (speed / 2 + 1);
					int prevY = ypos + (speed / 2 + 1);

					//blocking movement
					while (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 5, ypos) == 'L'
						|| map.getElement(xpos + 45, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'L'
						|| map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 5) == 'L'
						|| map.getElement(xpos + 5, ypos + 45) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'L')
					{
						if (map.getElement(prevX + 5, ypos) == 'D' || map.getElement(prevX + 5, ypos) == 'L'
							|| map.getElement(prevX + 45, ypos) == 'D' || map.getElement(prevX + 45, ypos) == 'L')
						{
							ypos += 1;
						}

						if (map.getElement(xpos + 5, prevY + 5) == 'D' || map.getElement(xpos + 5, prevY + 5) == 'L'
							|| map.getElement(xpos + 5, prevY + 45) == 'D' || map.getElement(xpos + 5, prevY + 45) == 'L')
						{
							xpos += 1;
						}
					}
				}
			}
			
			else
			if (x2 > x1 && y2 < y1)//3rd quadrant - backward left walk
			{
				super.moveDownLeft(map, items, enemiesMap);

				//doors blocking movement
				if (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 5, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
					|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
				{
					//Previous position
					int prevX = xpos - (speed / 2 + 1);
					int prevY = ypos + (speed / 2 + 1);

					//blocking movement
					while (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 5, ypos) == 'L'
						|| map.getElement(xpos + 45, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'L'
						|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
						|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
					{
						if (map.getElement(prevX + 5, ypos) == 'D' || map.getElement(prevX + 5, ypos) == 'L'
							|| map.getElement(prevX + 45, ypos) == 'D' || map.getElement(prevX + 45, ypos) == 'L')
						{
							ypos -= 1;
						}

						if (map.getElement(xpos + 45, prevY + 5) == 'D' || map.getElement(xpos + 45, prevY + 5) == 'L'
							|| map.getElement(xpos + 45, prevY + 45) == 'D' || map.getElement(xpos + 45, prevY + 45) == 'L')
						{
							xpos += 1;
						}
					}
			}
			
			else
			if (x2 < x1 && y2 < y1)//4th quadrant - backward right walk
			{
				
					super.moveDownRight(map, items, enemiesMap);

					//Door blocking movement
					if (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
						|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L'
						|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
						|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
					{
						//Previous position
						int prevX = xpos - (speed / 2 + 1);
						int prevY = ypos - (speed / 2 + 1);

						//blocking movement
						while (map.getElement(xpos + 5, ypos + 46) == 'D' || map.getElement(xpos + 5, ypos + 46) == 'L'
							|| map.getElement(xpos + 45, ypos + 46) == 'D' || map.getElement(xpos + 45, ypos + 46) == 'L'
							|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 5) == 'L'
							|| map.getElement(xpos + 45, ypos + 45) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'L')
						{
							if (map.getElement(prevX + 5, ypos + 46) == 'D' || map.getElement(prevX + 5, ypos + 46) == 'L'
								|| map.getElement(prevX + 45, ypos + 46) == 'D' || map.getElement(prevX + 45, ypos + 46) == 'L')
							{
								ypos -= 1;
							}

							if (map.getElement(xpos + 45, prevY + 5) == 'D' || map.getElement(xpos + 45, prevY + 5) == 'L'
								|| map.getElement(xpos + 45, prevY + 45) == 'D' || map.getElement(xpos + 45, prevY + 45) == 'L')
							{
								xpos -= 1;
							}
						}
					}
				}
			}
		}
	}
	
	
	public void lifeCheck (Player player, ArrayList <Monster> monsters)
	{
		if (xpos+2 >= player.getx() && xpos+2 <= player.getx()+50 && ypos+2 >= player.gety() && ypos+2 <= player.gety()+50)//topleft corner
		{
			lives --;
			player.removeLife();
		}
		else
		if (xpos+48 >= player.getx() && xpos+48 <= player.getx()+50 && ypos+2 >= player.gety() && ypos+2 <= player.gety()+50)//topright corner
		{
			lives --;
			player.removeLife();
		}
		else
		if (xpos+2 >= player.getx() && xpos+2 <= player.getx()+50 && ypos+48 >= player.gety() && ypos+48 <= player.gety()+50)//bottomleft corner
		{
			lives --;
			player.removeLife();	
		}
		else
		if (xpos+48 >= player.getx() && xpos+48 <= player.getx()+50 && ypos+48 >= player.gety() && ypos+48 <= player.gety()+50)//bottomright corner
		{
			lives --;
			player.removeLife();	
		}
		
		if (lives <= 0)
		{
			monsters.remove(this);
		}
	}
	
	
	public void lifeCheck (ArrayList <Monster> monsters)
	{
		if (lives <= 0)
		{
			monsters.remove(this);
		}
	}
	
	
	public void removeLife (ArrayList <Monster> monsters)
	{
		lives --;
		lifeCheck (monsters);
	}
	
}


