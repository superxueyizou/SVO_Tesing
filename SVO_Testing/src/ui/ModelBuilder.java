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
package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import java.awt.FileDialog;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.border.TitledBorder;

import sim.display.GUIState;
import sim.engine.SimState;
import tools.CONFIGURATION;
import tools.UTILS;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import dominant.Simulation;

import modeling.SAAModelWithUI;
import java.awt.Color;

public class ModelBuilder extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup selfCollisionAvoidanceAlgorithmGroup = new ButtonGroup();
	private final ButtonGroup selfSelfSeparationAlgorithmGroup = new ButtonGroup();
	private final SimState state;
	private final GUIState stateWithUI;
	private String file;
	private BufferedReader br;
	
	private JButton btnNext;
	private JButton btnFinish;
	private JButton btnOpen;
	private JButton btnLoad;

	/**
	 * @wbp.parser.constructor
	 */
	public ModelBuilder(SimState simState, GUIState guiState) 
	{
		this.state=simState;
		this.stateWithUI=guiState;

		setLayout(null);		
		JRadioButton rdbtnCAEnable = new JRadioButton("CA Enable?");
		rdbtnCAEnable.setBounds(12, 19, 102, 15);
		this.add(rdbtnCAEnable);
		rdbtnCAEnable.setSelected(CONFIGURATION.collisionAvoidanceEnabler);
		rdbtnCAEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JRadioButton)e.getSource()).isSelected())
				{
					CONFIGURATION.collisionAvoidanceEnabler = true;
				} else {
					
					CONFIGURATION.collisionAvoidanceEnabler = false;
				}
			}
		});
		
		
		JRadioButton rdbtnSSEnable = new JRadioButton("SS Enable?");
		rdbtnSSEnable.setEnabled(false);
		rdbtnSSEnable.setBounds(164, 19, 102, 15);
		this.add(rdbtnSSEnable);
		rdbtnSSEnable.setSelected(CONFIGURATION.selfSeparationEnabler);
		rdbtnSSEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JRadioButton)e.getSource()).isSelected())
				{
					CONFIGURATION.selfSeparationEnabler = true;
				} else {
					
					CONFIGURATION.selfSeparationEnabler = false;
				}
			}
		});
		

		JRadioButton rdbtnAccidentDetectorEnable = new JRadioButton("AccidentDetector Enable?");
		rdbtnAccidentDetectorEnable.setBounds(12, 53, 229, 15);
		this.add(rdbtnAccidentDetectorEnable);
		rdbtnAccidentDetectorEnable.setSelected(CONFIGURATION.accidentDetectorEnabler);
		rdbtnAccidentDetectorEnable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JRadioButton)e.getSource()).isSelected())
				{
					CONFIGURATION.accidentDetectorEnabler = true;
				} else {
					
					CONFIGURATION.accidentDetectorEnabler = false;
				}
			}
		});
		
		JRadioButton rdbtnSensorValueUncertainty = new JRadioButton("Sensor value uncertainty?");
		rdbtnSensorValueUncertainty.setBounds(12, 82, 229, 15);
		this.add(rdbtnSensorValueUncertainty);
		rdbtnSensorValueUncertainty.setSelected(CONFIGURATION.sensorValueUncertainty);
		rdbtnSensorValueUncertainty.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if(((JRadioButton)e.getSource()).isSelected())
				{
					CONFIGURATION.sensorValueUncertainty = true;
				} else {
					
					CONFIGURATION.sensorValueUncertainty = false;
				}
			}
		});
		
	
		JLabel lblModelbuilderSetting = new JLabel("ModelBuilder Setting");
		lblModelbuilderSetting.setBounds(12, 120, 165, 15);
		this.add(lblModelbuilderSetting);
			
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String result = JOptionPane.showInputDialog(null, "copy and paste:", "Genome",JOptionPane.PLAIN_MESSAGE);

				if(result!=null && !result.isEmpty())
				{
					result = result.trim();
					String[] pArr= result.split("\\s+");
//						System.out.println(pArr[1]);
													
					CONFIGURATION.selfDestDist= Double.parseDouble(pArr[0]);
					CONFIGURATION.selfPrefSpeed=Double.parseDouble(pArr[1]);
					
					CONFIGURATION.headOnSelected= Double.parseDouble(pArr[2]);
					CONFIGURATION.headOnOffset=Double.parseDouble(pArr[3]);
					CONFIGURATION.headOnIsRightSide= Double.parseDouble(pArr[4]);			
					CONFIGURATION.headOnPrefSpeed=Double.parseDouble(pArr[5]);
					
		    		CONFIGURATION.crossingSelected = Double.parseDouble(pArr[6]);
		    		CONFIGURATION.crossingEncounterAngle=Double.parseDouble(pArr[7]);
		    		CONFIGURATION.crossingIsRightSide= Double.parseDouble(pArr[8]);
		    		CONFIGURATION.crossingPrefSpeed =Double.parseDouble(pArr[9]);
		    		
		    		CONFIGURATION.tailApproachSelected = Double.parseDouble(pArr[10]);
		    		CONFIGURATION.tailApproachOffset= Double.parseDouble(pArr[11]);
		    		CONFIGURATION.tailApproachIsRightSide=Double.parseDouble(pArr[12]);
		    		CONFIGURATION.tailApproachPrefSpeed =Double.parseDouble(pArr[13]);		    		
				}
			
				SAAConfigurator newFrame = new SAAConfigurator(state, stateWithUI);
				newFrame.setBounds(1500+80, 404, 340,786); // for windows: frame.setBounds(1500+40, 380, 380,700);
				newFrame.setVisible(true);
				newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
				if(br!=null)
				{
					System.err.println("you forgoet to close the file");
				}
				
				((SAAConfigurator)((JButton)e.getSource()).getRootPane().getParent()).dispose();				
				
