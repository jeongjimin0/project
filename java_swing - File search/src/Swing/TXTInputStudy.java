package Swing;

import java.awt.BorderLayout;

import javax.swing.*;

public class TXTInputStudy extends JFrame{
	
	public TXTInputStudy() {
	
		JPanel panel = new JPanel();
		JLabel label = new JLabel("텍스트를 입력하시오.");
		JTextArea txt = new JTextArea();
		
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		panel.add(txt, BorderLayout.CENTER);
		add(panel);
		
		setVisible(true);
		setSize(500, 140);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TXTInputStudy();
		System.out.println("hi");
	}

}
