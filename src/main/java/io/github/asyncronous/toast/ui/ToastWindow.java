/*
 * This file was made by modmuss50. View the licence file to see what licence this is is on. You can always ask me if you would like to use part or all of this file in your project.
 */

package io.github.asyncronous.toast.ui;

import io.github.asyncronous.toast.ToasterConstants;
import io.github.asyncronous.toast.thread.ToastAnimator;
import me.modmuss50.lib.util.JavaUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main Toaster Notification class
 */
public final class ToastWindow extends JWindow {
	private final JLabel ICON = new JLabel();
	private final JTextArea MESSAGE = new JTextArea();

	public ToastWindow() {
		this.MESSAGE.setFont((Font) UIManager.get(ToasterConstants.FONT));
		this.MESSAGE.setBackground((Color) UIManager.get(ToasterConstants.BG_COLOR));
		this.MESSAGE.setForeground((Color) UIManager.get(ToasterConstants.MSG_COLOR));
		this.MESSAGE.setLineWrap(true);
		this.MESSAGE.setEditable(false);
		this.MESSAGE.setMargin(new Insets(2, 2, 2, 2));
		this.MESSAGE.setWrapStyleWord(true);

		if (!((Boolean) UIManager.get(ToasterConstants.OPAQUE)) && JavaUtil.isJava7OrAbove() && GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice().isWindowTranslucencySupported
						(WindowTranslucency.TRANSLUCENT)) {
			this.setOpacity((Float) UIManager.get(ToasterConstants.OPACITY));
		}

		JPanel CONTENT_PANEL = new JPanel(new BorderLayout(1, 1));
		CONTENT_PANEL.setBackground((Color) UIManager.get(ToasterConstants.BORDER_COLOR));
		JPanel WRAPPER_PANEL = new JPanel(new BorderLayout(2, 2));
		WRAPPER_PANEL.setBackground((Color) UIManager.get(ToasterConstants.BG_COLOR));
		WRAPPER_PANEL.add(this.ICON, BorderLayout.WEST);
		WRAPPER_PANEL.add(this.MESSAGE, BorderLayout.CENTER);
		CONTENT_PANEL.setBorder(BorderFactory.createEtchedBorder());
		CONTENT_PANEL.add(WRAPPER_PANEL);

		this.setFocusable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(250, 100));
		this.getContentPane().add(CONTENT_PANEL);
		this.pack();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

	/**
	 * Gets the text of the message
	 *
	 * @return The text of the message
	 */
	public String getText() {
		return this.MESSAGE.getText();
	}

	/**
	 * Sets the text of the message
	 *
	 * @param text The text you would like to pop
	 */
	public void setText(String text) {
		this.MESSAGE.setText(text);
	}

	/**
	 * Gets the icon of the message
	 *
	 * @return The icon of the message
	 */
	public Icon getIcon() {
		return this.ICON.getIcon();
	}

	/**
	 * Sets the icon of the message
	 *
	 * @param icon The icon you would like to pop
	 */
	public void setIcon(Icon icon) {
		this.ICON.setIcon(icon);
	}

	/**
	 * Called to pop the message
	 */
	public void pop() {
		new ToastAnimator(this).execute();
	}
}