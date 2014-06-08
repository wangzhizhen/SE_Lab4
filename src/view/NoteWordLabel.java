package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class NoteWordLabel extends JLabel {

	public NoteWordLabel() {
		super();
		super.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
		super.setForeground(Color.DARK_GRAY);
	}

	public void setText(String s) {
		super.setText(s);

	}

	public String toString() {
		return super.getText();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}
