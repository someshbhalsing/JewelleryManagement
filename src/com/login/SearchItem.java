package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchItem extends JFrame implements ActionListener {

    private JTextField query;
    private JButton search;
    private String searchQuery;
    private List<Item> list;
    private List<Customer> custList;
    private List<String> custDropDown;
    Connection connection;
    private List<String> dropdownList;
    JComboBox box;
    JTextField field;
    ItemView view;
    int billNo;
    JButton sell,delete,update;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    static final String USER = "root";
    Statement statement;
    ResultSet rs,rs1,rs2;
    JLabel label;
    JComboBox custBox;

    SearchItem (){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        dropdownList = new ArrayList<>();
        if(e.getSource()==search){
            list = new ArrayList<>();
            searchQuery = "SELECT * FROM item WHERE item_name=\""+query.getText().toString()+"\";";
            try {
                rs = statement.executeQuery(searchQuery);
                rs.first();
                while(!rs.isAfterLast()) {
                    Item item = new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    list.add(item);
                    dropdownList.add(item.getId()+","+item.getName()+","+item.getWeight()+","+item.getCategory()+","+item.getStatus());
                    rs.next();
                }
                box = new JComboBox(dropdownList.toArray());
                box.setBounds(200,150,200,20);
                box.addActionListener(this);
                label.setSize(599,599);
                label.add(box);
                label.setSize(600,600);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(label, "Data doesnot exist");
            }
        }
        if(e.getSource() == box){
            if(view!=null){
                view.setVisible(false);
                label.remove(view);
                view = null;
            }
            view = new ItemView(list.get(box.getSelectedIndex()));
            view.setBounds(225,180,600,600);
            view.setVisible(true);
            label.setSize(599,599);
            label.add(view);

            sell = new JButton("Sell");
            sell.setBounds(100,500,80,30);
            label.add(sell);

            update = new JButton("Update");
            update.setBounds(250,500,80,30);
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UpdateItem(list.get(box.getSelectedIndex()));
                    dispose();
                }
            });
            label.add(update);

            delete = new JButton("Delete");
            delete.setBounds(400,500,80,30);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int ans = JOptionPane.showConfirmDialog(label,"Are you sure");
                        if(ans != 0){
                            return;
                        }
                        rs = statement.executeQuery("select * from item where item_code="+String.valueOf(list.get(box.getSelectedIndex()).getId()));
                        rs.first();
                        rs.deleteRow();
                        dispose();
                        new SearchItem();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(label, "Data doesnot exist");
                    }
                }
            });
            label.add(delete);

            if(list.get(box.getSelectedIndex()).getStatus().equals(new String("sold"))) {
                sell.setEnabled(false);
            }
            sell.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    custDropDown = new ArrayList<>();
                    custList = new ArrayList<>();
                    try {
                        rs = statement.executeQuery("select * from customer");
                        rs.first();
                        while (!rs.isAfterLast()) {
                            Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                            custList.add(customer);
                            custDropDown.add(new String(customer.getId() + "," + customer.getName() + "," + customer.getAddress() + "," + customer.getPhoneNo()));
                            rs.next();
                        }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(label, "Customer doesnot exist");
                    }
                    custBox = new JComboBox(custDropDown.toArray());
                    int res = JOptionPane.showConfirmDialog(label, custBox, "select a customer", JOptionPane.OK_CANCEL_OPTION);
                    if(res!=0){
                        return;
                    }
                    try {
                        rs1 = statement.executeQuery("select * from purchase");
                    } catch (SQLException e1) {
                        System.out.println(e1.toString());
                    }
                    try {
                        rs1.moveToInsertRow();
                        rs1.updateInt(1, custList.get(custBox.getSelectedIndex()).getId());
                        rs1.updateInt(2, list.get(box.getSelectedIndex()).getId());
                        rs1.insertRow();
                    }catch (SQLException e1){
                        JOptionPane.showMessageDialog(label,"Error while purchasing");
                    }
                    field = new JTextField();
                    JOptionPane.showMessageDialog(label,field,"Per gram rate",JOptionPane.QUESTION_MESSAGE);
                    try {
                        rs2 = statement.executeQuery("select * from bill");
                        rs2.moveToInsertRow();
                        rs2.updateString(2, field.getText().toString());
                        rs2.updateString(3, String.valueOf(Double.valueOf(field.getText().toString()) * Double.parseDouble(list.get(box.getSelectedIndex()).getWeight())));
                        rs2.insertRow();
                        rs2 = statement.executeQuery("select max(bill_no) from bill");
                        rs2.first();
                        billNo = rs2.getInt(1);

                    }catch (SQLException e1){
                        System.out.println(e1.toString());
                        JOptionPane.showMessageDialog(label,"Error while generating bill");
                    }
                    try{
                        rs2 = statement.executeQuery("select * from pay");
                        rs2.moveToInsertRow();
                        rs2.updateInt(1,custList.get(custBox.getSelectedIndex()).getId());
                        rs2.updateInt(2,billNo);
                        rs2.insertRow();
                        rs2 = statement.executeQuery("select * from has4");
                        rs2.moveToInsertRow();
                        rs2.updateInt(2,billNo);
                        rs2.updateInt(1,list.get(box.getSelectedIndex()).getId());
                        rs2.insertRow();
                    }catch (SQLException e1){
                        System.out.println(e1.toString());
                        JOptionPane.showMessageDialog(label,"Error while generating bill");
                    }
                    JOptionPane.showMessageDialog(label,"Transaction Successful");
                    dispose();
                }
            });
            label.setSize(600,600);
        }
    }
}