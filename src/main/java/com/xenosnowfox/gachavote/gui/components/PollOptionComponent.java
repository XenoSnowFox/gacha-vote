package com.xenosnowfox.gachavote.gui.components;

import lombok.NonNull;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * Component that gives the user the ability to edit a single votable option.
 */
public class PollOptionComponent implements ActionListener {

	/**
	 * Parent panel.
	 */
	private final JPanel panel;

	/**
	 * Input field of the option's label.
	 */
	private final JTextField labelTextField;

	/**
	 * Label to display the number of votes this option contains.
	 */
	private final JLabel voteCountLabel;

	/**
	 * Button that fires the deletion listener.
	 */
	private final Button deleteButton;

	/**
	 * Listener that is called when the option's delete button is clicked.
	 */
	@Setter
	private Consumer<PollOptionComponent> onDeletionListener;

	/**
	 * Default constructor.
	 */
	public PollOptionComponent() {
		final GridBagLayout layout = new GridBagLayout();
		final GridBagConstraints constraints = new GridBagConstraints();

		this.panel = new JPanel(layout);
		this.panel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));

		this.labelTextField = new JTextField();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		layout.setConstraints(this.labelTextField, constraints);
		this.panel.add(this.labelTextField);

		constraints.weightx = 0;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.gridx++;
		this.panel.add(Box.createRigidArea(new Dimension(8, 8)), constraints);

		this.voteCountLabel = new JLabel("0");
		this.voteCountLabel.setPreferredSize(new Dimension(100, this.voteCountLabel.getPreferredSize().height + 4));
		this.voteCountLabel.setMinimumSize(new Dimension(100, this.voteCountLabel.getMinimumSize().height));
		this.voteCountLabel.setMaximumSize(new Dimension(100, this.voteCountLabel.getMaximumSize().height + 4));
		this.voteCountLabel.setBorder(BorderFactory.createEtchedBorder());
		this.voteCountLabel.setHorizontalAlignment(JTextField.CENTER);
		constraints.gridx++;
		constraints.fill = GridBagConstraints.VERTICAL;
		layout.setConstraints(this.voteCountLabel, constraints);
		this.panel.add(this.voteCountLabel);

		constraints.gridx++;
		constraints.fill = GridBagConstraints.VERTICAL;
		this.panel.add(Box.createRigidArea(new Dimension(8, 8)), constraints);

		this.deleteButton = new Button("Delete");
		this.deleteButton.addActionListener(this);
		constraints.gridx++;
		constraints.fill = GridBagConstraints.NONE;
		layout.setConstraints(this.deleteButton, constraints);
		this.panel.add(this.deleteButton);

	}

	/**
	 * Defines whether the text field is enabled. When not enable the user will be unable to change the text field.
	 *
	 * @param isEnabled
	 * 		flag indicating if the component is enabled.
	 */
	public void setEnabled(final boolean isEnabled) {
		this.labelTextField.setEnabled(isEnabled);
		this.deleteButton.setEnabled(isEnabled);
	}

	/**
	 * Returns whether the component is enabled or not.
	 *
	 * @return {@code true} if the component is enabled or {@code false} otherwise.
	 */
	public boolean isEnabled() {
		return this.labelTextField.isEnabled();
	}

	/**
	 * Defines the text to use as the option's label.
	 *
	 * @param withText
	 * 		Option label.
	 */
	public void setLabelText(@NonNull final String withText) {
		this.labelTextField.setText(withText);
	}

	/**
	 * Returns the text being used as the option's label.
	 *
	 * @return Option label.
	 */
	public String getLabelText() {
		return this.labelTextField.getText();
	}

	/**
	 * Defines the number of votes the option has obtained.
	 *
	 * @param withCount
	 * 		current vote count.
	 */
	public void setVoteCount(final int withCount) {
		if (withCount < 0) {
			throw new IllegalArgumentException("Vote count must be zero or greater.");
		}

		this.voteCountLabel.setText(String.valueOf(withCount));
	}

	/**
	 * Returns the base/parent component.
	 *
	 * @return Base/Parent component.
	 */
	public Component getBaseComponent() {
		return this.panel;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (this.onDeletionListener != null && e.getActionCommand().equalsIgnoreCase("delete")) {
			this.onDeletionListener.accept(this);
		}
	}
}
