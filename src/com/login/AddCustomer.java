package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCustomer extends JFrame implements ActionListener {

    Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    static final String USER = "root";
    Statement statement;
    ResultSet rs;

    JLabel no,name,address,phone,backGround;
    JTextField textNo,textName,textAddress,textPhone;
    JButton submit;

    AddCustomer(){
        getConnection();
        setTitle("Jewellery Management : Add Customer");
        setBounds(150,150,600,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        backGround = new RandomBackground(2);
        add(backGround);

        JLabel label1 = new JLabel("<html><h1><font color='white'>Add Customer</font><h1></html>");
        label1.setBounds(10,10,400,40);
        backGround.add(label1);

        name = new JLabel("<html><font color='white'>Name : </font></html>");
        name.setBounds(70,120,90,30);
        backGround.add(name);

        address = new JLabel("<html><font color='white'>Address : </font></html>");
        address.setBounds(70,170,90,30);
        backGround.add(address);

        phone = new JLabel("<html><font color='white'>Phone : </font></html>");
        phone.setBounds(70,220,90,30);
        backGround.add(phone);

        textName = new JTextField();
        textName.setBounds(160,120,200,30);
        backGround.add(textName);

        textAddress = new JTextField();
        textAddress.setBounds(160,170,200,30);
        backGround.add(textAddress);

        textPhone = new JTextField();
        textPhone.setBounds(160,220,200,30);
        backGround.add(textPhone);

        submit = new JButton("Add");
        submit.setBounds(70,370,300,30);
        submit.addActionListener(this);
        backGround.add(submit);

        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textPhone.getText().toString().length()!=10){
            JOptionPane.showMessageDialog(this, "Invalid phone number");
            return;
        }
        if(textAddress.getText().toString() == null || textName.getText().toString() == null){
            JOptionPane.showMessageDialog(this, "Mandatory field cannot be left empty");
            return;
        }
        try {
            rs.moveToInsertRow();
            rs.updateString(2,textName.getText());
            rs.updateString(3,textAddress.getText());
            rs.updateString(4,textPhone.getText());
            rs.insertRow();
        }catch (SQLException e1){
            System.out.println("" + e1.toString());
            JOptionPane.showMessageDialog(this, "Error inserting item");
            return;
        }
        JOptionPane.showMessageDialog(this, "Customer data added successfully");
        try {
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
        return;
    }
    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting");
            connection = DriverManager.getConnection(DB_URL,USER,"admin@123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select * from customer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
