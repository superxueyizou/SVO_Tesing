/* *************************************************************************************
 * Copyright (C) Xueyi Zou - All Rights Reserved
 * Written by Xueyi Zou <xz972@york.ac.uk>, 2015
 * You are free to use/modify/distribute this file for whatever purpose!
 -----------------------------------------------------------------------
 |THIS FILE IS DISTRIBUTED "AS IS", WITHOUT ANY EXPRESS OR IMPLIED
 |WARRANTY. THE USER WILL USE IT AT HIS/HER OWN RISK. THE ORIGINAL
 |AUTHORS AND COPPELIA ROBOTICS GMBH WILL NOT BE LIABLE FOR DATA LOSS,
 |DAMAGES, LOSS OF PROFITS OR ANY OTHER KIND OF LOSS WHILE USING OR
 |MISUSING THIS SOFTWARE.
 ------------------------------------------------------------------------
 **************************************************************************************/

package dominant;

import javax.swing.JOptionPane;

import tools.UTILS;
import weka.gui.GUIChooser;




public class DataMiner 
{

	public DataMiner() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void execute(String fileName)
	{
		try 
		{
			UTILS.CSV2Arff(fileName+"Dataset.csv", fileName+"Dataset.arff");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		GUIChooser gc = new GUIChooser();
		gc.showExplorer(fileName+"Dataset.arff");		
		
	}
	
	public static void main(String[] args) throws Exception
	{
		String fileName;
		String result = JOptionPane.showInputDialog(null, "Please paste the input file name without \"Dataset.csv\":", "File Name",JOptionPane.PLAIN_MESSAGE);

		fileName=result.trim();
		DataMiner dm = new DataMiner();
		dm.execute(fileName);
	}
	
	
	
	

}
