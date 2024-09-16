/*
 * Name:Talha Iqbal
 * Date: 2020-06-13
 */
package it_securespace;

//Libraries
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Character
{
	// ********************************************* Instance Variables *********************************************
	protected int speed, xpos, ypos, steps;
	protected String direction;
	protected int lives;
	protected Image[] moves;
	protected Image character;
	
	
	
	
	// ********************************************* Constructors *********************************************
	public Character (int speed, int lives, int x, int y)
	{
		//Basic initialisation of instance variables
		steps = 0;
		
		this.lives = lives;
		
		this.speed = speed;
		this.direction = direction;
		xpos = x;
		ypos = y;
		
		moves = new Image [12];
	}
	
	
	
	
	// ********************************************* Graphics *********************************************
	public void print(Graphics g) // displays the character on the screen
	{
		g.drawImage(character, xpos, ypos, null);
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	public int getLives()
	{
		return lives;
	}

	public int getx()
	{
		return xpos;
	}

	public int gety()
	{
		return ypos;
	}
	
	
	
	
	// ********************************************* Setters *********************************************
	// ----------- Add/Remove Elements -----------
	public void removeLife ()
	{
		lives --;
	}
	
	
	
	// ----------- Movement -----------
	public void up()
	{
		//Position character in the resting forward position
		steps = 3;
		direction = "F";
		character = moves[0];
	}

	public void moveUp(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)//for switing between steps
		{
			if ("F1".equals(direction))
			{
				direction = "F2";
				character = moves[2];
			}
			else
			{
				direction = "F1";
				character = moves[1];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		ypos -= speed;//move up

	}

	public void down()
	{
		//Position character in the resting Downward position
		steps = 3;
		direction = "B";
		character = moves[3];
	}

	public void moveDown(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)//for switing between steps
		{
			if ("B1".equals(direction))
			{
				direction = "B2";
				character = moves[5];
			}
			else
			{
				direction = "B1";
				character = moves[4];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		ypos += speed;//Move down
	}

	public void left()
	{
		//Position character in the resting Left position
		steps = 3;
		direction = "L";
		character = moves[6];
	}

	public void moveLeft(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)//For switching between steps
		{
			if ("L1".equals(direction))
			{
				direction = "L2";
				character = moves[8];
			}
			else
			{
				direction = "L1";
				character = moves[7];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		xpos -= speed;//Move left
	}
	
	public void right ()
	{
		//Position player in the resting Right position
		steps = 3;
		direction = "R";
		character = moves [9];
	}
	
	public void moveRight (Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)//For switching between steps
		{
			if ("R1".equals(direction))
			{
				direction = "R2";
				character = moves[11];
			}
			else
			{
				direction = "R1";
				character = moves[10];
			}
			
			steps = 0;
		}
		else
			steps ++;
		
		xpos += speed;//Move right
		
		
	}
	
	
	public void moveUpLeft(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)
		{
			if ("F1".equals(direction))
			{
				direction = "F2";
				character = moves[2];
			}
			else
			{
				direction = "F1";
				character = moves[1];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		ypos -= speed / 2 + 1;
		xpos -= speed / 2 + 1;

		//Wall blocking movement
		if (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
			|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y'
			|| map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
			|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
		{
			//Previous position
			int prevX = xpos + (speed / 2 + 1);
			int prevY = ypos + (speed / 2 + 1);

			//blocking movement
			while (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
				|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y'
				|| map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
				|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
			{
				if (map.getElement(prevX + 5, ypos) == 'W' || items.getElement(prevX + 5, ypos) == 'Y'
					|| map.getElement(prevX + 45, ypos) == 'W' || items.getElement(prevX + 45, ypos) == 'Y')
				{
					ypos += 1;
				}

				if (map.getElement(xpos + 5, prevY + 5) == 'W' || items.getElement(xpos + 5, prevY + 5) == 'Y'
					|| map.getElement(xpos + 5, prevY + 45) == 'W' || items.getElement(xpos + 5, prevY + 45) == 'Y')
				{
					xpos += 1;
				}
			}
		}
	}

	public void moveUpRight(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)
		{
			if ("F1".equals(direction))
			{
				direction = "F2";
				character = moves[2];
			}
			else
			{
				direction = "F1";
				character = moves[1];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		xpos += speed / 2 + 1;
		ypos -= speed / 2 + 1;

		//Wall blocking movement
		if (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
			|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y'
			|| map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
			|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
		{
			//Previous position
			int prevX = xpos - (speed / 2 + 1);
			int prevY = ypos + (speed / 2 + 1);

			//blocking movement
			while (map.getElement(xpos + 5, ypos) == 'W' || items.getElement(xpos + 5, ypos) == 'Y'
				|| map.getElement(xpos + 45, ypos) == 'W' || items.getElement(xpos + 45, ypos) == 'Y'
				|| map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
				|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
			{
				if (map.getElement(prevX + 5, ypos) == 'W' || items.getElement(prevX + 5, ypos) == 'Y'
					|| map.getElement(prevX + 45, ypos) == 'W' || items.getElement(prevX + 45, ypos) == 'Y')
				{
					ypos += 1;
				}

				if (map.getElement(xpos + 45, prevY + 5) == 'W' || items.getElement(xpos + 45, prevY + 5) == 'Y'
					|| map.getElement(xpos + 45, prevY + 45) == 'W' || items.getElement(xpos + 45, prevY + 45) == 'Y')
				{
					xpos -= 1;
				}
			}
		}
	}

	public void moveDownLeft(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)
		{
			if ("B1".equals(direction))
			{
				direction = "B2";
				character = moves[5];
			}
			else
			{
				direction = "B1";
				character = moves[4];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		xpos -= speed / 2 + 1;
		ypos += speed / 2 + 1;

		//Wall blocking movement
		if (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
			|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y'
			|| map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
			|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
		{
			//Previous position
			int prevX = xpos + (speed / 2 + 1);
			int prevY = ypos - (speed / 2 + 1);

			//blocking movement
			while (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
				|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y'
				|| map.getElement(xpos + 5, ypos + 5) == 'W' || items.getElement(xpos + 5, ypos + 5) == 'Y'
				|| map.getElement(xpos + 5, ypos + 45) == 'W' || items.getElement(xpos + 5, ypos + 45) == 'Y')
			{
				if (map.getElement(prevX + 5, ypos + 46) == 'W' || items.getElement(prevX + 5, ypos + 46) == 'Y'
					|| map.getElement(prevX + 45, ypos + 46) == 'W' || items.getElement(prevX + 45, ypos + 46) == 'Y')
				{
					ypos -= 1;
				}

				if (map.getElement(xpos + 5, prevY + 5) == 'W' || items.getElement(xpos + 5, prevY + 5) == 'Y'
					|| map.getElement(xpos + 5, prevY + 45) == 'W' || items.getElement(xpos + 5, prevY + 45) == 'Y')
				{
					xpos += 1;
				}
			}
		}
	}

	public void moveDownRight(Map map, Items items, Enemies enemiesMap)
	{
		if (steps > 3)
		{
			if ("B1".equals(direction))
			{
				direction = "B2";
				character = moves[5];
			}
			else
			{
				direction = "B1";
				character = moves[4];
			}

			steps = 0;
		}
		else
		{
			steps++;
		}

		xpos += speed / 2 + 1;
		ypos += speed / 2 + 1;

		//Wall blocking movement
		if (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
			|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y'
			|| map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
			|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
		{
			//Previous position
			int prevX = xpos - (speed / 2 + 1);
			int prevY = ypos - (speed / 2 + 1);

			//blocking movement
			while (map.getElement(xpos + 5, ypos + 46) == 'W' || items.getElement(xpos + 5, ypos + 46) == 'Y'
				|| map.getElement(xpos + 45, ypos + 46) == 'W' || items.getElement(xpos + 45, ypos + 46) == 'Y'
				|| map.getElement(xpos + 45, ypos + 5) == 'W' || items.getElement(xpos + 45, ypos + 5) == 'Y'
				|| map.getElement(xpos + 45, ypos + 45) == 'W' || items.getElement(xpos + 45, ypos + 45) == 'Y')
			{
				if (map.getElement(prevX + 5, ypos + 46) == 'W' || items.getElement(prevX + 5, ypos + 46) == 'Y'
					|| map.getElement(prevX + 45, ypos + 46) == 'W' || items.getElement(prevX + 45, ypos + 46) == 'Y')
				{
					ypos -= 1;
				}

				if (map.getElement(xpos + 45, prevY + 5) == 'W' || items.getElement(xpos + 45, prevY + 5) == 'Y'
					|| map.getElement(xpos + 45, prevY + 45) == 'W' || items.getElement(xpos + 45, prevY + 45) == 'Y')
				{
					xpos -= 1;
				}
			}
		}
	}
}
