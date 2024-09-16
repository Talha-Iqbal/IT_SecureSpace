/*
 * Name:Talha Iqbal
 * Date: 2020-06-12
 */
package it_securespace;

//Libraries

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Items
{
	// ********************************************* Instance Variables *********************************************
	private char itemsMap[][];
	private int currentMap;
	
	private int width, height;
	private Image coin, key, treasure, bomb, bullet, destroyable, merchant;
	
	private final String[] filesLoadInit =
	{
		"Images/Levels/Level1MapItems.txt", "Images/Levels/WelcomeMapItems.txt",
		"Images/Levels/Level2MapItems.txt", "Images/Levels/Level3MapItems.txt",
		"Images/Levels/Level4MapItems.txt", "Images/Levels/Level5MapItems.txt"
	};
	
	private final String [] tempData =
	{
		"Images/tempData/Level1MapItems.txt", "Images/tempData/WelcomeMapItems.txt",
		"Images/tempData/Level2MapItems.txt", "Images/tempData/Level3MapItems.txt",
		"Images/tempData/Level4MapItems.txt", "Images/tempData/Level5MapItems.txt"
	};
	
	
	
	
	// ********************************************* Constructor *********************************************	
	public Items () throws FileNotFoundException
	{
		//Initialization of instance variables
		width = 50;
		height = 50;
		currentMap = 1;
		
		//storeMap in temp data for modification later in game
		for (int i = 0; i < filesLoadInit.length; i++)
		{
			//Decleration of Variables
			String line = "";
			int rows = 0;
			int cols = 0;

			//Instancing of Objects
			File mapFile = new File(filesLoadInit[i]);
			Scanner scnr = new Scanner(mapFile);

			while (scnr.hasNextLine())//Get Num of rows and cols
			{
				line = scnr.nextLine();

				if (cols == 0)
				{
					cols = line.length();
				}

				rows++;
			}

			itemsMap = new char[rows][cols]; // define 2-d array size

			//Reopen Scanner
			scnr.close();
			scnr = new Scanner(mapFile);

			//copy array from file
			for (int row = 0; row < itemsMap.length && scnr.hasNextLine(); row++)
			{
				//get next line in text file
				line = scnr.nextLine();

				for (int col = 0; col < itemsMap[row].length; col++)
				{
					itemsMap[row][col] = line.charAt(col);
				}
			}

			//save File
			save(new File(tempData[i]));
		}
		
		
		//Decleration of Variables
		String line = "";
		int rows = 0;
		int cols = 0;
		
		
		//Instancing of Objects
		File mapFile = new File (tempData [currentMap]);
		Scanner scnr = new Scanner(mapFile);

		while (scnr.hasNextLine())//Get Num of rows and cols
		{
			line = scnr.nextLine();

			if (cols == 0)
			{
				cols = line.length();
			}

			rows++;
		}

		itemsMap = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(mapFile);

		for (int row = 0; row < itemsMap.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < itemsMap[row].length; col++)
			{
				itemsMap[row][col] = line.charAt(col);
			}
		}

		//Initialize images
		coin = null;
		key = null;
		treasure = null;
		bomb = null;
		bullet = null;
		destroyable = null;

		//Get images
		try
		{
			destroyable = ImageIO.read(new File("Images/PlayerItems/destroyable.png")); // load file into Image object
			coin = ImageIO.read(new File("Images/Collectable/coin.png")); // load file into Image object
			key = ImageIO.read(new File("Images/Collectable/key.png")); // load file into Image object
			treasure = ImageIO.read(new File("Images/Collectable/treasure.png")); // load file into Image object
			bomb = ImageIO.read(new File("Images/PlayerItems/bomb.png")); // load file into Image object
			bullet = ImageIO.read(new File("Images/PlayerItems/bullet.png")); // load file into Image object			
			merchant = ImageIO.read(new File("Images/PlayerItems/merchant.png")); // load file into Image object			
		}
		catch (IOException e)
		{
			System.out.println("File not found");
		}
	}

	
	
	
	// ********************************************* Graphics *********************************************
	public void print(Graphics g)
	{
		for (int row = 0; row < itemsMap.length; row++)
		{
			for (int col = 0; col < itemsMap[row].length; col++)
			{
				if (itemsMap[row][col] == 'C')
				{
					g.drawImage(coin, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'K')
				{
					g.drawImage(key, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'T')
				{
					g.drawImage(treasure, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'B')
				{
					g.drawImage(bomb, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'Y')
				{
					g.drawImage(destroyable, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'X')
				{
					g.drawImage(bullet, col * 50, row * 50, null);
				}
				if (itemsMap[row][col] == 'E')
				{
					g.drawImage(merchant, col * 50, row * 50, null);
				}
			}
		}
	}

	
	
	
	// ********************************************* Getters *********************************************
	public int getRowLength()
	{
		return itemsMap.length;
	}

	public int getColLength()
	{
		return itemsMap[0].length;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public char getElement(int x, int y)
	{
		return itemsMap[y / getHeight()][x / getWidth()];
	}

	
	
	
	// ********************************************* Setters *********************************************
	public void add(int row, int col, char item)
	{
		itemsMap[row][col] = item;
	}
	
	public void removeCoin (int x, int y)
	{
		//Decleration of variables
		int row = y / 50;
		int col = x / 50;

		itemsMap[row][col] = ' ';
	}
	
	public void removeKey (int x, int y)
	{
		//Decleration of variables
		int row = y/50;
		int col = x/50;
		
		itemsMap [row] [col] = ' ';
	}
	
	public void removeTreasure (int x, int y)
	{
		//Decleration of variables
		int row = y / 50;
		int col = x / 50;

		itemsMap[row][col] = ' ';
	}
	
	public void removeDestroyable (int x, int y)
	{
		//Decleration of variables
		int row = y / 50;
		int col = x / 50;

		itemsMap[row][col] = ' ';
	}
	
	void nextLevel()
	{
		try
		{
			save(new File(tempData[currentMap]));
			currentMap++;
			load(new File(tempData[currentMap]));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	void prevLevel()
	{
		try
		{
			save(new File(tempData[currentMap]));
			currentMap--;
			load(new File(tempData[currentMap]));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	

	// ********************************************* Other Methods *********************************************
	public void save(File text) throws FileNotFoundException
	{
		PrintWriter outputStream = new PrintWriter(text);

		for (int r = 0; r < itemsMap.length; r++)
		{
			for (int c = 0; c < itemsMap[r].length; c++)
			{
				//Print itemsMap character at specified location
				outputStream.print(itemsMap[r][c]);
				outputStream.flush();
			}
			
			if (r != itemsMap.length)
				outputStream.println("");
		}
		outputStream.close();

	}

	public void load(File text) throws FileNotFoundException
	{
		//Decleration of variables and instancing of objects
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

		itemsMap = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(text);

		for (int row = 0; row < itemsMap.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < itemsMap[row].length; col++)
			{
				itemsMap[row][col] = line.charAt(col);
			}
		}
	}
}

