package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchCustomer extends JFrame implements ActionListener {

    private JTextField query;
    private JButton search;
    private String searchQuery;
    private java.util.List<Customer> list;
    Connection connection;
    private List<String> dropdownList;
    JComboBox box;
    CustomerView view;
    JButton update;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    static final String USER = "root";
    Statement statement;
    ResultSet rs;
    JLabel label;

    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting");
            connection = DriverManager.getConnection(DB_URL, USER, "admin@123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select * from item");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    SearchCustomer(){
        getConnection();
        setTitle("Jewellery Management : Item Search");
        setBounds(0,0,600,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        label = new RandomBackground(1);
        add(label);

        query = new JTextField();
        query.setBounds(100,20,400,40);
        label.add(query);

        search = new JButton("Search by name");
        search.setBounds(200,80,200,40);
        search.addActionListener(this);
        label.add(search);

        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dropdownList = new ArrayList<>();
        if (e.getSource() == search) {
            list = new ArrayList<>();
            searchQuery = "SELECT * FROM customer WHERE c_name=\"" + query.getText().toString() + "\";";
            try {
                rs = statement.executeQuery(searchQuery);
                rs.first();
                while (!rs.isAfterLast()) {
                    Customer item = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    list.add(item);
                    dropdownList.add(item.getId() + "," + item.getName() + "," + item.getAddress() + "," + item.getPhoneNo());
                    rs.next();
                }
                box = new JComboBox(dropdownList.toArray());
                box.setBounds(200, 150, 200, 20);
                box.addActionListener(this);
                label.setSize(599, 599);
                label.add(box);
                label.setSize(600, 600);
                /*
*/
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(label, "Data doesnot exist");
            }
        }
        if (e.getSource() == box) {
            if (view != null) {
                view.setVisible(false);
                label.remove(view);
                view = null;
            }
            view = new CustomerView(list.get(box.getSelectedIndex()));
            view.setBounds(200, 180, 600, 600);
            view.setVisible(true);
            label.setSize(599, 599);
            label.add(view);

            update = new JButton("Update");
            update.setBounds(260, 500, 80, 30);
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdateCustomer(list.get(box.getSelectedIndex()));
                    dispose();
                }
            });
            label.add(update);
        }
    }
}
