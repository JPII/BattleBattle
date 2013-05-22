package com.jpii.battlebattle.gui;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -3989467303025155942L;
	
	public MainWindow() {
		setTitle("BattleBattle");
		getContentPane().setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		splitPane.setBounds(0, 0, 444, 272);
		getContentPane().add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new MigLayout("", "[46px][189px][89px]", "[14px][14px][14px][23px]"));
		
		JLabel lblGameTitle = new JLabel("Game Title");
		lblGameTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblGameTitle, "cell 0 0 3 1,growx,aligny top");
		
		JLabel lblVersion = new JLabel("version");
		panel.add(lblVersion, "cell 0 1,growx,aligny top");
		
		JLabel lblLatestChanges = new JLabel("Latest changes...");
		lblLatestChanges.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel.add(lblLatestChanges, "cell 0 2 3 1,growx,aligny top");
		
		JButton btnPlay = new JButton("Play");
		panel.add(btnPlay, "cell 2 3,growx,aligny top");
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new MigLayout("", "[121px]", "[14px]"));
		
		JLabel lblGamesGoHere = new JLabel("games go here...");
		lblGamesGoHere.setFont(new Font("Tahoma", Font.ITALIC, 11));
		panel_1.add(lblGamesGoHere, "cell 0 0,growx,aligny top");

		setSize(450,300);
		setResizable(false);
		setVisible(true);	
	}
}