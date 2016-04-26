package de.slothsoft.multiplier.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonBar extends JPanel {

	private static final long serialVersionUID = -2124770845363906742L;

	private JPanel buttonPanel;

	public ButtonBar() {
		setLayout(new BorderLayout());
		add(new JLabel(), BorderLayout.CENTER);

		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(1, 0, GridBagData.DEFAULT_GAP, GridBagData.DEFAULT_GAP));
		add(this.buttonPanel, BorderLayout.EAST);
	}

	public JButton addButton(String text, ActionListener listener) {
		JButton button = new JButton(text);
		button.addActionListener(listener);
		addButton(button);
		return button;
	}

	public void addButton(JButton button) {
		this.buttonPanel.add(button);
	}

}
