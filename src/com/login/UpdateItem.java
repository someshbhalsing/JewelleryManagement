package com.login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class UpdateItem extends JFrame implements ActionListener {

    Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    static final String USER = "root";
    Statement statement;
    ResultSet rs;
    JLabel name, category, weight;
    JTextField textName, textWeight;
    JButton button;
    JRadioButton gold, silver, diamond;
    Item item ;


    UpdateItem(Item item) {
        this.item = item;
        setTitle("Jewellery Management : Update Item");
        setBounds(0, 0, 600, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JLabel label = new RandomBackground(5);
        add(label);

        JLabel label1 = new JLabel("<html><h1><font color='white'>Update Item</font><h1></html>");
        label1.setBounds(10, 10, 400, 40);
        label.add(label1);

        name = new JLabel("<html><font color='white'>Name : </font></html>");
        name.setBounds(70, 220, 80, 30);
        label.add(name);

        category = new JLabel("<html><font color='white'>Category : </font></html>");
        category.setBounds(70, 270, 80, 30);
        label.add(category);

        weight = new JLabel("<html><font color='white'>Weight (gms) : </font></html>");
        weight.setBounds(70, 320, 80, 30);
        label.add(weight);

        textName = new JTextField(item.getName());
        textName.setBounds(160, 220, 200, 30);
        label.add(textName);

        JPanel panel = new JPanel(new FlowLayout());
        gold = new JRadioButton("Gold");
        silver = new JRadioButton("Silver");
        diamond = new JRadioButton("Diamond");
        ButtonGroup radios = new ButtonGroup();
        radios.add(gold);
        radios.add(silver);
        radios.add(diamond);
        panel.add(gold);
        panel.add(silver);
        panel.add(diamond);
        switch (item.getCategory()){
            case "Gold":
                gold.setSelected(true);
                break;
            case "Silver":
                silver.setSelected(true);
                break;
            case "Diamond":
                diamond.setSelected(true);
                break;
        }
        panel.setBounds(160, 270, 200, 30);
        label.add(panel);

        textWeight = new JTextField(item.getWeight());
        textWeight.setBounds(160, 320, 200, 30);
        label.add(textWeight);

        button = new JButton("Update");
        button.setBounds(70, 470, 300, 30);
        label.add(button);

        button.addActionListener(this);
        getConnection();
        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(textName.getText()==null){
                JOptionPane.showMessageDialog(this, "Name cannot be left empty");
                return;
            }
            try {
                Double.parseDouble(textWeight.getText());
            }catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(this, "Weight should be an integer value");
                return;
            }
            String category = "Gold";
            rs.first();
            rs.updateString(2, textName.getText());
            if (gold.isSelected()) {
                category = "Gold";
            } else if (silver.isSelected()) {
                category = "Silver";
            } else if (diamond.isSelected()) {
                category = "Diamond";
            }
            rs.updateString(3, category);
            rs.updateString(4, textWeight.getText());
            rs.updateString(5, "Available");
            rs.updateRow();
        } catch (SQLException err) {
            System.out.println("" + err.toString());
            JOptionPane.showMessageDialog(this, "Item code already exists");
            return;
        }
        closeConnection();
        JOptionPane.showMessageDialog(this, "Item updates successfully");
        dispose();
    }

    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting");
            connection = DriverManager.getConnection(DB_URL, USER, "admin@123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select * from item where item_code=\""+item.getId()+"\";");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}