package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class SwingStudy extends JFrame{

	public SwingStudy() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Can yo speak English?");
		JButton yBtn = new JButton("Yes");
		JButton nBtn = new JButton("No");
		
		panel.add(label);
		panel.add(yBtn);
		panel.add(nBtn);
		add(panel);
		
		yBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Not need to change language options.");
			}
		});
		
		nBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Need to change language options.");
			}
		});

		
		setVisible(true);
		setSize(560, 120);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SwingStudy();
	}
}
