package manage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherManagementSystem extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JLabel loginLabel, registerLabel;
    private JLabel usernameLabel, passwordLabel, confirmPasswordLabel, roleLabel;
    private JTextField usernameField, confirmPasswordField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton loginButton, registerButton;

    private JLabel nameLabel, genderLabel, ageLabel, majorLabel, phoneLabel;
    private JTextField nameField, genderField, ageField, majorField, phoneField;
    private JButton addButton, resetButton;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton updateButton, deleteButton;

    private UserDAO userDao;
    private TeacherDAO teacherDao;

    public TeacherManagementSystem() {
        setTitle("教师信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 初始化用户数据访问层对象
        userDao = new UserDAO();
        // 初始化教师数据访问层对象
        teacherDao = new TeacherDAO();

        // 登录面板
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginLabel = new JLabel("登录");
        loginLabel.setBounds(200, 20, 80, 30);
        loginPanel.add(loginLabel);
        usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(10, 60, 80, 30);
        loginPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(100, 60, 150, 30);
        loginPanel.add(usernameField);
        passwordLabel = new JLabel("密 码：");
        passwordLabel.setBounds(10, 100, 80, 30);
        loginPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 150, 30);
        loginPanel.add(passwordField);
        loginButton = new JButton("登录");
        loginButton.setBounds(120, 150, 80, 30);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        // 注册面板
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        registerLabel = new JLabel("注册");
        registerLabel.setBounds(200, 20, 80, 30);
        registerPanel.add(registerLabel);
        usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(10, 60, 80, 30);
        registerPanel.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setBounds(100, 60, 150, 30);
        registerPanel.add(usernameField);
        passwordLabel = new JLabel("密 码：");
        passwordLabel.setBounds(10, 100, 80, 30);
        registerPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 100, 150, 30);
        registerPanel.add(passwordField);
        confirmPasswordLabel = new JLabel("确认密码：");
        confirmPasswordLabel.setBounds(10, 140, 80, 30);
        registerPanel.add(confirmPasswordLabel);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(100, 140, 150, 30);
        registerPanel.add(confirmPasswordField);
        roleLabel = new JLabel("角 色：");
        roleLabel.setBounds(10, 180, 80, 30);
        registerPanel.add(roleLabel);
        roleComboBox = new JComboBox<>(new String[]{"教师", "管理员"});
        roleComboBox.setBounds(100, 180, 150, 30);
        registerPanel.add(roleComboBox);
        registerButton = new JButton("注册");
        registerButton.setBounds(120, 230, 80, 30);
        registerButton.addActionListener(this);
        registerPanel.add(registerButton);

        // 教师信息管理面板
        JPanel teacherPanel = new JPanel();
        teacherPanel.setLayout(null);
        nameLabel = new JLabel("姓 名：");
        nameLabel.setBounds(10, 10, 80, 30);
        teacherPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(100, 10, 150, 30);
        teacherPanel.add(nameField);
        genderLabel = new JLabel("性 别：");
        genderLabel.setBounds(10, 50, 80, 30);
        teacherPanel.add(genderLabel);
        genderField = new JTextField();
        genderField.setBounds(100, 50, 150, 30);
        teacherPanel.add(genderField);
        ageLabel = new JLabel("年 龄：");
        ageLabel.setBounds(10, 90, 80, 30);
        teacherPanel.add(ageLabel);
        ageField = new JTextField();
        ageField.setBounds(100, 90, 150, 30);
        teacherPanel.add(ageField);
        majorLabel = new JLabel("专 业：");
        majorLabel.setBounds(10, 130, 80, 30);
        teacherPanel.add(majorLabel);
        majorField = new JTextField();
        majorField.setBounds(100, 130, 150, 30);
        teacherPanel.add(majorField);
        phoneLabel = new JLabel("电 话：");
        phoneLabel.setBounds(10, 170, 80, 30);
        teacherPanel.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(100, 170, 150, 30);
        teacherPanel.add(phoneField);
        addButton = new JButton("添 加");
        addButton.setBounds(10, 210, 80, 30);
        addButton.addActionListener(this);
        teacherPanel.add(addButton);
        resetButton = new JButton("重 置");
        resetButton.setBounds(120, 210, 80, 30);
        resetButton.addActionListener(this);
        teacherPanel.add(resetButton);
        updateButton = new JButton("修 改");
        updateButton.setBounds(230, 210, 80, 30);
        updateButton.addActionListener(this);
        teacherPanel.add(updateButton);
        deleteButton = new JButton("删 除");
        deleteButton.setBounds(340, 210, 80, 30);
        deleteButton.addActionListener(this);
        teacherPanel.add(deleteButton);

        // 教师信息展示表格
        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 10, 480, 530);
        teacherPanel.add(scrollPane);

        // 添加面板到JFrame中
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("登录", null, loginPanel, "登录");
        tabbedPane.addTab("注册", null, registerPanel, "注册");
        tabbedPane.addTab("教师信息管理", null, teacherPanel, "教师信息管理");
        getContentPane().add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = userDao.getUserByUsernameAndPassword(username, password);
            if (user != null && user.getRole().equals("管理员")) {
                JOptionPane.showMessageDialog(this, "登录成功，欢迎您，管理员：" + username);
            } else if (user != null && user.getRole().equals("教师")) {
                JOptionPane.showMessageDialog(this, "登录成功，欢迎您，教师：" + username);
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误！");
            }
        } else if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();
            if (password.equals(confirmPassword)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                boolean result = userDao.addUser(user);
                if (result) {
                    JOptionPane.showMessageDialog(this, "注册成功！");
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "注册失败！");
                }
            } else
                