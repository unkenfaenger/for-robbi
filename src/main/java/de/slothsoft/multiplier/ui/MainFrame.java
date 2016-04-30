package de.slothsoft.multiplier.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import de.slothsoft.multiplier.Calculation;
import de.slothsoft.multiplier.Input;
import de.slothsoft.multiplier.Result;
import de.slothsoft.multiplier.graphics.InputPainter;

/**
 * This is the main window
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -6542618771529532760L;

	private Input input = new Input();

	private PainterPanel imagePanel;

	public MainFrame() {
		super("Multiplier");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createControls();
		pack();
		setLocationRelativeTo(null);
	}

	private void createControls() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createMenu(), BorderLayout.NORTH);

		getContentPane().add(createContent(), BorderLayout.CENTER);

	}

	private JMenuBar createMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);

		JMenu calculationMenu = new JMenu("Calculation");
		calculationMenu.add(createItem("Change Input", this::changeInput));
		calculationMenu.add(createItem("Calculate", this::calculate));
		menu.add(calculationMenu);

		return menu;
	}

	private JMenuItem createItem(String text, ActionListener listener) {
		JMenuItem item = new JMenuItem(text);
		item.addActionListener(listener);
		return item;
	}

	private Component createContent() {
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());

		this.imagePanel = new PainterPanel(new InputPainter(this.input));
		content.add(this.imagePanel, GridBagData.forPanel(0, 0));

		ButtonBar buttons = new ButtonBar();
		buttons.addButton("Change Input", this::changeInput);
		buttons.addButton("Calculate", this::calculate);
		content.add(buttons, GridBagData.forControl(0, 1));
		return content;
	}

	private void changeInput(ActionEvent event) {
		InputDialog inputDialog = new InputDialog(this, this.input);
		inputDialog.setVisible(true);
		this.imagePanel.repaint();
	}

	private void calculate(ActionEvent event) {
		Calculation calculation = new Calculation(this.input);
		Result result = calculation.calculate();

		ResultDialog resultDialog = new ResultDialog(this, result);
		resultDialog.setVisible(true);
	}
}
