package com.xenosnowfox.gachavote.gui;

import lombok.Setter;
import lombok.SneakyThrows;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

/**
 * Simplified listener for a text field.
 */
public class TextDocumentListener implements DocumentListener {

	/**
	 * The current value of the text field.
	 */
	private String currentTest = "";

	/**
	 * Listener to call when the text of the input field changes.
	 */
	@Setter
	public Consumer<String> onTextChangeListener;

	@SneakyThrows
	@Override
	public final void insertUpdate(final DocumentEvent e) {
		String str = e.getDocument().getText(0, e.getDocument().getLength());
		if (!currentTest.equalsIgnoreCase(str) && this.onTextChangeListener != null) {
			this.onTextChangeListener.accept(str);
		}
		this.currentTest = str;
	}

	@SneakyThrows
	@Override
	public final void removeUpdate(final DocumentEvent e) {
		String str = e.getDocument().getText(0, e.getDocument().getLength());
		if (!currentTest.equalsIgnoreCase(str) && this.onTextChangeListener != null) {
			this.onTextChangeListener.accept(str);
		}
		this.currentTest = str;
	}

//	@SneakyThrows
	@Override
	public final void changedUpdate(final DocumentEvent e) {
	}
}
