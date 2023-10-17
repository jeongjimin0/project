package inzent.project.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class CreateFile extends JFrame implements ActionListener, KeyListener {
	
	   private static JTextField inputName;
	   private static JTextArea inputName3;
	   private static DefaultListModel<String> model;
	   private JList jList;
	   
	CreateFile() {
		
		
		  // ① 컴포넌트 창을 만든다.
		
	      setSize(900, 600);
	      setLocation(100, 100);
	      setDefaultCloseOperation(EXIT_ON_CLOSE); // 창을 끄면 프로젝트 종료
	      //BorderLayout 객체 전달하기
	      setLayout(new BorderLayout());
	      
	      //페널에 추가할 UI 를 미리 만든다.
	      JPanel topPanel2 = new JPanel();
	      topPanel2.setSize(500, 300);
	      
	      // ② 검색 생성
	      inputName=new JTextField(10);
	      inputName3=new JTextArea(35, 80);
	      JLabel label=new JLabel("검색할 경로 선택");
	      JButton addBtn=new JButton("검색");
	      addBtn.setActionCommand("add");
	      addBtn.addActionListener(this);
	      //삭제 버튼 
	      JButton deleteBtn=new JButton("삭제");
	      deleteBtn.setActionCommand("delete");
	      deleteBtn.addActionListener(this);

	      //페널에 UI 를 추가하고 
	      JPanel topPanel=new JPanel();
	      topPanel.add(label);
	      topPanel.add(inputName);
	      topPanel.add(addBtn);
	      topPanel.add(deleteBtn);
	      topPanel2.add(inputName3);
	      //페널째로 프레임의 상단에 추가하기
	      add(topPanel, BorderLayout.NORTH);
	      add(topPanel2, BorderLayout.CENTER);

	      //JTextField 에 키 리스너 등록하기 
	      inputName.addKeyListener(this);
	      setVisible(true);
	}


	//ActionListener 인터페이스를 구현해서 강제로 Override 한 메소드 
	@Override
	public void actionPerformed(ActionEvent e) {
	   //이벤트가 발생한 버튼에 설정된 action command 읽어오기 
	
		String name = inputName.getText();
		String name2 = inputName3.getText();
		String command=e.getActionCommand();
		
		if(command.equals("add")) {
		try {
			// 파일 생성하기
			File file = new File(name);
			file.deleteOnExit();
			FileWriter fw = new FileWriter(file, true);
			fw.write(name2);
			fw.flush();
			fw.close();
			System.out.println("Create a file. ( in " + inputName + " ) ");
		} catch ( IOException e1 ) {
			System.out.println("파일 생성 에러");
			System.out.println(e1);
		}
		}
	
	 	
	}
	
	public static void main(String[] args) {
		new CreateFile();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void FileSearchAll(ActionEvent e)

    {
	
}
}