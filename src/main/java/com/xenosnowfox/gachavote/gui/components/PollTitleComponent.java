package com.xenosnowfox.gachavote.gui.components;

import com.xenosnowfox.gachavote.gui.TextDocumentListener;
import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Component that gives the user the ability to edit the title of the poll.
 */
public class PollTitleComponent extends Component {

	/**
	 * Parent panel.
	 */
	private final JPanel panel;

	/**
	 * Input field of the poll title.
	 */
	private final JTextField titleTextField;

	/**
	 * Listener that is fired when the text of the poll title changes.
	 */
	private final TextDocumentListener textDocumentListener = new TextDocumentListener();

	/**
	 * Default constructor.
	 */
	public PollTitleComponent() {
		this.panel = new JPanel();
		this.panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagLayout layout = new GridBagLayout();
		this.panel.setLayout(layout);
		GridBagConstraints layoutConstraints = new GridBagConstraints();

		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.weightx = 1;
		final JLabel label = new JLabel();
		label.setText("Poll Title");
		this.panel.add(label, layoutConstraints);

		layoutConstraints.gridy = 1;
		this.panel.add(Box.createRigidArea(new Dimension(0, 5)), layoutConstraints);

		layoutConstraints.gridy = 2;
		this.titleTextField = new JTextField();
		this.titleTextField.getDocument()
				.addDocumentListener(this.textDocumentListener);
		this.panel.add(this.titleTextField, layoutConstraints);

	}

	/**
	 * Defines a listener object to be called when the poll title changes.
	 *
	 * @param withListener listener
	 */
	public void setOnTextChangeListener(final Consumer<String> withListener) {
		this.textDocumentListener.setOnTextChangeListener(withListener);
	}

	/**
	 * Defines the text to use as the poll title, replacing the existing text within the text field.
	 *
	 * @param withText Poll Title
	 */
	public void setTitle(@NonNull final String withText) {
		this.titleTextField.setText(withText);
	}

	/**
	 * Returns the poll title text.
	 *
	 * @return Poll Title
	 */
	public String getTitle() {
		return this.titleTextField.getText();
	}

	/**
	 * Defines whether the text field is enabled. When not enable the user will be unable to change the text field.
	 *
	 * @param isEnabled flag indicating if the component is enabled.
	 */
	public void setEnabled(final boolean isEnabled) {
		this.titleTextField.setEnabled(isEnabled);
	}

	/**
	 * Returns whether the component is enabled or not.
	 *
	 * @return {@code true} if the component is enabled or {@code false} otherwise.
	 */
	public boolean isEnabled() {
		return this.titleTextField.isEnabled();
	}

	/**
	 * Returns the base/parent component.
	 *
	 * @return Base/Parent component.
	 */
	public Component getBaseComponent() {
		return this.panel;
	}
}
