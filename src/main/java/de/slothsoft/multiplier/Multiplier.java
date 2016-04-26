package de.slothsoft.multiplier;

import javax.swing.UIManager;

import de.slothsoft.multiplier.ui.MainFrame;

/**
 * This class is the starting point of the entire thing
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

public class Multiplier {

	private static MainFrame mainFrame;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
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
}
