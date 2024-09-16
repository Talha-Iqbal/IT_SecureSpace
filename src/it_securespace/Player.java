/*
 * Name:Talha Iqbal
 * Date: 2020-06-11
 */
package it_securespace;

//Libraries
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Player extends Character
{
	// ********************************************* Instance Variables *********************************************
	private int coins, keys, bullets, explosives;
	private int result;
	
	
	
	
	// ********************************************* Constructors *********************************************
	public Player ()
	{
		super (7, 5, 250, 450);
		//Basic initialisation of instance variables
		coins = 0;
		keys = 0;
		bullets = 0;
		explosives = 0;
		result = 0;
		
		direction = "F";
		
		
		try
		{
			character = ImageIO.read(new File ("Images/Character/characterF.png")); // load file into Image object
			
			moves[0] = ImageIO.read(new File ("Images/Character/characterF.png")); // load file into Image object
			moves[1] = ImageIO.read(new File ("Images/Character/characterWF1.png")); // load file into Image object
			moves[2] = ImageIO.read(new File ("Images/Character/characterWF2.png")); // load file into Image object
			moves[3] = ImageIO.read(new File ("Images/Character/characterB.png")); // load file into Image object
			moves[4] = ImageIO.read(new File ("Images/Character/characterWB1.png")); // load file into Image object
			moves[5] = ImageIO.read(new File ("Images/Character/characterWB2.png")); // load file into Image object
			moves[6] = ImageIO.read(new File ("Images/Character/characterL.png")); // load file into Image object
			moves[7] = ImageIO.read(new File ("Images/Character/characterWL1.png")); // load file into Image object
			moves[8] = ImageIO.read(new File ("Images/Character/characterWL2.png")); // load file into Image object
			moves[9] = ImageIO.read(new File ("Images/Character/characterR.png")); // load file into Image object
			moves[10] = ImageIO.read(new File ("Images/Character/characterWR1.png")); // load file into Image object
			moves[11] = ImageIO.read(new File ("Images/Character/characterWR2.png")); // load file into Image object
		}
		catch (IOException ex)
		{
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	public int getCoins ()
	{
		return coins;
	}
	
	public int getKeys ()
	{
		return keys;
	}
	
	public int getBullets ()
	{
		return bullets;
	}
	
	public int getExplosives ()
	{
		return explosives;
	}
	
	public String getDirection ()
	{
		return direction;
	}
	
	public int result ()
	{
		if (lives < 0)
			return -1;
			
		return result;
	}
	
	
	
	
	// ********************************************* Setters *********************************************	
	public void setx (int x)
	{
		xpos = x;
	}
	
	public void sety (int y)
	{
		ypos = y;
	}

	
	// ----------- Add/Remove Elements -----------
	public void addCoin ()
	{
		coins ++;
	}
	
	public void removeCoins (int num)
	{
		coins -= num;
	}
	
	public void addKeys ()
	{
		keys ++;
	}
	
	public void removeKeys ()
	{
		keys --;
	}
	
	
	public void addBullet ()
	{
		bullets ++;
	}
	
	public void removeBullet ()
	{
		bullets --;
	}
	
	
	public void addExplosive ()
	{
		explosives ++;
	}
	
	public void removeExplosive ()
	{
		explosives --;
	}
	
	
	
	
	// ----------- Move Player -----------
	public void moveUp (Map map, Items items, Enemies enemiesMap)
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
		
		//Locked door infront
		if (map.getElement(xpos+5, ypos) == 'L' || map.getElement(xpos+45, ypos) == 'L')
		{
			if (keys > 0)//if have keys unlock door
			{
				if (map.getElement(xpos+5, ypos) == 'L')
					map.add(xpos+5, ypos, 'D');
				else
					map.add(xpos+45, ypos, 'D');
				
				keys --;
			}
			else
			{
				while (map.getElement(xpos+5, ypos) == 'L' || map.getElement(xpos+45, ypos) == 'L')
				{
					ypos += 1;
				}
			}
		}
		
		
		//Door infront
		if (map.getElement(xpos+5, ypos) == 'D' || map.getElement(xpos+45, ypos) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		
		//Collectable infront
		else
		if (items.getElement(xpos+5, ypos) == 'C' || items.getElement(xpos+45, ypos) == 'C')
		{
			if (items.getElement(xpos+5, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos);
			}
			if (items.getElement(xpos+45, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'T' || items.getElement(xpos+45, ypos) == 'T')
		{	
			if (items.getElement(xpos+5, ypos) == 'T')
				items.removeTreasure(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'T')
				items.removeTreasure(xpos+45, ypos);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'K' || items.getElement(xpos+45, ypos) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos) == 'K')
				items.removeKey(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'K')
				items.removeKey(xpos+45, ypos);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	public void moveDown (Map map, Items items, Enemies enemiesMap)
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
		
		//Locked door infront
		if (map.getElement(xpos+5, ypos+46) == 'L' || map.getElement(xpos+45, ypos+46) == 'L')
		{
			if (keys > 0)//if have keys unlock door
			{
				if (map.getElement(xpos+5, ypos+46) == 'L')
					map.add(xpos+5, ypos+46, 'D');
				else
					map.add(xpos+45, ypos+46, 'D');
				
				keys --;
			}
			else
			{
				while (map.getElement(xpos+5, ypos+46) == 'L' || map.getElement(xpos+45, ypos+46) == 'L')
				{
					ypos -= 1;
				}
			}
		}
		
		
		//door infront
		if (map.getElement(xpos+5, ypos+46) == 'D' || map.getElement(xpos+45, ypos+46) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		//Collectable Behind
		else
		if (items.getElement(xpos+5, ypos+46) == 'C' || items.getElement(xpos+45, ypos+46) == 'C')
		{
			if (items.getElement(xpos+5, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+46);
			}
			if (items.getElement(xpos+45, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+46);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'T' || items.getElement(xpos+45, ypos+46) == 'T')
		{
			if (items.getElement(xpos+5, ypos+46) == 'T')
				items.removeTreasure(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'T')
				items.removeTreasure(xpos+45, ypos+46);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'K' || items.getElement(xpos+45, ypos+46) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+46) == 'K')
				items.removeKey(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'K')
				items.removeKey(xpos+45, ypos+46);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}

	public void moveLeft (Map map, Items items, Enemies enemiesMap)
	{
		super.moveLeft(map, items, enemiesMap);
		
		//if wall beside
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
		
		//Locked door Beside
		if (map.getElement(xpos+5, ypos+5) == 'L' || map.getElement(xpos+5, ypos+45) == 'L')
		{
			if (keys > 0)//if have keys unlock door
			{
				if (map.getElement(xpos+5, ypos+5) == 'L')
					map.add(xpos+5, ypos+5, 'D');
				else
					map.add(xpos+5, ypos+45, 'D');
				
				keys --;
			}
			else
			{
				while (map.getElement(xpos+5, ypos+5) == 'L' || map.getElement(xpos+5, ypos+45) == 'L')
				{
					xpos += 1;
				}
			}
		}
		
		
		//Collectable Beside
		if (items.getElement(xpos+5, ypos+5) == 'C' || items.getElement(xpos+5, ypos+45) == 'C')
		{
			if (items.getElement(xpos+5, ypos+5) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+5);
			}
			if (items.getElement(xpos+5, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+45);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'T' || items.getElement(xpos+5, ypos+45) == 'T')
		{
			if (items.getElement(xpos+5, ypos+5) == 'T')
				items.removeTreasure(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'T')
				items.removeTreasure(xpos+5, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'K' || items.getElement(xpos+5, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+5) == 'K')
				items.removeKey(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'K')
				items.removeKey(xpos+5, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	public void moveRight (Map map, Items items, Enemies enemiesMap)
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
		
		//Locked door Beside
		if (map.getElement(xpos+45, ypos+5) == 'L' || map.getElement(xpos+45, ypos+45) == 'L')
		{
			if (keys > 0)//if have keys unlock door
			{
				if (map.getElement(xpos+45, ypos+5) == 'L')
					map.add(xpos+45, ypos+5, 'D');
				else
					map.add(xpos+45, ypos+45, 'D');
				
				keys --;
			}
			else
			{
				while (map.getElement(xpos+45, ypos+5) == 'L' || map.getElement(xpos+45, ypos+45) == 'L')
				{
					xpos -= 1;
				}
			}
		}
		
		
		//Collectable Beside
		if (items.getElement(xpos+45, ypos+5) == 'C' || items.getElement(xpos+45, ypos+45) == 'C')
		{
			if (items.getElement(xpos+45, ypos+5) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+5);
			}
			if (items.getElement(xpos+45, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+45);
			}
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'T' || items.getElement(xpos+45, ypos+45) == 'T')
		{
			if (items.getElement(xpos+45, ypos+5) == 'T')
				items.removeTreasure(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'T')
				items.removeTreasure(xpos+45, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'K' || items.getElement(xpos+45, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+45, ypos+5) == 'K')
				items.removeKey(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'K')
				items.removeKey(xpos+45, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	
	public void moveUpLeft(Map map, Items items, Enemies enemiesMap)
	{
		super.moveUpLeft(map, items, enemiesMap);
		
		//Locked door in way
		if (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
			|| map.getElement(xpos + 5, ypos + 5) == 'L' || map.getElement(xpos + 5, ypos + 45) == 'L')
		{
			if (keys > 0)//if have keys; unlock door
			{
				if (map.getElement(xpos+5, ypos) == 'L')
					map.add(xpos+5, ypos, 'D');
				else
				if (map.getElement(xpos+45, ypos) == 'L')
					map.add(xpos+45, ypos, 'D');
				else
				if (map.getElement(xpos+5, ypos+5) == 'L')
					map.add(xpos+5, ypos+5, 'D');
				else
				if (map.getElement(xpos+5, ypos+45) == 'L')
					map.add(xpos+5, ypos+45, 'D');
				
				keys --;
			}
			else
			{
				//Previous position
				int prevX = xpos + (speed / 2 + 1);
				int prevY = ypos + (speed / 2 + 1);
				
				//Move back
				while (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 5, ypos + 5) == 'L' || map.getElement(xpos + 5, ypos + 45) == 'L')
				{
					if (map.getElement(prevX + 5, ypos) == 'L' || map.getElement(prevX + 45, ypos) == 'L')
					{
						ypos += 1;
					}

					if (map.getElement(xpos + 5, prevY + 5) == 'L'|| map.getElement(xpos + 5, prevY + 45) == 'L')
					{
						xpos += 1;
					}
				}
			}
		}
		
		//door in way
		if (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'D'
			|| map.getElement(xpos + 5, ypos + 5) == 'D' || map.getElement(xpos + 5, ypos + 45) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		//Collactables Infront
		if (items.getElement(xpos+5, ypos) == 'C' || items.getElement(xpos+45, ypos) == 'C')//coin
		{
			if (items.getElement(xpos+5, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos);
			}
			if (items.getElement(xpos+45, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'T' || items.getElement(xpos+45, ypos) == 'T')//treasure
		{
			if (items.getElement(xpos+5, ypos) == 'T')
				items.removeTreasure(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'T')
				items.removeTreasure(xpos+45, ypos);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'K' || items.getElement(xpos+45, ypos) == 'K')//key
		{
			keys ++;
			if (items.getElement(xpos+5, ypos) == 'K')
				items.removeKey(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'K')
				items.removeKey(xpos+45, ypos);
		}
		
		
		
		//Collectable Beside
		if (items.getElement(xpos+5, ypos+5) == 'C' || items.getElement(xpos+5, ypos+45) == 'C')
		{
			if (items.getElement(xpos+5, ypos+5) == 'C')
			{
				coins++;
				items.removeCoin(xpos+5, ypos+5);
			}
			if (items.getElement(xpos+5, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos + 5, ypos + 45);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'T' || items.getElement(xpos+5, ypos+45) == 'T')
		{
			if (items.getElement(xpos+5, ypos+5) == 'T')
				items.removeTreasure(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'T')
				items.removeTreasure(xpos+5, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'K' || items.getElement(xpos+5, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+5) == 'K')
				items.removeKey(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'K')
				items.removeKey(xpos+5, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	public void moveUpRight(Map map, Items items, Enemies enemiesMap)
	{
		super.moveUpRight (map, items, enemiesMap);
		
		//Locked door in way
		if (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
			|| map.getElement(xpos + 45, ypos + 5) == 'L' || map.getElement(xpos + 45, ypos + 45) == 'L')
		{
			if (keys > 0)//if have keys; unlock door
			{
				if (map.getElement(xpos+5, ypos) == 'L')
					map.add(xpos+5, ypos, 'D');
				else
				if (map.getElement(xpos+45, ypos) == 'L')
					map.add(xpos+45, ypos, 'D');
				else
				if (map.getElement(xpos+45, ypos+5) == 'L')
					map.add(xpos+45, ypos+5, 'D');
				else
				if (map.getElement(xpos+45, ypos+45) == 'L')
					map.add(xpos+45, ypos+45, 'D');
				
				keys --;
			}
			else
			{
				//Previous position
				int prevX = xpos - (speed / 2 + 1);
				int prevY = ypos + (speed / 2 + 1);
				
				//Move back
				while (map.getElement(xpos + 5, ypos) == 'L' || map.getElement(xpos + 45, ypos) == 'L'
					|| map.getElement(xpos + 45, ypos + 5) == 'L' || map.getElement(xpos + 45, ypos + 45) == 'L')
				{
					if (map.getElement(prevX + 5, ypos) == 'L' || map.getElement(prevX + 45, ypos) == 'L')
					{
						ypos += 1;
					}

					if (map.getElement(xpos + 45, prevY + 5) == 'L' || map.getElement(xpos + 45, prevY + 45) == 'L')
					{
						xpos -= 1;
					}
				}
			}
		}
		
		
		//door in way
		if (map.getElement(xpos + 5, ypos) == 'D' || map.getElement(xpos + 45, ypos) == 'D'
			|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		
		//Collactables Infront
		if (items.getElement(xpos+5, ypos) == 'C' || items.getElement(xpos+45, ypos) == 'C')//coin
		{
			
			if (items.getElement(xpos+5, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos);
			}
			if (items.getElement(xpos+45, ypos) == 'C')
			{
				coins ++;
				items.removeCoin(xpos + 45, ypos);
			}		
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'T' || items.getElement(xpos+45, ypos) == 'T')//treasure
		{
			if (items.getElement(xpos+5, ypos) == 'T')
				items.removeTreasure(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'T')
				items.removeTreasure(xpos+45, ypos);
			
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos) == 'K' || items.getElement(xpos+45, ypos) == 'K')//key
		{
			keys ++;
			if (items.getElement(xpos+5, ypos) == 'K')
				items.removeKey(xpos+5, ypos);
			if (items.getElement(xpos+45, ypos) == 'K')
				items.removeKey(xpos+45, ypos);
		}
		
		
		
		//Collectable Beside
		if (items.getElement(xpos+45, ypos+5) == 'C' || items.getElement(xpos+45, ypos+45) == 'C')
		{
			if (items.getElement(xpos+45, ypos+5) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+5);
			}
			if (items.getElement(xpos+45, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+45);
			}
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'T' || items.getElement(xpos+45, ypos+45) == 'T')
		{
			if (items.getElement(xpos+45, ypos+5) == 'T')
				items.removeTreasure(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'T')
				items.removeTreasure(xpos+45, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'K' || items.getElement(xpos+45, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+45, ypos+5) == 'K')
				items.removeKey(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'K')
				items.removeKey(xpos+45, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	public void moveDownLeft(Map map, Items items, Enemies enemiesMap)
	{
		super.moveDownLeft(map, items, enemiesMap);
		
		
		if (map.getElement(xpos + 5, ypos + 46) == 'D'|| map.getElement(xpos + 45, ypos + 46) == 'D'
			|| map.getElement(xpos + 5, ypos + 5) == 'D'|| map.getElement(xpos + 5, ypos + 45) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		
		//Collectable Behind
		if (items.getElement(xpos+5, ypos+46) == 'C' || items.getElement(xpos+45, ypos+46) == 'C')
		{
			if (items.getElement(xpos+5, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos + 5, ypos + 46);
			}
			if (items.getElement(xpos+45, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+46);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'T' || items.getElement(xpos+45, ypos+46) == 'T')
		{
			if (items.getElement(xpos+5, ypos+46) == 'T')
				items.removeTreasure(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'T')
				items.removeTreasure(xpos+45, ypos+46);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'K' || items.getElement(xpos+45, ypos+46) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+46) == 'K')
				items.removeKey(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'K')
				items.removeKey(xpos+45, ypos+46);
		}
		
		
		//Collectable Beside
		if (items.getElement(xpos+5, ypos+5) == 'C' || items.getElement(xpos+5, ypos+45) == 'C')
		{
			if (items.getElement(xpos+5, ypos+5) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+5);
			}
			if (items.getElement(xpos+5, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+45);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'T' || items.getElement(xpos+5, ypos+45) == 'T')
		{
			if (items.getElement(xpos+5, ypos+5) == 'T')
				items.removeTreasure(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'T')
				items.removeTreasure(xpos+5, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+5) == 'K' || items.getElement(xpos+5, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+5) == 'K')
				items.removeKey(xpos+5, ypos+5);
			if (items.getElement(xpos+5, ypos+45) == 'K')
				items.removeKey(xpos+5, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
	public void moveDownRight(Map map, Items items, Enemies enemiesMap)
	{
		super.moveDownRight(map, items, enemiesMap);
		
		if (map.getElement(xpos + 5, ypos + 46) == 'D'|| map.getElement(xpos + 45, ypos + 46) == 'D'
			|| map.getElement(xpos + 45, ypos + 5) == 'D' || map.getElement(xpos + 45, ypos + 45) == 'D')
		{
			map.levelChange(items, enemiesMap, this);
		}
		
		
		//Collectable Behind
		if (items.getElement(xpos+5, ypos+46) == 'C' || items.getElement(xpos+45, ypos+46) == 'C')
		{
			
			if (items.getElement(xpos+5, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+5, ypos+46);
			}
			if (items.getElement(xpos+45, ypos+46) == 'C')
			{
				coins ++;
				items.removeCoin(xpos + 45, ypos + 46);
			}
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'T' || items.getElement(xpos+45, ypos+46) == 'T')
		{
			if (items.getElement(xpos+5, ypos+46) == 'T')
				items.removeTreasure(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'T')
				items.removeTreasure(xpos+45, ypos+46);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+5, ypos+46) == 'K' || items.getElement(xpos+45, ypos+46) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+5, ypos+46) == 'K')
				items.removeKey(xpos+5, ypos+46);
			if (items.getElement(xpos+45, ypos+46) == 'K')
				items.removeKey(xpos+45, ypos+46);
		}
		
		
		
		
		//Collectable Beside
		if (items.getElement(xpos+45, ypos+5) == 'C' || items.getElement(xpos+45, ypos+45) == 'C')
		{
			if (items.getElement(xpos+45, ypos+5) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+5);
			}
			if (items.getElement(xpos+45, ypos+45) == 'C')
			{
				coins ++;
				items.removeCoin(xpos+45, ypos+45);
			}
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'T' || items.getElement(xpos+45, ypos+45) == 'T')
		{
			if (items.getElement(xpos+45, ypos+5) == 'T')
				items.removeTreasure(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'T')
				items.removeTreasure(xpos+45, ypos+45);
			
			result = 1;
		}
		
		else
		if (items.getElement(xpos+45, ypos+5) == 'K' || items.getElement(xpos+45, ypos+45) == 'K')
		{
			keys ++;
			if (items.getElement(xpos+45, ypos+5) == 'K')
				items.removeKey(xpos+45, ypos+5);
			if (items.getElement(xpos+45, ypos+45) == 'K')
				items.removeKey(xpos+45, ypos+45);
		}
		
		if (map.getElement(xpos, ypos) == 'S')
			map.atShop(true);
		else
			map.atShop(false);
	}
	
}





