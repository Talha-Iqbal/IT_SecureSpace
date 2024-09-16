/*
 * Name:Talha Iqbal
 * Date: 2020-06-13
 */
package it_securespace;

//Libraries

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Explosive
{
	// ********************************************* Instance Variables *********************************************
	//position
	private int xpos, ypos;
	
	private Timer explosionTimer;


	//Image
	private Image explosive;

	// ********************************************* Constructor *********************************************
	public Explosive (int x, int y, Items items, Player player, ArrayList <Monster> monsters, ArrayList <Explosive> explosives)
	{
		//initialization of instance variables
		xpos = x;
		ypos = y;
		
		
		explosionTimer = new Timer (1000, new ActionListener () {
		public void actionPerformed(ActionEvent e)
		{
			explode (items, player, monsters, explosives);
		}
		});
		explosionTimer.setInitialDelay(2000);
		explosionTimer.start();
		
		
		explosive = null;
		//Get images
		try
		{
			explosive = ImageIO.read(new File("Images/PlayerItems/bomb.png")); // load file into Image object		
		}
		catch (IOException e)
		{
			System.out.println("File not found");
		}
	}



	
	// ********************************************* Graphics *********************************************
	public void print(Graphics g) // displays bullet
	{
		g.drawImage(explosive, xpos, ypos, null);
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	
	
	
	
	// ********************************************* Setters *********************************************
	private void explode (Items items, Player player, ArrayList <Monster> monsters, ArrayList <Explosive> explosives)
	{
		//Explode
		explosionTimer.stop();
		
		//Decleration of variables
		int rangexStart = xpos-50;
		int rangeyStart = ypos-50;
		
		int rangexEnd = xpos+50;
		int rangeyEnd = ypos+50;
		Monster monsterTemp [] = new Monster [monsters.size()];
		
		
		//Copy monsters
		for (int i = 0; i < monsterTemp.length; i ++)
		{
			monsterTemp [i] = monsters.get(i);
		}
		
		
		for (int x = (rangexStart/50)*50; x <= (rangexEnd/50)*50; x += 50)//remove destroyable wall within a block radius
		{
			for (int y = (rangeyStart/50)*50; y <= (rangeyEnd/50)*50; y += 50)
			{
				if (items.getElement(x, y) == 'Y')
				{
					items.removeDestroyable(x, y);
				}
			}
		}
		
		//if monsters in range
		for (int i = 0; i < monsterTemp.length; i ++)//damge monsters within 1 block radius
		{
			if (monsterTemp[i].getx() >= rangexStart && monsterTemp[i].getx() <= rangexEnd)// within x range
			{
				if (monsterTemp[i].gety() >= rangeyStart && monsterTemp[i].gety() <= rangeyEnd)//within y range
				{
					//2 hp -
					monsterTemp[i].removeLife();
					monsterTemp[i].removeLife();
				}
			}
		}
		
		//if player in range
		if (player.getx() >= rangexStart && player.getx() <= rangexEnd)
		{
			if (player.gety() >= rangeyStart && player.gety() <= rangeyEnd)
			{
				//remove two lives
				player.removeLife();
				player.removeLife();
			}
		}
		
		explosives.remove(this);
	}
	
}


