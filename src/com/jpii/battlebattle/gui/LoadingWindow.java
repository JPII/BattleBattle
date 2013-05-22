package com.jpii.battlebattle.gui;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

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
		lblLoading.setBounds(183, 23, 54, 14);
		getContentPane().add(lblLoading);
		
		progressBar.setIndeterminate(true);

		setSize(450,120);
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
	
	public LoadingThread(LoadingWindow frame) {
		this.frame = frame;
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		frame.getProgressBar().setIndeterminate(false);
		
		for(int x = 0; x < 100; x++) {
			frame.getProgressBar().setValue(x);
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
