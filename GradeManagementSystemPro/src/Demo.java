import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Demo extends JFrame implements ActionListener {
    private JTextField tfName, tfStudentNumber, tfCollege, tfMajor, tfClass, tfGender, tfCircuitScore, tfCScore, tfMCUScore;
    private JButton btnSave, btnQuery, btnAdd, btnUpdate, btnDelete;
    private JTable table;
    private DefaultTableModel tableModel;

    public Demo() {
        setTitle("个人成绩信息管理系统");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建输入框和按钮
        tfName = new JTextField(10);
        tfStudentNumber = new JTextField(10);
        tfCollege = new JTextField(10);
        tfMajor = new JTextField(10);
        tfClass = new JTextField(10);
        tfGender = new JTextField(10);
        tfCircuitScore = new JTextField(10);
        tfCScore = new JTextField(10);
        tfMCUScore = new JTextField(10);

        btnSave = new JButton("保存");
        btnSave.addActionListener(this);
        btnQuery = new JButton("查询");
        btnQuery.addActionListener(this);
        btnAdd = new JButton("添加");
        btnAdd.addActionListener(this);
        btnUpdate = new JButton("修改");
        btnUpdate.addActionListener(this);
        btnDelete = new JButton("删除");
        btnDelete.addActionListener(this);

        // 创建表格
        String[] columnNames = {"姓名", "学号", "学院", "专业", "班级", "性别", "电路成绩", "C语言成绩", "单片机成绩"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // 创建面板，并将输入框和按钮添加到面板上
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("姓名:"), constraints);

        constraints.gridx = 1;
        panel.add(tfName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("学号:"), constraints);

        constraints.gridx = 1;
        panel.add(tfStudentNumber, constraints);

        // 添加其他输入框和按钮的代码省略...

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(btnSave, constraints);

        constraints.gridy = 2;
        panel.add(btnQuery, constraints);

        constraints.gridy = 3;
        panel.add(btnAdd, constraints);

        constraints.gridy = 4;
        panel.add(btnUpdate, constraints);

        constraints.gridy = 5;
        panel.add(btnDelete, constraints);

        // 将面板和表格添加到主窗口上
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            saveData();
        } else if (e.getSource() == btnQuery) {
            queryData();
        } else if (e.getSource() == btnAdd) {
            addData();
        } else if (e.getSource() == btnUpdate) {
            updateData();
        } else if (e.getSource() == btnDelete) {
            deleteData();
        }
    }

    private void saveData() {
        // 实现保存数据的逻辑
    }

    private void queryData() {
        // 实现查询数据的逻辑
    }

    private void addData() {
        // 实现添加数据的逻辑
    }

    private void updateData() {
        // 实现修改数据的逻辑
    }

    private void deleteData() {
        // 实现删除数据的逻辑
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Demo().setVisible(true);
            }
        });
    }
}
