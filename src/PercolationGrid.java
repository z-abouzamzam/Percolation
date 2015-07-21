import java.util.*;
import java.util.Timer;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
public class PercolationGrid
{
	private int[][] percGrid;
	private JFrame frame;
	private JPanel mainPanel;
	private int gridHeight, gridWidth;
	private Space[][] spaceArray;
	public final int EMPTY = 1, WALL = 2, TO_BE_COLORED = 3, COLORED = 4;
	private Timer timer;
	public PercolationGrid(int[][] grid)
	{
		percGrid = grid;
		gridHeight = grid.length;
		gridWidth = grid[0].length;
		spaceArray = convertToColorArray(percGrid);
		setUpGrid(spaceArray);
		setUpTimer();
	}
	public void setUpGrid(Space[][] s)
	{
		frame = new JFrame();
		frame.setTitle("Percolate!");
		mainPanel = (JPanel)frame.getContentPane();
		GridLayout layout = new GridLayout(gridHeight, gridWidth);
		mainPanel.setLayout(layout);
		for (int i = 0; i < gridHeight; i++)
		{
			for (int j = 0; j < gridWidth; j++)
			{
				mainPanel.add(s[i][j]);
			}
		}
		
		frame.setSize(gridWidth * 30, gridHeight * 25);
		frame.setVisible(true);
	}
	public Space[][] convertToColorArray(int[][] grid)
	{
		Space[][] sArray = new Space[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				Space temp = new Space(grid[i][j]);
				temp.setInitialColor(temp.getSpaceType());
				temp.setInitialProperty(temp.getSpaceType());
				sArray[i][j] = temp;
				// System.out.println(grid[i][j] + " color");
			}
		}
		return sArray;
	}
	
	public void setUpTimer()
	{
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask()
		{
			
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				animate();
			}
		}, 0, 700);
		
	}
	private void animate()
	{
		changeSpaceState(spaceArray);
		changeSpaceColor(spaceArray);
		if(didWin(spaceArray))
		{
			timer.cancel();
			timer.purge();
			JOptionPane.showMessageDialog(null, "Percolation has completed!", "Complete", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void changeSpaceState(Space[][] s)
	{
		int state;
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < s[i].length; j++)
			{
				state = s[i][j].getSpaceState();
				if(state == COLORED)
				{
					if(j < s[i].length - 1)
					{
						if(s[i][j+1].getSpaceState() == EMPTY)
						{
							s[i][j+1].setSpaceState(TO_BE_COLORED);
						}
					}
					if(j > 0)
					{
						if(s[i][j-1].getSpaceState() == EMPTY)
						{
							s[i][j-1].setSpaceState(TO_BE_COLORED);
						}
					}
					if(i < s.length - 1)
					{
						if(s[i+1][j].getSpaceState() == EMPTY)
						{
							s[i+1][j].setSpaceState(TO_BE_COLORED);
						}
					}
					if(i > 0)
					{
						if(s[i-1][j].getSpaceState() == EMPTY)
						{
							s[i-1][j].setSpaceState(TO_BE_COLORED);
						}
					}
				}
			}
		}
	}
	
	private boolean didWin(Space[][] s)
	{
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < s[i].length; j++)
			{
				if(s[i][j].getSpaceState() == COLORED && s[i][j].getSpaceType() == 3)
				{
					return true;
				}
			}
		}
		return false;
	}
	public void changeSpaceColor(Space[][] s)
	{
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < s[i].length; j++)
			{
				if(s[i][j].getSpaceState() == TO_BE_COLORED)
				{
					s[i][j].setBackground(Color.CYAN);
					s[i][j].setSpaceState(COLORED);
				}
			}
		}
	}

}
