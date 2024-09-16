/*
 * Name:Talha Iqbal
 * Date: 2020-06-04
 */
package it_securespace;



import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



public class Map
{

	// ********************************************* Instance Variables *********************************************
	//Images
	private Image bedRockWall, door, keyedDoor, floor, space, shop, shopBuy;

	//Size
	private int width, height;

	//Map
	private char map[][];
	private int currentMap;

	private final String[] filesLoadInit =
	{
		"Images/Levels/Level1Map.txt", "Images/Levels/WelcomeMap.txt",
		"Images/Levels/Level2Map.txt", "Images/Levels/Level3Map.txt",
		"Images/Levels/Level4Map.txt", "Images/Levels/Level5Map.txt"
	};


	private final String[] tempData =
	{
		"Images/tempData/Level1Map.txt", "Images/tempData/WelcomeMap.txt",
		"Images/tempData/Level2Map.txt", "Images/tempData/Level3Map.txt",
		"Images/tempData/Level4Map.txt", "Images/tempData/Level5Map.txt"
	};

	private final int[][] spawnPrev =
	{
		{
			850, 450, 850, 1250, 400
		},
		{
			600, 50, 50, 50, 50
		}
	};

	private final int[][] spawnNext =
	{
		{
			50, 100, 100, 100, 100
		},
		{
			50, 400, 650, 650, 700
		}
	};

	//others
	private boolean atShop;




	// ********************************************* Constructors *********************************************
	public Map() throws FileNotFoundException
	{
		//Initialization of instance variables
		currentMap = 1;
		width = 50;
		height = 50;
		atShop = false;


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

			map = new char[rows][cols]; // define 2-d array size

			//Reopen Scanner
			scnr.close();
			scnr = new Scanner(mapFile);

			//copy array from file
			for (int row = 0; row < map.length && scnr.hasNextLine(); row++)
			{
				//get next line in text file
				line = scnr.nextLine();

				for (int col = 0; col < map[row].length; col++)
				{
					map[row][col] = line.charAt(col);
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
		File mapFile = new File(tempData[currentMap]);
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

		map = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(mapFile);

		for (int row = 0; row < map.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < map[row].length; col++)
			{
				map[row][col] = line.charAt(col);
			}
		}

		//Initalize Images
		bedRockWall = null;
		door = null;
		keyedDoor = null;
		floor = null;
		space = null;
		shop = null;
		shopBuy = null;


		//Get Images
		try
		{
			bedRockWall = ImageIO.read(new File("Images/bedRock.png")); // load file into Image object
			door = ImageIO.read(new File("Images/door.jfif")); // load file into Image object
			keyedDoor = ImageIO.read(new File("Images/keyedDoor.jpg")); // load file into Image object
			floor = ImageIO.read(new File("Images/floor.jfif")); // load file into Image object
			space = ImageIO.read(new File("Images/space.jfif")); // load file into Image object
			shop = ImageIO.read(new File("Images/shop.png")); // load file into Image object
			shopBuy = ImageIO.read(new File("Images/shopBuy.png")); // load file into Image object
		}
		catch (IOException e)
		{
			System.out.println("File not found");
		}
	}




	// ********************************************* Graphics *********************************************
	public void print(Graphics g, Player player) // displays the map on the screen
	{

		for (int row = 0; row < map.length; row++)// number of rows
		{
			for (int col = 0; col < map[0].length; col++)// length of first row
			{
				if (map[row][col] == 'W') // wall
				{
					g.drawImage(bedRockWall, col * width, row * height, null);
				}
				else if (map[row][col] == 'D') // door
				{
					g.drawImage(door, col * width, row * height, null);
				}
				else if (map[row][col] == 'L') // keyed door
				{
					g.drawImage(keyedDoor, col * width, row * height, null);
				}
				else if (map[row][col] == ' ') // space
				{
					g.drawImage(space, col * width, row * height, null);
				}
				else if (map[row][col] == 'F') // floor
				{
					g.drawImage(floor, col * width, row * height, null);
				}
				else if (map[row][col] == 'S') // shop
				{
					g.drawImage(shop, col * width, row * height, null);
				}
			}

			if (atShop)
			{
				g.setColor(Color.white);

				g.drawString("Bullets (2 Coins)", 100, 595);
				g.drawImage(shopBuy, 300, 580, null);

				g.drawString("Explosives (3 Coins)", 100, 625);
				g.drawImage(shopBuy, 300, 610, null);
			}

			g.setColor(Color.white);
			g.drawString("Lives: " + player.getLives(), 100, 880);
			g.drawString("Coins: " + player.getCoins(), 100, 900);
			g.drawString("Keys: " + player.getKeys(), 100, 920);
			g.drawString("Bullets: " + player.getBullets(), 250, 880);
			g.drawString("Explosives: " + player.getExplosives(), 250, 900);

			g.setColor(Color.black);
		}
	}




	// ********************************************* Getters *********************************************
	public int getRowLength()
	{
		return map.length;
	}



	public int getColLength()
	{
		return map[0].length;
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
		return map[y / getHeight()][x / getWidth()];
	}



	public int getCurrentMapNum()
	{
		return currentMap;
	}



	public boolean getatShop()
	{
		return atShop;
	}




	// ********************************************* Setters *********************************************
	public void add(int x, int y, char item)
	{
		map[y / 50][x / 50] = item;
	}



	public void levelChange(Items items, Enemies enemiesMap, Player player)
	{
		if (player.getx() / 50 < map[0].length / 2)
		{
			try
			{
				enemiesMap.saveFiles();
				enemiesMap.clear();
				items.prevLevel();

				save(new File(tempData[currentMap]));
				currentMap--;
				load(new File(tempData[currentMap]));
			}
			catch (FileNotFoundException ex)
			{
				Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
			}

			player.setx(spawnPrev[0][currentMap]);
			player.sety(spawnPrev[1][currentMap]);
		}
		else
		{
			try
			{
				enemiesMap.saveFiles();
				enemiesMap.clear();
				items.nextLevel();

				save(new File(tempData[currentMap]));
				currentMap++;
				load(new File(tempData[currentMap]));
			}
			catch (FileNotFoundException ex)
			{
				Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
			}

			player.setx(spawnNext[0][currentMap - 1]);
			player.sety(spawnNext[1][currentMap - 1]);
		}


	}



	public boolean atShop(boolean change)
	{
		if (change != atShop)
		{
			atShop = change;
		}

		return atShop;
	}




	// ********************************************* Other Methods *********************************************
	public void save(File text) throws FileNotFoundException
	{
		//Instancing of PrintWriter object
		PrintWriter outputStream = new PrintWriter(text);

		for (int r = 0; r < map.length; r++)
		{
			for (int c = 0; c < map[r].length; c++)
			{
				//Print map character at specified location
				outputStream.print(map[r][c]);
				outputStream.flush();
			}
			if (r != map.length)
			{
				outputStream.println("");
			}
		}
		outputStream.close();

	}



	public void load(File text) throws FileNotFoundException
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

		map = new char[rows][cols]; // define 2-d array size

		//Reopen Scanner
		scnr.close();
		scnr = new Scanner(text);

		for (int row = 0; row < map.length && scnr.hasNextLine(); row++)
		{
			//get next line in text file
			line = scnr.nextLine();

			for (int col = 0; col < map[row].length; col++)
			{
				map[row][col] = line.charAt(col);
			}
		}
	}



}
