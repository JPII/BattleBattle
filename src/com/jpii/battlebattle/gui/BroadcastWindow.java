package com.jpii.battlebattle.gui;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.jpii.battlebattle.util.URLUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BroadcastWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel announcementTitle;
	JTextPane announcementText;
	
	public BroadcastWindow(String title, String text, final String url) {
		getContentPane().setLayout(null);
		
		JLabel lblAnnouncement = new JLabel("Announcement:");
		lblAnnouncement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnouncement.setBounds(10, 11, 128, 30);
		getContentPane().add(lblAnnouncement);
		
		announcementTitle = new JLabel(title);
		announcementTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		announcementTitle.setBounds(143, 19, 333, 14);
		getContentPane().add(announcementTitle);
		
		announcementText = new JTextPane();
		announcementText.setText(text);
		announcementText.setEditable(false);
		announcementText.setBounds(10, 44, 466, 221);
		getContentPane().add(announcementText);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnClose.setBounds(382, 270, 94, 30);
		getContentPane().add(btnClose);
		
		JButton moreInfoButton = new JButton("More Info");
		moreInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URLUtils.openURL(url);
			}
		});
		moreInfoButton.setBounds(10, 270, 94, 30);
		getContentPane().add(moreInfoButton);
		
		setSize(500,350);
		setVisible(true);
	}
}