package test;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.regex.PatternSyntaxException;

public class TableSearchExample extends JFrame {
    private final JTextField searchField = new JTextField(10);
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final TableRowSorter<DefaultTableModel> sorter;
    
    public static void main(String[] args) {
        TableSearchExample example = new TableSearchExample();
        example.setVisible(true);
    }

    public TableSearchExample() {
        setTitle("JTable Search Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 테이블 모델 초기화
        tableModel = new DefaultTableModel();
        tableModel.addColumn("이름");
        tableModel.addColumn("나이");
        tableModel.addColumn("주소");
        tableModel.addRow(new Object[]{"홍길동", 30, "서울"});
        tableModel.addRow(new Object[]{"김철수", 25, "부산"});
        tableModel.addRow(new Object[]{"이영희", 27, "순천"});

        // 테이블 초기화
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // 검색 필드 초기화
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }

            private void search() {
                String searchText = searchField.getText().trim();

                // 검색어가 없으면 모든 행을
                if (searchText.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                    } catch (PatternSyntaxException ex) {
                        // 검색어가 정규식으로 변환되지 않으면 모든 행을 보여줍니다.
                        sorter.setRowFilter(null);
                    }
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 선택된 행이 변경되면 호출됩니다.
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // 선택된 행의 인덱스를 모델에서의 인덱스로 변환합니다.
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    // 모델에서 선택된 행의 데이터를 가져옵니다.
                    String name = tableModel.getValueAt(modelRow, 0).toString();
                    int age = Integer.parseInt(tableModel.getValueAt(modelRow, 1).toString());
                    // 선택된 행의 데이터를 출력
                    System.out.println("Selected row: " + name + ", " + age);
                }
            }
        });
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("검색"));
        searchPanel.add(searchField);

        add(searchPanel, "South");

        pack();
        setLocationRelativeTo(null);
    }


}