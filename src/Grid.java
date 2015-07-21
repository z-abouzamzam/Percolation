import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.*;

import javax.swing.*;
public class Grid
{
	private int gridWidth, gridHeight;
	public JFrame frame;
	private boolean hasBeenCreated = false;
	public void setDimen(int height, int width)
	{
		gridWidth = width;
		gridHeight = height;
	}
	public Button[][] gridArray;
	public int[][] convertedArray;
	protected void convertInput()
	{
		convertedArray = new int[gridHeight][gridWidth];
		for(int i = 0; i < gridArray.length; i ++)
		{
			for (int j = 0; j < gridArray[i].length; j++)
			{
				convertedArray[i][j] = convertLabel(gridArray[i][j].getLabel().toString());
				// System.out.println(convertedArray[i][j]);
			}
		}
	}
	private int convertLabel(String label)
	{
		if(label.equals(" "))
			return 0;
		else if (label.equals("x"))
			return 1;
		else if (label.equals("S"))
			return 2;
		else if(label.equals("G"))
			return 3;
		return 0;
	}
	private OnButtonClick click = new OnButtonClick();
	public void showGrid()
	{
		frame = new JFrame();
		gridArray = new Button[gridHeight][gridWidth];
		JPanel mainPanel = (JPanel)frame.getContentPane();
		GridLayout layout = new GridLayout(gridHeight, gridWidth);
		mainPanel.setLayout(layout);
		for (int i = 0; i < gridHeight; i++)
		{
			for (int j = 0; j < gridWidth; j++)
			{
				Button b = new Button();
				b.addActionListener(click);
				// System.out.print(i + " " + j);
				gridArray[i][j] = b;
				mainPanel.add(b);
				b.setLabel(" ");
			}
		}
		OnCloseWindow listener = new OnCloseWindow();
		frame.addWindowListener(listener);
		frame.setSize(gridWidth * 40, gridHeight * 30);
		frame.setVisible(true);
	}
	public void showGrid(String[][] s)
	{
		frame = new JFrame();
		setDimen(s.length, s[0].length);
		gridArray = new Button[gridHeight][gridWidth];
		JPanel mainPanel = (JPanel)frame.getContentPane();
		GridLayout layout = new GridLayout(gridHeight, gridWidth);
		mainPanel.setLayout(layout);
		for (int i = 0; i < gridHeight; i++)
		{
			for (int j = 0; j < gridWidth; j++)
			{
				Button b = new Button();
				b.addActionListener(click);
				// System.out.print(i + " " + j);
				gridArray[i][j] = b;
				mainPanel.add(b);
				b.setLabel(s[i][j]);
			}
		}
		OnCloseWindow listener = new OnCloseWindow();
		frame.addWindowListener(listener);
		frame.setSize(gridWidth * 30, gridHeight * 25);
		frame.setVisible(true);
	}
	public class OnCloseWindow implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			// TODO Auto-generated method stub
			hasBeenCreated = true;
			convertInput();
			// System.out.println("Input Converted");
		}

		@Override
		public void windowDeactivated(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e)
		{
			// TODO Auto-generated method stub
			
		}
	}
	
	public String[][] readInGridFromFile(File file)
	{
		String[][] convertedArray = null;
		try
		{
			FileReader fir = new FileReader(file);
			// isr = new InputStreamReader(fis);
			BufferedReader bfr = new BufferedReader(fir);
			String line, inString;
			LinkedList<String> lines = new LinkedList<String>();
			try
			{
				String fileName = file.getName();
				String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
				if(!extension.equals("txt"))
					throw new IOException();
				while ((line = bfr.readLine()) != null)
				{
					lines.add(line);
					// System.out.println(line);
					
				}
				convertedArray = convertToTwoDimen(lines);
				// showGrid(convertedArray);
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				// showGrid();
				JOptionPane.showMessageDialog(null, "Invalid File Type\n"
						+ "Use .txt file or input manually", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}			
		} 
		
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			showGrid();
			JOptionPane.showMessageDialog(null, "File Not Found", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}
		return convertedArray;
	}
	
	private String[][] convertToTwoDimen(LinkedList<String> s)
	{	
		LinkedList<LinkedList<String> > sList = new LinkedList<LinkedList<String> >();
		// String[][] se = new String[s.size()][s.get(0).length()];
		for(int i = 0; i < s.size(); i++)
		{
			LinkedList<String> list = new LinkedList<String>();
			for(int j = 0; j < s.get(i).length(); j++)
			{
				// se[i][j] = s.get(i).substring(j, j + 1);
				String sse = s.get(i).substring(j, j + 1);
				if(sse == null)
					sse = " ";
				list.add(sse);
				// System.out.println(list.get(j) + " " + i + " " + j);
			}
			sList.add(list);
			// System.out.println(sList.get(i).size() + " SIZE" + i);
		}
		// System.out.println(sList.get(0).size() + " SIZE");
		String[][] newString = convertListToArray(sList);
		return newString;
	}
	private String[][] convertListToArray(LinkedList<LinkedList<String> > list2d)
	{
		String[][] array = new String[list2d.size()][list2d.get(0).size()];
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array[i].length; j++)
			{
				array[i][j] = list2d.get(i).get(j);
				// System.out.print(array[i][j]);
			}
			// System.out.println("");
		}
		return array;
	}
	public boolean isValid()
	{
		return hasBeenCreated;
	}
	
	public class OnButtonClick implements ActionListener
	{
	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			Object control = e.getSource();
			for(int i = 0; i < gridArray.length; i ++)
			{
				for (int j = 0; j < gridArray[i].length; j++)
				{
					if(control == gridArray[i][j])
					{
						if(gridArray[i][j].getLabel() == " ")
						{
							gridArray[i][j].setLabel("x");
						}
						else if(gridArray[i][j].getLabel() == "x") 
						{
							gridArray[i][j].setLabel("S");
						}
						else if(gridArray[i][j].getLabel() == "S")
						{
							gridArray[i][j].setLabel("G");
						}
						else if(gridArray[i][j].getLabel() == "G")
						{
							gridArray[i][j].setLabel(" ");
						}
					}
				}
			}
			
		}
		
	}
}



