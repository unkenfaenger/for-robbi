package de.slothsoft.multiplier.ui;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import de.slothsoft.multiplier.Input;

/**
 * This is the main window
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

public class InputDialog extends JDialog {

	private static final long serialVersionUID = -6542618771529532760L;

	private final Input input;

	private JSpinner factor1;
	private JSpinner factor2;

	public InputDialog(Frame owner, Input input) {
		super(owner);
		setTitle("Input Dialog");
		this.input = input;
		setModal(true);
		createControls();
		pack();
		setLocationRelativeTo(owner);
	}

	private void createControls() {
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		content.setLayout(new GridBagLayout());
		add(content);

		int y = 0;
		content.add(new JLabel("Enter your input here"), GridBagData.forLabel(0, y).gridwidth(2));
		y++;

		content.add(new JLabel("Factor 1"), GridBagData.forLabel(0, y));
		this.factor1 = new JSpinner(new SpinnerNumberModel(this.input.getFactor1(), 1, Integer.MAX_VALUE, 1));
		content.add(this.factor1, GridBagData.forControl(1, y));
		y++;

		content.add(new JLabel("Factor 2"), GridBagData.forLabel(0, y));
		this.factor2 = new JSpinner(new SpinnerNumberModel(this.input.getFactor2(), 1, Integer.MAX_VALUE, 1));
		content.add(this.factor2, GridBagData.forControl(1, y));
		y++;

		ButtonBar buttons = new ButtonBar();
		buttons.addButton("OK", this::okPressed);
		buttons.addButton("Cancel", this::cancelPressed);
		content.add(buttons, GridBagData.forControl(0, y).gridwidth(2));
	}

	private void okPressed(ActionEvent event) {
		this.input.setFactor1(((Integer) this.factor1.getValue()).intValue());
		this.input.setFactor2(((Integer) this.factor2.getValue()).intValue());
		dispose();
	}

	private void cancelPressed(ActionEvent event) {
		dispose();
	}
}
