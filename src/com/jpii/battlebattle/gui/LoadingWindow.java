package com.jpii.battlebattle.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.jpii.battlebattle.io.ClientUpdateService;
import com.jpii.battlebattle.io.GameUpdateService;

public class LoadingWindow extends JFrame {

	private static final long serialVersionUID = -3989467303025155942L;
	private JProgressBar progressBar;
	private JLabel lblLoading;
	
	public LoadingWindow() {
		setTitle("BattleBattle");
		getContentPane().setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 48, 414, 23);
		getContentPane().add(progressBar);

		lblLoading = new JLabel("Loading...");
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoading.setBounds(10, 23, 414, 14);
		getContentPane().add(lblLoading);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(450,120);
		setResizable(false);
		setVisible(true);
		
		new LoadingThread(this).run();
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public JLabel getLabel() {
		return lblLoading;
	}
}

class LoadingThread extends Thread {
	
	private LoadingWindow frame;
	private ClientUpdateService clientUpdateService;
	private GameUpdateService gameUpdateService;
	
	public LoadingThread(LoadingWindow frame) {
		this.frame = frame;
	}

	public void run() {
		
		// TODO: Loading sequence
		// [X] Check for client updates
		// [ ] Check for and register local games
		// [X] Check for and register online games
		// [ ] Pair local games with online games
		// [ ] Check for paired game updates
		// [ ] Update and verify games
		
		frame.getLabel().setText("Checking for BattleBattle updates...");
		frame.getProgressBar().setIndeterminate(true);
		
		clientUpdateService = new ClientUpdateService();
		
		frame.getLabel().setText("Searching for installed games...");
		frame.getProgressBar().setIndeterminate(true);
		pause(1000);
		
		frame.getLabel().setText("Retrieving list of games from server...");
		frame.getProgressBar().setIndeterminate(true);
		pause(1000);
				
		frame.getLabel().setText("Checking for game updates...");
		frame.getProgressBar().setIndeterminate(true);
		
		gameUpdateService = new GameUpdateService();
		pause(1000);
		
		frame.getLabel().setText("Verifying updates...");
		frame.getProgressBar().setIndeterminate(true);
		
		pause(100);
		
		frame.setVisible(false);
		frame.dispose();
		
		new MainWindow(clientUpdateService);
	}
	
	public void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
