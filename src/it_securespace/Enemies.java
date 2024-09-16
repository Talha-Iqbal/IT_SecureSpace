/*
 * Name:Talha Iqbal
 * Date: 2020-06-14
 */
package it_securespace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author talha
 */
public class Enemies
{
	// ********************************************* Instance Variables *********************************************
	//Map
	private char[][] enemyMap;
	private char[][] enemyHealthMap;
	private int currentMap;
	
	//Size
	private int width, height;
	
	
	private final String [][] filesLoadInit = 
	{
		{
			"Images/Levels/Level1MapEnemies.txt", "Images/Levels/WelcomeMapEnemies.txt",
			"Images/Levels/Level2MapEnemies.txt", "Images/Levels/Level3MapEnemies.txt",
			"Images/Levels/Level4MapEnemies.txt", "Images/Levels/Level5MapEnemies.txt"
		},
		{
			"Images/Levels/Level1MapEnemiesHealth.txt", "Images/Levels/WelcomeMapEnemiesHealth.txt",
			"Images/Levels/Level2MapEnemiesHealth.txt", "Images/Levels/Level3MapEnemiesHealth.txt",
			"Images/Levels/Level4MapEnemiesHealth.txt", "Images/Levels/Level5MapEnemiesHealth.txt"
		}
	};
	
	private final String [][] tempData = 
	{
		{
			"Images/tempData/Level1MapEnemies.txt", "Images/tempData/WelcomeMapEnemies.txt",
			"Images/tempData/Level2MapEnemies.txt", "Images/tempData/Level3MapEnemies.txt",
			"Images/tempData/Level4MapEnemies.txt", "Images/tempData/Level5MapEnemies.txt"
		},
		{
			"Images/tempData/Level1MapEnemiesHealth.txt", "Images/tempData/WelcomeMapEnemiesHealth.txt",
			"Images/tempData/Level2MapEnemiesHealth.txt", "Images/tempData/Level3MapEnemiesHealth.txt",
			"Images/tempData/Level4MapEnemiesHealth.txt", "Images/tempData/Level5MapEnemiesHealth.txt"
		}
	};
	
	
	
	
	// ********************************************* Constructors *********************************************	
	public Enemies () throws FileNotFoundException
	{
		//Initialisation of instance variables
		width = 50;
		height = 50;
		currentMap = 1;

		
		
		//Load all enemyMap data into tempdata
		for (int i = 0; i < filesLoadInit[0].length; i ++)
		{
			//Decleration of variables and instancing of collectable
			String line = "";
			int rows = 0;
			int cols = 0;

			//Instancing of Objects
			Scanner scnr = new Scanner(new File(filesLoadInit[0][i]));

			while (scnr.hasNextLine())//Get Num of rows and cols
			{
				line = scnr.nextLine();

				if (cols == 0)
				{
					cols = line.length();
				}

				rows++;
			}

			enemyMap = new char[rows][cols]; // define 2-d array size

			//Reopen Scanner
			scnr.close();
			scnr = new Scanner(new File(filesLoadInit[0][i]));

			for (int row = 0; row < enemyMap.length && scnr.hasNextLine(); row++)
			{
				//get next line in text file
				line = scnr.nextLine();

				for (int col = 0; col < enemyMap[row].length; col++)
				{
					enemyMap[row][col] = line.charAt(col);
				}
			}
			
			//save File
			saveEnemyMap (new File (tempData [0][i]));
		}
		
		
		//Load all enemyHealthMap data into tempdata
		for (int i = 0; i < filesLoadInit[1].length; i ++)
		{
			//Decleration of variables and instancing of collectable
			String line = "";
			int rows = 0;
			int cols = 0;

			//Instancing of Objects
			Scanner scnr = new Scanner(new File(filesLoadInit[1][i]));

			while (scnr.hasNextLine())//Get Num of rows and cols
			{
				line = scnr.nextLine();

				if (cols == 0)
				{
					cols = line.length();
				}

				rows++;
			}
			
			enemyHealthMap = new char[rows][cols]; // define 2-d array size

			//Reopen Scanner
			scnr.close();
			scnr = new Scanner(new File(filesLoadInit[1][i]));

			for (int row = 0; row < enemyHealthMap.length && scnr.hasNextLine(); row++)
			{
				//get next line in text file
				line = scnr.nextLine();

				for (int col = 0; col < enemyHealthMap[row].length; col++)
				{
					enemyHealthMap[row][col] = line.charAt(col);
				}
			}
			
			//save File
			saveEnemyHealthMap (new File (tempData [1][i]));
		}
		
		
		//Decleration of variables and instancing of collectable
		String line1 = "";
		String line2 = "";
		int rows = 0;
		int cols = 0;
		
		
		//Instancing of Objects
		Scanner scnr1 = new Scanner(new File (tempData[0][currentMap]));
		Scanner scnr2 = new Scanner(new File (tempData[1][currentMap]));

		while (scnr1.hasNextLine())//Get Num of rows and cols
		{
			line1 = scnr1.nextLine();

			if (cols == 0)
			{
				cols = line1.length();
			}

			rows++;
		}

		enemyMap = new char[rows][cols]; // define 2-d array size
		enemyHealthMap = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr1.close();
		scnr1 = new Scanner(new File (tempData[0][currentMap]));

		for (int row = 0; row < enemyMap.length && scnr1.hasNextLine(); row++)
		{
			//get next line in text file
			line1 = scnr1.nextLine();
			line2 = scnr2.nextLine();

			for (int col = 0; col < enemyMap[row].length; col++)
			{
				enemyMap[row][col] = line1.charAt(col);
				enemyHealthMap[row][col] = line2.charAt(col);
			}
		}
	}
	
	
	
	
	// ********************************************* Getters *********************************************
	public int getRowLength()
	{
		return enemyMap.length;
	}

