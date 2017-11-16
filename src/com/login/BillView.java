package com.login;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillView extends JFrame {

    Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost/jewel";
    static final String USER = "root";
    Statement statement;
    ResultSet rs,rs1,rs2;
    List<String[]> list;


    BillView(){
        list = new ArrayList<>();

        getConnection();
        try{
            rs.first();
            while(!rs.isAfterLast()) {
                int billNo = rs.getInt(1);
                String custName = rs.getString(2);
                String itemName = rs.getString(3);
                String category = rs.getString(4);
                String wt = rs.getString(5);
                String rate = rs.getString(6);
                String total = rs.getString(7);
                String[] object = new String[]{String.valueOf(billNo),custName,itemName,category,wt,rate,total};
                list.add(object);
                rs.next();
            }
        }catch (SQLException e1){
            e1.printStackTrace();
        }
        setTitle("Jewellery Management : Bill View");
        setBounds(0,0,700,700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String[] columns = {"Bill no.","Customer Name","Item Name","Category","Weight","Rate","Total"};
        String[][] data = (String[][]) list.toArray(new String[0][]);
        JTable table = new JTable(data,columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        getContentPane().add(scrollPane,BorderLayout.CENTER);
        setSize(700,700);

        setVisible(true);
        setIconImage(new ImageIcon("C:\\Users\\somesh\\IdeaProjects\\JewelleryManagement\\src\\com\\login\\icons8-Diamond-100.png").getImage());

    }
    public void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting");
            connection = DriverManager.getConnection(DB_URL,USER,"admin@123");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select bill.bill_no,c_name,item.item_name,category,weight,rate_per_gms,total_amount from bill,customer,pay,item,has4 where customer.c_no=pay.c_no and bill.bill_no=pay.bill_no and item.item_code=has4.item_code and bill.bill_no=has4.bill_no");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
