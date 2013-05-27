package com.jpii.battlebattle.gui;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.jpii.battlebattle.util.URLUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JLabel updateTitle;
	JTextPane updateText;
	
	public UpdateWindow(String title, String text, final String url) {
		getContentPane().setLayout(null);
		
		JLabel lblAnnouncement = new JLabel("Update:");
		lblAnnouncement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnouncement.setBounds(10, 11, 74, 30);
		getContentPane().add(lblAnnouncement);
		
		updateTitle = new JLabel("BattleBattle " + text);
		updateTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateTitle.setBounds(80, 19, 333, 14);
		getContentPane().add(updateTitle);
		
		updateText = new JTextPane();
		updateText.setText(text);
		updateText.setEditable(false);
		updateText.setBounds(10, 44, 466, 221);
		getContentPane().add(updateText);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnClose.setBounds(382, 271, 94, 30);
		getContentPane().add(btnClose);
		
		JButton moreInfoButton = new JButton("More Info");
		moreInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URLUtils.openURL(url);
			}
		});
		moreInfoButton.setBounds(10, 271, 94, 30);
		getContentPane().add(moreInfoButton);
		
		setSize(500,350);
		setVisible(true);
	}
}