	public int getColLength()
	{
		return enemyMap[0].length;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public char getElement1(int x, int y)
	{
		return enemyMap[y / getHeight()][x / getWidth()];
	}
	
	public char getElement2 (int r, int c)
	{
		return enemyMap [r][c];
	}
	
	
	public ArrayList <Monster> getMonsters ()
	{
		//Decleration of variables
		int lives;
		int x;
		int y;
		int walk;
		int monsterCount = 0;
		int monsternum = 0;
		
		for (int r = 0; r < enemyMap.length; r ++)
		{
			for (int c = 0; c < enemyMap[0].length; c ++)
			{
				if (enemyMap[r][c] != ' ')
					monsterCount ++;
			}
		}
		
		ArrayList <Monster> monsters = new ArrayList <> ();
		
		for (int r = 0; r < enemyMap.length; r ++)
		{
			for (int c = 0; c < enemyMap[r].length; c ++)
			{
				if (enemyMap[r][c] != ' ')
				{
					lives = Integer.parseInt(enemyHealthMap [r][c] + "");
					walk = Integer.parseInt(enemyMap [r][c] + "");
					x = c*50;
					y = r*50;
					Monster temp = new Monster (lives, x, y, walk);
					monsters.add(temp);
					monsternum++;
				}
			}
		}
		
		
		return monsters;
	}

	
	
	// ********************************************* Setters *********************************************	
	void nextLevel()
	{
		
		try
		{
			currentMap++;
			loadEnemyMap(new File(tempData[0][currentMap]));
			loadEnemyHealthMap(new File(tempData[1][currentMap]));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(Enemies.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void prevLevel()
	{
		try
		{
			currentMap--;
			loadEnemyMap(new File(tempData[0][currentMap]));
			loadEnemyHealthMap(new File(tempData[1][currentMap]));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(Enemies.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	void clear ()
	{
		for (int r = 0; r < enemyMap.length; r ++)
		{
			for (int c = 0; c < enemyMap[r].length; c ++)
			{
				enemyMap [r][c] = ' ';
				enemyHealthMap [r][c] = ' ';
			}
		}
	}
	
	void save (ArrayList <Monster> monsters)
	{
		clear ();
		//save monster positions
		for (int i = 0; i < monsters.size(); i ++)
		{
			enemyMap [monsters.get(i).gety()/50] [monsters.get(i).getx()/50] = (monsters.get(i).getWalk() + "").charAt(0);
			enemyHealthMap [monsters.get(i).gety()/50] [monsters.get(i).getx()/50] = (monsters.get(i).getLives() + "").charAt(0);
		}
	}

	void saveFiles ()
	{
		try
		{
			saveEnemyMap (new File (tempData[0][currentMap]));
			saveEnemyHealthMap (new File (tempData[1][currentMap]));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(Enemies.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

		
	
	// ********************************************* Other Methods *********************************************
	public void saveEnemyMap(File text) throws FileNotFoundException
	{
		//Instancing of PrintWriter object
		PrintWriter outputStream = new PrintWriter(text);

		for (int r = 0; r < enemyMap.length; r++)
		{
			for (int c = 0; c < enemyMap[r].length; c++)
			{
				//Print enemyMap character at specified location
				outputStream.print(enemyMap[r][c]);
				outputStream.flush();
			}
			
			if (r != enemyMap.length)
				outputStream.println("");
		}
		outputStream.close();

	}
	
	public void saveEnemyHealthMap(File text) throws FileNotFoundException
	{
		//Instancing of PrintWriter object
		PrintWriter outputStream = new PrintWriter(text);

		for (int r = 0; r < enemyHealthMap.length; r++)
		{
			for (int c = 0; c < enemyHealthMap[r].length; c++)
			{
				//Print enemyMap character at specified location
				outputStream.print(enemyHealthMap[r][c]);
				outputStream.flush();
			}
			
			if (r != enemyHealthMap.length)
				outputStream.println("");
		}
		outputStream.close();

	}

	public void loadEnemyMap(File text) throws FileNotFoundException
	{
		//Decleration of variables and instancing of collectable
		String line = "";
		int rows = 0;
		int cols = 0;
		Scanner scnr = new Scanner(text);

		while (scnr.hasNextLine())//Get Num of rows and cols
		{
			line = scnr.nextLine();

			if (cols == 0)
			{
				cols = line.length();
			}

			rows++;
		}

		enemyMap = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(text);

		for (int row = 0; row < enemyMap.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < enemyMap[row].length; col++)
			{
				enemyMap[row][col] = line.charAt(col);
			}
		}
	}
	
	public void loadEnemyHealthMap(File text) throws FileNotFoundException
	{
		//Decleration of variables and instancing of collectable
		String line = "";
		int rows = 0;
		int cols = 0;
		Scanner scnr = new Scanner(text);

		while (scnr.hasNextLine())//Get Num of rows and cols
		{
			line = scnr.nextLine();

			if (cols == 0)
			{
				cols = line.length();
			}

			rows++;
		}

		enemyHealthMap = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(text);

		for (int row = 0; row < enemyHealthMap.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < enemyHealthMap[row].length; col++)
			{
				enemyHealthMap[row][col] = line.charAt(col);
			}
		}
	}
}


