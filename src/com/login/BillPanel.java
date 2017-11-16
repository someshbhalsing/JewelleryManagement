package com.login;

import com.sun.corba.se.impl.oa.toa.TOA;

import javax.swing.*;
import java.awt.*;

public class BillPanel extends JPanel {

    private JLabel BillNo,CustName,ItemName,Category,Weight,Rate,Total;

    BillPanel(){
        setLayout(new FlowLayout());

        BillNo = new JLabel("Bill No");
        add(BillNo);

        CustName = new JLabel("Customer Name");
        add(CustName);

        ItemName = new JLabel("Item Name");
        add(ItemName);

        Category = new JLabel("Category");
        add(Category);

        Weight = new JLabel("Weight");
        add(Weight);

        Rate = new JLabel("Rate");
        add(Rate);

        Total = new JLabel("Total");
        add(Total);

        setOpaque(true);
        setBackground(new Color(0,0,0,0));
    }

    BillPanel(Item item,Customer customer,int billNo,String rate){
        BillNo = new JLabel(String.valueOf(billNo));
        add(BillNo);

        CustName = new JLabel(customer.getName());
        add(CustName);

        ItemName = new JLabel(item.getName());
        add(ItemName);

        Category = new JLabel(item.getCategory());
        add(Category);

        Weight = new JLabel(item.getWeight());
        add(Weight);

        Rate = new JLabel(rate);
        add(Rate);

        String total = new String(String.valueOf(Double.parseDouble(item.getWeight())*Double.parseDouble(rate)));
        Total = new JLabel(total);
        add(Total);

        setOpaque(true);
        setBackground(new Color(0,0,0,0));
    }
}
