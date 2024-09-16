/*
 * Name:Talha Iqbal
 * Date: 2020-06-13
 */
package it_securespace;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author talha
 */
public class Bullet
{
	// ********************************************* Instance Variables *********************************************
	//position
	private int xpos, ypos;
	
	private String direction;
	
	private int speed;
	
	//Image
	private Image bullet;
	
	
	// ********************************************* Constructor *********************************************
	public Bullet (String direction, int x, int y)
	{
		//initialization of instance variables
		xpos = x;//snap to grid
		ypos = y;//snap to grid
		
		this.direction = direction;
		
		speed = 5;
		
		//Get images
		try
		{
			bullet = ImageIO.read(new File("Images/PlayerItems/bullet.png")); // load file into Image object		
		}
		catch (IOException e)
		{
			System.out.println("File not found");
		}
		
	}
	
	
	
	
	// ********************************************* Graphics *********************************************
	public void print(Graphics g) // displays bullet
	{
		g.drawImage(bullet, xpos, ypos, null);
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	
	
	
	
	// ********************************************* Setters *********************************************
	public void moveBullet (Map map, Items items, ArrayList <Bullet> bullets, ArrayList <Monster> monsters)
	{
		if (direction.charAt(0) == 'F')
		{
			moveForward ();
		}
		else
		if (direction.charAt(0) == 'B')
		{
			moveBackward ();
		}
		else
		if (direction.charAt(0) == 'L')
		{
			moveLeft ();
		}
		else
		if (direction.charAt(0) == 'R')
		{
			moveRight ();
		}
		hitCheck (map, items, bullets, monsters);
	}
	
	
	private  void moveForward ()
	{
		ypos -= speed;
	}
	
	private void moveBackward ()
	{
		ypos += speed;
	}
	
	
	private void moveLeft ()
	{
		xpos -= speed;
	}
	
	
	private void moveRight ()
	{
		xpos += speed;
	}
	
	
	private void hitCheck (Map map, Items items, ArrayList <Bullet> bullets, ArrayList <Monster> monsters)
	{
		//Decleration of Variables
		Monster temp [] = new Monster [monsters.size()];
		
		
		//copy arrayList
		for (int i = 0; i < monsters.size(); i ++)
		{
			temp [i] = monsters.get(i);
		}
		
		if (map.getElement(xpos, ypos) != ' ' && map.getElement(xpos, ypos) != 'F')//collision
		{
			bullets.remove(this);
		}
		else if (items.getElement(xpos, ypos) == 'Y')//collision
		{
			bullets.remove(this);
		}
		else
		{
			for (int i = 0; i < temp.length; i ++)
			{
				if (xpos+23 >= temp[i].getx() && xpos+23 <= temp[i].getx()+48 || xpos+26 >= temp[i].getx() && xpos+26 <= temp[i].getx()+48)//Hit
				{
					if (ypos+22 >= temp[i].gety() && ypos+22 <= temp[i].gety()+48 || ypos+25 >= temp[i].gety() && ypos+25 <= temp[i].gety()+48)
					{
						temp[i].removeLife(monsters);
						bullets.remove(this);
					}
						
				}
			}
		}
	}
}

