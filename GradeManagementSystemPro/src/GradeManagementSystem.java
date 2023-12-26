

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * @Author
 * @Description // 个人成绩信息管理系统
 * @Date 16:37 2023/11/23
 * @Param
 * @return
 **/
public class GradeManagementSystem extends JFrame implements ActionListener {

    //组件声明
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton searchButton;
    private JTextField textField;


    //构造方法初始化
    public GradeManagementSystem() {
        setTitle("个人成绩信息管理系统");
        //设置大小
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格和数据模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("学号");
        tableModel.addColumn("学院");
        tableModel.addColumn("专业");
        tableModel.addColumn("班级");
        tableModel.addColumn("性别");
        tableModel.addColumn("电路成绩");
        tableModel.addColumn("C语言成绩");
        tableModel.addColumn("单片机成绩");
        table = new JTable(tableModel);

        // 创建按钮
        addButton = new JButton("添加");
        modifyButton = new JButton("修改");
        deleteButton = new JButton("删除");
        saveButton = new JButton("保存");
        loadButton = new JButton("加载");
        searchButton = new JButton("查询");

        //文本框
        textField = new JTextField();
        textField.setColumns(10);

        // 添加按钮事件监听器
        addButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        searchButton.addActionListener(this);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(textField);

        // 将表格和按钮面板添加到窗口中
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    //按钮事件监听
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // 添加按钮事件处理
            addGrade();
        } else if (e.getSource() == modifyButton) {
            // 修改按钮事件处理
            modifyGrade();
        } else if (e.getSource() == deleteButton) {
            // 删除按钮事件处理
            deleteGrade();
        } else if (e.getSource() == saveButton) {
            // 保存按钮事件处理
            saveData();
        } else if (e.getSource() == loadButton) {
            // 加载按钮事件处理
            loadData();
        }else if(e.getSource() == searchButton){
            //学号搜索
            if (null != textField.getText() && !"".equals(textField.getText())){
                updateTableContentByStudentNumber(textField.getText());
            }else{
                //为空选择查询所有
                loadData();
            }

        }
    }

    //添加
    private void addGrade() {
        // 创建对话框
        JDialog dialog = new JDialog(this, "添加成绩", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new BorderLayout());

        // 创建表单面板
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2));
        formPanel.add(new JLabel("姓名:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("学号:"));
        JTextField idField = new JTextField();
        formPanel.add(idField);
        formPanel.add(new JLabel("学院:"));
        JTextField collegeField = new JTextField();
        formPanel.add(collegeField);
        formPanel.add(new JLabel("专业:"));
        JTextField majorField = new JTextField();
        formPanel.add(majorField);
        formPanel.add(new JLabel("班级:"));
        JTextField classField = new JTextField();
        formPanel.add(classField);
        formPanel.add(new JLabel("性别:"));
        JTextField genderField = new JTextField();
        formPanel.add(genderField);
        formPanel.add(new JLabel("电路成绩:"));
        JTextField circuitField = new JTextField();
        formPanel.add(circuitField);
        formPanel.add(new JLabel("C语言成绩:"));
        JTextField cLanguageField = new JTextField();
        formPanel.add(cLanguageField);
        formPanel.add(new JLabel("单片机成绩:"));
        JTextField microcontrollerField = new JTextField();
        formPanel.add(microcontrollerField);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("确定");
        JButton cancelButton = new JButton("取消");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // 绑定按钮事件
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 添加成绩信息到表格
                Vector<String> rowData = new Vector<String>();
                rowData.add(nameField.getText());
                rowData.add(idField.getText());
                rowData.add(collegeField.getText());
                rowData.add(majorField.getText());
                rowData.add(classField.getText());
                rowData.add(genderField.getText());
                rowData.add(circuitField.getText());
                rowData.add(cLanguageField.getText());
                rowData.add(microcontrollerField.getText());
                tableModel.addRow(rowData);
                dialog.dispose();
            }
        });
        //取消
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // 将表单和按钮面板添加到对话框中
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        //设置显示
        dialog.setVisible(true);
    }


    //修改
    private void modifyGrade() {
        // 获取选中的行
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "请先选中要修改的行");
        } else {
            // 创建对话框
            JDialog dialog = new JDialog(this, "修改成绩", true);
            dialog.setSize(300, 200);
            dialog.setLayout(new BorderLayout());
            // 创建表单面板
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(9, 2));
            formPanel.add(new JLabel("姓名:"));
            JTextField nameField = new JTextField(table.getValueAt(selectedRow, 0).toString());
            formPanel.add(nameField);
            formPanel.add(new JLabel("学号:"));
            JTextField idField = new JTextField(table.getValueAt(selectedRow, 1).toString());
            formPanel.add(idField);
            formPanel.add(new JLabel("学院:"));
            JTextField collegeField = new JTextField(table.getValueAt(selectedRow, 2).toString());
            formPanel.add(collegeField);
            formPanel.add(new JLabel("专业:"));
            JTextField majorField = new JTextField(table.getValueAt(selectedRow, 3).toString());
            formPanel.add(majorField);
            formPanel.add(new JLabel("班级:"));
            JTextField classField = new JTextField(table.getValueAt(selectedRow, 4).toString());
            formPanel.add(classField);
            formPanel.add(new JLabel("性别:"));
            JTextField genderField = new JTextField(table.getValueAt(selectedRow, 5).toString());
            formPanel.add(genderField);
            formPanel.add(new JLabel("电路成绩:"));
            JTextField circuitField = new JTextField(table.getValueAt(selectedRow, 6).toString());
            formPanel.add(circuitField);
            formPanel.add(new JLabel("C语言成绩:"));
            JTextField cLanguageField = new JTextField(table.getValueAt(selectedRow, 7).toString());
            formPanel.add(cLanguageField);
            formPanel.add(new JLabel("单片机成绩:"));
            JTextField microcontrollerField = new JTextField(table.getValueAt(selectedRow, 8).toString());
            formPanel.add(microcontrollerField);

            // 创建按钮面板
            JPanel buttonPanel = new JPanel();
            JButton confirmButton = new JButton("确定");
            JButton cancelButton = new JButton("取消");
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            // 绑定按钮事件
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 更新表格中的数据
                    table.setValueAt(nameField.getText(), selectedRow, 0);
                    table.setValueAt(idField.getText(), selectedRow, 1);
                    table.setValueAt(collegeField.getText(), selectedRow, 2);
                    table.setValueAt(majorField.getText(), selectedRow, 3);
                    table.setValueAt(classField.getText(), selectedRow, 4);
                    table.setValueAt(genderField.getText(), selectedRow, 5);
                    table.setValueAt(circuitField.getText(), selectedRow, 6);
                    table.setValueAt(cLanguageField.getText(), selectedRow, 7);
                    table.setValueAt(microcontrollerField.getText(), selectedRow, 8);
                    dialog.dispose();
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            // 将表单和按钮面板添加到对话框中
            dialog.add(formPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        }
    }


    //删除
    private void deleteGrade() {
        // 获取选中的行
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "请先选中要删除的行");
        } else {
            // 确认是否删除
            int result = JOptionPane.showConfirmDialog(null, "确定删除该行数据吗?", "删除确认", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        }

    }


    //保存文件到本地
    private void saveData() {
        // 创建文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        // 保存数据到文件
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        writer.write(table.getValueAt(i, j).toString());
                        if (j != table.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");
                }
                writer.flush();
                JOptionPane.showMessageDialog(null, "保存成功");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "保存失败");
            }
        }
    }


    //初始加载查询
    private void loadData() {
        // 创建文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        // 加载数据到表格
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                clearTable();
                while ((line = reader.readLine()) != null) {
                    String[] rowData = line.split(",");
                    tableModel.addRow(rowData);
                }
                JOptionPane.showMessageDialog(null, "加载成功");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "加载失败");
            }
        }
    }


    //根据搜索框内容查询
    private void updateTableContentByStudentNumber(String studentNumber) {
        // 清空表格

        // 遍历表格数据，查找匹配的学号
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String currentStudentNumber = (String) tableModel.getValueAt(i, 1);
            if (currentStudentNumber.equals(studentNumber)) {
                // 将匹配的行添加到表格中
                String[] rowData = new String[tableModel.getColumnCount()];
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    rowData[j] = (String) tableModel.getValueAt(i, j);
                }
                clearTable();
                tableModel.addRow(rowData);
            }
        }
    }


    //清除表格内容
    private void clearTable() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }


    //主程序
    public static void main(String[] args) {
        GradeManagementSystem system = new GradeManagementSystem();
        system.setVisible(true);
    }
}
