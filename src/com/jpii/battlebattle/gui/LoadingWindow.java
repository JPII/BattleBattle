package com.jpii.battlebattle.gui;

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
		
		frame.getLabel().setText("Checking for BattleBattle updates...");
		frame.getProgressBar().setIndeterminate(true);
		
		clientUpdateService = new ClientUpdateService();
				
		frame.getLabel().setText("Checking for game updates...");
		frame.getProgressBar().setIndeterminate(true);
		
		// TODO
		gameUpdateService = new GameUpdateService();
		pause(1000);
		
		frame.getLabel().setText("Downloading NavalBattle updates...");
		frame.getProgressBar().setIndeterminate(false);
		for(int x = 0; x <= 100; x++) {
			frame.getProgressBar().setValue(x);
			pause(20);
		}
		
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
