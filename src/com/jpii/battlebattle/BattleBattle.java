package com.jpii.battlebattle;

import java.awt.Dimension;
import java.awt.Font;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import com.jpii.battlebattle.data.Constants;
import com.jpii.battlebattle.data.GameDatabase;
import com.jpii.battlebattle.gui.LoadingWindow;
import com.jpii.battlebattle.io.BattleBattleIO;
import com.jpii.gamekit.GameKit;
import com.jpii.gamekit.debug.Debugger;
import com.jpii.gamekit.exception.InvalidApiLevelException;

public class BattleBattle {
	
	private static Debugger debugInstance;
	private static GameDatabase gameDatabase;
	
	public static void main(String[] args) {
		try {
			GameKit.checkVersion(Constants.GAMEKIT_MIN_API_LEVEL, Constants.GAMEKIT_MAX_API_LEVEL);
		} catch (InvalidApiLevelException e) {
			e.printStackTrace();
		}
		
		setDefaultLookAndFeel();
		
		debugInstance = new Debugger("BattleBattle");
		gameDatabase = new GameDatabase();
		
		if(Constants.DEBUG_MODE)
			debugInstance.showDebugWindow();
		
		BattleBattleIO.run();
		
		debugInstance.printInfo("Opening LoadingWindow");
		new LoadingWindow();
		
		debugInstance.printInfo("BattleBattle initialized successfully");
	}
	
	public static Debugger getDebugger() {
		return debugInstance;
	}
	
	public static GameDatabase getGameDatabase() {
		return gameDatabase;
	}
	
	/**
	 * Attempt to set <code>DefaultLookAndFeel</code> to Nimbus and
	 * alert the users if the process fails.
	 */
	private static void setDefaultLookAndFeel(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
			getDebugger().printError("NimbusLookAndFeel was unable to be loaded, unsuported");
			criticalError(e);
		} catch (ClassNotFoundException e) {
			getDebugger().printError("NimbusLookAndFeel was unable to be loaded, class not found");
			criticalError(e);
		} catch (InstantiationException e) {
			getDebugger().printError("NimbusLookAndFeel was unable to be loaded, instantiation");
			criticalError(e);
		} catch (IllegalAccessException e) {
			getDebugger().printError("NimbusLookAndFeel was unable to be loaded, illegalaccess");
			criticalError(e);
		} catch (Exception e) {
			getDebugger().printError("NimbusLookAndFeel has encountered an error, " + e.getMessage());
			criticalError(e);
		} catch (Error e) {
			getDebugger().printError("NimbusLookAndFeel has encountered an error, " + e.getMessage());
			criticalError(e);
		} catch (Throwable thr) {
			getDebugger().printError("NimbusLookAndFeel has encountered an error, " + thr.getMessage());
			criticalError(thr);
		}
	}
	
	/**
	 * Global method in the event of something terrible. Reports the exception to the user for bug reporting.
	 * @param thr
	 */
	public static void criticalError(Throwable thr) {
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		textArea.setEditable(false);
		StringWriter writer = new StringWriter();
		thr.printStackTrace(new PrintWriter(writer));
		textArea.setText(Constants.CRITICAL_ERROR_HEADER + writer.toString());

		JScrollPane scrollPane = new JScrollPane(textArea);		
		scrollPane.setPreferredSize(new Dimension(350, 150));

		JOptionPane.showMessageDialog(new JFrame(), scrollPane, "Critical Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}
