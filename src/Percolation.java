import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Percolation
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Percolation();
	}
	
	JTextField hField, wField;
	JButton setButton, chooseGridButton, startPerc;
	Grid grid;
	JFrame mainFrame;
	public Percolation()
	{
		mainFrame = new JFrame();
		JPanel mainPanel = (JPanel)mainFrame.getContentPane();
		mainFrame.setTitle("Percolation");
		// mainFrame.setSize(500,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel label1 = new JLabel("Grid Dimensions: ");
		
		mainPanel.add(label1);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new BoxLayout(secondPanel, BoxLayout.X_AXIS));
	
		// add buttons with textinput and dimensions
		JLabel widthLabel = new JLabel("Width: ");
		secondPanel.add(widthLabel);
		wField = new JTextField(3);
		secondPanel.add(wField);
		
		JLabel heightLabel = new JLabel("Height: ");
		secondPanel.add(heightLabel);
		hField = new JTextField(3);
		secondPanel.add(hField);
		
		mainPanel.add(secondPanel);
		
		setButton = new JButton("Set up grid");
		
		ButtonListener listener = new ButtonListener();
		setButton.addActionListener(listener);
		
		mainPanel.add(setButton);
		
		chooseGridButton = new JButton("Choose Grid From File");
		chooseGridButton.addActionListener(listener);
		mainPanel.add(chooseGridButton);
		
		startPerc = new JButton("Begin");
		startPerc.setEnabled(false);
		startPerc.addActionListener(listener);
		mainPanel.add(startPerc);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		
		
	}
	
	public LinkedList<LinkedList<Character> > inputGrid()
	{
		LinkedList<LinkedList<Character> > list=  new LinkedList<LinkedList<Character> >();
		return list;
	}
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			Object control = e.getSource();
			if(control == setButton)
			{
				int gridWidth, gridHeight;
				try
				{
					gridWidth = Integer.parseInt(wField.getText().toString());
				}
				catch(NumberFormatException ex)
				{
					gridWidth = 10;
				}
				try
				{
					gridHeight = Integer.parseInt(hField.getText().toString());
				}
				catch(NumberFormatException ex)
				{
					gridHeight = 10;
				}
				// System.out.println(gridHeight + " height " + gridWidth + " width");
				grid = new Grid();
				grid.setDimen(gridHeight, gridWidth);
				grid.showGrid();
				startPerc.setEnabled(true);
				// System.out.println( Integer.parseInt(wField.getText().toString()));
			}
			else if(control == chooseGridButton)
			{
				
				/*JFileChooser chooser = new JFileChooser();
				int choice = chooser.showOpenDialog(chooser);
				if(choice != JFileChooser.OPEN_DIALOG) return;
				
				File chosenFile = chooser.getSelectedFile();
				
				*/
				File chosenFile = null;
				FileDialog fDialog = new FileDialog(mainFrame, "Load File", FileDialog.LOAD);
				fDialog.setDirectory("C:\\");
				fDialog.setFile("*.txt");
				
				// fix
				fDialog.setVisible(true);
				String path = fDialog.getDirectory() + fDialog.getFile();
				System.out.println(path);
				if(path != null)
					chosenFile = new File(path);
				else
					return;
				
				//finish
				System.out.println(chosenFile);
				
				grid = new Grid();
				String[][] gridArray = grid.readInGridFromFile(chosenFile);
				if(gridArray != null)
				{
					grid.showGrid(gridArray);
					startPerc.setEnabled(true);
				}
				// System.out.print(chosenFile);
				
			}
			else if (control == startPerc)
			{
				PercolationGrid pGrid = new PercolationGrid(grid.convertedArray);
				
			}
			
		}
	}
}
