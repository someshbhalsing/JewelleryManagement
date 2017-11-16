package com.login;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Login extends JFrame implements ActionListener {

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    private static final String USER = "root";
    private Statement statement;
    private ResultSet rs;

    JLabel labelName,labelPassword;
    JTextField textName;
    JPasswordField passwordField;
    JButton buttonLogin;

    Login() {

        getConnection();

        setTitle("Jewellery Management");
        setBounds(150,150,400,400);
        setLayout(null);

        labelName = new JLabel("User Id : ");
        labelName.setBounds(50,50,80,30);
        add(labelName);

        labelPassword = new JLabel("Password : ");
        labelPassword.setBounds(50,100,80,30);
        add(labelPassword);

        textName = new JTextField();
        textName.setBounds(150,50,100,30);
        add(textName);

        passwordField = new JPasswordField();
        passwordField.setBounds(150,100,100,30);
        add(passwordField);

        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(150,150,80,30);
        add(buttonLogin);

        buttonLogin.addActionListener(this);
        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting");
            connection = DriverManager.getConnection(DB_URL,USER,"admin@123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select * from user");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean success = false;
        try {
            List<User> list = new ArrayList<>();
                rs.first();
                while(!rs.isAfterLast()){
                    User user = new User(rs.getString(1),rs.getString(2));
                    list.add(user);
                    rs.next();
                }
            User user = new User(textName.getText().toString(),String.valueOf(passwordField.getPassword()));
                for(int i=0;i<list.size();i++){
                    if(user.equals(list.get(i))){
                        System.out.println("Login Successful");
                        new Home();
                        dispose();
                        return;
                    }
                }
                if(!success){
                    JOptionPane.showMessageDialog(this, "Invalid User Id");
                    return;
                }
        }
         catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
