package Swing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class t2 extends JFrame {

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
    // 인자 ( 사용자가 보여지는 텍스트, 사용 가능한 텍스트... )

    public static void main(String[] args) {
        t2 fileJChooser = new t2();
        fileJChooser.printFilePath();
    }

    public void printFilePath() {

        JButton button = new JButton("파일 열기");
        JLabel label = new JLabel("파일 없음");

        setVisible(true); // 보이게 할지 여부
        setLayout(new FlowLayout()); // 레이아웃 변경
        add(button); // 컨테이너에 버튼 추가
        add(label); // 컨테이너에 레이블 추가
        setSize(500, 300); //컨테이너 사이즈
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우의 x를 누르면 종료


        button.addActionListener(new ActionListener() { // 익명 객체
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileFilter(filter); // 파일 필터 추가
                int returnVal = fileChooser.showOpenDialog(getParent());
                // 창 열기 정상 수행시 0 반환, 취소시 1 반환

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    label.setText(fileChooser.getSelectedFile().getPath());
                    // 레이블에 파일 경로 넣기
                }
            }
        });

    }
}