//					System.out.println(CONFIGURATION.selfDestDist);
			}
		});
		btnLoad.setBounds(12, 147, 77, 25);
		this.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			       
				String result = JOptionPane.showInputDialog(null, "please add comments:", "Comments",JOptionPane.PLAIN_MESSAGE);
			
				String comment = "";
				if(result!=null && !result.isEmpty())
				{
					comment = result.trim();
				}
				
	        	StringBuilder dataItem = new StringBuilder();
	        	dataItem.append(comment+",");
	        	dataItem.append(CONFIGURATION.selfDestDist+",");
	        	dataItem.append(CONFIGURATION.selfPrefSpeed+",");
	        	dataItem.append(CONFIGURATION.headOnSelected+",");
	        	dataItem.append(CONFIGURATION.headOnOffset+",");
	        	dataItem.append(CONFIGURATION.headOnIsRightSide+",");
	        	dataItem.append(CONFIGURATION.headOnPrefSpeed+",");
	        	dataItem.append(CONFIGURATION.crossingSelected+",");
	        	dataItem.append(CONFIGURATION.crossingEncounterAngle+",");
	        	dataItem.append(CONFIGURATION.crossingIsRightSide+",");
	        	dataItem.append(CONFIGURATION.crossingPrefSpeed+",");
	        	dataItem.append(CONFIGURATION.tailApproachSelected+",");	        	
	        	dataItem.append(CONFIGURATION.tailApproachOffset+",");
	        	dataItem.append(CONFIGURATION.tailApproachIsRightSide+",");
	        	dataItem.append(CONFIGURATION.tailApproachPrefSpeed);
	        	
	        	UTILS.writeDataItem2CSV("./src/tools/ChallengingDB.csv", dataItem.toString(), true);
        		
				}					
		});
		btnSave.setBounds(211, 147, 77, 25);
		this.add(btnSave);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setForeground(Color.YELLOW);
		panel.setBounds(12, 184, 276, 87);
		add(panel);
		panel.setLayout(null);
		
		
		final JLabel lblFile = new JLabel("filename");
		lblFile.setBounds(88, 12, 176, 26);
		panel.add(lblFile);
		
		btnOpen = new JButton("open");
		btnOpen.setBounds(12, 13, 70, 25);
		panel.add(btnOpen);
		btnOpen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				FileDialog fd = new FileDialog((SAAConfigurator)((JButton)e.getSource()).getRootPane().getParent(), "select a file", FileDialog.LOAD);
				fd.setDirectory("/home/viki/GitLocal/Framework/UAS/");
				fd.setFile("*.csv");
				fd.setVisible(true);
				String filename = fd.getFile();
				file= fd.getDirectory()+fd.getFile();
				lblFile.setText(filename);
				System.out.println(file);
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
					br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnNext.setEnabled(true);
				btnFinish.setEnabled(true);
				btnOpen.setEnabled(false);
				btnLoad.setEnabled(false);
				
			}
		});
		
		btnNext = new JButton("Next");// won't refresh the panel
		btnNext.setBounds(12, 50, 66, 25);
		panel.add(btnNext);
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{					
					String line = br.readLine();
//					System.out.println(line);
					if(line!=null && !line.isEmpty())
					{
						line = line.trim();
						String[] pa= line.split(";");
						String[] pArr = new String[14];
//						System.out.println(pa.length);
						for (int i=1; i<15; i++)
						{
							pArr[i-1]=pa[i];
						}
//							System.out.println(pArr[1]);
														
						CONFIGURATION.selfDestDist= Double.parseDouble(pArr[0]);
						CONFIGURATION.selfPrefSpeed=Double.parseDouble(pArr[1]);
						
						CONFIGURATION.headOnSelected= Double.parseDouble(pArr[2]);
						CONFIGURATION.headOnOffset=Double.parseDouble(pArr[3]);
						CONFIGURATION.headOnIsRightSide= Double.parseDouble(pArr[4]);			
						CONFIGURATION.headOnPrefSpeed=Double.parseDouble(pArr[5]);
						
			    		CONFIGURATION.crossingSelected = Double.parseDouble(pArr[6]);
			    		CONFIGURATION.crossingEncounterAngle=Double.parseDouble(pArr[7]);
			    		CONFIGURATION.crossingIsRightSide= Double.parseDouble(pArr[8]);
			    		CONFIGURATION.crossingPrefSpeed =Double.parseDouble(pArr[9]);
			    		
			    		CONFIGURATION.tailApproachSelected = Double.parseDouble(pArr[10]);
			    		CONFIGURATION.tailApproachOffset= Double.parseDouble(pArr[11]);
			    		CONFIGURATION.tailApproachIsRightSide=Double.parseDouble(pArr[12]);
			    		CONFIGURATION.tailApproachPrefSpeed =Double.parseDouble(pArr[13]);		    		
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			
//				SAAConfigurator newFrame = new SAAConfigurator(state, stateWithUI);
//				newFrame.setBounds(1500+80, 404, 340,786); // for windows: frame.setBounds(1500+40, 380, 380,700);
//				newFrame.setVisible(true);
//				newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
//				
//				((SAAConfigurator)((JButton)e.getSource()).getRootPane().getParent()).dispose();			
			}
		});
		
		btnFinish = new JButton("Finish");
		btnFinish.setBounds(120, 50, 76, 25);
		panel.add(btnFinish);
		btnFinish.setEnabled(false);
		btnFinish.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(br!=null)
				{
					// Done with the file
					try {
						br.close();
						br = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				btnNext.setEnabled(false);
				btnFinish.setEnabled(false);
				btnOpen.setEnabled(true);
				btnLoad.setEnabled(true);
			}
		});		
		

		
		JLabel lblDestDist = new JLabel("Dest Dist");
		lblDestDist.setBounds(12, 272, 65, 15);
		this.add(lblDestDist);
		
		JLabel lblDestAngle = new JLabel("Dest Angle");
		lblDestAngle.setBounds(12, 299, 77, 15);
		this.add(lblDestAngle);
		
		JSlider destDistSlider = new JSlider();
		destDistSlider.setSnapToTicks(true);
		destDistSlider.setPaintLabels(true);		
		destDistSlider.setMaximum((int)(1.1*CONFIGURATION.selfDestDist/CONFIGURATION.lengthScale));
		destDistSlider.setMinimum((int)(0.5*CONFIGURATION.selfDestDist/CONFIGURATION.lengthScale));
		destDistSlider.setValue((int)(CONFIGURATION.selfDestDist/CONFIGURATION.lengthScale));
		destDistSlider.setBounds(95, 273, 200, 16);
		this.add(destDistSlider);
		destDistSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				CONFIGURATION.selfDestDist = source.getValue()*CONFIGURATION.lengthScale;
			}
		});
		
		JSlider destAngleSlider = new JSlider();
		destAngleSlider.setValue((int)Math.toDegrees(CONFIGURATION.selfDestAngle));
		destAngleSlider.setMinimum(-180);
		destAngleSlider.setMaximum(180);
		destAngleSlider.setBounds(95, 299, 200, 16);
		this.add(destAngleSlider);
		destAngleSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				CONFIGURATION.selfDestAngle = Math.toRadians(source.getValue());
			}
		});
		
		
		
	}
}
