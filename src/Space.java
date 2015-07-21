import java.util.*;
import java.awt.*;

import javax.swing.*;
public class Space extends JButton
{
	private int spaceType;
	private int spaceState;
	public final int EMPTY = 1, WALL = 2, TO_BE_COLORED = 3, COLORED = 4;
	public Space(int type)
	{
		spaceType = type;
		
	}
	public int getSpaceType()
	{
		return spaceType;
	}
	public int getSpaceState()
	{
		return spaceState;
	}
	
	public void setSpaceState(int state)
	{
		spaceState = state;
	}
	
	public void setInitialColor(int spaceType)
	{
		setOpaque(true);
		setEnabled(false);
		if(spaceType == 0) // empty
			setBackground(Color.white);
		else if(spaceType == 1) // wall
			setBackground(Color.BLACK);
		else if (spaceType == 2) //start
			setBackground(Color.WHITE);
		else if (spaceType == 3) //goal
			setBackground(Color.WHITE);
	}
	public void setInitialProperty(int spaceType)
	{
		if(spaceType == 1)
		{
			spaceState = WALL;
		}
		else if (spaceType == 2)
		{
			spaceState = TO_BE_COLORED;
		}
		else
		{
			spaceState = EMPTY;
		}
	}
	
}
