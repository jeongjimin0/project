package inzent.project.swing;

import java.awt.BorderLayout;
import inzent.project.swing.*;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CreateSwing extends JFrame implements ActionListener, KeyListener {

	// 필요한 필드 정의하기
	protected static JTextField inputName;
	protected static JTextField inputName2;
	protected static DefaultListModel<String> model;
	private JList jList;
	
	// 생성자

	public CreateSwing() {

		
		// 1. 컴포넌트 창을 만든다.
		
		
		setSize(900, 600);
		setLocation(100, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 끄면 프로젝트 종료
		// BorderLayout 객체 전달하기
		setLayout(new BorderLayout());
		
		// 2. input 창을 만든다.
		inputName = new JTextField(20);
		inputName2 = new JTextField(20);
		

		// 3. 검색 버튼 생성
		
		// 페널에 추가할 UI 를 미리 만든다.
		JLabel label = new JLabel("찾을 파일 검색");
		JLabel label2 = new JLabel("확장자 검색");
		JButton addBtn = new JButton("검색");
		addBtn.setActionCommand("add");
		addBtn.addActionListener(this);
		// 삭제 버튼
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(this);
		// 추가 버튼
		JButton createBtn = new JButton("추가");
		createBtn.setActionCommand("create");
		createBtn.addActionListener(this);

		// 페널에 UI 를 추가하고
		JPanel topPanel = new JPanel();
		topPanel.add(label);
		topPanel.add(inputName);
		topPanel.add(label2);
		topPanel.add(inputName2);
		topPanel.add(addBtn);
		topPanel.add(deleteBtn);
		topPanel.add(createBtn);

		// 페널째로 프레임의 상단에 추가하기
		add(topPanel, BorderLayout.NORTH);

		
		 // 4. 리스트 생성 
		// 목록을 출력할 UI
		jList = new JList<>();
		
		// JList 에 출력할 데이터를 가지고 있는 모델 객체
		model = new DefaultListModel<>();

		// 모델을 JList 에 연결하기
		jList.setModel(model);

		// 스크롤 페널에 JList 넣어주기
		JScrollPane scroll = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// 프레임에 스크롤 페널 추가하기
		add(scroll, BorderLayout.CENTER);

		// JTextField 에 키 리스너 등록하기
		inputName.addKeyListener(this);

		setVisible(true);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("keyReleased");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("keyTyped");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	// 5. 이벤트 생성
	@Override
	public void actionPerformed(ActionEvent e) {
		String rootPath = "C:\\test";
		
		
		// 이벤트가 발생한 버튼에 설정된 action command 읽어오기
		// 6. input text 값 읽어오기
		String name = inputName.getText();
		String ext = inputName2.getText();
		String command = e.getActionCommand();
		

		if (command.equals("add")) {

//			ArrayList<String> res = new ArrayList<String>();
//			res.addAll(FileSearchAll.fileSearch(rootPath, name));

			//ArrayList<String> res = FileSearchAll.fileSearch(rootPath, name);
			model.removeAllElements();
			
			ArrayList<String> res = new ArrayList<String>();
			FileSearchAll hh = new FileSearchAll();
			
			// 7. fileSearchAll 클래스의 fileSearch 메서드에 파라미터 값 넣기
			res = hh.fileSearch(rootPath, name, ext);
			
			for (int i = 0; i < res.size(); i++) {
				
				// 10. 파일리스트를 출력되는 리스트에 추가한다.
				model.add(i, res.get(i));
			}
			
		}

		else if (command.equals("delete")) {

//			model.removeAllElements();

		}

		else if (command.equals("create")) {

			new CreateFile();

		}

	}

}
