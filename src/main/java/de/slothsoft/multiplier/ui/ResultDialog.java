package de.slothsoft.multiplier.ui;

import java.awt.Frame;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import de.slothsoft.multiplier.Result;
import de.slothsoft.multiplier.graphics.ResultPainter;

/**
 * This is the main window
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

public class ResultDialog extends JDialog {

	private static final long serialVersionUID = -6542618771529532760L;

	private final Result result;

	public ResultDialog(Frame owner, Result result) {
		super(owner);
		setTitle("Result Dialog");
		this.result = result;
		setModal(true);
		createControls();
		pack();
		setLocationRelativeTo(owner);
	}

	private void createControls() {
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		add(content);

		PainterPanel resultPanel = new PainterPanel(new ResultPainter(this.result));
		content.add(resultPanel, GridBagData.forPanel(0, 0));

		ButtonBar buttons = new ButtonBar();
		buttons.addButton("Close", e -> dispose());
		content.add(buttons, GridBagData.forControl(0, 1));
	}
}
