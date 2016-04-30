package de.slothsoft.multiplier;

import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.slothsoft.multiplier.ui.MainFrame;

/**
 * This class is the starting point of the entire thing
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

public class Multiplier {

	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(Multiplier.class);
	private static MainFrame mainFrame;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGui();
			}
		});
	}

	private static void createAndShowGui() {
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public static Preferences getPreferences() {
		return PREFERENCES;
	}
}
